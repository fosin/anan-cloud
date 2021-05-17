package top.fosin.anan.platform.service.inter;

import org.springframework.util.Assert;
import top.fosin.anan.platform.dto.res.OauthClientDetailsRespDto;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.dto.request.OauthClientDetailsCreateDto;
import top.fosin.anan.platform.dto.request.OauthClientDetailsRetrieveDto;
import top.fosin.anan.platform.dto.request.OauthClientDetailsUpdateDto;
import top.fosin.anan.platform.entity.OauthClientDetailsEntity;

/**
 * 2017/12/29.
 * Time:12:30
 *
 * @author fosin
 */
public interface OauthClientService extends ISimpleJpaService<OauthClientDetailsEntity, OauthClientDetailsRespDto,
        String, OauthClientDetailsCreateDto,
        OauthClientDetailsRetrieveDto,
        OauthClientDetailsUpdateDto> {


    /**
     * 根据实体类修改一条数据服务(null值字段不更新)
     *
     * @param dto 更新实体类
     */
    @Override
    default void update(OauthClientDetailsUpdateDto dto) {
        Assert.isTrue(dto != null && dto.getClientId() != null, "传入的更新数据实体对象不能为空!");
        IJpaRepository<OauthClientDetailsEntity, String> repository = getRepository();
        //查找数据库
        repository.findById(dto.getClientId()).ifPresent(entity -> {
            //复制新数据到当前数据实体类中，忽略空值
            BeanUtil.copyProperties(dto, entity, BeanUtil.getNullProperties(dto));

            repository.save(entity);
        });
    }
}
