package baldwin.dgte.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import baldwin.dgte.model.Article;
import baldwin.dgte.model.Dude;
import baldwin.dgte.model.Item;
import baldwin.dgte.service.ReadOnlyService;
import baldwin.dgte.utils.DgteUtil;
import baldwin.dgte.utils.DgteUtil.Filter;

/**
 * This controller must be stateless
 * 
 * @author user
 */
@Controller
public class ReadOnlyController {
	Dude user;
	
	@Autowired ReadOnlyService readOnlyService;
	@Autowired DgteUtil dgteUtil;
	
	/**
	 * When user wants to load 10 more items
	 */
	@RequestMapping("/viewall/loadmore/")
	public @ResponseBody List<Item> loadMore(Filter filter, int firstResult, int maxResult) {
		return readOnlyService.getAllItems(filter, firstResult, maxResult);
	}
	
	@RequestMapping({"/{domain}/page/{articleDomain}", "/{domain}/page/{articleDomain}/"})
	public ModelAndView view(@PathVariable String domain, @PathVariable String articleDomain, HttpServletRequest request) {
		ModelAndView m = new ModelAndView();
		
		Item item = readOnlyService.getItem(domain);
		Article article = readOnlyService.getArticle(articleDomain);
		
		if(null == item) {
			m.setViewName("error");
			m.addObject("message", "No such item: " + domain);
		} else if(null == article) {
			m.setViewName("error");
			m.addObject("message", "No such article for " + domain + ": " + articleDomain);
		} else {
			m.setViewName("viewarticle");
			m.addObject("item", item.htmlFriendly());
			m.addObject("article", article);
		}
		return m;
	}
	
	@RequestMapping({"/getArticles/{domain}/", "/getArticles/{domain}"})
	public @ResponseBody Article getArticles(@PathVariable String domain, HttpServletRequest request) {
		return readOnlyService.getArticle(domain);
	}
}
