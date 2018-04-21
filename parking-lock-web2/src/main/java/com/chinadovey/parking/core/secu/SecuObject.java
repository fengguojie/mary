package com.chinadovey.parking.core.secu;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.chinadovey.parking.webapps.pojo.Authority;
import com.chinadovey.parking.webapps.pojo.User;

/**
 * 用户登录信息操作辅助类
 * @author Bean
 *
 */
public class SecuObject {

	private User user;
	private Map<String, SecuResource> resources;

	public User getUser() {
		return user;
	}

	private SecuObject(User user,List<Authority> auths) {
		this.user = user;
		resources = new HashMap<String, SecuResource>();
		for (Authority auth : auths) {
			if (!resources.containsKey(auth.getResource())) {
				resources.put(auth.getResource(), new SecuResource());
			}
			resources.get(auth.getResource()).getOperations()
					.add(auth.getOperation());
		}
	}

	/**
	 * 创建一个对象
	 * @param user
	 * @param auths
	 * @return
	 */
	public static SecuObject create(User user,List<Authority> auths) {
		return new SecuObject(user,auths);
	}

	/**
	 * 如果用户是否拥有权限返回真
	 * @param resource 资源
	 * @param operation 操作
	 * @return
	 */
	public boolean validate(String resource, String operation) {
		return (resources.containsKey(resource) && resources.get(resource)
				.getOperations().contains(operation))
				|| (resources.containsKey("*"))
				|| (resources.containsKey(resource) && resources.get(resource)
						.getOperations().contains("*"));
	}
	
	public Integer getUserKey(){
		return user.getId();
	}
	
	public String getDisplay(){
		return user.getRealname();
	}
}

class SecuResource {
	
	private String resource;
	private Set<String> operations;
	
	SecuResource(){
		operations = new HashSet<String>();
	}
	
	String getResource() {
		return resource;
	}
	void setResource(String resource) {
		this.resource = resource;
	}
	Set<String> getOperations() {
		return operations;
	}
	void setOperations(Set<String> operations) {
		this.operations = operations;
	}
}