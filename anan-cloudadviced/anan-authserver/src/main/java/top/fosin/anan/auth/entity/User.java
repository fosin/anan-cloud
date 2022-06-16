package top.fosin.anan.auth.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import top.fosin.anan.cloudresource.dto.UserAuthDto;
import top.fosin.anan.cloudresource.dto.req.RoleReqDto;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.jpa.entity.IdCreateUpdateOrganizEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
@Table(name = "anan_user")
@ApiModel(value = "用户表实体类", description = "用户的实体类")
public class User extends IdCreateUpdateOrganizEntity<Long> {
    private static final long serialVersionUID = 897030139778409164L;

    public UserAuthDto toAuthDto() {
        UserAuthDto userDto = new UserAuthDto();
        BeanUtil.copyProperties(this, userDto);
        userDto.setId(this.getId());
        List<UserRole> userRoles = this.getUserRoles();
        List<RoleReqDto> userRoles2 = new ArrayList<>();
        if (userRoles != null) {
            userRoles.toString();
            if (userRoles.size() > 0) {
                userRoles.forEach(userRole -> {
                    Role role = userRole.getRole();
                    RoleReqDto role2 = BeanUtil.copyProperties(role, RoleReqDto.class);
                    role2.setId(role.getId());
                    userRoles2.add(role2);
                });
            }
        }
        userDto.setUserRoles(userRoles2);
        return userDto;
    }

    /**
     * orphanRemoval=true配置表明删除无关联的数据。级联更新子结果集时此配置最关键
     */
    @OneToMany(orphanRemoval = true, cascade = {CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn(name = "user_id")
    @ApiModelProperty(value = "用户拥有的角色")
    private List<UserRole> userRoles;

    @Basic
    @ApiModelProperty(value = "用户工号", required = true)
    @Column(name = "usercode", nullable = false, length = 45)
    private String usercode;

    @Basic
    @ApiModelProperty(value = "用户姓名", required = true)
    @Column(name = "username", nullable = false, length = 45)
    private String username;

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
    @ApiModelProperty(value = "手机号码")
    @Column(name = "phone", length = 45)
    private String phone;

    @Basic
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.id=11", required = true)
    @Column(name = "status", nullable = false)
    private Integer status;

    @Basic
    @ApiModelProperty(value = "头像")
    @Column(name = "avatar", length = 150)
    private String avatar;

    @Basic
    @ApiModelProperty(value = "过期时间，账户过期后用户被锁定切不能登录系统", required = true)
    @Column(name = "expire_time", nullable = false)
    private Date expireTime;

}
