package com.fishuman.jqdemo;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

public class DemoInterface {
	Context mContext = null;

	DemoInterface(Context context) {
		mContext = context;
	}

	public String getArray() {
		DemoData[] data = new DemoData[3];

		for (int i = 0; i < 3; i++) {
			data[i] = new DemoData();
			data[i].name = "name" + i;
			data[i].desc = "desc" + i;
		}

		// toJSON
		StringBuilder sb = new StringBuilder("[");
		for (DemoData d : data) {
			sb.append(d.toJSON());
			sb.append(",");
		}
		sb.replace(sb.length() - 1, sb.length(), "]");
		return sb.toString();
	}

	public String getMessage() {
		return "Hi!!";
	}

	private Cursor _getContacts() {
		// Run query
		Uri uri = ContactsContract.Contacts.CONTENT_URI;
		String[] projection = new String[] { ContactsContract.Contacts._ID,
				ContactsContract.Contacts.DISPLAY_NAME,
				ContactsContract.Contacts.HAS_PHONE_NUMBER };
		String selection = ContactsContract.Contacts.IN_VISIBLE_GROUP
				+ " = '1'";
		String[] selectionArgs = null;
		String sortOrder = ContactsContract.Contacts.DISPLAY_NAME
				+ " COLLATE LOCALIZED ASC";

		return ((Activity) mContext).managedQuery(uri, projection, selection,
				selectionArgs, sortOrder);
	}

	public String getContacts() {
		StringBuilder sb = new StringBuilder("[");
		Cursor c = _getContacts();
		try {
			while (c.moveToNext()) {

				String contactId = c.getString(0);
				String phoneNumber = "";
				String contactName = c.getString(1);
				if (Integer.parseInt(c.getString(2)) == 1) {
					Cursor phones = mContext.getContentResolver().query(
							ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
							null,
							ContactsContract.CommonDataKinds.Phone.CONTACT_ID
									+ " = " + contactId, null, null);
					while (phones.moveToNext()) {
						phoneNumber = phones
								.getString(phones
										.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
					}
					if (phones != null)
						phones.close();
				}
				sb.append("{\"name\":\"").append(contactName).append(
						"\", \"number\":\"").append(phoneNumber).append("\"}");
				sb.append(",");
			}
		} finally {
			if (c != null)
				c.close();
		}
		sb.replace(sb.length() - 1, sb.length(), "]");
		return sb.toString();
	}

	public Cursor _getContact(String id) {
		Uri uri = Uri.withAppendedPath(
				ContactsContract.Contacts.CONTENT_LOOKUP_URI, id);
		String[] projection = new String[] { ContactsContract.Contacts._ID,
				ContactsContract.Contacts.DISPLAY_NAME,
				ContactsContract.Contacts.HAS_PHONE_NUMBER };
		String selection = null;
		String[] selectionArgs = null;
		String sortOrder = null;

		Cursor c = ((Activity) mContext).managedQuery(uri, projection,
				selection, selectionArgs, sortOrder);
		return c;
	}

	public String getContact(String id) {
		StringBuilder sb = new StringBuilder("[");
		Cursor c = _getContacts();
		try {
			if (c.moveToNext()) {
				String contactId = c.getString(0);
				String phoneNumber = "";
				String contactName = c.getString(1);
				if (Integer.parseInt(c.getString(2)) == 1) {
					Cursor phones = mContext.getContentResolver().query(
							ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
							null,
							ContactsContract.CommonDataKinds.Phone.CONTACT_ID
									+ " = " + contactId, null, null);
					while (phones.moveToNext()) {
						phoneNumber = phones
								.getString(phones
										.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
					}
					if (phones != null)
						phones.close();
				}
				sb.append("{\"name\":\"").append(contactName).append(
						"\", \"number\":\"").append(phoneNumber).append("\"}");
				sb.append(",");
			}
		} finally {
			if (c != null)
				c.close();

		}
		return "";
	}
}
