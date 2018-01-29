package com.jee4a.user.service;

import java.util.Random;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
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

	private final static Logger  logger = LoggerFactory.getLogger(UserService.class) ;
	
	@Resource
	private UserManager userManger ;
	
	/**
	 * 
	 * 描述     : 
	 * @author tpeng 2018年1月29日
	 * @email 398222836@qq.com
	 */
	public User queryById(Integer id) {
		if(id==null || id<0) {
			throw new ApiException(-10000,"id error") ;
		}
		
		return userManger.selectByPrimaryKey(id) ;
	}
	
	 
	
}
