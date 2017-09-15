package com.iqmsoft.payara.security;

import org.glassfish.soteria.identitystores.annotation.Credentials;
import org.glassfish.soteria.identitystores.annotation.EmbeddedIdentityStoreDefinition;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationPath("/")
@DeclareRoles({"user", "admin", "superadmin"})
@EmbeddedIdentityStoreDefinition({
    @Credentials(callerName = "dasniko", password = "secret", groups = {"user", "admin"}),
    @Credentials(callerName = "john", password = "doe", groups = "user")
})
public class JaxRsApp extends Application {
}
