package com.chinadovey.parking.webapps.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.chinadovey.parking.webapps.mina.heart.HeartPackets;
import com.chinadovey.parking.webapps.mina.protocol.CMDPackets;
import com.chinadovey.parking.webapps.mina.protocol.DASPackets;
import com.chinadovey.parking.webapps.mina.protocol.DASReportPackets;
import com.chinadovey.parking.webapps.mina.protocol.DASSerialPortPackets;
import com.chinadovey.parking.webapps.mina.protocol.ERRPackets;
import com.chinadovey.parking.webapps.mina.protocol.PacketsEntity;
import com.chinadovey.parking.webapps.mina.protocol.RESPackets;
import com.chinadovey.parking.webapps.mina.protocol.ValuePackets;
import com.chinadovey.parking.webapps.utils.ByteUtil;

/**
 * 
 * 数据流解析，提取socket中的符合{@link PacketsRequest}的数据，并返回由{@link CloudNodeIoHandler}处理
 * 
 * @author Bean
 * 
 */
public class DPSProtocolDecoder extends CumulativeProtocolDecoder {

	private int m = 0;

	private byte p = 0x00;

	private int buf_idx = -1;
	private byte[] buf = new byte[4];

	private int buf2_idx = 0;
	private int buf2_len = 0;
	private byte[] buf2;

	@Override
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {

		byte c;
		while (in.hasRemaining()) {
			c = in.get();
			if (c == PacketsEntity.START_FLAG_C && p == c) {
				m = 1;
				buf_idx = 0;
				continue;
			}

			switch (m) {
			case 0:
				break;
			case 1:
				buf[buf_idx++] = c;
				if (buf_idx == 4) {
					m = 2;

					buf2_idx = 0;
					buf2_len = (buf[3] & 0xFF) + (buf[2] & 0xFF00);
					if (buf2_len <= 0) {
						m = 0;
						break;
					}
					buf2 = new byte[buf2_len];
				}
				break;
			case 2:
				buf2[buf2_idx] = c;
				buf2_idx++;

				if (buf2_idx == buf2_len) {
					m = 0;
					PacketsEntity data = new PacketsEntity();

					data.setTag(new byte[] { buf[0], buf[1] });
					data.setIndex(new byte[] { buf2[0], buf2[1] });
					byte[] value = new byte[buf2.length - 3];
					for (int i = 0; i < buf2.length - 3; i++) {
						value[i] = buf2[i + 2];
					}

					ValuePackets valuePackets = null;
					switch (ByteUtil.makeIntFromByte(data.getTag())) {
					case 0x0001 /* 0001H DAS上报数据包 */:
						valuePackets = new DASPackets();
						break;
					case 0x0002 /* 0002H DAS错误包 */:
						valuePackets = new ERRPackets();
						break;
					case 0x0003 /* 0003H DAS发送的回应包 */:
						valuePackets = new RESPackets();
						break;
					case 0x0004 /* 0004H DAS首次上报数据包 */:
						valuePackets = new DASReportPackets();
						break;
					case 0x0005 /* 0005H DAS心跳回复包 */:
						valuePackets = new HeartPackets();
						break;
					case 0x0100 /* 0100H DPS给DAS的回应包,无需处理 */:
						break;
					case 0x0101 /* 0101H DPS给DAS的命令包 */:
						valuePackets = new CMDPackets();
						break;
					case 0x0201 /* 0201H APP给DPS的命令包 */:
						valuePackets = new CMDPackets();
						break;
					case 0x0203 /* 0201H APP给DPS的命令包 */:
						valuePackets = new CMDPackets();
						break;
					case 0x0204 /* 0201H APP给DPS的命令包 */:
						valuePackets = new CMDPackets();
						break;
					case 0x0205 /* 0201H APP给DPS的命令包 */:
						valuePackets = new DASSerialPortPackets();
						break;
					case 0x0302 /* 0302H DPS给APP的错误包 */:
						valuePackets = new ERRPackets();
						break;
					case 0x0303 /* 0303H DPS转发给APP的回应包 */:
						valuePackets = new RESPackets();
						break;
					default:
						break;
					}

					if (valuePackets == null) {
						m = 0;
						break;
					}
					valuePackets.parsed(value);
					data.setValuePackets(valuePackets);
					
					data.finishFilling();
					int cs = buf2[buf2.length - 1];
					if(data.getChecksum() != cs){
						m = 0;
						break;
					}
					
					out.write(data);
					return false;
				}
				break;
			}
			p = c;
		}

		return true;
	}
}
