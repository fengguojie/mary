package com.chinadovey.parking.webapps.mina;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.transport.socket.SocketSessionConfig;

import com.chinadovey.parking.webapps.biz.DasConnectBiz;
import com.chinadovey.parking.webapps.mina.heart.KeepAliveMessageFactoryImpl;
import com.chinadovey.parking.webapps.mina.heart.KeepAliveRequestTimeoutHandlerImpl;
import com.chinadovey.parking.webapps.utils.ConfUtils;


public class MinaServer{

	private static final Logger logger = Logger.getLogger(MinaServer.class);

	private IoAcceptor ioAcceptor;
	private DasConnectBiz dasConnectBiz;
	
	
	public void start() {
		// 2. 启动Socket
		try {
			ioAcceptor.getSessionConfig().setReadBufferSize(1024*1024);
			((SocketSessionConfig) ioAcceptor.getSessionConfig())
					.setReceiveBufferSize(1024*1024);
			
			ioAcceptor.getSessionConfig().setIdleTime(IdleStatus.READER_IDLE, 60);
			if (ConfUtils.getValueByKey("heartEnable").equals("1")) {
				KeepAliveMessageFactory heartBeatFactory = new KeepAliveMessageFactoryImpl(dasConnectBiz);
				KeepAliveRequestTimeoutHandler heartBeatHandler = new  KeepAliveRequestTimeoutHandlerImpl();
				KeepAliveFilter heartBeat = new KeepAliveFilter(heartBeatFactory,
						IdleStatus.BOTH_IDLE,heartBeatHandler);
				heartBeat.setForwardEvent(true);//设置是否到下一个filter
				heartBeat.setRequestInterval(Integer.parseInt(ConfUtils.getValueByKey("requestInterval")));//设置心跳频率
				heartBeat.setRequestTimeout(Integer.parseInt(ConfUtils.getValueByKey("requestTimeout")));
				ioAcceptor.getFilterChain().addLast("heartbeat", heartBeat);
			}
			// 端口重用
			ioAcceptor.bind();
			logger.info("mina服务启动了！");
		} catch (IOException e) {
			logger.error("mina 服务启动失败！",e);
			destroy(e);
			return;
		}

	}
	public void destroy(Exception e) {
		if (e != null)
			logger.error(e.getMessage(), e);
		destroy();
	}

	public void destroy() {
		if (ioAcceptor != null) {
			ioAcceptor.unbind();
			ioAcceptor.dispose();
		}
	}
	public void setIoAcceptor(IoAcceptor ioAcceptor) {
		this.ioAcceptor = ioAcceptor;
	}
	public void setDasConnectBiz(DasConnectBiz dasConnectBiz) {
		this.dasConnectBiz = dasConnectBiz;
	}
	

}
