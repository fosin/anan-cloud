package top.fosin.anan.cloudresource.entity.res;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.data.prop.TreeProp;

import java.util.List;

/**
 * 包含菜单、按钮两种权限(AnanPermission)请求DTO
 *
 * @author fosin
 * @date 2021-05-10
 * @since 2.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "权限权限树响应DTO")
public class PermissionTreeDTO extends PermissionDTO
        implements TreeProp<PermissionTreeDTO, Long> {
    private static final long serialVersionUID = -61984917164013694L;

    @Schema(description = "子节点，虚拟字段，增删改时不需要关心")
    private List<PermissionTreeDTO> children;

    @Schema(description = "是否叶子节点，虚拟字段，增删改时不需要关心")
    private Boolean leaf;

    @Override
    public Boolean getLeaf() {
        switch (getType()) {
            case 1: //组件
            case 3: //目录
            case 6: //子组件
                return leaf != null && leaf;
            default:
                return true;
        }
    }

}
