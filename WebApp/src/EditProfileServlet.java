import com.mysql.jdbc.Connection;
/*
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
*/
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

@WebServlet
@MultipartConfig
public class EditProfileServlet extends HttpServlet {
    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 500 * 1024;
    private int maxMemSize = 400 * 1024;
    private File file ;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sesi = req.getSession();
        JSONObject arrayObj;
        arrayObj = new JSONObject();
        Connection Con = null;
        int userId = 0;
        try {
            userId = Integer.parseInt(sesi.getAttribute("userId").toString());
            String name = req.getParameter("name");
            String phone = req.getParameter("phone");
            //connect to the database
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/user_ojekonline",
                    "root", "");
            String checkingQuery = "SELECT user_status FROM user_info WHERE user_id = '" + userId + "';";
            Statement stmt = Con.createStatement();
            ResultSet rs = stmt.executeQuery(checkingQuery);
            if (rs.next()) {
                String status = rs.getString("user_status");
                String updateQuery;
                if (status.equals("0") && (req.getParameter("is_driver")) == null) {
                    updateQuery = "UPDATE user_info SET user_name = '" + name + "', user_phone = '" + phone + "', user_status = '" + "1" + "' WHERE user_id = '" + userId + "';";
                } else if (status.equals("1") && (req.getParameter("is_driver") != null)) {
                    updateQuery = "UPDATE user_info SET user_name = '" + name + "', user_phone = '" + phone + "', user_status = '" + "0" + "' WHERE user_id = '" + userId + "';";
                } else {
                    updateQuery = "UPDATE user_info SET user_name = '" + name + "', user_phone = '" + phone + "' WHERE user_id = '" + userId + "';";
                }
                Statement stmt2 = Con.createStatement();
                arrayObj.put("a", userId);
                arrayObj.put("b", name);
                arrayObj.put("c", phone);
                arrayObj.put("d", status);
                int rs2 = stmt.executeUpdate(updateQuery);
                arrayObj.put("asdf", "QWEQWE");
                if (rs2 >= 1) {
                    resp.sendRedirect("profile.jsp");
                } else {
                    resp.sendRedirect("order.jsp");
                }
            }
        } catch ( ClassNotFoundException | IllegalAccessException | InstantiationException |
                SQLException | JSONException theException) {
            //arrayObj.put("asdf", theException);
            //arrayObj.put("kevin", theException);
            System.out.println("Register failed: An Exception has occurred! " + theException);
        } finally {
            if (Con != null) {
                try {
                    Con.close();
                } catch (Exception e) {
                }
            }
        }

        /*
        try {
            userId = Integer.parseInt(sesi.getAttribute("userid").toString());
            ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> multifiles = sf.parseRequest(req);

            for (FileItem item : multifiles) {
                System.out.println("MASUK");
                item.write(new File("C:\\Users\\MASTER\\Desktop" + String.valueOf(userId)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }*/
/*
            try {
                userId = Integer.parseInt(sesi.getAttribute("userid").toString());
                isMultipart = ServletFileUpload.isMultipartContent(req);
                resp.setContentType("text/html");
                java.io.PrintWriter out = resp.getWriter( );

                DiskFileItemFactory factory = new DiskFileItemFactory();

                // maximum size that will be stored in memory
                factory.setSizeThreshold(maxMemSize);

                // Location to save data that is larger than maxMemSize.
                factory.setRepository(new File("C:\\Users\\MASTER\\Desktop"));
                System.out.println("POPOPOPOPOPOPO");
                // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload(factory);

                // maximum file size to be uploaded.
                upload.setSizeMax( maxFileSize );


                // Parse the request to get file items.
                List fileItems = upload.parseRequest(req);

                // Process the uploaded file items
                Iterator i = fileItems.iterator();
                System.out.println("PPAPAPAPAPAPAPAPA");
                while ( i.hasNext () ) {
                    System.out.println("PIPIPIPIPIPIP");
                    FileItem fi = (FileItem)i.next();
                    if ( !fi.isFormField () ) {
                        // Get the uploaded file parameters
                        String fieldName = fi.getFieldName();
                        String fileName = String.valueOf(userId);
                        //String fileName = fi.getName();
                        String contentType = fi.getContentType();
                        boolean isInMemory = fi.isInMemory();
                        long sizeInBytes = fi.getSize();

                        // Write the file

                        fi.write( file ) ;
                        System.out.println("END WHILE");
                    }
                }
                System.out.println("CLEAR");
            } catch(Exception ex) {
                System.out.println("LALALALA" + ex);
                arrayObj.put("asdf", ex);
            }
*/
        resp.setContentType("application/json:charset=UTF-8");
        resp.getWriter().write(arrayObj.toString());
    }
}
