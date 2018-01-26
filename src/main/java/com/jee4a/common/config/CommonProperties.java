package com.jee4a.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>公共配置类</p> 
 * @author tpeng 2018年1月24日
 * @email 398222836@qq.com
 */
@Component
@ConfigurationProperties
public class CommonProperties {
	
	@Value("${spring.application.name}")
	private String appName ;
	
	/**
	 * @return the appName
	 */
	public String getAppName() {
		return appName;
	}
}
