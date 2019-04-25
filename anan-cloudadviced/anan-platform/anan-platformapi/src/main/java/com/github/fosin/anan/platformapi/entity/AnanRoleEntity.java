package com.github.fosin.anan.platformapi.entity;

import com.github.fosin.anan.jpa.entity.AbstractOrganizIdJpaEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

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
@ApiModel(value = "系统角色表实体类", description = "表(anan_role)的对应的实体类")
public class AnanRoleEntity extends AbstractOrganizIdJpaEntity<Long, Long> implements Serializable {
    private static final long serialVersionUID = -64971271359941469L;

    @Basic
    @ApiModelProperty(value = "角色名称", required = true)
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Basic
    @ApiModelProperty(value = "角色标识", required = true)
    @Column(name = "value", nullable = false, length = 64)
    private String value;

    @Basic
    @ApiModelProperty(value = "角色说明")
    @Column(name = "tips", length = 255)
    private String tips;

    @Basic
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.id=11", required = true)
    @Column(name = "status", nullable = false)
    private Integer status;

    @Basic
    @ApiModelProperty(value = "内置标志：是否是系统内置角色，内置角色不能被用户删除和修改，0=不是 1=是", required = true)
    @Column(name = "built_in", nullable = false)
    private Integer builtIn = 0;

    @Override
    public void setId(Long id) {
        super.setId(id);
    }
}
