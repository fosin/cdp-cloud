package top.fosin.anan.platformapi.parameter;

import top.fosin.anan.platformapi.service.AnanUserDetailService;

/**
 * 机构参数
 * @author fosin
 * @date 2019/5/13
 */
public class OrganStrategy implements IParameterStrategy {
    private final AnanUserDetailService ananUserDetailService;

    public OrganStrategy(AnanUserDetailService ananUserDetailService) {
        this.ananUserDetailService = ananUserDetailService;
    }

    @Override
    public int getType() {
        return ParameterType.Organization.getTypeValue();
    }

    @Override
    public String getScope() {
        return ananUserDetailService.getAnanOrganizId() + "";
    }
}
