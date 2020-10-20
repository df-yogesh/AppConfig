package com.dreamfolkstech.appconfig.service.mapper;


import com.dreamfolkstech.appconfig.domain.*;
import com.dreamfolkstech.appconfig.service.dto.MasterUserFieldDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MasterUserField} and its DTO {@link MasterUserFieldDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MasterUserFieldMapper extends EntityMapper<MasterUserFieldDTO, MasterUserField> {


    @Mapping(target = "appUserFieldMaps", ignore = true)
    @Mapping(target = "removeAppUserFieldMap", ignore = true)
    MasterUserField toEntity(MasterUserFieldDTO masterUserFieldDTO);

    default MasterUserField fromId(Long id) {
        if (id == null) {
            return null;
        }
        MasterUserField masterUserField = new MasterUserField();
        masterUserField.setId(id);
        return masterUserField;
    }
}
