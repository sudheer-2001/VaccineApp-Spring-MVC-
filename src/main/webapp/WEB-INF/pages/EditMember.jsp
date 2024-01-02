<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
	@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800;900&display=swap');
*{
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: 'Poppins',sans-serif;
}

body{
	display: flex;
	min-height: 100vh;
	flex-direction: column;
	background: #000;
	margin: 0;
}
#header{
	padding-left: 4%;
	padding-right: 4%;
	background: rgba(0, 20, 40, 0.9);
	height: 50px;
	margin-bottom: auto; 
	min-height : 60px;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

#footer{
	background: rgba(0, 20, 40, 0.9);
	height: 50px;
	margin-top: auto;
}

.wrapper{
    display: flex;
    justify-content: center;
    align-items: center; 
    padding-bottom: 70px;
	margin-left: auto;
	margin-right: auto;
	margin-top: 0%;
	margin-bottom: 3%;
	width: 400px;
	max-height: 600px;
	background: #000;
	box-shadow: 0 0 50px #0ef;
	border-radius: 20px;
	padding: 10px;
	overflow: hidden;
}

#addButton {
  position: relative;
  width: 100%;
  height: 40px;
  background: #0ef;
  font-size: 16px;
  color: #000;
  font-weight: 500;
  cursor: pointer;
  border-radius: 30px;
  border: none;
  outline: none;
  padding: 0;
  animation: border-pulse 2s linear infinite;
}
@keyframes animate {
  100%{
  	filter: hue-rotate(360deg);
  }
}
@keyframes border-pulse {
  0% {
    box-shadow: 0 0 0 0px #03f40f;
  }
  50% {
    box-shadow: 0 0 0 4px #03f40f; /* Adjust the width of the "border" here */
  }
  100% {
    box-shadow: 0 0 0 0px #03f40f;
  }
}

.wrapper:hover {
  animation: animate 50s linear infinite;
}


.form-wrapper{
	justify-content: center;
	align-items: center;
}

h2{
	font-size: 30px;
	color: #fff;
	text-align: center;
}

.input-field{
	position: relative;
	margin: 30px 0;
	border-bottom: 2px solid #fff;
}

.input-field1{
	color: #51f1fc;
	position: relative;
	margin: 30px 0;
	border-bottom: 2px solid #fff;
}

.input-field>input[placeholder]{
	color: #51f1fc;
}

.input-field label{
	position: absolute;
	top : 50%;
	left: 5px;
	transform: translateY(-50%);
	font-size: 16px;
	color: #d0f5da;
	pointer-events: none;
}

.input-field1 label{
	transform: translateY(-50%);
	font-size: 16px;
	color: #d0f5da;
	pointer-events: none;
	transition: .5s;
}

.input-field1 input{
	font-size: 16px;
	color: #d0f5da;
	background: transparent;
	border: none;
	outline: none;
}

.input-field1 input:focus~label,.input-field1 input:valid~label{
	top: -5px;
}
.input-field input{
	font-size: 16px;
	color: white;
	padding: 0 5px;
	background: transparent;
	border: none;
	outline: none;
}
.input-field1>select{
	color: grey;
	width: 120px;
	margin-left: 20px;
}
input[type="date"]{
	color: navy;
	background: #FFFFFF;
	width: 138px;
	margin-left: 98px;
	height: 22px
}
select[name="idProof"]{
	background: #FFFFFF;
	color: navy;
	margin-left: 69px;
	height: 22px
}
select[name="vaccineType"]{
	background: #FFFFFF;
	color: navy;
	height: 22px;
	margin-left:20px;
	width: 142px;
}
select[name="dose"]{
	background: #FFFFFF;
	color: navy;
	margin-left: 88px;
	height: 22px
}
option{
	text-align: center;
	color: navy;
}
input[type="radio"]{
	color: #d0f5da;
}
::placeholder {
  color: #d0f5da;
  opacity: 0.8;
}

input:focus{
	font-weight: bolder;
}
#result>h1 {
	align-items: center;
	justify-content: center;
	border: none;
	outline: none;
	border-radius: 40px;
	font-size: 26px;
	margin-left: auto;
	margin-right: auto;
	color: white;
	padding: 40px 45px 68px 20px;
	height: 2vh;
	text-align: center;
}
</style>
</head>
<body>
	<header id="header">
		
	</header>
	<div id="result">
				<h1>${updateMessage}</h1>
			</div>
	<div class="wrapper">
		<div class="form-wrapper member">
			<form action="${pageContext.request.contextPath}/updateDetails" method="post">
				<h2>Edit Details</h2>
				<tr>
	                <td><input type="hidden" name="memberId" value="${memberId}"></td>
	            </tr>	
				<div class="input-field">
					<input type="text" name="memberName" value="${memberName}" required>
					<label for=""></label>
				</div>
				<div class="input-field">
					<input type="email" name="memberEmail" value="${memberEmail}" required>
					<label for=""></label>
				</div>
				<div class="input-field1">
					<label for="">Gender&#128073;</label>&nbsp;
					<input type="radio" name="gender" value="M" ${gender == 'M' ? 'checked' : ''} required>Male&nbsp;&nbsp;
					<input type="radio" name="gender" value="F" ${gender == 'F' ? 'checked' : ''} required>Female&nbsp;&nbsp;
					<input type="radio" name="gender" value="O" ${gender == 'O' ? 'checked' : ''} required>Others
				</div>
				<div class="input-field1">
					<label for="">DOB</label>&#128073;
					<input type="date" name="dob" value="${dob}" pattern="200[1-5]-\d{2}-\d{2}" required placeholder="DOB">
				</div>
				<div class="input-field1">
					<label for="">Id Proof</label>&#128073;
					<select name="idProof" value="" style="width: 142px;">
						<option value="" selected disabled>Select</option>
						<option value="DrivingLicense" ${idProof == 'DrivingLicense' ? 'selected' : ''}>Driving License</option>
						<option value="VoterId" ${idProof == 'VoterId' ? 'selected' : ''}>Voter Id</option>
						<option value="Aadhar" ${idProof == 'Aadhar' ? 'selected' : ''}>Aadhar Card</option>
						<option value="Pan" ${idProof == 'Pan' ? 'selected' : ''}>Pan Card</option>
					</select>
				</div>
				<div class="input-field">
					<input type="text" name="proofNumber" value="${idProofNumber}" required placeholder="Id Proof Number">
					<label for=""></label>
				</div>
				<div class="input-field1">
					<label for="">Vaccine Type</label>
					&#128073;<select name="vaccineType">
						<option value="" selected disabled>Select</option>
						<option value="CoviSheild" ${vaccineType == 'CoviSheild' ? 'selected' : ''}>CoviSheild</option>
						<option value="Covaxine" ${vaccineType == 'Covaxine' ? 'selected' : ''}>Covaxine</option>
					</select>
				</div>
				<div class="input-field1">
					<label for="">Dose</label>
					&#128073;<select name="dose" style="width: 142px;">
						<option value="" selected disabled>Select</option>
						<option value="1" ${dose == '1' ? 'selected' : ''}>1</option>
						<option value="2" ${dose == '2' ? 'selected' : ''}>2</option>
						<option value="3" ${dose == '3' ? 'selected' : ''}>3</option>
					</select>
				</div>

				<button id="addButton" type="submit">Update</button>
			</form>
		</div>
	</div>
	<footer id="footer"></footer>
</body>
</html>