package com.finreach.platform.ipfilter.service;

import java.util.Map;

import com.finreach.platform.ipfilter.beans.FilterConfig;

public interface IPFilterService {
	
	FilterConfig getIPFilterConfig(String filterProtocol)
            throws Exception;
	
	boolean checkFilterConfig(String ipAddress, String filterProtocol)
            throws Exception;

    FilterConfig createFilterConfig(FilterConfig filterConfig) throws Exception;

    void deleteIPFilterConfig(FilterConfig filterConfig) throws Exception;

    Map<String,String> getIPFilterConfigs() throws Exception;
   
}
