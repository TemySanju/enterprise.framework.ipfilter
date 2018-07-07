package com.finreach.platform.ipfilter.beans;

public class FilterConfig{
	
	private String filterProtocol;
	private String ipAddress;
	
	public FilterConfig(String filterProtocol,String ipAddress){
		this.filterProtocol = filterProtocol;
		this.ipAddress = ipAddress;
	}
	
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getFilterProtocol() {
		return filterProtocol;
	}
	public void setFilterProtocol(String filterProtocol) {
		this.filterProtocol = filterProtocol;
	}
	
	
	
	
}
 