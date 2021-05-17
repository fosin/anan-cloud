package top.fosin.anan.auth.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import top.fosin.anan.auth.service.inter.AuthService;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.cloudresource.dto.AnanUserAllPermissionTreeDto;

/**
 * @author fosin
 * @date 2018.8.22
 */
@RestController
@ApiIgnore
@RequestMapping(UrlPrefixConstant.PERMISSION)
public class PermissionController {
    private final AuthService authService;

    public PermissionController(AuthService authService) {
        this.authService = authService;
    }

//    @RequestMapping(value = "/serviceCode/{serviceCode}", method = {RequestMethod.GET, RequestMethod.POST})
//    @ApiImplicitParam(name = "serviceCode", value = "服务标识",
//            required = true, dataTypeClass = String.class, paramType = "path")
//    @ApiOperation(value = "查询应用权限", notes = "根据服务标识查询其权限列表")
//    public ResponseEntity<List<AnanPermissionRetrieveDto>> findByServiceCode(@PathVariable("serviceCode") String serviceCode) {
//        return ResponseEntity.ok(permissionService.findByServiceCode(serviceCode));
//    }
//
//    @RequestMapping(value = PathConstant.PATH_DYNAMIC_LIST, method = {RequestMethod.GET, RequestMethod.POST})
//    @ApiOperation(value = "查询应用权限", notes = "查询所有权限列表")
//    public ResponseEntity<List<AnanPermissionRetrieveDto>> findAll() {
//        Collection<AnanPermissionEntity> all = permissionService.findAll();
//        List<AnanPermissionRetrieveDto> retrieveDtos = new ArrayList<>();
//        for (AnanPermissionEntity ananPermissionEntity : all) {
//            AnanPermissionRetrieveDto retrieveDto = new AnanPermissionRetrieveDto();
//            BeanUtils.copyProperties(ananPermissionEntity, retrieveDto);
//            retrieveDto.setId(ananPermissionEntity.getId());
//            retrieveDtos.add(retrieveDto);
//        }
//
//        return ResponseEntity.ok(retrieveDtos);
//    }

    @RequestMapping(value = "/user/tree/{userId}", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "查询用户权限树", notes = "查询用户权限树")
    @ApiImplicitParam(name = "userId", value = "用户的唯一ID",
            required = true, dataTypeClass = Long.class, paramType = "path")
    public ResponseEntity<AnanUserAllPermissionTreeDto> findTree(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(authService.findTreeByUserId(userId));
    }

}
