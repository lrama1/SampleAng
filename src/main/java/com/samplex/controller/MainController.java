package com.samplex.controller;

import java.io.StringWriter;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceResourceBundle;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerMapping;

import com.samplex.util.ExposedResourceBundleMessageSource;

@RestController
public class MainController {
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	protected ExposedResourceBundleMessageSource messageSource;

	@RequestMapping("/home")
	public String showHomePage() {
		return "index";
	}

	@RequestMapping("/resources/js/.templates/**")
	public String getTemplate(HttpServletRequest request) {
		String restOfURL = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		//restOfURL = restOfURL.replaceFirst("\\.", "").replaceFirst("\\/js", "");
		restOfURL = restOfURL.replaceFirst("\\.", "");
		String actualURL = "/WEB-INF" + restOfURL + ".jsp";
		return actualURL;
	}

	/*  removed 'produces' when I upgraded to Spring 3.2 from 3.1.x
		@RequestMapping(value = "/localizedmessages.js", method = RequestMethod.GET, produces="application/javascript;charset=UTF-8")*/
	@RequestMapping(value = "/localizedmessages.js", method = RequestMethod.GET)
	public String getMessages(Locale locale) {
		StringWriter stringWriter = new StringWriter();

		logger.info("Getting localized messages from the server." + locale);
		stringWriter.append("define(function() {");
		stringWriter.append("var localizedMessages = {");

		//stringWriter.append("welcome :" +  "\"Welcome To Localized Greeting Boo ya \"");
		String baseName = messageSource.getBaseName().indexOf(":") == -1 ? messageSource.getBaseName()
				: messageSource.getBaseName().split("\\:")[1];
		ResourceBundle bundle = MessageSourceResourceBundle.getBundle(baseName, locale);
		boolean firstEntry = true;
		for (String key : bundle.keySet()) {
			if (!firstEntry) {
				stringWriter.write(", ");
			}
			stringWriter.write("\"" + key + "\" : " + "\"" + bundle.getObject(key) + "\"");
			firstEntry = false;
		}

		stringWriter.append("};");
		//stringWriter.append("return localizedMessages;");
		stringWriter.append("var messages = function(key, valuesToPlugin){ " + "	if(valuesToPlugin !== undefined){ "
				+ "		var messageTemplate = localizedMessages[key]; " + "		if(messageTemplate === undefined){ "
				+ "			return 'LABEL_NOT_FOUND'; " + "		} "
				+ "		for(var i = 0; i < valuesToPlugin.length; i++){ "
				+ "			messageTemplate = messageTemplate.replace('{' + i + '}', valuesToPlugin[i]); " + "		} "
				+ "		return messageTemplate; " + "	}else{ " + "		return localizedMessages[key]; " + "	} "
				+ "}; " + "return messages; ");

		stringWriter.append("});");
		logger.info(stringWriter.toString());

		return stringWriter.toString();
	}
}
