package com.chinadovey.parking.webapps.controller.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chinadovey.parking.webapps.biz.CarLockBiz;
import com.chinadovey.parking.webapps.mina.client.ParkCloudClient;
import com.chinadovey.parking.webapps.mina.protocol.CMDPackets;
import com.chinadovey.parking.webapps.mina.protocol.ProtocolConst;
import com.chinadovey.parking.webapps.mina.protocol.RESPackets;
import com.chinadovey.parking.webapps.pojo.CarLock;
import com.chinadovey.parking.webapps.utils.ByteUtil;
import com.chinadovey.parking.webapps.utils.ConfUtils;

@Component
public class ControlBaseImpl implements ControlBase{
	;
	@Autowired
	private CarLockBiz carLockBiz;
	
	
	@Override
	public Integer operate(String slaveId, int action) {
		try {
			CarLock carlock = carLockBiz.getBySlaveid(slaveId);
			CMDPackets cmd = new CMDPackets();
			cmd.setCmd(ProtocolConst.CMD_OPEN_OR_CLOSE);//开关通断命令
			cmd.setDasId(ByteUtil.writeInt4(Integer.parseInt(carlock.getGatewayNo(),16), 0));
			String rtuIdStr = slaveId.substring(0, 8);
			int rtuId = Integer.parseInt(rtuIdStr, 16);
			cmd.setRtuId(ByteUtil.writeInt4(rtuId, 0));//05060708 RTU ID
			byte[] b = new byte[13];
			String equiStr = slaveId.substring(8, 12);
			int equi = Integer.parseInt(equiStr, 16);
			ByteUtil.writeInt2(equi, b, 0);//0A0B 设备ID
			ByteUtil.writeByte(action, b, 2);//1表示开2 表示关
			ByteUtil.writeInt2(0x00230012, b, 3);//修改上报周期：1-65535秒，0表示不修改上报时间
			ByteUtil.writeInt4(0x00000000, b, 5);//保留字段
			ByteUtil.writeInt4(0x00000000, b, 9);//保留字段
			cmd.setCmdData(b);
			
			String ip = ConfUtils.getControlAddress();
			ParkCloudClient cc = new ParkCloudClient(ip);
			RESPackets res = cc.execute(cmd, 32 * 1000l);
			int stat = res.getValue()[2] & 0x0f;
			switch (stat) {
			case 0x00:
				System.out.println("车位锁打开");
				carlock.setSwitchStatus(1);
				carLockBiz.update(carlock);
				break;
			case 0x01:
				System.out.println("车位锁关闭");
				carlock.setSwitchStatus(2);
				carLockBiz.update(carlock);
				break;
			case 0x02:
				System.out.println("正在打开");
				break;
			case 0x03:
				System.out.println("正在关闭");
				break;
			}
			return stat;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	@Override
	public Integer autoOrHand(String slaveId, int action) {
		try {
			CarLock carlock = carLockBiz.getBySlaveid(slaveId);
			CMDPackets cmd = new CMDPackets();
			cmd.setCmd(ProtocolConst.CMD_OPEN_OR_CLOSE);//开关通断命令
			cmd.setDasId(ByteUtil.writeInt4(Integer.parseInt(carlock.getGatewayNo(),16), 0));
			String rtuIdStr = slaveId.substring(0, 8);
			int rtuId = Integer.parseInt(rtuIdStr, 16);
			cmd.setRtuId(ByteUtil.writeInt4(rtuId, 0));//05060708 RTU ID
			byte[] b = new byte[13];
			String equiStr = slaveId.substring(8, 12);
			int equi = Integer.parseInt(equiStr, 16);
			ByteUtil.writeInt2(equi, b, 0);//0A0B 设备ID
			ByteUtil.writeByte(action, b, 2);//1表示开2 表示关
			ByteUtil.writeInt2(0x00230012, b, 3);//修改上报周期：1-65535秒，0表示不修改上报时间
			ByteUtil.writeInt4(0x00000000, b, 5);//保留字段
			ByteUtil.writeInt4(0x00000000, b, 9);//保留字段
			cmd.setCmdData(b);
			
			
			String ip = ConfUtils.getControlAddress();
			ParkCloudClient cc = new ParkCloudClient(ip);
			RESPackets res = cc.execute(cmd, 32 * 1000l);
			int stat = res.getValue()[2] & 0x0f;
			if (stat == 0x00) {
				carlock.setIsauto(action);
				carLockBiz.update(carlock);
			}
			return stat;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	@Override
	public Integer carlockConfig(String dasId, String slaveId,String serial) {
		try {
			CarLock carlock = carLockBiz.getBySlaveid(slaveId);
			CMDPackets cmd = new CMDPackets();
			cmd.setCmd(ProtocolConst.CMD_CONF_DAS_ADD);
			String dasIdStr = dasId;//8位网关id
			cmd.setDasId(ByteUtil.writeInt4(Integer.parseInt(dasIdStr,16), 0));
			
			String equiStr = slaveId;
			String rtuIdStr = equiStr.substring(0, 8);
			cmd.setRtuId(ByteUtil.writeInt4(Integer.parseInt(rtuIdStr,16), 0));
			
			byte[] b = new byte[11];
			String slaveIdStr = equiStr.substring(8, 12);
			ByteUtil.writeInt2(Integer.parseInt(slaveIdStr,16), b, 0);// 设备id

			int delay = 6000;
			ByteUtil.writeInt4(delay, b, 2);//延时（ms）
			if (serial.equals("00000001")) {
				b[6] = 0x00;
			}else{
				b[6] = 0x01;
			}
			b[7] = 0x07;
			String wireIdStr = equiStr.substring(2,6);
			ByteUtil.writeInt2(Integer.parseInt(wireIdStr,16), b, 8);
			String channelStr = equiStr.substring(6,8);
			ByteUtil.writeByte(Integer.parseInt(channelStr,16), b, 10);
			cmd.setCmdData(b);
			
			String ip = ConfUtils.getControlAddress();
			ParkCloudClient cc = new ParkCloudClient(ip);
			byte[] tag = new byte[]{0x02,0x03};
			RESPackets res = cc.execute(tag, cmd, 1000l * 120);

			int status = res.getValue()[2]&0x0f;
			System.err.println(res.getValue());
			switch(status){
			case 0x00:
				if (serial.equals("00000001")) {
					carlock.setConfigStatus(0);
				}else{
					carlock.setConfigStatus(1);
				}
				carLockBiz.update(carlock);
				System.out.println("配置成功！");
				break;
			case 0x01:
				System.out.println("配置失败！");
				break;
			}
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	@Override
	public Integer carlockDelConfig(String dasId, String slaveId) {
		try {
			CarLock carlock = carLockBiz.getBySlaveid(slaveId);
			CMDPackets cmd = new CMDPackets();
			cmd.setCmd(ProtocolConst.CMD_CONF_DAS_DEL);
			String dasIdStr = dasId;//8位网关id
			cmd.setDasId(ByteUtil.writeInt4(Integer.parseInt(dasIdStr,16), 0));
			
			String equiStr = slaveId;
			String rtuIdStr = equiStr.substring(0, 8);
			cmd.setRtuId(ByteUtil.writeInt4(Integer.parseInt(rtuIdStr,16), 0));
			
			byte[] b = new byte[11];
			String slaveIdStr = equiStr.substring(8, 12);
			ByteUtil.writeInt2(Integer.parseInt(slaveIdStr,16), b, 0);// 设备id

			int delay = 6000;
			ByteUtil.writeInt4(delay, b, 2);//延时（ms）
			Integer flag = carlock.getConfigStatus();
			if (flag == 0) {
				b[6] = 0x00;//串口1
			}else {
				b[6] = 0x01;
			}
			b[7] = 0x07;
			String wireIdStr = equiStr.substring(2,6);
			ByteUtil.writeInt2(Integer.parseInt(wireIdStr,16), b, 8);
			String channelStr = equiStr.substring(6,8);
			ByteUtil.writeByte(Integer.parseInt(channelStr,16), b, 10);
			cmd.setCmdData(b);
			
			String ip = ConfUtils.getControlAddress();
			ParkCloudClient cc = new ParkCloudClient(ip);
			byte[] tag = new byte[]{0x02,0x04};
			RESPackets res = cc.execute(tag, cmd, 1000l * 30);

			int status = res.getValue()[2]&0x0f;
			switch(status){
			case 0x00:
				carlock.setConfigStatus(2);
				carLockBiz.update(carlock);
				System.out.println("删除配置成功！");
				break;
			case 0x01:
				System.out.println("删除配置失败！");
				break;
			}
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	

	
	
	
	
	

	
}
