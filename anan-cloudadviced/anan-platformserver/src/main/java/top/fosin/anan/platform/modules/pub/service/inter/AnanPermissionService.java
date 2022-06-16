package top.fosin.anan.platform.modules.pub.service.inter;


import top.fosin.anan.jpa.service.ICrudBatchJpaService;
import top.fosin.anan.model.prop.ForeignKeyProp;
import top.fosin.anan.model.prop.IdProp;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 系统版本权限系统版本权限表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface AnanPermissionService<ReqDto extends ForeignKeyProp<ID> & IdProp<ID>, RespDto extends IdProp<ID>, ID extends Serializable, Entity>
        extends ICrudBatchJpaService<ReqDto, RespDto, ID, Entity> {
    @Override
    default List<RespDto> createInBatch(Collection<ReqDto> reqDtos) {
        throw new UnsupportedOperationException("不支持批量创建操作！");
    }

    @Override
    default void deleteInBatch(ID foreingKey) {
        throw new UnsupportedOperationException("不支持批量删除操作！");
    }
}
