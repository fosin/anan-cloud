package top.fosin.anan.platform.modules.pay.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import top.fosin.anan.platform.modules.pay.dao.PayDao;
import top.fosin.anan.platform.modules.pay.service.inter.PayService;

/**
 * 支付表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class PayServiceImpl implements PayService {
    private final PayDao ananSysPayDao;

    public PayServiceImpl(PayDao ananSysPayDao) {
        this.ananSysPayDao = ananSysPayDao;
    }

    /**
     * 获取DAO
     */
    @Override
    public PayDao getRepository() {
        return ananSysPayDao;
    }
}
