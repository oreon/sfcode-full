package org.witchcraft.seam.security;

import org.jboss.seam.faces.rewrite.FacesRedirect;
import org.jboss.seam.faces.security.AccessDeniedView;
import org.jboss.seam.faces.security.LoginView;
import org.jboss.seam.faces.view.config.ViewConfig;
import org.jboss.seam.faces.view.config.ViewPattern;


@ViewConfig
public interface Pages {


    static enum Pages1 {


     
        @FacesRedirect
        @ViewPattern("/admin/*")
        @AccessDeniedView("/denied.xhtml")
        @LoginView("/login.xhtml")
        @Admin
        ALL;

        

    }

}
