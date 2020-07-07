package service.consumer;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import service.model.HelloMessage;
import service.model.Ticket;
import service.model.User;
import service.wrapper.ServiceResponse;

@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ConsumerService {
	
	@Path("/addUser")
	@POST
	ServiceResponse<HelloMessage> addUser(User user);
	
	@Path("/addTicket")
	@POST
	ServiceResponse<HelloMessage> addTicket(Ticket ticket);

}