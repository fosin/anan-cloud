package com.github.fosin.anan.platformapi.parameter;

import com.github.fosin.anan.pojo.util.AnanUserDetailUtil;

/**
 * @author fosin
 * @date 2019/5/13
 */
public class UserStrategy implements IParameterStrategy {
    private final AnanUserDetailUtil ananUserDetailUtil;

    public UserStrategy(AnanUserDetailUtil ananUserDetailUtil) {
        this.ananUserDetailUtil = ananUserDetailUtil;
    }

    @Override
    public int getType() {
        return 2;
    }

    @Override
    public String getScope() {
        return ananUserDetailUtil.getAnanUser().getId() + "";
    }
}
