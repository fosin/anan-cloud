package top.fosin.anan.auth.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import top.fosin.anan.jpa.entity.OrganizIdCreateEntity;

import javax.persistence.*;
import java.io.Serializable;

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
@ApiModel(value = "系统用户角色表实体类", description = "表(anan_user_role)的对应的实体类")
public class AnanUserRoleEntity extends OrganizIdCreateEntity<Long> implements Serializable {
    private static final long serialVersionUID = 907700875932623662L;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "role_id")
    @ApiModelProperty(value = "角色ID", notes = "取值于角色表anan_role.id")
    private AnanRoleEntity role;

    @Column(name = "user_id")
    @Basic
    @ApiModelProperty(value = "用户ID", notes = "用户ID")
    private Long userId;
}
