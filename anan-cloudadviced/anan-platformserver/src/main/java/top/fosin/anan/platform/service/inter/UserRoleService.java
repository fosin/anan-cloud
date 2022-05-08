package top.fosin.anan.platform.service.inter;


import top.fosin.anan.cloudresource.dto.res.AnanUserRoleRespDto;
import top.fosin.anan.jpa.service.ICrudBatchJpaService;
import top.fosin.anan.platform.dto.req.AnanUserRoleReqDto;
import top.fosin.anan.platform.entity.AnanUserRoleEntity;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 *
 */
public interface UserRoleService extends ICrudBatchJpaService<AnanUserRoleEntity,
        AnanUserRoleRespDto,
        Long, Long, AnanUserRoleReqDto, AnanUserRoleReqDto> {
    List<AnanUserRoleEntity> findByUserId(Long userId);

    List<AnanUserRoleEntity> findByRoleId(Long roleId);

    List<AnanUserRoleEntity> findByUsercodeAndPassword(String usercode, String password);
}
