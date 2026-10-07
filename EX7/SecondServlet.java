import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/SecondServlet")
public class SecondServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        showUser(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        showUser(request, response);
    }

    private void showUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("username");
        if (name == null || name.trim().isEmpty()) {
            response.sendError(400, "Start from the registration page.");
            return;
        }
        ServletContext context = getServletContext();
        Integer count;
        synchronized (context) {
            count = (Integer) context.getAttribute("visitorCount");
        }
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html><html><head><title>Tracked User</title></head><body>");
        out.println("<h2>Hello, " + escape(name) + "</h2>");
        out.println("<h3>Total Visitors: " + (count == null ? 0 : count) + "</h3>");
        out.println("<p>Session tracking successful.</p>");
        out.println("<a href='" + escape(response.encodeURL("index.html")) + "'>Start again</a>");
        out.println("</body></html>");
    }

    private static String escape(String value) {
        return value.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&#39;");
    }
}
