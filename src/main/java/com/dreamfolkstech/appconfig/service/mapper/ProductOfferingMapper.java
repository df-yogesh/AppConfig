package com.dreamfolkstech.appconfig.service.mapper;


import com.dreamfolkstech.appconfig.domain.*;
import com.dreamfolkstech.appconfig.service.dto.ProductOfferingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProductOffering} and its DTO {@link ProductOfferingDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProductOfferingMapper extends EntityMapper<ProductOfferingDTO, ProductOffering> {


    @Mapping(target = "productServices", ignore = true)
    @Mapping(target = "removeProductService", ignore = true)
    ProductOffering toEntity(ProductOfferingDTO productOfferingDTO);

    default ProductOffering fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProductOffering productOffering = new ProductOffering();
        productOffering.setId(id);
        return productOffering;
    }
}
