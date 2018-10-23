package com.github.fosin.cdp.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Description:
 *
 * @author fosin
 * @date 2018.8.22
 */
@Controller
@ApiIgnore
public class LoginController {
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }
}
