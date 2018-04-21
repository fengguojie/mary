package com.chinadovey.parking.webapps.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import com.chinadovey.parking.webapps.mina.protocol.PacketsEntity;

public class DPSProtocolEncoder extends ProtocolEncoderAdapter {

	@Override
	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {

		IoBuffer buffer = IoBuffer.allocate(100).setAutoExpand(true);
		if (message instanceof PacketsEntity) {
			PacketsEntity pd = (PacketsEntity) message;
			buffer.put(pd.getStartFlag());
			buffer.put(pd.getTag());
			buffer.put(pd.getLength());
			buffer.put(pd.getIndex());
			buffer.put(pd.getValuePackets().getBytes());
			buffer.put(pd.getChecksum());
		} else {
			buffer.put(message.toString().getBytes());
		}

		buffer.flip();
		out.write(buffer);
	}
}
