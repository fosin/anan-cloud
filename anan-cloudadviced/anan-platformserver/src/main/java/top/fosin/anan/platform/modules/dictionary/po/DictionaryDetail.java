package top.fosin.anan.platform.modules.dictionary.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import top.fosin.anan.jpa.po.IdCreateUpdateDeletePO;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 系统通用字典明细表(AnanDictionaryDetail)实体类
 *
 * @author fosin
 * @date 2019-01-27 18:42:47
 * @since 1.0.0
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@SQLDelete(sql = "update anan_dictionary_detail set deleted = 1 where id = ?")
@Where(clause = "deleted=0")
@Table(name = "anan_dictionary_detail")
@ApiModel(value = "通用字典明细表实体类", description = "通用字典明细的实体类")
public class DictionaryDetail extends IdCreateUpdateDeletePO<Long> {
  private static final long serialVersionUID = -5294666385626195733L;

  @Basic
  @ApiModelProperty(value = "字典明细键，不能重复，字典内明细项唯一代码", required = true)
  @Column(name = "code", nullable = false)
  private Long code;

  @Basic
  @ApiModelProperty(value = "字典明细值表示字面意义")
  @Column(name = "name", length = 64)
  private String name;

  @Basic
  @ApiModelProperty(value = "取值于字典明细表anan_dictionary.id", required = true)
  @Column(name = "dictionary_id", nullable = false)
  private Long dictionaryId;

  @Basic
  @ApiModelProperty(value = "顺序，用于显示数据时的顺序，数值越小越靠前", required = true)
  @Column(name = "sort", nullable = false)
  private Integer sort;

  @Basic
  @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.id=11", required = true)
  @Column(name = "status", nullable = false)
  private Integer status;

  @Basic
  @ApiModelProperty(value = "标准代码，该字段通常用于对接标准字典")
  @Column(name = "scode", length = 64)
  private String scode;

  @Basic
  @ApiModelProperty(value = "作用域，用于字典明细项的作用域")
  @Column(name = "scope", length = 12)
  private String scope;

  @Basic
  @ApiModelProperty(value = "使用标志：0=未使用，1=已使用，已使用的字典就不能再修改name属性", required = true)
  @Column(name = "used", nullable = false)
  private Integer used = 0;

  @Basic
  @ApiModelProperty(value = "字典说明")
  @Column(name = "description", length = 120)
  private String description;
}
