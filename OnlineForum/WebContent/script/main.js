$(function(){
	$("#sectionHome").show();
	$("#sectionFeatures").hide();
	$("#sectionAboutUS").hide();
	$("#sectioncontactUS").hide();
	
	$("#homeLink").click(function(){
		$("#sectionHome").show();
		$("#sectionFeatures").hide();
		$("#sectionAboutUS").hide();
		$("#sectioncontactUS").hide();
	});
	
	$("#featuresLink").click(function(){
		$("#sectionHome").hide();
		$("#sectionFeatures").show();
		$("#sectionAboutUS").hide();
		$("#sectioncontactUS").hide();
	});
	
	$("#aboutusLink").click(function(){
		$("#sectionHome").hide();
		$("#sectionFeatures").hide();
		$("#sectionAboutUS").show();
		$("#sectioncontactUS").hide();
	});
	
	$("#contactusLink").click(function(){
		$("#sectionHome").hide();
		$("#sectionFeatures").hide();
		$("#sectionAboutUS").hide();
		$("#sectioncontactUS").show();
	});
        
        $("#allquestions").click(function(){
            url = "MainServlet/AllQuestions";
            $(location).attr("href", url);
        });
        
        $("#yourquestions").click(function(){
            url = "MainServlet/YourQuestions";
            $(location).attr("href", url);
        });
        $("#newquestion").click(function(){
            url = "MainServlet/PostQuestion";
            $(location).attr("href", url);
        });
	
        $("#pendingusers").click(function(){
            url = "MainServlet/PendingUsers";
            $(location).attr("href", url);
        });
        
        $("#declinedusers").click(function(){
            url = "MainServlet/DeclinedUsers";
            $(location).attr("href", url);
        });
        $("#reportedquestions").click(function(){
            url = "MainServlet/ReportedQuestions";
            $(location).attr("href", url);
        });
        
	var slideIndex = 0;
	showSlides();

	function showSlides() {
            var i;
            var slides = document.getElementsByClassName("mySlides");
            for (i = 0; i < slides.length; i++) {
       		slides[i].style.display = "none";  
            }
            slideIndex++;
            if (slideIndex> slides.length) {slideIndex = 1}    
            slides[slideIndex-1].style.display = "block";  
            setTimeout(showSlides, 2500); // Change image every 2 seconds
	}
	
});