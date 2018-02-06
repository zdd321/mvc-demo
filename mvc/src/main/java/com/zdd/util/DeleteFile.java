package com.zdd.util;

import java.io.File;
import java.text.ParseException;
import java.util.Date;

/**
 * �ļ�ɾ������
 * @author Administrator
 *
 */
public class DeleteFile {

	/**
	 * ɾ���ļ�
	 * @param path
	 * @return
	 * @throws ParseException 
	 */
	 public static boolean delAllFile(String path) throws ParseException {
	       boolean flag = false;
	       File file = new File(path);
	       if (!file.exists()) {
	         return flag;
	       }
	       if (!file.isDirectory()) {
	         return flag;
	       }
	       String[] tempList = file.list();
	       File temp = null;
//	       Date date1=new Date();
//	       Long time=(long)15*24*60*60*1000;
//	       time=date1.getTime()-time;
	       for (int i = 0; i < tempList.length; i++) {
	          if (path.endsWith(File.separator)) {
	             temp = new File(path + tempList[i]);
	          } else {
	              temp = new File(path + File.separator + tempList[i]);
	          }
	          
	          if (temp.isFile()) {
	             temp.delete();
	          }
	          if (temp.isDirectory()) {
	             delAllFile(path + "/" + tempList[i]);//��ɾ���ļ���������ļ�
	             delFolder(path + "/" + tempList[i]);//��ɾ�����ļ���
	             flag = true;
	          }
	       }
	       return flag;
	     }
	 /**
	  * ɾ���ļ���
	  */
	 public static void delFolder(String folderPath) {
	     try {
	        delAllFile(folderPath); //ɾ����������������
	        String filePath = folderPath;
	        filePath = filePath.toString();
	        java.io.File myFilePath = new java.io.File(filePath);
	        myFilePath.delete(); //ɾ�����ļ���
	     } catch (Exception e) {
	       e.printStackTrace(); 
	     }
	}
}
