package com.finreach.platform.ipfilter.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.finreach.platform.ipfilter.beans.FilterConfig;
import com.finreach.platform.ipfilter.util.IPFilterFileUtil;
import com.intuit.platform.common.exception.CommonException;
import com.intuit.platform.webs.aqjms.beans.AQJMSRequest;
import com.intuit.platform.webs.aqjms.beans.AQJMSResponse;
import com.intuit.platform.webs.aqjms.config.ConnectorCommand;
import com.intuit.platform.webs.aqjms.config.ConnectorConfig;
import com.intuit.platform.webs.aqjms.config.DBConnectorConfig;
import com.intuit.platform.webs.aqjms.connectors.ConnectionFactory;
import com.intuit.platform.webs.aqjms.connectors.Connector;
import com.intuit.platform.webs.aqjms.connectors.ConnectorCreationException;
import com.intuit.platform.webs.aqjms.util.ConnectorUtil;
import com.intuit.platform.webs.aqjms.constants.Constants.ConnectionType;
import com.intuit.platform.webs.aqjms.exception.AQJMSException;

@Service
public class IPFilterServiceImpl implements IPFilterService {

	@Resource
	public Environment env;
	
	public IPFilterFileUtil ipFilterFileUtil = new IPFilterFileUtil();
	

	@Override
	public FilterConfig getIPFilterConfig(String filterProtocol) throws Exception {
		if(ipFilterFileUtil.getFilterMap().containsKey(filterProtocol))
			return new FilterConfig(filterProtocol,ipFilterFileUtil.getFilterMap().get(filterProtocol));
		else
			return null;
	}

	@Override
	public boolean checkFilterConfig(String ipAddress, String filterProtocol) throws Exception {
		if(ipAddress != null && filterProtocol != null){
			if(ipFilterFileUtil.getFilterMap().containsKey(filterProtocol)){
				String ipAdrresses = ipFilterFileUtil.getFilterMap().get(filterProtocol);
				if(ipAdrresses.indexOf(ipAddress) != -1)
					return true;
				else
					return false;
			}else{
				return false;
			}
				
		}
		return false;
	}

	@Override
	public FilterConfig createFilterConfig(FilterConfig filterConfig) throws Exception {
		if(filterConfig != null){
			try {
				ipFilterFileUtil.addIPFilterConfigToFile(filterConfig);
			} catch (IOException e) {
				throw e;
			}
		}
		return filterConfig;
		
	}

	@Override
	public void deleteIPFilterConfig(FilterConfig filterConfig) throws Exception {
		if(filterConfig != null){
			try {
				ipFilterFileUtil.deleteIPFilterConfigFromFile(filterConfig);
			} catch (IOException e) {
				throw e;
			}
		}
		
	}

	@Override
	public Map<String,String> getIPFilterConfigs() throws Exception {
		return ipFilterFileUtil.getFilterMap();
		
	}

}
