package service.lookup;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import service.model.HelloMessage;
import service.wrapper.ServiceResponse;

@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface LookupService {

    @GET
    @Path("/sayHello/{name}")
    ServiceResponse<HelloMessage> getHelloMessage(@PathParam("name") String name);

}