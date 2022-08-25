package top.fosin.anan.auth.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import top.fosin.anan.auth.po.User;
import top.fosin.anan.jpa.repository.IJpaRepository;

/**
 * @author fosin
 * @date 2017/12/27
 */
@Repository
@Lazy
public interface UserDao extends IJpaRepository<Long, User> {
    User findByUsercode(String usercode);
}

