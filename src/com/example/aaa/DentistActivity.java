package com.example.aaa;

import jane.data.DocRecord;
import jane.data.UIUtils;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DentistActivity extends Activity {
	static public int nMode;
	SharedPreferences prefs;
	String strID;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		prefs = getPreferences(MODE_PRIVATE);   
		strID = prefs.getString("ID", "");
		
		if(nMode > 0 && strID.equals(""))
		{
			Builder dlg = new AlertDialog.Builder(DentistActivity.this);
			dlg.setMessage("There is no data about the specialist doctor");
			
			dlg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Intent intent = new Intent(DentistActivity.this, ControlActivity.class);
	        		startActivity(intent);
					finish();
				}
			});
			
			dlg.show();
		}
		
		ViewGroup vg;
		
		if(nMode < 2)
		{
			setContentView(R.layout.new_dentists);
			vg = (ViewGroup)findViewById(R.id.dntst_new_ViewGroup);
			
			EditText edt_clientphone = (EditText) findViewById(R.id.txtPhone);
			EditText edt_clientMobile = (EditText) findViewById(R.id.txtMobile);
			InputFilter filter = new InputFilter() {

	            @Override
	            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
	                if (source.length() > 0) {

	                    if (!Character.isDigit(source.charAt(0))) {
	                    	//return "";
	                    }
	                    else {
	                        if (dstart == 3) {
	                            return source + ") ";
	                        } else if (dstart == 0) {
	                            return "(" + source;
	                        } else if ((dstart == 5) || (dstart == 9))
	                            return "-" + source;
	                        else if (dstart >= 14)
	                            return "";
	                    }

	                } else {

	                }

	                return null;

	            }
	        };
	        InputFilter filter2 = new InputFilter() {

	            @Override
	            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
	                if (source.length() > 0) {

	                    if (!Character.isDigit(source.charAt(0))) {
	                    	//return "";
	                    }
	                    else {
	                        if (dstart == 3) {
	                            return source + ") ";
	                        } else if (dstart == 0) {
	                            return "(" + source;
	                        } else if ((dstart == 5) || (dstart == 9))
	                            return "-" + source;
	                        else if (dstart >= 14)
	                            return "";
	                    }

	                } else {

	                }

	                return null;

	            }
	        };

	        edt_clientphone.setFilters(new InputFilter[] { filter });
	        edt_clientMobile.setFilters(new InputFilter[] { filter2 });
		}
		else
		{
			setContentView(R.layout.dentists);
			vg = (ViewGroup)findViewById(R.id.dntst_ViewGroup);
		}
		
		UIUtils.setFontForAll(vg);
		
		init();
		setClickListener(); 
		
	}
	
	private String Decode_string(String str)
	{
		if(str.equals("NULL")) return "";
		return str;
	}
	
	private String Code_string(String str)
	{
		if(str.equals("")) return "NULL";
		return str;			
	}
	
	private void init()
	{
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		
		String strToday = month + "-" + day + "-" + year;
		((TextView)findViewById(R.id.txtDate_title)).setText(strToday);
		
		prefs = getPreferences(MODE_PRIVATE);
		
		String strDoctor = Decode_string( prefs.getString("Doctor", ""));
		String strSpecialist = Decode_string( prefs.getString("Specialist", ""));
		String strAddress = Decode_string(prefs.getString("Address", "")) ;
		String strCity = Decode_string(prefs.getString("City", ""));
		String strState = Decode_string(prefs.getString("State", "")) ;
		String strZip = Decode_string(prefs.getString("Zip", "")) ;
		String strPhone = Decode_string(prefs.getString("Phone", "")) ;
		String strMobile = Decode_string(prefs.getString("Mobile", "")) ;
		String strEmail = Decode_string(prefs.getString("Email", "")) ;
		
		if(nMode == 2)
		{
			((TextView)findViewById(R.id.txtDoctor)).setText(strDoctor);
			((TextView)findViewById(R.id.txtSpecialist)).setText(strSpecialist);
			((TextView)findViewById(R.id.txtAddress)).setText(strAddress);
			((TextView)findViewById(R.id.txtCity)).setText(strCity);
			((TextView)findViewById(R.id.txtState)).setText(strState);
			((TextView)findViewById(R.id.txtZip)).setText(strZip);
			((TextView)findViewById(R.id.txtPhone)).setText(strPhone);
			((TextView)findViewById(R.id.txtMobile)).setText(strMobile);
			((TextView)findViewById(R.id.txtEmail)).setText(strEmail);
		}
		else
		{
			((EditText)findViewById(R.id.txtDoctor)).setText(strDoctor);
			((EditText)findViewById(R.id.txtSpecialist)).setText(strSpecialist);
			((EditText)findViewById(R.id.txtAddress)).setText(strAddress);
			((EditText)findViewById(R.id.txtCity)).setText(strCity);
			((EditText)findViewById(R.id.txtState)).setText(strState);
			((EditText)findViewById(R.id.txtZip)).setText(strZip);
			((EditText)findViewById(R.id.txtPhone)).setText(strPhone);
			((EditText)findViewById(R.id.txtMobile)).setText(strMobile);
			((EditText)findViewById(R.id.txtEmail)).setText(strEmail);
		}
		
	}
	
	private void setClickListener()
	{
		((Button) findViewById(R.id.btnBack)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				Intent i = new Intent(DentistActivity.this, ControlActivity.class);
				startActivity(i);
				finish();
			}
		 });
		
		((Button)findViewById(R.id.btnHome)).setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(DentistActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		if(nMode < 2)
		{
			((Button) findViewById(R.id.btnSave)).setOnClickListener(new OnClickListener(){
				public void onClick(View v) {
					String strDoctor = ((EditText)findViewById(R.id.txtDoctor)).getText().toString();
					String strSpecialist = ((EditText)findViewById(R.id.txtSpecialist)).getText().toString();
					String strAddress = ((EditText)findViewById(R.id.txtAddress)).getText().toString();
					String strCity = ((EditText)findViewById(R.id.txtCity)).getText().toString();
					String strState = ((EditText)findViewById(R.id.txtState)).getText().toString();
					String strZip = ((EditText)findViewById(R.id.txtZip)).getText().toString();
					String strPhone = ((EditText)findViewById(R.id.txtPhone)).getText().toString();
					String strMobile = ((EditText)findViewById(R.id.txtMobile)).getText().toString();
					String strEmail = ((EditText)findViewById(R.id.txtEmail)).getText().toString();
					
					if(strDoctor == null || strDoctor.equals(""))
					{
						AlertView("Input the doctor name");
						return;
					}
					
//					if(strSpecialist == null || strSpecialist.equals(""))
//					{
//						AlertView("Input the specialist");
//						return;
//					}
					
//					if(strAddress == null || strAddress.equals(""))
//					{
//						AlertView("Input the address");
//						return;
//					}
//					
//					if(strCity == null || strCity.equals(""))
//					{
//						AlertView("Input the city");
//						return;
//					}
//					
//					if(strState == null || strState.equals(""))
//					{
//						AlertView("Input the state");
//						return;
//					}
//					
//					if(strZip == null || strZip.equals(""))
//					{
//						AlertView("Input the zip code");
//						return;
//					}
//					
//					if(strPhone == null || strPhone.equals(""))
//					{
//						AlertView("Input the phone");
//						return;
//					}
//					
//					if(strMobile == null || strMobile.equals(""))
//					{
//						AlertView("Input the mobile number");
//						return;
//					}
//					
//					if(strEmail == null || strEmail.equals(""))
//					{
//						AlertView("Input the email");
//						return;
//					}
					
					prefs = getPreferences(MODE_PRIVATE);
					SharedPreferences.Editor editor = prefs.edit();
					
					editor.putString("Doctor", Code_string(strDoctor));
					editor.putString("Specialist", Code_string(strSpecialist));
					editor.putString("Address", Code_string(strAddress));
					editor.putString("City", Code_string(strCity));
					editor.putString("State", Code_string(strState));
					editor.putString("Zip", Code_string(strZip));
					editor.putString("Phone", Code_string(strPhone));
					editor.putString("Mobile", Code_string(strMobile));
					editor.putString("Email", Code_string(strEmail));
					
					DocRecord record = new DocRecord();
					
					record.doctor = strDoctor;
					record.address = strAddress;
					record.city = strCity;
					record.state = strState;
					record.zip = strZip;
					record.phone = strPhone;
					record.mobile = strMobile;
					record.email = strEmail;
					
					if(strID.equals(""))
					{						
						MainActivity.docModel.maxIdx ++;
						
						int curIdx = MainActivity.docModel.maxIdx;
						
						record.idx = String.valueOf(curIdx);
						
						MainActivity.docModel.list_appts.add(record);
						MainActivity.docModel.updateDB();
						
						editor.putString("ID", Code_string(String.valueOf(curIdx)));
					}
					else
					{
						record.idx = strID;
						
						MainActivity.docModel.updateArray(record);
						MainActivity.docModel.updateDB();
						
						editor.putString("ID", Code_string(strID));
					}
					
					editor.commit();
					
					Intent intent = new Intent(DentistActivity.this, SuccessWarningActivity.class);
	        		startActivity(intent);
	        		
					finish();
				}
			 });
		}
		else
		{
			((Button)findViewById(R.id.btnPhone)).setOnClickListener(new OnClickListener() {
				
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					String strPhone = ((TextView)findViewById(R.id.txtPhone)).getText().toString();
					
					if(strPhone.equals("")) return;
					
					Intent i = new Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:" + strPhone));
					startActivity(i);
				}
			});
			
			((Button)findViewById(R.id.btnMobile)).setOnClickListener(new OnClickListener() {
				
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					String strMobile = ((TextView)findViewById(R.id.txtMobile)).getText().toString();
					
					if(strMobile.equals("")) return;
					
					Intent i = new Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:" + strMobile));
					startActivity(i);
				}
			});
		}
		
	}
	
	private void AlertView(String strMessage)
	{
		Builder dlg = new AlertDialog.Builder(DentistActivity.this);
		dlg.setMessage(strMessage);
		
		dlg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		
		dlg.show();
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent i = new Intent(DentistActivity.this, ControlActivity.class);
		startActivity(i);
		finish();
		super.onBackPressed();
	}
}
