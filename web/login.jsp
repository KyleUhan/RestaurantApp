<%-- 
    Document   : login
    Created on : Sep 14, 2014, 12:27:19 AM
    Author     : Kyle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="styles/styles.css" rel="stylesheet" type="text/css"/>
        <link href='http://fonts.googleapis.com/css?family=Shadows+Into+Light' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css'>
        <script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>

        <title>Login</title>
        <%

            
        %>
        <style>
            #loginForm{
                position: fixed;
                background: rgba(0,0,0,.7);
                top: 50%;
                margin-top: -150px;
                left: 50%;
                margin-left: -200px;
                width: 400px;
                height: 300px;

            }

            #loginInnerFormWrapper{
                position: absolute;
                background: rgb(204,187,172);
                width: 80%;
                height: 80%;
                left: 10%;
                top: 10%;
                border-top: 1px solid black;
                border-left: 1px solid black;
                border-radius: 3px;
                -webkit-box-shadow: 10px 10px 10px #000;
                -moz-box-shadow: 10px 10px 10px #000;
                box-shadow: 10px 10px 10px #000;
            }

            .loginInputWrapper{
                position: relative;
                margin-top: 10%;
                margin-bottom: 20px;
                text-align: center;
            }

            .loginInputWrapper label{
                float: left;
                width:100px;
                font-size: 18px;
            }

            .loginInput{
                outline: none;
                border: none;
                padding-left: 5px;
                font-size: 18px;
                width: 180px;
                background: rgba(255,255,255,1);
                //  background: rgba(81,58,45,1);
            }

            #loginFormButton{
                position: absolute;
                right: 10%;
                width: 100px;
                height: 40px;
                cursor: pointer;
                font-size: 18px;

            }
        </style>
    </head>
    <body>
        <img src='<%out.print(getServletContext().getInitParameter("background"));%>' id="bg" alt="">
        <header>
            <a href='index.jsp'><h1> - <% out.print(getServletContext().getInitParameter("title")); %> -</h1></a>
            <form id='loginWrapper' name='loginWrapper' method='post' action='MainController?command=login'>
                <span id='userName'></span>
                <input type='submit' id='login' value='<% out.print(getServletContext().getInitParameter("login"));%>'></input>
            </form>
        </header>

        <form id='loginForm' name='loginForm' method='post' action="LoginController?action=login">
            <div id='loginInnerFormWrapper'>
                <div class='loginInputWrapper' id='userNameWrapper'>
                    <label for='userName' >User Name: </label>
                    <input type="text" id='userName' name='userName' class='loginInput' required/>
                </div>
                <div class='loginInputWrapper'>
                    <label for='userPass'>Password: </label>
                    <input type="password" id='userPass' name='userPass' class='loginInput' required/>
                </div>
                <input type='submit' value='Login' id='loginFormButton'/>
            </div>

        </form>
    </body>
</html>
