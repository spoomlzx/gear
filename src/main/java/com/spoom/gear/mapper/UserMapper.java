package com.spoom.gear.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spoom.gear.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * package com.spoom.gear.mapper
 *
 * @author spoomlan
 * @date 2019-04-03
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
