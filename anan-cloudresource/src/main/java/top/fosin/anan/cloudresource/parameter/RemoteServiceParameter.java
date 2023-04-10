package top.fosin.anan.cloudresource.parameter;

import top.fosin.anan.cloudresource.service.UserInfoService;

/**
 * 服务参数远程模式
 *
 * @author fosin
 * @date 2021/5/2
 */
public class RemoteServiceParameter extends RemoteParameter {
    public RemoteServiceParameter(UserInfoService userInfoService) {
        super(new ServiceStrategy(userInfoService));
    }
}
