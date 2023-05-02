package top.fosin.anan.cloudresource.parameter;

import top.fosin.anan.cloudresource.service.CurrentUserService;

/**
 * 机构参数远程模式
 *
 * @author fosin
 * @date 2019/5/13
 */
public class RemoteOrganParameter extends RemoteParameter {
    public RemoteOrganParameter(CurrentUserService currentUserService) {
        super(new OrganStrategy(currentUserService));
    }
}
