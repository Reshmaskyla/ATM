package com.example.atm;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class BalanceServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pin = request.getParameter("pin");
        if (pin == null || pin.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "PIN is required");
            return;
        }
        double balance = getBalance(pin);
        response.setContentType("text/plain");
        response.getWriter().write(String.valueOf(balance));
    }


    private double getBalance(String pin) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_db", "root", "Reshma$99");
             PreparedStatement stmt = conn.prepareStatement("SELECT balance FROM users WHERE pin = ?")) {

            stmt.setString(1, pin);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
