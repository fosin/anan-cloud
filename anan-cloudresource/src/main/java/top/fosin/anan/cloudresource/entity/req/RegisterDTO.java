package top.fosin.anan.cloudresource.entity.req;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author fosin
 * @date 2018.12.5
 */
@Data
@Schema(description = "注册新机构")
public class RegisterDTO implements Serializable {

    private static final long serialVersionUID = -9074869342835236663L;
    @Schema(description = "机构信息")
    private OrgRegisterReqDTO organization;

    @Schema(description = "用户信息")
    private UserRegisterDTO user;

    @Schema(description = "版本序号")
    private Long versionId;

    @Schema(description = "版本价格")
    private Double versionPrice;

}
