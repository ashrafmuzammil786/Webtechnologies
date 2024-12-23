package com.ecofriendly;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TodoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String task = request.getParameter("task");

        HttpSession session = request.getSession();
        List<String> tasks = (List<String>) session.getAttribute("tasks");
        if (tasks == null) {
            tasks = new ArrayList<>();
            session.setAttribute("tasks", tasks);
        }

        tasks.add(task);

        response.sendRedirect("todo");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Boxing Workout List</title>");
        out.println("<style>");
        out.println("body { background: linear-gradient(135deg, #ff0000, #0000ff, #ffd700); color: white; font-family: Arial, sans-serif; text-align: center; margin: 0; padding: 0; }");
        out.println("h1 { margin-top: 20px; text-shadow: 0 0 10px #ffd700, 0 0 20px #ff0000; }");
        out.println("form { margin: 20px; }");
        out.println("label { font-size: 1.2em; }");
        out.println("input[type='text'] { padding: 10px; border-radius: 5px; border: 2px solid #ffd700; margin-right: 10px; width: 250px; }");
        out.println("input[type='submit'] { padding: 10px 20px; background: linear-gradient(135deg, #ff0000, #0000ff); color: white; border: 2px solid #ffd700; border-radius: 5px; font-weight: bold; cursor: pointer; transition: transform 0.3s, box-shadow 0.3s; }");
        out.println("input[type='submit']:hover { transform: scale(1.1); box-shadow: 0 0 15px #ffd700, 0 0 30px #ff0000; }");
        out.println("ul { list-style-type: none; padding: 0; }");
        out.println("li { font-size: 1.2em; margin: 10px 0; padding: 10px; background: rgba(255, 255, 255, 0.1); border: 1px solid #ffd700; border-radius: 10px; box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2); color: white; }");
        out.println("hr { border: 1px solid #ffd700; margin: 20px 0; }");
        out.println("</style></head><body>");
        out.println("<h1>Boxing Workout To-Do List</h1>");
        out.println("<form action='todo' method='POST'>");
        out.println("<label for='task'>Add a Workout:</label>");
        out.println("<input type='text' id='task' name='task' placeholder='e.g., Jump Rope, Push-ups' required>");
        out.println("<input type='submit' value='Add Workout'>");
        out.println("</form>");
        out.println("<hr>");
        out.println("<h2>Your Boxing Workout Plan:</h2>");
        out.println("<ul>");

        HttpSession session = request.getSession();
        List<String> tasks = (List<String>) session.getAttribute("tasks");

        if (tasks != null) {
            for (String task : tasks) {
                out.println("<li>" + task + "</li>");
            }
        }

        out.println("</ul>");
        out.println("</body></html>");
    }
}
