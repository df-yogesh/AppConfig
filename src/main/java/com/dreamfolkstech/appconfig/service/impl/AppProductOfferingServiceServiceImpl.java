package com.dreamfolkstech.appconfig.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dreamfolkstech.appconfig.domain.AppProductOfferingService;
import com.dreamfolkstech.appconfig.repository.AppProductOfferingServiceRepository;
import com.dreamfolkstech.appconfig.service.AppProductOfferingServiceService;
import com.dreamfolkstech.appconfig.service.dto.AppProductOfferingServiceDTO;
import com.dreamfolkstech.appconfig.service.mapper.AppProductOfferingServiceMapper;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;

/**
 * Service Implementation for managing {@link AppProductOfferingService}.
 */
@Service
@Transactional
public class AppProductOfferingServiceServiceImpl implements AppProductOfferingServiceService {

	private final Logger log = LoggerFactory.getLogger(AppProductOfferingServiceServiceImpl.class);

	private final AppProductOfferingServiceRepository appProductOfferingServiceRepository;

	private final AppProductOfferingServiceMapper appProductOfferingServiceMapper;

	public AppProductOfferingServiceServiceImpl(AppProductOfferingServiceRepository appProductOfferingServiceRepository,
			AppProductOfferingServiceMapper appProductOfferingServiceMapper) {
		this.appProductOfferingServiceRepository = appProductOfferingServiceRepository;
		this.appProductOfferingServiceMapper = appProductOfferingServiceMapper;
	}

	@Override
	public AppProductOfferingServiceDTO save(AppProductOfferingServiceDTO appProductOfferingServiceDTO) {
		log.debug("Request to save AppProductService : {}", appProductOfferingServiceDTO);
		AppProductOfferingService appProductOfferingService = appProductOfferingServiceMapper.toEntity(appProductOfferingServiceDTO);
		appProductOfferingService = appProductOfferingServiceRepository.save(appProductOfferingService);
		return appProductOfferingServiceMapper.toDto(appProductOfferingService);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<AppProductOfferingServiceDTO> findAll(Pageable pageable) {
		log.debug("Request to get all AppProductServices");
		return appProductOfferingServiceRepository.findAllByStatus(GenericStatus.ENABLED, pageable)
				.map(appProductOfferingServiceMapper::toDto);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<AppProductOfferingServiceDTO> findOne(Long id) {
		log.debug("Request to get AppProductService : {}", id);
		return appProductOfferingServiceRepository.findById(id).filter(x -> !GenericStatus.DISABLED.equals(x.getStatus()))
				.map(appProductOfferingServiceMapper::toDto);
	}

	@Override
	public void delete(Long id) {
		log.debug("Request to delete AppProductService : {}", id);
		appProductOfferingServiceRepository.deleteById(id);
	}

}
