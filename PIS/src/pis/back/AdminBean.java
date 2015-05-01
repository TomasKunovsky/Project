package pis.back;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pis.data.Course;
import pis.service.AdminManager;
import pis.data.Admin;

@ManagedBean
@SessionScoped
public class AdminBean {
	@EJB
	private AdminManager adminMgr;
	private Admin admin;
	
	public List<Admin> getAdmins()
    {
		return adminMgr.findAll();
    }
	
	public String actionNew()
	{
		admin = new Admin();
		return "new";
	}
	
	public String actionInsertNew()
    {
        adminMgr.save(admin);
        return "insert";
    }
	
	public String actionEdit(Admin admin)
    {
    	setAdmin(admin);
    	return "edit";
    }
	
	public String actionDelete(Admin admin)
    {
		adminMgr.remove(admin);
    	return "delete";
    }
		
	public String actionUpdate() {
		adminMgr.save(admin);
		return "update";
	}

	/*gettery a settery*/
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	
	

}
