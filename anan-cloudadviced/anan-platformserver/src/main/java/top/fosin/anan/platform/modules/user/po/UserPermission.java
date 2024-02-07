package top.fosin.anan.platform.modules.user.po;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import top.fosin.anan.platform.modules.organization.po.OrganizIdPermissionId;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * 用于增减用户的单项权限，通常实在角色的基础上增减单项权限(AnanUserPermission)实体类
 *
 * @author fosin
 * @date 2019-01-27 19:35:05
 * @since 1.0.0
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@Table(name = "anan_user_permission")
@Schema(description = "用户权限的实体类")
public class UserPermission extends OrganizIdPermissionId<Long> {
    private static final long serialVersionUID = 539048606557875412L;

    @Basic
    @Schema(description = "用户序号")
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Basic
    @Schema(description = "补充方式：0=增加权限、1=删除权限")
    @Column(name = "add_mode", nullable = false)
    private Integer addMode;
}
