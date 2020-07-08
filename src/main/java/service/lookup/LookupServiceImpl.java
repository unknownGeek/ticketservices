package service.lookup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import service.jdbc.JDBCConnectionManager;
import service.model.HelloMessage;
import service.model.Users;
import service.wrapper.ServiceResponse;
import service.wrapper.ServiceResponseBuilder;


public class LookupServiceImpl implements LookupService{
    @Override
    public ServiceResponse<HelloMessage> getHelloMessage(String name) {
        HelloMessage helloMessage = new HelloMessage(name);
        return ServiceResponseBuilder.ok(helloMessage,  null);
    }

	@Override
	public ServiceResponse<HelloMessage> getAuthUserMessage(String emailAddr, String passwd) {
		
		String message;
		try {
		   Connection con = JDBCConnectionManager.getDBConnection();
		   String sqlQuery =MessageFormat.format( "Select * from user where email_address=''{0}'' AND password=''{1}''", emailAddr,passwd);
	       PreparedStatement preparedStatement = con.prepareStatement(sqlQuery);
	       preparedStatement.setQueryTimeout(60);
	       ResultSet resultSet = preparedStatement.executeQuery();
	       if(resultSet.next()){
	    	   message="success";
	       }else {
	    	   message="email address or password is invalid";
	       }
	       HelloMessage helloMessage = new HelloMessage(message, true);
	       return ServiceResponseBuilder.ok(helloMessage,  null);
		}
		catch(Exception ex){
			return null;
		}
		
	}
	@Override
    public ServiceResponse<Users> getUsers() {
		List<String> emailAddrList=new ArrayList<>();
		try {
		   Connection con = JDBCConnectionManager.getDBConnection();
		   String sqlQuery ="Select email_Address from user";
	       PreparedStatement preparedStatement = con.prepareStatement(sqlQuery);
	       preparedStatement.setQueryTimeout(60);
	       ResultSet resultSet = preparedStatement.executeQuery();
	       while(resultSet.next()) {
	        String emailAddr = resultSet.getString(1);
	        emailAddrList.add(emailAddr);
	       }
            Users users=new Users(emailAddrList);
            return ServiceResponseBuilder.ok(users,  null);
      }catch(Exception ex){
		return null;
	
	  }

  }
}



