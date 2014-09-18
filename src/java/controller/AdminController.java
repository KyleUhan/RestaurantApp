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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

        HttpSession session = request.getSession();

        DataAccessStrategy db = FakeDBSingleton.getNewInstance();

        MenuItem item = new MenuItem();
        String itemName = request.getParameter("itemName");
        String itemPrice = request.getParameter("itemPrice");
        String itemCalories = request.getParameter("itemCalories");
        String itemDescription = request.getParameter("itemDescription");
        String itemImage = request.getParameter("itemImage");

        item.setItemName(itemName);
        item.setItemPrice(itemPrice);
        item.setItemCalories(itemCalories);
        item.setItemDescription(itemDescription);
        item.setItemPicture(itemImage);

        db.addMenuItem(item);
        //To quick clear data use below
       // db.clearAllData();

        List<MenuItem> all = db.getAllMenuItems();
        session.setAttribute("dbContent", all);
        LinkedHashMap<Integer, LinkedHashMap<String,MenuItem>> allMap = db.getAllMenuItemsMap();
        
        
        session.setAttribute("dbMap", allMap);
        response.sendRedirect("admin.jsp");

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
