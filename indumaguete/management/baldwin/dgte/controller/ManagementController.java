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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import baldwin.dgte.model.Article;
import baldwin.dgte.model.Item;
import baldwin.dgte.service.ManagementService;
import baldwin.dgte.service.ManagementService;
import baldwin.dgte.utils.DgteUtil;

@Controller @RequestMapping("/manage") @SessionAttributes({"item", "article"})
public class ManagementController {
	static Logger log = Logger.getLogger(ManagementController.class);

	@Autowired ManagementService adminService;
	@Autowired DgteUtil dgteUtil;

	@RequestMapping({"/{domain}", "/{domain}/"})
	public ModelAndView manageItem(@PathVariable String domain) {
		ModelAndView m = new ModelAndView();
		Item item = adminService.getIfEditable(domain);

		log.debug("Edit flow started for " + domain);

		if(null == item) {
			m.setViewName("error");
			m.addObject("message", domain + " does not exist or you do not have permission to edit this item. ");
			m.addObject("domain", domain);
		} else {
			m.setViewName("edit");
			m.addObject("item", item.htmlFriendly());
		}

		return m;
	}

	/*
	 * Edit flow functions
	 */
	@RequestMapping("/{domain}/edit/primary/")
	public ModelAndView editPrimaryDetailsScreen(@PathVariable String domain) {
		ModelAndView m = new ModelAndView();
		Item item = adminService.getIfEditable(domain);
		m.setViewName("editprimary");
		m.addObject("item", item.htmlFriendly());
		return m;
	}

	@RequestMapping("/{domain}/edit/primary/submit/")
	public @ResponseBody Map<String, String> validateEditedPrimaryDetails(@RequestBody Item newItem, @ModelAttribute Item item) {
		Map<String, String> response = new HashMap<String, String>();

		String violatedKeyword = dgteUtil.checkAgainstReservedKeywords(newItem.getDomain());
		if(null != violatedKeyword) {
			response.put("status", "failed");
			response.put("message", "Reserved keyword " + violatedKeyword + " must not be used in your domain.");
		} else {
			try {
				adminService.update(item, newItem);
				response.put("status", "success");
			} catch (Exception e) {
				response.put("status", "failed");
				response.put("message", e.getMessage());
			}
		}

		return response;
	}

	@RequestMapping("/{domain}/edit/homepage/") 	
	public ModelAndView editHomepage(@PathVariable String domain) {
		ModelAndView m = new ModelAndView();

		log.info("Editing homepage requested for " + domain);

		Item item = adminService.getIfEditable(domain);
		if(null == item) {
			m.setViewName("error");
			m.addObject("message", "Item not found with domain: " + domain);
		} else {
			m.setViewName("edithomepage");
			m.addObject("item", item.htmlFriendly());
		}

		return m;
	}

	@RequestMapping("/{domain}/addpage/")
	public ModelAndView newPageScreen(@ModelAttribute Item item, String ancestry) {
		log.info("Add page requested for " + item.getDomain() + ". Ancestry: " + ancestry);

		ModelAndView m = new ModelAndView("addpage");
		String[] ancestryArray = ancestry.split("/");
		m.addObject("ancestry", ancestry);
		m.addObject("ancestryArray", ancestryArray);
		m.addObject("item", item);
		return m;
	}

	/**
	 * @param ancestry - full ancestry line starting from item domain format is Baldwinternet/Deliveries/International
	 * @param domain - domain used in URL
	 * @param title - article title, used in sidebar navigation
	 * @param content - actual content
	 * @return
	 */
	@RequestMapping("/{domain}/addpage/save/")
	public @ResponseBody Map<String, Object> newArticle(String ancestry, String domain, String title, String content, ModelMap model) {
		log.info("Page save: " + domain + " at " + ancestry);

		Map<String, Object> m = new HashMap<String, Object>();
		try {
			Article article = adminService.newArticle(ancestry, title, domain, content);
			m.put("status", "success");
			m.put("domain", domain);
			m.put("path", ancestry + "/" + domain);
		} catch (Exception e) {
			m.put("message", "Article creation failed. " + e.getMessage() + ". Please try again");
		}

		return m;
	}
	
	@RequestMapping("/{domain}/editpage/{pageDomain}/")
	public ModelAndView editPage(@PathVariable String domain, @PathVariable String pageDomain, @ModelAttribute Item item, ModelMap model) {
		//1. check if pageDomain exists 
		//2. check if user may edit item
		//3. let it be edited
		ModelAndView m = new ModelAndView();

		Article article = adminService.getArticle(pageDomain);

		if(article != null) {
			m.setViewName("editpage");
			m.addObject("item", item.htmlFriendly());
			model.put("article", article);
		} else {
			m.setViewName("error");
			m.addObject("message", "No page found with domain " + pageDomain);
		}

		return m;
	}

	@RequestMapping("/{domain}/editpage/{pageDomain}/save/")
	public @ResponseBody Map<String, String> editPageSubmit(@ModelAttribute Article article, @PathVariable String domain, @PathVariable String pageDomain, String newDomain, String newTitle, String content) {
		log.info("Update requested to " + article.getTitle() + " Domain: " + article.getDomain() + "->" + newDomain + " Title: " + article.getTitle() + "->" + newTitle);
		
		Map<String, String> response = new HashMap<String, String>();
		String status  = adminService.updateArticle(article, domain, newDomain, newTitle, content);
		response.put("status", status); //expecting "success"
		return response;
	}
}
