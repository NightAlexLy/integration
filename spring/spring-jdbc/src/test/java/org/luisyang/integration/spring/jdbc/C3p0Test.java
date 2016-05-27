package org.luisyang.integration.spring.jdbc;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.luisyang.integration.spring.jdbc.dao.IStudentDao;
import org.luisyang.integration.spring.jdbc.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * 	
 *  C3P0数据源测试
 * 
 * @author ly
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans-c3p0.xml")
public class C3p0Test {
	
	@Autowired
	private IStudentDao studentDao;

	@Test
	public void testDao() {

		List<Student> students = studentDao.findAll();
		for(Student student : students){
			System.out.println(student.toString());
		}
		
		Student student = new Student();
		student.setName("zhangsan");
		student.setAge(100);
		
		studentDao.insert(student);
		
		studentDao.delete(12313);
	}
}

/**
 * 五月 27, 2016 2:43:27 下午 com.mchange.v2.c3p0.impl.AbstractPoolBackedDataSource 
信息: Initializing c3p0 pool... com.mchange.v2.c3p0.ComboPooledDataSource [ acquireIncrement -> 5, acquireRetryAttempts -> 30, acquireRetryDelay -> 1000, autoCommitOnClose -> false, automaticTestTable -> null, breakAfterAcquireFailure -> false, checkoutTimeout -> 0, connectionCustomizerClassName -> null, connectionTesterClassName -> com.mchange.v2.c3p0.impl.DefaultConnectionTester, contextClassLoaderSource -> caller, dataSourceName -> 1hgeiv59g1uutdbs1fzbv3d|33356fc, debugUnreturnedConnectionStackTraces -> false, description -> null, driverClass -> com.mysql.jdbc.Driver, extensions -> {}, factoryClassLocation -> null, forceIgnoreUnresolvedTransactions -> false, forceUseNamedDriverClass -> false, identityToken -> 1hgeiv59g1uutdbs1fzbv3d|33356fc, idleConnectionTestPeriod -> 0, initialPoolSize -> 20, jdbcUrl -> jdbc:mysql://192.168.91.135:3306/test?useUnicode=yes&characterEncoding=utf8, maxAdministrativeTaskTime -> 0, maxConnectionAge -> 0, maxIdleTime -> 600, maxIdleTimeExcessConnections -> 0, maxPoolSize -> 100, maxStatements -> 5, maxStatementsPerConnection -> 0, minPoolSize -> 10, numHelperThreads -> 3, preferredTestQuery -> null, privilegeSpawnedThreads -> false, properties -> {user=******, password=******}, propertyCycle -> 0, statementCacheNumDeferredCloseThreads -> 0, testConnectionOnCheckin -> false, testConnectionOnCheckout -> false, unreturnedConnectionTimeout -> 0, userOverrides -> {}, usesTraditionalReflectiveProxies -> false ]
五月 27, 2016 2:43:47 下午 com.mchange.v2.async.ThreadPoolAsynchronousRunner 
警告: com.mchange.v2.async.ThreadPoolAsynchronousRunner$DeadlockDetector@3a7e9eb9 -- APPARENT DEADLOCK!!! Creating emergency threads for unassigned pending tasks!
五月 27, 2016 2:43:47 下午 com.mchange.v2.async.ThreadPoolAsynchronousRunner 
警告: com.mchange.v2.async.ThreadPoolAsynchronousRunner$DeadlockDetector@3a7e9eb9 -- APPARENT DEADLOCK!!! Complete Status: 
	Managed Threads: 3
	Active Threads: 3
	Active Tasks: 
		com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask@58383316
			on thread: C3P0PooledConnectionPoolManager[identityToken->1hgeiv59g1uutdbs1fzbv3d|33356fc]-HelperThread-#2
		com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask@51a21699
			on thread: C3P0PooledConnectionPoolManager[identityToken->1hgeiv59g1uutdbs1fzbv3d|33356fc]-HelperThread-#0
		com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask@6879c0ad
			on thread: C3P0PooledConnectionPoolManager[identityToken->1hgeiv59g1uutdbs1fzbv3d|33356fc]-HelperThread-#1
	Pending Tasks: 
		com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask@1158d371
		
		
		原因：数据库没有正常提供服务。。。
		
*/
