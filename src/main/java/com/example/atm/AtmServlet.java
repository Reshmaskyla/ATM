package com.example.atm;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AtmServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Display the main page or handle initial requests
        // Forward request to the HTML page or handle the logic here
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle form submissions or AJAX requests
        String action = request.getParameter("action");

        if ("validatePin".equals(action)) {
            String pin = request.getParameter("pin");
            // Call ValidatePinServlet or its equivalent
            RequestDispatcher dispatcher = request.getRequestDispatcher("validate-pin");
            dispatcher.forward(request, response);
        } else if ("checkBalance".equals(action)) {
            String pin = request.getParameter("pin");
            // Call BalanceServlet or its equivalent
            RequestDispatcher dispatcher = request.getRequestDispatcher("balance");
            dispatcher.forward(request, response);
        } else if ("withdraw".equals(action)) {
            String pin = request.getParameter("pin");
            double amount = Double.parseDouble(request.getParameter("amount"));
            // Call WithdrawServlet or its equivalent
            RequestDispatcher dispatcher = request.getRequestDispatcher("withdraw");
            dispatcher.forward(request, response);
        } else {
            // Handle unknown actions
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action");
        }
    }
}
