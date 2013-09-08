package com.diors.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Environment;
import android.text.TextUtils;
import com.diors.constellation.Constants;

public class FileUtil {

	/**
	 * get the external storage file
	 * 
	 * @return the file
	 */
	public static File getExternalStorageDir() {
		return Environment.getExternalStorageDirectory();
	}

	/**
	 * get the external storage file path
	 * 
	 * @return the file path
	 */
	public static String getExternalStoragePath() {
		return getExternalStorageDir().getAbsolutePath();
	}

	/**
	 * get the external storage state
	 * 
	 * @return
	 */
	public static String getExternalStorageState() {
		return Environment.getExternalStorageState();
	}

	/**
	 * check the usability of the external storage.
	 * 
	 * @return enable -> true, disable->false
	 */
	public static boolean isExternalStorageEnable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		}
		return false;

	}
	
	public static void initExternalDir(boolean cleanFile)
	{
		if(isExternalStorageEnable())
		{
			File external = new File(Constants.EXTERNAL_DIR);
			if(!external.exists())
			{
				external.mkdirs();
			}
			//check the cache whether exist
			File cache = new File(Constants.CACHE_DIR);
			if(!cache.exists())
			{
				cache.mkdirs();
			}
			else
			{
				if(cleanFile)
				{
					//if exist,so clear the old file
					cleanFile(cache, DateUtil.WEEK_MILLIS);
				}
			}
			//check the log dir
			File logs = new File(Constants.LOGS_DIR);
			if(!logs.exists())
			{
				logs.mkdirs();
			}
			else
			{
				if(cleanFile)
				{
					cleanFile(logs, DateUtil.HALF_MONTH_MILLIS);
				}
			}
		}
	}
	
	public static int cleanFile(File dir, long maxInterval)
	{
		File[] files = dir.listFiles();
		if(files == null) return 0;
		int beforeNum = 0;
		long current = System.currentTimeMillis();
		for(File file:files)
		{
			long lastModifiedTime = file.lastModified();
			if((current-lastModifiedTime) > maxInterval)
			{
				//if the file is exist more than a week , so need to delete.
				file.delete();
				beforeNum ++;
			}
		}
		return beforeNum;
	}

	public static String getFileContent(String path) 
	{

		File file = new File(path);
		return getFileContent(file);
	}
	
	public static String getFileContent(File file) 
	{
		if(!file.exists())
		{
			return null;
		}
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			StringBuilder sb = new StringBuilder();
			byte buffer[] = new byte[2048];
			int len = 0;
			while ((len = fis.read(buffer)) != -1) {
				sb.append(new String(buffer, 0, len));
			}
			return sb.toString();
		} catch (Exception e) {
			L.e("get file exception:"+file.getAbsolutePath(), e);
		}
		finally
		{
			if(fis !=null)
			{
				try {
					fis.close();
				} catch (IOException e) {
				}
			}
		}
		return null;
	}

	public static boolean saveFile(String content,String path) 
	{
		if(isExternalStorageEnable())
		{
			File cacheFile = new File(path);
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(cacheFile);
				fos.write(content.getBytes());
				fos.flush();
				return true;
			} catch (IOException e) {
				L.e("save file exception:"+path,e);
			}
			finally
			{
				if(fos !=null)
				{
					try {
						fos.close();
					} catch (IOException e) {
					}
				}
			}
		}
		return false;
	}
	
	public static boolean deleteFile(String path)
	{
		if(TextUtils.isEmpty(path))
		{
			return true;
		}
		File file = new File(path);
		if(file.exists())
		{
			return file.delete();
		}
		return true;
	}
	
	public static boolean isFileExist(String path)
	{
		if(TextUtils.isEmpty(path))
			return false;
		File file = new File(path);
		return file.exists();
	}
}
