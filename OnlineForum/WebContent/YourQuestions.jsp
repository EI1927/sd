
<%@page import="BeanClasses.Question"%>
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
        <%
                String user = (String)request.getSession().getAttribute("user");
                    if(user!=null && user.length()>0){
                %>
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
                        ArrayList <Question> questions = (ArrayList<Question>) request.getSession().getAttribute("myquestions");
                        if(questions!= null && questions.size()>0){
                            for(int i=0; i<questions.size() ; i++) {
                                out.println("<div><a href='MainServlet/GetQuestion?id="+questions.get(i).getId()+"'><h3>"+questions.get(i).getTitle()+"</h3></a><p>"+questions.get(i).getDescription()+"</p>");
                                out.print("<h6><a href='MainServlet/DeleteMyQuestion?questionId="+questions.get(i).getId()+"'>Delete Question</a></h6>");
                                out.println("</div>");
                            }
                        }else{
                            out.println("<div><h4>No Questions asked</h4>");
                            out.println("</div>");
                        }
                    %>
                    
                </div>
            </section>
           
        </div>
                    <%
                    }else{
                        out.println("<div><h4>You are not authorized.! <a href='Home.jsp'>Login</a></h4>");
                        out.println("</div>");
                    }
                %>
    </body>
</html>
