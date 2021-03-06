package com.iqmsoft.payara.security;

import org.glassfish.soteria.Utils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.auth.message.AuthException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.IdentityStore;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@ApplicationScoped
public class DemoAuthMechanism implements HttpAuthenticationMechanism {

    @Inject
    private IdentityStore identityStore;

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) throws AuthException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        if (Utils.notNull(name, password)) {
            return httpMessageContext.notifyContainerAboutLogin(
                identityStore.validate(new UsernamePasswordCredential(name, password)));
        }

        if (httpMessageContext.isProtected()) {
            return httpMessageContext.responseUnAuthorized();
        }

        return httpMessageContext.doNothing();
    }

}
