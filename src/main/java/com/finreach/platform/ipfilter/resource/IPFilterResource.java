package com.finreach.platform.ipfilter.resource;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.finreach.platform.ipfilter.beans.FilterConfig;

public interface IPFilterResource {
	
	public Response checkFilterConfig(@QueryParam("ipaddress") final String ipAddress, @QueryParam("protocol") final String filterProtocol ) 
			throws Exception;
	
	public Response createIPFilerConfig(FilterConfig filterConfig) throws Exception;

    public Response deleteIPFilterConfig(FilterConfig filterConfig) throws Exception;
    
    public Response getIPFilterConfigs() throws Exception;
    
    public Response getIPFilterConfig(@PathParam("filterprotocol") final String filterProtocol) throws Exception;

}
