package com.chinadovey.parking.webapps.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinadovey.parking.core.secu.SecuObject;
import com.chinadovey.parking.core.supports.mybatis.pagination.PageContext;
import com.chinadovey.parking.core.supports.mybatis.pagination.Pagination;
import com.chinadovey.parking.webapps.biz.system.UserBiz;
import com.chinadovey.parking.webapps.mappers.base.AuthorityBaseMapper;
import com.chinadovey.parking.webapps.mappers.gen.CompanyMapper;
import com.chinadovey.parking.webapps.mappers.gen.ParkMapper;
import com.chinadovey.parking.webapps.mappers.gen.RoleMapper;
import com.chinadovey.parking.webapps.mappers.gen.UserMapper;
import com.chinadovey.parking.webapps.mappers.gen.UserRelBusinessMapper;
import com.chinadovey.parking.webapps.mappers.gen.UserRelParkMapper;
import com.chinadovey.parking.webapps.mappers.gen.UserRelRoleMapper;
import com.chinadovey.parking.webapps.pojo.Authority;
import com.chinadovey.parking.webapps.pojo.Company;
import com.chinadovey.parking.webapps.pojo.CompanyExample;
import com.chinadovey.parking.webapps.pojo.Park;
import com.chinadovey.parking.webapps.pojo.ParkExample;
import com.chinadovey.parking.webapps.pojo.Role;
import com.chinadovey.parking.webapps.pojo.RoleExample;
import com.chinadovey.parking.webapps.pojo.User;
import com.chinadovey.parking.webapps.pojo.UserExample;
import com.chinadovey.parking.webapps.pojo.UserRelBusinessExample;
import com.chinadovey.parking.webapps.pojo.UserRelBusiness;
import com.chinadovey.parking.webapps.pojo.UserRelParkExample;
import com.chinadovey.parking.webapps.pojo.UserRelPark;
import com.chinadovey.parking.webapps.pojo.UserRelRoleExample;
import com.chinadovey.parking.webapps.pojo.UserRelRole;
import com.chinadovey.parking.webapps.utils.SecurityUtil;
import com.chinadovey.parking.webapps.utils.StringUtil;

@Service
public class UserBizImpl implements UserBiz {

	@Resource
	private UserMapper mapper;
	@Resource
	private UserRelRoleMapper userRelRoleMapper;

	@Resource
	private RoleMapper roleMapper;

	@Resource
	private AuthorityBaseMapper zAuthMapper;

	@Resource
	private UserRelParkMapper userRelParkMapper;

	@Resource
	private UserRelBusinessMapper userRelBusinessMapper;
	@Resource
	private ParkMapper parkMapper;
	@Resource
	private CompanyMapper companyMapper;

	@Override
	public User find(int id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public void save(User user) {
		String password = SecurityUtil.MD5Encrypt(user.getPassword());
		user.setPassword(password);
		user.setCreateTime(new Date());
		mapper.insertSelective(user);
		insertRoleByUser(user);
		insertParkByUser(user);
	}

	@Override
	public void update(User user) {
		String password = user.getPassword();
		if (password != null && !password.isEmpty()) {
			password = SecurityUtil.MD5Encrypt(password);
			user.setPassword(password);
		}

		mapper.updateByPrimaryKeySelective(user);

		updateUserRoles(user);
		updateUserParks(user);
	}
	
	@Override
	public void updateUser(User user) {

		mapper.updateByPrimaryKeySelective(user);

	}

	public void updateUserRoles(User user) {
		if (user.getRoles() == null || user.getRoles().isEmpty()) {
			return;
		}
		// 删除用户角色
		deleteRoleByUser(user.getId());
		// 插入关联角色
		insertRoleByUser(user);
	}
	public void updateUserParks(User user) {
		
		// 删除用户关联的停车场
		UserRelParkExample e = new UserRelParkExample();
		e.createCriteria().andUserIdEqualTo(user.getId());
		userRelParkMapper.deleteByExample(e);
		// 插入用户关联的停车场
		String[] parkIds = user.getParkIds();
		if(parkIds != null && parkIds.length != 0){
			for(String id:parkIds){
				UserRelPark urp = new UserRelPark();
				urp.setParkId(Integer.parseInt(id));
				urp.setUserId(user.getId());
				
				userRelParkMapper.insert(urp);
			}
			
		}
	}

	@Override
	public void updateByMobile(User user) {
		UserExample userExample = new UserExample();
		userExample.createCriteria().andTelEqualTo(user.getTel());
		mapper.updateByExampleSelective(user, userExample);
	}

	@Override
	public void updateBusinessId(Integer id, Integer[] businessIds) {
		UserRelBusinessExample userRelBusinessExample = new UserRelBusinessExample();
		userRelBusinessExample.createCriteria().andUserIdEqualTo(id);
		userRelBusinessMapper.deleteByExample(userRelBusinessExample);
		if (businessIds == null) {
			return;
		}
		for (Integer businessId : businessIds) {
			UserRelBusiness userRelBusinessKey = new UserRelBusiness();
			userRelBusinessKey.setBusinessId(businessId);
			userRelBusinessKey.setUserId(id);
			userRelBusinessMapper.insertSelective(userRelBusinessKey);
		}
	}

	/**
	 * 根据用户插入角色
	 * 
	 * @param user
	 */
	private void insertRoleByUser(User user) {
		if (user.getRoles() != null && !user.getRoles().isEmpty()) {
			for (Role role : user.getRoles()) {
				UserRelRole key = new UserRelRole();
				key.setRoleId(role.getId());
				key.setUserId(user.getId());
				userRelRoleMapper.insert(key);
			}
		}
	}
	/**
	 * 根据用户插入停车场 "parkId":["10","1558"]
	 * 
	 * @param user
	 */
	private void insertParkByUser(User user) {
		String[] parkIds = user.getParkIds();
		if (user.getParkIds() != null && user.getParkIds().length != 0) {
			for(String id:parkIds){
				
				UserRelPark key = new UserRelPark();
				key.setParkId(Integer.parseInt(id));
				key.setUserId(user.getId());
				userRelParkMapper.insert(key);
			}
		}
	}

	@Override
	public void delete(int id) {
		// 删除用户角色
		deleteRoleByUser(id);
		//删除关联停车场
		deleteParkByUser(id);
		// 删除用户
		mapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据用户删除角色
	 * 
	 * @param id
	 */
	private void deleteRoleByUser(int id) {
		UserRelRoleExample example = new UserRelRoleExample();
		example.createCriteria().andUserIdEqualTo(id);
		userRelRoleMapper.deleteByExample(example);
	}
	/**
	 * 根据用户删除关联的停车场
	 * 
	 * @param id
	 */
	private void deleteParkByUser(int id) {
		UserRelParkExample example = new UserRelParkExample();
		example.createCriteria().andUserIdEqualTo(id);
		userRelParkMapper.deleteByExample(example);
	}

	@Override
	public Map<String, Object> getList(int page, int rows) {
		return getList(page, rows, null);
	}

	@Override
	public Map<String, Object> getList(int page, int rows, String search) {
		return getList(page, rows, search, null, null);
	}

	@Override
	public Map<String, Object> getList(int page, int rows, String search, String sort, String order) {
		return getList(page, rows, search, sort, order, null);

	}

	@Override
	public Map<String, Object> getList(int page, int rows, String search, String sort, String order,
			List<Integer> userIds) {
		UserExample example = new UserExample();
		// 进行分页
		if (page <= 0 || rows <= 0) {
			page = 0;
			rows = 10;
		}

		// 进行排序
		if (sort != null && !sort.isEmpty()) {
			example.setOrderByClause(StringUtil.changeOrderStr(sort) + " " + order);
		}

		if (userIds != null && userIds.size() > 0) {
			example.createCriteria().andIdIn(userIds);
		}

		// 进行模糊查询
		if (search != null && !search.isEmpty()) {
			example.createCriteria().andNameLike("%" + search + "%");
			/*example.or().andEmailLike("%" + search + "%");
			example.or().andTelLike("%" + search + "%");
			example.or().andRealnameLike("%" + search + "%");*/
		}

		Map<String, Object> map = new HashMap<String, Object>();
		Pagination<?> pagin = PageContext.initialize(page, rows);
		List<User> list = mapper.selectByExample(example, pagin.getRowBounds());
		List<User> users =new ArrayList<User>();
		//每个用户对应的角色、所属机构、所属车场。
		for(User u:list){
			UserRelRoleExample e = new UserRelRoleExample();
			e.createCriteria().andUserIdEqualTo(u.getId());
			List<UserRelRole> urrs = userRelRoleMapper.selectByExample(e);
			List<Role> roles = new ArrayList<Role>();
			if(urrs!=null && !urrs.isEmpty()){
				RoleExample re = new RoleExample();
				re.createCriteria().andIdEqualTo(urrs.get(0).getRoleId());
				roles = roleMapper.selectByExample(re);
			}else{
				Role role = new Role();
				role.setName("");
				roles.add(role);
			}
			
			//所属机构
			CompanyExample ce = new CompanyExample();
			ce.createCriteria().andIdEqualTo(u.getCompanyId());
			List<Company> coms = companyMapper.selectByExample(ce);
			if(coms.isEmpty()){
				Company c = new Company();
				c.setName("");
				coms.add(c);
			}
			
			
			//车场
			UserRelParkExample upe = new UserRelParkExample();
			upe.createCriteria().andUserIdEqualTo(u.getId());
			List<UserRelPark> urps = userRelParkMapper.selectByExample(upe);
			List<Integer> parkIds = new ArrayList<Integer>();
			for(UserRelPark p:urps){
				parkIds.add(p.getParkId());
			}
			
			ParkExample pe = new ParkExample();
			StringBuffer parkName =new StringBuffer();
			if(parkIds!=null && !parkIds.isEmpty() ){
				pe.createCriteria().andIdIn(parkIds);
				List<Park> parks = parkMapper.selectByExample(pe);
				for(int i=0;i<parks.size();i++){
					parkName.append(parks.get(i).getName());
					if(i != parks.size()-1){
						parkName.append(",");
					}
				}
			}else{
				parkName.append("");
			}
			
			User user = new User();
			user.setId(u.getId());
			user.setName(u.getName());
			user.setRealname(u.getRealname());
			user.setTel(u.getTel());
			user.setEmail(u.getEmail());
			user.setRoleName(roles.get(0).getName());
			
			user.setComName(coms.get(0).getName());
			user.setParkName(parkName.toString());
			users.add(user);
		}
		
		map.put("rows", users);
		map.put("total", pagin.getTotalRows());
		return map;

	}

	@Override
	public Map<String, Object> getBusinessList(int page, int rows, String search, String sort, String order) {
		UserRelParkExample userRelParkExample = new UserRelParkExample();
		userRelParkExample.setDistinct(true);
		List<UserRelPark> userRelParkKeys = userRelParkMapper.selectByExample(userRelParkExample);
		List<Integer> userIds = new ArrayList<Integer>();
		for (UserRelPark userRelParkKey : userRelParkKeys) {
			userIds.add(userRelParkKey.getUserId());
		}
		return getList(page, rows, search, sort, order, userIds);
	}

	@Override
	public void batchDelete(int[] ids) {
		List<Integer> idList = new ArrayList<Integer>();
		for (int i = 0; i < ids.length; i++) {
			idList.add(ids[i]);
		}
		UserExample example = new UserExample();
		example.createCriteria().andIdIn(idList);
		mapper.deleteByExample(example);
	}

	@Override
	public boolean isNameExit(String name) {
		UserExample example = new UserExample();
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
	public boolean isNameExitExcept(String name, String orName) {
		UserExample example = new UserExample();
		example.createCriteria().andNameEqualTo(name).andNameNotEqualTo(orName);
		List<User> users = mapper.selectByExample(example);
		if (users != null && !users.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public SecuObject getSecuObject(String username, String password) {

		UserExample example = new UserExample();
		example.createCriteria().andNameEqualTo(username).andPasswordEqualTo(password);

		List<User> list = mapper.selectByExample(example);
		if (list.size() != 0) {
			User user = list.get(0);
			return SecuObject.create(user, getUserAuthoritys(user.getId()));
		}

		return null;
	}

	@Override
	public User getUser(String username, String password) {
		UserExample example = new UserExample();
		password = SecurityUtil.MD5Encrypt(password);
		example.createCriteria().andNameEqualTo(username).andPasswordEqualTo(password);
		List<User> list = mapper.selectByExample(example);
		if (list != null && !list.isEmpty()) {
			User user = list.get(0);
			return user;
		}
		return null;
	}

	@Override
	public User getUserByMobile(String mobile, String password) {
		UserExample example = new UserExample();
		password = SecurityUtil.MD5Encrypt(password);
		example.createCriteria().andTelEqualTo(mobile).andPasswordEqualTo(password);
		List<User> list = mapper.selectByExample(example);
		if (list != null && !list.isEmpty()) {
			User user = list.get(0);
			return user;
		}
		return null;
	}

	@Override
	public List<Authority> getUserAuthoritys(Integer userId) {
		return zAuthMapper.selectAuthorityByUserId(userId);
	}

	@Override
	public int[] getUserRoles(int id) {
		List<UserRelRole> keys = getRoleIdsByUserId(id);

		// 取出角色id
		int ids[] = new int[keys.size()];
		for (int i = 0; i < keys.size(); i++) {
			ids[i] = keys.get(i).getRoleId();
		}
		return ids;
	}

	@Override
	public List<Integer> getUserRolesList(int id) {
		List<UserRelRole> keys = getRoleIdsByUserId(id);

		// 取出角色id
		List<Integer> ids = new ArrayList<Integer>();
		for (int i = 0; i < keys.size(); i++) {
			ids.add(keys.get(i).getRoleId());
		}
		return ids;
	}

	@Override
	public List<Role> getRolesByUserId(int id) {
		// 取得用户角色
		List<UserRelRole> urrs = getRoleIdsByUserId(id);

		// 取出id列表
		List<Integer> ids = extractIds(urrs);

		// 查询出角色
		RoleExample re = new RoleExample();
		re.createCriteria().andIdIn(ids);
		return roleMapper.selectByExample(re);
	}

	@Override
	public List<Role> getRolesByCreateUserId(int id) {
		RoleExample roleExample = new RoleExample();
		roleExample.createCriteria().andCreateUserIdEqualTo(id);
		return roleMapper.selectByExample(roleExample);
	}

	/**
	 * 根据角色列表取出id列表
	 * 
	 * @param urrs
	 * @return
	 */
	private List<Integer> extractIds(List<UserRelRole> urrs) {
		List<Integer> ids = new ArrayList<Integer>();
		for (UserRelRole urr : urrs) {
			ids.add(urr.getRoleId());
		}
		return ids;
	}

	/**
	 * 根据用户id取得角色列表
	 * 
	 * @param id
	 * @return
	 */
	private List<UserRelRole> getRoleIdsByUserId(int id) {
		UserRelRoleExample urre = new UserRelRoleExample();
		urre.createCriteria().andUserIdEqualTo(id);
		List<UserRelRole> urrs = userRelRoleMapper.selectByExample(urre);
		return urrs;
	}

	@Override
	public List<Role> getNoRolesByUserId(int id) {
		// 取得用户角色
		List<UserRelRole> urrs = getRoleIdsByUserId(id);

		// 取出id列表
		List<Integer> ids = extractIds(urrs);

		// 查询出不是用户的角色
		RoleExample re = new RoleExample();
		re.createCriteria().andIdNotIn(ids);
		return roleMapper.selectByExample(re);
	}

	@Override
	public boolean isPasswordCorrect(int id, String password) {
		password = SecurityUtil.MD5Encrypt(password);
		UserExample example = new UserExample();
		example.createCriteria().andIdEqualTo(id).andPasswordEqualTo(password);
		return mapper.countByExample(example) > 0;
	}

	@Override
	public boolean updatePassword(int id, String password) {
		password = SecurityUtil.MD5Encrypt(password);
		User user = new User();
		user.setId(id);
		user.setPassword(password);
		return mapper.updateByPrimaryKeySelective(user) > 0;
	}

	@Override
	public int getParkIdByUser(User user) {
		UserRelParkExample urpe = new UserRelParkExample();
		urpe.createCriteria().andUserIdEqualTo(user.getId());
		List<UserRelPark> urpk = userRelParkMapper.selectByExample(urpe);
		return (urpk == null || urpk.isEmpty()) ? 0 : urpk.get(0).getParkId();
	}

	@Override
	public List<Integer> getParkIdsByUser(User user) {
		UserRelParkExample urpe = new UserRelParkExample();
		urpe.createCriteria().andUserIdEqualTo(user.getId());

		List<UserRelPark> urpks = userRelParkMapper.selectByExample(urpe);
		List<Integer> parkIds = new ArrayList<Integer>(urpks.size());

		for (UserRelPark urpk : urpks) {
			parkIds.add(urpk.getParkId());
		}
		return parkIds;
	}
	@Override
	public List<Integer> getUserIdsByParkId(int id) {
		UserRelParkExample urpe = new UserRelParkExample();
		urpe.createCriteria().andParkIdEqualTo(id);

		List<UserRelPark> urpks = userRelParkMapper.selectByExample(urpe);
		List<Integer> userIds = new ArrayList<Integer>(urpks.size());

		for (UserRelPark urpk : urpks) {
			userIds.add(urpk.getUserId());
		}
		return userIds;
	}
	@Override
	public List<Integer> getBusinessIdsByUser(User user) {
		UserRelBusinessExample urbe = new UserRelBusinessExample();
		urbe.createCriteria().andUserIdEqualTo(user.getId());

		List<UserRelBusiness> urpks = userRelBusinessMapper.selectByExample(urbe);
		List<Integer> businessIds = new ArrayList<Integer>(urpks.size());

		for (UserRelBusiness urpk : urpks) {
			businessIds.add(urpk.getBusinessId());
		}
		return businessIds;
	}

	@Override
	public void linkParks(List<Park> _parks, int id) {
		List<UserRelPark> urpks = new ArrayList<UserRelPark>(_parks.size());

		for (Park park : _parks) {
			UserRelPark urpk = new UserRelPark();
			urpk.setParkId(park.getId());
			urpk.setUserId(id);
			urpks.add(urpk);
		}

		userRelParkMapper.batchInsert(urpks);
	}

	@Override
	public void removeParks(List<Park> _parks, int id) {
		List<Integer> parkIds = new ArrayList<Integer>();
		for (Park park : _parks) {
			parkIds.add(park.getId());
		}
		UserRelParkExample userRelParkExample = new UserRelParkExample();
		userRelParkExample.createCriteria().andParkIdIn(parkIds).andUserIdEqualTo(id);
		userRelParkMapper.deleteByExample(userRelParkExample);
	}

	@Override
	public boolean isMobileExit(String mobile) {
		UserExample userExample = new UserExample();
		userExample.createCriteria().andTelEqualTo(mobile);
		return mapper.countByExample(userExample) > 0;
	}

	@Override
	public User getUserByMobile(String mobile) {
		UserExample userExample = new UserExample();
		userExample.createCriteria().andTelEqualTo(mobile);
		List<User> users = mapper.selectByExample(userExample);
		if (users == null || users.isEmpty()) {
			return null;
		}
		return users.get(0);
	}
	@Override
	public List<User> getAll() {
		return mapper.selectByExample(null);
	}


	@Override
	public User findcompanyid(String username) {
		UserExample userExample = new UserExample();
		userExample.createCriteria().andNameEqualTo(username);
		List<User> users = mapper.selectByExample(userExample);
		if (users == null || users.isEmpty()) {
			return null;
		}
		return users.get(0);
	}
	/**
	 * 初始化密码  为123456
	 */
	@Override
	public void resetPassword(int id) {
		UserExample e = new UserExample();
		e.createCriteria().andIdEqualTo(id);
		
		User u=new User();
		u.setPassword(SecurityUtil.MD5Encrypt("123456"));
		
		mapper.updateByExampleSelective(u, e);
		
	}
}
