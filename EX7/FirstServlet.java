import java.io.*;
import java.net.URLEncoder;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("username");
        if (name == null || name.trim().isEmpty()) {
            response.sendError(400, "Enter your name.");
            return;
        }
        HttpSession session = request.getSession();
        ServletContext context = getServletContext();
        int count;
        // Update the shared count atomically; count each session only once.
        synchronized (context) {
            Integer stored = (Integer) context.getAttribute("visitorCount");
            count = stored == null ? 0 : stored;
            if (session.getAttribute("counted") == null) {
                count++;
                context.setAttribute("visitorCount", count);
                session.setAttribute("counted", true);
            }
        }
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html><html><head><title>Session Tracking</title></head><body>");
        out.println("<h2>Welcome " + escape(name) + "</h2>");
        out.println("<h3>Number of Visitors: " + count + "</h3>");

        // Hidden field carries the name; encodeURL preserves the session without cookies.
        out.println("<h3>1. Hidden Form Field</h3>");
        out.println("<form action='" + escape(response.encodeURL("SecondServlet")) + "' method='post'>");
        out.println("<input type='hidden' name='username' value='" + escape(name) + "'>");
        out.println("<input type='submit' value='Continue using Hidden Field'></form>");

        // Encode the name so spaces, ampersands and other characters survive the URL.
        String url = "SecondServlet?username=" + URLEncoder.encode(name, "UTF-8");
        out.println("<h3>2. URL Rewriting</h3>");
        out.println("<a href='" + escape(response.encodeURL(url))
                + "'>Continue using URL Rewriting</a>");
        out.println("</body></html>");
    }

    private static String escape(String value) {
        if (value == null) return "";
        return value.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&#39;");
    }
}
