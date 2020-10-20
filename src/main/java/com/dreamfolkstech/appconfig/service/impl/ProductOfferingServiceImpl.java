package com.dreamfolkstech.appconfig.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dreamfolkstech.appconfig.domain.ProductOffering;
import com.dreamfolkstech.appconfig.repository.ProductOfferingRepository;
import com.dreamfolkstech.appconfig.service.ProductOfferingService;
import com.dreamfolkstech.appconfig.service.dto.ProductOfferingDTO;
import com.dreamfolkstech.appconfig.service.mapper.ProductOfferingMapper;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;

/**
 * Service Implementation for managing {@link ProductOffering}.
 */
@Service
@Transactional
public class ProductOfferingServiceImpl implements ProductOfferingService {

    private final Logger log = LoggerFactory.getLogger(ProductOfferingServiceImpl.class);

    private final ProductOfferingRepository productOfferingRepository;

    private final ProductOfferingMapper productOfferingMapper;

    public ProductOfferingServiceImpl(ProductOfferingRepository productOfferingRepository, ProductOfferingMapper productOfferingMapper) {
        this.productOfferingRepository = productOfferingRepository;
        this.productOfferingMapper = productOfferingMapper;
    }

    @Override
    public ProductOfferingDTO save(ProductOfferingDTO productOfferingDTO) {
        log.debug("Request to save ProductOffering : {}", productOfferingDTO);
        ProductOffering productOffering = productOfferingMapper.toEntity(productOfferingDTO);
        productOffering = productOfferingRepository.save(productOffering);
        return productOfferingMapper.toDto(productOffering);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductOfferingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProductOfferings");
        return productOfferingRepository.findAllByStatus(GenericStatus.ENABLED, pageable)
                .map(productOfferingMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ProductOfferingDTO> findOne(Long id) {
        log.debug("Request to get ProductOffering : {}", id);
        return productOfferingRepository.findById(id).filter(x -> !GenericStatus.DISABLED.equals(x.getStatus()))
                .map(productOfferingMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProductOffering : {}", id);
        productOfferingRepository.deleteById(id);
    }
}
