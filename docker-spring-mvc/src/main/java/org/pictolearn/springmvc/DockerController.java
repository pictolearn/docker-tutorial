package org.pictolearn.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/docker")
public class DockerController {

	@RequestMapping(method=RequestMethod.GET)
	public String sayHello(ModelMap model) {
		model.addAttribute("greeting", "Spring MVC Container running on docker");
		return "welcome";
	}
}
