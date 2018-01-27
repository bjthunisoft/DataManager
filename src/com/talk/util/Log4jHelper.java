package com.talk.util;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * log4j ������
 * 
 * @author gaoqiang01
 *
 */
public class Log4jHelper {
	static {
		Properties prop = new Properties();
		try {
//			String path = ConfigHelper.GetAppPath(Constant.CONFIG_FILE_FOLDER, false);
			prop.load(new FileInputStream("log4j.properties" + Constant.LOG4J_FILE_NAME));
			PropertyConfigurator.configure(prop);
		} catch (Exception e) {
			System.out.println("�����ļ�����ʧ��" + e);
		}
	}

	public static Logger getLogger(Class<?> c) {
		return Logger.getLogger(c);
	}
}
