package com.jee4a.user.controller;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jee4a.common.model.User;
import com.jee4a.user.service.UserService;

/**
 * <p>此用于ribbon例子</p> 
 * @author tpeng 2018年1月26日
 * @email 398222836@qq.com
 */
@RestController
public class UserController {
	
	private final Logger  logger = LoggerFactory.getLogger(getClass()) ;
	@Resource
	private DiscoveryClient discoveryClient ;
	@Resource
	private Registration registration; // 服务注册
	@Resource
	private UserService userService ;
	
	@RequestMapping(value="/user/{id}")
	public User queryByUserId(@PathVariable Integer id) {
		ServiceInstance instance  = serviceInstance() ;
		logger.info("/user/{},host:{},service_id:{}",id,instance.getHost(),instance.getServiceId());
		return userService.queryById(id) ;
	}
	
	/**
	 * 
	 * 描述     :熔断器示例 ，模拟服务阻塞 
	 * @author tpeng 2018年1月29日
	 * @email 398222836@qq.com
	 */
	@RequestMapping(value="/user-1/{id}")
	public User queryByUserId1(@PathVariable Integer id) throws InterruptedException {
		ServiceInstance instance  = serviceInstance() ;
		int sleepTime = new Random().nextInt(3000) ;
		Thread.sleep(sleepTime);
		logger.info("sleep time : {}" ,sleepTime );
		logger.info("/user-1/{},host:{},service_id:{}",id,instance.getHost(),instance.getServiceId());
		return userService.queryById(id) ;
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
