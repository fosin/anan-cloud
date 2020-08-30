package com.github.fosin.anan.auth.service;

import com.github.fosin.anan.auth.service.inter.PermissionService;
import com.github.fosin.anan.jpa.repository.IJpaRepository;
import com.github.fosin.anan.platformapi.entity.AnanPermissionEntity;
import com.github.fosin.anan.platformapi.repository.PermissionRepository;
import com.github.fosin.anan.pojo.dto.AnanUserAllPermissionDto;
import com.github.fosin.anan.pojo.constant.RedisConstant;
import com.github.fosin.anan.pojo.dto.request.AnanPermissionRetrieveDto;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 2017/12/29.
 * Time:12:38
 *
 * @author fosin
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    @Cacheable(value = RedisConstant.ANAN_PERMISSION, key = "#id")
    public AnanPermissionEntity findById(Long id) {
        return permissionRepository.findById(id).orElse(null);
    }

    @Override
    public List<AnanPermissionRetrieveDto> findByAppName(String appName) {
        List<AnanPermissionEntity> byAppName = permissionRepository.findByAppName(appName);
        List<AnanPermissionRetrieveDto> retrieveDtos = new ArrayList<>();
        for (AnanPermissionEntity ananPermissionEntity : byAppName) {
            AnanPermissionRetrieveDto retrieveDto = new AnanPermissionRetrieveDto();
            BeanUtils.copyProperties(ananPermissionEntity, retrieveDto);
            retrieveDto.setId(ananPermissionEntity.getId());
            retrieveDtos.add(retrieveDto);
        }
        return retrieveDtos;
    }

    @Override
    public IJpaRepository<AnanPermissionEntity, Long> getRepository() {
        return permissionRepository;
    }

    @Override
    public AnanUserAllPermissionDto copyPermissionData(AnanPermissionEntity entity) {
        AnanUserAllPermissionDto dto = new AnanUserAllPermissionDto();
        dto.setId(entity.getId());
        dto.setAppName(entity.getAppName());
        dto.setCode(entity.getCode());
        dto.setIcon(entity.getIcon());
        dto.setLevel(entity.getLevel());
        dto.setSort(entity.getSort());
        dto.setStatus(entity.getStatus());
        dto.setMethod(entity.getMethod());
        dto.setName(entity.getName());
        dto.setPath(entity.getPath());
        dto.setPid(entity.getPid());
        dto.setType(entity.getType());
        dto.setUrl(entity.getUrl());
        dto.setLeaf(entity.getLeaf());

        List<AnanPermissionEntity> children = entity.getChildren();
        List<AnanUserAllPermissionDto> childrenPermission = new ArrayList<>();
        if (children != null && children.size() > 0) {
            children.forEach(ananPermissionEntity -> {
                childrenPermission.add(copyPermissionData(ananPermissionEntity));
            });
        }
        dto.setChildren(childrenPermission);
        return dto;
    }
}
