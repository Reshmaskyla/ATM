package com.example.atm;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class WithdrawServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pin = request.getParameter("pin");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String result = withdraw(pin, amount);

        response.setContentType("text/plain");
        response.getWriter().write(result);
    }

    private String withdraw(String pin, double amount) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_db", "root", "Reshma$99")) {
            conn.setAutoCommit(false);

            PreparedStatement stmt = conn.prepareStatement("SELECT balance FROM users WHERE pin = ?");
            stmt.setString(1, pin);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("balance");
                if (balance >= amount) {
                    stmt = conn.prepareStatement("UPDATE users SET balance = balance - ? WHERE pin = ?");
                    stmt.setDouble(1, amount);
                    stmt.setString(2, pin);
                    stmt.executeUpdate();
                    conn.commit();
                    return "Success";
                } else {
                    return "Insufficient Balance";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Transaction Failed";
    }
}
