package com.github.fosin.anan.platformapi.repository;

import com.github.fosin.anan.jpa.repository.IJpaRepository;
import com.github.fosin.anan.platformapi.entity.AnanUserAllPermissionsEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author fosin
 */
@Repository
@Lazy
public interface UserAllPermissionsRepository extends IJpaRepository<AnanUserAllPermissionsEntity, Long> {

    List<AnanUserAllPermissionsEntity> findByUserId(Long userId);
}
