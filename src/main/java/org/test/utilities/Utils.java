package org.test.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Utils {

	private static String body=null;
	public static String getPayLoads(String jsonBodyPath) throws Exception {
		try {
			 body=new String(Files.readAllBytes(Path.of(System.getProperty("user.dir")+jsonBodyPath)));
			 return body;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new Exception("invalid json path");
		}
	}
}
