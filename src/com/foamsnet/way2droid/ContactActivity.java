package com.foamsnet.way2droid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

class Contact {

    protected String name;
    protected String number;
    protected String id;

    protected Contact(String name, String number) {
 //       if(Log.isLoggable(TAG, Log.DEBUG))
   //         Log.d(TAG, "Contact()["+id+"] '"+name+"': "+number);
        this.name = name;
        this.number = number;
    }
    /**
     * @return the string for the Listview */
    @Override
    public String toString() {
         return name + ":" + number;
    }
    
    public int compareTo(Contact c) {
    	
    	String c1 = c.name.toUpperCase();
    	String c2 = this.name.toUpperCase();
    	return c2.compareTo(c1);
    	
    }
}
class ContactComparator implements Comparator<Contact> {
    public int compare(Contact c1, Contact c2) {
        return c1.name.compareTo(c2.name);
    }
}


public class ContactActivity extends ListActivity {
	private EditText filterText;
	private ArrayAdapter<Contact> adapter;
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		// Use your own layout
		setContentView(R.layout.contact);
		
		List<Contact> simcontactlist = retrieveSIMContacts();
		Collections.sort(simcontactlist, new ContactComparator());
	    filterText = (EditText)findViewById(R.id.search_box);
	    filterText.addTextChangedListener(filterTextWatcher);

		adapter = new ArrayAdapter<Contact>(this,
				R.layout.single_contact, simcontactlist);
		setListAdapter(adapter);
		

	}
	

private TextWatcher filterTextWatcher = new TextWatcher() {

    public void afterTextChanged(Editable s) {
    }

    public void beforeTextChanged(CharSequence s, int start, int count,
            int after) {
    }

    public void onTextChanged(CharSequence s, int start, int before,
            int count) {
        adapter.getFilter().filter(s);
    }

};

@Override
protected void onDestroy() {
    super.onDestroy();
    filterText.removeTextChangedListener(filterTextWatcher);
}
	
	

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Contact c = (Contact) getListAdapter().getItem(position);
		String num = c.number;
		String name = c.name;
		Intent i = new Intent();
		i.putExtra("number", num);
		i.putExtra("name", name);
		setResult(Activity.RESULT_OK, i);
		finish();
		//Toast.makeText(this, num + " selected", Toast.LENGTH_LONG).show();
	}
	
	public List<Contact> retrieveSIMContacts() {
	      //  if(Log.isLoggable(TAG, Log.DEBUG))
	        //    Log.d(TAG, "retrieveSIMContacts()");

			Uri simUri = Uri.parse("content://icc/adn/");

	        // get these columns
	        final String[] simProjection = new String[] { //
	        android.provider.Contacts.PeopleColumns.NAME, //
	                android.provider.Contacts.PhonesColumns.NUMBER };
	        ContentResolver resolver = getContentResolver();
	        Cursor results = resolver.query( //
	                simUri, // URI of contacts on SIM card
	                simProjection, // get above defined columns
	                null, null, android.provider.Contacts.PeopleColumns.NAME); // order by name

	        final ArrayList<Contact> simContacts;
	        if (results != null && results.getCount() > 0) {
	            simContacts  = new ArrayList<Contact>(results.getCount());
	            // create array of SIM contacts and fill it
	            while (results.moveToNext()) {
	                final Contact simContact = new Contact(//
	                        results.getString(0), // name
	                        results.getString(1));// number

	                simContacts.add(simContact);
	            }
	        } else {
	            simContacts = new ArrayList<Contact>(0);
	        }
	        return simContacts;
	    }
}


