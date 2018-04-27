/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import BeanClasses.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class UserDB 
{   
    public static int insert(User user) {
    
     Connection connection = null;       
     PreparedStatement ps = null;        
     String query = "INSERT INTO user (firstname, lastname, emailid, phoneno, password, role, status) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";
      try {   
          connection = DatabaseCon.getConnection();
          ps = connection.prepareStatement(query);
          ps.setString(1, user.getFirstname());  
          ps.setString(2, user.getLastname()); 
          ps.setString(3, user.getEmailid());
          ps.setString(4, user.getPhone());
          ps.setString(5, user.getPassword());
          ps.setString(6, user.getRole());
          ps.setString(7, user.getStatus());
          return ps.executeUpdate();     
      } catch (SQLException e) {       
          System.out.println(e);
             return 0;    
      } catch (ClassNotFoundException e) { 
            System.out.println(e);
             return 0;
        } 
      finally {         
          DBUtil.closePreparedStatement(ps);
          if(connection!=null)
              try {
                  connection.close();
          } catch (SQLException ex) {
              Logger.getLogger(QuestionDB.class.getName()).log(Level.SEVERE, null, ex);
          }       
      }    
}
    public static User userExists(String email,String password) {
    
     Connection connection = null;       
     PreparedStatement ps = null;        
     String query = "select * from user where emailid=? and password=?";
      try {
          connection = DatabaseCon.getConnection();
          ps = connection.prepareStatement(query);
          ps.setString(1,email);  
          ps.setString(2,password);
          ResultSet rs = ps.executeQuery();
          if (rs.next()) {
              int id = rs.getInt("id");
              String firstname = rs.getString("firstname");
              String lastname = rs.getString("lastname");
              String emailid = rs.getString("emailid");
              String phoneno = rs.getString("phoneno");
              String pwd = rs.getString("password");
              String role = rs.getString("role");
              String status = rs.getString("status");
              User user = new User(id, firstname, lastname, emailid, phoneno, pwd, status, role);
              return user;
          }else{
              return null;
          }
      } catch (SQLException e) {       
          System.out.println(e);
             return null;    
      } catch (ClassNotFoundException e) { 
            System.out.println(e);
             return null; 
        } 
      finally {         
          DBUtil.closePreparedStatement(ps);
          if(connection!=null)
              try {
                  connection.close();
          } catch (SQLException ex) {
              Logger.getLogger(QuestionDB.class.getName()).log(Level.SEVERE, null, ex);
          }       
      }
    }
    public static ArrayList<User> getPendingUsers()
    {
     
     Connection connection = null;       
     Statement ps = null;        
     String query = "select * from user where status='pending'";
     ArrayList<User> pendingUsers = new ArrayList<>();
     try
     {
         connection = DatabaseCon.getConnection();
         ps = connection.createStatement();
         ResultSet rs = ps.executeQuery(query);
         while(rs.next()){
             int id = rs.getInt("id");
              String firstname = rs.getString("firstname");
              String lastname = rs.getString("lastname");
              String emailid = rs.getString("emailid");
              String phoneno = rs.getString("phoneno");
              String pwd = rs.getString("password");
              String role = rs.getString("role");
              String status = rs.getString("status");
              User user = new User(id, firstname, lastname, emailid, phoneno, pwd, status, role);
              pendingUsers.add(user);
         }
         return pendingUsers;
     }catch(Exception e){
         System.out.println(e);
         return null; 
     }finally{
         DBUtil.closeStatement(ps);
         if(connection!=null)
              try {
                  connection.close();
          } catch (SQLException ex) {
              Logger.getLogger(QuestionDB.class.getName()).log(Level.SEVERE, null, ex);
          }
     }
    }
    
    public static ArrayList<User> getDeclinedUsers()
    {
     
     Connection connection = null;       
     Statement ps = null;        
     String query = "select * from user where status='Decline'";
     ArrayList<User> pendingUsers = new ArrayList<>();
     try
     {
         connection = DatabaseCon.getConnection();
         ps = connection.createStatement();
         ResultSet rs = ps.executeQuery(query);
         while(rs.next()){
             int id = rs.getInt("id");
              String firstname = rs.getString("firstname");
              String lastname = rs.getString("lastname");
              String emailid = rs.getString("emailid");
              String phoneno = rs.getString("phoneno");
              String pwd = rs.getString("password");
              String role = rs.getString("role");
              String status = rs.getString("status");
              User user = new User(id, firstname, lastname, emailid, phoneno, pwd, status, role);
              pendingUsers.add(user);
         }
         return pendingUsers;
     }catch(Exception e){
         System.out.println(e);
         return null; 
     }finally{
         DBUtil.closeStatement(ps);
         if(connection!=null)
              try {
                  connection.close();
          } catch (SQLException ex) {
              Logger.getLogger(QuestionDB.class.getName()).log(Level.SEVERE, null, ex);
          }
     }
    }
    
    public static int ApproveAdmin(int id) {
        Connection connection = null;       
        Statement ps = null;        
        String query = "update user set status='Approved' where id="+id;
        try
        {
            connection = DatabaseCon.getConnection();
            ps = connection.createStatement();
            return ps.executeUpdate(query);
        }catch(Exception e){
            System.out.println(e);
            return 0;
        }finally{
            DBUtil.closeStatement(ps);
            if(connection!=null)
              try {
                  connection.close();
          } catch (SQLException ex) {
              Logger.getLogger(QuestionDB.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
    }
    
    public static int DeclineAdmin(int id) {
        Connection connection = null;       
        Statement ps = null;        
        String query = "update user set status='Decline' where id="+id;
        try
        {
            connection = DatabaseCon.getConnection();
            ps = connection.createStatement();
            return ps.executeUpdate(query);
        }catch(Exception e){
            System.out.println(e);
            return 0;
        }finally{
            DBUtil.closeStatement(ps);
            if(connection!=null)
              try {
                  connection.close();
          } catch (SQLException ex) {
              Logger.getLogger(QuestionDB.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
    }
    
    public static int DeleteAdmin(int id) {
        Connection connection = null;       
        Statement ps = null;        
        String query = "DELETE FROM users WHERE id="+id;
        try
        {
            connection = DatabaseCon.getConnection();
            ps = connection.createStatement();
            return ps.executeUpdate(query);
        }catch(Exception e){
            System.out.println(e);
            return 0;
        }finally{
            DBUtil.closeStatement(ps);
            if(connection!=null)
              try {
                  connection.close();
          } catch (SQLException ex) {
              Logger.getLogger(QuestionDB.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
    }
}