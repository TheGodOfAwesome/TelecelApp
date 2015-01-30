package com.example.telecelapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.telecelapp.SimpleGestureFilter;
import com.example.telecelapp.SimpleGestureFilter.SimpleGestureListener;

public class Promotions extends Activity implements SimpleGestureListener{
	
	 private SimpleGestureFilter detector;
	 int i=0;
	 
	 
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.promotions);
	        
	     // Detect touched area 
	        detector = new SimpleGestureFilter(this,this);
	        
	        final ImageView iv2 = (ImageView) findViewById(R.id.background);
	        final int []imageArray = {R.drawable.promotion_background_1,R.drawable.promotion_background_2,R.drawable.promotion_background_3};
			final Handler handler = new Handler();
	        Runnable runnable = new Runnable() {
	           
	           public void run() {
	               iv2.setImageResource(imageArray [i]);
	               i++;
	               if(i>imageArray.length-1)
	               {
	               i=0;    
	               }
	               handler.postDelayed(this, 5000);  //for interval...
	           }
	       };
	       handler.postDelayed(runnable, 2000); //for initial delay..
	 }
	 
	 @Override
	    public boolean dispatchTouchEvent(MotionEvent me){
	    	// Call onTouchEvent of SimpleGestureFilter class
	         this.detector.onTouchEvent(me);
	       return super.dispatchTouchEvent(me);
	    }
		@Override
		 public void onSwipe(int direction) {
		  String str = "";
		 
		  switch (direction) {
		 
		  case SimpleGestureFilter.SWIPE_RIGHT : str = "Swipe Right";
		  changescreen();
		                                                 break;
		  case SimpleGestureFilter.SWIPE_LEFT :  str = "Swipe Left";
		  changescreen();
		                                                 break;
		  }
		 }
		 
		 @Override
		 public void onDoubleTap() {
		    Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
		 }
		 
		 public void changescreen(){
			 // Detect touched area 
		        detector = new SimpleGestureFilter(this,this);
		        
		        final ImageView iv2 = (ImageView) findViewById(R.id.background);
		        final int []imageArray = {R.drawable.promotion_background_1,R.drawable.promotion_background_2,R.drawable.promotion_background_3};
				final Handler handler = new Handler();
		        Runnable runnable = new Runnable() {
		           
		           public void run() {
		               iv2.setImageResource(imageArray [i]);
		               i++;
		               if(i>imageArray.length-1)
		               {
		               i=0;    
		               }
		           }
		       };
		       handler.post(runnable); //for initial delay..
		 }
			 

}
