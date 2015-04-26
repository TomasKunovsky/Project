package pis.back;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Filter pre overenie prihlasenia a prislusnosti do roli.
 * 
 * Pri zadani parametru s klucom "role" a hodnotou s nazvami roli oddelenych ciarkov,
 * dojde k overeniu, ci prihlaseny uzivatel je v jednej zo zadanych roli. 
 */
public final class AuthenticationFilter implements Filter 
{
    private static final String ROLE = "role";
    
	@SuppressWarnings("unused")
	private FilterConfig filterConfig = null;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
    {
        HttpSession session = ((HttpServletRequest) request).getSession();
        AuthenticationBean auth = (AuthenticationBean) session.getAttribute("authenticationBean");
        
        // ziska sa zoznam roli na overenie
        String rolesStr = this.filterConfig.getInitParameter(ROLE);
        String roles[] = {};
        if (null != rolesStr) {
        	roles = rolesStr.split(",");
        }
        
        if (auth != null && auth.isAuthorized()) { // overi sa prihlasenie
        	if (roles.length == 0 || auth.isOneOfRole(roles)) { // ak pozadovane overenie roli, tak sa overia)
        		chain.doFilter(request, response);
        	} else {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<html><head><title>Access denied.</title></head><body>");
                out.println("<h1>Access denied</h1>");
                out.println("<h2>You are not authorized to enter this section.</h2>");
                out.println("</body></html>");
        	}
        }
        else
        {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>Bad login</title></head><body>");
            out.println("<h1>Access denied</h1>");
            out.println("Access denied. <a href=\"./login.xhtml\">Try again</a>.");
            out.println("</body></html>");
        }
    }

    public void destroy() 
    {
    }

    public void init(FilterConfig filterConfig) 
    {
        this.filterConfig = filterConfig;
    }
}
