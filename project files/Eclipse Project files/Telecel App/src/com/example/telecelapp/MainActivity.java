package com.example.telecelapp;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity implements OnItemClickListener {
	
	final Context context = this;
	String encodedHash = Uri.encode("#");
	String NewPosition;
    ListView lview3;
    ListViewCustomAdapter adapter;
    private ArrayList<Object> itemList;
    private ItemBean bean;
   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final ImageView iv = (ImageView) findViewById(R.id.imageView1);
        final int []imageArray = {R.drawable.androidflash,R.drawable.androidflash2};
		
		final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
           int i=0;
           public void run() {
               iv.setImageResource(imageArray [i]);
               i++;
               if(i>imageArray.length-1)
               {
               i=0;    
               }
               handler.postDelayed(this, 3000);  //for interval...
           }
       };
       handler.postDelayed(runnable, 2000); //for initial delay..

 
     // add PhoneStateListener
		PhoneCallListener phoneListener = new PhoneCallListener();
		TelephonyManager telephonyManager = (TelephonyManager) this
				.getSystemService(Context.TELEPHONY_SERVICE);
		telephonyManager.listen(phoneListener,
				PhoneStateListener.LISTEN_CALL_STATE);

		
        prepareArrayLits();
        lview3 = (ListView) findViewById(R.id.listView1);
        adapter = new ListViewCustomAdapter(this, itemList);
        lview3.setAdapter(adapter);
 
        lview3.setOnItemClickListener(this);
    }
 
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
        // TODO Auto-generated method stub
    	ItemBean bean = (ItemBean) adapter.getItem(position);
		String Title;
		Title = bean.getTitle();
    	    if (Title == "Balance Enquiry"){
				Toast.makeText(this, "Balance Enquiry", Toast.LENGTH_SHORT).show();
				String Balance_Enq = "*122" + encodedHash;
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:" + Balance_Enq));
				startActivity(callIntent);
    	    }
			if (Title == "Recharge"){
				Toast.makeText(this, "Recharge", Toast.LENGTH_SHORT).show();
				Class ourClass;
				try {
					ourClass = Class.forName("com.example.telecelapp.Recharge");
				    Intent ourIntent = new Intent(MainActivity.this, ourClass);
				    startActivity(ourIntent);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (Title == "Telecash"){
				Toast.makeText(this, "Telecash", Toast.LENGTH_SHORT).show();
				String Telecash = "*888" + encodedHash;
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:" + Telecash));
				startActivity(callIntent);
    	    }
			if (Title == "Data Bundles"){
				Toast.makeText(this, "Data Bundles", Toast.LENGTH_SHORT).show();
				String Data_Bundles = "*144" + encodedHash;
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:" + Data_Bundles));
				startActivity(callIntent);
    	    }
			if (Title == "Whatsapp Bundles"){
				Toast.makeText(this, "Whatsapp Bundles", Toast.LENGTH_SHORT).show();
				String Whatsapp_Bundles = "*480" + encodedHash;
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:" + Whatsapp_Bundles));
				startActivity(callIntent);
    	    }
			if (Title == "Call Me Back"){
				Toast.makeText(this, "Processing Call Me Back", Toast.LENGTH_SHORT).show();
				Class ourClass;
				try {
					ourClass = Class.forName("com.example.telecelapp.Call_Me_Back");
				    Intent ourIntent = new Intent(MainActivity.this, ourClass);
				    startActivity(ourIntent);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (Title == "Smart Ads"){
				Toast.makeText(this, "Smart Ads", Toast.LENGTH_SHORT).show();
				Class ourClass;
				try {
					ourClass = Class.forName("com.example.telecelapp.CameraTestActivity");
				    Intent ourIntent = new Intent(MainActivity.this, ourClass);
				    startActivity(ourIntent);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (Title == "Promotions"){
				Toast.makeText(this, "Promotions", Toast.LENGTH_SHORT).show();
				Class ourClass;
				try {
					ourClass = Class.forName("com.example.telecelapp.Promotions");
				    Intent ourIntent = new Intent(MainActivity.this, ourClass);
				    startActivity(ourIntent);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (Title == "USSD Menu"){
				Toast.makeText(this, "USSD Menu", Toast.LENGTH_SHORT).show();
				String USSD_Menu = "*145" + encodedHash;
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:" + USSD_Menu));
				startActivity(callIntent);
    	    }
			if (Title == "Customer Care"){
				Toast.makeText(this, "Calling Customer Care", Toast.LENGTH_SHORT).show();
				String Customer_Care = "150";
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:" + Customer_Care));
				startActivity(callIntent);
    	    }
    	
    }
    
    /* Method used to prepare the ArrayList,
     * Same way, you can also do looping and adding object into the ArrayList.
     */
    public void prepareArrayLits()
    {
    	itemList = new ArrayList<Object>();
    	 
    	AddObjectToList(R.drawable.ic_balance, "Balance Enquiry", "Check your current airtime balance");
    	AddObjectToList(R.drawable.ic_recharge, "Recharge", "Recharge your airtime");
    	AddObjectToList(R.drawable.ic_telecash, "Telecash", "Access your Telecash Account");
    	AddObjectToList(R.drawable.ic_data, "Data Bundles", "Purchase Internet Data");
    	AddObjectToList(R.drawable.ic_whatsapp, "Whatsapp Bundles", "Buy Whatsapp credit");
    	AddObjectToList(R.drawable.ic_mail, "Call Me Back", "Send A Call Me Back");
    	AddObjectToList(R.drawable.ic_info, "Smart Ads", "See New Telecel Smart Ads");
    	AddObjectToList(R.drawable.ic_info, "Promotions", "See the latest Telecel Promotions");
    	AddObjectToList(R.drawable.ic_info, "USSD Menu", "See a comprehensive list of USSD commands");
    	AddObjectToList(R.drawable.ic_info, "Customer Care", "Call customer care");
    }
    
    // Add one item into the Array List
    public void AddObjectToList(int image, String title, String desc)
    {
    	bean = new ItemBean();
    	bean.setDescription(desc);
    	bean.setImage(image);
    	bean.setTitle(title);
    	itemList.add(bean);
    }
    
  
    
	private class PhoneCallListener extends PhoneStateListener {

		private boolean isPhoneCalling = false;

		String LOG_TAG = "LOGGING 123";

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {

			if (TelephonyManager.CALL_STATE_RINGING == state) {
				// phone ringing
				Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
			}

			if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
				// active
				Log.i(LOG_TAG, "OFFHOOK");

				isPhoneCalling = true;
			}

			if (TelephonyManager.CALL_STATE_IDLE == state) {
				// run when class initial and phone call ended, need detect flag
				// from CALL_STATE_OFFHOOK
				Log.i(LOG_TAG, "IDLE");

				if (isPhoneCalling) {

					Log.i(LOG_TAG, "restart app");

					// restart app
					Intent i = getBaseContext().getPackageManager()
							.getLaunchIntentForPackage(
									getBaseContext().getPackageName());
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);

					isPhoneCalling = false;
				}

			}
		}
	}
}