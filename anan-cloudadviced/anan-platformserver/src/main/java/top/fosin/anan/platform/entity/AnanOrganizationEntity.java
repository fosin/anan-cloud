package top.fosin.anan.platform.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import top.fosin.anan.jpa.entity.PidCreateUpdateEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 系统机构表(AnanOrganization)实体类
 *
 * @author fosin
 * @date 2019-01-27 18:42:49
 * @since 1.0.0
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@Table(name = "anan_organization")
@ApiModel(value = "机构表实体类", description = "机构的实体类")
public class AnanOrganizationEntity extends PidCreateUpdateEntity<Long> implements Serializable {
    private static final long serialVersionUID = -27331190994806707L;

    @Basic
    @ApiModelProperty(value = "顶级机构编号：一般指用户注册的机构，通常是一个集团组的最高级别机构，取值于id", required = true)
    @Column(name = "top_id", nullable = false)
    private Long topId;

    @Basic
    @ApiModelProperty(value = "机构编码，自定义机构编码，下级机构必须以上级机构编码为前缀", required = true)
    @Column(name = "code", nullable = false, length = 64)
    private String code;

    @Basic
    @ApiModelProperty(value = "机构名称", required = true)
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Basic
    @ApiModelProperty(value = "深度", required = true)
    @Column(name = "level", nullable = false)
    private Integer level;

    @Basic
    @ApiModelProperty(value = "机构全名")
    @Column(name = "fullname", length = 128)
    private String fullname;

    @Basic
    @ApiModelProperty(value = "机构地址")
    @Column(name = "address", length = 128)
    private String address;

    @Basic
    @ApiModelProperty(value = "机构电话")
    @Column(name = "telphone", length = 24)
    private String telphone;

    @Basic
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.id=11", required = true)
    @Column(name = "status", nullable = false)
    private Integer status;




}
