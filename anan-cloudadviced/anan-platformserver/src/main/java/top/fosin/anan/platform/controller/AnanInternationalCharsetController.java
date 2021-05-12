package top.fosin.anan.platform.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.model.controller.ISimpleController;
import top.fosin.anan.platform.dto.request.AnanInternationalCharsetCreateDto;
import top.fosin.anan.platform.dto.request.AnanInternationalCharsetRetrieveDto;
import top.fosin.anan.platform.dto.request.AnanInternationalCharsetUpdateDto;
import top.fosin.anan.platform.entity.AnanInternationalCharsetEntity;
import top.fosin.anan.platform.service.inter.AnanInternationalCharsetService;

import java.util.List;

/**
 * 国际化明显(anan_international_charset: table)表控制层
 *
 * @author fosin
 * @date 2020-12-04 11:05:46
 */
@RestController
@RequestMapping(UrlPrefixConstant.INTERNATIONAL_CHARSET)
@Api(value = UrlPrefixConstant.INTERNATIONAL_CHARSET, tags = "国际化明显(anan_international_charset)接入层API")
public class AnanInternationalCharsetController implements ISimpleController<AnanInternationalCharsetEntity, Long,
        AnanInternationalCharsetCreateDto, AnanInternationalCharsetRetrieveDto, AnanInternationalCharsetUpdateDto> {

    private final AnanInternationalCharsetService ananInternationalCharsetService;

    public AnanInternationalCharsetController(AnanInternationalCharsetService ananInternationalCharsetService) {
        this.ananInternationalCharsetService = ananInternationalCharsetService;
    }

    @RequestMapping(path = "/internationalId/{internationalId}", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation("根据语言ID查找所有字符集清单")
    @ApiImplicitParam(
            name = "internationalId",
            value = "状态: 0=有效，1=无效",
            paramType = "path",
            required = true,
            dataTypeClass = Integer.class
    )
    public ResponseEntity<List<AnanInternationalCharsetEntity>> findAllByInternationalId(@PathVariable Integer internationalId) {
        return ResponseEntity.ok(ananInternationalCharsetService.findAllByInternationalId(internationalId));
    }

    @RequestMapping(path = "/internationalId/{internationalId}/serviceId/{serviceId}", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation("根据语言ID和服务ID查找所有字符集实际数据")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "internationalId",
                    value = "状态: 0=有效，1=无效",
                    paramType = "path",
                    required = true,
                    dataTypeClass = Integer.class
            ),
            @ApiImplicitParam(
                    name = "serviceId",
                    value = "服务ID，对应anan_service.id",
                    paramType = "path",
                    required = true,
                    dataTypeClass = Integer.class
            )
    })
    public ResponseEntity<List<AnanInternationalCharsetEntity>> findAllByInternationalIdAndServiceId(@PathVariable Integer internationalId, @PathVariable Integer serviceId) {
        return ResponseEntity.ok(ananInternationalCharsetService.findAllByInternationalIdAndServiceId(internationalId, serviceId));
    }

    @Override
    public AnanInternationalCharsetService getService() {
        return ananInternationalCharsetService;
    }
}
