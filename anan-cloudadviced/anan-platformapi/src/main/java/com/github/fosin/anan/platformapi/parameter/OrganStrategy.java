package com.github.fosin.anan.platformapi.parameter;

import com.github.fosin.anan.platformapi.service.AnanUserDetailService;

/**
 * @author fosin
 * @date 2019/5/13
 */
public class OrganStrategy implements IParameterStrategy {
    private final AnanUserDetailService ananUserDetailService;

    public OrganStrategy(AnanUserDetailService ananUserDetailService) {
        this.ananUserDetailService = ananUserDetailService;
    }

    @Override
    public int getType() {
        return 1;
    }

    @Override
    public String getScope() {
        return ananUserDetailService.getAnanOrganizId() + "";
    }
}
