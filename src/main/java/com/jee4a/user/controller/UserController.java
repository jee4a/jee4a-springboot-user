package com.jee4a.user.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jee4a.common.model.User;
import com.jee4a.user.service.UserService;

/**
 * <p></p> 
 * @author tpeng 2018年1月26日
 * @email 398222836@qq.com
 */
@RestController
public class UserController {
	
	private final Logger  logger = LoggerFactory.getLogger(getClass()) ;
	@Resource
	private DiscoveryClient discoveryClient ;
	@Resource
	private UserService userService ;
	
	@RequestMapping(value="/user/{id}")
	public User queryByUserId(@PathVariable Integer id) {
		@SuppressWarnings("deprecation")
		ServiceInstance instance  = discoveryClient.getLocalServiceInstance() ;
		logger.info("/user/{},host:{},service_id:{}",id,instance.getHost(),instance.getServiceId());
		return userService.queryById(id) ;
	}
}
