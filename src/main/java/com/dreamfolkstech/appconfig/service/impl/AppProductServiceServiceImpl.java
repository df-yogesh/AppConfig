package com.dreamfolkstech.appconfig.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dreamfolkstech.appconfig.domain.AppProductService;
import com.dreamfolkstech.appconfig.repository.AppProductServiceRepository;
import com.dreamfolkstech.appconfig.service.AppProductServiceService;
import com.dreamfolkstech.appconfig.service.dto.AppProductServiceDTO;
import com.dreamfolkstech.appconfig.service.mapper.AppProductServiceMapper;
import com.dreamfolkstech.appconfig.service.util.UtilityFunctions;
import com.dreamfolkstech.appconfig.web.rest.errors.ErrorConstants;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;
import com.dreamfolkstech.common.errors.ExternalBaseResponse;

/**
 * Service Implementation for managing {@link AppProductService}.
 */
@Service
@Transactional
public class AppProductServiceServiceImpl implements AppProductServiceService {

	private final Logger log = LoggerFactory.getLogger(AppProductServiceServiceImpl.class);

	private final AppProductServiceRepository appProductServiceRepository;

	private final AppProductServiceMapper appProductServiceMapper;

	public AppProductServiceServiceImpl(AppProductServiceRepository appProductServiceRepository,
			AppProductServiceMapper appProductServiceMapper) {
		this.appProductServiceRepository = appProductServiceRepository;
		this.appProductServiceMapper = appProductServiceMapper;
	}

	@Override
	public AppProductServiceDTO save(AppProductServiceDTO appProductServiceDTO) {
		log.debug("Request to save AppProductService : {}", appProductServiceDTO);
		AppProductService appProductService = appProductServiceMapper.toEntity(appProductServiceDTO);
		appProductService = appProductServiceRepository.save(appProductService);
		return appProductServiceMapper.toDto(appProductService);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<AppProductServiceDTO> findAll(Pageable pageable) {
		log.debug("Request to get all AppProductServices");
		return appProductServiceRepository.findAllByStatus(GenericStatus.ENABLED, pageable)
				.map(appProductServiceMapper::toDto);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<AppProductServiceDTO> findOne(Long id) {
		log.debug("Request to get AppProductService : {}", id);
		return appProductServiceRepository.findById(id).filter(x -> !GenericStatus.DISABLED.equals(x.getStatus()))
				.map(appProductServiceMapper::toDto);
	}

	@Override
	public void delete(Long id) {
		log.debug("Request to delete AppProductService : {}", id);
		appProductServiceRepository.deleteById(id);
	}

	@Override
	public ExternalBaseResponse findAllByProductCode(String code) {
		log.debug("Request to get all AppProductServices by product code");
		return UtilityFunctions.getBaseExternalResponse(ErrorConstants.SUCCESS,
				appProductServiceRepository.findAllByProductCode(code));

	}
}
