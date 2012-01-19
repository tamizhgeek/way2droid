package com.foamsnet.way2droid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SpeedDial extends Activity implements OnClickListener {
	
	private int dialnumber;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speeddial);
        dialnumber = 0;
        ((Button)findViewById(R.id.dial1)).setOnClickListener(this);
        ((Button)findViewById(R.id.dial2)).setOnClickListener(this);
        ((Button)findViewById(R.id.dial3)).setOnClickListener(this);
        ((Button)findViewById(R.id.dial4)).setOnClickListener(this);
        ((Button)findViewById(R.id.dial5)).setOnClickListener(this);
        ((Button)findViewById(R.id.dial6)).setOnClickListener(this);
        ((Button)findViewById(R.id.dial7)).setOnClickListener(this);
        ((Button)findViewById(R.id.dial8)).setOnClickListener(this);
        ((Button)findViewById(R.id.dial9)).setOnClickListener(this);
        ((Button)findViewById(R.id.SaveDial)).setOnClickListener(this);
    }
	
	private void showInput()
	{
		SharedPreferences settings = getApplicationContext().getSharedPreferences("Way2Droid", Context.MODE_PRIVATE);
		String str = settings.getString("d" + dialnumber, "");
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Speed Dial");
		alert.setMessage("Set Number for Dial " + dialnumber);

		// Set an EditText view to get user input 
		final EditText input = new EditText(this);
		input.setText(str);
		input.setInputType(InputType.TYPE_CLASS_NUMBER);
		alert.setView(input);

		alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
			if(!input.getText().toString().trim().equalsIgnoreCase(""))
			{
				SharedPreferences settings = getApplicationContext().getSharedPreferences("Way2Droid", Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = settings.edit();
				editor.putString("d" + dialnumber, input.getText().toString());
				editor.commit();
				Toast.makeText(getApplicationContext(), "Shortcut saved for dial " + dialnumber, Toast.LENGTH_SHORT).show();
			}
		  }
		});

		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int whichButton) {
		    return;
		  }
		});
		alert.show();
	}
    
	//@Override
	public void onClick(View v) {
		switch(v.getId())
		{
			case R.id.dial1:
				dialnumber = 1;
				showInput();
				break;
			case R.id.dial2:
				dialnumber = 2;
				showInput();
				break;
			case R.id.dial3:
				dialnumber = 3;
				showInput();
				break;
			case R.id.dial4:
				dialnumber = 4;
				showInput();
				break;
			case R.id.dial5:
				dialnumber = 5;
				showInput();
				break;
			case R.id.dial6:
				dialnumber = 6;
				showInput();
				break;
			case R.id.dial7:
				dialnumber = 7;
				showInput();
				break;
			case R.id.dial8:
				dialnumber = 8;
				showInput();
				break;
			case R.id.dial9:
				dialnumber = 9;
				showInput();
				break;
			case R.id.SaveDial:
				this.finish();
				break;
		}
	}
}
