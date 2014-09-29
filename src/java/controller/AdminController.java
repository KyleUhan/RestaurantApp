/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.FakeDBSingleton;
import model.MenuItem;
import model.MenuItemDAO;
import model.MySQLDB;
import model.RestaurantService;

/**
 *
 * @author Kyle
 */
@WebServlet(name = "AdminController", urlPatterns = {"/AdminController"})
public class AdminController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(true);
        RequestDispatcher view;
        String command = request.getParameter("adminCommand");
        RestaurantService service;

        List<MenuItem> allData;
        String responseDisplay = "";

        if (session.getAttribute("service") == null) {
            service = new RestaurantService(new MenuItemDAO(new MySQLDB()));
        } else {
            service = (RestaurantService) session.getAttribute("service");
        }
        //  request.setAttribute("keyValid", key1);
        // if (key != null && key1.equals(getServletContext().getInitParameter("door"))) {

        //DataAccessStrategy db = FakeDBSingleton.getNewInstance();
        Integer itemId;
        String itemName;
        MenuItem item;
        switch (command) {
            case "addItem":
                item = new MenuItem();
                itemName = request.getParameter("itemName");
                String itemPrice = request.getParameter("itemPrice");
                String itemCalories = request.getParameter("itemCalories");
                String itemDescription = request.getParameter("itemDescription");
                String itemImage = request.getParameter("itemImage");
                itemImage = request.getServletContext().getInitParameter("imagePath") + itemImage;

                item.setItemName(itemName);
                item.setItemPrice(itemPrice);
                item.setItemCalories(itemCalories);
                item.setItemDescription(itemDescription);
                item.setItemPicture(itemImage);
                service.addMenuItem(item);
                responseDisplay = "Item added. ID: " + item.getId();
                break;
            case "removeItem":
                itemId = Integer.parseInt(request.getParameter("itemId"));
                service.removeMenuItem(itemId);
                responseDisplay = "Item removed.";
                break;
            case "updateItem":
                item = new MenuItem();
                itemId = Integer.parseInt(request.getParameter("itemId"));

                String itemNameUpdate = request.getParameter("itemNameUpdate");
                String itemPriceUpdate = request.getParameter("itemPriceUpdate");
                String itemCaloriesUpdate = request.getParameter("itemCaloriesUpdate");
                String itemDescriptionUpdate = request.getParameter("itemDescriptionUpdate");
                String itemImageUpdate = request.getParameter("itemImageUpdate");
                itemImageUpdate = request.getServletContext().getInitParameter("imagePath") + itemImageUpdate;

                item.setItemName(itemNameUpdate);
                item.setItemPrice(itemPriceUpdate);
                item.setItemCalories(itemCaloriesUpdate);
                item.setItemDescription(itemDescriptionUpdate);
                item.setItemPicture(itemImageUpdate);

                service.updateMenuItem(itemId, item);
                break;
            case "showAll":
             /*   allData = service.getAllMenuItems();
                request.setAttribute("dbContent", allData);*/
                responseDisplay = "List of all db items.";
                break;
            case "clearAll":
                service.clearAllMenuItem();
                responseDisplay = "Items cleared.";
                break;
            default:
        }

        request.setAttribute("responseDisplay", responseDisplay);

        view = request.getRequestDispatcher("/admin.jsp");
        view.forward(request, response);

    }

}
