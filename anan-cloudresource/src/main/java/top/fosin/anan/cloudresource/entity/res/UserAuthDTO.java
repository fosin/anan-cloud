package top.fosin.anan.cloudresource.entity.res;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.Id;

import java.util.Date;

/**
 * 系统用户表(AnanUser)更新DTO
 *
 * @author fosin
 * @date 2019-01-27 12:23:44
 * @since 1.0.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "用户的更新DTO")
public class UserAuthDTO extends Id<Long> {
    private static final long serialVersionUID = -38545495043403316L;

    @Schema(description = "顶级机构序号", example = "1")
    private Long topId;

    @Schema(description = "机构ID", example = "Long")
    private Long organizId;

    @Schema(description = "用户工号", example = "String")
    private String usercode;

    @Schema(description = "用户姓名", example = "String")
    private String username;

    @Schema(description = "姓氏", example = "String")
    private String familyName;

    @Schema(description = "中间名", example = "String")
    private String middleName;

    @Schema(description = "名字", example = "String")
    private String givenName;

    @Schema(description = "昵称", example = "String")
    private String nickname;

    @Schema(description = "希望被称呼的名字", example = "String")
    private String preferredUsername;

    @Schema(description = "网站地址", example = "String")
    private String website;

    @Schema(description = "实名认证标志", example = "Integer")
    private Byte realNameVerified;

    @Schema(description = "传入原始密码，后台会对原始密码进行加密后再存储", example = "String")
    private String password;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "生日", example = "Date")
    private Date birthday;

    @Schema(description = "使用状态：具体取值于字典表anan_dictionary.code=15", example = "Integer")
    private Byte sex;

    @Schema(description = "电子邮箱", example = "String")
    private String email;

    @Schema(description = "邮箱认证标志", example = "Integer")
    private Byte emailVerified;

    @Schema(description = "手机号码", example = "String")
    private String phone;

    @Schema(description = "手机验证标志", example = "Integer")
    private Byte phoneVerified;

    @Schema(description = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11", example = "Integer")
    private Byte status;

    @Schema(description = "头像", example = "String")
    private String avatar;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "过期时间，账户过期后用户被锁定切不能登录系统", example = "Date")
    private Date expireTime;
}
