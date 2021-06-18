package top.fosin.anan.platform.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import top.fosin.anan.platform.repository.PayDetailRepository;
import top.fosin.anan.platform.service.inter.PayDetailService;

/**
 * 支付明细支付明细表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class PayDetailServiceImpl implements PayDetailService {
    private final PayDetailRepository ananSysPayDetailRepository;

    public PayDetailServiceImpl(PayDetailRepository ananSysPayDetailRepository) {
        this.ananSysPayDetailRepository = ananSysPayDetailRepository;
    }

    /**
     * 获取DAO
     */
    @Override
    public PayDetailRepository getRepository() {
        return ananSysPayDetailRepository;
    }

}
