package com.dreamfolkstech.appconfig.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dreamfolkstech.appconfig.domain.ProductService;
import com.dreamfolkstech.appconfig.repository.ProductServiceRepository;
import com.dreamfolkstech.appconfig.service.ProductServiceService;
import com.dreamfolkstech.appconfig.service.dto.ProductServiceDTO;
import com.dreamfolkstech.appconfig.service.mapper.ProductServiceMapper;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;

/**
 * Service Implementation for managing {@link ProductService}.
 */
@Service
@Transactional
public class ProductServiceServiceImpl implements ProductServiceService {

    private final Logger log = LoggerFactory.getLogger(ProductServiceServiceImpl.class);

    private final ProductServiceRepository productServiceRepository;

    private final ProductServiceMapper productServiceMapper;

    public ProductServiceServiceImpl(ProductServiceRepository productServiceRepository, ProductServiceMapper productServiceMapper) {
        this.productServiceRepository = productServiceRepository;
        this.productServiceMapper = productServiceMapper;
    }

    @Override
    public ProductServiceDTO save(ProductServiceDTO productServiceDTO) {
        log.debug("Request to save ProductService : {}", productServiceDTO);
        ProductService productService = productServiceMapper.toEntity(productServiceDTO);
        productService = productServiceRepository.save(productService);
        return productServiceMapper.toDto(productService);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductServiceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProductServices");
        return productServiceRepository.findAllByStatus(GenericStatus.ENABLED, pageable)
                .map(productServiceMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ProductServiceDTO> findOne(Long id) {
        log.debug("Request to get ProductService : {}", id);
        return productServiceRepository.findById(id).filter(x -> !GenericStatus.DISABLED.equals(x.getStatus()))
                .map(productServiceMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProductService : {}", id);
        productServiceRepository.deleteById(id);
    }
}
