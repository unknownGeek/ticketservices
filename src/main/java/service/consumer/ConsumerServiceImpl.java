package service.consumer;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.MessageFormat;

import service.jdbc.JDBCConnectionManager;
import service.model.HelloMessage;
import service.model.People;
import service.model.Ticket;
import service.model.TicketContent;
import service.model.TicketInfo;
import service.model.User;
import service.wrapper.ServiceResponse;
import service.wrapper.ServiceResponseBuilder;

public class ConsumerServiceImpl implements ConsumerService {

	@Override
	public ServiceResponse<HelloMessage> addUser(User user) {
		String message;
		try {
			   Connection con = JDBCConnectionManager.getDBConnection();
			   String sqlQuery =MessageFormat.format( "Insert into user (email_address,password) VALUES(''{0}'',''{1}'')",user.getEmailAddr(),user.getPassword());
		       PreparedStatement preparedStatement = con.prepareStatement(sqlQuery);
		       preparedStatement.setQueryTimeout(60);
		       int rows = preparedStatement.executeUpdate();
		       if(rows == 1){
		    	   message="successfully registered";
		       }else {
		    	   message="User Already exists";
		       }
		       HelloMessage helloMessage = new HelloMessage(message, true);
		       return ServiceResponseBuilder.ok(helloMessage,  null);
			}
			catch(Exception ex){
			  return null;
			}
		
	}

	@Override
	public ServiceResponse<HelloMessage> addTicket(Ticket ticket) {
		String message;
		try {
			  
			 Connection con = JDBCConnectionManager.getDBConnection();
		       PreparedStatement preparedStatement = getPreparedStatement(con, ticket);
		       int numRows = preparedStatement.executeUpdate();
		       if(numRows == 1) {
		           message = "Ticket Successfully Created!";
		       } else  {
		           message = "Ticket cannot be creaeted!";
		       }
		       HelloMessage helloMessage = new HelloMessage(message, true);
		       return ServiceResponseBuilder.ok(helloMessage,  null);
			}
		catch (Exception e) {
		       String msg = "Error while preparing Response or closing the JDBC connection";
		       System.err.println(msg);
		       return null;
		   } 
	}


private PreparedStatement getPreparedStatement(Connection con, Ticket ticket) throws SQLException {
   String sqlQuery = getSqlQuery(ticket);
   PreparedStatement preparedStatement = con.prepareStatement(sqlQuery);
   preparedStatement.setQueryTimeout(60);
   return preparedStatement;
}

private String getSqlQuery(Ticket ticket) {

   TicketInfo ticketInfo = ticket.getInfo();
   String ticketStatus = null, ticketType = null, priority = null;
   if(ticketInfo != null) {
       ticketStatus = ticketInfo.getTicketStatus();
       ticketType = ticketInfo.getTicketType();
       priority = ticketInfo.getPriority();
   }

   String acceptanceCriteria = null, description = null, ticketTitle = null;

   TicketContent ticketContent = ticket.getTicketContent();
   if(ticketContent != null) {
       acceptanceCriteria = ticketContent.getAcceptanceCriteria();
       description = ticketContent.getDescription();
       ticketTitle = ticketContent.getTicketTitle();
   }
   People people = ticket.getPeople();

   User assignee= null, reporter=null;

   if(people != null) {
       assignee = people.getAssignee();
       reporter = people.getReporter();
   }

   String assigneeEmailAddr = null;
   String reporterEmailAddr = null;

   if(assignee != null) {
       assigneeEmailAddr = assignee.getEmailAddr();
   }
   if(reporter != null) {
       reporterEmailAddr = reporter.getEmailAddr();
   }

   String strInsertQuery = MessageFormat.format("insert into ticket_header (ticket_status, ticket_type, priority, ticket_title, description, acceptance_criteria, assignee_email_Addr, reporter_email_Addr) VALUES (''{0}'',''{1}'',''{2}'',''{3}'',''{4}'',''{5}'',''{6}'',''{7}'')", ticketStatus, ticketType, priority, ticketTitle, description, acceptanceCriteria, assigneeEmailAddr, reporterEmailAddr);
   return strInsertQuery;
}



		
	
	

}