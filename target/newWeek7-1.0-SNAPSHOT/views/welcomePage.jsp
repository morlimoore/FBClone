<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>

    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            background-color: #E9EBEE;
            font-family: 'Lucida Grande', tahoma,verdana,arial,sans-serif;
            font-style: normal;
        }

        header a {
            color: #b0bcd5;
            text-decoration: none;
            font-family: sans-serif;
        }

        .formulaire {
            width: 480px;
            display: flex;
            align-items: flex-end;
        }

        .formulaire div {
            display: flex;
            flex-direction: column;
            margin-right: 20px;
        }

        .container {
            margin: 8px 0 0 200px;
            font-size: 12px;
        }

        header {
            display: flex;
            justify-content: space-around;
            align-items: center;
            height: 80px;
            background-color: #3B5998;
            color: white;
        }

        label {
            font-size: 12px;
            margin-bottom: 4px;
        }

        .btn {
            background-color: #516ac1;
            border: solid rgba(0, 0, 0, 0.3) 0.1em;
            color: white;
            font-family: helvetica, arial, sans-serif;
            font-size: 13px;
            padding: 5px 6px;
            margin-bottom: -1px;
            font-weight: bold;
            cursor: pointer;
            border-radius: 3px;
            margin-left: -5px;
            outline: none;
        }

        .logo {
            margin-top: 10px;
            width: 174px;
            cursor: pointer;
            color: white;
            font-size: 40px;
            font-weight: bold;
        }

        .forgot {
            margin: 2px 0 0 200px;
            color: #6d84b4;
        }

        input {
            padding: 3px;
        }

        .left {
            margin: 70px 650px 0 200px
        }

        .connect {
            color: rgba(0, 0, 0, 0.85);
            font-size: 24px;
            margin-bottom: 100px;
        }

        .center_list {
            list-style: none;
            padding-bottom:10px;
            margin-top:20px;
        }

        .list {
            float: left;
            margin-top: -40px;
            margin-bottom: 50px;
            padding-bottom:20px;
        }

        .img-list {
            display: inline-block;
            vertical-align: middle;
            margin-right: 20px;
        }

        .right {
            float: right;
            margin-top: -380px;
            margin-right: 230px;
        }

        .inputs {
            margin-top: 25px
        }

        .input {
            padding-left: 10px;
            font-size: 19px;
            margin-bottom: 10px;
            border-radius: 6px;
            border: 1px solid rgba(78, 78, 78, 0.5);
            height: 40px;
        }

        .input_1 {
            width: 190px;
        }

        .input_2 {
            width: 385px;
        }

        select {
            margin-top: 6px;
            padding: 6px;
            margin-left: -6px
        }

        .why {
            margin: -30px 0 0 195px;
            font-size: 10px;
            font-family: sans-serif;
        }

        .terms {
            font-size: 10px;
            width: 306px;
            margin-top: 10px;
            margin-bottom:30px;
            color: rgba(0, 0, 0, 0.6)
        }

        .terms_a {
            text-decoration: none;
            color: #32549b;
        }

        .signUp {
            margin-top:100px;
            margin-bottom:5px;
            font-family: 'Open Sans',sans-serif;
            text-rendering:optimizelegibility;
            color:#333;
        }

        .signUpSub {
            font-size:16px;
            margin-bottom:20px;
            color:#333;
        }

        .signUpBtn {
            text-decoration:none;
            font-size: 19px;
            font-weight: 700;
            letter-spacing: 1px;
            color:#fff;
            min-width: 194px;
            padding: 7px 20px;
            text-align: center;
            -webkit-border-radius: 5px;
            background: -webkit-linear-gradient(#67ae55, #578843);
            -webkit-box-shadow: inset 0 1px 1px #a4e388;
            border: 1px solid;
            border-color: #3b6e22 #3b6e22 #2c5115;
            margin-top: 10px;
            text-shadow: 0 1px 2px rgba(0,0,0,.5);
            cursor:pointer;
        }

        .signUpBtn:hover {
            background: -webkit-linear-gradient(#79bc64, #578843);
        }

        .signUpBtn:active {
            position: relative;
            top: 1px;
        }

        .formbox {
            display: inline-block;
            width:399px;
        }

        .create {
            border-top: 1px solid #a0a9c0;
            color: #666;
            font-size: 13px;
            font-weight: bold;
            margin-top: 30px;
            margin-bottom:30px;
            padding-top: 15px;
        }

        .create a {
            text-decoration:none;
        }

        .male, .female {
            font-size:16px;
        }
    </style>

</head>

<body>
<header>
    <div class="logo">facebook</div>
    <div class="container">
        <form class="formulaire" action="${pageContext.request.contextPath}/LoginServlet" method="POST">
            <div class="userMail">
                <label for="mail">Email or Phone</label>
                <input type="text" name="email" id="mail" />
            </div>
            <div class="password">
                <label for="password">Password</label>
                <input type="password" name="password" id="password" />
            </div>
            <input type="submit" value="Login" class="btn" />
        </form>
        <div class="forgot"><a href="#">Forgot account?</a></div>
    </div>
</header>
<div class="left">
    <h2 class="connect">Connect with friends and the
        <br>
        world around you on Facebook.</h2>
    <ul class="center_list">
        <li class="list">
            <img class="img-list" src="https://fb-s-d-a.akamaihd.net/h-ak-xft1/v/t39.2365-6/851565_602269956474188_918638970_n.png?oh=894e994a5ae3ec3434da54fe42e31e72&oe=5971E6F5&__gda__=1500718098_effe0b8bd493ae0c23cc14fa99bda630">
            <big>See photos and updates</big> <small>from friends in News Feed.</small>
        </li>
        <li class="list">
            <img class="img-list" src="https://fb-s-c-a.akamaihd.net/h-ak-xaf1/v/t39.2365-6/851585_216271631855613_2121533625_n.png?oh=ae821220803265e80c0c261997b83a1b&oe=59295120&__gda__=1499782408_8fd3d0bad1815952ce6195f4e9c363df">
            <big>Share what's new</big> <small>in your life on your Timeline.</small>
        </li>
        <li class="list">
            <img class="img-list last" src="https://fb-s-b-a.akamaihd.net/h-ak-xft1/v/t39.2365-6/851558_160351450817973_1678868765_n.png?oh=8de3304d724a879592bcc3fe9f967dcd&oe=5959B4D8&__gda__=1499970870_5087f0141d0ed913a7092687c8c10213">
            <big>Find more</big> <small>of what you're looking for with Facebook Search.</small>
        </li>
    </ul>
</div>
<div class="right">
    <h1 class="signUp">Sign Up</h1>
    <h4 class="signUpSub">It’s free and always will be.</h4>


    <form action="${pageContext.request.contextPath}/RegisterServlet" method="GET">
        <div class="inputs">
            <input type="text" name="fName" class="input input_1" placeholder="First name">
            <input type="text" name="lName" class="input input_1" placeholder="Last name">
            <br>
            <input type="text" class="input input_2" placeholder="Email Address">
            <br>
            <input type="text" name="email" class="input input_2" placeholder="Re-enter Email Address">
            <br>
            <input type="password" name="password" class="input input_2" placeholder="New password">
        </div>
        <div>
            <h4>Birthday</h4>
            <input type="date" name="dateOfBirth">
            <div class="why"><a style="text-decoration:none; color:#3c6ed6;" href="#">Why do I need to provide <br>my birthday?</a></div>
        </div>
        <div style="margin-top: 20px;">
            <input id="female" name="gender" value="female" type="radio">
            <label class="female" for="female">Female</label>
            <input id="male" name="gender" value="male" type="radio">
            <label class="male" for="male">Male</label>
        </div>
        <div class="terms">
            <p>By clicking Sign Up, you agree to our <a href="#" class="terms_a">Terms</a> and that you have read our <a href="#" class="terms_a">Data Policy</a>, including our <a href="#" class="terms_a">Cookie Use</a>.</p>
        </div>
        <div>
            <input type="submit" value="Create Account" class="signUpBtn"/>
        </div>
    </form>
    <div class="formbox">
        <div class="create"><a href="">Create a Page</a> for a celebrity, band or business.</div>
    </div>
</div>
</body>

</html>















<%--<html lang="en">--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
<%--    <title>Index</title>--%>
<%--    <style>--%>

<%--    </style>--%>
<%--</head>--%>
<%--<body>--%>
<%--    <h1 class="welcome">Welcome!</h1>--%>
<%--        </br></br>--%>
<%--    <button onclick="window.location.href='register.jsp'; return false;">Register</button>--%>
<%--    <!-- <input type="button" value="Register" onclick="window.location.href='register.jsp'; return false;">-->--%>
<%--    <button onclick="window.location.href='login.jsp'; return false;">Log In</button>--%>
<%--</body>--%>
<%--</html>--%>
