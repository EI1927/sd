package BeanClasses;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class Comment {
    int id,questionid;
    String commentText, userName, userEmailID;

    public Comment(){
    
    }
    
    public Comment(int id, int questionid, String commentText, String userName, String userEmailID) {
        this.id = id;
        this.questionid = questionid;
        this.commentText = commentText;
        this.userName = userName;
        this.userEmailID = userEmailID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmailID() {
        return userEmailID;
    }

    public void setUserEmailID(String userEmailID) {
        this.userEmailID = userEmailID;
    }
    
    
}
