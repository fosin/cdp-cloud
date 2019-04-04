package com.github.fosin.cdp.platformapi.parameter;

import com.github.fosin.cdp.platformapi.entity.CdpParameterEntity;
import com.github.fosin.cdp.platformapi.entity.CdpUserEntity;
import com.github.fosin.cdp.platformapi.util.LoginUserUtil;
import com.github.fosin.cdp.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

/**
 * 用户参数
 *
 * @author fosin
 * @date 2018.8.6
 */
@Slf4j
public class UserParameterUtil extends AbstractParameterUtil {

    public static CdpParameterEntity setParameter(String name, String value, String description) {
        return setParameter(ParameterUtil.getScope(ParameterType.User), name, value, description);
    }

    public static CdpParameterEntity setParameter(String scope, String name, String value, String description) {
        return ParameterUtil.setParameter(ParameterType.User, scope, name, value, description);
    }


    public static String getParameter(String name) {
        return getParameter(ParameterUtil.getScope(ParameterType.User), name);
    }

    public static String getParameter(String scope, String name) {
        CdpParameterEntity parameter = ParameterUtil.getParameter(ParameterType.User, scope, name);
        String info = "没有从参数[" + "type:" + ParameterType.User + " scope:" + scope + " name:" + name + "]中查询到参数";
        log.debug(info);
        Assert.isTrue(parameter != null && parameter.getId() != null, info);
        return getValue(parameter);
    }

    public static String getOrCreateParameter(String name, String defaultValue, String description) {
        return getOrCreateParameter(ParameterUtil.getScope(ParameterType.User), name, defaultValue, description);
    }

    public static String getOrCreateParameter(String scope, String name, String defaultValue, String description) {
        return ParameterUtil.getOrCreateParameter(ParameterType.User, scope, name, defaultValue, description);
    }
}
