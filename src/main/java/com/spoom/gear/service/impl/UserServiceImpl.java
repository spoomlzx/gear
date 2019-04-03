package com.spoom.gear.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spoom.gear.mapper.UserMapper;
import com.spoom.gear.model.User;
import com.spoom.gear.service.UserService;
import org.springframework.stereotype.Service;

/**
 * package com.spoom.gear.service.impl
 *
 * @author spoomlan
 * @date 2019-04-03
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
