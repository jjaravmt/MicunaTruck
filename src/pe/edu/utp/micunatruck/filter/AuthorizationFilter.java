package pe.edu.utp.micunatruck.filter;

//import pe.edu.utp.micunatruck.beans.AuthBean;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthorizationFilter implements Filter {

    public AuthorizationFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        try {

            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession ses = reqt.getSession(false);

            String reqURI = reqt.getRequestURI();
            if (reqURI.indexOf("/login.xhtml") >= 0
                    || reqURI.indexOf("/home.xhtml") >= 0
                    || reqURI.indexOf("/user-login.xhtml") >= 0
                    || reqURI.indexOf("/user-register.xhtml") >= 0
                    || reqURI.indexOf("/user-index.xhtml") >= 0
                    || reqURI.indexOf("/newAds.xhtml") >= 0
                    || reqURI.indexOf("/showAds.xhtml") >= 0
                    || reqURI.indexOf("/editAds.xhtml") >= 0
                    || (ses != null && ses.getAttribute("username") != null)
                    || reqURI.indexOf("/public/") >= 0
                    || reqURI.contains("javax.faces.resource")
                    ) {
                chain.doFilter(request, response);
            }else{
                resp.sendRedirect(reqt.getContextPath() + "/home.xhtml");
            }
//
////            AuthBean auth = new AuthBean();
////            if(auth.getUser().getId()==1){
////
////            }else{
////                resp.sendRedirect(reqt.getContextPath() + "/faces/login.xhtml");
////            }

//            chain.doFilter(request, response);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void destroy() {

    }
}