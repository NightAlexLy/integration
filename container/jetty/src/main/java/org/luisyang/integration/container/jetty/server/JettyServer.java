package org.luisyang.integration.container.jetty.server;

import java.io.File;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JettyServer {

	private static final Logger logger = LoggerFactory.getLogger(JettyServer.class);

	public static int DEFALUT_PORT = 8888;

	public static void main(String[] args) throws Exception {
		// String webapp = Constants.WEB_APP;//声明项目所在的目录
		String webapp = getWebPath() ;
		logger.info("web context : " + webapp);
		Server server = new Server(DEFALUT_PORT); // 声明端口
		WebAppContext context = new WebAppContext(); // 声明上下文对象
		context.setDescriptor(webapp + "/WEB-INF/web.xml"); // 指定web.xml文件，可选
		context.setResourceBase(webapp); // 设置项目路径
		context.setContextPath("/"); // 设置上下文根，可以任意的值
		context.setWelcomeFiles(new String[] { "index.jsp" }); // 设置欢迎页面
		server.setHandler(context); // 设置句柄
		server.start(); // 启动
		server.join();
	}

	private static String getWebPath() {
		File file = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath());
		String path = file.getParentFile().getParentFile().getAbsolutePath();
		return path;
	}

}
/**

	Jetty  Pom
	
		<dependency>
			<groupId>org.eclipse.jetty</groupId>
			<artifactId>jetty-server</artifactId>
			<version>7.2.0.v20101020</version>
		</dependency>
		<dependency>
			<groupId>org.eclipse.jetty</groupId>
			<artifactId>jetty-webapp</artifactId>
			<version>7.2.0.v20101020</version>
		</dependency>
		<dependency>
			<groupId>org.mortbay.jetty</groupId>
			<artifactId>jsp-2.1-jetty</artifactId>
			<version>6.1.15</version>
		</dependency>



*/