<%-- 
    Document   : OrderConfirmation
    Created on : Sep 12, 2014, 2:21:54 PM
    Author     : Kyle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='http://fonts.googleapis.com/css?family=Shadows+Into+Light' rel='stylesheet' type='text/css'>
        <link href="styles/styles.css" rel="stylesheet" type="text/css"/>
        <title>Confirm Order</title>
    </head>
    <body>
        <img src="images/background3.gif" id="bg" alt="">
        <h1>Confirm Order</h1>
        <div id='orderWrapper'>

            <div class="blockOfInfo">
                <c:forEach var="itemName" items="${itemName}">
                    ${itemName}&nbsp&nbsp;<br>
                </c:forEach>
            </div>
            <div class="blockOfInfo">
                <c:forEach var="itemQnty" items="${qnty}">
                    ${itemQnty}&nbsp;&nbsp;<br>
                </c:forEach>
            </div>
            <div class="blockOfInfo">
                <c:forEach var="price" items="${pricePerItem}">
                    ${price}<br>
                </c:forEach>
            </div>
            <div id='totals'>
                <label for='tax'>Tax:</label>
                <input type="text" readonly="true" id='tax' class='addedAmounts' value="${tax}"/>
                <div id='totalWrapper'>
                    <label for='total'>Total:</label>
                    <input type="text" readonly="true" id='total' class='addedAmounts'value="${total}"/>
                </div>
            </div>

        </div>
    </body>
</html>
