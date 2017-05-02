package com.xxx.webapp.struts2.Filter;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.ServletContext;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerRender {
	private Configuration configuration = null;
	
	FreeMarkerRender(ServletContext servletContext) {
		configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");
		configuration.setDefaultEncoding("ISO-8859-1");
		configuration.setServletContextForTemplateLoading(servletContext, null);
	}
	
	public boolean render(String filename, Map<String, Object> dataMap, Writer out) {
		
		try {
			String userName = new String(((String) dataMap.get("userName")).getBytes("utf-8"), "iso-8859-1");
			dataMap.put("userName", userName);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			Template t = configuration.getTemplate(filename);
			try {
				t.process(dataMap, out);
				return true;
			} catch (TemplateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}