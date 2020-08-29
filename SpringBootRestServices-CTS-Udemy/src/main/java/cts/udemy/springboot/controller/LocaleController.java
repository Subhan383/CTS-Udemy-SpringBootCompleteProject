package cts.udemy.springboot.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocaleController {
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	@GetMapping("/i18N")
	public String getMessagesInI18NFormat(@RequestHeader(name="Accept-language",required=false) String locale) {
		
		return messageSource.getMessage("label.hello", null,new Locale(locale));
	}

}
