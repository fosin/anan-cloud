package top.fosin.anan.platform.modules.version.po;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import top.fosin.anan.jpa.po.IdCreateUpdatePO;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Date;

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
@Schema(description = "版本的实体类")
public class Version extends IdCreateUpdatePO<Long> {
    private static final long serialVersionUID = -54459367678395780L;

    @Basic
    @Schema(description = "版本名称")
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Basic
    @Schema(description = "版本类型：0=收费版 1=免费版 2=开发版")
    @Column(name = "type", nullable = false)
    private Byte type;

    @Basic
    @Schema(description = "版本价格")
    @Column(name = "price", nullable = false, precision = 8, scale = 2)
    private Double price;

    @Basic
    @Schema(description = "开始时间")
    @Column(name = "begin_time", nullable = false)
    private Date beginTime;

    @Basic
    @Schema(description = "结束时间")
    @Column(name = "end_time", nullable = false)
    private Date endTime;

    @Basic
    @Schema(description = "有效期：一般按天计算")
    @Column(name = "validity", nullable = false)
    private Integer validity;

    @Basic
    @Schema(description = "到期后保护期")
    @Column(name = "protect_days", nullable = false)
    private Integer protectDays;

    @Basic
    @Schema(description = "最大机构数：0=无限制 n=限制数")
    @Column(name = "max_organizs", nullable = false)
    private Integer maxOrganizs;

    @Basic
    @Schema(description = "最大机构数：0=无限制 n=限制数")
    @Column(name = "max_users", nullable = false)
    private Integer maxUsers;

    @Basic
    @Schema(description = "是否试用：0=不试用 1=试用")
    @Column(name = "tryout", nullable = false)
    private Integer tryout;

    @Basic
    @Schema(description = "试用天数")
    @Column(name = "tryout_days", nullable = false)
    private Integer tryoutDays;

    @Basic
    @Schema(description = "启用状态：0=启用，1=禁用")
    @Column(name = "status", nullable = false)
    private Byte status;

    @Basic
    @Schema(description = "版本描述")
    @Column(name = "description", length = 512)
    private String description;



}
