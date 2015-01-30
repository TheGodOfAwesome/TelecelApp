package com.example.telecelapp;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
 
public class VideoViewConsole extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                   setContentView(R.layout.console);
                   showVideo();
               }
    
    @Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
    
            private void showVideo()
            {
                VideoView vd = (VideoView)findViewById(R.id.VideoScreen);
                Uri uri = Uri.parse("android.resource://"+ getApplication().getPackageName() +"/"+R.raw.movie);
                MediaController mc = new MediaController(this);
                vd.setMediaController(mc);
                vd.setVideoURI(uri);
                vd.start();
                vd.setOnCompletionListener(new OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Toast.makeText(getApplicationContext(), "Video completed", Toast.LENGTH_LONG).show();
                        Class ourClass = null;
                    	try {
        					ourClass = Class.forName("com.example.telecelapp.MainActivity");
        				} catch (ClassNotFoundException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        				}
        			    Intent ourIntent = new Intent(VideoViewConsole.this, ourClass);
        			    startActivity(ourIntent);
                    }
                });
                
            }
}


