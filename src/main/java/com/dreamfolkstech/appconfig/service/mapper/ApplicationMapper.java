package com.dreamfolkstech.appconfig.service.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.dreamfolkstech.appconfig.domain.Application;
import com.dreamfolkstech.appconfig.service.dto.ApplicationDTO;

/**
 * Mapper for the entity {@link Application} and its DTO {@link ApplicationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ApplicationMapper extends EntityMapper<ApplicationDTO, Application> {


    @Mapping(target = "appProductServices", ignore = true)
    @Mapping(target = "removeAppProductService", ignore = true)
    Application toEntity(ApplicationDTO applicationDTO);

    default Application fromId(Long id) {
        if (id == null) {
            return null;
        }
        Application application = new Application();
        application.setId(id);
        return application;
    }
}
