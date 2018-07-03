package fi.restservice.database;

import java.util.List;
import fi.restservice.database.MobileDAOService;
import fi.restservice.model.Mobile;


public interface MobileDAOService {

	public abstract void postMobile(Mobile mobile);
	
	public abstract void putMobile(Mobile mobile);

	public abstract Mobile getMobile(int id);

	public abstract List<Mobile> getMobile();
	
	public abstract List<Mobile> getOwnMobile(int personId);

	public abstract List<Mobile> getPersonMobile(int id);

	public abstract void deleteMobile(Mobile jsonToMobile);

}