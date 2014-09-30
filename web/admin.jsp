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
        <link href='http://fonts.googleapis.com/css?family=Shadows+Into+Light' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css'>
        <link href="styles/styles.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>

        <title>Admin</title>
        <style>
            #adminSelectionMenu{
                border-bottom: 1px solid black;
                padding-bottom: 5px;
                margin-bottom: 15px;
                padding: 5px;
                color: white;
                background: rgba(0,0,0,.8);
            }

            .adminRadioBtn, #adminSelectionMenu label, .adminSubmitButton{
                cursor: pointer;
            }

            .adminForm{
                width: 350px;
                border-bottom: 1px solid black;
                padding-bottom: 10px;
                color: white;
                background: rgba(0,0,0,.5);
                padding: 5px;
                overflow: hidden;
            }

            .fieldWrapper{
                position: relative;
                margin-bottom: 8px;
            }

            .fieldWrapper label{
                position: absolute;
                padding: 3px;
                width: 120px;
            }

            .fieldWrapper input, textarea{
                position: relative;
                margin-left: 130px;
                width: 180px;
                max-width: 200px;
            }

            #itemImage{
                width: 210px;
            }

            .adminSubmitButton{
                width: 70px;
                height: 40px;
                font-size: 18px;
            }

            #showAllForm{
                width: 95%;
            }

            #allResults{
                font-family: arial;
                background: black;
                padding: 5px;
            }

            #updateNewItemButton{
                width: 70px;
                height: 40px;
                font-size: 18px;
            }

            #tesingArea{
                background: rgba(0,0,0,.8);
                color: white;
                border: 1px solid yellow;
            }
        </style>
        <script>
            $(function () {
                function hideForms() {
                    $('#addItemForm').hide();
                    $('#removeItemForm').hide();
                    $('#updateItemForm').hide();
                    $('#showAllForm').hide();
                    $('#clearAllForm').hide();
                    $('#updateNewItemInfo').hide();
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
                        case "showAll":
                            $('#showAllForm').show('slow');
                            break;
                        case "clearAll":
                            $('#clearAllForm').show('slow');
                            break;
                        default:
                            alert("whoops");
                    }
                });

                $('#updateNewItemButton').click(function () {
                    $('#updateNewItemInfo').show('slow');
                });
            });
        </script>

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
        <%
         //   Object key = request.getAttribute("keyValid");
            //    String theKey = (String) key;
            //     if (key == null) {
        %>    

        <nav id='adminSelectionMenu'>
            <input id='add' class="adminRadioBtn" type="radio" name="adminRadioBtn" value="add">
            <label for="add">Add item</label>
            <input id='remove' class="adminRadioBtn" type="radio" name="adminRadioBtn" value="remove">
            <label for="remove">Remove item</label>
            <input id='update' class="adminRadioBtn" type="radio" name="adminRadioBtn" value="update">
            <label for="update">Update Item</label>
            <input id='showAll' class="adminRadioBtn" type="radio" name="adminRadioBtn" value="showAll">
            <label for="showAll">Show All Items</label>
            <input id='clearAll' class="adminRadioBtn" type="radio" name="adminRadioBtn" value="clearAll">
            <label for="showAll">Clear All Items</label>
        </nav>

        <!-- ADD ITEM FORM -->
        <form id='addItemForm' name='addItemForm' class='adminForm' method="post" action='AdminController?adminCommand=addItem'>
            <h3>Add Item</h3><br>
            <div class='fieldWrapper'>
                <label for="itemName">Item Name: </label>
                <input type='text' name='itemName' id='itemName' class='inputBox'/>
            </div>
            <div class='fieldWrapper'>
                <label for="itemPrice">Item price: </label>
                <input type='text' name='itemPrice' id='itemPrice' class='inputBox'/>
            </div>
            <div class='fieldWrapper'>
                <label for="itemCalories">Item calories: </label>
                <input type='text' name='itemCalories' id='itemCalories' class='inputBox'/>
            </div>
            <div class='fieldWrapper'>
                <label for="itemDescription">Description: </label>
                <textarea type='text' name='itemDescription' id='itemDescription' class='inputBox'></textarea>
            </div>
            <div class='fieldWrapper'>
                <label for="itemImage">Choose picture:</label>
                <input type='file' id='itemImage' name='itemImage' class='inputBox'/><br><br>
            </div>
            <!-- <input type="reset" id='reset' name='reset' class='button' value='reset'/>-->
            <input type='submit' id='submitAdd' name='submit' class='adminSubmitButton' value='Add'/>
        </form>

        <!-- REMOVE ITEM FORM -->
        <form id='removeItemForm' name='removeItemForm' class='adminForm' method="post" action='AdminController?adminCommand=removeItem'>
            <h3>Remove Item</h3><br>
            <div class='fieldWrapper'>
                <label for="itemId">Item ID: </label>
                <input type='text' name='itemId' id='itemIdRemove' class='inputBox'/>
            </div>
            <div class='fieldWrapper'>
                &nbsp&nbsp <i>- or -</i>
            </div>
            <div class='fieldWrapper'>
                <label for="itemName">Item Name: </label>
                <input type='text' name='itemName' id='itemNameRemove' class='inputBox'/>
            </div>
            <br>
            <br>
            <input type='submit' id='submitRemove' name='submit' class='adminSubmitButton' value='Remove'/>
        </form>

        <!-- UPDATE ITEM FORM -->
        <form id='updateItemForm' name='updateItemForm' class='adminForm' method="post" action='AdminController?adminCommand=updateItem'>
            <h3>Update Item</h3><br>
            <div class='fieldWrapper'>
                <label for="itemId">Old Item ID: </label>
                <input type='text' name='itemId' id='itemIdUpdate' class='inputBox'/>
            </div>
            <div class='fieldWrapper'>
                &nbsp&nbsp <i>- or -</i>
            </div>
            <div class='fieldWrapper'>
                <label for="itemName">Old Item Name: </label>
                <input type='text' name='itemName' id='itemNameUpdate' class='inputBox'/>
            </div>
            <br><hr><br>
            <button id='updateNewItemButton' type='button' >New Item</button>
            <div id='updateNewItemInfo'>
                <div class='fieldWrapper'>
                    <label for="itemNameUpdate">Item Name: </label>
                    <input type='text' name='itemNameUpdate' id='itemNameUpdate' class='inputBox'/>
                </div>
                <div class='fieldWrapper'>
                    <label for="itemPriceUpdate">Item price: </label>
                    <input type='text' name='itemPriceUpdate' id='itemPriceUpdate' class='inputBox'/>
                </div>
                <div class='fieldWrapper'>
                    <label for="itemCaloriesUpdate">Item calories: </label>
                    <input type='text' name='itemCaloriesUpdate' id='itemCaloriesUpdate' class='inputBox'/>
                </div>
                <div class='fieldWrapper'>
                    <label for="itemDescriptionUpdate">Description: </label>
                    <textarea type='text' name='itemDescriptionUpdate' id='itemDescriptionUpdate' class='inputBox'></textarea>
                </div>
                <div class='fieldWrapper'>
                    <label for="itemImageUpdate">Choose picture:</label>
                    <input type='file' id='itemImageUpdate' name='itemImageUpdate' class='inputBox'/><br><br>
                </div>
                <br>
                <br>
                <input type='submit' id='submitUpdate' name='submit' class='adminSubmitButton' value='submit'/>
            </div>

        </form>

        <!-- SHOW ALL FROM DB FORM -->
        <form id='showAllForm' name='showAllForm' class='adminForm' method="post" action='AdminController?adminCommand=showAll'>
            <h3>Show all items</h3><br>
            <br><br>
            <input type='submit' id='submitShowAll' name='submit' class='adminSubmitButton' value='submit'/>
            <br>
            <br>
            <div id='allResults'>
                <c:forEach var='item' items="${dbContent}">
                    ${item}<br><hr>
                </c:forEach>
            </div>
        </form>

        <!-- CLEAR ALL FROM DB FORM -->
        <form id='clearAllForm' name='clearAllForm' class='adminForm' method="post" action='AdminController?adminCommand=clearAll'>
            <h3>Remove/Reset all items</h3><br>
            <br><br>
            <input type='submit' id='submitClearAll' name='submit' class='adminSubmitButton' value='submit'/>
            <br>
            <br>
        </form>
        <%
           // }
        %>
        <p id='tesingArea'>
            <br>
            This area is for testing:<br>
            ${responseDisplay}

        </p>
    </p>
</body>
</html>
