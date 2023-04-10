package top.fosin.anan.cloudresource.grpc.util;

/**
 * @author fosin
 * @date 2023/4/8
 * @since 3.0.0
 */
public class StringUtil {
    public static String getNonNullValue(String value) {
        return value == null ? "" : value;
    }
}
