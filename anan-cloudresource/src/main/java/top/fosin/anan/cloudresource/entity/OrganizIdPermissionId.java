package top.fosin.anan.cloudresource.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 机构序号DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "机构序号DTO")
public class OrganizIdPermissionId<ID extends Serializable> extends PermissionId<ID> {
    private static final long serialVersionUID = -16743805718001139L;

    @Schema(description = "机构序号")
    private ID organizId;

}
