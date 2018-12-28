package com.github.fosin.cdp.platform.controller;

import com.github.fosin.cdp.mvc.controller.ISimpleController;
import com.github.fosin.cdp.mvc.service.ISimpleService;
import com.github.fosin.cdp.platformapi.entity.CdpSysDictionaryEntity;
import com.github.fosin.cdp.platformapi.service.inter.IDictionaryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description 字典控制器
 *
 * @author fosin
 */
@RestController
@RequestMapping("v1/dictionary")
@Api(value = "v1/dictionary", tags = "通用字典管理", description = "通用字典管理(增删改查)")
public class DictionaryController implements ISimpleController<CdpSysDictionaryEntity, Long, CdpSysDictionaryEntity, CdpSysDictionaryEntity, CdpSysDictionaryEntity> {
    @Autowired
    private IDictionaryService dictionaryService;

    @Override
    public ISimpleService<CdpSysDictionaryEntity, Long, CdpSysDictionaryEntity, CdpSysDictionaryEntity, CdpSysDictionaryEntity> getService() {
        return dictionaryService;
    }
}
