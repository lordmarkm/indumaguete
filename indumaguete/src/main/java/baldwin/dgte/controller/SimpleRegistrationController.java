package baldwin.dgte.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import baldwin.dgte.utils.MavBuilder;

/**
 * TODO: require Facebook authenticated, also for most other URLs
 */
@Controller @RequestMapping("/simplereg/") @SessionAttributes({"item"})
public class SimpleRegistrationController {
	static Logger log = LoggerFactory.getLogger(RegistrationController.class);

	@Autowired 
	private ManagementService adminService;

	@Autowired 
	private DgteUtil dgteUtil;

	final String URL_ADVERTISE = "/new/";
	final String URL_EDIT_DOMAIN = "/edit/{domain}/";
	final String URL_EDIT_SESSION = "/edit/";
	final String URL_SAVE = "/save/";
	
	@RequestMapping(URL_ADVERTISE)
	public ModelAndView registrationForm(Item item, HttpServletRequest request) {
		log.debug("Registration form requested by {}" + request.getRemoteAddr());
		return new MavBuilder("simplereg")
			.addObject("item", item)
			.addObject("categories", dgteUtil.getSimpleCategories(DgteUtil.CATEGORIES_NO_GROUPS))
			.mav();
	}
	
	@RequestMapping(URL_EDIT_DOMAIN)
	public ModelAndView editForm(@PathVariable String domain) {
		log.debug("Domain-based edit request for item with domain: [{}]", domain);
		Item item = adminService.get(domain);
		return editForm(item);
	}
	
	@RequestMapping(URL_EDIT_SESSION)
	public ModelAndView editForm(@ModelAttribute Item item) {
		log.debug("Edit request for item: [{}]", item.getName());
		return new MavBuilder("simpleedit")
			.addObject("item", item)
			.addObject("categories", dgteUtil.getSimpleCategories(DgteUtil.CATEGORIES_NO_GROUPS))
			.mav();
	}
	
	@RequestMapping(URL_SAVE) 
	public @ResponseBody Map<String, Object> submitRegistration(@RequestBody Item item, ModelMap model) {
		Map<String, Object> m = new HashMap<String, Object>();

		try {
			adminService.save(item);
		} catch (Exception e) {
			m.put("message", e.getMessage());
			return m;
		}
		model.put("item", item);
		m.put("message", "success");
		m.put("domain", item.getDomain());

		return m;
	}

	@RequestMapping("/createHomepage/{domain}/")
	public ModelAndView modify(@PathVariable String domain) {
		log.info("About to create homepage for {}", domain);
		Item item = adminService.get(domain);
		return new MavBuilder("modify")
			.addObject("source", "createHomepage")
			.addObject("item", item)
			.mav();
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
