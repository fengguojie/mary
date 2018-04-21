package com.chinadovey.parking.core.secu;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.chinadovey.parking.core.supports.spring.ApplicationContextUtils;
import com.chinadovey.parking.webapps.pojo.UiMenu;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 菜单工具加载类
 * @author Bean
 *
 */
public class MainMenu {
	private List<UiMenu> menus;

	public MainMenu(List<UiMenu> menus) {
		this.menus = menus;
	}

	public String toString() {
		try {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("menus", menus);
			FreeMarkerConfigurer configurer = ApplicationContextUtils
					.getBean(FreeMarkerConfigurer.class);
			Configuration cfg = configurer.getConfiguration();
			Template template = cfg
					.getTemplate("com/chinadovey/parking/webapps/resources/freemarker/menu/menu.ftl");
			StringWriter sw = new StringWriter();
			template.process(model, sw);
			return sw.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
