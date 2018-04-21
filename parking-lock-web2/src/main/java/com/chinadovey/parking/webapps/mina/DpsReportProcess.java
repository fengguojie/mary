package com.chinadovey.parking.webapps.mina;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.proxy.utils.ByteUtilities;

import com.chinadovey.parking.webapps.biz.DasConnectBiz;
import com.chinadovey.parking.webapps.mina.exception.RTUProcessException;
import com.chinadovey.parking.webapps.mina.exception.TagUndefinedException;
import com.chinadovey.parking.webapps.mina.pojo.EquimentRealTime;
import com.chinadovey.parking.webapps.mina.pojo.Equipment;
import com.chinadovey.parking.webapps.mina.pojo.Equipment0710;
import com.chinadovey.parking.webapps.mina.pojo.RTURealTime;
import com.chinadovey.parking.webapps.mina.protocol.DASPackets;
import com.chinadovey.parking.webapps.mina.protocol.PacketsEntity;
import com.chinadovey.parking.webapps.mina.protocol.RTUPackets;
import com.chinadovey.parking.webapps.mina.temp.CarLockDataOpt;
import com.chinadovey.parking.webapps.pojo.DasConnect;
import com.chinadovey.parking.webapps.utils.ByteUtil;

public class DpsReportProcess {
	
	private static Log logger = LogFactory.getLog(DPSProcess.class);
	
	private String localAddress;
	private DasConnectBiz dasConnectBiz;
	private CloudNodeIoHandler ioHandler;
	private RTUProcessFactory rtuProcessFactory;
	
	public DpsReportProcess(String localAddress , DasConnectBiz dasConnectBiz, CloudNodeIoHandler ioHandler , RTUProcessFactory rtuProcessFactory) {
		this.localAddress = localAddress;
		this.dasConnectBiz = dasConnectBiz;
		this.ioHandler = ioHandler;
		this.rtuProcessFactory = rtuProcessFactory;
	}
	
	/**
	 * tag:0x0001H DAS上报数据包
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public void p0x0001(IoSession session, PacketsEntity packets) throws SQLException, IOException {
		DASPackets dasPackets = (DASPackets) packets.getValuePackets();
		try {
			/*DASRealTime dasRelaTime = new DASRealTime();
			dasRelaTime.setId(ByteUtil.asHex(dasPackets.getDasId()));
			dasRelaTime.setDasIdBytes(dasPackets.getDasId());
			dasRelaTime.setSessionId(session.getId());
			dasRelaTime.setSlaveAddress(localAddress);
			
			Query query = Query.query(
					Criteria.where("id").is(dasRelaTime.getId()));
			Update update = Update
					.update("slaveAddress", dasRelaTime.getSlaveAddress())
					.set("sessionId", dasRelaTime.getSessionId())
					.set("dasIdBytes", dasRelaTime.getDasIdBytes());

			mongoOps.upsert(query, update, DASRealTime.class);*/
			
			DasConnect dasConnect = new DasConnect();
			dasConnect.setDasId(ByteUtil.asHex(dasPackets.getDasId()));
			dasConnect.setSessionId(session.getId());
			dasConnect.setServerAddress(localAddress);
			
			boolean flag = dasConnectBiz.getByDasId(dasConnect.getDasId());
			if(flag){
				//更新
				dasConnectBiz.updateByDasId(dasConnect);
			}else{
				//插入
				dasConnectBiz.insert(dasConnect);
			}
			List<Object> objectsToSave = new ArrayList<Object>();
			for (RTUPackets rtuParkets : dasPackets.getRtus()) {
				RTURealTime rtuRealTime = new RTURealTime();
				rtuRealTime.setId(ByteUtil.asHex(rtuParkets.getRtuId()));
				rtuRealTime.setRtuIdBytes(rtuParkets.getRtuId());
				rtuRealTime.setDasIdBytes(dasPackets.getDasId());
				rtuRealTime.setSessionId(dasConnect.getSessionId());
				rtuRealTime.setSlaveAddress(dasConnect.getServerAddress());
				
				// RTU 数据处理
				RTUProcess cnp = rtuProcessFactory.getProcess(ByteUtilities
						.asHex(rtuParkets.getTag()));
				
				// 处理接收到的数据
				List<? extends Equipment> list = null;
				try {
					list = cnp.process(rtuParkets);
				} catch (RTUProcessException e) {
					e.printStackTrace();
				}
				
				EquimentRealTime equiRealTime = null;
				for(Equipment equi : list){
					equiRealTime = new EquimentRealTime();
					equiRealTime.setRtuId(equi.getRtuId());
					equiRealTime.setEquiId(equi.getEquiId());
					equiRealTime.setEquipment(equi);
					equiRealTime.setRtuRealTime(rtuRealTime);
					}
				
				if (list != null && list.size() > 0)
					objectsToSave.addAll((Collection<?>) list);
			}

			if (logger.isDebugEnabled()) {
				logger.debug("将数据存入数据中心mongoDB");
			}
			// 插入车位锁数据
			CarLockDataOpt parkOpt = null;
			try {
				for (Object obj : objectsToSave) {
					if (obj instanceof Equipment0710) {
						if (parkOpt == null) {
							parkOpt = new CarLockDataOpt();
							parkOpt.conn();
						}
						parkOpt.insert((Equipment0710) obj);
					}
				}
				if (parkOpt != null)
					parkOpt.commit();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				if (parkOpt != null)
					parkOpt.rollback();
			}
			
			
			
		} catch (TagUndefinedException e) {
			logger.error(e.getMessage() + "\n DasPackets:\n" + dasPackets, e);
			e.printStackTrace();
		}
	}

}
