package top.fosin.anan.cloudresource.parameter;

import top.fosin.anan.cloudresource.service.CurrentUserService;

/**
 * 机构参数
 *
 * @author fosin
 * @date 2019/5/13
 */
public class OrganStrategy implements IParameterStrategy {
    private final CurrentUserService currentUserService;

    public OrganStrategy(CurrentUserService currentUserService) {
        this.currentUserService = currentUserService;
    }

    @Override
    public int getType() {
        return ParameterType.Organization.getTypeValue();
    }

    @Override
    public String getTypeName() {
        return ParameterType.Organization.getTypeName();
    }

    @Override
    public String getScope() {
        return currentUserService.isSysAdminUser() ? null : currentUserService.getAnanOrganizId() + "";
    }
}
