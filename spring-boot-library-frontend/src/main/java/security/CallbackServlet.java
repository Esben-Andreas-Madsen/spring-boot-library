package security;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@WebServlet("/callback")
public class CallbackServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String code = req.getParameter("code");
        if (code == null) {
            resp.getWriter().write("No code!");
            return;
        }

        String redirectUri = req.getRequestURL().toString();

        String codeVerifier = (String) req.getSession().getAttribute("code_verifier");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://host.docker.internal:1852/realms/library/protocol/openid-connect/token"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(
                        "grant_type=authorization_code" +
                                "&client_id=jakarta-frontend" +
                                "&code=" + code +
                                "&redirect_uri=" + URLEncoder.encode(redirectUri, "UTF-8") +
                                "&code_verifier=" + codeVerifier
                ))
                .build();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject json = Json.createReader(new StringReader(response.body())).readObject();
            String accessToken = json.getString("access_token");
            String refreshToken = json.getString("refresh_token");

            req.getSession().setAttribute("access_token", accessToken);
            req.getSession().setAttribute("refresh_token", refreshToken);

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("Token exchange failed");
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/index.xhtml");
    }
}
