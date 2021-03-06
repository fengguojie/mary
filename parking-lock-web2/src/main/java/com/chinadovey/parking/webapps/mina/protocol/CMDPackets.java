package com.chinadovey.parking.webapps.mina.protocol;

import java.util.Arrays;

import com.chinadovey.parking.webapps.mina.exception.ProtocolParseErrorException;
import com.chinadovey.parking.webapps.utils.ByteUtil;

/**
 * TAG 0x0101H
 * 
 * @author Administrator
 * 
 */
public class CMDPackets implements ValuePackets {
	
	private byte[] cmd;
	private byte[] appId;
	private byte[] dasId;
	private byte[] rtuId;
	private byte[] cmdData;

	@Override
	public void parsed(byte[] buf) throws ProtocolParseErrorException {
		try {
			setCmd(Arrays.copyOfRange(buf, 0, 2));
			setAppId(Arrays.copyOfRange(buf, 2, 6));
			setDasId(Arrays.copyOfRange(buf, 6, 10));
			setRtuId(Arrays.copyOfRange(buf, 10, 14));
			setCmdData(Arrays.copyOfRange(buf, 14, buf.length));
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new ProtocolParseErrorException("解析CMD Packets时发生数组下标越界");
		}
	}

	public byte[] getCmd() {
		return cmd;
	}

	public void setCmd(byte[] cmd) {
		this.cmd = cmd;
	}

	public byte[] getDasId() {
		return dasId;
	}

	public void setDasId(byte[] dasId) {
		this.dasId = dasId;
	}

	public byte[] getCmdData() {
		return cmdData;
	}

	public void setCmdData(byte[] cmdData) {
		this.cmdData = cmdData;
	}

	public byte[] getRtuId() {
		return rtuId;
	}

	public void setRtuId(byte[] rtuId) {
		this.rtuId = rtuId;
	}

	public byte[] getAppId() {
		return appId;
	}

	public void setAppId(byte[] appId) {
		this.appId = appId;
	}

	public String toString() {
		if (dasId == null) dasId = new byte[4];
		if (appId == null) appId = new byte[4];
		StringBuffer sb = new StringBuffer();

		sb.append("\tCMD:		");
		sb.append(ByteUtil.asHex(getCmd()));
		sb.append('\n');
		sb.append("\tAPP ID:		");
		sb.append(ByteUtil.asHex(getAppId()));
		sb.append('\n');
		sb.append("\tDAS ID:		");
		sb.append(ByteUtil.asHex(getDasId()));
		sb.append('\n');
		sb.append("\tRTU ID:		");
		sb.append(ByteUtil.asHex(getRtuId()));
		sb.append('\n');
		sb.append("\tCMD DATA:	");
		sb.append(ByteUtil.asHex(getCmdData(), " "));
		return sb.toString();
	}

	@Override
	public byte[] getBytes() {
		if (dasId == null) dasId = new byte[4];
		if (appId == null) appId = new byte[4];
		
		int len = cmd.length + appId.length + dasId.length + rtuId.length + cmdData.length;
		byte[] value = new byte[len];
		System.arraycopy(cmd, 		0, value, 0, 														cmd.length);
		System.arraycopy(appId, 	0, value, cmd.length, 												appId.length);
		System.arraycopy(dasId, 	0, value, cmd.length + appId.length, 								dasId.length);
		System.arraycopy(rtuId, 	0, value, cmd.length + appId.length + dasId.length,					rtuId.length);
		System.arraycopy(cmdData, 	0, value, cmd.length + appId.length + dasId.length + rtuId.length, 	cmdData.length);
		return value;
	}
}
