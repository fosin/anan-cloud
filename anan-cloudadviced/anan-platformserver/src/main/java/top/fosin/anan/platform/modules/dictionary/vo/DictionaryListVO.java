package top.fosin.anan.platform.modules.dictionary.vo;

                                                                                                                                                                            
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;
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
public class DictionaryListVO extends Id<Long> {
    private static final long serialVersionUID = -41252549845922912L;
    @ApiModelProperty(value = "字典名称")
    private String name;

    @ApiModelProperty(value = "字典类别，区别字典的大分类，取值于表anan_dictionary.code = 1数据")
    private Integer type;

    @ApiModelProperty(value = "字典作用域，以字典类别为前提，在字典类别基础上再次细化分类字典")
    private String scope;

    @ApiModelProperty(value = "详细说明")
    private String description;

}
