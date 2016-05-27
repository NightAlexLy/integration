# integration
集成工具Demo

项目结构
	
integration
	| _ _ _ cache   //缓存框架
			| _ _ _redis
	| _ _ _ clock   //auto clock
	| _ _ _ log     //日志框架
			| _ _ _ log4j    //slf4j+log4j
			| _ _ _ logback  //logback+slf4j
			| _ _ _ slf4j   //标准
	| _ _ _ mq
			| _ _ _ activemq  //apache activemq demo
			| _ _ _ kafka     //apache kafka demo
			| _ _ _ metaq     //alibaba metaq demo
	| _ _ _ spring
			| _ _ _ spring-basic  //sping 基本功能  ioc/aop
			| _ _ _ spring-jdbc   //spring datasource   c3p0/dbcp/druid/proxool
			| _ _ _ spring-web    //spring-mvc  demo
	| _ _ _ tools   //basic tool   common code