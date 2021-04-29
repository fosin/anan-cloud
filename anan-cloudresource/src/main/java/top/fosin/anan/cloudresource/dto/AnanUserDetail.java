package top.fosin.anan.cloudresource.dto;

import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.fosin.anan.core.util.ClientUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

/**
 * Description:
 *
 * @author fosin
 * @date 2018.7.9
 */
public class AnanUserDetail extends User {
    @Getter
    private AnanUserDto user;
    @Getter
    private AnanClient ananClient;

    //该无参构造函数用于Map转Bean时使用
    public AnanUserDetail() {
        super("dfgsdfgdsgr", "sdfgergergerg", new HashSet<>());
    }

    public AnanUserDetail(AnanUserDto user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsercode(), user.getPassword(), user.getStatus() == 0, user.getExpireTime().after(new Date()), true, user.getStatus() != 9, authorities);
        this.user = new AnanUserDto();
        BeanUtils.copyProperties(user, this.user);
        this.user.setPassword(null);
        ananClient = new AnanClient();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            String clientIp = ClientUtil.getClientOneIp(request);
            ananClient.setIp(clientIp);
//        client.setMacAddress(ClientUtil.getMacAddress(clientIp));
        }
    }

    @Override
    public String toString() {
        String lineSeparator = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(":").append(lineSeparator);
        sb.append("Username: ").append(this.getUsername()).append(";").append(lineSeparator);
        sb.append("Password: [PROTECTED]; ").append(lineSeparator);
        sb.append("Enabled: ").append(this.getPassword()).append("; ").append(lineSeparator);
        sb.append("user: ").append(this.getUser()).append("; ").append(lineSeparator);
        Collection<GrantedAuthority> authorities = this.getAuthorities();
        if (!authorities.isEmpty()) {
            sb.append("Granted Authorities: ");

            boolean first = true;
            for (GrantedAuthority auth : authorities) {
                if (!first) {
                    sb.append(",");
                }
                first = false;

                sb.append(auth);
            }
            sb.append(lineSeparator);
        } else {
            sb.append("Not granted any authorities");
        }

        return sb.toString();
    }
}
