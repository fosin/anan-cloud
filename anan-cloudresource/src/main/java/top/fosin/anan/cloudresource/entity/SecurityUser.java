package top.fosin.anan.cloudresource.entity;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.fosin.anan.cloudresource.entity.res.UserAuthDTO;
import top.fosin.anan.core.util.ClientUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author fosin
 * @date 2018.7.9
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public class SecurityUser extends User {
    private static final long serialVersionUID = -8649502953562240792L;
    @Getter
    private UserAuthDTO user;
    @Getter
    private Client client;

    /**
     * 该无参构造函数用于Map转Bean时使用
     */
    public SecurityUser() {
        super("dfgsdfgdsgr", "sdfgergergerg", new HashSet<>());
    }

    public SecurityUser(UserAuthDTO user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsercode(), user.getPassword(), user.getStatus() == 0, user.getExpireTime().after(new Date()), true, user.getStatus() != 9, authorities);
        this.user = user;
        this.user.setPassword(null);
        client = new Client();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            String clientIp = ClientUtil.getClientOneIp(request);
            client.setIp(clientIp);
//        client.setMacAddress(ClientUtil.getMacAddress(clientIp));
        }
    }

    public SecurityUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Set<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SecurityUser) {
            return this.getUsername().equals(((SecurityUser) obj).getUsername());
        }
        return false;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " [" +
                "Username=" + this.getUsername() + ", " +
                "Password=[PROTECTED], " +
                "Enabled=" + this.isEnabled() + ", " +
                "AccountNonExpired=" + this.isAccountNonExpired() + ", " +
                "credentialsNonExpired=" + this.isCredentialsNonExpired() + ", " +
                "AccountNonLocked=" + this.isAccountNonLocked() + ", " +
                "Granted Authorities=" + this.getAuthorities() + ", " +
                "UserAuthDTO=" + this.getUser() + ", " +
                "Client=" + this.getClient() + "]";
    }
}
