package top.fosin.anan.platform.modules.pay.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import top.fosin.anan.platform.modules.pay.dao.PayDetailDao;
import top.fosin.anan.platform.modules.pay.service.inter.PayDetailService;

/**
 * 支付明细支付明细表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class PayDetailServiceImpl implements PayDetailService {
    private final PayDetailDao ananSysPayDetailDao;

    public PayDetailServiceImpl(PayDetailDao ananSysPayDetailDao) {
        this.ananSysPayDetailDao = ananSysPayDetailDao;
    }

    /**
     * 获取DAO
     */
    @Override
    public PayDetailDao getRepository() {
        return ananSysPayDetailDao;
    }

}
