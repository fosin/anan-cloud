package top.fosin.anan.platform.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import top.fosin.anan.jpa.entity.SoftDeleteEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 系统通用字典表(AnanDictionary)实体类
 *
 * @author fosin
 * @date 2019-01-27 18:42:43
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@SQLDelete(sql = "update anan_dictionary set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
@Table(name = "anan_dictionary")
@ApiModel(value = " ", description = "通用字典的实体类")
public class AnanDictionaryEntity extends SoftDeleteEntity<Long> {
    private static final long serialVersionUID = -48637204028516104L;

    @Basic
    @ApiModelProperty(value = "字典名称", required = true)
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Basic
    @ApiModelProperty(value = "字典类别，区别字典的大分类，取值于表anan_dictionary.id = 1数据", required = true)
    @Column(name = "type", nullable = false)
    private Integer type;

    @Basic
    @ApiModelProperty(value = "字典作用域，以字典类别为前提，在字典类别基础上再次细化分类字典")
    @Column(name = "scope", length = 64)
    private String scope;

    @Basic
    @ApiModelProperty(value = "字典说明")
    @Column(name = "description", length = 120)
    private String description;
}
