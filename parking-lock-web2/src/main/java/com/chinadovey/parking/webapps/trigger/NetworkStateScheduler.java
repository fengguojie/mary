package com.chinadovey.parking.webapps.trigger;

//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.Date;
//import java.util.Timer;
//
//import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
//
//import com.chinadovey.parking.webapps.mina.MinaClient;
//import com.chinadovey.parking.webapps.mina.client.CloudClient;
//import com.chinadovey.parking.webapps.mina.protocol.CloudPacketsEntity;
//import com.chinadovey.parking.webapps.mina.protocol.CloudParkIdPackets;
//import com.chinadovey.parking.webapps.mina.protocol.CloudResponsePackets;
//import com.chinadovey.parking.webapps.utils.ConfUtils;
//import com.chinadovey.parking.webapps.utils.DateUtil;
@Service
public class NetworkStateScheduler {

//	private Logger logger = Logger.getLogger(NetworkStateScheduler.class);
//
//	public void check() {
//		Runtime runtime = Runtime.getRuntime();
//		try {
//			Process process = runtime.exec("ping " + "www.baidu.com");
//			InputStream is = process.getInputStream();
//			InputStreamReader isr = new InputStreamReader(is);
//			BufferedReader br = new BufferedReader(isr);
//			String line = null;
//			StringBuffer sb = new StringBuffer();
//			while ((line = br.readLine()) != null) {
//				sb.append(line);
//			}
//			is.close();
//			isr.close();
//			br.close();
//
//			if (null != sb && !sb.toString().equals("")) {
//				if (sb.toString().indexOf("TTL") > 0) {
//					logger.info("网络正常，时间 "
//							+ DateUtil.dateConvertString(new Date(), 3));
//					Thread.sleep(20000);
//					Integer parkId = ConfUtils.getParkId();
//					CloudParkIdPackets packets = new CloudParkIdPackets();
//					packets.setParkId(parkId);
//					CloudPacketsEntity  cpe = new CloudPacketsEntity(parkId,ConfUtils.getSecretKey(),27,1,packets);
//					CloudClient client = new CloudClient(ConfUtils.getCloudHost(),ConfUtils.getCloudPort());
//                    CloudResponsePackets res =  (CloudResponsePackets) client.execute(cpe);					
//					if(!res.getRes().equals("0")){
//						MinaClient  c = new MinaClient();
//						c.start();
//						Timer timer = new Timer();
//						timer.schedule(c, 5000, 15000);
//					}
//					logger.info(res.toString());
//				} else {
//					logger.error("网络断开，时间 "
//							+ DateUtil.dateConvertString(new Date(), 3));
//				}
//			}
//		} catch (Exception e) {
//			logger.error("检测网络状态是出现错误！", e);
//		}
//	}

}
