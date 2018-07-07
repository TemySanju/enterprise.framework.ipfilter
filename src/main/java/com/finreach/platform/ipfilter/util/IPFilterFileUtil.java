package com.finreach.platform.ipfilter.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.finreach.platform.ipfilter.beans.FilterConfig;

public class IPFilterFileUtil{
	
	Map<String,String> filterMap;
	
	public IPFilterFileUtil() {
		filterMap = new HashMap<>();
		
		try {
			readIPFilterFlatFile();
		} catch (IOException e) {
		e.printStackTrace();
		}
	}	
	
	public Map<String,String> getFilterMap(){
		return this.filterMap;
	}
	
	public void readIPFilterFlatFile()  throws IOException{
		try (BufferedReader br =
                new BufferedReader(new FileReader("blackListConfigs"))) {
			while(br.readLine() != null){
				String[] protocolArray = br.readLine().split("=");
				filterMap.put(protocolArray[0], protocolArray[1]);
			}
		} 
	}
	
	public void deleteIPFilterConfigFromFile(FilterConfig filterConfig) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("blacklistconfigs"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("temp"));

		String currentLine;
		String inputStr = null;
		while((currentLine = reader.readLine()) != null) {
		    String[] protocolArray = currentLine.split("=");
			if(filterConfig.getFilterProtocol().equals(protocolArray[0])){
				if(protocolArray[1].indexOf(filterConfig.getIpAddress())!= -1){
					protocolArray[1].replaceAll(filterConfig.getIpAddress()+",","");
					inputStr = protocolArray[0]+"="+protocolArray[1];
					filterMap.put(protocolArray[0], protocolArray[1]);
					writer.write(inputStr);
				}else{
					writer.write(currentLine);
				}
			}
				writer.close(); 
				reader.close(); 
				File file = new File("temp");
				File newfile = new File("blacklistconfigs");
				file.renameTo(newfile);

			}
	}
	
	public void addIPFilterConfigToFile(FilterConfig filterConfig) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("blacklistconfigs"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("temp"));

		String currentLine;
		String inputStr = null;
		while((currentLine = reader.readLine()) != null) {
		    String[] protocolArray = currentLine.split("=");
			if(filterConfig.getFilterProtocol().equals(protocolArray[0])){
				if(protocolArray[1].indexOf(filterConfig.getIpAddress())!= -1){
					protocolArray[1]+=","+filterConfig.getIpAddress();
					inputStr = protocolArray[0]+"="+protocolArray[1]+",";
					filterMap.put(protocolArray[0], protocolArray[1]);
					writer.write(inputStr);
				}else{
					writer.write(currentLine);
				}
			}
				writer.close(); 
				reader.close(); 
				File file = new File("temp");
				File newfile = new File("blacklistconfigs");
				file.renameTo(newfile);

			}
	}
}