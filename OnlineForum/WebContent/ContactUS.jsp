
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="styles/main.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>Contact US</title>
    </head>
    <body>
        <div class="pqDiv">
                    <form method="POST" action="MainServlet/ContactUS" name="contactForm">
                        <div class="form-group">
                            <label for="nameEmail">Full Name: </label>
                            <input type="text" class="form-control" name="nameEmail" required>
                        </div>
                        <div class="form-group">
                            <label for="fromEmail">Email ID: </label>
                            <input type="email" class="form-control" name="fromEmail" required>
                        </div>
                        <div class="form-group">
                            <label for="subjectEmail">Subject: </label>
                            <input type="text" class="form-control" name="subjectEmail" required>
                        </div>
                        <div class="form-group">
                            <label for="messageEmail">Message: </label>
                            <textarea name="messageEmail" class="form-control" rows="6"></textarea>
                        </div>
                        <input id="aqcButton" type="reset" class="btn btn-danger" value="Reset">
                        <input id="aqsButton" type="submit" class="btn btn-info" value="Send Email">
                    </form>
                </div>
    </body>
</html>
