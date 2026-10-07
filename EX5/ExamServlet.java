import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/ExamServlet")
public class ExamServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String q1 = request.getParameter("q1");
        String q2 = request.getParameter("q2");
        String q3 = request.getParameter("q3");
        int score = 0;

        if ("Paris".equals(q1)) score++;
        if ("4".equals(q2)) score++;
        if (q3 != null && "CSS".equalsIgnoreCase(q3.trim())) score++;

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html><html><head><title>Examination Result</title></head><body>");
        out.println("<h1>Examination Result</h1>");
        out.println("<h3>Name: " + escape(username) + "</h3>");
        out.println("<p>Question 1 Answer: " + escape(q1) + "</p>");
        out.println("<p>Question 2 Answer: " + escape(q2) + "</p>");
        out.println("<p>Question 3 Answer: " + escape(q3) + "</p>");
        out.println("<h2>Score: " + score + " / 3</h2>");
        if (score == 3) {
            out.println("<p>Excellent! All answers are correct.</p>");
        } else if (score >= 2) {
            out.println("<p>Good Performance!</p>");
        } else {
            out.println("<p>Try Again!</p>");
        }
        out.println("<a href='exam.html'>Take the exam again</a>");
        out.println("</body></html>");
    }

    // Display entered answers as text instead of interpreting them as HTML.
    private static String escape(String value) {
        if (value == null) return "Not answered";
        return value.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&#39;");
    }
}
