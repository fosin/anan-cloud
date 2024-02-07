package top.fosin.anan.platform.modules.role.po;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import top.fosin.anan.jpa.po.IdCreateUpdateOrganizPO;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * 系统角色表(AnanRole)实体类
 *
 * @author fosin
 * @date 2019-01-27 15:59:30
 * @since 1.0.0
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@Table(name = "anan_role")
@Schema(description = "角色的实体类")
public class Role extends IdCreateUpdateOrganizPO<Long> {
    private static final long serialVersionUID = -64971271359941469L;

    @Basic
    @Schema(description = "角色名称")
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Basic
    @Schema(description = "角色标识")
    @Column(name = "value", nullable = false, length = 64)
    private String value;

    @Basic
    @Schema(description = "角色说明")
    @Column(name = "tips")
    private String tips;

    @Basic
    @Schema(description = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.id=11")
    @Column(name = "status", nullable = false)
    private Byte status;

    @Basic
    @Schema(description = "内置标志：是否是系统内置角色，内置角色不能被用户删除和修改，0=不是 1=是")
    @Column(name = "built_in", nullable = false)
    private Byte builtIn = 0;

}
