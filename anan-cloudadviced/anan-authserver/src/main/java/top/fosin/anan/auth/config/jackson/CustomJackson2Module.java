package top.fosin.anan.auth.config.jackson;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import top.fosin.anan.cloudresource.entity.res.UserAuthDTO;

public class CustomJackson2Module extends SimpleModule {
    private static final String NAME = CustomJackson2Module.class.getName();
    private static final Version VERSION = new Version(1, 0, 0, (String) null, (String) null, (String) null);

    public CustomJackson2Module() {
        super(NAME, VERSION);
    }

    public void setupModule(Module.SetupContext context) {
        SecurityJackson2Modules.enableDefaultTyping(context.getOwner());
        context.setMixInAnnotations(UserAuthDTO.class, UserAuthDTOMixin.class);
    }
}
