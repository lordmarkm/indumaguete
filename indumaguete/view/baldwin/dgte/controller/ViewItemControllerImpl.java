package baldwin.dgte.controller;

import java.util.List;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import baldwin.dgte.model.Item;
import baldwin.dgte.service.ViewItemService;
import baldwin.dgte.utils.DgteUtil;
import baldwin.dgte.utils.DgteUtil.Filter;

@Named
public class ViewItemControllerImpl implements ViewItemController {
	@Autowired private ViewItemService viewItemService;
	@Autowired private DgteUtil dgteUtil;
	
	@Override
	public ModelAndView view(@PathVariable String domain, HttpServletRequest request) {
		ModelAndView m = new ModelAndView();
		
		Item item = viewItemService.getItem(domain);
		if(null == item) {
			m.setViewName("error");
			m.addObject("message", "No such item: " + domain);
		} else {
			m.setViewName("view");
			m.addObject("item", item.htmlFriendly());
		}
		
		return m;
	}
	
	@Override
	public ModelAndView viewAllBusinesses() {
		ModelAndView m = new ModelAndView("viewall");
		List<Item> items = viewItemService.getAllItems(Filter.recent, 0, 10);
		items = dgteUtil.htmlFriendly(items);
		m.addObject("items", items);
		return m;
	}
}
