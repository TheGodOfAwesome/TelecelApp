package com.example.telecelapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;

public class Call_Me_Back extends Activity{
	
	final Context context = this;
	String encodedHash = Uri.encode("#");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_me_back);
        
    	// add PhoneStateListener
		PhoneCallListener phoneListener = new PhoneCallListener();
		TelephonyManager telephonyManager = (TelephonyManager) this
				.getSystemService(Context.TELEPHONY_SERVICE);
		telephonyManager.listen(phoneListener,
				PhoneStateListener.LISTEN_CALL_STATE);

        Button CallMeBack = (Button) findViewById(R.id.buttonCallMeBack);
        final EditText input = (EditText) findViewById(R.id.etCallMeBack);
        
        CallMeBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String CallMeBack = input.getText().toString();
				
                if( CallMeBack.contentEquals("")){
					
                	Toast.makeText(getApplicationContext(), "Please Enter The Call Me Back Number First!", Toast.LENGTH_LONG).show();
					
				}
                else {
					Intent callIntent = new Intent(Intent.ACTION_CALL);
					callIntent.setData(Uri.parse("tel:*140*" + CallMeBack + encodedHash));
					startActivity(callIntent);
					Toast.makeText(getApplicationContext(), "Request Sent!", Toast.LENGTH_LONG).show();
				}
                
			}
		});
            
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
