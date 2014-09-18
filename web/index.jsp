<%-- 
    Document   : index
    Created on : Sep 11, 2014, 1:23:14 AM
    Author     : Kyle
--%>

<%@page import="model.MenuItem"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    //LIST OF ACTUAL MENU ITEMS
    List<MenuItem> menuItems = new ArrayList<MenuItem>();
    //LISTS OF EACH SPECIFIC PORTION OF MENUITEM NEEDEED.
    List<String> menuImages = new ArrayList<String>();
    List<String> menuName = new ArrayList<String>();
    List<String> menuDescription = new ArrayList<String>();
    List<Double> menuPrice = new ArrayList<Double>();
    List<Double> menuCalorie = new ArrayList<Double>();
    
    Object obj = request.getAttribute("menuItems");
    
    if (obj == null) {
        response.sendRedirect("MainController?command=getMenuItems");
    } else {
        menuItems = (List<MenuItem>) obj;
        for (MenuItem mi : menuItems) {
            menuImages.add(mi.getItemPicture());
            menuName.add(mi.getItemName());
            menuDescription.add(mi.getItemDescription());
            menuPrice.add(mi.getItemPrice());
            menuCalorie.add(mi.getItemCalories());
        }
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='http://fonts.googleapis.com/css?family=Shadows+Into+Light' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css'>
        <link href="styles/styles.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="js/js.js" type="text/javascript"></script>
        <title>Ummm Ummm Good</title>
        <script>
            
            var menuOptionPics = ('<%=menuImages%>').split(",");
            var menuItemName = '<%=menuName%>'.split(",");
            var menuItemDescription = '<%=menuDescription%>'.split(",");
            var menuItemPrice = '<%=menuPrice%>'.split(",");

        </script>
    </head>
    <body>
        <img src="images/background3.gif" id="bg" alt="">
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
                <span id='detailsTitle'>
                    Welcome to the cafe!
                </span><br>
                <div id='detailsDescription'>
                </div><br>
                <div id='detailsPrice'>
                </div>
                <div id='addToListButton'>
                    <span>
                        ADD
                    </span>
                </div>
            </div>
        </div>
        <form id='orderMenuForm' name='orderMenuForm' method='post' action='MainController?command=orderReady'> 
        </form>
    </body>
</html>
