package com.jee4a.user.controller;

import java.util.Random;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

import com.jee4a.common.model.User;
import com.jee4a.user.api.interfaces.UserApiServcie;
import com.jee4a.user.api.model.UserModel;
import com.jee4a.user.service.UserService;

/**
 * <p></p> 
 * @author tpeng 2018年1月29日
 * @email 398222836@qq.com
 */

@RestController
public class UserApiController  implements UserApiServcie {

	private final Logger  logger = LoggerFactory.getLogger(getClass()) ;
	 
	@Resource
	private DiscoveryClient discoveryClient ;
	
	@Resource
	private UserService userService ;
	
	
	/** 
	 * @author tpeng 2018年1月29日
	 * @email 398222836@qq.com
	 */
	@Override
	public String queryUserById(Integer id) {
		@SuppressWarnings("deprecation")
		ServiceInstance instance  = discoveryClient.getLocalServiceInstance() ;
		logger.info("/api/user/{},host:{},service_id:{}",id,instance.getHost(),instance.getServiceId());
		return userService.queryById(id).toString() ;
	}

	/** 
	 * @author tpeng 2018年1月29日
	 * @email 398222836@qq.com
	 */
	@Override
	public UserModel queryUserById1(Integer id)  {
		@SuppressWarnings("deprecation")
		ServiceInstance instance  = discoveryClient.getLocalServiceInstance() ;
		int sleepTime = new Random().nextInt(3000) ;
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			logger.error("exceptin :{} ",e);
			return null ;
		}
		logger.info("sleep time : {}" ,sleepTime );
		logger.info("/api/user-1/{},host:{},service_id:{}",id,instance.getHost(),instance.getServiceId());
		
		UserModel model = new UserModel();
		User user = userService.queryById(id) ;
		BeanUtils.copyProperties(user, model);
		return model;
	}

	
}
