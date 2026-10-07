import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/TicketServlet")
public class TicketServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html><html><head><title>Booked Tickets</title></head><body>");
        try {
            String username = request.getParameter("username");
            String event = request.getParameter("event");
            int tickets = Integer.parseInt(request.getParameter("tickets"));
            Date date = Date.valueOf(request.getParameter("date"));
            if (username == null || username.trim().isEmpty() || username.length() > 50
                    || event == null || event.length() > 50 || tickets < 1) {
                throw new IllegalArgumentException("Enter valid booking details.");
            }

            // Load driver and connect to the local lab database.
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sql = "INSERT INTO tickets "
                    + "(user_name,event_name,num_tickets,booking_date) VALUES (?,?,?,?)";
            // Try-with-resources closes JDBC resources even if an error occurs.
            try (Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ticketdb", "root", "");
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, username.trim());
                ps.setString(2, event);
                ps.setInt(3, tickets);
                ps.setDate(4, date);
                ps.executeUpdate();
                out.println("<h2>Ticket Booked Successfully!</h2>");
                try (Statement stmt = con.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT * FROM tickets ORDER BY ticket_id")) {
                    out.println("<h2>Booked Ticket Details</h2><table border='1' cellpadding='10'>");
                    out.println("<tr><th>Ticket ID</th><th>Name</th><th>Event</th>"
                            + "<th>No. of Tickets</th><th>Date</th></tr>");
                    while (rs.next()) {
                        out.println("<tr><td>" + rs.getInt("ticket_id") + "</td><td>"
                                + escape(rs.getString("user_name")) + "</td><td>"
                                + escape(rs.getString("event_name")) + "</td><td>"
                                + rs.getInt("num_tickets") + "</td><td>"
                                + rs.getDate("booking_date") + "</td></tr>");
                    }
                    out.println("</table>");
                }
            }
        } catch (Exception e) {
            out.println("<h3>Error: " + escape(e.getMessage()) + "</h3>");
        }
        out.println("<p><a href='booking.html'>Book another ticket</a></p></body></html>");
    }

    private static String escape(String value) {
        if (value == null) return "";
        return value.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&#39;");
    }
}
