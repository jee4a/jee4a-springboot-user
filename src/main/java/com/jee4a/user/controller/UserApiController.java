package com.jee4a.user.controller;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.RestController;

import com.jee4a.common.model.User;
import com.jee4a.user.api.interfaces.UserApiServcie;
import com.jee4a.user.api.model.UserModel;
import com.jee4a.user.service.UserService;

/**
 * <p>此用于声明式接口例子</p> 
 * @author tpeng 2018年1月29日
 * @email 398222836@qq.com
 */

@RestController
public class UserApiController  implements UserApiServcie {

	private final Logger  logger = LoggerFactory.getLogger(getClass()) ;
	 
	@Resource
	private DiscoveryClient discoveryClient ;
	
	@Resource
	private Registration registration; // 服务注册
	
	@Resource
	private UserService userService ;
	
	
	/** 
	 * @author tpeng 2018年1月29日
	 * @email 398222836@qq.com
	 */
	@Override
	public String queryUserById(Integer id) {
		ServiceInstance instance  = serviceInstance() ;
		logger.info("/api/user/{},host:{},service_id:{}",id,instance.getHost(),instance.getServiceId());
		return userService.queryById(id).toString() ;
	}

	/** 
	 * @author tpeng 2018年1月29日
	 * @email 398222836@qq.com
	 */
	@Override
	public UserModel queryUserById1(Integer id)  {
		ServiceInstance instance  =  serviceInstance() ; 
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

	
	public ServiceInstance serviceInstance() {
        List<ServiceInstance> list = discoveryClient.getInstances(registration.getServiceId());
        if (list != null && list.size() > 0) {
            for(ServiceInstance itm : list){
                if(itm.getPort() == 2001)
                    return itm;
            }   
        }
        return null;
    }
	
}
