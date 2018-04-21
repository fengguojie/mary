package com.chinadovey.parking.webapps.utils;

import java.io.UnsupportedEncodingException;


public class ByteUtil {
	
	

	public static byte[] writeLong8(long v, byte[] b, int offset) {
		b[offset + 7] = (byte) v;
		b[offset + 6] = (byte) (v >> 8);
		b[offset + 5] = (byte) (v >> 16);
		b[offset + 4] = (byte) (v >> 24);
		b[offset + 3] = (byte) (v >> 32);
		b[offset + 2] = (byte) (v >> 40);
		b[offset + 1] = (byte) (v >> 48);
		b[offset] = (byte) (v >> 56);
		return b;
	}

	public static byte[] writeLong6(long v, byte[] b, int offset) {
		b[offset + 5] = (byte) v;
		b[offset + 4] = (byte) (v >> 8);
		b[offset + 3] = (byte) (v >> 16);
		b[offset + 2] = (byte) (v >> 24);
		b[offset + 1] = (byte) (v >> 32);
		b[offset] = (byte) (v >> 40);
		return b;
	}
	
	public static byte[] writeLong4(long v,byte[] b ,int offset){
		b[offset + 3] = (byte) v;
		b[offset + 2] = (byte) (v >> 8);
		b[offset + 1] = (byte) (v >> 16);
		b[offset] = (byte) (v >> 24);
		return b;

	}

	public static byte[] writeInt4(int v, byte[] b, int offset) {
		b[offset + 3] = (byte) v;
		b[offset + 2] = (byte) (v >> 8);
		b[offset + 1] = (byte) (v >> 16);
		b[offset] = (byte) (v >> 24);
		return b;
	}

	public static byte[] writeInt4(int v, int offset) {
		return writeInt4(v, new byte[4], offset);
	}

	public static byte[] writeInt4(String hex, int offset) {
		return writeInt4(Integer.parseInt(hex, 16), offset);
	}
	
	public static byte[] writeInt3(int v, byte[] b, int offset) {
		b[offset + 2] = (byte) v;
		b[offset + 1] = (byte) (v >> 8);
		b[offset] = (byte) (v >> 16);
		return b;
	}

	public static byte[] writeInt2(int v, byte[] b, int offset) {
		b[offset + 1] = (byte) v;
		b[offset] = (byte) (v >> 8);
		return b;
	}

	public static byte[] writeInt2(int v, int offset) {
		return writeInt2(v, new byte[2], offset);
	}

	public static byte[] writeInt2(String hex, int offset) {
		return writeInt2(Integer.parseInt(hex, 16), offset);
	}

	public static void writeByte(int v, byte[] b, int offset) {
		b[offset] = (byte) v;
	}

	public static int makeIntFromByte(byte[] b) {
		return makeIntFromByte(b, Math.min(4, b.length), 0);
	}

	public static int makeIntFromByte(byte[] b, int len) {
		return makeIntFromByte(b, len, 0);
	}

	public static int makeIntFromByte(byte[] b, int len, int offset) {
		int temp = 0;
		int res = 0;
		for (int i = 0; i < len; i++) {
			res <<= 8;
			temp = b[i + offset] & 0xff;
			res |= temp;
		}
		return res;
	}

	public static final long makeLongFromByte(byte[] b) {
		return makeLongFromByte(b, Math.min(8, b.length), 0);
	}

	public static final long makeLongFromByte(byte[] b, int len) {
		return makeLongFromByte(b, len, 0);
	}

	public static final long makeLongFromByte(byte[] b, int len, int offset) {
		long temp = 0;
		long res = 0;
		for (int i = 0; i < len; i++) {
			res <<= 8;
			temp = b[i + offset] & 0xff;
			res |= temp;
		}
		return res;
	}

	public static String asHex(byte bytes) {
		return asHex(new byte[] { bytes }, null);
	}

	public static String asHex(byte[] bytes) {
		return asHex(bytes, null);
	}

	public static String asHex(byte[] bytes, String separator) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String code = Integer.toHexString(bytes[i] & 0xFF);
			if ((bytes[i] & 0xFF) < 16) {
				sb.append('0');
			}

			sb.append(code);

			if (separator != null && i < bytes.length - 1) {
				sb.append(separator);
			}
		}

		return sb.toString();
	}
	
	
    /** 
     * @Title:bytes2HexString 
     * @Description:字节数组转16进制字符串 
     * @param b 
     *            字节数组 
     * @return 16进制字符串 
     * @throws 
     */  
    public static String bytes2HexString(byte[] b) {  
        StringBuffer result = new StringBuffer();  
        String hex;  
        for (int i = 0; i < b.length; i++) {  
            hex = Integer.toHexString(b[i] & 0xFF);  
            if (hex.length() == 1) {  
                hex = '0' + hex;  
            }  
            result.append(hex.toUpperCase());  
        }  
        return result.toString();  
    }  
  
    /** 
     * @Title:hexString2Bytes 
     * @Description:16进制字符串转字节数组 
     * @param src 
     *            16进制字符串 
     * @return 字节数组 
     * @throws 
     */  
    public static byte[] hexString2Bytes(String src) {  
        int l = src.length() / 2;  
        byte[] ret = new byte[l];  
        for (int i = 0; i < l; i++) {  
            ret[i] = (byte) Integer  
                    .valueOf(src.substring(i * 2, i * 2 + 2), 16).byteValue();  
        }  
        return ret;  
    }  
  
    /** 
     * @Title:string2HexString 
     * @Description:字符串转16进制字符串 
     * @param strPart 
     *            字符串 
     * @return 16进制字符串 
     * @throws 
     */  
    public static String string2HexString(String strPart) {  
        StringBuffer hexString = new StringBuffer();  
        for (int i = 0; i < strPart.length(); i++) {  
            int ch = (int) strPart.charAt(i);  
            String strHex = Integer.toHexString(ch);  
            hexString.append(strHex);  
        }  
        return hexString.toString();  
    }  
  
    /** 
     * @Title:hexString2String 
     * @Description:16进制字符串转字符串 
     * @param src 
     *            16进制字符串 
     * @return 字节数组 
     * @throws 
     */  
    public static String hexString2String(String src) {  
        String temp = "";  
        for (int i = 0; i < src.length() / 2; i++) {  
            temp = temp  
                    + (char) Integer.valueOf(src.substring(i * 2, i * 2 + 2),  
                            16).byteValue();  
        }  
        return temp;  
    }  
      
    /** 
     * @Title:char2Byte 
     * @Description:字符转成字节数据char-->integer-->byte 
     * @param src 
     * @return 
     * @throws 
     */  
    public static Byte char2Byte(Character src) {  
        return Integer.valueOf((int)src).byteValue();  
    }  
      
        /** 
     * @Title:intToHexString 
     * @Description:10进制数字转成16进制 
     * @param a 转化数据 
     * @param len 占用字节数 
     * @return 
     * @throws 
     */  
    private static String intToHexString(int a,int len){  
        len<<=1;  
        String hexString = Integer.toHexString(a);  
        int b = len -hexString.length();  
        if(b>0){  
            for(int i=0;i<b;i++)  {  
                hexString = "0" + hexString;  
            }  
        }  
        return hexString;  
    } 

    
    public static byte[] long2bytes(long num) {  
        byte[] b = new byte[8];  
        for (int i=0;i<8;i++) {  
            b[i] = (byte)(num>>>(56-(i*8))); 
            System.err.println(b[i]);
        }  
        return b;  
    }  
    
    /** 
     * 将一个单字节的byte转换成32位的int 
     *  
     * @param b 
     *            byte 
     * @return convert result 
     */  
    public static int unsignedByteToInt(byte b) {  
        return (int) b & 0xFF;  
    }  
  
    /** 
     * 将一个单字节的Byte转换成十六进制的数 
     *  
     * @param b 
     *            byte 
     * @return convert result 
     */  
    public static String byteToHex(byte b) {  
        int i = b & 0xFF;  
        return Integer.toHexString(i);  
    }  
  
    /** 
     * 将一个4byte的数组转换成32位的int 
     *  
     * @param buf 
     *            bytes buffer 
     * @param byte[]中开始转换的位置 
     * @return convert result 
     */  
    public static long unsigned4BytesToInt(byte[] buf, int pos) {  
        int firstByte = 0;  
        int secondByte = 0;  
        int thirdByte = 0;  
        int fourthByte = 0;  
        int index = pos;  
        firstByte = (0x000000FF & ((int) buf[index]));  
        secondByte = (0x000000FF & ((int) buf[index + 1]));  
        thirdByte = (0x000000FF & ((int) buf[index + 2]));  
        fourthByte = (0x000000FF & ((int) buf[index + 3]));  
        index = index + 4;  
        return ((long) (firstByte << 24 | secondByte << 16 | thirdByte << 8 | fourthByte)) & 0xFFFFFFFFL;  
    }  
  
    /** 
     * 将16位的short转换成byte数组 
     *  
     * @param s 
     *            short 
     * @return byte[] 长度为2 
     * */  
    public static byte[] shortToByteArray(short s) {  
        byte[] targets = new byte[2];  
        for (int i = 0; i < 2; i++) {  
            int offset = (targets.length - 1 - i) * 8;  
            targets[i] = (byte) ((s >>> offset) & 0xff);  
        }  
        return targets;  
    }  
  
    /** 
     * 将32位整数转换成长度为4的byte数组 
     *  
     * @param s 
     *            int 
     * @return byte[] 
     * */  
    public static byte[] intToByteArray(int s) {  
        byte[] targets = new byte[2];  
        for (int i = 0; i < 4; i++) {  
            int offset = (targets.length - 1 - i) * 8;  
            targets[i] = (byte) ((s >>> offset) & 0xff);  
        }  
        return targets;  
    }  
  
    /** 
     * long to byte[] 
     *  
     * @param s 
     *            long 
     * @return byte[] 
     * */  
    public static byte[] longToByteArray(long s) {  
        byte[] targets = new byte[2];  
        for (int i = 0; i < 8; i++) {  
            int offset = (targets.length - 1 - i) * 8;  
            targets[i] = (byte) ((s >>> offset) & 0xff);  
        }  
        return targets;  
    }  
  
    /**32位int转byte[]*/  
    public static byte[] int2byte(int res) {  
        byte[] targets = new byte[4];  
        targets[0] = (byte) (res & 0xff);// 最低位  
        targets[1] = (byte) ((res >> 8) & 0xff);// 次低位  
        targets[2] = (byte) ((res >> 16) & 0xff);// 次高位  
        targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。  
        return targets;  
    }  
  

	
	
}
