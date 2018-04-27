<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYEPE html>
<html>
	<head>
		<meta charset="UTF-8"/>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="styles/main.css">
		<script src="script/main.js"></script>
		<title>Online Forum</title>
	</head>
	<body>
		<div id ='container'>
			<header id="homeHeader">
				<div id="homeHeaderTitle">
					<label id="titleText" class="noselect">Online Forum</label>
                                </div>
				<div id="homeHeaderNav">
					<label class="noselect" id="homeLink">Home</label>
					<label class="noselect" id="aboutusLink">About US</label>
					<label class="noselect" id="contactusLink">Contact US</label>
				</div>
				<div  id="headerLogin">
						<form class="form-inline" action="MainServlet/Login" method="POST">
							<div class="form-group">
    							<input width= "100px" type="email" name="loginEmail" class="form-control input-sm" id="email" placeholder="Email ID">
  							</div>
							<div class="form-group">
								<input type="password" name="loginPassword" class="form-control input-sm" id="pwd" placeholder="Password">
							</div>
							<button type="submit" class="btn btn-info btn-sm">Login</button>
						</form>
                                                <c:choose>
                                                    <c:when test="${empty message}">
                                                    </c:when>
                                                    <c:otherwise>
                                                        <p id="loginMessage">${message}</p>
                                                        <%session.setAttribute( "message", "" );%>
                                                    </c:otherwise>
                                                </c:choose>
				</div>
			</header>
			<div class="homeSection" id="sectionHome">
				<div id="homeMain">
                                    <p></p>    
				</div>
				<div id="homeSignUp">
					<div id="signUpDiv">
						<label id="registerTitle">Register</label>
						<form action="MainServlet/Register" method="POST">
      						<div class="form-group">
      							<label for="FirstName">First Name:</label>
      							<input type="text" name="signupFName" class="form-control input-sm" id="FirstName" placeholder="Enter First Name" required>
          					</div>
            				<div class="form-group">
            					<label for="LastName">Last Name:</label>
            					<input type="text" name="signupLName" class="form-control input-sm" id="LastName" placeholder="Enter Last Name" required>
          					</div>
          					<div class="form-group">
            					<label for="Phone">Phone:</label>
            					<input type="tel" name="signupPhone" pattern='[\(]\d{3}[\)]\d{3}[\-]\d{4}' class="form-control input-sm" id="Phone" placeholder="Enter Phone number" required>
          					</div>
          					<div class="form-group">
          						<label>Type :</label>
              					<label class="radio-inline">
              						<input type="radio" value="user" name="usertype" checked="true"> User
              					</label>
              					<label class="radio-inline">
                  					<input type="radio" value="admin" name="usertype"> Admin
              					</label>
          					</div>
          					<div class="form-group">
            					<label for="email">Email:</label>
                                                <input type="email" name="signupEmail" class="form-control input-sm" id="email" placeholder="Enter Email" required>
          					</div>
          					<div class="form-group">
            					<label for="pwd">Password:</label>
          						<input type="password" name="signupPassword" class="form-control input-sm" id="pwd" placeholder="Enter Password" required>
          					</div>
          					<button id="resetButton" type="reset" class="btn btn-danger btn-sm">Reset</button>
          					<button type="submit" class="btn btn btn-info btn-sm">Register</button>
          					<br>
          					<br>
        				</form>
					</div>
				</div>	
			</div>
			<div class="homeSection" id="sectionFeatures">
				<br>
				<div class="slideshow-container">
  					<div class="mySlides fade">
    					<div class="numbertext">1 / 4</div>
    					<img src="images/image1.jpg" style="width:100%">
  					</div>
					<div class="mySlides fade">
						<div class="numbertext">2 / 4</div>
						<img src="images/image2.jpg" style="width:100%">
  					</div>

  					<div class="mySlides fade">
  						<div class="numbertext">3 / 4</div>
  						<img src="images/image3.jpg" style="width:100%">
  					</div>
  					
				</div>
  				<br>
			</div>
			<div class="homeSection" id="sectionAboutUS">
			<br>
				<div id="aboutusDiv">
					<div id="aboutProfile">
						<p>Simple, fast, easy to use, and fun... these are words commonly used to describe our Online Forum. </p>
					</div>
				</div>	
			</div>
			<div class="homeSection" id="sectioncontactUS">
				<br>
				<div id="contactdiv">
				<form method="POST" action="MainServlet/ContactUS" name="contactForm">
                        <div class="form-group">
                            <label for="nameEmail">Full Name: </label>
                            <input type="text" class="form-control input-sm" name="nameEmail" required>
                        </div>
                        <div class="form-group">
                            <label for="fromEmail">Email ID: </label>
                            <input type="email" class="form-control input-sm" name="fromEmail" required>
                        </div>
                        <div class="form-group">
                            <label for="subjectEmail">Subject: </label>
                            <input type="text" class="form-control input-sm" name="subjectEmail" required>
                        </div>
                        <div class="form-group">
                            <label for="messageEmail">Message: </label>
                            <textarea name="messageEmail" class="form-control input-sm" rows="6"></textarea>
                        </div>
                        <input id="aqcButton" type="reset" class="btn btn-danger btn-sm" value="Reset">
                        <input id="aqsButton" type="submit" class="btn btn-info btn-sm" value="Send Email">
                    </form>
				</div>
				
			</div>
		</div>
	</body>
</html>