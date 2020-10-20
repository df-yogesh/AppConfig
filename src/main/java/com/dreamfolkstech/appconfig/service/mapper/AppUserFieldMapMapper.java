package com.dreamfolkstech.appconfig.service.mapper;


import com.dreamfolkstech.appconfig.domain.*;
import com.dreamfolkstech.appconfig.service.dto.AppUserFieldMapDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AppUserFieldMap} and its DTO {@link AppUserFieldMapDTO}.
 */
@Mapper(componentModel = "spring", uses = {MasterUserFieldMapper.class})
public interface AppUserFieldMapMapper extends EntityMapper<AppUserFieldMapDTO, AppUserFieldMap> {

    @Mapping(source = "masterUserField.id", target = "masterUserFieldId")
    @Mapping(source = "masterUserField.name", target = "masterUserFieldName")
    AppUserFieldMapDTO toDto(AppUserFieldMap appUserFieldMap);

    @Mapping(source = "masterUserFieldId", target = "masterUserField")
    AppUserFieldMap toEntity(AppUserFieldMapDTO appUserFieldMapDTO);

    default AppUserFieldMap fromId(Long id) {
        if (id == null) {
            return null;
        }
        AppUserFieldMap appUserFieldMap = new AppUserFieldMap();
        appUserFieldMap.setId(id);
        return appUserFieldMap;
    }
}
