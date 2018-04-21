package com.chinadovey.parking.core.secu;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Controller;

import com.chinadovey.parking.core.annotation.SecurityAccessCheckable;
import com.chinadovey.parking.core.annotation.SecurityAclDesc;
import com.chinadovey.parking.webapps.pojo.Authority;

/**
 * 系统权限加载工具类
 * @author Bean
 *
 */
public class SystemAuthorityHelper {

	private static Logger logger = Logger.getLogger(SystemAuthorityHelper.class);
	
	/**
	 * 返回系统中的控制器权限控制对象
	 * @return
	 */
	public static List<Authority> searchControllerAuthority() {
		List<Authority> auths = new ArrayList<Authority>();
		auths.add(new Authority() {
			{
				setName("所有权限");
				setResource("*");
				setOperation("*");
				setDescription("所有系统权限");
			}
		});
		Set<String> keys = new HashSet<String>();
		Authority auth = null;
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			Resource[] resources = resolver
					.getResources("classpath*:com/chinadovey/parking/webapps/controller/**/*.class");

			for (Resource resource : resources) {
				String classname = resource.getURL().toString();
				classname = classname.split("(classes/)|(!/)")[1];
				classname = classname.replace("/", ".");
				classname = classname.replace(".class", "");
				Class<?> controllerClass = Class.forName(classname);
				if (controllerClass.isAnnotationPresent(Controller.class)) {
					
					if (logger.isDebugEnabled())
						logger.debug("Matched Controller:" + classname);
					
					for (Method method : controllerClass.getMethods()) {
						
						SecurityAccessCheckable anno = method.getAnnotation(SecurityAccessCheckable.class);
						
						if (anno != null) {
							SecurityAclDesc acldesc = anno.resource().getAnnotation(SecurityAclDesc.class);
							if(acldesc!=null){
								if (logger.isDebugEnabled())
									logger.debug("\t" + method.getName() + ":" + acldesc.value());

								String key = anno.resource() + "+" + anno.operation().name();
								if (keys.contains(key)) {
									continue;
								}

								auth = new Authority();
								auth.setResource(anno.resource().getName());
								auth.setOperation(anno.operation().name());
								auth.setName(acldesc.value());
								auths.add(auth);
								keys.add(key);
							}
						}
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		return auths;
	}
}
