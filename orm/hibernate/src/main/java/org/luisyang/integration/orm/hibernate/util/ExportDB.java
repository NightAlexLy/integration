package org.luisyang.integration.orm.hibernate.util;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * 使用hibernate工具类将对象模型生成关系模型
 * 
 * 
 * 此方法会打印出创建的SQL语句， 但是会将之前数据库中的表清除。 导致之前保存的数据都会被删除。
 * 
 * @author apple
 */
public class ExportDB {

	public static void main(String[] args) {

		/*
		 * org.hibernate.cfg.Configuration类的作用：
		 * 读取hibernate配置文件(hibernate.cfg.xml或hiberante.properties)的. new
		 * Configuration()默认是读取hibernate.properties
		 * 所以使用newConfiguration().configure();来读取hibernate.cfg.xml配置文件
		 */
		Configuration cfg = new AnnotationConfiguration().configure();
		/*
		 * org.hibernate.tool.hbm2ddl.SchemaExport工具类： 需要传入Configuration参数
		 * 此工具类可以将类导出生成数据库表
		 */
		SchemaExport export = new SchemaExport(cfg);
		/**
		 * 开始导出 第一个参数：script是否打印DDL信息 第二个参数：export是否导出到数据库中生成表
		 */
		export.create(true, true);

	}

}
