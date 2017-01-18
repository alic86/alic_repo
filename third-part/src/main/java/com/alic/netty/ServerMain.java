package com.alic.netty;

import java.util.concurrent.ConcurrentMap;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.util.internal.ConcurrentHashMap;

public class ServerMain {
	//线程安全map,处理服务器hold住客户端连接的channel
	public static ConcurrentMap<String, Channel> channelMap = new ConcurrentHashMap<String, Channel>();

	public static void main(String[] args) {
		
		try {
			//启动服务器
			ServerThread r = new ServerThread();
			Thread t = new Thread(r);
			t.setName("server thread");
			t.start();
			
			System.out.println("server start at localhost:8080");
			//推送消息
			ServerPullUitl.pullMsg();//这里是个while ture,模拟server不停给client反馈
			
		} catch (Exception e) {
			System.out.println("know exception on server: " + e.getMessage());
		}
	}
}
