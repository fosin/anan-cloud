package top.fosin.anan.cloudresource.converter.translate;

import org.springframework.core.annotation.AliasFor;
import top.fosin.anan.cloudresource.grpc.service.DicDetailGrpcServiceImpl;
import top.fosin.anan.data.converter.translate.Translate2String;
import top.fosin.anan.data.converter.translate.service.StringTranslateService;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

/**
 * 字典明旭序号翻译为名称
 *
 * @author fosin
 * @date 2023/4/28
 * @since 4.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {FIELD})
@Translate2String(service = DicDetailGrpcServiceImpl.class, dicId = "")
public @interface DicDetailIdTranslate {
    @AliasFor(annotation = Translate2String.class)
    Class<? extends StringTranslateService<?>> service() default DicDetailGrpcServiceImpl.class;

    @AliasFor(annotation = Translate2String.class)
    String dicId();
}
