package top.fosin.anan.platform.modules.organization.po;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.platform.modules.permission.po.PermissionId;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * 权限实体类
 *
 * @author fosin
 * @date 2021-10-15 12:50:38
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@MappedSuperclass
public class OrganizIdPermissionId<ID extends Serializable> extends PermissionId<ID> {
    private static final long serialVersionUID = 117455991817648863L;

    @Column(name = "organiz_id", nullable = false)
    @Basic
    @Schema(description = "机构序号")
    private ID organizId;

}
