package top.fosin.anan.zuul.filter;

import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author fosin
 * @date 2019.1.17
 */
@Slf4j
@Component
public class ErrorFilter extends SendErrorFilter {

    @Value("${error.path:/error}")
    private String errorPath;

    @Override
    public int filterOrder() {
        return -1;
    }

    @Override
    public Object run() {
        try {
            RequestContext ctx = RequestContext.getCurrentContext();
            Throwable throwable = getRealCause(ctx.getThrowable());
            log.error("", throwable);
            HttpServletRequest request = ctx.getRequest();
//            request.setAttribute("jakarta.servlet.error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            request.setAttribute("jakarta.servlet.error.status_code", ctx.getResponseStatusCode());

//            log.warn("Error during filtering", throwable);
            request.setAttribute("jakarta.servlet.error.exception", throwable);

            if (StringUtils.hasText(throwable.getMessage())) {
                request.setAttribute("jakarta.servlet.error.message", throwable.getMessage());
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    this.errorPath);
            if (dispatcher != null) {
                ctx.set(SEND_ERROR_FILTER_RAN, true);
                if (!ctx.getResponse().isCommitted()) {
                    ctx.setResponseStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    dispatcher.forward(request, ctx.getResponse());
                }
            }
        } catch (Exception ex) {
            ReflectionUtils.rethrowRuntimeException(ex);
        }
        return null;
    }

    private Throwable getRealCause(Throwable throwable) {
        Throwable cause = throwable.getCause();
        if (cause != null) {
            return getRealCause(cause);
        }
        return throwable;
    }
}
