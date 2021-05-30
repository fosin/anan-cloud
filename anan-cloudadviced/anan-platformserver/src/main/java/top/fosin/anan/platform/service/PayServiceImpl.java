package top.fosin.anan.platform.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import top.fosin.anan.platform.repository.PayRepository;
import top.fosin.anan.platform.service.inter.PayService;

/**
 * 系统支付表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class PayServiceImpl implements PayService {
    private final PayRepository ananSysPayRepository;

    public PayServiceImpl(PayRepository ananSysPayRepository) {
        this.ananSysPayRepository = ananSysPayRepository;
    }

    /**
     * 获取DAO
     */
    @Override
    public PayRepository getRepository() {
        return ananSysPayRepository;
    }
}
