package com.github.fosin.anan.platform.entity;

import com.github.fosin.anan.jpa.entity.AbstractCreateJpaEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
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
@ApiModel(value = "系统版本角色权限表实体类", description = "表(anan_version_role_permission)的对应的实体类")
public class AnanVersionRolePermissionEntity extends AbstractCreateJpaEntity<Long, Long> implements Serializable {
    private static final long serialVersionUID = -46739456017788098L;

    @Basic
    @ApiModelProperty(value = "角色ID", required = true)
    @Column(name = "role_id", nullable = false)
    private Long roleId;

    @Basic
    @ApiModelProperty(value = "权限ID", required = true)
    @Column(name = "permission_id", nullable = false)
    private Long permissionId;

}
