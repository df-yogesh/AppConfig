package com.dreamfolkstech.appconfig.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dreamfolkstech.appconfig.domain.AppConfig;
import com.dreamfolkstech.appconfig.repository.AppConfigRepository;
import com.dreamfolkstech.appconfig.service.AppConfigService;
import com.dreamfolkstech.appconfig.service.dto.AppConfigDTO;
import com.dreamfolkstech.appconfig.service.mapper.AppConfigMapper;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;

/**
 * Service Implementation for managing {@link AppConfig}.
 */
@Service
@Transactional
public class AppConfigServiceImpl implements AppConfigService {

    private final Logger log = LoggerFactory.getLogger(AppConfigServiceImpl.class);

    private final AppConfigRepository appConfigRepository;

    private final AppConfigMapper appConfigMapper;

    public AppConfigServiceImpl(AppConfigRepository appConfigRepository, AppConfigMapper appConfigMapper) {
        this.appConfigRepository = appConfigRepository;
        this.appConfigMapper = appConfigMapper;
    }

    @Override
    public AppConfigDTO save(AppConfigDTO appConfigDTO) {
        log.debug("Request to save AppConfig : {}", appConfigDTO);
        AppConfig appConfig = appConfigMapper.toEntity(appConfigDTO);
        appConfig = appConfigRepository.save(appConfig);
        return appConfigMapper.toDto(appConfig);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AppConfigDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AppConfigs");
        return appConfigRepository.findAllByStatus(GenericStatus.ENABLED, pageable)
                .map(appConfigMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<AppConfigDTO> findOne(Long id) {
        log.debug("Request to get AppConfig : {}", id);
        return appConfigRepository.findById(id).filter(x -> !GenericStatus.DISABLED.equals(x.getStatus()))
                .map(appConfigMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AppConfig : {}", id);
        appConfigRepository.deleteById(id);
    }
}
