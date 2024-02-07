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
 * 系统版本权限表(AnanVersionPermission)实体类
 *
 * @author fosin
 * @date 2019-01-28 12:50:38
 * @since 1.0.0
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@Table(name = "anan_version_permission")
@Schema(description = "版本权限的实体类")
public class VersionPermission extends PermissionId<Long> {
    private static final long serialVersionUID = 117455991817648863L;

    @Basic
    @Schema(description = "版本序号")
    @Column(name = "version_id", nullable = false)
    private Long versionId;

}
