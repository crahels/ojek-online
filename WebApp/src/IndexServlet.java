import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // Set the response message's MIME type
        PrintWriter out = response.getWriter();

        out.println("AH");
        request.getRequestDispatcher("/editProfile.jsp").forward(request,response);
        response.sendRedirect(request.getContextPath()+"/editprofile");
    }
}
