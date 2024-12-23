import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class TrainingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Get or initialize training goals
        List<String> trainingGoals = (List<String>) session.getAttribute("trainingGoals");
        if (trainingGoals == null) {
            trainingGoals = new ArrayList<>();
            session.setAttribute("trainingGoals", trainingGoals);
        }

        // Get input values
        String name = request.getParameter("name");
        String goal = request.getParameter("goal");

        // Create the entry
        String goalEntry = "<strong style='color:#e74c3c;'>Name:</strong> " + name + "<br>" +
                           "<strong style='color:#2980b9;'>Training Goal:</strong> " + goal;
        trainingGoals.add(goalEntry);

        // Redirect to display updated list
        response.sendRedirect("training");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        List<String> trainingGoals = (List<String>) session.getAttribute("trainingGoals");

        // Output with glass styling
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'><head><title>Boxing Training Goals</title></head><body style='background: linear-gradient(135deg, #2c3e50, #d35400); color: white; font-family: Arial, sans-serif; padding: 20px;'>");
        out.println("<h1 style='text-align: center;'>Boxing Training Goals</h1>");
        out.println("<div style='max-width: 600px; margin: auto;'>");

        if (trainingGoals != null && !trainingGoals.isEmpty()) {
            out.println("<ul style='list-style: none; padding: 0;'>");
            for (String goal : trainingGoals) {
                out.println("<li style='background: rgba(255,255,255,0.2); padding: 15px; border-radius: 10px; margin-bottom: 10px; border: 2px solid rgba(0,122,204,0.5);'>" + goal + "</li>");
            }
            out.println("</ul>");
        } else {
            out.println("<p style='text-align: center; font-size: 1.2em;'>No training goals submitted yet!</p>");
        }

        out.println("<p style='text-align: center;'><a href='training.html' style='color: #fff; text-decoration: underline;'>Go Back to Submit More Goals</a></p>");
        out.println("</div></body></html>");
    }
}
