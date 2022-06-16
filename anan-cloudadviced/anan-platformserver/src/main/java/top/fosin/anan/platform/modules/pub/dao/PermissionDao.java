package top.fosin.anan.platform.modules.pub.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.model.dto.res.TreeDto;
import top.fosin.anan.platform.modules.pub.entity.Permission;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/27
 */
@Repository
@Lazy
public interface PermissionDao extends IJpaRepository<Permission, Long> {
    List<Permission> findByPid(Long pid);

    long countByPid(Long pid);

    @Query(value = "select a.* from anan_permission a,anan_version_permission b where a.p_id = :pid " +
            "and a.id = b.permission_id and b.version_id = :versionId order by a.sort", nativeQuery = true)
    List<Permission> findAllByPidAndVersionId(@Param(value = TreeDto.PID_NAME) Long pid, @Param(value = "versionId") Long versionId);

    List<Permission> findAllByServiceId(Long serviceId);

    List<Permission> findByServiceIdIn(List<Long> serviceIds);
}
