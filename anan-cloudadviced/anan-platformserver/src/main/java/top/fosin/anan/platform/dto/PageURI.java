package top.fosin.anan.platform.dto;

import lombok.Data;
import org.springframework.http.HttpHeaders;

/**
 * Description:
 *
 * @author fosin
 * @date 2018.8.21
 */
@Data
public class PageURI {
    private String url;
    private HttpHeaders headers;

    public PageURI() {
    }

    public PageURI(String url, HttpHeaders headers) {
        this.url = url;
        this.headers = headers;
    }
}
