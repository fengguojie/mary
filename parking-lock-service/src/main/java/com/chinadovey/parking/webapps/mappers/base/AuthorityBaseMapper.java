package com.chinadovey.parking.webapps.mappers.base;

import java.util.List;

import com.chinadovey.parking.webapps.pojo.Authority;

public interface AuthorityBaseMapper {
	List<Authority>	selectAuthorityByUserId(Integer userId);
}
