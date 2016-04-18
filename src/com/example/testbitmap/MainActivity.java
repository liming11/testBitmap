package com.example.testbitmap;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
public class MainActivity extends Activity {
    private ImageView view ;
    private static final String ImageUrl = "http://192.168.202.64:8080/amuro/captchaView120_40.jpg";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// LayoutInflater inflater = LayoutInflater.from(this); 
		    view = (ImageView)findViewById(R.id.background);
		    view.setOnClickListener(new View.OnClickListener() {
		        public void onClick(View v) {
		        	//do something
		        	
		        	        }
		        	    });
	}

	/*Handler handler = new Handler() {
	    @Override  
	    public void handleMessage(Message msg) {  
	        super.handleMessage(msg);  
	        Bundle data = msg.getData();  
	        String val = data.getString("value");  
	        Log.i("mylog", "������Ϊ-->" + val);  
	        // TODO  
	        // UI����ĸ��µ���ز���  
	    }  
	};  
  
	Runnable networkTask = new Runnable() {  
	  
	    @Override  
	    public void run() {  
	        // TODO
	    	Bitmap bitmap = null;
	        // ��������� http request.����������ز���  
            URL myFileUrl = null;  
            InputStream is = null;  
	        HttpURLConnection conn = null;  
	        try {  
	            myFileUrl = new URL("");  
	        } catch (MalformedURLException e) {  
	            e.printStackTrace();  
	        } 
	        try {  
	            conn = (HttpURLConnection)myFileUrl  
	                    .openConnection();  
	            conn.setDoInput(true);  
	            conn.connect();  
	            is =conn.getInputStream();  
	            bitmap =BitmapFactory.decodeStream(is);  
	            is.close();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }finally{                 
                try {  
                    if(is != null){  
                    is.close();  
                    }  
                    if( conn != null){  
                        conn.disconnect();  
                    }  
                } catch (IOException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }                 
	        }

	        Message msg = new Message();
	        msg.obj = bitmap;
	        Bundle data = new Bundle();  
	        data.putString("value", "������");  
	        msg.setData(data);  
	        handler.sendMessage(msg);  
	    }  
	}; */
	
	
	
	class DownImage extends AsyncTask<String, Void, Bitmap> {
		  
	    private ImageView imageView;  
	  
	    public DownImage(ImageView imageView) {
	        this.imageView = imageView;
	    }  
	  
	    @Override  
	    protected Bitmap doInBackground(String... params) {  
            URL myFileUrl = null;  
            Bitmap bitmap = null;  
            InputStream is = null;  
	        HttpURLConnection conn = null;  
	        try {  
	            myFileUrl = new URL(params[0]);  
	        } catch (MalformedURLException e) {  
	            e.printStackTrace();  
	        } 
	        try {  
	            conn = (HttpURLConnection)myFileUrl  
	                    .openConnection();  
	            conn.setDoInput(true);  
	            conn.connect();  
	            is =conn.getInputStream();  
	            bitmap =BitmapFactory.decodeStream(is);  
	            is.close();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }finally{                 
                try {  
                    if(is != null){  
                    is.close();  
                    }  
                    if( conn != null){  
                        conn.disconnect();  
                    }  
                } catch (IOException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }                 
	        } 
	        return bitmap;  
	    }  
	  
	    @Override  
	    protected void onPostExecute(Bitmap result) {  
	    	imageView.setImageBitmap(result);
	        super.onPostExecute(result);
	    }  
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		//networkTask.run();
		//new DownImage(view).execute(ImageUrl);
	    // ����һ�����̣߳���������������ȴ��з��ؽ����ʹ��handler֪ͨUI
	}
}
