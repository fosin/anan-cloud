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
 * 国际化语言字符集(anan_international_charset)集合VO
 *
 * @author fosin
 * @date 2023-05-12
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "国际化语言字符集(anan_international_charset)集合VO")
public class InternationalCharsetPageVO extends Id<Long> {
    private static final long serialVersionUID = -78231511396985223L;
    @Schema(description = "国际化语言ID")
    private Long internationalId;

    @Schema(description = "服务ID")
    private Long serviceId;

    @Schema(description = "自定义字符集")
    private String charset;

    @Schema(description = "状态：0=启用，1=禁用")
    @Translate2String(service = DicDetailGrpcServiceImpl.class, dicId = "11")
    private Byte status;

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
