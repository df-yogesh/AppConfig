package com.dreamfolkstech.appconfig.service.mapper;


import com.dreamfolkstech.appconfig.domain.*;
import com.dreamfolkstech.appconfig.service.dto.AppConfigDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AppConfig} and its DTO {@link AppConfigDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AppConfigMapper extends EntityMapper<AppConfigDTO, AppConfig> {



    default AppConfig fromId(Long id) {
        if (id == null) {
            return null;
        }
        AppConfig appConfig = new AppConfig();
        appConfig.setId(id);
        return appConfig;
    }
}
