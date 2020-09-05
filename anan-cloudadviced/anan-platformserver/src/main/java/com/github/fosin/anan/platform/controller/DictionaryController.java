package com.github.fosin.anan.platform.controller;

import com.github.fosin.anan.model.controller.ISimpleController;
import com.github.fosin.anan.model.service.ISimpleService;
import com.github.fosin.anan.cloudresource.dto.request.AnanDictionaryCreateDto;
import com.github.fosin.anan.cloudresource.dto.request.AnanDictionaryRetrieveDto;
import com.github.fosin.anan.cloudresource.dto.request.AnanDictionaryUpdateDto;
import com.github.fosin.anan.platformapi.entity.AnanDictionaryEntity;
import com.github.fosin.anan.platform.service.inter.DictionaryService;
import io.swagger.annotations.Api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description 字典控制器
 *
 * @author fosin
 */
@RestController
@RequestMapping("v1/dictionary")
@Api(value = "v1/dictionary", tags = "通用字典管理(增删改查)")
public class DictionaryController implements ISimpleController<AnanDictionaryEntity, Long, AnanDictionaryCreateDto, AnanDictionaryRetrieveDto, AnanDictionaryUpdateDto> {
    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @Override
    public ISimpleService<AnanDictionaryEntity, Long, AnanDictionaryCreateDto, AnanDictionaryRetrieveDto, AnanDictionaryUpdateDto> getService() {
        return dictionaryService;
    }
}
