package top.fosin.anan.platform.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.jpa.entity.CreateUpdateEntity;

/**
 * 系统版本表(AnanVersion)实体类
 *
 * @author fosin
 * @date 2019-01-28 12:50:37
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DynamicUpdate
@Table(name = "anan_version")
@ApiModel(value = "版本表实体类", description = "版本的实体类")
public class AnanVersionEntity extends CreateUpdateEntity<Long> {
    private static final long serialVersionUID = -54459367678395780L;

    @Basic
    @ApiModelProperty(value = "版本名称", required = true)
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Basic
    @ApiModelProperty(value = "版本类型：0=收费版 1=免费版 2=开发版", required = true)
    @Column(name = "type", nullable = false)
    private Integer type;

    @Basic
    @ApiModelProperty(value = "版本价格", required = true)
    @Column(name = "price", nullable = false, precision = 8, scale = 2)
    private Double price;

    @Basic
    @ApiModelProperty(value = "开始时间", required = true)
    @Column(name = "begin_time", nullable = false)
    private Date beginTime;

    @Basic
    @ApiModelProperty(value = "结束时间", required = true)
    @Column(name = "end_time", nullable = false)
    private Date endTime;

    @Basic
    @ApiModelProperty(value = "有效期：一般按天计算", required = true)
    @Column(name = "validity", nullable = false)
    private Integer validity;

    @Basic
    @ApiModelProperty(value = "到期后保护期", required = true)
    @Column(name = "protect_days", nullable = false)
    private Integer protectDays;

    @Basic
    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数", required = true)
    @Column(name = "max_organizs", nullable = false)
    private Integer maxOrganizs;

    @Basic
    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数", required = true)
    @Column(name = "max_users", nullable = false)
    private Integer maxUsers;

    @Basic
    @ApiModelProperty(value = "是否试用：0=不试用 1=试用", required = true)
    @Column(name = "tryout", nullable = false)
    private Integer tryout;

    @Basic
    @ApiModelProperty(value = "试用天数", required = true)
    @Column(name = "tryout_days", nullable = false)
    private Integer tryoutDays;

    @Basic
    @ApiModelProperty(value = "启用状态：0=启用，1=禁用", required = true)
    @Column(name = "status", nullable = false)
    private Integer status;

    @Basic
    @ApiModelProperty(value = "版本描述")
    @Column(name = "description", length = 512)
    private String description;



}
