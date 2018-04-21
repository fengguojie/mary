package com.chinadovey.parking.webapps.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinadovey.parking.Constants;
import com.chinadovey.parking.core.secu.MainMenu;
import com.chinadovey.parking.core.secu.SecuObject;
import com.chinadovey.parking.webapps.biz.system.UiMenuBiz;
import com.chinadovey.parking.webapps.mappers.gen.UiMenuMapper;
import com.chinadovey.parking.webapps.pojo.UiMenu;
import com.chinadovey.parking.webapps.pojo.UiMenuExample;

@Service
public class UiMenuBizImpl implements UiMenuBiz {

	@Autowired
	private UiMenuMapper mapper;
	
	@Override
	public boolean modify() {
		return false;
	}

	@Override
	public boolean delete() {
		return false;
	}

	@Override
	public boolean create() {
		return false;
	}

	@Override
	public MainMenu getUserMenu(SecuObject secuObj) {
		
		UiMenuExample example = new UiMenuExample();
		example.createCriteria().andMenuIsEnableEqualTo(true).andMenuTreePathLike("/%");
		example.setOrderByClause("menu_tree_path asc, menu_order asc");
		
		List<UiMenu> list = mapper.selectByExample(example);

		List<UiMenu> r = new ArrayList<UiMenu>();
		Map<String,UiMenu> tempMap = new HashMap<String,UiMenu>();
		
		//create menus
		for(UiMenu menu : list){
			if(!tempMap.containsKey(menu.getMenuParentGuid())){
				r.add(menu);
			}else{
				UiMenu parent = tempMap.get(menu.getMenuParentGuid());
				if(parent.getChildrens()==null)
					parent.setChildrens(new ArrayList<UiMenu>());
				parent.getChildrens().add(menu);
			}
			tempMap.put(menu.getMenuGuid(), menu);
		}
		//filter by authority
		for(UiMenu menu : list){
			if(secuObj==null||!secuObj.validate(menu.getMenuResource(), menu.getMenuOperation())){
				menu.setMenuAction(Constants.EMPTY);
			}
		}
		
		//clear menus
		UiMenu menu = null;
		for(int i=list.size()-1;i>=0;i--){
			menu = list.get(i);
			if((null==menu.getMenuAction()||Constants.EMPTY.equals(menu.getMenuAction().trim()))
					&&(null==menu.getChildrens()||0==menu.getChildrens().size())){
				if(Constants.ZERO.equals(menu.getMenuParentGuid())){
					r.remove(menu);
				}else if(tempMap.containsKey(menu.getMenuParentGuid())){
					List<UiMenu> childrens = tempMap.get(menu.getMenuParentGuid()).getChildrens();
					if(null!=childrens)
						childrens.remove(menu);
				}
			}
		}
		
		return new MainMenu(r);
	}
}
