package top.fosin.anan.platform.modules.user.vo;



import io.swagger.v3.oas.annotations.media.Schema;

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
@Schema(description = "系统用户表(anan_user)单体VO")
public class UserVO extends Id<Long> {
    private static final long serialVersionUID = -52894088471472561L;

    @Schema(description = "机构ID")
    private Long organizId;

    @Schema(description = "用户工号")
    private String usercode;

    @Schema(description = "用户姓名")
    @UsernameMasking
    private String username;

    @Schema(description = "姓氏")
    private String familyName;

    @Schema(description = "中间名")
    private String middleName;

    @Schema(description = "名字")
    private String givenName;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "希望被称呼的名字")
    private String preferredUsername;

    @Schema(description = "网站地址")
    private String website;

    @Schema(description = "实名认证标志")
    private Byte realNameVerified;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "生日")
    private Date birthday;

    @Schema(description = "使用状态：具体取值于字典表anan_dictionary.code=15")
    private Byte sex;

    @Schema(description = "电子邮箱")
    @EmailMasking
    private String email;

    @Schema(description = "邮箱认证标志")
    private Byte emailVerified;

    @Schema(description = "手机号码")
    @PhoneMasking
    private String phone;

    @Schema(description = "手机验证标志")
    private Byte phoneVerified;

    @Schema(description = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11")
    private Byte status;

    @Schema(description = "头像")
    private String avatar;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "过期时间，账户过期后用户被锁定切不能登录系统")
    private Date expireTime;

}
