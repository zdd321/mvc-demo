package com.zdd.util;

import java.io.File;
import java.text.ParseException;
import java.util.Date;

/**
 * 文件删除操作
 * @author Administrator
 *
 */
public class DeleteFile {

	/**
	 * 删除文件
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
	             delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
	             delFolder(path + "/" + tempList[i]);//再删除空文件夹
	             flag = true;
	          }
	       }
	       return flag;
	     }
	 /**
	  * 删除文件夹
	  */
	 public static void delFolder(String folderPath) {
	     try {
	        delAllFile(folderPath); //删除完里面所有内容
	        String filePath = folderPath;
	        filePath = filePath.toString();
	        java.io.File myFilePath = new java.io.File(filePath);
	        myFilePath.delete(); //删除空文件夹
	     } catch (Exception e) {
	       e.printStackTrace(); 
	     }
	}
}
