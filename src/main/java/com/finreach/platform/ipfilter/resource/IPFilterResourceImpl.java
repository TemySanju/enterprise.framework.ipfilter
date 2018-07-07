package com.finreach.platform.ipfilter.resource;

import java.util.Map;

import javax.ws.rs.*;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.finreach.platform.ipfilter.beans.FilterConfig;
import com.finreach.platform.ipfilter.service.IPFilterService;

@Component
@Path("/v1/ipfilters")

public class IPFilterResourceImpl implements IPFilterResource {
	
	@Autowired
	public IPFilterService ipFilterService;
		
	
	@GET
	@Path("/validate")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response checkFilterConfig( @QueryParam("ipaddress") final String ipAddress, 
			@QueryParam("protocol") final String filterProtocol) throws Exception {
		Response response;
		try{
			boolean isValid = ipFilterService.checkFilterConfig(ipAddress, filterProtocol);
			return Response.ok(isValid).build();
		}catch (BadRequestException e)
        {
            response = Response.status(Status.BAD_REQUEST).build();
        }
        catch (IllegalArgumentException e)
        {
            response = Response.status(Status.BAD_REQUEST).build();
        }
        catch (Exception ex)
        {
            response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
            ex.printStackTrace();
        } 
		return response;

	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/")
	public Response createIPFilerConfig(FilterConfig filterConfig) throws Exception {
		Response response;
		try{
			ipFilterService.createFilterConfig(filterConfig);
			response = Response.status(Status.OK).build();
		}
		catch (BadRequestException e)
        {
            response = Response.status(Status.BAD_REQUEST).build();
        }
        catch (IllegalArgumentException e)
        {
            response = Response.status(Status.BAD_REQUEST).build();
        }
        catch (Exception ex)
        {
            response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
            ex.printStackTrace();
        } 
		return response;
	}

	@DELETE
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteIPFilterConfig(FilterConfig filterConfig) throws Exception {
		Response response;
		
		try{
			ipFilterService.deleteIPFilterConfig(filterConfig);
			return Response.noContent().build();
		}catch (BadRequestException e)
        {
            response = Response.status(Status.BAD_REQUEST).build();
        }
        catch (IllegalArgumentException e)
        {
            response = Response.status(Status.BAD_REQUEST).build();
        }
        catch (Exception ex)
        {
            response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
            ex.printStackTrace();
        } 
		return response;
		
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getIPFilterConfigs() throws Exception {
		Response response;
		
		try{
			Map<String,String> filterConfigsList= ipFilterService.getIPFilterConfigs();
			return Response.ok(filterConfigsList).build();
			
		}catch (BadRequestException e)
        {
            response = Response.status(Status.BAD_REQUEST).build();
        }
        catch (IllegalArgumentException e)
        {
            response = Response.status(Status.BAD_REQUEST).build();
        }
        catch (Exception ex)
        {
            response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
            ex.printStackTrace();
        } 
		return response;
	}

	@GET
	@Path("/filterprotocol/{filterProtocol}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getIPFilterConfig(@PathParam("filterProtocol") final String filterProtocol) throws Exception {
		
		Response response;
		try{
			FilterConfig filterConfig= ipFilterService.getIPFilterConfig(filterProtocol);
			return Response.ok(filterConfig).build();
			
		}catch (BadRequestException e)
        {
            response = Response.status(Status.BAD_REQUEST).build();
        }
        catch (IllegalArgumentException e)
        {
            response = Response.status(Status.BAD_REQUEST).build();
        }
        catch (Exception ex)
        {
            response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
            ex.printStackTrace();
        } 
		return response;
	}

}
