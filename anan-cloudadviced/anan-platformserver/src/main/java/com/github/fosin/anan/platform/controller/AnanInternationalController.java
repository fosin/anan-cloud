package com.github.fosin.anan.platform.controller;

import com.github.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import com.github.fosin.anan.model.controller.ISimpleController;
import com.github.fosin.anan.model.service.ISimpleService;
import com.github.fosin.anan.platform.dto.request.AnanInternationalCreateDto;
import com.github.fosin.anan.platform.dto.request.AnanInternationalRetrieveDto;
import com.github.fosin.anan.platform.dto.request.AnanInternationalUpdateDto;
import com.github.fosin.anan.platform.entity.AnanInternationalEntity;
import com.github.fosin.anan.platform.service.inter.AnanInternationalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 国际化(anan_international: table)表控制层
 *
 * @author fosin
 * @date 2020-12-04 11:05:46
 */
@RestController
@RequestMapping(UrlPrefixConstant.INTERNATIONAL)
@Api(value = UrlPrefixConstant.INTERNATIONAL, tags = "国际化(anan_international)接入层API")
public class AnanInternationalController implements ISimpleController<AnanInternationalEntity, Integer,
        AnanInternationalCreateDto, AnanInternationalRetrieveDto, AnanInternationalUpdateDto> {

    private final AnanInternationalService ananInternationalService;

    public AnanInternationalController(AnanInternationalService ananInternationalService) {
        this.ananInternationalService = ananInternationalService;
    }

    @RequestMapping(path = "/status/{status}")
    @ApiOperation("根据状态码查找所有国际化语言清单")
    @ApiImplicitParam(
            name = "status",
            value = "状态: 0=有效，1=无效",
            paramType = "path",
            required = true,
            dataTypeClass = Integer.class
    )
    public ResponseEntity<List<AnanInternationalEntity>> findAllByStatus(@PathVariable Integer status) {
        return ResponseEntity.ok(ananInternationalService.findAllByStatus(status));
    }

    @RequestMapping(path = "/code/{code}")
    @ApiOperation("根据国际化语言编码查找国际化语言")
    @ApiImplicitParam(
            name = "code",
            value = "国际化语言编码",
            paramType = "path",
            required = true,
            dataTypeClass = String.class
    )
    public ResponseEntity<AnanInternationalEntity> findAllByCode(@PathVariable String code) {
        return ResponseEntity.ok(ananInternationalService.findByCode(code));
    }

    @RequestMapping(path = "/default")
    @ApiOperation("查找默认语言")
    public ResponseEntity<AnanInternationalEntity> findByDefaultFlag() {
        return ResponseEntity.ok(ananInternationalService.findByDefaultFlag());
    }

    @Override
    public ISimpleService<AnanInternationalEntity, Integer, AnanInternationalCreateDto, AnanInternationalRetrieveDto, AnanInternationalUpdateDto> getService() {
        return ananInternationalService;
    }
}
