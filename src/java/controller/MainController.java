/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CalculateOrderTotal;
import model.DataAccessService;
import model.MenuItem;
import model.MenuItemDAO;
import model.MockDB;
import model.MySQLDB;
import model.RestaurantService;

/**
 *
 * @author Kyle
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

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

        String redirectPage = "";
        RequestDispatcher view;
      //  DataAccessService accessService;
        RestaurantService service;

        String command = request.getParameter("command");

        switch (command) {
            case "getMenuItems":
                redirectPage = getServletContext().getInitParameter("index");

                service = new RestaurantService(new MenuItemDAO(new MySQLDB()));

               /* service.addMenuItem(new MenuItem("", "", "", "", "images/fillerItemFadeL.png"));
                service.addMenuItem(new MenuItem("IMPORTED COFFEE", "2.99", "100", "Fine imported coffee. Molto Bene!", "images/coffee2.png"));
                service.addMenuItem(new MenuItem("LATTE", "3.49", "210", "Thanks a Latte...", "images/coffee3.jpg"));
                service.addMenuItem(new MenuItem("HOUSE COFFEE", "1.99", "65", "Our famous home made coffee!", "images/coffeeSized.jpg"));
                service.addMenuItem(new MenuItem("DESSERT COFFEE", "3.95", "480", "Calorie counters go away.", "images/coffee5.jpg"));
                service.addMenuItem(new MenuItem("MUFFIN", "3.55", "225", "These muffins are out of control.", "images/muffinSized.jpg"));
                service.addMenuItem(new MenuItem("", "", "", "", "images/fillerItemFadeR.png"));*/

                List<MenuItem> menuItems = service.getAllMenuItems();
                request.setAttribute("menuItems", menuItems);

                break;
            case "orderReady":
                redirectPage = getServletContext().getInitParameter("confirmOrder");

                List<String> itemNames = new ArrayList<>();
                List<String> qnty = new ArrayList<>();
                List<String> itemAmountOwed = new ArrayList<>();

                String totalItems = request.getParameter("amountOfItems");

                for (int i = 0; i < (Integer.parseInt(totalItems) + 1); i++) {
                    itemNames.add(request.getParameter("itemName" + i));
                    qnty.add(request.getParameter("quantity" + i));
                    itemAmountOwed.add(request.getParameter("totalOwedPerItem" + i));
                }
                CalculateOrderTotal ct = new CalculateOrderTotal(Double.valueOf(getServletContext().getInitParameter("tax")));
                String total = ct.calculateTotal(itemAmountOwed);
                String tax = ct.getAdjustedTax();

                request.setAttribute("itemName", itemNames);
                request.setAttribute("qnty", qnty);
                request.setAttribute("pricePerItem", itemAmountOwed);
                request.setAttribute("tax", tax);
                request.setAttribute("total", total);

                break;
            case "orderComplete":
                redirectPage = getServletContext().getInitParameter("confirmOrder");
                String complete = "Complete";

                request.setAttribute("itemName", null);
                request.setAttribute("qnty", null);
                request.setAttribute("pricePerItem", null);
                request.setAttribute("tax", null);
                request.setAttribute("total", null);
                request.setAttribute("complete", complete);

                String thankyou = "Thankyou for your order";
                request.setAttribute("thanks", thankyou);

                break;
            case "sendToMenu":
                redirectPage = getServletContext().getInitParameter("index");
                break;
            case "login":
                redirectPage = getServletContext().getInitParameter("loginPage");
                break;
            case "admin":
                redirectPage = getServletContext().getInitParameter("admin");
                break;
            default:
                redirectPage = "";
        }

        view = request.getRequestDispatcher(redirectPage);
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
