package top.fosin.anan.platform.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import top.fosin.anan.jpa.entity.OrganizIdCreateEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 系统机构权限表(AnanOrganizationPermission)实体类
 *
 * @author fosin
 * @date 2019-01-28 12:50:42
 * @since 1.0.0
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@Table(name = "anan_organization_permission")
@ApiModel(value = "系统机构权限表实体类", description = "表(anan_organization_permission)的对应的实体类")
public class AnanOrganizationPermissionEntity extends OrganizIdCreateEntity<Long> implements Serializable {
    private static final long serialVersionUID = -76400180272089246L;

    @Basic
    @ApiModelProperty(value = "权限ID", required = true)
    @Column(name = "permission_id", nullable = false)
    private Long permissionId;

}
