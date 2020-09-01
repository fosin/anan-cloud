package com.github.fosin.anan.cloudresource.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.color.ANSIConstants;
import ch.qos.logback.core.pattern.color.ForegroundCompositeConverterBase;

/**
 * @author fosin
 * @date 2020/8/31
 * @since 2.1.0
 */
public class LogbackColorful extends ForegroundCompositeConverterBase<ILoggingEvent> {

    @Override
    protected String getForegroundColorCode(ILoggingEvent event) {
        Level level = event.getLevel();
        switch (level.toInt()) {
            //ERROR等级为红色
            case Level.ERROR_INT:
                return ANSIConstants.RED_FG;
            //WARN等级为黄色
            case Level.WARN_INT:
                return ANSIConstants.YELLOW_FG;
            //INFO等级为白色
            case Level.INFO_INT:
                return ANSIConstants.WHITE_FG;
            //DEBUG等级为青色
            case Level.DEBUG_INT:
                return ANSIConstants.CYAN_FG;
            //DEBUG等级为蓝色
            case Level.TRACE_INT:
                return ANSIConstants.BLUE_FG;
            //其他为默认颜色
            default:
                return ANSIConstants.DEFAULT_FG;
        }
    }
}
