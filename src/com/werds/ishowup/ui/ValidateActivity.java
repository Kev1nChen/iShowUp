package com.werds.ishowup.ui;

import info.androidhive.slidingmenu.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.werds.ishowup.validation.AttendanceValidator;

public class ValidateActivity extends Activity {

	private SharedPreferences sp;
	private ImageView icon;
	private String netID;
	private Button return_btn;
	private TextView hello, youHave, courseInfo;
	private String firstName = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_validate);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		icon = (ImageView) findViewById(R.id.checkin_status);
		return_btn = (Button) findViewById(R.id.return_btn);
		hello = (TextView) findViewById(R.id.hello);
		youHave = (TextView) findViewById(R.id.you_have);
		courseInfo = (TextView) findViewById(R.id.course_info);
		sp = this.getSharedPreferences("userInfo", MODE_PRIVATE);
		netID = sp.getString("NetID", null);

		Bundle bundle = getIntent().getExtras();
		String qrCodeData = new String(bundle.getString("QRCodeData"));

		AttendanceValidator validator = new AttendanceValidator(netID,
				qrCodeData);
		if (validator.validateCheckIn().equals("SUCCESS")) {
			// check in successfully
			icon.setBackgroundResource(R.drawable.check);
			// hello.setText("Hello "+firstName);
			return_btn.setOnClickListener(new Button.OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent(ValidateActivity.this,
							MainActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
				}
			});
		} else {
			// check in failed
			icon.setBackgroundResource(R.drawable.cross);
			hello.setText("Sorry " + firstName);
			youHave.setText("Your checked-in has failed to");
			return_btn.setOnClickListener(new Button.OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent(ValidateActivity.this,
							ScanActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
				}
			});
			return_btn.setText("Try again");
		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// Respond to the action bar's Up/Home button
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// Disable back button. (override to avoid unknown terminating)
	@Override
	public void onBackPressed() {
	}

}
