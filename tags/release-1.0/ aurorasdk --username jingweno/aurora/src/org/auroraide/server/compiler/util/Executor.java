package org.auroraide.server.compiler.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Executor {
	public static String doCommand(String command, String dir) throws Exception {
		StringBuffer result =new StringBuffer();
		String line;
		Process p = Runtime.getRuntime().exec(command, null, new File(dir));
		BufferedReader input = new BufferedReader(new InputStreamReader(p
				.getInputStream()));
		while ((line = input.readLine()) != null) {
			result.append(line);
		}
		input.close();
		input = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		while ((line = input.readLine()) != null) {
			result.append("ERROR:"+line);
		}
		input.close();
		p.destroy();
		return result.toString();
	}
}
