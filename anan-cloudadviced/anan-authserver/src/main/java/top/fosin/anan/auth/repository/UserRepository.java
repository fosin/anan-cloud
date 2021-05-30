package top.fosin.anan.auth.repository;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import top.fosin.anan.auth.entity.AnanUserEntity;
import top.fosin.anan.jpa.repository.IJpaRepository;

/**
 * @author fosin
 * @date 2017/12/27
 */
@Repository
@Lazy
public interface UserRepository extends IJpaRepository<AnanUserEntity, Long> {
    AnanUserEntity findByUsercode(String usercode);
}

