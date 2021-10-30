package top.fosin.anan.auth.entity;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.jpa.entity.OrganizIdCreateEntity;

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
@ApiModel(value = "用户角色表实体类", description = "用户角色的实体类")
public class AnanUserRoleEntity extends OrganizIdCreateEntity<Long> {
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
