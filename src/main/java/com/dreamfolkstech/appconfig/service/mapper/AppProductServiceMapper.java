package com.dreamfolkstech.appconfig.service.mapper;


import com.dreamfolkstech.appconfig.domain.*;
import com.dreamfolkstech.appconfig.service.dto.AppProductServiceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AppProductService} and its DTO {@link AppProductServiceDTO}.
 */
@Mapper(componentModel = "spring", uses = {ApplicationMapper.class})
public interface AppProductServiceMapper extends EntityMapper<AppProductServiceDTO, AppProductService> {

    @Mapping(source = "application.id", target = "applicationId")
    @Mapping(source = "application.name", target = "applicationName")
    AppProductServiceDTO toDto(AppProductService appProductService);

    @Mapping(source = "applicationId", target = "application")
    AppProductService toEntity(AppProductServiceDTO appProductServiceDTO);

    default AppProductService fromId(Long id) {
        if (id == null) {
            return null;
        }
        AppProductService appProductService = new AppProductService();
        appProductService.setId(id);
        return appProductService;
    }
}
