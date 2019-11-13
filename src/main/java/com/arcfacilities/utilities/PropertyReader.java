package com.arcfacilities.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropertyReader {
	
		static Properties prop = new Properties();
	
		static{
			try{
				File file = new File("./src/main/resources/Testdata.properties");
				
				FileInputStream fileInput = new FileInputStream(file);
				
				prop.load(fileInput);
				
			}catch(IOException ep){
				ep.printStackTrace();
			}
		}
		
		
		public static void loadProperties(String envirnomentName){
			try{
				
				String fileLocation = "src/test/resources/";
				
				if(envirnomentName.isEmpty() && !(envirnomentName.equalsIgnoreCase("dev") || envirnomentName.equalsIgnoreCase("stg") || envirnomentName.equalsIgnoreCase("prod")))
					fileLocation = fileLocation+"testdata.properties";
				else{
					envirnomentName = envirnomentName.substring(0, 1).toUpperCase()+envirnomentName.substring(1);
					fileLocation = fileLocation+"testdata_"+envirnomentName+".properties";
				}
				
				File file = new File("src/test/resources/Testdata_"+envirnomentName+".properties");
				
				FileInputStream fileInput = new FileInputStream(file);
				
				prop.load(fileInput);
				
				System.out.println(" Loaded Properties from "+file.getName());
				
			}catch(IOException ep){
				ep.printStackTrace();
			}
		}

		/**
	 	 * get property
	 	 */
		public static String getProperty(String key){
			String val = prop.getProperty(key);
			Objects.requireNonNull(val);
			return val;
		}

}
