package com.jee4a.user.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jee4a.common.exceptions.ApiException;
import com.jee4a.common.model.User;
import com.jee4a.user.manager.UserManager;

/**
 * <p></p> 
 * @author tpeng 2018年1月26日
 * @email 398222836@qq.com
 */
@Service
public class UserService {

	@Resource
	private UserManager userManger ;
	
	public User queryById(Integer id) {
		if(id==null || id<0) {
			throw new ApiException(-10000,"id error") ;
		}
		
		return userManger.selectByPrimaryKey(id) ;
	}
}
