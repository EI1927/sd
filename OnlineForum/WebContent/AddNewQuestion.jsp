
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
        <title>Ask new Question</title>
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
                <button id="newquestion" class="btn btn-primary">Ask a Question</button>
            </nav>
            <section>
                <div class="pqDiv">
                    <form method="POST" action="MainServlet/PostQuestion" name="questionForm">
                        <div class="form-group">
                            <label for="qstTitle">Title:</label>
                            <input type="text" class="form-control" name="qstTitle" required>
                        </div>
                        <div class="form-group">
                            <label for="qstDesc">Description(optional): </label>
                            <textarea name="qDesc" class="form-control" rows="6"></textarea>
                        </div>
                        <input id="aqcButton" type="button" class="btn btn-danger" value="Cancel">
                        <input id="aqsButton" type="submit" class="btn btn-info" value="Post Question">
                    </form>
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

