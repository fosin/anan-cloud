package com.github.fosin.anan.platformapi.parameter;

import com.github.fosin.anan.cloudresource.util.AnanUserDetailUtil;

/**
 * @author fosin
 * @date 2019/5/13
 */
public class OrganStrategy implements IParameterStrategy {
    private final AnanUserDetailUtil ananUserDetailUtil;

    public OrganStrategy(AnanUserDetailUtil ananUserDetailUtil) {
        this.ananUserDetailUtil = ananUserDetailUtil;
    }

    @Override
    public int getType() {
        return 1;
    }

    @Override
    public String getScope() {
        return ananUserDetailUtil.getAnanUser().getOrganizId() + "";
    }
}
