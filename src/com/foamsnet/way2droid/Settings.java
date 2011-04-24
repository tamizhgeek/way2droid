package com.foamsnet.way2droid;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Settings extends Activity implements OnClickListener
{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        ((Button)findViewById(R.id.btnSave)).setOnClickListener(this);
        SharedPreferences settings = getApplicationContext().getSharedPreferences("Way2Droid", Context.MODE_PRIVATE);
        ((EditText)findViewById(R.id.txtusername)).setText(settings.getString("username", ""));
        ((EditText)findViewById(R.id.txtpassword)).setText(settings.getString("password", ""));
        ((CheckBox)findViewById(R.id.chklogsent)).setChecked(settings.getBoolean("logsent", true));
        ((CheckBox)findViewById(R.id.chkbg)).setChecked(settings.getBoolean("bgsend", false));
        
    }
	
	private void show(String s)
    {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.equals(findViewById(R.id.btnSave)))
		{
			Context context = getApplicationContext();
			SharedPreferences settings = context.getSharedPreferences("Way2Droid", Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = settings.edit();
			editor.putString("username", ((EditText)findViewById(R.id.txtusername)).getText().toString());
			editor.putString("password", ((EditText)findViewById(R.id.txtpassword)).getText().toString());
			editor.putBoolean("logsent", ((CheckBox)findViewById(R.id.chklogsent)).isChecked());
			editor.putBoolean("bgsend", ((CheckBox)findViewById(R.id.chkbg)).isChecked());
			editor.commit();
			show("Settings Saved!");
			this.finish();
		}
	}
}
