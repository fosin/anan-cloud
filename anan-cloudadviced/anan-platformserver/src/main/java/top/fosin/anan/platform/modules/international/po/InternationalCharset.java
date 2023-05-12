package top.fosin.anan.platform.modules.international.po;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import top.fosin.anan.jpa.po.IdCreateUpdatePO;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 国际化语言字符集实体类
 *
 * @author fosin
 * @date 2020-12-09 10:34:33
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DynamicUpdate
@Table(name = "anan_international_charset")
@ApiModel(value = "国际化语言字符集实体类", description = "国际化语言字符集实体类")
public class InternationalCharset extends IdCreateUpdatePO<Long> {
    private static final long serialVersionUID = -47422702414130736L;

    /**
     * 国际化语言ID
     */
    @Basic
    @Column(name = "international_id", nullable = false)
    private Integer internationalId;

    /**
     * 服务ID
     */
    @Basic
    @Column(name = "service_id", nullable = false)
    private Long serviceId;

    /**
     * 自定义字符集
     */
    @Basic
    @Column(name = "charset")
    private String charset;

    /**
     * 状态：0=启用，1=禁用
     */
    @Basic
    @Column(name = "status", nullable = false)
    private Integer status;
}
