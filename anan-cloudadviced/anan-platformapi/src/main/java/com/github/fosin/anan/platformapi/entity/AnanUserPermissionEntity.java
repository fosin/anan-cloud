package com.github.fosin.anan.platformapi.entity;

import com.github.fosin.anan.jpa.entity.AbstractOrganizIdCreateJpaEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
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
@ApiModel(value = "用于增减用户的单项权限，通常实在角色的基础上增减单项权限实体类", description = "表(anan_user_permission)的对应的实体类")
public class AnanUserPermissionEntity extends AbstractOrganizIdCreateJpaEntity<Long, Long> implements Serializable {
    private static final long serialVersionUID = 539048606557875412L;

    @Basic
    @ApiModelProperty(value = "用户ID", required = true)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Basic
    @ApiModelProperty(value = "权限ID", required = true)
    @Column(name = "permission_id", nullable = false)
    private Long permissionId;

    @Basic
    @ApiModelProperty(value = "补充方式：0=增加权限、1=删除权限", required = true)
    @Column(name = "add_mode", nullable = false)
    private Integer addMode;

}
