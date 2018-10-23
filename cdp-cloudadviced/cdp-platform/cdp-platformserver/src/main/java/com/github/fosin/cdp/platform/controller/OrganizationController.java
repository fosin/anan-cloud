package com.github.fosin.cdp.platform.controller;

import com.github.fosin.cdp.core.exception.CdpControllerException;
import com.github.fosin.cdp.core.exception.CdpServiceException;
import com.github.fosin.cdp.mvc.controller.AbstractBaseController;
import com.github.fosin.cdp.mvc.controller.ISimpleController;
import com.github.fosin.cdp.mvc.service.ISimpleService;
import com.github.fosin.cdp.platformapi.entity.CdpSysOrganizationEntity;
import com.github.fosin.cdp.platformapi.service.inter.IOrganizationService;
import com.github.fosin.cdp.util.TreeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Description
 *
 * @author fosin
 */
@RestController
@Slf4j
@RequestMapping("v1/organiz")
@Api(value = "v1/organiz",tags = "机构管理",description = "机构管理相关操作(增删改查)")
public class OrganizationController extends AbstractBaseController
        implements ISimpleController<CdpSysOrganizationEntity, Integer> {
    @Autowired
    private IOrganizationService organizationService;

    @ApiOperation("根据父机构ID获取其孩子节点数据")
    @ApiImplicitParam(name = "pId", value = "父节点ID,CdpSysOrganizationEntity.pid")
    @PostMapping("/listChild/{pId}")
    public ResponseEntity<Object> listChild(@PathVariable("pId") Integer pId) throws CdpServiceException {
        return ResponseEntity.ok(organizationService.findByPid(pId));
    }

    @ApiOperation("根据父机构ID获取其所有后代节点数据")
    @ApiImplicitParam(name = "pId", value = "父节点ID,CdpSysOrganizationEntity.pid")
    @PostMapping("/listAllChild/{pId}")
    public ResponseEntity<Object> listAllChild(@PathVariable("pId") Integer pId) throws CdpServiceException {
        String code;
        if (pId == 0) {
            List<CdpSysOrganizationEntity> list = organizationService.findByPid(pId);
            code = list.get(0).getCode();
        } else {
            code = organizationService.findOne(pId).getCode();
        }
        return ResponseEntity.ok(organizationService.findByCodeStartingWith(code));

    }

    @ApiOperation("生成机构树")
    @RequestMapping(value = "/tree",method = {RequestMethod.POST})
    public ResponseEntity<Object> tree() throws CdpControllerException {
        Collection<CdpSysOrganizationEntity> list = organizationService.findAll();

        CdpSysOrganizationEntity root = null;
        for (CdpSysOrganizationEntity entity : list) {
            if (0 == entity.getpId()) {
                root = entity;
                break;
            }
        }
        if (list.size() < 1) {
            throw new CdpControllerException("没有找到任何机构数据!");
        }
        if (root == null) {
            throw new CdpControllerException("未找到根节点数据!");
        }

        TreeUtil.createTree(list, root, "id", "pId", "children");
        List<CdpSysOrganizationEntity> result = new ArrayList<>();
        result.add(root);
        return ResponseEntity.ok(result);
    }

    @Override
    public ISimpleService<CdpSysOrganizationEntity, Integer> getService() {
        return organizationService;
    }
}
