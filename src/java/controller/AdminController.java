/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

        HttpSession session = request.getSession();
        RequestDispatcher view;
        
        RestaurantService service;

        String command = request.getParameter("adminCommand");
        String responseDisplay = "";

        if (session.getAttribute("service") == null) {
            service = new RestaurantService(new MenuItemDAO(new MySQLDB()));
        } else {
            service = (RestaurantService) session.getAttribute("service");
        }

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
                responseDisplay = "Item added.";
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
                List<MenuItem> allData = service.getAllMenuItems();
                request.setAttribute("dbContent", allData);
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

     // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
