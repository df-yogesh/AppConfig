package com.dreamfolkstech.appconfig.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dreamfolkstech.appconfig.domain.ProductOfferingService;
import com.dreamfolkstech.appconfig.repository.ProductOfferingServiceRepository;
import com.dreamfolkstech.appconfig.service.ProductOfferingServiceService;
import com.dreamfolkstech.appconfig.service.dto.ProductOfferingServiceDTO;
import com.dreamfolkstech.appconfig.service.mapper.ProductOfferingServiceMapper;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;

/**
 * Service Implementation for managing {@link ProductOfferingService}.
 */
@Service
@Transactional
public class ProductOfferingServiceServiceImpl implements ProductOfferingServiceService {

    private final Logger log = LoggerFactory.getLogger(ProductOfferingServiceServiceImpl.class);

    private final ProductOfferingServiceRepository productOfferingServiceRepository;

    private final ProductOfferingServiceMapper productOfferingServiceMapper;

    public ProductOfferingServiceServiceImpl(ProductOfferingServiceRepository productOfferingServiceRepository, ProductOfferingServiceMapper productOfferingServiceMapper) {
        this.productOfferingServiceRepository = productOfferingServiceRepository;
        this.productOfferingServiceMapper = productOfferingServiceMapper;
    }

    @Override
    public ProductOfferingServiceDTO save(ProductOfferingServiceDTO productOfferingServiceDTO) {
        log.debug("Request to save ProductService : {}", productOfferingServiceDTO);
        ProductOfferingService productOfferingService = productOfferingServiceMapper.toEntity(productOfferingServiceDTO);
        productOfferingService = productOfferingServiceRepository.save(productOfferingService);
        return productOfferingServiceMapper.toDto(productOfferingService);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductOfferingServiceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProductServices");
        return productOfferingServiceRepository.findAllByStatus(GenericStatus.ENABLED, pageable)
                .map(productOfferingServiceMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ProductOfferingServiceDTO> findOne(Long id) {
        log.debug("Request to get ProductService : {}", id);
        return productOfferingServiceRepository.findById(id).filter(x -> !GenericStatus.DISABLED.equals(x.getStatus()))
                .map(productOfferingServiceMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProductService : {}", id);
        productOfferingServiceRepository.deleteById(id);
    }
    
}
