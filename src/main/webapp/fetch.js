const url = "http://localhost:8081/ERS/"

let loginbtn = document.getElementById("loginButton");
let registerbtn = document.getElementById("registerUserBtn");

loginbtn.addEventListener("click", login);
registerbtn.addEventListener("click", registerUser);

async function login(){
  let userN = document.getElementById("username").value;
  let passW = document.getElementById("password").value;
  
  let user = {
    username:userN,
    password:passW
  };

	let response = await fetch(url +"login", {
    method:"POST",
    body: JSON.stringify(user),
    credentials:"include"
  });

  if(response.status===200){
    let errorMessage = document.getElementById("Registeration");
     errorMessage.innerText=uName + "good Job u are now loged in.";
    //revealDivs();
  }else{
    console.log("could not log in");
    let errorMessage  = document.getElementById("Registeration");
    errorMessage.innerText="Sorry u should try agin";
    console.log(response);
    revealRegisterForm(true);
  }
}

function revealRegisterForm(boolean){
  let regdivs = document.getElementsByClassName("registerDiv");
  if (boolean){
    for (let div of regdivs){
      div.hidden=false;
    }
  } else{
    for (let div of regdivs){
      div.hidden=true;
    }
  }
}

async function registerUser(){
  
  let userName = document.getElementById("newUsername").value;
  let passWord = document.getElementById("newPassword").value;
  let role = "EMPLOYEE"; /* document.getElementById("userRoleForm").elements["foo"] */
  let firstName = document.getElementById("newUserFname").value;
  let lastName = document.getElementById("newUserLname").value;
  let email = document.getElementById("newUserEmail").value;
  let phonNumber = document.getElementById("newUserPhone").value;
  let addresse = document.getElementById("newUserAddress").value; 

  if (userName === "" || passWord === ""){
	  let errorMessege = document.getElementById("Registeration");
    errorMessege.innerText="Username and password cannot be empty. Please try again.";
    return;
  }
  
  let userToRegister = {
    username: userName,
    password: passWord,
    role: role,
    firstName: firstName,
    lastName: lastName,
    eMail: email,
    phoneNumber: phonNumber,
    address: addresse   
  }

  console.log(userToRegister);

  let response = await fetch(url+"register", {
    method:"POST",
    body:JSON.stringify(userToRegister),
    credentials:"include"
  });

  if(response.status===201){
    console.log("user registered succesfully");
    let errMsg = document.getElementById("registerMsg");
    errMsg.innerText="Now User can login after Regestiration Process";
    revealRegisterForm(false);
  }else{
    console.log("Could not register user");
    let errMsg = document.getElementById("registerMsg");
    errMsg.innerText="User is Exsit u can not logging";
    console.log(response);
  }
}


async function GetReimbursements(){
  let response = await fetch(url+"reimbursements", {
    credentials:"include"
  });

  if(response.status===200){
    let list = await response.json();

    populateReimbTable(list);
  }
}

function populateReimbTable(list){
  let tableBody = document.getElementById("todoBody");
  tableBody.innerHTML="";
  for(let todo of list){
    let row = document.createElement("tr");
    let id = document.createElement("td");
    let name = document.createElement("td");
    let task = document.createElement("td");
    let status = document.createElement("td");
    let user = document.createElement("td");

    id.innerText = todo.id;
    name.innerText = todo.name;
    task.innerText = todo.task;
    user.innerText = todo.creator.firstName+" "+todo.creator.lastName;
    status.innerText = todo.status;

    row.appendChild(id);
    row.appendChild(name);
    row.appendChild(task);
    row.appendChild(status);
    row.appendChild(user);
    tableBody.appendChild(row);
  }
}



async function updateReimbursements(){
  let todo = {
    id:document.getElementById("updateTodoId").value,
    name:"",
    task:"",
    status:document.getElementById("updateTodoStatus").value,
    creator:null
  }

  let response = await fetch(url+"todos", {
    method:"PUT",
    body:JSON.stringify(todo),
    credentials:"include"
  });

  if(response.status===200){
    getTodos();
  }else{
    console.log("Could not update Todo");
    console.log(response);
  }
}