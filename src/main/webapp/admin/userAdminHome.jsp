<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>User admin home</title>
	<link rel="stylesheet" type="text/css" href="../css/userAdminHome">
	
</head>
<body>
<jsp:include page="menu.jsp"/>

<h1>Congratulations for successfully logging in!</h2>
<h1>Current Date and Time:</h1>
<p id="currentDateTime"></p>

 <script type="text/javascript">
     // Function to update the current date and time
     function updateCurrentDateTime() {
         var currentDateTimeElement = document.getElementById("currentDateTime");

         // Get the current date, time, and time zone
         var currentDate = new Date();
         var currentDateString = currentDate.toLocaleDateString();
         var currentTimeString = currentDate.toLocaleTimeString();
         var timeZoneString = Intl.DateTimeFormat().resolvedOptions().timeZone;

         // Display the current date, time, and time zone
         var currentDateTime = currentDateString + ' ' + currentTimeString + ' ' + timeZoneString;
         currentDateTimeElement.textContent = currentDateTime;
     }

     // Update the current date and time every second
     setInterval(updateCurrentDateTime, 1000);

     // Initial call to set the date and time immediately
     updateCurrentDateTime();
 </script>


</body>
</html>