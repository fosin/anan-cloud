package top.fosin.anan.cloudresource.dto;

import top.fosin.anan.cloudresource.dto.request.AnanUserRegisterDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 *
 * @author fosin
 * @date 2018.12.5
 */
@Data
@ApiModel(value = "注册新机构")
public class RegisterDto implements Serializable {

    @ApiModelProperty(value = "机构信息")
    private OrganizationRegisterDto organization;

    @ApiModelProperty(value = "用户信息")
    private AnanUserRegisterDto user;

    @ApiModelProperty(value = "版本ID")
    private Long versionId;

    @ApiModelProperty(value = "版本价格")
    private Double versionPrice;

}
