import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class MatchesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.html");
            return;
        }

        String username = (String) session.getAttribute("username");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BoxingArena", "root", "password");

            String query = "SELECT * FROM matches ORDER BY match_date ASC";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Upcoming Matches</title>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<style>");
            out.println("body {background-color: #0d0d0d; color: white;}");
            out.println(".card {background-color: #1c1c1c; border: 1px solid #ff0000; margin-bottom: 20px;}");
            out.println(".btn-secondary {background-color: #ff0000; border: none;}");
            out.println(".btn-secondary:hover {background-color: #e60000;}");
            out.println("</style></head>");
            out.println("<body class='container'>");
            out.println("<h1>Welcome to Boxing Arena, " + username + "!</h1>");
            out.println("<h2>Upcoming Matches:</h2>");

            while (rs.next()) {
                out.println("<div class='card p-3'>");
                out.println("<h3>" + rs.getString("match_name") + "</h3>");
                out.println("<p><strong>Date:</strong> " + rs.getDate("match_date") + "</p>");
                out.println("<p><strong>Venue:</strong> " + rs.getString("venue") + "</p>");
                out.println("</div>");
            }

            out.println("<a href='login.html' class='btn btn-secondary'>Logout</a>");
            out.println("</body></html>");

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
