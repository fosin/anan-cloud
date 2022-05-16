package top.fosin.anan.platform.modules.version.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import top.fosin.anan.platform.modules.pub.entity.PermissionEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
@ApiModel(value = "版本权限表实体类", description = "版本权限的实体类")
public class VersionPermission extends PermissionEntity<Long> {
    private static final long serialVersionUID = 117455991817648863L;

    @Basic
    @ApiModelProperty(value = "版本序号", required = true)
    @Column(name = "version_id", nullable = false)
    private Long versionId;

}
