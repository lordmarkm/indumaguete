package baldwin.dgte.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import baldwin.dgte.service.SearchService;
import baldwin.dgte.utils.DgteUtil;

@Controller
public class SearchController {
	static Logger log = Logger.getLogger(SearchController.class);
	
	@Autowired SearchService searchService;
	@Autowired DgteUtil dgteUtil;
	
	final String SEARCH_URL = "/search/";
	
	@RequestMapping(value = SEARCH_URL, method = RequestMethod.GET)
	public ModelAndView search() {
		ModelAndView m = new ModelAndView();
		m.setViewName("search");
		m.addObject("categories", dgteUtil.getCategories());
		return m;
	}
	
	@RequestMapping(value = SEARCH_URL, method = RequestMethod.POST)
	public @ResponseBody Object search(@RequestParam String term, @RequestParam String subcategory) {
		return searchService.search(term, subcategory);
	}
}
