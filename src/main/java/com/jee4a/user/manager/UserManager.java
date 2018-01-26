package com.jee4a.user.manager;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jee4a.common.mapper.UserMapper;
import com.jee4a.common.model.User;

@Component
@Transactional(readOnly=false,rollbackFor=Exception.class)
public class UserManager {
	@Resource
	private UserMapper userMapper ;
     
    /**
     * 保存属性不为空的记录
     */
    public void insertSelective(User record) {
    	userMapper.insertSelective(record) ;
    }

    /**
     * 根据主键查询记录
     */
    @Transactional(readOnly=true)
    public User selectByPrimaryKey(Integer id) {
    	return userMapper.selectByPrimaryKey(id) ;
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public void updateByPrimaryKeySelective(User record) {
    	userMapper.updateByPrimaryKeySelective(record) ;
    }
    
}