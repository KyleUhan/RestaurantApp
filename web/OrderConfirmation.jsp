<%-- 
    Document   : OrderConfirmation
    Created on : Sep 12, 2014, 2:21:54 PM
    Author     : Kyle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='http://fonts.googleapis.com/css?family=Shadows+Into+Light' rel='stylesheet' type='text/css'>
        <title>Confirm Order</title>
        <style>
            *{
                margin: 0;
                padding: 0;
                font-family: 'Shadows Into Light', cursive;
            }
            
            h1{
                margin-left: 5%;
            }

            body{  
                background: rgba(0,127,124,.1);
            }

            #orderWrapper{
                position: fixed;
                width: 90%;
                height: 80%;
                background:black;
                margin-left: 5%;
            }
        </style>
    </head>
    <body>
        <h1>Confirm Order</h1>
        <div id='orderWrapper'>

        </div>
    </body>
</html>
