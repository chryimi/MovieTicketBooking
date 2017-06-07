package com.movbooking.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class FileIOUtil {
	public static String read(String filename) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(filename));
		String s;
		StringBuilder sb = new StringBuilder();
		while((s = in.readLine()) != null) {
			sb.append(s);
		}
		in.close();
		return sb.toString();
	}
}
