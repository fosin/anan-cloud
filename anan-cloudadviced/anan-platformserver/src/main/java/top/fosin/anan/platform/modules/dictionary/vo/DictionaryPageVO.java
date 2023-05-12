package top.fosin.anan.platform.modules.dictionary.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
 * 系统通用字典表(anan_dictionary)集合VO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统通用字典表集合VO", description = "系统通用字典表(anan_dictionary)集合VO")
public class DictionaryPageVO extends Id<Long> {
    private static final long serialVersionUID = -46559257564357169L;
    @ApiModelProperty(value = "字典名称")
    private String name;

    @ApiModelProperty(value = "字典类别，区别字典的大分类，取值于表anan_dictionary.code = 1数据")
    @Translate2String(service = DicDetailGrpcServiceImpl.class, dicId = "1")
    private Integer type;

    @ApiModelProperty(value = "字典作用域，以字典类别为前提，在字典类别基础上再次细化分类字典")
    private String scope;

    @ApiModelProperty(value = "详细说明")
    private String description;

    @ApiModelProperty(value = "创建人")
    @Translate2String(service = UserGrpcServiceImpl.class, dicId = "")
    private Long createBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建日期")
    private Date createTime;

    @ApiModelProperty(value = "修改人")
    @Translate2String(service = UserGrpcServiceImpl.class, dicId = "")
    private Long updateBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "修改日期")
    private Date updateTime;

}
