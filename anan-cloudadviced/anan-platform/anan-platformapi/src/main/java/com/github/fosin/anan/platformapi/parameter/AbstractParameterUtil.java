package com.github.fosin.anan.platformapi.parameter;

import com.github.fosin.anan.platformapi.entity.AnanParameterEntity;

/**
 * Description:
 *
 * @author fosin
 * @date 2018.8.6
 */
public class AbstractParameterUtil {
    protected static String getValue(AnanParameterEntity entity) {
        return (entity == null) ? "" : entity.getValue();
    }
}
