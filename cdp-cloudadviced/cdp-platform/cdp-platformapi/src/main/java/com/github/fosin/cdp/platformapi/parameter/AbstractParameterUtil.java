package com.github.fosin.cdp.platformapi.parameter;

import com.github.fosin.cdp.platformapi.entity.CdpParameterEntity;

/**
 * Description:
 *
 * @author fosin
 * @date 2018.8.6
 */
public class AbstractParameterUtil {
    protected static String getValue(CdpParameterEntity entity) {
        return (entity == null) ? "" : entity.getValue();
    }
}
