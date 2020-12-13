package com.github.fosin.anan.platform.controller;

import com.github.fosin.anan.model.controller.ISimpleController;
import com.github.fosin.anan.model.module.PageModule;
import com.github.fosin.anan.model.result.ListResult;
import com.github.fosin.anan.model.service.ISimpleService;
import com.github.fosin.anan.platform.dto.request.AnanInternationalCharsetCreateDto;
import com.github.fosin.anan.platform.dto.request.AnanInternationalCharsetRetrieveDto;
import com.github.fosin.anan.platform.dto.request.AnanInternationalCharsetUpdateDto;
import com.github.fosin.anan.platform.entity.AnanInternationalCharsetEntity;
import com.github.fosin.anan.platform.service.inter.AnanInternationalCharsetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 国际化明显(anan_international_charset: table)表控制层
 *
 * @author fosin
 * @date 2020-12-04 11:05:46
 */
@RestController
@RequestMapping("v1/international/charset")
@Api(value = "v1/international/charset", tags = "国际化明显(anan_international_charset)接入层API")
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

    @PostMapping({"/pageList/internationalId/{internationalId}"})
    @ApiOperation("根据分页条件查找分页信息")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "pageModule",
                    value = "分页条件实体类",
                    paramType = "body",
                    required = true,
                    dataTypeClass = PageModule.class
            ),
            @ApiImplicitParam(
                    name = "internationalId",
                    value = "语言ID，对应anan_international.id",
                    paramType = "path",
                    required = true,
                    dataTypeClass = Integer.class
            )
    })
    public ResponseEntity<ListResult<AnanInternationalCharsetEntity>> findAllCharsetPageByinternationalId(@RequestBody PageModule pageModule, @PathVariable Integer internationalId) {
        return ResponseEntity.ok(ananInternationalCharsetService.findAllCharsetPageByinternationalId(pageModule, internationalId));
    }

    @Override
    public ISimpleService<AnanInternationalCharsetEntity, Long, AnanInternationalCharsetCreateDto, AnanInternationalCharsetRetrieveDto, AnanInternationalCharsetUpdateDto> getService() {
        return ananInternationalCharsetService;
    }
}
