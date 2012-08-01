package baldwin.dgte.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public interface ViewItemController {
	@RequestMapping({"/{domain}", "/{domain}/"})
	public ModelAndView view(@PathVariable String domain, HttpServletRequest request);
	
	/**
	 * Show the view all screen with first 10 recently modified results
	 */
	@RequestMapping("/viewall/")
	public ModelAndView viewAllBusinesses();
}
