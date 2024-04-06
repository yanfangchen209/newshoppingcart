<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>	


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>To do list</title>
	<style>
		#userInputError{
			color:red;
			font-size:14px;
			margin-top: 5px;
		}


	</style> 

 
	
	<script>
        //call back related, 1
		function inputNotDuplicate(userinput, update){

			const xhttp = new XMLHttpRequest();
			xhttp.onload = function(){ 
				const duplicateOrNot = JSON.parse(this.responseText);
				//call back 2
				update(duplicateOrNot);
			}
			xhttp.open("GET", "datavalidation?taskName=" + userinput, true);
			xhttp.send();
			
		}
		function inputBoxValueNotDuplicate(userinput, updateUI){
			const xhttp = new XMLHttpRequest();
			xhttp.onload = function(){ 
				const duplicateOrNot = JSON.parse(this.responseText);
				//call back 2
				updateUI(duplicateOrNot);
			}
			xhttp.open("GET", "datavalidation?taskName=" + userinput, true);
			xhttp.send();
		}

		function updateUI(isDuplicate){
			if(isDuplicate){
				userInputError.textContent = "Duplicated tasks are not allowed.";
			}
		}
        //update(), call back related 3 
		function saveTask(isDuplicate){
			if(!isDuplicate){
				const toDoName = document.getElementById("taskvalue").value;
				userInputError.textContent = "";
				const xhttp = new XMLHttpRequest();
				xhttp.onload = function(){
	                
					//const rows = document.getElementsByClassName("rows")[0];
					const rows = document.getElementById("rows");
					const row_div = generateRow(toDoName);
					rows.appendChild(row_div);
					//const todolistsize = JSON.parse(this.reponseText);
					
					const todolistsize = rows.getElementsByClassName("rowdiv").length;
	
					document.getElementById("taskcount").innerText = todolistsize; 
				}
				
				//send form data
				xhttp.open("POST", "todolist");
				xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				//submit form data using javascript.
				xhttp.send("toDoName=" + toDoName);
				
				//xhttp.send("toDoName=" + toDoName + "&age=" + age + "&gender="+ gender);
				
				
				//send form data, Use builtin FormData api
				
				//const form = new FormData();
				//form.append("name", name);
				//form.append("age", age);
				//form.append("gender", gender)
				//xhttp.send(new URLSearchParams(form).toString());
				
				//send json data
				
				//var person= {name: 'justin', age: 30};
				//xhttp.setRequestHeader("Content-type", "application/json");
				//var json = JSON.stringify(person);// '{"name":"justin","age":30}'
				//xhttp.send(json);
				
				//send xml data
				//xhttp.setRequestHeader("Content-type", "application/xml");
				//xhttp.send("<person><name>Justin</name><age>30</age></person>");

			}else{
				userInputError.textContent = "Duplicated tasks are not allowed.";
			}  

		}//end of function(isDuplicate)

		function onAddTask(){
			//console.log("test add");
			const toDoName = document.getElementById("taskvalue").value;
			
			if(!toDoName){
					//let userInputError = document.getElementById("userInputError");
				userInputError.textContent = "This field cannot be empty.";
				return; 
			}else{
				//call back related ,4
				inputNotDuplicate(toDoName, saveTask);
				
			}//end of else	
		}//end of function onAddTask() 
 
		function generateRow(taskName){
			const row_div = document.createElement("div");
			row_div.className = "rowdiv";
			const row = document.createElement("span");
			row.className = "taskname";
			row.innerText = taskName;

			const deleteButton = document.createElement("button");
			deleteButton.type="button";
			deleteButton.innerText = "Delete";
			deleteButton.addEventListener("click", onConfirmDeleteDialogBox);
 
			row_div.appendChild(row);
			row_div.appendChild(deleteButton);  
			return row_div;  
 
		}
		

		
		
		//event given by browser passed to onConfirmDeleteDialogBox

		function onConfirmDeleteDialogBox(){ 
			
			//console.log("test delete confirm");
			let result = confirm("Are you sure you want to delete this items?");
			const rowdiv = event.target.parentElement;
			if(result){				
				onDeleteTask(rowdiv);				
			}		
		}
//<a href="url_to_delete" onclick="return confirm('Are you sure you want to delete this item?');">Delete</a>
/*The return confirm(...) expression ensures that the link action (navigating to the delete URL) 
only proceeds if the user clicks "OK" in the confirmation dialog. If the user clicks "Cancel," the 
link action will be canceled.
*/
		function onDeleteTask(rowdiv){
					const toDeleteName = rowdiv.getElementsByClassName("taskname")[0].innerText;
					const xhttp = new XMLHttpRequest();

					xhttp.onload = function(){
						const reply = JSON.parse(this.responseText);
						if(reply) {
							const rows = document.getElementById("rows");
							rows.removeChild(rowdiv);
							//update task summary
							const todolistsize = rows.getElementsByClassName("rowdiv").length;
							document.getElementById("taskcount").innerText = todolistsize;
						 }

					}
		 			
					xhttp.open("Delete", "todolist");
					xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
					xhttp.send("toDeleteName=" + toDeleteName)

		} 
		function inputNotNull(){
			let inputValue = document.getElementById("taskvalue").value;
			// Check if the user input is null or empty
            if (!inputValue) {
				//let userInputError = document.getElementById("userInputError");
                userInputError.textContent = "This field cannot be empty.";
            } else {
                userInputError.textContent = ""; // Clear the error message
            }
		} 

		 
	</script>

 
</head>
<body>
	<div class="container">
		<div>
			<input id="taskvalue" required> 
			<button type="button" onclick="onAddTask()">Add Task</button><br>
			<span id="userInputError"></span>
		</div>
		<script>
			let userinput = document.getElementById("taskvalue");
			userinput.addEventListener("change", inputNotNull);
			userinput.addEventListener("change", function(event){
				inputBoxValueNotDuplicate(event, updateUI)
			});
		</script>
		<div>
			<span id="totaltasks">You have <span id="taskcount">${todos.size()}</span> task(s) to do.
			</span><br><br> 
		</div>		 
		<div id="rows">	
				<c:forEach var="item" items="${todos}">
					<div class="rowdiv">
						<span class="taskname">
							<c:out value="${item}"/>
						</span> 
						<button onclick="onConfirmDeleteDialogBox()">Delete</button><br>
					</div>
				</c:forEach>		 
		</div>
	</div>  


</body>
</html>