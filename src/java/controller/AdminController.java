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
import model.DataAccessService;
import model.DataAccessStrategy;
import model.FakeDB;
import model.FakeDBSingleton;
import model.MenuItem;

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

        List<MenuItem> all = new ArrayList<>();
        String responseDisplay = "";

        DataAccessService db;
        Object obj = session.getAttribute("dbUsed");
        //  request.setAttribute("keyValid", key1);
        // if (key != null && key1.equals(getServletContext().getInitParameter("door"))) {

        if (obj != null) {
            db = (DataAccessService) obj;
        } else {
            String dbUsed = getServletContext().getInitParameter("dataAccess");
            db = new DataAccessService(dbUsed);
        }
        //DataAccessStrategy db = FakeDBSingleton.getNewInstance();
        Integer itemId;
        String itemName;
        MenuItem item = null;
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

                db.addMenuItem(item);
                responseDisplay = "Item added. ID: " + item.getId();

                break;
            case "removeItem":
                MenuItem remvItem = new MenuItem();
                itemName = request.getParameter("itemName");
                itemId = Integer.parseInt(request.getParameter("itemId"));
                for (MenuItem mi : db.getAllMenuItems()) {
                    if (itemId.equals(mi.getId())) {
                        remvItem = mi;
                    }
                }

                responseDisplay = "Item removed. - " + remvItem.getItemName();
                db.removeMenuItem(remvItem);
                break;
            case "updateItem":
                MenuItem updateItemOld = new MenuItem();
                MenuItem updateItemNew = new MenuItem();
                itemId = Integer.parseInt(request.getParameter("itemId"));
                for (MenuItem mi : db.getAllMenuItems()) {
                    if (itemId.equals(mi.getId())) {
                        updateItemOld = mi;
                    }
                }

                String itemNameUpdate = request.getParameter("itemNameUpdate");
                String itemPriceUpdate = request.getParameter("itemPriceUpdate");
                String itemCaloriesUpdate = request.getParameter("itemCaloriesUpdate");
                String itemDescriptionUpdate = request.getParameter("itemDescriptionUpdate");
                String itemImageUpdate = request.getParameter("itemImageUpdate");
                itemImageUpdate = request.getServletContext().getInitParameter("imagePath") + itemImageUpdate;

                updateItemNew.setItemName(itemNameUpdate);
                updateItemNew.setItemPrice(itemPriceUpdate);
                updateItemNew.setItemCalories(itemCaloriesUpdate);
                updateItemNew.setItemDescription(itemDescriptionUpdate);
                updateItemNew.setItemPicture(itemImageUpdate);

                db.updateMenuItem(updateItemOld, updateItemNew);
                break;
            case "showAll":
                all = db.getAllMenuItems();
                request.setAttribute("dbContent", all);
                responseDisplay = "List of all db items.";
                break;
            case "clearAll":
                db.clearAllItems();
                String dbUsed = getServletContext().getInitParameter("dataAccess");
                db = new DataAccessService(dbUsed);
                responseDisplay = "Items cleared.";
                break;
            default:
        }
            //To quick clear data use below
        //db.clearAllItems();

        // LinkedHashMap<Integer, LinkedHashMap<String, MenuItem>> allMap = db.getAllMenuItemsMap();
        request.setAttribute("responseDisplay", responseDisplay);
        session.setAttribute("dbUsed", db);
        // all = db.getAllMenuItems();
        //  request.getSession().setAttribute("dbContent", all);
        //   request.getSession().setAttribute("dbMap", all);
        //  }

        view = request.getRequestDispatcher("/admin.jsp");
        view.forward(request, response);
        //response.sendRedirect("admin.jsp");

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
