package top.fosin.anan.cloudresource.service.inter.rpc;


import top.fosin.anan.cloudresource.entity.res.DictionaryDetailRespDTO;

import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * 字典明细GRPC调用入口服务
 *
 * @author fosin
 * @date 2023/4/8
 * @since 3.0.0
 */
public interface DicDetailRpcService {
    DictionaryDetailRespDTO findOneByDicIdAndCode(@NotNull Long dicId, @NotNull Long code);

    List<DictionaryDetailRespDTO> listByDicId(@NotNull Long dicId);
}
