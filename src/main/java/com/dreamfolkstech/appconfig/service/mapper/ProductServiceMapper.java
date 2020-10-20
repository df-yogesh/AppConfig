package com.dreamfolkstech.appconfig.service.mapper;


import com.dreamfolkstech.appconfig.domain.*;
import com.dreamfolkstech.appconfig.service.dto.ProductServiceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProductService} and its DTO {@link ProductServiceDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProductOfferingMapper.class})
public interface ProductServiceMapper extends EntityMapper<ProductServiceDTO, ProductService> {

    @Mapping(source = "productOffering.id", target = "productOfferingId")
    @Mapping(source = "productOffering.name", target = "productOfferingName")
    ProductServiceDTO toDto(ProductService productService);

    @Mapping(source = "productOfferingId", target = "productOffering")
    ProductService toEntity(ProductServiceDTO productServiceDTO);

    default ProductService fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProductService productService = new ProductService();
        productService.setId(id);
        return productService;
    }
}
