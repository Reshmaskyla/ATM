package com.example.atm;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class ValidatePinServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pin = request.getParameter("pin").trim();  // Get and trim the PIN from the request

        System.out.println("Received PIN: " + pin);  // Debug: Print the received PIN

        boolean isValid = validatePin(pin);  // Validate the PIN

        if (isValid) {
            System.out.println("PIN is valid.");  // Debug: Print if PIN is valid
            response.getWriter().write("Valid");
        } else {
            System.out.println("PIN is invalid.");  // Debug: Print if PIN is invalid
            response.getWriter().write("Invalid");
        }
    }

    private boolean validatePin(String pin) {
        boolean isValid = false;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_db", "root", "Reshma$99");
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE pin = ?")) {

            stmt.setString(1, pin);  // Set the PIN in the query
            System.out.println("Executing query: SELECT * FROM users WHERE pin = '" + pin + "'");  // Debug: Print the query

            ResultSet rs = stmt.executeQuery();  // Execute the query

            if (rs.next()) {
                System.out.println("Query returned a result.");  // Debug: Print if the query returns a result
                isValid = true;
            } else {
                System.out.println("Query did not return any results.");  // Debug: Print if the query does not return a result
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());  // Debug: Print SQL exceptions
        }

        return isValid;
    }
}
