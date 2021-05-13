package top.fosin.anan.cloudresource.dto;

import lombok.Data;

import java.io.Serializable;

/**
 *  用于保存客户端环境信息
 *
 * @author fosin
 * @date 2018.7.21
 */
@Data
public class AnanClient implements Serializable {
    private String ip;
    private String macAddress;
}
