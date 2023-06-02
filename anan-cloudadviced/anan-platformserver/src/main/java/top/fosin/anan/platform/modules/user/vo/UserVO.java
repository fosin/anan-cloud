package top.fosin.anan.platform.modules.user.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.converter.masking.EmailMasking;
import top.fosin.anan.data.converter.masking.PhoneMasking;
import top.fosin.anan.data.converter.masking.UsernameMasking;
import top.fosin.anan.data.entity.Id;

import java.util.Date;

/**
 * 系统用户表(anan_user)单体VO
 *
 * @author fosin
 * @date 2023-05-02
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统用户表单体VO", description = "系统用户表(anan_user)单体VO")
public class UserVO extends Id<Long> {
    private static final long serialVersionUID = -52894088471472561L;

    @ApiModelProperty(value = "机构ID")
    private Long organizId;

    @ApiModelProperty(value = "用户工号")
    private String usercode;

    @ApiModelProperty(value = "用户姓名")
    @UsernameMasking
    private String username;

    @ApiModelProperty(value = "姓氏")
    private String familyName;

    @ApiModelProperty(value = "中间名")
    private String middleName;

    @ApiModelProperty(value = "名字")
    private String givenName;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "希望被称呼的名字")
    private String preferredUsername;

    @ApiModelProperty(value = "网站地址")
    private String website;

    @ApiModelProperty(value = "实名认证标志")
    private Byte realNameVerified;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "使用状态：具体取值于字典表anan_dictionary.code=15")
    private Byte sex;

    @ApiModelProperty(value = "电子邮箱")
    @EmailMasking
    private String email;

    @ApiModelProperty(value = "邮箱认证标志")
    private Byte emailVerified;

    @ApiModelProperty(value = "手机号码")
    @PhoneMasking
    private String phone;

    @ApiModelProperty(value = "手机验证标志")
    private Byte phoneVerified;

    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11")
    private Byte status;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "过期时间，账户过期后用户被锁定切不能登录系统")
    private Date expireTime;

}
