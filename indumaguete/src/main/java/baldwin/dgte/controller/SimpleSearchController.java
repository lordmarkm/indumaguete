package baldwin.dgte.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import baldwin.dgte.model.Item;
import baldwin.dgte.service.SearchService;
import baldwin.dgte.utils.DgteUtil;
import baldwin.dgte.utils.MavBuilder;

@Controller @RequestMapping("/simplesearch/")
public class SimpleSearchController {
	static Logger log = LoggerFactory.getLogger(SimpleSearchController.class);
	
	@Autowired 
	SearchService searchService;
	
	@Autowired 
	DgteUtil dgteUtil;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView search(String term, HttpServletRequest request) {
		log.info("Search page requested by {}", request.getRemoteAddr());
		return new MavBuilder("simplesearch")
			.addObject("categories", dgteUtil.getSimpleCategories(DgteUtil.CATEGORIES_NO_GROUPS))
			.mav();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody List<Item> search(String term, String category) {
		log.info("Search requested: [{}] in {}", term, category);
		return searchService.simpleSearch(term, category);
	}
}