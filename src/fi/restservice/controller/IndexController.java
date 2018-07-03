package fi.restservice.controller;

import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import fi.restservice.database.MobileDAOService;

@Controller
public class IndexController {

	@Inject
	private MobileDAOService mobileDao;
	
	public MobileDAOService getMobileDao() {
		return this.mobileDao;
	}

	public void setMobileDao(MobileDAOService dao) {
		this.mobileDao = dao;
	}
	// Test GIT
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("message", "Extra page for all");
		return "index";
	}
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index2(Model model) {
		model.addAttribute("message", "Extra page for all");
		return "index";
	}
}