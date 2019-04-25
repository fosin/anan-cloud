package com.github.fosin.anan.platformapi.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Description: 用于保存客户端环境信息
 *
 * @author fosin
 * @date 2018.7.21
 */
@Data
public class Client implements Serializable {
    private String ip;
    private String macAddress;
}
