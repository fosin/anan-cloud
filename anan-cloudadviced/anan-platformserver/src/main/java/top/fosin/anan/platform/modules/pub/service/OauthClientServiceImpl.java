package top.fosin.anan.platform.modules.pub.service;


import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.modules.pub.dto.OauthClientDetailsReqDto;
import top.fosin.anan.platform.modules.pub.dto.OauthClientDetailsRespDto;
import top.fosin.anan.platform.modules.pub.entity.OauthClientDetails;
import top.fosin.anan.platform.modules.pub.dao.OauthClientDao;
import top.fosin.anan.platform.modules.pub.service.inter.OauthClientService;

import java.util.Objects;

/**
 * @author fosin
 * @date 2017/12/29
 */
@Service
@Lazy
public class OauthClientServiceImpl implements OauthClientService {

    private final OauthClientDao oauthClientDao;
    private final PasswordEncoder passwordEncoder;

    public OauthClientServiceImpl(OauthClientDao oauthClientDao, PasswordEncoder passwordEncoder) {
        this.oauthClientDao = oauthClientDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OauthClientDetailsRespDto create(OauthClientDetailsReqDto entity) {
        Assert.notNull(entity, "传入的创建数据实体对象不能为空!");
        String id = entity.getClientId();
        OauthClientDetails createEntity = oauthClientDao.findById(id).orElse(null);
        Assert.isNull(createEntity, "该数据已存在，请重新设置客户端标识以区分");
        createEntity = new OauthClientDetails();
        BeanUtils.copyProperties(entity, createEntity);
        createEntity.setClientSecret(passwordEncoder.encode(createEntity.getClientSecret()));
        return BeanUtil.copyProperties(oauthClientDao.save(createEntity), OauthClientDetailsRespDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(OauthClientDetailsReqDto dto) {
        String id = dto.getClientId();
        Assert.isTrue(StringUtils.hasText(id), "更新数据时ClientId不能为空!");
        OauthClientDetails updateEntiy = oauthClientDao.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("没有找到对应的数据!");
        });

        //复制新数据到当前数据实体类中
        BeanUtils.copyProperties(dto, updateEntiy);

        //如果密码与数据库中的不一致则需要加密
        if (!Objects.equals(dto.getClientSecret(), updateEntiy.getClientSecret())) {
            updateEntiy.setClientSecret(passwordEncoder.encode(updateEntiy.getClientSecret()));
        }
        oauthClientDao.save(updateEntiy);
    }

    @Override
    public OauthClientDao getRepository() {
        return oauthClientDao;
    }
}
