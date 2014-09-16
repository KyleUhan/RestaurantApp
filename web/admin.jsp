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
        <script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>
        <title>Admin</title>
        <style>
            #adminSelectionMenu{
                border-bottom: 1px solid black;
                padding-bottom: 5px;
                margin-bottom: 15px;
            }

            .adminRadioBtn, #adminSelectionMenu label{
                cursor: pointer;
            }

            .adminForm{
                width: 200px;
                border-bottom: 1px solid black;
                padding-bottom: 10px;
            }

            .adminForm *{
                display: inline-block;
            }
        </style>
        <script>
            $(function () {
                function hideForms() {
                    $('#addItemForm').hide();
                    $('#removeItemForm').hide();
                    $('#updateItemForm').hide();
                }
                hideForms();

                $('input:radio[name="adminRadioBtn"]').change(function () {
                    hideForms();
                    var name = $(this).val();
                    switch (name) {
                        case "add":
                            $('#addItemForm').show('slow');
                            break;
                        case "remove":
                            $('#removeItemForm').show('slow');
                            break;
                        case "update":
                             $('#updateItemForm').show('slow');
                            break;
                        default:
                            alert("whoops");
                    }
                });
            });
        </script>

    </head>
    <body>
        <h1>Admin</h1>
        <nav id='adminSelectionMenu'>
            <input id='add' class="adminRadioBtn" type="radio" name="adminRadioBtn" value="add">
            <label for="add">Add item</label>
            <input id='remove' class="adminRadioBtn" type="radio" name="adminRadioBtn" value="remove">
            <label for="remove">Remove item</label>
            <input id='update' class="adminRadioBtn" type="radio" name="adminRadioBtn" value="update">
            <label for="update">Update Item</label>
        </nav>

        <!-- ADD ITEM FORM -->
        <form id='addItemForm' name='addItemForm' class='adminForm' method="post" action='AdminController'>
            <h3>Add Item</h3><br>
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
           <!-- <input type="reset" id='reset' name='reset' class='button' value='reset'/>-->
            <input type='submit' id='submit' name='submit' class='button' value='submit'/>
        </form>

        <!-- REMOVE ITEM FORM -->
        <form id='removeItemForm' name='removeItemForm' class='adminForm' method="post" action='AdminController'>
            <h3>Remove Item</h3><br>
            <label for="itemId">Item ID: </label>
            <input type='text' name='itemId' id='itemId' class='inputBox'/>
            <label for="itemName">Item Name: </label>
            <input type='text' name='itemName' id='itemName' class='inputBox'/>
            <input type='submit' id='submit' name='submit' class='button' value='submit'/>
        </form>

        <!-- UPDATE ITEM FORM -->
        <form id='updateItemForm' name='updateItemForm' class='adminForm' method="post" action='AdminController'>
            <h3>Update Item</h3><br>
            <label for="itemId">Item ID: </label>
            <input type='text' name='itemId' id='itemId' class='inputBox'/>
            <!--<label for="itemName">Item Name: </label>
            <input type='text' name='itemName' id='itemName' class='inputBox'/>
            <label for="itemPrice">Item price: </label>
            <input type='text' name='itemPrice' id='itemPrice' class='inputBox'/>
            <label for="itemCalories">Item calories: </label>
            <input type='text' name='itemCalories' id='itemCalories' class='inputBox'/>
            <label for="itemDescription">Description: </label>
            <textarea type='text' name='itemDescription' id='itemDescription' class='inputBox'></textarea>
            <label for="itemImage">Choose picture:</label>
            <input type='file' id='itemImage' name='itemImage' class='inputBox'/>-->
            <br><br>
            <input type='submit' id='submit' name='submit' class='button' value='submit'/>
        </form>
        <p>
            <br>
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
