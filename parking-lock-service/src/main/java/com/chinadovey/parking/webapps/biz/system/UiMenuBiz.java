package com.chinadovey.parking.webapps.biz.system;

import com.chinadovey.parking.core.secu.MainMenu;
import com.chinadovey.parking.core.secu.SecuObject;

public interface UiMenuBiz {
	
	public boolean modify();
	public boolean delete();
	public boolean create();
	
	public MainMenu getUserMenu(SecuObject secuObj);
}
