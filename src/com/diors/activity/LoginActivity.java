package com.diors.activity;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.diors.constellation.R;
import com.diors.http.HttpManager;
import com.diors.http.ITransaction;
import com.diors.json.UserLoginJason;
import com.diors.utils.L;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;

public class LoginActivity extends Activity {
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        EditText editTextPhoneNum = (EditText) findViewById(R.id.editTextPhoneNum);
        editTextPhoneNum.requestFocus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }
    
    private String userMgmtUrl = "http://localhost:8080";
    
    private void Login(String url)
    {
    	Map<String, String> aRequestData = new HashMap<String, String>(); 
    	new HttpManager(userMgmtUrl, null, HttpManager.GET, getUserLoginTransaction).start();
    }
    
    private ITransaction getUserLoginTransaction = new ITransaction() {
    	@Override
    	public void transactionOver(String result) {
    		mapperJson(result);
    		//L.d("get the category from the net");
    	}
    	
    	@Override
    	public void transactionException(int erroCode,String result, Exception e) {
    		//mHandler.obtainMessage(HANDLER_LOAD_CATEGORY_ERROR).sendToTarget();
    		L.e("getUserLogin Transaction exception at getUserLoginTransaction", e);
    	}
    };
    
    private void mapperJson(String result) {
    	try {
    		UserLoginJason aReponseObject = objectMapper.readValue(result, new TypeReference<UserLoginJason>() { });
    		//L.e("version:"+mResponseObject.getVersion());
    		/*
    		if(fromNet) {
    			//if it is load from net ,save category
    			saveWikiCategory(mResponseObject.getVersion(),mResponseObject.getPageId(), result);
    		}
    		else {
    			//check the net wiki
    			UpdateEntity updateEntity = mWikiUpdateDao.getWikiUpdateByUrl(mUrl);
    			if(updateEntity!=null) {
    				long current = System.currentTimeMillis();
    				if((current-updateEntity.getUpdateDate())>DateUtil.DAY_MILLIS) {
    					L.d("need to refreah the cache:"+mUrl);
    					//check the new wiki every day
    					mHandler.sendEmptyMessage(HANDLER_REFRESH_CATEGORY_NET);
    				}
    			}
    			else {
    				//如果这次是多缓存中读取的，但是去没有一个update的时候，则更新update数据库
    				mWikiUpdateDao.addOrUpdateTime(mUrl);
    			}
    		}
    		*/
    		//mHandler.obtainMessage(HANDLER_DISPLAY_CATEGORY, mResponseObject).sendToTarget();
    	} catch (Exception e) {
    		L.e("getUserLogin Transaction exception", e);
    		/*if(!fromNet) {
    			L.d("category content is erro which is read from the cache dir");
    			//如果不是从网络来的。错误了还得从网络去拿一次
    			mHandler.sendEmptyMessage(HANDLER_LOAD_CATEGORY_NET);
    		}
    		else {
    			//如果是从网络上面来的，错误了就错误了
    			mHandler.obtainMessage(HANDLER_LOAD_CATEGORY_ERROR).sendToTarget();
    		}
    		*/
    	}
    }

}