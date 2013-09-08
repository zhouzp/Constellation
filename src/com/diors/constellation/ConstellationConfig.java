package com.diors.constellation;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import android.content.Context;
import com.diors.utils.L;
import com.diors.constellation.R;

public class ConstellationConfig {
	/**
	 * the switch of the debug .<br>
	 * if turn on (true), will print the log in the console,<br>
	 * otherwise, will never print the logs(event the Google analytics log .)
	 */
	private static boolean debug		= false;
	/**
	 * whether wrtie the log info to the local file <br>
	 * @path /sdcard/MatchMove/logs/YYMMDD_log
	 */
	private static boolean persistLog 	= false;
	
	private static String	mainCategoruUrl = null;

	public static boolean 	init = false;
	
	public static void initConfig(Context context) {
		// if true , it means , have load the infos, so need not to load again .
		if(init) {
			return;
		}
		try {
			//read the configure file to the memory
			InputStream inputStream =  context.getResources().openRawResource(R.raw.config);
//			InputStream inputStream =  AppConfigs.class.getResourceAsStream("/anim/"+config);
			Properties properties  = new Properties();
			properties.load(inputStream);
			debug = Boolean.valueOf(properties.getProperty("debug"));
			L.e("Debug:"+debug);
			persistLog = Boolean.valueOf(properties.getProperty("persistLog"));
			L.e("persistLog:"+persistLog);

			mainCategoruUrl = properties.getProperty("mainCategoruUrl");
			L.e("mainCategoruUrl:"+mainCategoruUrl);
			init = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean isDebug() {
		return debug;
	}


	public static boolean isPersistLog() {
		return persistLog;
	}

	public static String getMainCategoruUrl() {
		return mainCategoruUrl;
	}
	
}
