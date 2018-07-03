package fi.restservice.model;

import java.io.File;

public interface Mobile {
	
	public abstract int getRowId();

	public abstract void setRowId(int id);
	
	public abstract int getPersonId();

	public abstract void setPersonId(int id);
	
	public abstract String getMobileManufacturer();

	public abstract void setMobileManufacturer(String mobileManufacturer);
	
	public abstract String getMobileModel();

	public abstract void setMobileModel(String mobileModel);
	
	public abstract String getMobileSpecs();

	public abstract void setMobileSpecs(String mobileSpecs);
	
	public abstract String getMobileFreedescription();

	public abstract void setMobileFreedescription(String mobileFreedescription);
	
	public abstract double getPrice();
	
	public abstract void setPrice(double price);
	
	public abstract File getPhoto();

	public abstract void setPhoto(File photo);

}
