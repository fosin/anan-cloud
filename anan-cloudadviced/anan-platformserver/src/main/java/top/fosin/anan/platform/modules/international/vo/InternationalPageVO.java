package top.fosin.anan.platform.modules.international.vo;



import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.cloudresource.grpc.service.DicDetailGrpcServiceImpl;
import top.fosin.anan.cloudresource.grpc.service.UserGrpcServiceImpl;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.converter.translate.Translate2String;
import top.fosin.anan.data.entity.Id;

import java.util.Date;
/**
 * 国际化语言集(anan_international)集合VO
 *
 * @author fosin
 * @date 2023-05-12
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "国际化语言集(anan_international)集合VO")
public class InternationalPageVO extends Id<Long> {
    private static final long serialVersionUID = 871388669133635610L;
    @Schema(description = "标识")
    private String code;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "状态：0=启用，1=禁用")
    @Translate2String(service = DicDetailGrpcServiceImpl.class, dicId = "11")
    private Byte status;

    @Schema(description = "默认标志")
    private Integer defaultFlag;

    @Translate2String(service = UserGrpcServiceImpl.class, dicId = "")
    @Schema(description = "创建人")
    private Long createBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "创建时间")
    private Date createTime;

    @Translate2String(service = UserGrpcServiceImpl.class, dicId = "")
    @Schema(description = "修改人")
    private Long updateBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "修改时间")
    private Date updateTime;

}
