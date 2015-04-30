package pis.back;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pis.data.Client;
import pis.service.ClientManager;

@ManagedBean
@SessionScoped
public class RegistrationBean {
	@EJB
	private ClientManager clientMgr;
	
	private Client client;

    public String actionRegistration() {
		client = new Client();

		return "registration";
	}
    
	public String actionRegistrate() {
        clientMgr.save(client);
        
        return "ok";
    }

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
