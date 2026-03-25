package security;

import jakarta.inject.Inject;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
public class AuthTokenFilter implements ClientRequestFilter {

    @Inject
    TokenProvider tokenProvider;

    @Override
    public void filter(ClientRequestContext requestContext) {

        String token = tokenProvider.getToken();

        requestContext.getHeaders()
                .add("Authorization", "Bearer " + token);
    }
}
