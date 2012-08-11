package baldwin.dgte.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import baldwin.dgte.model.Item;
import baldwin.dgte.service.ManagementService;
import baldwin.dgte.utils.DgteUtil;

@Controller @RequestMapping("/register/") @SessionAttributes({"item"})
public class RegistrationController {
	static Logger log = Logger.getLogger(SimpleRegistrationController.class);
	
	@Autowired ManagementService adminService;
	@Autowired private DgteUtil dgteUtil;
	
	/*
	 * Registration flow functions
	 */
	
	@RequestMapping("/advertise*")
	public ModelAndView registrationForm() {
		ModelAndView m = new ModelAndView("regform");
		m.addObject("categories", dgteUtil.getCategories());
		return m;
	}
	
	@RequestMapping("/submitPrimary*") 
	public @ResponseBody Map<String, Object> submitRegistration(@RequestBody Item item, ModelMap model) {
		Map<String, Object> m = new HashMap<String, Object>();
		
		String validationResult = adminService.validate(item);
		if(ManagementService.VALIDATION_SUCCESS.equals(validationResult)) {
			try {
				adminService.save(item);
			} catch (Exception e) {
				m.put("message", e.getMessage());
				return m;
			}
			model.put("item", item);
			m.put("message", "success");
			m.put("domain", item.getDomain());
		} else {
			m.put("message", "Validation failed: " + validationResult);
		}
		
		return m;
	}
	
	@RequestMapping("/createHomepage/{domain}/")
	public ModelAndView modify(@PathVariable String domain) {
		ModelAndView m = new ModelAndView();
		
		Item item = adminService.get(domain);
		if(null == item) {
			m.setViewName("error");
			m.addObject("message", "Item not found with domain: " + domain);
		} else {
			m.setViewName("modify");
			m.addObject("source", "createHomepage");
			m.addObject("item", item.htmlFriendly());
		}
		
		return m;
	}
	
	@RequestMapping("/saveHomepage*")
	public @ResponseBody String saveHomepage(@ModelAttribute Item item, @RequestParam String textboxContents) {
		if(null == item || null == item.getDomain()) {
			log.error("Item or domain is null. Item: " + item);
			return "Can't save, item is undefined.";
		}
		
		return adminService.saveHomepage(item.getDomain(), item.getName(), textboxContents);
	}
	
	@RequestMapping("/regComplete/")
	public ModelAndView regComplete(@ModelAttribute Item item) {
		ModelAndView m = new ModelAndView("regcomplete");
		m.addObject("item", item);
		return m;
	}
}
