package com.example.a1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class act1 extends Activity {
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        requestWindowFeature(Window.FEATURE_NO_TITLE); 
	    	   getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    	   
	    	   
	        setContentView(R.layout.act1);

	    	
	        HashMap<String, String> resultMap = installPackagesInfo();  
	        
	        new Handler().postDelayed(new Runnable() {
	            public void run() {
	                /* Create an Intent that will start the Main WordPress Activity. */
	                Intent mainIntent = new Intent(act1.this, MainActivity.class);
	                act1.this.startActivity(mainIntent);
	                act1.this.finish();
	            	
	            }
	        }, 1000); 
	       
	    }

	    private HashMap<String, String> installPackagesInfo(){  
	        // ��ȡpackageManager����  
	        PackageManager packageManager = this.getPackageManager();  
	        /*getInstalledApplications ���ص�ǰ�豸�ϰ�װ��Ӧ�ð����� 
	         * ApplicationInfo��Ӧ��androidManifest.xml�е�application��ǩ��ͨ�������Ի�ȡ��application��Ӧ����Ϣ 
	         */  
	        List<ApplicationInfo> applicationInfos = packageManager.getInstalledApplications(0);  
	        HashMap<String, String> resultMap = new HashMap<String, String>();  
	        Iterator<ApplicationInfo> iterator = applicationInfos.iterator();  
	        while(iterator.hasNext()){  
	            ApplicationInfo applicationInfo = iterator.next();  
	            String packageName = applicationInfo.packageName;// ����  
	            String packageLabel = packageManager.getApplicationLabel(applicationInfo).toString();//��ȡlabel  
	            resultMap.put(packageLabel, packageName);  
	        }  
	          
	        return resultMap;  
	          
	    }  
}
