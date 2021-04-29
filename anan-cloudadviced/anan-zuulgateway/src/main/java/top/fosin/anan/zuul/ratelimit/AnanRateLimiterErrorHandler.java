package top.fosin.anan.zuul.ratelimit;

import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.repository.DefaultRateLimiterErrorHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义错误处理信息
 *
 * @author fosin
 * @date 2020/3/2
 * @since 2.0.0
 */
//@Component
@Slf4j
public class AnanRateLimiterErrorHandler extends DefaultRateLimiterErrorHandler {

    /**
     * 往redis存储限流请求信息的时候报错的处理，此方法一般不用重写
     *
     * @param key
     * @param e
     */
    @Override
    public void handleSaveError(String key, Exception e) {
        super.handleSaveError(key, e);
    }

    /**
     * 从redis取出限流请求信息的时候报错的处理，此方法一般不用重写
     *
     * @param key
     * @param e
     */
    @Override
    public void handleFetchError(String key, Exception e) {
        super.handleFetchError(key, e);
    }

    /**
     * 请求发生限流了，或者限流过程中发生错误了的处理
     * 对限流进行日志记录，返回限流的信息等，方便后期分析日志排查问题，这里就简单处理打印日志
     *
     * @param msg
     * @param e
     */
    @Override
    public void handleError(String msg, Exception e) {
        log.error("限流信息msg={}，错误信息e={}", msg, e);
        super.handleError(msg, e);
    }
}


