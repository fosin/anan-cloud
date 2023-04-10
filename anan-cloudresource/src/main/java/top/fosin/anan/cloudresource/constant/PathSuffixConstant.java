package top.fosin.anan.cloudresource.constant;

/**
 * @author fosin
 * @date 2022/9/1
 * @since 3.0.0
 */
public class PathSuffixConstant {

    public static final String USER_ID = "/{" + FieldConstant.USER_ID + "}";

    public static final String ROLE_ID = "/{" + FieldConstant.ROLE_ID + "}";

    public static final String ORGANIZ_ID = "/{" + FieldConstant.ORGANIZ_ID + "}";

    public static final String USER_CODE = "/" + FieldConstant.USER_CODE + "/{" + FieldConstant.USER_CODE + "}";

    public static final String SERVICE_CODE = "/" + FieldConstant.SERVICE_CODE;
    public static final String SERVICE_CODE_VARIBALE = SERVICE_CODE + "/{" + FieldConstant.SERVICE_CODE + "}";
    public static final String SERVICE_CODES = SERVICE_CODE + "s";
}
