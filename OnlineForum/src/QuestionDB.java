
import BeanClasses.Comment;
import BeanClasses.Question;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class QuestionDB {
    public static int insert(Question question) {
    
     Connection connection = null;       
     PreparedStatement ps = null;        
     String query = "INSERT INTO questions (title, description, emailid, status, timestamp) " + "VALUES (?, ?, ?, ?, ?)";
      try {       
          connection = DatabaseCon.getConnection();
          ps = connection.prepareStatement(query);
          ps.setString(1, question.getTitle());  
          ps.setString(2, question.getDescription()); 
          ps.setString(3, question.getEmailid());
          ps.setString(4, question.getStatus());
          ps.setDate(5, new java.sql.Date(question.getPostedDate().getTime()));
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
    public static ArrayList<Question> getAllQuestions() {
    
     Connection connection = null;       
     Statement stmt = null;        
     String query = "select * from questions order by id desc";
     ArrayList<Question> questions = new ArrayList<>();
      try {
          connection = DatabaseCon.getConnection();
          stmt = connection.createStatement();
          ResultSet rs = stmt.executeQuery(query);
          while(rs.next()){
              int id = rs.getInt("id");
              String title = rs.getString("title");
              String desc = rs.getString("description");
              String emailid = rs.getString("emailid");
              String status = rs.getString("status");
              java.util.Date date = rs.getDate("timestamp");
              Question question = new Question(id,title,desc,emailid,status,date);
              questions.add(question);
          }
          return questions;
      } catch (SQLException e) {       
          System.out.println(e);
             return null;    
      }catch (ClassNotFoundException e) { 
            System.out.println(e);
             return null;  
        } 
      finally {         
          DBUtil.closeStatement(stmt);
          if(connection!=null)
              try {
                  connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QuestionDB.class.getName()).log(Level.SEVERE, null, ex);
                }
      }
    }
    public static ArrayList<Question> getAllQuestionsByUser(String email) {
    
     Connection connection = null;       
     PreparedStatement stmt = null;        
     String query = "select * from questions where emailid = ? order by id desc";
     ArrayList<Question> questions = new ArrayList<>();
      try {   
          connection = DatabaseCon.getConnection();
          stmt = connection.prepareStatement(query);
          stmt.setString(1, email);
          ResultSet rs = stmt.executeQuery();
          while(rs.next()){
              int id = rs.getInt("id");
              String title = rs.getString("title");
              String desc = rs.getString("description");
              String emailid = rs.getString("emailid");
              String status = rs.getString("status");
              java.util.Date date = rs.getDate("timestamp");
              Question question = new Question(id,title,desc,emailid,status,date);
              questions.add(question);
          }
          return questions;
      } catch (SQLException e) {       
          System.out.println(e);
             return null;    
      } catch (ClassNotFoundException e) { 
            System.out.println(e);
             return null;
        } 
      finally {         
          DBUtil.closePreparedStatement(stmt);
                 if(connection!=null)
                     try {
                         connection.close();
          } catch (SQLException ex) {
              Logger.getLogger(QuestionDB.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    }
    public static ArrayList<Comment> getCommentsForQuestion(int questionID) {
    
     Connection connection = null;       
     PreparedStatement stmt = null;        
     String query = "select * from comments where questionid = ?";
     ArrayList<Comment> comments = new ArrayList<>();
      try {      
          connection = DatabaseCon.getConnection();
          stmt = connection.prepareStatement(query);
          stmt.setInt(1, questionID);
          ResultSet rs = stmt.executeQuery();
          while(rs.next()){
              int id = rs.getInt("commentid");
              int questionId = rs.getInt("questionid");
              String commentText = rs.getString("commenttext");
              String useremailid = rs.getString("useremailid");
              String username = rs.getString("username");
              Comment comment = new Comment(id, questionId,commentText, username, useremailid);
              comments.add(comment);
          }
          return comments;
      } catch (SQLException e) {       
          System.out.println(e);
             return null;    
      } catch (ClassNotFoundException e) { 
            System.out.println(e);
             return null; 
        } 
      finally {         
          DBUtil.closePreparedStatement(stmt);
          if(connection!=null)
              try {
                  connection.close();
          } catch (SQLException ex) {
              Logger.getLogger(QuestionDB.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    }
    
    public static int postCommnet(Comment comment) {
     Connection connection = null;       
     PreparedStatement stmt = null;        
     String query = "insert into comments(questionid, commenttext,username,useremailid) values(?,?,?,?)";
      try {    
          connection = DatabaseCon.getConnection();
          stmt = connection.prepareStatement(query);
          stmt.setInt(1, comment.getQuestionid());
          stmt.setString(2, comment.getCommentText());
          stmt.setString(3, comment.getUserName());
          stmt.setString(4, comment.getUserEmailID());
          return stmt.executeUpdate();
      } catch (SQLException e) {       
          System.out.println(e);
             return 0;    
      } catch (ClassNotFoundException e) { 
            System.out.println(e);
             return 0; 
        } 
      finally {         
          DBUtil.closePreparedStatement(stmt);
          if(connection!=null)
              try {
                  connection.close();
          } catch (SQLException ex) {
              Logger.getLogger(QuestionDB.class.getName()).log(Level.SEVERE, null, ex);
          }              
      }
    }
    
    public static void reportQuestion(int id){
    
        Connection connection = null;       
        Statement ps = null;        
        String query = "update questions set status='R' where id="+id;
        try
        {
            connection = DatabaseCon.getConnection();
            ps = connection.createStatement();
            ps.executeUpdate(query);
        }catch(Exception e){
            System.out.println(e);
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

    static ArrayList<Question> getAllReportedQuestions() {
        
        Connection connection = null;       
        Statement stmt = null;        
        String query = "select * from questions where status='R'";
        ArrayList<Question> questions = new ArrayList<>();
        try {    
            connection = DatabaseCon.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String desc = rs.getString("description");
                String emailid = rs.getString("emailid");
                String status = rs.getString("status");
                java.util.Date date = rs.getDate("timestamp");
                Question question = new Question(id,title,desc,emailid,status,date);
                questions.add(question);
          }
          return questions;
      } catch (SQLException e) {       
          System.out.println(e);
             return null;    
      } catch (ClassNotFoundException e) { 
            System.out.println(e);
             return null;
        } 
      finally {         
          DBUtil.closePreparedStatement(stmt);
          if(connection!=null)
              try {
                  connection.close();
          } catch (SQLException ex) {
              Logger.getLogger(QuestionDB.class.getName()).log(Level.SEVERE, null, ex);
          }       
      }
    }

    static void revokeQuestion(int id) {
       
        Connection connection = null;       
        Statement ps = null;        
        String query = "update questions set status='N' where id="+id;
        try
        {
            connection = DatabaseCon.getConnection();
            ps = connection.createStatement();
            ps.executeUpdate(query);
        }catch(Exception e){
            System.out.println(e);
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

    static void deleteQuestion(int id) {
        
        Connection connection = null;       
        Statement ps = null;        
        String query = "delete from questions where id="+id;
        try
        {
            connection = DatabaseCon.getConnection();
            ps = connection.createStatement();
            ps.executeUpdate(query);
        }catch(Exception e){
            System.out.println(e);
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
