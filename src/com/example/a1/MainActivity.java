package com.example.a1;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity 
{
	//MediaPlayer mprainlake;  
	MediaPlayer mprain; 
	MediaPlayer mplake; 
	Boolean israinplay = false;
    Boolean islakeplay = false;
    float lakevol=(float)0.1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
         	
        	mprain=MediaPlayer.create(this,R.raw.rainsound);
        	mprain.setOnCompletionListener(mplistener);
        	
        	mplake=MediaPlayer.create(this,R.raw.lake);
        	mplake.setOnCompletionListener(mplistener);
        	createSDCardDir();
        	
        	this.registerForContextMenu(findViewById(R.id.button3));
        	this.registerForContextMenu(findViewById(R.id.button4));
    }

    MediaPlayer.OnCompletionListener mplistener = new MediaPlayer.OnCompletionListener() {
		
		@Override
		public void onCompletion(MediaPlayer arg0) {
			
			arg0.start();
		}
	};
    
	
	@Override 
	public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        //menu.add("������������");
        menu.add(1, 1, 1, "���");
        menu.add(1, 2, 2, "����");
        //menu.add(2, 3, 3, "item 3");
        //menu.add(2, 4, 4, "item 4");
        return true;
    }
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    	
        int id = item.getItemId();
        
        switch (id) {
        
        //��Ӧÿ���˵���(ͨ���˵����ID)
        case 0:
        	
        	dialog("��δ���Ź���");
            break;
        case 1:
	    	mplake=null;
	    	mplake=MediaPlayer.create(this,R.raw.lake);
	    	mplake.setOnCompletionListener(mplistener);
    break;
		case 2:
        	mplake=null;
        	mplake=MediaPlayer.create(this,R.raw.gq);
        	mplake.setOnCompletionListener(mplistener);
        	
    break;
        case 3:
            // do something here
        	
    break;
        case 4:
            // do something here
        	
    break;
        default:
            //��û�д�����¼�����������������
    //return super.onOptionsItemSelected(item);
        }
        
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
        
        
    }

    public  void onrain(View v)
    {
    	Button b = (Button)findViewById(R.id.button3);
    	if(!israinplay)
    	{
    		try {
				mprain.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	mprain.start();  
	    	israinplay=true;
	    	b.setBackgroundResource(R.drawable.main_pause);
    	}
    	else
    	{
    		mprain.pause();
	    	israinplay=false;
	    	b.setBackgroundResource(R.drawable.main_play);
    	}
    }
    
    public  void onlake(View v)
    {
    	Button b = (Button)findViewById(R.id.button4);
    	if(!islakeplay)
    	{
    		try {
				mplake.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		mplake.start();  
	    	islakeplay=true;
	    	b.setBackgroundResource(R.drawable.main_pause);
    	}
    	else
    	{
    		mplake.pause();
    		islakeplay=false;
	    	b.setBackgroundResource(R.drawable.main_play);
    	}
    }

    public  void onvol(View v)
    {
    	TextView t = (TextView)findViewById(R.id.textView1);
    	lakevol+=0.1;
    	t.setText(Float.toString(lakevol));
    	mplake.setAudioStreamType(AudioManager.STREAM_VOICE_CALL);
    	mplake.setVolume(lakevol,lakevol);
    }
    
    public  void onClick2(View v)
    {
    }
    
    public void createSDCardDir(){
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
               // ����һ���ļ��ж��󣬸�ֵΪ�ⲿ�洢����Ŀ¼
                File sdcardDir =Environment.getExternalStorageDirectory();
              //�õ�һ��·����������sdcard���ļ���·��������
                String path=sdcardDir.getPath()+"/rainlake";
                File path1 = new File(path);
               if (!path1.exists()) {
                //�������ڣ�����Ŀ¼��������Ӧ��������ʱ�򴴽�
                path1.mkdirs();
                //dialog("paht ok,path:"+path);
              }
               }
        else{
        	//dialog("paht fail");
         return;

       }
       }
    
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            
           
            
            return rootView;
        }
    }

    @Override
    protected void onStop() {
    	// TODO Auto-generated method stub

    	
    	super.onStop();
    }
    
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	super.onDestroy();
    	if(mprain!=null)
    	{
    		mprain.stop();
    		mprain.release();
    	}
    	if(mplake!=null)
    	{
    		mplake.stop();
    		mplake.release();
    	}
    }
    
    @Override  
 // ���������Ĳ˵�  
 public void onCreateContextMenu(ContextMenu menu, View v,  
    ContextMenuInfo menuInfo) {  
   // �������Ĳ˵�����Ӳ˵���  
   // ע��˴���menu��ContextMenu  
    	if(( (android.widget.Button)v).getTag().toString().equals("y"))
    	{
   menu.add(0, 1, 0, "��");  
   menu.add(0, 2, 0, "��");  
    	}
    	if(( (android.widget.Button)v).getTag().toString().equals("q"))
    	{
   menu.add(0, 3, 0, "����");  
   menu.add(0, 4, 0, "����");  
    	}
 } 
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	dialog(Integer.toString(item.getItemId()));
    	switch(item.getItemId()) {
        case 1:
            // do something
    break;
        case 2:
            // do something
    break;
        case 3:
            // do something
    break;
        case 4:
            // do something
    break;
        default:
            return super.onContextItemSelected(item);
        }
        return true;
    	//return super.onContextItemSelected(item);
    }
    
    protected void dialog(String  s) {
    	  AlertDialog.Builder builder = new Builder(MainActivity.this);
    	  builder.setMessage(s);  
    	  builder.setTitle("��ʾ");  
    	  builder.setNegativeButton("ȷ��", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				arg0.dismiss();
			}
		});
    	  builder.setPositiveButton("����", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				 Toast.makeText(MainActivity.this, "̸����ϲ����ϲ����", Toast.LENGTH_LONG)
			       .show();
			}
		});
    	    //builder.create().show();
    	    builder.show();
    	}
}
