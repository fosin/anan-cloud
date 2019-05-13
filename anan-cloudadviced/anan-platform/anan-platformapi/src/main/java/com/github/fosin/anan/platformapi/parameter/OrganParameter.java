package com.github.fosin.anan.platformapi.parameter;

/**
 * @author fosin
 * @date 2019/5/13
 */
public class OrganParameter extends RemoteParameter {
    public OrganParameter() {
        super(new OrganStrategy());
    }
}
