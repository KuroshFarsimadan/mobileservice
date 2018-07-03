package fi.restservice.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fi.restservice.model.Mobile;
import fi.restservice.model.MobileImpl;



@Service
public class JSONConverter {
	
	public JSONConverter() {
	}

	public ArrayList<Mobile> jsonToMobiles(String jsonS) throws JsonParseException, JsonMappingException, IOException, JSONException {
		ArrayList<Mobile> mobiles = new ArrayList<Mobile> ();
		JSONArray jsonArray = new JSONArray(jsonS);
		JSONObject jsonObject = new JSONObject();
		for (int i=0; i<jsonArray.length(); i++) {
			// jsonObject.isNull("parentId")
			jsonObject = jsonArray.getJSONObject(i);
			Mobile mobile = new MobileImpl();
			System.out.println(jsonObject.toString());
			if(!jsonObject.isNull("mobileFreedescription")) {
				mobile.setMobileFreedescription(jsonObject.getString("mobileFreedescription").toString());
			} 
			if(!jsonObject.isNull("mobileManufacturer")) {
				mobile.setMobileManufacturer(jsonObject.getString("mobileManufacturer"));
			}
			if(!jsonObject.isNull("mobileModel")) {
				mobile.setMobileModel(jsonObject.getString("mobileModel"));
			}
			if(!jsonObject.isNull("mobileSpecs")) {
				mobile.setMobileSpecs(jsonObject.getString("mobileSpecs"));
			}
			if(!jsonObject.isNull("price")) {
				mobile.setPrice(jsonObject.getDouble("price"));
			}
			mobile.setPersonId(jsonObject.getInt("personId"));
			System.out.println("*****	public ArrayList<Mobile> jsonToMobiles(String jsonS) " +mobile.getPersonId());
			mobile.setRowId(jsonObject.getInt("rowId"));

			mobiles.add(mobile);
		}
		return mobiles;
	}
	
	public Mobile jsonToMobile(String jsonS) throws JsonParseException, JsonMappingException, IOException, JSONException {
		System.out.println("public Mobile jsonToMobile" + jsonS);
		JSONObject jsonObject = new JSONObject(jsonS);
		Mobile mobile = new MobileImpl();
		System.out.println(jsonObject.toString());
		if(!jsonObject.isNull("mobileFreedescription")) {
			mobile.setMobileFreedescription(jsonObject.getString("mobileFreedescription").toString());
		} 
		if(!jsonObject.isNull("mobileManufacturer")) {
			mobile.setMobileManufacturer(jsonObject.getString("mobileManufacturer"));
		}
		if(!jsonObject.isNull("mobileModel")) {
			mobile.setMobileModel(jsonObject.getString("mobileModel"));
		}
		if(!jsonObject.isNull("mobileSpecs")) {
			mobile.setMobileSpecs(jsonObject.getString("mobileSpecs"));
		}
		if(!jsonObject.isNull("price")) {
			mobile.setPrice(jsonObject.getDouble("price"));
		}
		mobile.setPersonId(jsonObject.getInt("personId"));
		mobile.setRowId(jsonObject.getInt("rowId"));
		return mobile;
	}
	
	public String MobileToJSONString(Mobile mobile) {
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(mobile);
			System.out.println(json);
		} catch (JsonMappingException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return json;
	}

	/**
	public ArrayList<Person> jsonToPersons(String jsonS) throws JsonParseException, JsonMappingException, IOException, JSONException {
		ArrayList<Person> persons = new ArrayList<Person> ();
		JSONArray jsonArray = new JSONArray(jsonS);
		JSONObject jsonObject = new JSONObject();
		for (int i=0; i<jsonArray.length(); i++) {
			// jsonObject.isNull("parentId")
			jsonObject = jsonArray.getJSONObject(i);
			Person person = new PersonImpl();
			System.out.println(jsonObject.toString());
			if(!jsonObject.isNull("mobileFreedescription")) {
				person.setMobileFreedescription(jsonObject.getString("mobileFreedescription").toString());
			} 
			if(!jsonObject.isNull("mobileManufacturer")) {
				person.setMobileManufacturer(jsonObject.getString("mobileManufacturer"));
			}
			if(!jsonObject.isNull("mobileModel")) {
				person.setMobileModel(jsonObject.getString("mobileModel"));
			}
			if(!jsonObject.isNull("mobileSpecs")) {
				person.setMobileSpecs(jsonObject.getString("mobileSpecs"));
			}
			person.setPersonId(jsonObject.getInt("personId"));
			person.setPersonId(jsonObject.getInt("rowId"));

			persons.add(person);
		}
		return persons;
	}*/
	
	

}
