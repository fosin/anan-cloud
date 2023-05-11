package top.fosin.anan.cloudresource.converter.translate;

import org.springframework.core.annotation.AliasFor;
import top.fosin.anan.cloudresource.grpc.service.OrganizGrpcServiceImpl;
import top.fosin.anan.data.converter.translate.Translate2String;
import top.fosin.anan.data.converter.translate.service.StringTranslateService;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

/**
 * 机构序号翻译为机构名称
 *
 * @author fosin
 * @date 2023/4/28
 * @since 4.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {FIELD})
@Translate2String(service = OrganizGrpcServiceImpl.class, dicId = "")
public @interface OrganizIdTranslate {
    @AliasFor(annotation = Translate2String.class)
    Class<? extends StringTranslateService<?>> service() default OrganizGrpcServiceImpl.class;
}
