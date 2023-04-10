package top.fosin.anan.platform.modules.user.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import top.fosin.anan.jpa.po.IdCreateUpdateOrganizDeletePO;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 系统用户表(AnanUser)实体类
 *
 * @author fosin
 * @date 2019-01-27 15:39:54
 * @since 1.0.0
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@SQLDelete(sql = "update anan_user set deleted = 1 where id = ?")
@Table(name = "anan_user")
@Where(clause = "deleted=0")
@ApiModel(value = "用户表实体类", description = "用户的实体类")
public class User extends IdCreateUpdateOrganizDeletePO<Long> {
  private static final long serialVersionUID = 897030139778409164L;

  @Basic
  @ApiModelProperty(value = "用户工号", required = true)
  @Column(name = "usercode", nullable = false, length = 45)
  private String usercode;

  @Basic
  @ApiModelProperty(value = "用户姓名", required = true)
  @Column(name = "username", nullable = false, length = 45)
  private String username;

  @Basic
  @Column(name = "family_name")
  @ApiModelProperty(value = "姓氏")
  private String familyName;

  @Basic
  @Column(name = "middle_name")
  @ApiModelProperty(value = "中间名")
  private String middleName;

  @Basic
  @Column(name = "given_name")
  @ApiModelProperty(value = "名字")
  private String givenName;

  @Basic
  @Column(name = "nickname")
  @ApiModelProperty(value = "昵称")
  private String nickname;

  @Basic
  @Column(name = "preferred_username")
  @ApiModelProperty(value = "希望被称呼的名字")
  private String preferredUsername;

  @Basic
  @Column(name = "real_name_verified")
  @ApiModelProperty(value = "实名认证标志")
  private Integer realNameVerified;

  @Basic
  @ApiModelProperty(value = "传入原始密码，后台会对原始密码进行加密后再存储", required = true)
  @Column(name = "password", nullable = false, length = 96)
  private String password;

  @Basic
  @ApiModelProperty(value = "生日", required = true)
  @Column(name = "birthday", nullable = false)
  private Date birthday;

  @Basic
  @ApiModelProperty(value = "使用状态：具体取值于字典表anan_dictionary.id=15", required = true)
  @Column(name = "sex", nullable = false)
  private Integer sex;

  @Basic
  @ApiModelProperty(value = "电子邮箱")
  @Column(name = "email", length = 45)
  private String email;

  @Basic
  @Column(name = "email_verified")
  @ApiModelProperty(value = "邮箱认证标志")
  private Integer emailVerified;

  @Basic
  @ApiModelProperty(value = "手机号码")
  @Column(name = "phone", length = 45)
  private String phone;

  @Basic
  @Column(name = "phone_verified")
  @ApiModelProperty(value = "手机验证标志")
  private Integer phoneVerified;

  @Basic
  @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.id=11", required = true)
  @Column(name = "status", nullable = false)
  private Integer status;

  @Basic
  @ApiModelProperty(value = "头像")
  @Column(name = "avatar", length = 150)
  private String avatar;

  @Basic
  @Column(name = "website")
  @ApiModelProperty(value = "网站地址")
  private String website;

  @Basic
  @ApiModelProperty(value = "过期时间，账户过期后用户被锁定切不能登录系统", required = true)
  @Column(name = "expire_time", nullable = false)
  private Date expireTime;

}
