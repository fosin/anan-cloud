package top.fosin.anan.platform.modules.user.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
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
public class User extends IdCreateUpdateOrganizDeletePO<Long> {
    private static final long serialVersionUID = 897030139778409164L;

    /**
     * 用户工号
     */
    @Basic
    @Column(name = "usercode", nullable = false)
    private String usercode;

    /**
     * 用户姓名
     */
    @Basic
    @Column(name = "username", nullable = false)
    private String username;

    /**
     * 姓氏
     */
    @Basic
    @Column(name = "family_name")
    private String familyName;

    /**
     * 中间名
     */
    @Basic
    @Column(name = "middle_name")
    private String middleName;

    /**
     * 名字
     */
    @Basic
    @Column(name = "given_name")
    private String givenName;

    /**
     * 昵称
     */
    @Basic
    @Column(name = "nickname")
    private String nickname;

    /**
     * 希望被称呼的名字
     */
    @Basic
    @Column(name = "preferred_username")
    private String preferredUsername;

    /**
     * 网站地址
     */
    @Basic
    @Column(name = "website")
    private String website;

    /**
     * 实名认证标志
     */
    @Basic
    @Column(name = "real_name_verified", nullable = false)
    private Integer realNameVerified;

    /**
     * 传入原始密码，后台会对原始密码进行加密后再存储
     */
    @Basic
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * 生日
     */
    @Basic
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Column(name = "birthday", nullable = false)
    private Date birthday;

    /**
     * 使用状态：具体取值于字典表anan_dictionary.code=15
     */
    @Basic
    @Column(name = "sex", nullable = false)
    private Integer sex;

    /**
     * 电子邮箱
     */
    @Basic
    @Column(name = "email")
    private String email;

    /**
     * 邮箱认证标志
     */
    @Basic
    @Column(name = "email_verified", nullable = false)
    private Integer emailVerified;

    /**
     * 手机号码
     */
    @Basic
    @Column(name = "phone")
    private String phone;

    /**
     * 手机验证标志
     */
    @Basic
    @Column(name = "phone_verified", nullable = false)
    private Integer phoneVerified;

    /**
     * 使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11
     */
    @Basic
    @Column(name = "status", nullable = false)
    private Integer status;

    /**
     * 头像
     */
    @Basic
    @Column(name = "avatar")
    private String avatar;

    /**
     * 过期时间，账户过期后用户被锁定切不能登录系统
     */
    @Basic
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Column(name = "expire_time", nullable = false)
    private Date expireTime;

}
