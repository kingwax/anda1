package com.example.a1;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        menu.add("�˵���1");
        
        menu.add(1, 1, 1, "item 1");
        menu.add(1, 2, 2, "item 2");
        menu.add(2, 3, 3, "item 3");
        menu.add(2, 4, 4, "item 4");
        return true;
    }
	MediaPlayer mediaPlayer;  
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    	
        int id = item.getItemId();
        dialog( Integer.toString(id));
        switch (id) {
        
        //��Ӧÿ���˵���(ͨ���˵����ID)
    case 1:
    	
    	mediaPlayer=MediaPlayer.create(this,R.raw.rainlake);
    	mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer arg0) {
				
				arg0.start();
			}
		});
    	mediaPlayer.start();
    break;
        case 2:
            // do something here
        	mediaPlayer.stop();
    break;
        case 3:
            // do something here
        	mediaPlayer.pause();
    break;
        case 4:
            // do something here
        	mediaPlayer.start();
    break;
        default:
            //��û�д������¼�����������������
    //return super.onOptionsItemSelected(item);
        }
        
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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