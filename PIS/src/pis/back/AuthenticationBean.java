package pis.back;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import pis.data.Admin;
import pis.data.Client;
import pis.data.Lector;
import pis.service.AdminManager;
import pis.service.ClientManager;
import pis.service.LectorManager;

/**
 * Umoznuje prihlasenie a odhlasenie uzivatela.
 * Dalej umoznuje rozlisit, medzi roznymi rolami uzivatelov.
 */
@ManagedBean
@SessionScoped
public class AuthenticationBean
{
    private static final String CLIENT_INIT_PARAM = "client";
	private static final String LECTOR_INIT_PARAM = "lector";
	private static final String ADMIN_INIT_PARAM = "admin";
	private static final int LECTOR = 0;
    private static final int ADMIN = 1;
    private static final int CLIENT = 2;
    private static final int NOBODY = 3;
	private boolean authorized = false;
    private String login;
    private String password;
    int loggedRole = NOBODY;
	Client loggedClient;

	Admin loggedAdmin;
	Lector loggedLector;
    
	@EJB
	private LectorManager lectorMgr;
	
	@EJB
	private AdminManager adminMgr;
	
	@EJB
	private ClientManager clientMgr;
    
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
        loggedRole = NOBODY;
        return "logout";
    }
    
    public boolean isLector() {
    	return loggedRole == LECTOR;
    }
    
    public boolean isAdmin() {
    	return loggedRole == ADMIN;
    }
    
	public boolean isClient() {
		return loggedRole == CLIENT;
	}
    
	/**
	 * Overi prihlasovacie udaje a podla role nastavi presmerovanie
	 * @return 
	 */
    public String actionLogin()
    {
    	String ret = "failed"; 
    	
    	loggedClient = clientMgr.find(login);
    	
    	if (null != loggedClient) {
    		if (password.equals(loggedClient.getPassword())) {
				loggedRole = CLIENT;
				authorized = true;
				ret = "login_client";
			}
    	} else {
    		loggedLector = lectorMgr.find(login);
    		
    		if (null != loggedLector) {
    			if (password.equals(loggedLector.getPassword())) {
    				loggedRole = LECTOR;
    				authorized = true;
    				ret = "login_lector";
    			}
    		} else {
    			loggedAdmin = adminMgr.find(login);
    			
    			if (null != loggedAdmin) {
    				if (password.equals(loggedAdmin.getPassword())) {
    					loggedRole = ADMIN;
    					authorized = true;
    					ret = "login_admin";
    				}
    			}
    		}
    	}
    	
    	if (!authorized) {
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Invalid login or password"));
    	} 
    	
    	return ret;
    }

    /**
     * Urci, ci je prihlaseny uzivatel je aspon jednou zo zadanych roli.
     * @param roles - role, ktore sa budu overovat
     * @return
     */
	public boolean isOneOfRole(String[] roles) {
		boolean ret = false;
		
		for (int i = 0; i < roles.length; i++) {
			if ((ADMIN_INIT_PARAM.equals(roles[i]) && isAdmin())
				|| (LECTOR_INIT_PARAM.equals(roles[i]) && isLector())
				|| (CLIENT_INIT_PARAM.equals(roles[i]) && isClient())) {
				
				ret = true;
				break;
			}
		}
		
		return ret;
	}

	public Lector getLoggedLector() {
		return loggedLector;
	}
	
	public Client getLoggedClient() {
		return loggedClient;
	}

	public Admin getLoggedAdmin() {
		return loggedAdmin;
	}
}
