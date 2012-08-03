package test.springforms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import test.springforms.model.TestForm;

@Controller @RequestMapping("/form")
public class FormsController {
	static Logger log = LoggerFactory.getLogger(FormsController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getForm(TestForm testForm) {
		log.info("Get request received. TestForm: " + testForm);
		
		ModelAndView mav = new MavBuilder("formpage").mav();
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView submitForm(TestForm testForm, BindingResult bindingResult) throws Exception {
		log.info("Post request received. TestForm: " + testForm);
	
		if(!bindingResult.hasErrors()) {
			ModelAndView mav = new MavBuilder("resultspage")
									.addObject("testform", testForm).mav();
			return mav;
		} else {
			throw new Exception("It didn't work.");
		}
	}
}
