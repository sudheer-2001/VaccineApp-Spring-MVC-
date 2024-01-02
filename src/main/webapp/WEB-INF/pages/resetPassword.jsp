<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Forget Password</title>
<style type="text/css">

#header {
            background-color: #333;
            color: #fff;
            padding: 10px;
            text-align: center;
            height: 50px;
        }
         * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Poppins', sans-serif;
        }
        body {
            display: flex;
            min-height: 100vh;
            flex-direction: column;
           background: #000;
            margin: 0;
        }
        .link-button{
            order: -1;
            display: flex;
            justify-content: flex-end;
            align-items: center;
            width: 100%;
        }
        .link-button a {
            background-color: #fff;
            color: navy;
            width: 100px;
            border-radius: 15px;
            padding-top: 5px;
            margin-right: 30px;
            text-decoration: none;
            height: 30px;
         }

            .link-button a:hover {
               background-color: rgba(50, 190, 225, .9);
               color: #fff;
            }
        footer {
            margin-top: auto;
            height: 50px;
            bottom: 0;
            width: 100%;
            background-color: #333;
            color: #fff;
            text-align: center;
            padding: 10px 0;
        }
      .login-box {
  position: absolute;
  top: 50%;
  left: 50%;
  width: 400px;
  padding: 40px;
  margin: 20px auto;
  transform: translate(-50%, -55%);
 background: rgba(0,0,0,.9);
  box-sizing: border-box;
  box-shadow: 0 15px 25px rgba(0,0,0,.6);
  border-radius: 10px;
}

.login-box p:first-child {
  margin: 0 0 30px;
  padding: 0;
  color: #fff;
  text-align: center;
  font-size: 1.5rem;
  font-weight: bold;
  letter-spacing: 1px;
}

.login-box .user-box {
  position: relative;
}

.login-box .user-box input {
  width: 100%;
  padding: 10px 0;
  font-size: 16px;
  color: #fff;
  margin-bottom: 30px;
  border: none;
  border-bottom: 1px solid #fff;
  outline: none;
  background: transparent;
}

.login-box .user-box label {
  position: absolute;
  top: 0;
  left: 0;
  padding: 10px 0;
  font-size: 16px;
  color: #fff;
  pointer-events: none;
  transition: .5s;
}

.login-box .user-box input:focus ~ label,
.login-box .user-box input:valid ~ label {
  top: -20px;
  left: 0;
  color: #fff;
  font-size: 12px;
}
input::placeholder{
	color: #fff;
}
.login-box form button {
  position: relative;
  display: inline-block;
  padding: 10px 20px;
  font-weight: bold;
  color: #fff;
  font-size: 16px;
  text-decoration: none;
  text-transform: uppercase;
  overflow: hidden;
  transition: background-color 0.3s, color 0.3s;
  margin-top: 40px;
  letter-spacing: 3px;
  border: none;
  border-radius: 5px;
  cursor: pointer; /* Change cursor to pointer on hover */
  background-color: #333;
}

.login-box form button:hover {
  background-color: #fff;
  color: #272727;
  border-radius: 5px;
}

.login-box form button span {
  position: absolute;
  display: block;
}

.login-box button span:nth-child(1) {
  top: 0;
  left: -100%;
  width: 100%;
  height: 2px;
  background: linear-gradient(90deg, transparent, #fff);
  animation: btn-anim1 1.5s linear infinite;
}

@keyframes btn-anim1 {
  0% {
    left: -100%;
  }

  50%,100% {
    left: 100%;
  }
}
.login-box button span:nth-child(2) {
  top: -100%;
  right: 0;
  width: 2px;
  height: 100%;
  background: linear-gradient(180deg, transparent, #fff);
  animation: btn-anim2 1.5s linear infinite;
  animation-delay: .375s
}

@keyframes btn-anim2 {
  0% {
    top: -100%;
  }

  50%,100% {
    top: 100%;
  }
}

.login-box button span:nth-child(3) {
  bottom: 0;
  right: -100%;
  width: 100%;
  height: 2px;
  background: linear-gradient(270deg, transparent, #fff);
  animation: btn-anim3 1.5s linear infinite;
  animation-delay: .75s
}

@keyframes btn-anim3 {
  0% {
    right: -100%;
  }

  50%,100% {
    right: 100%;
  }
}

.login-box button span:nth-child(4) {
  bottom: -100%;
  left: 0;
  width: 2px;
  height: 100%;
  background: linear-gradient(360deg, transparent, #fff);
  animation: btn-anim4 1.5s linear infinite;
  animation-delay: 1.125s
}

@keyframes btn-anim4 {
  0% {
    bottom: -100%;
  }

  50%,100% {
    bottom: 100%;
  }
}

.login-box p:last-child {
  color: #aaa;
  font-size: 14px;
}


   </style>
</head>
<body>

   <header id="header">
      <div class="link-button">
         <a href="getIndexPage">HomePage</a>
      </div>
   </header>
   <div class="login-box">
  <p>Reset Password</p>
   <form action="resetPasswordPage" method="post"  id="resetForm">
    <div class="user-box">
      <input required name="email" type="email" placeholder="Email">
    </div>
    <div class="user-box">
      <input required name="password" type="password">
      <label>Password</label>
    </div>
    <div class="user-box">
      <input required name="confirmPassword" type="password">
      <label>Confirm Password</label>
    </div>
    <button type="submit" name="resetBtn">
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      Reset
    </button>
  </form>

</div>
<footer id="footer"></footer>
</body>
</html>