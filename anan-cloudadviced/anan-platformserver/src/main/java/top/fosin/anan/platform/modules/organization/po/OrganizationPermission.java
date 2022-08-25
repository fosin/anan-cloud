package top.fosin.anan.platform.modules.organization.po;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

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
@ApiModel(value = "机构权限表实体类", description = "机构权限的实体类")
public class OrganizationPermission extends OrganizIdPermissionId<Long> {
    private static final long serialVersionUID = -76400180272089246L;

}
