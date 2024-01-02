<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/AddMember.css">
<style>
	body {
	display: flex;
	min-height: 100vh;
	flex-direction: column;
	background-image: url('resources/images/addmember.jpg');
	background-size: cover;
	margin: 0;
}
</style>
<script>
	function validateForm() {
		const memberName = document.getElementsByName('memberName')[0].value;
		const memberEmail = document.getElementsByName('memberEmail')[0].value;
		const gender = document.querySelector('input[name="gender"]:checked');
		const dob = document.getElementsByName('memberDob')[0].value;
		const idProof = document.getElementsByName('idProof')[0].value;
		const proofNumber = document.getElementsByName('proofNumber')[0].value;
		const vaccineType = document.getElementsByName('vaccineType')[0].value;
		const dose = document.getElementsByName('dose')[0].value;

		// Check if any field is empty
		if (memberName === "") {
			alert("Member Name is mandatory...");
			return false;
		} else if (memberEmail === "") {
			alert("Email is mandatory...");
			return false;
		} else if (!gender) {
			alert("Gender is mandatory...");
			return false;
		} else if (dob === "") {
			alert("please give valid date of birth...");
			return false;
		} else if (idProof === "") {
			alert("Please select Id Proof...");
			return false;
		} else if (proofNumber === "") {
			alert("Enter the Id Proof Number");
			return false;
		} else if (vaccineType === "") {
			alert("Please select Vaccine type..")
			return false;
		} else if (dose === "") {
			alert("Please select Dose which you have done...");
			return false;
		}

		// Validate government ID based on the selected option
		if (idProof === "Aadhar"
				&& (proofNumber.length !== 12 || isNaN(proofNumber))) {
			alert("Aadhar ID must be exactly 12 digits");
			return false;
		} else if (idProof === "Pan"
				&& (proofNumber.length !== 10 || !isValidPan(proofNumber))) {
			alert("Pan ID must be exactly 10 characters and alphanumeric");
			return false;
		} else if (idProof === "DrivingLicense"
				&& (proofNumber.length !== 16 || !isValidDrivingLicense(proofNumber))) {
			alert("Driving License ID must be exactly 16 characters and alphanumeric");
			return false;

		} else if (idProof === "DrivingLicense"
				&& (proofNumber.length !== 16 || !isValidDrivingLicense(proofNumber))) {
			alert("Driving License ID must be exactly 16 characters and alphanumeric");
			return false;
		}

		return true;
	}

	// Function to validate Pan ID format
	function isValidPan(pan) {
		var regex = /^[A-Za-z]{5}\d{4}[A-Za-z]{1}$/;
		return regex.test(pan);
	}

	// Function to validate Driving License format
	function isValidDrivingLicense(dl) {
		var regex = /^[A-Za-z0-9]{16}$/;
		return regex.test(dl);
	}

	function isValidVoterId(voter) {
		var regex = /^[A-Z]{3}\d{7}$/;
		return regex.test(voter);
	}
</script>
</head>
<body>
	<header id="header">
		<a href="getAllMember" class="viewMembers-button"><button
				id="view">View Members</button></a> <b> Hi, ${userEmail} <a
			href="gotoHome" class="viewMembers-button"><button id="home">Home</button>
		</a>
		</b>
	</header>
	<div id="result">
		<h1>${responseMessage}</h1>
	</div>
	<div class="wrapper">
		<div class="form-wrapper member">
			<form onsubmit="return validateForm()" action="addMember"
				method="post">
				<h2>Add Member</h2>
				<div class="input-field">
					<input type="text" name="memberName" placeholder="Name"> <label
						for=""></label>
				</div>
				<div class="input-field">
					<input type="email" name="memberEmail" placeholder="Email">
					<label for=""></label>
				</div>
				<div class="input-field1">
					<label for="">Gender&#128073;</label>&nbsp; <input type="radio"
						name="gender" value="M">Male&nbsp;&nbsp; <input
						type="radio" name="gender" value="F">Female&nbsp;&nbsp; <input
						type="radio" name="gender" value="O">Others
				</div>
				<div class="input-field1">
					<label for="">DOB</label>&#128073; <input type="date"
						name="memberDob" pattern="200[1-5]-\d{2}-\d{2}" placeholder="DOB">
				</div>
				<div class="input-field1">
					<label for="">Id Proof</label>&#128073; <select name="idProof"
						style="width: 142px;">
						<option value="" selected disabled>Select</option>
						<option value="DrivingLicense">Driving License</option>
						<option value="VoterId">Voter Id</option>
						<option value="Aadhar">Aadhar Card</option>
						<option value="Pan">Pan Card</option>
					</select>
				</div>
				<div class="input-field">
					<input type="text" name="proofNumber" placeholder="Id Proof Number">
					<label for=""></label>
				</div>
				<div class="input-field1">
					<label for="">Vaccine Type</label> &#128073;<select
						name="vaccineType">
						<option value="" selected disabled>Select</option>
						<option value="CoviSheild">CoviSheild</option>
						<option value="Covaxine">Covaxine</option>
					</select>
				</div>
				<div class="input-field1">
					<label for="">Dose</label> &#128073;<select name="dose"
						style="width: 142px;">
						<option value="" selected disabled>Select</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
					</select>
				</div>

				<button id="addButton" type="submit">Add</button>
			</form>
		</div>
	</div>
	<h2>
		<p>${updateMessage}</p>
	</h2>
	<table id="table2" border="2px">
		<tr id="tr21">
			<th>Member Id</th>
			<th>Member Name</th>
			<th>gender</th>
			<th>DOB</th>
			<th>Id Proof</th>
			<th>Proof Number</th>
			<th>Vaccine Type</th>
			<th>Dose</th>
			<th>Member Email</th>
			<th colspan="2">Functions</th>
		</tr>
		<c:forEach var="member" items="${viewMembers}">
			<tr id="tr22">
				<td>${member.getMemberId()}</td>
				<td>${member.memberName}</td>
				<td>${member.gender}</td>
				<td>${member.dob}</td>
				<td>${member.idProof}</td>
				<td>${member.idProofNumber}</td>
				<td>${member.vaccineType}</td>
				<td>${member.dose}</td>
				<td>${member.memberEmail}</td>
				<td><a class="cb" href="editMembers/${member.getMemberId()}">Edit</a></td>
				<td><a class="del-button"
					href="deleteMember/${member.getMemberId()}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<footer id="footer"></footer>
</body>
</html>