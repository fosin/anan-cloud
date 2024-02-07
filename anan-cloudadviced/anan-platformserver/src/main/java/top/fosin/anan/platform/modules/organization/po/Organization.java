package top.fosin.anan.platform.modules.organization.po;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import top.fosin.anan.jpa.po.IdCreateUpdatePidDeletePO;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

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
@SQLDelete(sql = "update anan_organization set deleted = 1 where id = ?")
@Table(name = "anan_organization")
@Where(clause = "deleted=0")
@Schema(description = "机构的实体类")
public class Organization extends IdCreateUpdatePidDeletePO<Long> {
    private static final long serialVersionUID = -27331190994806707L;

    @Basic
    @Schema(description = "顶级机构编号：一般指用户注册的机构，通常是一个集团组的最高级别机构，取值于id")
    @Column(name = "top_id", nullable = false)
    private Long topId;

    @Basic
    @Schema(description = "机构编码，自定义机构编码，下级机构必须以上级机构编码为前缀")
    @Column(name = "code", nullable = false, length = 64)
    private String code;

    @Basic
    @Schema(description = "机构名称")
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Basic
    @Schema(description = "深度")
    @Column(name = "level", nullable = false)
    private Integer level;

    @Basic
    @Schema(description = "机构全名")
    @Column(name = "fullname", length = 128)
    private String fullname;

    @Basic
    @Schema(description = "机构地址")
    @Column(name = "address", length = 128)
    private String address;

    @Basic
    @Schema(description = "机构电话")
    @Column(name = "telphone", length = 24)
    private String telphone;

    @Basic
    @Schema(description = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.id=11")
    @Column(name = "status", nullable = false)
    private Byte status;

}
