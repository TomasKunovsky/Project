package pis.back;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class AuthenticationBean
{
    private boolean authorized;
    private String login;
    private String password;
    
    public AuthenticationBean()
    {
        authorized = false;
    }

    public boolean isAuthorized()
    {
        return authorized;
    }

    public void setAuthorized(boolean authorized)
    {
        this.authorized = authorized;
    }
    
    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String actionLogout()
    {
        authorized = false;
        return "logout";
    }
    
    public boolean isLector() {
    	return true;
    }
    
    public boolean isAdmin() {
    	return false;
    }
    
	private boolean isUser() {
		return true;
	}
    
	/**
	 * Overi prihlasovacie udaje a podla role nastavi presmerovanie
	 * @return 
	 */
    public String actionLogin()
    {
        if (login.equals("jan") && password.equals("novak"))
        {
            authorized = true;
            return "login_lector";
        }
        else
        {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Invalid login"));
            return "failed";
        }
    }

    /**
     * Urci, ci je prihlaseny uzivatel je aspon jednou zo zadanych roli.
     * @param roles - role, ktore sa budu overovat
     * @return
     */
	public boolean isOneOfRole(String[] roles) {
		boolean ret = false;
		
		for (int i = 0; i < roles.length; i++) {
			if (("admin".equals(roles[i]) && isAdmin())
				|| ("lector".equals(roles[i]) && isLector())
				|| ("user".equals(roles[i]) && isUser())) {
				
				ret = true;
				break;
			}
		}
		
		return ret;
	}
}
