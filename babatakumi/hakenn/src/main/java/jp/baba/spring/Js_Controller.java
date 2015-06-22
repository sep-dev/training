package jp.baba.spring;

import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class Js_Controller {
	@Controller
	public class JqueryController {

	    @RequestMapping(value = "/menu/sample", method = RequestMethod.GET)
	    public String js(HttpServletResponse response, Locale locale, Model model) {
	        return "sample.js";
	    }
	}
}
