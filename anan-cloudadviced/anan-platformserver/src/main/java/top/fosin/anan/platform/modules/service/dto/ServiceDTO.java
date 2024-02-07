package top.fosin.anan.platform.modules.service.dto;



import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.Id;

import java.util.Date;
/**
 * 系统服务表(anan_service)DTO
 *
 * @author fosin
 * @date 2023-05-12
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统服务表(anan_service)DTO")
public class ServiceDTO extends Id<Long> {
    private static final long serialVersionUID = -90160903658912682L;
    @Schema(description = "服务标识", example = "String")
    private String code;

    @Schema(description = "服务名称", example = "String")
    private String name;

    @Schema(description = "创建人", example = "Long")
    private Long createBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "创建时间", example = "Date")
    private Date createTime;

    @Schema(description = "修改人", example = "Long")
    private Long updateBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "修改时间", example = "Date")
    private Date updateTime;

    @Schema(description = "状态码：0：禁用 1：启用", example = "Integer")
    private Byte status;

}
