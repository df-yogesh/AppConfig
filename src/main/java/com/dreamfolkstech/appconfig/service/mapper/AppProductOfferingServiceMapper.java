package com.dreamfolkstech.appconfig.service.mapper;


import com.dreamfolkstech.appconfig.domain.*;
import com.dreamfolkstech.appconfig.service.dto.AppProductOfferingServiceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AppProductOfferingService} and its DTO {@link AppProductOfferingServiceDTO}.
 */
@Mapper(componentModel = "spring", uses = {ApplicationMapper.class})
public interface AppProductOfferingServiceMapper extends EntityMapper<AppProductOfferingServiceDTO, AppProductOfferingService> {

    @Mapping(source = "application.id", target = "applicationId")
    @Mapping(source = "application.name", target = "applicationName")
    AppProductOfferingServiceDTO toDto(AppProductOfferingService appProductOfferingService);

    @Mapping(source = "applicationId", target = "application")
    AppProductOfferingService toEntity(AppProductOfferingServiceDTO appProductOfferingServiceDTO);

    default AppProductOfferingService fromId(Long id) {
        if (id == null) {
            return null;
        }
        AppProductOfferingService appProductOfferingService = new AppProductOfferingService();
        appProductOfferingService.setId(id);
        return appProductOfferingService;
    }
}
