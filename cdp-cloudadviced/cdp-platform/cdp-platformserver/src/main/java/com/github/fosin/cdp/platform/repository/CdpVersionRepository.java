package com.github.fosin.cdp.platform.repository;

import com.github.fosin.cdp.platform.entity.CdpVersionEntity;
import com.github.fosin.cdp.jpa.repository.IJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Lazy;
/**
 * 系统版本表(cdp_version)表数据库访问层
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Repository
@Lazy
public interface CdpVersionRepository extends IJpaRepository<CdpVersionEntity, Long> {
}
