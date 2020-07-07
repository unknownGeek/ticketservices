package service.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCConnectionManager {


   private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

   private JDBCConnectionManager() {
       throw new IllegalStateException("Cannot instantiate util class");
   }

   public static Connection getConnection(String url, String username, String password) throws SQLException, ClassNotFoundException {
       try {
           Class.forName(DRIVER_NAME);
           return DriverManager.getConnection(url, username, password);
       } catch (SQLException sqlEx) {
           System.err.println("Failed to create the database connection");
           throw sqlEx;
       } catch (ClassNotFoundException ex) {
           System.err.println("Driver not found.");
           throw ex;
       }
   }

   public static Connection getDBConnection() throws SQLException, ClassNotFoundException {
       String url = "jdbc:mysql://localhost:3306/employee";
       String username = "root";
       String password = "";
//        System.out.println(MessageFormat.format("url = {0}", url));
       return JDBCConnectionManager.getConnection(url, username, password);
   }


   public static void main(String[] args) throws SQLException, ClassNotFoundException {
       Connection con = getDBConnection();
       System.out.println("Success");
       String sqlQuery = "Select * from user";
       PreparedStatement preparedStatement = con.prepareStatement(sqlQuery);
       preparedStatement.setQueryTimeout(60);
       ResultSet resultSet = preparedStatement.executeQuery();

       while(resultSet.next()) {
          System.out.println(resultSet.getString(1) + " " + resultSet.getString(2)+ " " + resultSet.getString(3)+ " " +resultSet.getString(4));
       }


   }

}


