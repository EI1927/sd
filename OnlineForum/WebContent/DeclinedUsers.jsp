<%@page import="BeanClasses.User"%>
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
        <title>Admin: Welcome</title>
    </head>
    <body>
        <%
                String userName = (String)request.getSession().getAttribute("user");
                    if(userName!=null && userName.length()>0){
                %>
        <div id="container">
            <header id="homeHeader">
		<div id="homeHeaderTitle">
                    <label id="titleText" class="noselect">Online Forum</label>
		</div>
                <div class="headerSession">Welcome <span><c:out value="${user}" /></span>  | <a href="MainServlet/Logout">Sign out</a></p></div>
            </header>
            <nav>
                <button id="pendingusers" class="btn btn-primary" onclick="return pu_onclick()">Pending Users</button>
                <button id="declinedusers" class="btn btn-primary" onclick="return du_onclick()">Declined Users</button>
                <button id="reportedquestions" class="btn btn-primary" onclick="return rp_onclick()">Reported Posts</button>
            </nav>
            <section>
                <div>
                     <%
                    ArrayList<User> users = (ArrayList<User>) request.getSession().getAttribute("declinedUsers");
                    if(users!= null && users.size()>0){
                        for(int i=0; i<users.size() ; i++) {
                            User user =users.get(i);
                            out.print("<div style='border: 2px solid buttonface; border-radius: 5px; width: 500px;'>");
                            out.print("<h3 style='margin-left: 10px;'>Fullname: "+user.getFirstname()+", "+user.getLastname()+"</h3>");
                            out.print("<h4 style='margin-left: 10px;'>Email ID: "+user.getEmailid()+"</h4>");
                            out.print("<h4 style='margin-left: 10px;'>Phone No: "+user.getPhone()+"</h4>");
                            out.print("<h6 style='margin-left: 10px;'><a href='MainServlet/ApproveAdmin1?userId="+user.getId()+"'>Approve</a>&nbsp;&nbsp;&nbsp;</h6>");
                            //<a href='MainServlet/DeleteAdmin?userId="+user.getId()+"'>Delete</a>
                            out.print("</div>");
                        }
                    }else{
                        out.println("<div><h4>No Declined Users</h4>");
                        out.println("</div>");
                    }
                    %>
                </div>
            </section>
            
                <%
                    }else{
                        out.println("<div><h4>You are not authorized.! <a href='Home.jsp'>Login</a></h4>");
                        out.println("</div>");
                    }
                %>
        </div>
    </body>
</html>
