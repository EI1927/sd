<%@page import="BeanClasses.Question"%>
<%@page import="BeanClasses.Comment"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="styles/main.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="script/main.js"></script>
        <title>Welcome</title>
    </head>
    <body>
        <div id="container">
            <header id="homeHeader">
		<div id="homeHeaderTitle">
                    <label id="titleText" class="noselect">Online Forum</label>
		</div>
                <div class="headerSession">Welcome <span><c:out value="${user}" /></span>  | <a href="MainServlet/Logout">Sign out</a></p></div>
            </header>
            <nav>
                <button id="allquestions" class="btn btn-primary" onclick="return aq_onclick()">All Questions</button>
                <button id="yourquestions" class="btn btn-primary" onclick="return yq_onclick()">Your Questions</button>
                <button id="newquestion" class="btn btn-primary" onclick="return nq_onclick()">Ask a Question</button>
            </nav>
            <section>
                <div>
                    <%
                    Question question = (Question)request.getSession().getAttribute("question");
                    if(question!= null){
                        out.println("<div><h2>"+question.getTitle()+"</h2><p>"+question.getDescription()+"</p>");
                        out.println("</div>");
                        out.println("<form action='MainServlet/PostComment' method='POST'><textarea required rows='3' cols='50' name='commentText'></textarea>");
                        out.println("<br><button type='submit'>Comment</button></form>");
                        out.print("<h6><a href='MainServlet/ReportQuestion?questionId="+question.getId()+"'>Report Question</a></h6>");
                        ArrayList<Comment> comments= (ArrayList<Comment>)request.getSession().getAttribute("comments");
                        if(comments!=null && comments.size()>0){
                            out.println("<br><p>Comments</p>");
                            for(Comment comment: comments){
                                out.println("<div style='border: 2px solid buttonface; border-radius: 5px; width: 430px;'><p>"+comment.getCommentText()+"</p><p><small>By "+comment.getUserName()+"</small></p>");
                                out.println("</div>");
                            }
                        }
                    }
                    %>
                </div>
            </section>
            
        </div>
    </body>
</html>
