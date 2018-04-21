package com.chinadovey.parking.webapps.biz.system;

import java.util.List;
import java.util.Map;

import com.chinadovey.parking.core.secu.SecuObject;
import com.chinadovey.parking.webapps.pojo.Authority;
import com.chinadovey.parking.webapps.pojo.Park;
import com.chinadovey.parking.webapps.pojo.Role;
import com.chinadovey.parking.webapps.pojo.User;

/**
 * 用户业务层接口
 * Copyright ©2014 中瑞华清（北京）智能科技有限公司  http://zhrhq.com
 * @author 王生栋
 * 2015-2-14 下午4:56:37
 */
public interface UserBiz {
	
	/**
	 * 根据id查找用户
	 * @author 王生栋
	 * @param id 用户的id
	 */
	public User find(int id);
	public User findcompanyid(String username);
	/**
	 * 保存用户
	 * @author 王生栋
	 * @param user 用户对象
	 */
	public void save(User user);
	
	/**
	 * 更新用户
	 * @author 王生栋
	 * @param user 用户对象
	 */
	public void update(User user);
	
	/**
	 * 删除用户
	 * @author 王生栋
	 * @param id 用户id
	 */
	public void delete(int id);
	
	/**
	 * 批量删除用户
	 * @author 王生栋
	 * @param ids
	 */
	public void batchDelete(int[] ids);
	
	/**
	 * 判断编码是否存在
	 * @param name
	 * @return
	 * @auther 王生栋
	 */
	boolean isNameExit(String name);
	
	/**
	 * @author 王生栋
	 * @param name
	 * @param orName
	 * @return
	 */
	boolean isNameExitExcept(String name , String orName);
	
	/**
	 * 获取用户所有权限
	 * @author 王生栋
	 * @param userId
	 * @return
	 */
	public List<Authority> getUserAuthoritys(Integer userId);
	
	/**
	 * 
	 * @author 王生栋
	 * @param username
	 * @param password
	 * @return
	 */
	public SecuObject getSecuObject(String username,String password);

	/**
	 * 根据用户名手机号密码得到user对象
	 * @param username 用户名或手机号
	 * @param password 密码
	 * @return
	 */
	public User getUser(String username,String password);
	
	/**
	 * 根据手机号和密码得到user对象
	 * @param username
	 * @param password
	 * @return
	 */
	public User getUserByMobile(String username,String password);
	
	/**
	 * 根据id得到用户角色id
	 * @param id
	 * @return
	 */
	int[] getUserRoles(int id);

	/**
	 * 根据用户id得到角色列表
	 * @param id
	 * @return
	 */
	List<Role> getRolesByUserId(int id);

	/**
	 * 得到用户创建的角色列表
	 * @param id
	 * @return
	 */
	List<Role> getRolesByCreateUserId(int id);
	
	/**
	 * 根据id判断名称是否存在，若存在，则根据名称判断
	 * @param name
	 * @param id
	 * @return
	 */
	boolean isNameExitById(String name, int id);

	/**
	 * 根据id得到不是用户所属的角色列表
	 * @param id
	 * @return
	 */
	List<Role> getNoRolesByUserId(int id);

	/**
	 * 根据id和密码判断是否正确
	 * @param id
	 * @param password
	 * @return
	 */
	boolean isPasswordCorrect(int id, String password);
	
	/**
	 * 根据id更新密码
	 * @param id
	 * @param password
	 * @return
	 */
	boolean updatePassword(int id,String password);
	
	/**
	 * 根据用户得到parkId
	 * @param user
	 * @return
	 */
	int getParkIdByUser(User user);
	
	/**
	 * 根据用户得到所有parkId
	 * @param user
	 * @return
	 */
	List<Integer> getParkIdsByUser(User user);

	/**
	 * 根据管理员id关联停车场
	 * @param _parks
	 * @param id 
	 */
	void linkParks(List<Park> _parks, int id);

	/**
	 * 分页查询
	 * @author 王生栋
	 * @param page 当前页
	 * @param rows 每页显示记录数
	 * @return
	 */
	Map<String , Object> getList(int page , int rows);
	
	/**
	 * 分页查询
	 * @param page
	 * @param rows
	 * @param search
	 * @return
	 */
	Map<String, Object> getList(int page, int rows, String search);

	/**
	 * 分页查询
	 * @param page 当前页
	 * @param rows 每页显示记录数
	 * @param search 进行查询的关键字
	 * @param sort 排序的字段
	 * @param order 排序的顺序
	 * @return
	 */
	Map<String, Object> getList(int page, int rows, String search, String sort,
			String order);

	/**
	 * 得到商户列表
	 * @param page
	 * @param rows
	 * @param search
	 * @param sort
	 * @param order
	 * @return
	 */
	public Map<String, Object> getBusinessList(int page, int rows, String search, String sort, String order);

	Map<String, Object> getList(int page, int rows, String search, String sort, String order, List<Integer> userIds);

	List<Integer> getUserRolesList(int id);

	/**
	 * 判断是否存在该手机号的用户
	 * @param mobile
	 * @return
	 */
	public boolean isMobileExit(String mobile);

	/**
	 * 根据手机号得到用户
	 * @param mobile
	 * @return
	 */
	public User getUserByMobile(String mobile);

	/**
	 * 删除根据id停车场
	 * @param _parks
	 * @param id
	 */
	public void removeParks(List<Park> _parks, int id);

	/**
	 * 根据手机号更新用户
	 * @param user
	 */
	public void updateByMobile(User user);

	/**
	 * 更新商家id
	 * @param id
	 * @param businessIds
	 */
	public void updateBusinessId(Integer id, Integer[] businessIds);
	

	/**
	 * 查找商家id
	 * @param id
	 * @param businessIds
	 */
	List<Integer> getBusinessIdsByUser(User user);
	
	public void updateUser(User user);
	
	List<Integer> getUserIdsByParkId(int id);
	
	List<User> getAll();
	/**
	 * 初始化密码
	 * @param id
	 */
	public void resetPassword(int id);
}