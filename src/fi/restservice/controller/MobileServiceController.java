package fi.restservice.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import fi.restservice.database.MobileDAOService;
import fi.restservice.model.Mobile;
import fi.restservice.model.MobileImpl;
import fi.restservice.util.JSONConverter;

@Controller
public class MobileServiceController {

	@Inject
	private MobileDAOService mobileDao;
	
	public MobileDAOService getMobileDao() {
		return this.mobileDao;
	}

	public void setMobileDao(MobileDAOService dao) {
		this.mobileDao = dao;
	}
	
	@Inject
	private JSONConverter jsonC;
	
	public JSONConverter getJsonC() {
		return this.jsonC;
	}

	public void setJsonC(JSONConverter jsonC) {
		this.jsonC = jsonC;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(value="/mobile")
	@ResponseBody public List<Mobile> mobile() {
		List<Mobile> mobiles = mobileDao.getMobile();
		return mobiles;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(value="/mobile/{id}")
	@ResponseBody public Mobile getMobile(@PathVariable int id, Model model) {
		Mobile mobile = mobileDao.getMobile(id);
		model.addAttribute("mobile", mobile);
		return mobile;
	}
	

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(value="/personMobile/{id}")
	@ResponseBody public List<Mobile> getPersonMobile(@PathVariable int id, Model model) {
		//List<Mobile> mobiles = mobileDao.getPersonMobile(id);
		List<Mobile> mobiles = mobileDao.getMobile();
		ArrayList<Mobile> returnableList = new ArrayList<Mobile>();
		for(int i = 0; i < mobiles.size(); i++) {
			if(mobiles.get(i).getPersonId() == id) {
				returnableList.add(mobiles.get(i));
			}
		}
		System.out.println(id + mobiles.toString());
		model.addAttribute("mobile", returnableList);
		return returnableList;
	}
	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(value="/mobile", method = RequestMethod.POST)
	@ResponseBody public String postMobile(@RequestBody final String mobile) throws JsonParseException, JsonMappingException, IOException, JSONException {	
		mobileDao.postMobile(jsonC.jsonToMobile(mobile));
		return mobile;
	}
	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(value="/mobile", method = RequestMethod.PUT)
	@ResponseBody public String putMobile(@RequestBody final String mobile) throws JsonParseException, JsonMappingException, IOException, JSONException {	
		mobileDao.putMobile(jsonC.jsonToMobile(mobile));
		return mobile;
	}
	
	@RequestMapping(value="/mobile/{id}", method = RequestMethod.DELETE)
	@ResponseBody public void deleteMobile(@PathVariable("id") int id) throws JsonParseException, JsonMappingException, IOException, JSONException {	
		System.out.println("@ResponseBody public String deleteMobile " + id);
		Mobile mobile = new MobileImpl();
		mobile.setRowId(id);
		mobileDao.deleteMobile(mobile);
	}
	
}