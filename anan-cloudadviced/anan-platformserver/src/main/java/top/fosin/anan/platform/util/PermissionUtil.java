package top.fosin.anan.platform.util;

import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.modules.pub.entity.PermissionEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fosin
 * @date 2021/10/15
 * @since 2.0.0
 */
public class PermissionUtil {
    public static <Entity extends PermissionEntity<ID>,
            ID extends Serializable> void saveNewPermission(Collection<Entity> beforeEntities,
                                                            Collection<Entity> afterEntities, IJpaRepository<Entity, ID> iJpaRepository) {
        //保存新增的权限
        List<Entity> entities = afterEntities.stream().filter(after -> beforeEntities.stream().noneMatch(before -> before.getPermissionId().equals(after.getPermissionId()))).collect(Collectors.toList());
        if (entities.size() > 0) {
            iJpaRepository.saveAll(entities);
        }
    }

    public static <Before extends PermissionEntity<ID>, after extends PermissionEntity<ID>, ID extends Serializable> void deletePermission(Collection<Before> beforeEntities, Collection<after> afterEntities, IJpaRepository<Before, ID> iJpaRepository) {
        //删除版本权限中不在版本新权限中的权限
        List<Before> entityList =
                beforeEntities.stream().filter(before -> afterEntities.stream().noneMatch(after -> after.getPermissionId().equals(before.getPermissionId()))).collect(Collectors.toList());
        if (entityList.size() > 0) {
            iJpaRepository.deleteAll(entityList);
        }
    }
}
