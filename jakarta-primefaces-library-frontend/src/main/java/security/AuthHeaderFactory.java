package security;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.MultivaluedHashMap;

import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;

import org.wildfly.security.http.oidc.OidcPrincipal;
import org.wildfly.security.http.oidc.OidcSecurityContext;

@RequestScoped
public class AuthHeaderFactory implements ClientHeadersFactory {

    @Override
    public MultivaluedMap<String, String> update(
            MultivaluedMap<String, String> incomingHeaders,
            MultivaluedMap<String, String> clientOutgoingHeaders) {

        MultivaluedMap<String, String> headers = new MultivaluedHashMap<>();

        try {
            HttpServletRequest request = (HttpServletRequest)
                    FacesContext.getCurrentInstance()
                            .getExternalContext()
                            .getRequest();

            if (request.getUserPrincipal() instanceof OidcPrincipal) {

                OidcPrincipal<?> principal =
                        (OidcPrincipal<?>) request.getUserPrincipal();

                OidcSecurityContext context =
                        principal.getOidcSecurityContext();

                String token = context.getTokenString();

                headers.add("Authorization", "Bearer " + token);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return headers;
    }
}