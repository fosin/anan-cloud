package com.github.fosin.anan.platformapi.parameter;

import com.github.fosin.anan.platformapi.util.LoginUserUtil;

/**
 * @author fosin
 * @date 2019/5/13
 */
public class OrganStrategy implements IParameterStrategy {
    @Override
    public int getType() {
        return 1;
    }

    @Override
    public String getScope() {
        return LoginUserUtil.getUser().getOrganizId() + "";
    }
}
