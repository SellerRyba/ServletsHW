import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@WebServlet(urlPatterns = "/time")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("text/html");

        String timezone = req.getParameter("timezone");

        LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));
        String currentTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        if (timezone != null && !timezone.isEmpty()) {
            now = now.atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.of(timezone)).toLocalDateTime();
            currentTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " " + timezone;
        }


        PrintWriter out = resp.getWriter();
        out.println("<html><head><title>Current Time</title></head>");
        out.println("<body><h1>Current Time</h1>");
        out.println("<p>" + currentTime + "</p>");
        out.println("</body></html>");
    }
}
