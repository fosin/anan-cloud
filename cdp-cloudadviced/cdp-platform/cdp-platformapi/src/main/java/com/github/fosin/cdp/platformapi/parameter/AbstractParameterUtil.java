package com.github.fosin.cdp.platformapi.parameter;

import com.github.fosin.cdp.platformapi.entity.CdpSysParameterEntity;

/**
 * Description:
 *
 * @author fosin
 * @date 2018.8.6
 */
public class AbstractParameterUtil {
    protected static String getValue(CdpSysParameterEntity entity) {
        return (entity == null) ? "" : entity.getValue();
    }
}
