package com.github.fosin.cdp.auth.controller;

import com.github.fosin.cdp.core.exception.CdpControllerException;
import com.github.fosin.cdp.core.exception.CdpServiceException;
import com.github.fosin.cdp.mvc.constant.MvcConstant;
import com.github.fosin.cdp.platformapi.entity.OauthClientDetailsEntity;
import com.github.fosin.cdp.platformapi.service.inter.IOauthClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Description 授权客户端控制器
 *
 * @author fosin
 */
//@RestController
//@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientRegistrationService clientRegistrationService;
    @Autowired
    private IOauthClientService oauthClientService;

    
    @PostMapping
    public ResponseEntity<String> post(@RequestBody @Validated BaseClientDetails entity, BindingResult bindingResult, HttpServletRequest request,
                                       HttpServletResponse response) throws CdpControllerException, CdpServiceException {
        clientRegistrationService.addClientDetails(entity);
        return ResponseEntity.ok("");
    }

    
    @DeleteMapping(MvcConstant.PATH_ID)
    public ResponseEntity<String> delete(@PathVariable("id") String id) throws CdpControllerException, CdpServiceException {
        clientRegistrationService.removeClientDetails(id);
        return ResponseEntity.ok("");
    }

    
    @PutMapping
    public ResponseEntity<String> put(@RequestBody @Validated BaseClientDetails entity, BindingResult bindingResult, HttpServletRequest request,
                                      HttpServletResponse response) throws CdpControllerException, CdpServiceException {
        clientRegistrationService.updateClientDetails(entity);
        return ResponseEntity.ok("");
    }

    
    @GetMapping(MvcConstant.PATH_ID)
    public ResponseEntity<OauthClientDetailsEntity> get(@PathVariable("id") String id) throws CdpControllerException {
        return ResponseEntity.ok(oauthClientService.findOne(id));
    }


//    @RequestMapping(MvcConstant.PATH_PAGE_LIST)
//    public ResponseEntity<Result> pageList(@RequestBody PageModule pageModule) throws CdpControllerException {
//        PageRequest pageRequest = new PageRequest(pageModule.getPageNumber() - 1, pageModule.getPageSize(), Sort.Direction.fromString(pageModule.getSortOrder()), pageModule.getSortName());
//        //分页查找
//        Page<OauthClientDetailsEntity> page;
//        page = oauthClientService.findAll(pageModule.getSearchText(), pageRequest);
//
//        return ResponseEntity.ok(ResultUtils.success(page.getTotalElements(), page.getContent()));
//    }

    @RequestMapping(MvcConstant.PATH_LIST)
    public ResponseEntity<List<ClientDetails>> list() throws CdpControllerException {
        return ResponseEntity.ok(clientRegistrationService.listClientDetails());
    }
}
