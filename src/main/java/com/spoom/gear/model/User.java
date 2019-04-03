package com.spoom.gear.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * package com.spoom.gear.model
 *
 * @author spoomlan
 * @date 2019-04-03
 */
@TableName("sys_user")
@Data
public class User implements Serializable {

  private static final long serialVersionUID = 6862232995865649718L;
  @TableId
  private Long userId;
  @Length(min = 6, max = 20, message = "请使用6至20位长度的用户名")
  private String username;
  @Length(min = 6, max = 50, message = "请使用6至50位长度的密码")
  private String avatar;
  private String password;
  private String roles;
  @Email(message = "邮箱格式错误")
  private String email;
  private String mobile;
  private Integer status;
  private Long createUserId;
  private Date createTime;
}
