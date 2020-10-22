package com.dreamfolkstech.appconfig.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dreamfolkstech.appconfig.domain.ProductOffering;
import com.dreamfolkstech.appconfig.repository.ProductOfferingRepository;
import com.dreamfolkstech.appconfig.service.ProductOfferingService;
import com.dreamfolkstech.appconfig.service.dto.ProductOfferingDTO;
import com.dreamfolkstech.appconfig.service.mapper.ProductOfferingMapper;
import com.dreamfolkstech.appconfig.service.mapper.ProductOfferingServiceMapper;
import com.dreamfolkstech.appconfig.service.util.UtilityFunctions;
import com.dreamfolkstech.appconfig.web.rest.errors.ErrorConstants;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;
import com.dreamfolkstech.common.errors.ExternalBaseResponse;

/**
 * Service Implementation for managing {@link ProductOffering}.
 */
@Service
@Transactional
public class ProductOfferingServiceImpl implements ProductOfferingService {

    private final Logger log = LoggerFactory.getLogger(ProductOfferingServiceImpl.class);

    private final ProductOfferingRepository productOfferingRepository;
    
    private final ProductOfferingMapper productOfferingMapper;
    
    @Autowired
    private ProductOfferingServiceMapper productOfferingServiceMapper;

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
    
    /**fetch all product offering for app code on basis of enabled product offering services 
     *@param code
     */
    @Override
   	public ExternalBaseResponse findAllByAppCode(String code) {
   		log.debug("Request to get all AppProductServices by product code");
		List<com.dreamfolkstech.appconfig.domain.ProductOfferingService> productOfferingList = productOfferingRepository
				.findAllByProductCode(code);
		Map<ProductOffering, List<com.dreamfolkstech.appconfig.domain.ProductOfferingService>> map = productOfferingList
				.parallelStream().collect(Collectors.groupingBy(com.dreamfolkstech.appconfig.domain.ProductOfferingService::getProductOffering));
		List<ProductOfferingDTO> results = new ArrayList<>();
		map.entrySet().forEach(x -> {
			ProductOfferingDTO dto = productOfferingMapper.toDto(x.getKey());
			x.getValue().forEach(y -> dto.getProductOfferingServices().add(productOfferingServiceMapper.toDto(y)));
			results.add(dto);
		});
   		return UtilityFunctions.getBaseExternalResponse(ErrorConstants.SUCCESS,results);

   	}
}
