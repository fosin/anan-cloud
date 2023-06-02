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
 * 国际化语言集(AnanInternational)实体类
 *
 * @author fosin
 * @date 2020-12-08 20:54:10
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@Table(name = "anan_international")
@ApiModel(value = "国际化语言的实体类", description = "国际化语言的实体类")
public class International extends IdCreateUpdatePO<Long> {
    private static final long serialVersionUID = 717249075172389735L;

    /**
     * 标识
     */
    @Basic
    @Column(name = "code", nullable = false)
    private String code;

    /**
     * 名称
     */
    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 图标
     */
    @Basic
    @Column(name = "icon")
    private String icon;

    /**
     * 状态：0=启用，1=禁用
     */
    @Basic
    @Column(name = "status", nullable = false)
    private Byte status;

    /**
     * 默认标志
     */
    @Basic
    @Column(name = "default_flag", nullable = false)
    private Integer defaultFlag;
}
