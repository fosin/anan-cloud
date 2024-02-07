package top.fosin.anan.cloudresource.entity.res;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.data.prop.TreeProp;

import java.util.List;

/**
 * 系统机构表(AnanOrganization)创建DTO
 *
 * @author fosin
 * @date 2019-02-19 18:17:04
 * @since 2.6.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "机构表树形响应DTO")
public class OrganizTreeDTO extends OrganizRespDTO
        implements TreeProp<OrganizTreeDTO, Long> {
    private static final long serialVersionUID = 389815217019211695L;

    @Schema(description = "子节点，虚拟字段，增删改时不需要关心")
    private List<OrganizTreeDTO> children;

    @Schema(description = "是否叶子节点，虚拟字段，增删改时不需要关心")
    private Boolean leaf;

}
