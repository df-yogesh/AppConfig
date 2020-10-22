package com.dreamfolkstech.appconfig.service.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.dreamfolkstech.appconfig.domain.ProductOffering;
import com.dreamfolkstech.appconfig.service.dto.ProductOfferingDTO;

/**
 * Mapper for the entity {@link ProductOffering} and its DTO {@link ProductOfferingDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductOfferingMapper extends EntityMapper<ProductOfferingDTO, ProductOffering> {


    @Mapping(target = "productOfferingServices", ignore = true)
    @Mapping(target = "removeProductOfferingService", ignore = true)
    ProductOffering toEntity(ProductOfferingDTO productOfferingDTO);
    
    @Mapping(target = "productOfferingServices", ignore = true)
    ProductOfferingDTO toDto(ProductOffering productOfferingDTO);

    default ProductOffering fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProductOffering productOffering = new ProductOffering();
        productOffering.setId(id);
        return productOffering;
    }
}
