package com.talk.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.TreeMap;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

/**
 * �����ļ� ������
 * 
 * @author gaoqiang01
 *
 */
public class ConfigHelper {
	private static final Logger log = Log4jHelper.getLogger(ConfigHelper.class);
	
	/**
	 * ���������ļ�
	 * 
	 * @param fileName
	 *            �ļ���
	 * @param sep
	 *            ����ֵ�ָ���
	 * @param setConfigFile
	 *            �Ƿ��ֶ�ָ�������ļ�
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static TreeMap<String, String> GetParamsMap(String fileName, String sep, boolean setConfigFile) {
		TreeMap<String, String> map = new TreeMap<String, String>();
		
		 InputStream in = ConfigHelper.class.getClassLoader()  
	                .getResourceAsStream("config.properties");  
		 Properties prop = new Properties();  
	        try {  
	            prop.load(in);  
	            Enumeration en = prop.keys();  
	            while(en.hasMoreElements()){  
	                String name = en.nextElement().toString();  
	                String path = prop.getProperty(name);  
	                map.put(name, path);  
	            }  
	            in.close();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
		return map;
	}

	
	
	
	
	
	
//	/**
//	 * ��ȡ�����ļ�·��
//	 * 
//	 * @param fileFolder
//	 *            �����ļ������ļ���
//	 * @param setConfigFile
//	 *            �Ƿ�ָ�������ļ�
//	 * @return
//	 */
//	public static String GetAppPath(String fileFolder, boolean setConfigFile) {
//		String path = "";
//		String classPath = System.getProperties().getProperty("user.dir");// Tools.class.getClassLoader().getResource("/").getPath();
//		String sep = System.getProperties().getProperty("file.separator");
//		if (classPath.indexOf("bin") >= 0) {
//			classPath = classPath.substring(0, classPath.lastIndexOf(sep));
//		}
//		if (setConfigFile) {
//
//			path = classPath + sep + sep;
//		} else {
//			path = classPath + sep + fileFolder + sep;
//		}
//		return path;
//	}

}
