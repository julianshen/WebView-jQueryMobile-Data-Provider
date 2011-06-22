package com.fishuman.jqdemo;

import android.util.Log;

public class DemoData {
	public String name;
	public String desc;
	
	public String toJSON() {
		return "{\"name\":\""+name+"\",\"desc\":\""+desc + "\"}";
	}
}
