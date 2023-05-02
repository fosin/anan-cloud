package top.fosin.anan.cloudresource.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用于保存客户端环境信息
 *
 * @author fosin
 * @date 2018.7.21
 */
@Data
public class Client implements Serializable {
    private static final long serialVersionUID = 2267917446967549021L;
    private String ip;
    private String macAddress;
}
