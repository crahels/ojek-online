package example;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@WebService(serviceName = "HelloWorldService")
public class HelloWorld {
    @WebMethod(operationName = "expiryTime")
    public int expiryTime(String token) throws IOException, ParseException {
        String USER_AGENT = "Mozilla/5.0";
        String url = "http://localhost:8001/validateToken";
        URL connection = null;
        int result = 0;
        try {
            connection = new URL(url);
            HttpURLConnection conIS = (HttpURLConnection) connection.openConnection();

            //add request header
            conIS.setRequestMethod("POST");
            conIS.setRequestProperty("User-Agent", USER_AGENT);
            conIS.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            String urlParameters;
            urlParameters = "token=" + token;
            // Send post request
            conIS.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conIS.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();

            int responseCode = conIS.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(conIS.getInputStream()));
            String inputLine;
            StringBuilder resp = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                resp.append(inputLine);
            }
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(resp.toString());
            conIS.disconnect();
            String retVal = obj.get("token_auth").toString();
            System.out.println(retVal);
            if (retVal.equals("VALID")) {
                result = 1;
            } else if (retVal.equals("INVALID")) {
                result = 0;
            } else {
                result = 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] argv) {
        Object implementor = new HelloWorld();
        String address = "http://localhost:8002/HelloWorld";
        Endpoint.publish(address, implementor);
        implementor = new OrderGojek();
        address = "http://localhost:8002/OrderGojek";
        Endpoint.publish(address, implementor);
        implementor = new Location();
        address = "http://localhost:8002/Location";
        Endpoint.publish(address, implementor);
        implementor = new HistoryGojek();
        address = "http://localhost:8002/HistoryGojek";
        Endpoint.publish(address, implementor);
        implementor = new Profile();
        address = "http://localhost:8002/Profile";
        Endpoint.publish(address, implementor);
    }
}