package top.fosin.anan.platform.modules.role.service.inter;

import top.fosin.anan.jpa.service.ICreateJpaService;
import top.fosin.anan.jpa.service.IDeleteJpaService;
import top.fosin.anan.jpa.service.IRetrieveJpaService;
import top.fosin.anan.jpa.service.IUpdateJpaService;
import top.fosin.anan.platform.modules.role.dto.RoleCreateDTO;
import top.fosin.anan.platform.modules.role.dto.RoleDTO;
import top.fosin.anan.platform.modules.role.dto.RoleUpdateDTO;
import top.fosin.anan.platform.modules.role.po.Role;

import javax.validation.constraints.Positive;
import java.util.List;

/**
 * 系统角色表(anan_role)服务类
 *
 * @author fosin
 * @date 2023-05-14
 */
public interface RoleService extends 
        ICreateJpaService<RoleCreateDTO, RoleDTO, Long, Role>,
        IRetrieveJpaService<RoleDTO, Long, Role>,
        IUpdateJpaService<RoleUpdateDTO, Long, Role>,
        IDeleteJpaService<Long, Role> {
        List<RoleDTO> listOtherUsersByRoleId(@Positive Long userId);

        List<RoleDTO> listRoleUsersByRoleId(@Positive Long userId);

        List<RoleDTO> listByOrganizId(@Positive Long organizId);
}

