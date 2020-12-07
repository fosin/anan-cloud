package com.github.fosin.anan.platform.controller;

import com.github.fosin.anan.model.controller.ISimpleController;
import com.github.fosin.anan.model.service.ISimpleService;
import com.github.fosin.anan.platform.dto.request.AnanInternationalCharsetCreateDto;
import com.github.fosin.anan.platform.dto.request.AnanInternationalCharsetRetrieveDto;
import com.github.fosin.anan.platform.dto.request.AnanInternationalCharsetUpdateDto;
import com.github.fosin.anan.platform.entity.AnanInternationalCharsetEntity;
import com.github.fosin.anan.platform.service.inter.AnanInternationalCharsetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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

    @RequestMapping(path = "/keyvalue/internationalId/{internationalId}", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation("根据语言ID查找所有字符集实际数据")
    @ApiImplicitParam(
            name = "internationalId",
            value = "状态: 0=有效，1=无效",
            paramType = "path",
            required = true,
            dataTypeClass = Integer.class
    )
    public ResponseEntity<Map<String, Object>> findCharsetByInternationalId(@PathVariable Integer internationalId) {
        return ResponseEntity.ok(ananInternationalCharsetService.findCharsetByInternationalId(internationalId));
    }

    @Override
    public ISimpleService<AnanInternationalCharsetEntity, Long, AnanInternationalCharsetCreateDto, AnanInternationalCharsetRetrieveDto, AnanInternationalCharsetUpdateDto> getService() {
        return ananInternationalCharsetService;
    }
}
