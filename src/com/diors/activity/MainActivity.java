package com.diors.activity;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.diors.constellation.R;
import com.diors.http.HttpManager;
import com.diors.http.ITransaction;
import com.diors.json.ResponseJason;
import com.diors.json.UserLoginJason;
import com.diors.json.UserSignUpJason;
import com.diors.utils.L;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	ObjectMapper objectMapper = new ObjectMapper();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button mBtnLogin = (Button) findViewById(R.id.btnLogin);
		mBtnLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent _intent = new Intent(MainActivity.this,
						LoginActivity.class);
				startActivity(_intent);
			}
		});
		
		SignUp(signUpServerUrl);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private String signUpServerUrl = "http://192.168.60.197:2000/reg";

	private void SignUp(String signUpServerUrl) {
		Map<String, String> aRequestData = new HashMap<String, String>();
		aRequestData.put("phone", "13027931101");
		aRequestData.put("pwd", "helloPassword");
		aRequestData.put("code", "helloCode");
		new HttpManager(signUpServerUrl, aRequestData, HttpManager.POST,
				postSignUpTransaction).start();
	}

	private ITransaction postSignUpTransaction = new ITransaction() {
		@Override
		public void transactionOver(String result) {
			mapperJson(result);
			// L.d("get the category from the net");
		}

		@Override
		public void transactionException(int erroCode, String result,
				Exception e) {
			// mHandler.obtainMessage(HANDLER_LOAD_CATEGORY_ERROR).sendToTarget();
			L.e("post postSignUpTransaction exception at postSignUpTransaction",
					e);
		}
	};

	private void mapperJson(String result) {
		try {
			ResponseJason aReponseObject = objectMapper.readValue(result,
					new TypeReference<ResponseJason>() {
					});
			// L.e("version:"+mResponseObject.getVersion());
			/*
			 * if(fromNet) { //if it is load from net ,save category
			 * saveWikiCategory
			 * (mResponseObject.getVersion(),mResponseObject.getPageId(),
			 * result); } else { //check the net wiki UpdateEntity updateEntity
			 * = mWikiUpdateDao.getWikiUpdateByUrl(mUrl); if(updateEntity!=null)
			 * { long current = System.currentTimeMillis();
			 * if((current-updateEntity.getUpdateDate())>DateUtil.DAY_MILLIS) {
			 * L.d("need to refreah the cache:"+mUrl); //check the new wiki
			 * every day
			 * mHandler.sendEmptyMessage(HANDLER_REFRESH_CATEGORY_NET); } } else
			 * { //如果这次是多缓存中读取的，但是去没有一个update的时候，则更新update数据库
			 * mWikiUpdateDao.addOrUpdateTime(mUrl); } }
			 */
			// mHandler.obtainMessage(HANDLER_DISPLAY_CATEGORY,
			// mResponseObject).sendToTarget();
			
			new AlertDialog.Builder(MainActivity.this).setTitle(aReponseObject.getStatus()).setMessage(aReponseObject.getMsg()).show();
	} catch (Exception e) {
			L.e("postUserSignUp Transaction exception", e);
			/*
			 * if(!fromNet) {
			 * L.d("category content is erro which is read from the cache dir");
			 * //如果不是从网络来的。错误了还得从网络去拿一次
			 * mHandler.sendEmptyMessage(HANDLER_LOAD_CATEGORY_NET); } else {
			 * //如果是从网络上面来的，错误了就错误了
			 * mHandler.obtainMessage(HANDLER_LOAD_CATEGORY_ERROR
			 * ).sendToTarget(); }
			 */
		}
	}
}
