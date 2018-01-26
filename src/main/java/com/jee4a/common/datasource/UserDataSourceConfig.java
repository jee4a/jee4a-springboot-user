package com.jee4a.common.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <p>商品库数据源配置</p> 
 * @author tpeng 2018年1月25日
 * @email 398222836@qq.com
 */
@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@MapperScan(basePackages="com.jee4a.common.mapper",sqlSessionFactoryRef="userSessionFactory")
public class UserDataSourceConfig extends AbstractDataSourceConfig{

	@Value("${jdbc.url_user}")
	private String url ;
	
	@Value("${jdbc.username_user}")
	private String username ;
	
	@Value("${jdbc.pwd_user}")
	private String password ;
	
	private static final String mapperLocations = "sqlmap/*.xml" ;
	
	@Bean(name="userDS",initMethod="init" ,destroyMethod="close")
	@Primary
	public DataSource  userDataSource() {
		return super.createDataSource(url, username, password) ;
	}
	
	@Bean(name="userSessionFactory")
	@Primary
	public SqlSessionFactory userSqlSessionFactory() throws Exception {
        return super.createSqlSessionFactory(userDataSource(),ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperLocations) ;
    }
	
	@Bean(name="userTransactionManager")
	public DataSourceTransactionManager userTransactionManager() {
		return super.createDataSourceTransactionManager(userDataSource()) ;
	}
	
}
