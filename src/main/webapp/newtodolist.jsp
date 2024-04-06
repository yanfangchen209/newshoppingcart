<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>to do list with js but not jsp to generate web page</title>
<script>

    function todolist(){
        const xhttp = new XMLHttpRequest();
        xhttp.onload = function(){
            //todolist is a array
            const todolist = JSON.parse(this.responseText);
            renderpage(todolist);
        }
        xhttp.open("GET", "newtodolist", true);
        //if have parameter,
        
        xhttp.send();
    }

    function notNullCheck(){
        //?
        const userInput = event.target.value;
        if(!userInput){
            document.getElementById("inputError").textContent = "This field cannot be empty";
        }else{
            document.getElementById("inputError").textContent = ""; // Clear the error message
        }
    }  


    function renderpage(todolist){
        const inputAddDiv = document.createElement("div");
        inputAddDiv.className = "inputAddDiv";

        const inputBox = document.createElement("input");
        inputBox.id = "inputBox";
        inputBox.addEventListener("change", notNullCheck);

        const addButton = document.createElement("button");
        addButton.type = "button";
        addButton.innerText = "Add";
        //todo: check empty input, check duplicate input
        addButton.addEventListener("click", addTask);

        const userInputError = document.createElement("span");
        userInputError.id = "inputError";

        const addResultMessage = document.createElement("span");
        addResultMessage.id = "addResultMessage";

        //total tasks summary
        const spanContainer = document.createElement("div");

        const taskSummary = document.createElement("span");
        taskSummary.id = "taskSummary";
        //get request return an array-todolist
        taskSummary.innerText = "You have a total of " + todolist.length  + " task(s).\n\n";



        //draw rows of tasks
        const rows = document.createElement("div");
        rows.id = "rows";

        //append
        const root = document.getElementById("container");
        inputAddDiv.appendChild(inputBox);
        inputAddDiv.appendChild(addButton);
        spanContainer.appendChild(taskSummary);
        root.appendChild(inputAddDiv);
        root.appendChild(addResultMessage);
        root.appendChild(spanContainer);
        generateAllRows(todolist, rows);
        root.appendChild(rows);

    }

     function generateAllRows(todolist, parentElem){
        todolist.forEach(todo => {
            const rowdiv = generateEachRow(todo);
            parentElem.appendChild(rowdiv);       
        });
    }



    function generateEachRow(taskName){
        const row = document.createElement("div");
        row.className = "row";

        const task = document.createElement("span");
        task.className = "task";
        task.innerText = taskName;

        const deleteButton = document.createElement("button");
        deleteButton.innerText = "Delete";
        //deleteButton.addEventListener("click", () => onDelete(row_div));
        deleteButton.addEventListener("click", onDelete);

        row.appendChild(task);
        row.appendChild(deleteButton);
        return row;

    } 
    function onDelete(){
        //console.log("test delete button");
        const deleteConfirm = confirm("Are you sure to delete this task?");
        const row_div = event.target.parentElement;

        if(deleteConfirm){
            doDelete(row_div);
        }
    }

    function doDelete(row_div){
        const xhttp = new XMLHttpRequest();
        const taskName = row_div.getElementsByClassName("task")[0].innerText;
        xhttp.onload = function(){
            const deleteResult = JSON.parse(this.responseText);
            if(deleteResult){
                const rows = document.getElementById("rows");
                rows.removeChild(row_div);
                const rowCount = rows.getElementsByClassName("row").length;
                document.getElementById("taskSummary").innerText = "You have a total of " + rowCount + " task(s).\n\n";
            }else{
                console.log("in practice, here give a user a message notifying deletion failed.");
            }
        }
        xhttp.open("DELETE", "newtodolist", true)
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send("taskName=" + taskName);

    }


    function addTask(){
       //console.log("test addtask");
       const taskName = document.getElementById("inputBox").value;
       const xhttp = new XMLHttpRequest();
       xhttp.onload = function(){
        const addResult = JSON.parse(this.responseText);
        //if adding successfully at back end, revise frontend/ui accordingly
        if(addResult){
            const newRow = generateEachRow(taskName);
            const rows = document.getElementById("rows");
            rows.appendChild(newRow);
            const rowCount = rows.getElementsByClassName("row").length;
            document.getElementById("taskSummary").innerText = "You have a total of " + rowCount + " task(s).\n\n";          
        }else{
            document.getElementById("addResultMessage").innerText = "Adding task failed.";
        }
     
       }  


        xhttp.open("POST", "newtodolist", true);
       xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
       xhttp.send("toDoName=" + taskName);
     
    } 




</script>
</head>
<body>
<div id="container">

</div>


<script>
    todolist();
</script>

</body>
</html>