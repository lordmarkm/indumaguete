package test.springforms.controller;

import org.springframework.web.servlet.ModelAndView;

public class MavBuilder {
	ModelAndView mav;
	
	public MavBuilder(String view) {
		mav = new ModelAndView(view);
	}
	
	public MavBuilder addObject(String name, Object object) {
		mav.addObject(name, object);
		return this;
	}
	
	public ModelAndView mav() {
		return mav;
	}
}
