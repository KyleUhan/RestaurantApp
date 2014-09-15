<%-- 
    Document   : index
    Created on : Sep 11, 2014, 1:23:14 AM
    Author     : Kyle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='http://fonts.googleapis.com/css?family=Shadows+Into+Light' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css'>
        <link href="styles/styles.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="js/js.js" type="text/javascript"></script>
        <title>Ummm Ummm Good</title>

    </head>
    <body>
        <img src="images/MainBG4.jpg" id="bg" alt="">
        <header>
            <h1> - Kyle's Cafe -</h1>
            <div id='loginWrapper'>
                <span id='userName'></span>
                <span id='login'>Login</span>
            </div>
        </header>
        <div id='menuDisplay'>
            <img id='pictureLeft' class='menuOption' src="">
            <img id='pictureCenter' class='menuOption' src="">
            <img id='pictureRight' class='menuOption' src="">
            <div id='leftControl' class='control'></div>
            <div id='rightControl' class='control'></div>
        </div>
        <div id='detailsWrapper'>
            <div id='details'>
                <span id='detailsTitle'>Pick a meal and add it to your order</span><br>
                This is a sample of the details.<br>
                Buy this, it's delicious.
                <div id='addToListButton'>
                    <span>
                        ADD
                    </span>
                </div>
            </div>
        </div>
        <form id='orderMenuForm' name='orderMenuForm' method='post' action='MainController'>

        </form>
    </body>
</html>
