package top.fosin.anan.platform.modules.user.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.req.LogiSortQuery;
import top.fosin.anan.data.module.LogiQueryRule;
import top.fosin.anan.data.module.SortRule;

import java.util.Date;

/**
* 系统用户表(anan_user)通用查询DTO
*
* @author fosin
* @date 2023-05-02
*/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统用户表通用查询DTO", description = "系统用户表(anan_user)通用查询DTO")
public class UserQuery extends LogiSortQuery<LogiQueryRule,SortRule,Long> {
private static final long serialVersionUID = 778841577712580213L;

@ApiModelProperty(value = "机构ID", example = "Long")
private Long organizId;

@ApiModelProperty(value = "用户工号", example = "String")
private String usercode;

@ApiModelProperty(value = "用户姓名", example = "String")
private String username;

@ApiModelProperty(value = "姓氏", example = "String")
private String familyName;

@ApiModelProperty(value = "中间名", example = "String")
private String middleName;

@ApiModelProperty(value = "名字", example = "String")
private String givenName;

@ApiModelProperty(value = "昵称", example = "String")
private String nickname;

@ApiModelProperty(value = "希望被称呼的名字", example = "String")
private String preferredUsername;

@ApiModelProperty(value = "网站地址", example = "String")
private String website;

@ApiModelProperty(value = "实名认证标志", example = "Integer")
private Byte realNameVerified;

@DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
@ApiModelProperty(value = "生日", example = "Date")
private Date birthday;

@ApiModelProperty(value = "使用状态：具体取值于字典表anan_dictionary.code=15", example = "Integer")
private Byte sex;

@ApiModelProperty(value = "电子邮箱", example = "String")
private String email;

@ApiModelProperty(value = "邮箱认证标志", example = "Integer")
private Byte emailVerified;

@ApiModelProperty(value = "手机号码", example = "String")
private String phone;

@ApiModelProperty(value = "手机验证标志", example = "Integer")
private Byte phoneVerified;

@ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11", example = "Integer")
private Byte status;

@ApiModelProperty(value = "头像", example = "String")
private String avatar;

@DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
@ApiModelProperty(value = "过期时间，账户过期后用户被锁定切不能登录系统", example = "Date")
private Date expireTime;

}
