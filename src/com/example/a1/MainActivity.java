package com.example.a1;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Text;

import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity 
{

	MediaPlayer mprainlake;  
	MediaPlayer mprain; 
	MediaPlayer mplake; 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        
        
        	mprainlake=MediaPlayer.create(this,R.raw.rainlake);
        	mprainlake.setOnCompletionListener(mplistener);
        	
        	mprain=MediaPlayer.create(this,R.raw.rainsound);
        	mprain.setOnCompletionListener(mplistener);
        	
        	mplake=MediaPlayer.create(this,R.raw.lake);
        	mplake.setOnCompletionListener(mplistener);

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
        menu.add("下载其他音乐");
        //menu.add(1, 1, 1, "item 1");
        //menu.add(1, 2, 2, "item 2");
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
        
        //响应每个菜单项(通过菜单项的ID)
        case 0:
        	
        	dialog("暂未开放功能");
            break;
    case 1:
    	
    	
    break;
        case 2:
            // do something here
        	
    break;
        case 3:
            // do something here
        	
    break;
        case 4:
            // do something here
        	
    break;
        default:
            //对没有处理的事件，交给父类来处理
    //return super.onOptionsItemSelected(item);
        }
        
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
        
        
    }

    public  void onrain(View v)
    {
    	 mprain.start();    	
    }
    
    public  void onlake(View v)
    {
    	//mplake.setVolume(10, 10);
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
    }
    
    float lakevol=(float)0.1;
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

      Button b = (Button)findViewById(R.id.button2);
      if(b.getTag().toString()!="pause")
      {
    	  b.setBackgroundResource(R.drawable.main_pause);
          b.setTag("pause");
          mprainlake.start();
      }
      else
      {
      b.setBackgroundResource(R.drawable.main_play); 
	  b.setTag("play");
	  mprainlake.stop();
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
    	if(mprainlake!=null)
    		mprainlake.release();
    	if(mprain!=null)
    		mprain.release();
    	if(mplake!=null)
    		mplake.release();
    	super.onStop();
    }
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	super.onDestroy();
    	
    }
    
    protected void dialog(String  s) {
    	  AlertDialog.Builder builder = new Builder(MainActivity.this);
    	  builder.setMessage(s);  
    	  builder.setTitle("提示");  
    	  builder.setNegativeButton("确认", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				arg0.dismiss();
			}
		});
    	  builder.setPositiveButton("测试", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				 Toast.makeText(MainActivity.this, "谈不上喜欢不喜欢。", Toast.LENGTH_LONG)
			       .show();
			}
		});
    	    //builder.create().show();
    	    builder.show();
    	}
}
