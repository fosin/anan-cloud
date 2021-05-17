package top.fosin.anan.auth.repository;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.fosin.anan.auth.entity.AnanUserEntity;
import top.fosin.anan.jpa.repository.IJpaRepository;

import java.util.List;

/**
 * 2017/12/27.
 * Time:14:52
 *
 * @author fosin
 */
@Repository
@Lazy
public interface UserRepository extends IJpaRepository<AnanUserEntity, Long> {
    AnanUserEntity findByUsercode(String usercode);
}

