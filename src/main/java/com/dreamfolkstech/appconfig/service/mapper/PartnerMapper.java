package com.dreamfolkstech.appconfig.service.mapper;


import com.dreamfolkstech.appconfig.domain.*;
import com.dreamfolkstech.appconfig.service.dto.PartnerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Partner} and its DTO {@link PartnerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PartnerMapper extends EntityMapper<PartnerDTO, Partner> {



    default Partner fromId(Long id) {
        if (id == null) {
            return null;
        }
        Partner partner = new Partner();
        partner.setId(id);
        return partner;
    }
}
