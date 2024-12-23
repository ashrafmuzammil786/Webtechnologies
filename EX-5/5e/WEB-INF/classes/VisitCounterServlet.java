import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class VisitCounterServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        Integer visitCount = (Integer) context.getAttribute("visitCount");
        if (visitCount == null) {
            visitCount = 0;
        }
        context.setAttribute("visitCount", visitCount);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        Integer visitCount = (Integer) context.getAttribute("visitCount");

        // Increment the visit count
        visitCount++;
        context.setAttribute("visitCount", visitCount);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Output HTML with Boxing theme
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Boxing Match Visit Counter</title>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println("<style>");
        out.println("body { background-color: #f9f3f3; color: #9c2a2a; text-align: center; margin-top: 50px; font-family: 'Arial', sans-serif; }");
        out.println(".btn { margin-top: 20px; background-color: #e91e63; color: white; }");
        out.println(".btn:hover { background-color: #c2185b; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h1 class='my-4'>Boxing Match Tracker</h1>");
        out.println("<p class='lead'>The match has been watched <strong>" + visitCount + "</strong> times. Stay tuned for more action!</p>");
        out.println("<a href='/' class='btn btn-lg'>Back to the Ring</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
