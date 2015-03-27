package com.example.a1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity 
{
	MediaPlayer mprain; 
	MediaPlayer mplake; 
	Boolean israinplay = false;
    Boolean islakeplay = false;
    float lakevol=(float)0.1;
    String[] ls=null;
    ListView listViewleft=null;
    ListView listViewright=null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solidrelative);
        init();
//        if (savedInstanceState == null) {
//            getFragmentManager().beginTransaction()
//                    .add(R.id.container, new PlaceholderFragment())
//                    .commit();
//        }
    }
    ListSelect adapter;
    ListSelect adapterright;
    private void init()
    {
    	BidirSlidingLayout bidirSldingLayout= (BidirSlidingLayout) findViewById(R.id.bidir_sliding_layout);  
        RelativeLayout container1 = (RelativeLayout) findViewById(R.id.container123);  
       
        bidirSldingLayout.setScrollEvent(container1); 
        
        	mprain=MediaPlayer.create(this,R.raw.rainsound);
        	mprain.setOnCompletionListener(mplistener);
        	
        	mplake=MediaPlayer.create(this,R.raw.lake);
        	mplake.setOnCompletionListener(mplistenerlake);
        	createSDCardDir();
        	
        	adapter = new ListSelect(this,getData()); 
        	listViewleft = (ListView)findViewById(R.id.leftlist);
        	listViewleft.setAdapter(adapter);
        	listViewleft.setChoiceMode(ListView.CHOICE_MODE_SINGLE);// 一定要设置这个属性，否则ListView不会刷新    
        	listViewleft.setOnItemClickListener(new OnItemClickListener() {    
        	@Override   
        	public void onItemClick(AdapterView<?> arg0, View arg1,    
        	int position, long id) {    
        		adapter.cur_pos = position;// 更新当前行    
        		adapter.notifyDataSetInvalidated();
        		
        		String item = adapter.getItem(position);
        		if(item.equals("细雨"))
                {
    				 StopMp(mprain);
    		        	mprain=MediaPlayer.create(MainActivity.this,R.raw.rainsound);
    		        	mprain.setOnCompletionListener(mplistener);
                }
    			 else if(item.equals("大海"))
                {
    				 StopMp(mprain);
    		        	mprain=MediaPlayer.create(MainActivity.this,R.raw.sea);
    		        	mprain.setOnCompletionListener(mplistener);
                }
    			 else
    			 {
    				 File sdcardDir =Environment.getExternalStorageDirectory();
    		            //得到一个路径，内容是sdcard的文件夹路径和名字
    		              String path=sdcardDir.getPath()+"/rainlake/"+item;
    		              StopMp(mprain);
    		              mprain = new MediaPlayer();
    		              try {
    		            	  mprain.setDataSource(path);
    					} catch (IllegalArgumentException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					} catch (SecurityException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					} catch (IllegalStateException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					} catch (IOException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    			 }
        	}    
        	});   
        	
        	
        	
        	/*listViewleft.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked,getData()));
        	listViewleft.setOnItemClickListener(itemlistenleft);*/
        	adapterright = new ListSelect(this,getData()); 
        	listViewright = (ListView)findViewById(R.id.rightlist);
        	listViewright.setAdapter(adapterright);
        	listViewright.setChoiceMode(ListView.CHOICE_MODE_SINGLE);// 一定要设置这个属性，否则ListView不会刷新    
        	listViewright.setOnItemClickListener(new OnItemClickListener() {    
        	@Override   
        	public void onItemClick(AdapterView<?> arg0, View arg1,    
        	int position, long id) {    
        		adapterright.cur_pos = position;// 更新当前行    
        		adapterright.notifyDataSetInvalidated();
        		String item = adapterright.getItem(position);
        		if(item.equals("细雨"))
                {
     				 StopMp(mplake);
     				 mplake=MediaPlayer.create(MainActivity.this,R.raw.rainsound);
     				 mplake.setOnCompletionListener(mplistener);
                }
     			 else if(item.equals("大海"))
                {
     				 StopMp(mplake);
     				 mplake=MediaPlayer.create(MainActivity.this,R.raw.sea);
     				 mplake.setOnCompletionListener(mplistener);
                }
     			 else
     			 {
     				 File sdcardDir =Environment.getExternalStorageDirectory();
     		            //得到一个路径，内容是sdcard的文件夹路径和名字
     		              String path=sdcardDir.getPath()+"/rainlake/"+item;
     		              StopMp(mplake);
     		              mplake = new MediaPlayer();
     		              try {
     		            	  mplake.setDataSource(path);
     					} catch (IllegalArgumentException e) {
     						// TODO Auto-generated catch block
     						e.printStackTrace();
     					} catch (SecurityException e) {
     						// TODO Auto-generated catch block
     						e.printStackTrace();
     					} catch (IllegalStateException e) {
     						// TODO Auto-generated catch block
     						e.printStackTrace();
     					} catch (IOException e) {
     						// TODO Auto-generated catch block
     						e.printStackTrace();
     					}
     			 }
        	}    
        	});   
        	//listViewright.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked,getData()));
        	//listViewright.setOnItemClickListener(itemlistenright);
        	//this.registerForContextMenu(findViewById(R.id.button3));
        	//this.registerForContextMenu(findViewById(R.id.button4));
    }
    
    private List<String> getData(){
        
        List<String> data = new ArrayList<String>();
        /*data.add("1");
        data.add("11");
        data.add("111");
        data.add("1111");
        data.add("11111");
        data.add("111111");
        data.add("1111111");*/
        data.add("细雨");
        data.add("大海");
        if(ls!=null)
		   {
		   for(int i=0;i<ls.length;i++)
		   {
			   data.add(ls[i]); 			   
		   }
		   }
        return data;
    }
    
    MediaPlayer.OnCompletionListener mplistener = new MediaPlayer.OnCompletionListener() {
		
		@Override
		public void onCompletion(MediaPlayer arg0) {

			String item = adapter.getItem(adapter.cur_pos);
			if(item.equals("细雨"))
            {
 				
            }
 			 else if(item.equals("大海"))
            {
 				 
            }
 			 else
 			 {
 				 File sdcardDir =Environment.getExternalStorageDirectory();
 		            //得到一个路径，内容是sdcard的文件夹路径和名字
 		              String path=sdcardDir.getPath()+"/rainlake/"+item;
 		              
 		              try {
 		            	 arg0.setDataSource(path);
 					} catch (IllegalArgumentException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					} catch (SecurityException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					} catch (IllegalStateException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					} catch (IOException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					}
 			 }
			
			try {
				arg0.prepareAsync();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 			
			arg0.start();
		}
	};
    
	MediaPlayer.OnCompletionListener mplistenerlake = new MediaPlayer.OnCompletionListener() {
		
		@Override
		public void onCompletion(MediaPlayer arg0) {
			String item = adapter.getItem(adapterright.cur_pos);
			if(item.equals("细雨"))
            {
 				
            }
 			 else if(item.equals("大海"))
            {
 				 
            }
 			 else
 			 {
 				 File sdcardDir =Environment.getExternalStorageDirectory();
 		            //得到一个路径，内容是sdcard的文件夹路径和名字
 		              String path=sdcardDir.getPath()+"/rainlake/"+item;
 		              
 		              try {
 		            	 arg0.setDataSource(path);
 					} catch (IllegalArgumentException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					} catch (SecurityException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					} catch (IllegalStateException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					} catch (IOException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					}
 			 }
			try {
				arg0.prepareAsync();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 	
			arg0.start();
		}
	};
	
	
	@Override 
	public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        menu.add(1, 1, 2, "帮助");
        //menu.add("下载其他音乐");
        //menu.add(1, 1, 1, "鋼琴");
        //menu.add(1, 2, 2, "古琴");
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
        	 //setContentView(R.layout.line);
        	dialog("可以在SD卡的rainlake目录下放入喜欢的mp3文件");
    break;
		case 2:
        	
        	
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

    public  void onreturn(View v)
    {
    	init();
    }
    
    public  void onrain(View v)
    {
    	Button b = (Button)findViewById(R.id.button3);
    	if(!israinplay)
    	{
    		try {
				mprain.prepareAsync();
			} catch (IllegalStateException e) {
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
				mplake.prepareAsync();
			} catch (IllegalStateException e) {
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
    	lakevol+=0.1;
    	mplake.setAudioStreamType(AudioManager.STREAM_VOICE_CALL);
    	mplake.setVolume(lakevol,lakevol);
    }
    
    public void createSDCardDir(){
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
        {
               // 创建一个文件夹对象，赋值为外部存储器的目录
              File sdcardDir =Environment.getExternalStorageDirectory();
              //得到一个路径，内容是sdcard的文件夹路径和名字
                String path=sdcardDir.getPath()+"/rainlake";
                File path1 = new File(path);
               if (!path1.exists()) 
               {
                //若不存在，创建目录，可以在应用启动的时候创建
                path1.mkdirs();
                //dialog("paht ok,path:"+path);
              }
               else
               {
            	   ls =path1.list();
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
 // 创建上下文菜单  
    public void onCreateContextMenu(ContextMenu menu, View v,  
    ContextMenuInfo menuInfo) {  
   // 向上下文菜单中添加菜单项  
   // 注意此处的menu是ContextMenu  
    	if(( (android.widget.Button)v).getTag().toString().equals("y"))
    	{
		   menu.add(0, 1, 0, "雨");  
		   menu.add(0, 2, 0, "海");  
    	}
    	if(( (android.widget.Button)v).getTag().toString().equals("q"))
    	{
		   menu.add(0, 3, 0, "钢琴");  
		   menu.add(0, 4, 0, "古琴"); 
		   if(ls!=null)
		   {
		   for(int i=0;i<ls.length;i++)
		   {
			   menu.add(0, i+5, 0, ls[i]); 			   
		   }
		   }
    	}
 } 
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	//dialog(Integer.toString(item.getItemId()));
    	switch(item.getItemId()) {
        case 1:
        	StopMp(mprain);
        	mprain=MediaPlayer.create(this,R.raw.rainsound);
        	mprain.setOnCompletionListener(mplistener);
    break;
        case 2:
        	StopMp(mprain);
        	mprain=MediaPlayer.create(this,R.raw.sea);
        	mprain.setOnCompletionListener(mplistener);
    break;
        case 3:
        	StopMp(mplake);
        	mplake=MediaPlayer.create(this,R.raw.lake);
        	mplake.setOnCompletionListener(mplistener);
    break;
        case 4:
        	StopMp(mplake);
        	mplake=MediaPlayer.create(this,R.raw.gq);
        	mplake.setOnCompletionListener(mplistener);
    break;
        default:
            //return super.onContextItemSelected(item);
        	File sdcardDir =Environment.getExternalStorageDirectory();
            //得到一个路径，内容是sdcard的文件夹路径和名字
              String path=sdcardDir.getPath()+"/rainlake/"+item.getTitle();
              StopMp(mplake);
              mplake = new MediaPlayer();
              try {
				mplake.setDataSource(path);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return true;
    	//return super.onContextItemSelected(item);
    }
    
    void StopMp(MediaPlayer mp)
    {
    	if(mp!=null)
    	{
    		mp.stop();
    		mp.release();
    	}
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
    	  /*builder.setPositiveButton("测试", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				 Toast.makeText(MainActivity.this, "谈不上喜欢不喜欢。", Toast.LENGTH_LONG)
			       .show();
			}
		});*/
    	    //builder.create().show();
    	    builder.show();
    	}
}
