package com.goparty.app.common;

import android.os.Environment;

public class Utils {
	public static boolean hasSdcard() {
		String state = Environment.getExternalStorageState();
		return state.equals(Environment.MEDIA_MOUNTED);
	}
}
