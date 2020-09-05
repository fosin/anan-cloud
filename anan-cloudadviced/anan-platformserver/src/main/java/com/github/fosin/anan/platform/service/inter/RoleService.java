package com.github.fosin.anan.platform.service.inter;

import com.github.fosin.anan.model.module.PageModule;
import com.github.fosin.anan.model.result.Result;
import com.github.fosin.anan.jpa.service.ISimpleJpaService;
import com.github.fosin.anan.cloudresource.dto.request.AnanRoleCreateDto;
import com.github.fosin.anan.cloudresource.dto.request.AnanRoleRetrieveDto;
import com.github.fosin.anan.cloudresource.dto.request.AnanRoleUpdateDto;
import com.github.fosin.anan.platformapi.entity.AnanRoleEntity;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:30
 *
 * @author fosin
 */
public interface RoleService extends ISimpleJpaService<AnanRoleEntity, Long, AnanRoleCreateDto, AnanRoleRetrieveDto, AnanRoleUpdateDto> {

    List<AnanRoleEntity> findOtherUsersByRoleId(Long userId);

    List<AnanRoleEntity> findRoleUsersByRoleId(Long userId);

    Result findAllByOrganizId(Long organizId, PageModule pageModule);

    List<AnanRoleEntity> findAllByOrganizId(Long organizId);
}
