package top.fosin.anan.auth.modules.auth.po;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import top.fosin.anan.jpa.po.IdCreateOrganizPO;

import jakarta.persistence.*;

/**
 * 系统用户角色表(AnanUserRole)实体类
 *
 * @author fosin
 * @date 2018-10-27 09:38:39
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@Table(name = "anan_user_role")
@Schema(description = "用户角色的实体类")
public class UserRole extends IdCreateOrganizPO<Long> {
    private static final long serialVersionUID = 907700875932623662L;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "role_id")
    @Schema(description = "取值于角色表anan_role.id")
    private Role role;

    @Column(name = "user_id")
    @Basic
    @Schema(description = "用户序号")
    private Long userId;
}
