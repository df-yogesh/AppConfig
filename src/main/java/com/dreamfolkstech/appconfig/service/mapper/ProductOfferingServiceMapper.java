package com.dreamfolkstech.appconfig.service.mapper;


import com.dreamfolkstech.appconfig.domain.*;
import com.dreamfolkstech.appconfig.service.dto.ProductOfferingServiceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProductOfferingService} and its DTO {@link ProductOfferingServiceDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProductOfferingMapper.class})
public interface ProductOfferingServiceMapper extends EntityMapper<ProductOfferingServiceDTO, ProductOfferingService> {

    @Mapping(source = "productOffering.id", target = "productOfferingId")
    @Mapping(source = "productOffering.name", target = "productOfferingName")
    ProductOfferingServiceDTO toDto(ProductOfferingService productOfferingService);

    @Mapping(source = "productOfferingId", target = "productOffering")
    ProductOfferingService toEntity(ProductOfferingServiceDTO productOfferingServiceDTO);

    default ProductOfferingService fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProductOfferingService productOfferingService = new ProductOfferingService();
        productOfferingService.setId(id);
        return productOfferingService;
    }
}
