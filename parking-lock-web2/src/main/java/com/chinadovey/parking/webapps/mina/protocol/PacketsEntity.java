package com.chinadovey.parking.webapps.mina.protocol;

import com.chinadovey.parking.webapps.utils.ByteUtil;


public class PacketsEntity {

	public static final byte START_FLAG_C = 0X7B;
	public static final byte[] START_FLAG = new byte[] { START_FLAG_C,
			START_FLAG_C };

	private byte[] startFlag = START_FLAG;
	private byte[] tag;
	private byte[] length;
	private byte[] index;
	private ValuePackets valuePackets;
	private byte checksum;

	public byte[] getStartFlag() {
		return startFlag;
	}

	public byte[] getTag() {
		return tag;
	}

	public void setTag(byte[] tag) {
		this.tag = tag;
	}

	public byte[] getLength() {
		return length;
	}

	public byte[] getIndex() {
		return index;
	}

	public void setIndex(byte[] index) {
		this.index = index;
	}

	public byte getChecksum() {
		return checksum;
	}

	private void setChecksum(byte checksum) {
		this.checksum = checksum;
	}

	public ValuePackets getValuePackets() {
		return valuePackets;
	}

	public void setValuePackets(ValuePackets valuePackets) {
		this.valuePackets = valuePackets;
	}

	public void finishFilling() {
		byte[] value = valuePackets.getBytes();
		// 长度
		length = new byte[2];
		ByteUtil.writeInt2(index.length + value.length + 1, length, 0);

		// CS
		int check = 0;
		for (byte b : index)
			check += b;
		for (byte b : value)
			check += b;
		setChecksum((byte) check);
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append("START FLAG:	");
		sb.append(ByteUtil.asHex(getStartFlag()));
		sb.append('\n');
		sb.append("TAG:		");
		sb.append(ByteUtil.asHex(getTag()));
		sb.append('\n');
		sb.append("LENGTH:		");
		sb.append(ByteUtil.asHex(getLength()));
		sb.append('\n');
		sb.append("INDEX:		");
		sb.append(ByteUtil.asHex(getIndex()));
		sb.append('\n');
		sb.append("VALUE:		");
		sb.append(ByteUtil.asHex(valuePackets.getBytes()," "));
		sb.append('\n');
		sb.append("CHECKSUM:	");
		sb.append(ByteUtil.asHex(getChecksum()));

		return sb.toString();
	}
}
