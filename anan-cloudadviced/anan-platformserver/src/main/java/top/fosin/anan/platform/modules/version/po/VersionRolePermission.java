package top.fosin.anan.platform.modules.version.po;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import top.fosin.anan.platform.modules.permission.po.PermissionId;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * 系统版本角色权限表(AnanVersionRolePermission)实体类
 *
 * @author fosin
 * @date 2019-01-28 12:50:43
 * @since 1.0.0
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@Table(name = "anan_version_role_permission")
@Schema(description = "版本角色权限的实体类")
public class VersionRolePermission extends PermissionId<Long> {
    private static final long serialVersionUID = -46739456017788098L;

    @Basic
    @Schema(description = "角色序号")
    @Column(name = "role_id", nullable = false)
    private Long roleId;

}
