<%-- 
    Document   : admin
    Created on : Sep 12, 2014, 1:35:58 PM
    Author     : Kyle
--%>

<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
        <style>
            .adminForm{
                width: 200px;
            }

            #addItemForm *{
                display: inline-block;
            }
        </style>
    </head>
    <body>
        <h1>Admin</h1>
        <form id='addItemForm' name='addItemForm' class='adminForm' method="post" action='AdminController'>
            <label for="itemName">Item Name: </label>
            <input type='text' name='itemName' id='itemName' class='inputBox'/>
            <label for="itemPrice">Item price: </label>
            <input type='text' name='itemPrice' id='itemPrice' class='inputBox'/>
            <label for="itemCalories">Item calories: </label>
            <input type='text' name='itemCalories' id='itemCalories' class='inputBox'/>
            <label for="itemDescription">Description: </label>
            <textarea type='text' name='itemDescription' id='itemDescription' class='inputBox'></textarea>
            <label for="itemImage">Choose picture:</label>
            <input type='file' id='itemImage' name='itemImage' class='inputBox'/><br><br>
            <input type="reset" id='reset' name='reset' class='button' value='reset'/>
            <input type='submit' id='submit' name='submit' class='button' value='submit'/>
        </form>
        <p>
            Stuff here is for testing:<br>
            <%
                Object obj = session.getAttribute("allItems");
                if (obj != null) {

                }

            %>
        <p>
        <p>
            ${dbMap};
        </p>
        </p>
    </body>
</html>
