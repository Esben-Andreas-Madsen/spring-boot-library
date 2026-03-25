package security;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.security.MessageDigest;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final String AUTH_URL =
            "http://host.docker.internal:1852/realms/library/protocol/openid-connect/auth";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String redirectUri = "http://localhost:8081/spring-boot-library-frontend/callback";

        // 1. Generate code_verifier
        byte[] code = new byte[32];
        new SecureRandom().nextBytes(code);
        String codeVerifier = Base64.getUrlEncoder().withoutPadding().encodeToString(code);

        // 2. Generate code_challenge
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hashed = digest.digest(codeVerifier.getBytes());
        String codeChallenge = Base64.getUrlEncoder().withoutPadding().encodeToString(hashed);

        // 3. Store verifier in session
        req.getSession().setAttribute("code_verifier", codeVerifier);

        String url = AUTH_URL +
                "?client_id=jakarta-frontend" +
                "&response_type=code" +
                "&scope=openid" +
                "&redirect_uri=" + URLEncoder.encode(redirectUri, "UTF-8") +
                "&code_challenge=" + codeChallenge +
                "&code_challenge_method=S256";

        resp.sendRedirect(url);
    }
}