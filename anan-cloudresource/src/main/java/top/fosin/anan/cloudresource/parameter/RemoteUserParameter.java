package top.fosin.anan.cloudresource.parameter;

import top.fosin.anan.cloudresource.service.UserInfoService;

/**
 * 用户参数远程模式
 *
 * @author fosin
 * @date 2019/5/13
 */
public class RemoteUserParameter extends RemoteParameter {
    public RemoteUserParameter(UserInfoService userInfoService) {
        super(new UserStrategy(userInfoService));
    }
}
