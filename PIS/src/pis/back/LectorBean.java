package pis.back;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pis.data.Lector;
import pis.service.LectorManager;

@ManagedBean
@SessionScoped
public class LectorBean {
	@EJB
	private LectorManager lectorMgr;
	private Lector lector;
	
	public LectorBean() {
		lector = new Lector();
	}
	
	public Lector getLector() {
		return lector;
	}

	public void setLector(Lector lector) {
		this.lector = lector;
	}
	
    public List<Lector> getLectors()
    {
		return lectorMgr.findAll();
    }
    
    public String actionNew()
	{
		lector = new Lector();
		return "new";
	}
    
	public String actionInsertNew()
    {
        lectorMgr.save(lector);
        return "insert";
    }

	public String actionUpdate()
    {
        lectorMgr.save(lector);
        return "update";
    }
    
    public String actionEdit(Lector lector)
    {
    	setLector(lector);
    	return "edit";
    }
    
    public String actionDelete(Lector lector)
    {
    	lectorMgr.remove(lector);
    	return "delete";
    }

}
