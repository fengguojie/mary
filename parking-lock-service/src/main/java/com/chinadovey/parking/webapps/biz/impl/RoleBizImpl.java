package com.chinadovey.parking.webapps.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinadovey.parking.core.supports.mybatis.pagination.PageContext;
import com.chinadovey.parking.core.supports.mybatis.pagination.Pagination;
import com.chinadovey.parking.webapps.biz.system.RoleBiz;
import com.chinadovey.parking.webapps.biz.system.UserBiz;
import com.chinadovey.parking.webapps.mappers.gen.AuthorityMapper;
import com.chinadovey.parking.webapps.mappers.gen.RoleMapper;
import com.chinadovey.parking.webapps.mappers.gen.UserRelRoleMapper;
import com.chinadovey.parking.webapps.pojo.Authority;
import com.chinadovey.parking.webapps.pojo.AuthorityExample;
import com.chinadovey.parking.webapps.pojo.Role;
import com.chinadovey.parking.webapps.pojo.RoleExample;
import com.chinadovey.parking.webapps.pojo.UserRelRole;
import com.chinadovey.parking.webapps.pojo.UserRelRoleExample;
import com.chinadovey.parking.webapps.utils.StringUtil;

@Service
public class RoleBizImpl implements RoleBiz {
	@Autowired
	private RoleMapper mapper;
	@Autowired
	private AuthorityMapper authMapper;

	@Autowired
	private UserRelRoleMapper userRelRoleMapper;

	@Autowired
	private UserBiz userBiz;

	@Override
	public Role find(int id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public void save(Role role) {
		role.setCreateTime(new Date());
		mapper.insertSelective(role);
		insertAuthByRole(role);
	}

	@Override
	public void update(Role role) {
		mapper.updateByPrimaryKeySelective(role);

		// 根据角色删除权限
		deleteAuthByRoleId(role.getId());

		// 插入关联权限
		insertAuthByRole(role);
	}

	/**
	 * 根据角色插入所有权限
	 * 
	 * @param role
	 */
	private void insertAuthByRole(Role role) {
		if (role.getAuthoritys() != null && !role.getAuthoritys().isEmpty()) {
			for (Authority auth : role.getAuthoritys()) {
				auth.setRoleId(role.getId());
				auth.setId(null);
				authMapper.insert(auth);
			}
		}
	}

	/**
	 * 删除角色所有权限
	 * 
	 * @param role
	 */
	private void deleteAuthByRoleId(int id) {
		AuthorityExample example = new AuthorityExample();
		example.createCriteria().andRoleIdEqualTo(id);
		authMapper.deleteByExample(example);
	}

	@Override
	public void delete(int id) {
		// 删除权限表中角色信息
		deleteAuthByRoleId(id);

		// 删除用户与角色管理
		deleteUserByRoleId(id);

		// 删除角色
		mapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据角色删除关联的用户
	 * 
	 * @param id
	 */
	private void deleteUserByRoleId(int id) {
		UserRelRoleExample userRelRoleExample = new UserRelRoleExample();
		userRelRoleExample.createCriteria().andRoleIdEqualTo(id);
		userRelRoleMapper.deleteByExample(userRelRoleExample);
	}

	@Override
	public Map<String, Object> getList(int page, int rows) {
		return getList(page, rows, null, null, null);
	}

	@Override
	public Map<String, Object> getList(int page, int rows, String search) {
		return getList(page, rows, null, null, search);
	}

	@Override
	public Map<String, Object> getList(int page, int rows, String sort, String order, String search) {
		RoleExample example = new RoleExample();
		if (page <= 0 || rows <= 0) {
			page = 0;
			rows = 10;
		}
		// 进行排序
		if (sort != null && !sort.isEmpty()) {
			example.setOrderByClause(StringUtil.changeOrderStr(sort) + " " + order);
		}
		// 进行模糊查询
		if (search != null && !search.isEmpty()) {
			example.or().andNameLike("%" + search + "%");
		}

		Map<String, Object> map = new HashMap<String, Object>();
		Pagination<?> pagin = PageContext.initialize(page, rows);
		List<Role> list = mapper.selectByExample(example, pagin.getRowBounds());
		map.put("rows", list);
		map.put("total", pagin.getTotalRows());
		return map;
	}

	@Override
	public void batchDelete(int[] ids) {
		List<Integer> idList = new ArrayList<Integer>();
		for (int i = 0; i < ids.length; i++) {
			idList.add(ids[i]);
		}
		RoleExample example = new RoleExample();
		example.createCriteria().andIdIn(idList);
		mapper.deleteByExample(example);
	}

	@Override
	public List<Role> getAll() {
		return mapper.selectByExample(null);
	}

	@Override
	public List<Authority> getAutoritys(int roleId) {
		AuthorityExample example = new AuthorityExample();
		example.createCriteria().andRoleIdEqualTo(roleId);
		return authMapper.selectByExample(example);
	}

	@Override
	public String[] getAutorityResources(int roleId) {
		List<Authority> authoritys = getAutoritys(roleId);
		String[] resources = new String[authoritys.size()];
		for (int i = 0; i < resources.length; i++) {
			resources[i] = authoritys.get(i).getResource();
		}
		return resources;
	}
	
	@Override
	public String[] getAutorityNames(int roleId) {
		List<Authority> authoritys = getAutoritys(roleId);
		String[] names = new String[authoritys.size()];
		for (int i = 0; i < names.length; i++) {
			names[i] = authoritys.get(i).getName();
		}
		return names;
	}
	

	@Override
	public List<Authority> getAutoritesByUserId(int userId) {
		List<Integer> roleIds = userBiz.getUserRolesList(userId);
		List<Authority> authorities = getAutoritysByRoleIds(roleIds);
		return authorities;
	}

	private List<Authority> getAutoritysByRoleIds(List<Integer> roleIds) {
		AuthorityExample example = new AuthorityExample();
		example.createCriteria().andRoleIdIn(roleIds);
		List<Authority> authorities = authMapper.selectByExample(example);
		List<Authority> authorities2 = new ArrayList<Authority>();
		Iterator<Authority> iterator =  authorities.iterator();
		while(iterator.hasNext()){
			Authority authority = iterator.next();
			for(Authority authority2 :authorities2){
				if(authority.getResource().equals(authority2.getResource())){
					iterator.remove();
					break;
				}
			}
			authorities2.add(authority);
		}
		return authorities;
	}

	@Override
	public boolean isNameExit(String name) {
		RoleExample example = new RoleExample();
		example.createCriteria().andNameEqualTo(name);
		return mapper.countByExample(example) > 0;
	}

	@Override
	public boolean isNameExitById(String name, int id) {
		if (mapper.selectByPrimaryKey(id).getName().equals(name)) {
			return false;
		}
		return isNameExit(name);
	}

	@Override
	public List<Authority> getInfoByRoleIds(List<Integer> roleIds) {
		AuthorityExample example = new AuthorityExample();
		example.createCriteria().andRoleIdIn(roleIds);
		List<Authority> authorities = authMapper.selectByExample(example);
		List<Authority> authorities2 = new ArrayList<Authority>();
		Iterator<Authority> iterator =  authorities.iterator();
		while(iterator.hasNext()){
			Authority authority = iterator.next();
			for(Authority authority2 :authorities2){
				if(authority.getResource().equals(authority2.getResource())){
					iterator.remove();
					break;
				}
			}
			authorities2.add(authority);
		}
		return authorities;
	}
	/**
	 * 根据userId获取角色集合
	 * @param userId
	 * @return
	 */
	@Override
	public List<Role> getByUserId(Integer userId) {
		
		List<Integer> roleIds = new ArrayList<Integer>();
		
		UserRelRoleExample e=new UserRelRoleExample();
		e.createCriteria().andUserIdEqualTo(userId);
		List<UserRelRole> urrs = userRelRoleMapper.selectByExample(e);
		if(urrs!=null && !urrs.isEmpty()){
			for(UserRelRole urr:urrs){
				roleIds.add(urr.getRoleId());//拿到用户对应的roleId
			}
		}
		//根据roleId,到角色表查询相应数据
		RoleExample re = new RoleExample();
		re.createCriteria().andIdIn(roleIds);
		List<Role> roles = mapper.selectByExample(re);
		return roles;
	}

	@Override
	public Map<String, Object> getList(List<Integer> roleIds, int page, int rows, String sort, String order, String search) {
		RoleExample example = new RoleExample();
		if (page <= 0 || rows <= 0) {
			page = 0;
			rows = 10;
		}
		// 进行排序
		if (sort != null && !sort.isEmpty()) {
			example.setOrderByClause(StringUtil.changeOrderStr(sort) + " " + order);
		}
		if(roleIds != null && !roleIds.isEmpty()){
			example.createCriteria().andIdIn(roleIds);
		}
		// 进行模糊查询
		if (search != null && !search.isEmpty()) {
			example.or().andNameLike("%" + search + "%");
		}

		Map<String, Object> map = new HashMap<String, Object>();
		Pagination<?> pagin = PageContext.initialize(page, rows);
		List<Role> list = mapper.selectByExample(example, pagin.getRowBounds());
		map.put("rows", list);
		map.put("total", pagin.getTotalRows());
		return map;
	}


}
