package top.fosin.anan.platform.modules.pub.service;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.modules.pub.dao.OauthClientDao;
import top.fosin.anan.platform.modules.pub.dto.OauthClientDetailsReqDto;
import top.fosin.anan.platform.modules.pub.po.OauthClientDetails;
import top.fosin.anan.platform.modules.pub.service.inter.OauthClientService;

import java.util.Objects;

/**
 * @author fosin
 * @date 2017/12/29
 */
@Service
@Lazy
@AllArgsConstructor
public class OauthClientServiceImpl implements OauthClientService {
    private final OauthClientDao oauthClientDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void preCreate(OauthClientDetailsReqDto reqDto) {
        reqDto.setClientSecret(passwordEncoder.encode(reqDto.getClientSecret()));
    }

    @Override
    public void update(OauthClientDetailsReqDto dto, String[] ignorePreperties) {
        String id = dto.getClientId();
        Assert.isTrue(StringUtils.hasText(id), "更新数据时ClientId不能为空!");
        OauthClientDetails updateEntiy = oauthClientDao.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("没有找到对应的数据!");
        });

        //复制新数据到当前数据实体类中
        BeanUtil.copyProperties(dto, updateEntiy, ignorePreperties);

        //如果密码与数据库中的不一致则需要加密
        if (!Objects.equals(dto.getClientSecret(), updateEntiy.getClientSecret())) {
            updateEntiy.setClientSecret(passwordEncoder.encode(updateEntiy.getClientSecret()));
        }
        oauthClientDao.save(updateEntiy);
    }

    @Override
    public OauthClientDao getDao() {
        return oauthClientDao;
    }
}
