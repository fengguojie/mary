package com.chinadovey.parking.webapps.biz.system;

import java.util.List;
import java.util.Map;

import com.chinadovey.parking.webapps.pojo.Authority;
import com.chinadovey.parking.webapps.pojo.Role;

/**
 * 角色业务层接口
 * Copyright ©2014 中瑞华清（北京）智能科技有限公司  http://zhrhq.com
 * @author 王生栋
 * 2015-2-13 下午4:01:19
 */
public interface RoleBiz {
	/**
	 * 分页查询
	 * @author 王生栋
	 * @param page 当前页
	 * @param rows 每页显示记录数
	 * @return
	 */
	Map<String , Object> getList(int page , int rows);
	
	/**
	 * 根据id查找角色
	 * @author 王生栋
	 * @param id 角色的id
	 */
	public Role find(int id);
	
	/**
	 * 保存角色
	 * @author 王生栋
	 * @param role 角色对象
	 */
	public void save(Role role);
	
	/**
	 * 更新角色
	 * @author 王生栋
	 * @param role 角色对象
	 */
	public void update(Role role);
	
	/**
	 * 删除角色
	 * @author 王生栋
	 * @param id 角色id
	 */
	public void delete(int id);
	
	/**
	 * 批量删除角色
	 * @author 王生栋
	 * @param ids
	 */
	public void batchDelete(int[] ids);
	
	/**
	 *  获取所有（不分页）
	 * @author 王生栋
	 * @return
	 */
	public List<Role> getAll();

	/**
	 * 得到角色拥有的权限
	 * @return
	 */
	List<Authority> getAutoritys(int roleId);
	
	/**
	 * 根据角色id获得拥有的resource
	 * @param id
	 * @return
	 */
	String[] getAutorityResources(int roleId);

	/**
	 * 得到角色拥有的权限名称
	 * @return
	 */
	String [] getAutorityNames(int roleId);

	/**
	 * 根据名称判断是否存在
	 * @param name
	 * @return
	 */
	boolean isNameExit(String name);

	/**
	 * 根据id判断是否存在,若不存在，则根据姓名判断
	 * @param name
	 * @param id
	 * @return
	 */
	boolean isNameExitById(String name,int id);

	Map<String, Object> getList(int page, int rows, String search);
	/**
	 * 显示全部角色列表
	 * 
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @param search
	 * @return
	 */
	Map<String, Object> getList(int page, int rows, String sort, String order, String search);
	/**
	 * 显示非管理员用户关联的角色列表
	 * 
	 * @param roleIds
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @param search
	 * @return
	 */
	public Map<String, Object> getList(List<Integer>roleIds, int page, int rows, String sort, String order, String search);

	List<Authority> getAutoritesByUserId(int userId);
	/**
	 * 根据角色查询对应的所有权限
	 * 
	 * @param roleIds
	 * @return
	 */
	List<Authority> getInfoByRoleIds(List<Integer> roleIds);
	/**
	 * 根据userId获取角色集合
	 * @param userId
	 * @return
	 */
	List<Role> getByUserId(Integer userId);
	
	
}
