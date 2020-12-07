package com.github.fosin.anan.platform.controller;

import com.github.fosin.anan.model.controller.ISimpleController;
import com.github.fosin.anan.model.service.ISimpleService;
import com.github.fosin.anan.platform.dto.request.AnanServiceCreateDto;
import com.github.fosin.anan.platform.dto.request.AnanServiceRetrieveDto;
import com.github.fosin.anan.platform.dto.request.AnanServiceUpdateDto;
import com.github.fosin.anan.platformapi.entity.AnanServiceEntity;
import com.github.fosin.anan.platform.service.inter.AnanServiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统服务表(anan_service: table)表控制层
 *
 * @author fosin
 * @date 2020-12-04 17:48:02
 */
@RestController
@RequestMapping("v1/service")
@Api(value = "v1/service", tags = "系统服务表(anan_service)接入层API")
public class AnanServiceController implements ISimpleController<AnanServiceEntity, Integer,
        AnanServiceCreateDto, AnanServiceRetrieveDto, AnanServiceUpdateDto> {

    private final AnanServiceService ananServiceService;

    public AnanServiceController(AnanServiceService ananServiceService) {
        this.ananServiceService = ananServiceService;
    }

    @RequestMapping(path = "/status/{status}")
    @ApiOperation("根据状态码查找所有服务数据")
    @ApiImplicitParam(
            name = "status",
            value = "状态: 0=有效，1=无效",
            paramType = "path",
            required = true,
            dataTypeClass = Integer.class
    )
    public ResponseEntity<List<AnanServiceEntity>> findAllByStatus(@PathVariable Integer status) {
        return ResponseEntity.ok(ananServiceService.findAllByStatus(status));
    }

    @Override
    public ISimpleService<AnanServiceEntity, Integer, AnanServiceCreateDto, AnanServiceRetrieveDto, AnanServiceUpdateDto> getService() {
        return ananServiceService;
    }
}
