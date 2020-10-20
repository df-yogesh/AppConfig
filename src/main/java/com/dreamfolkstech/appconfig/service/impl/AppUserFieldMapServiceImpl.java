package com.dreamfolkstech.appconfig.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dreamfolkstech.appconfig.domain.AppUserFieldMap;
import com.dreamfolkstech.appconfig.repository.AppUserFieldMapRepository;
import com.dreamfolkstech.appconfig.service.AppUserFieldMapService;
import com.dreamfolkstech.appconfig.service.dto.AppUserFieldMapDTO;
import com.dreamfolkstech.appconfig.service.mapper.AppUserFieldMapMapper;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;

/**
 * Service Implementation for managing {@link AppUserFieldMap}.
 */
@Service
@Transactional
public class AppUserFieldMapServiceImpl implements AppUserFieldMapService {

    private final Logger log = LoggerFactory.getLogger(AppUserFieldMapServiceImpl.class);

    private final AppUserFieldMapRepository appUserFieldMapRepository;

    private final AppUserFieldMapMapper appUserFieldMapMapper;

    public AppUserFieldMapServiceImpl(AppUserFieldMapRepository appUserFieldMapRepository, AppUserFieldMapMapper appUserFieldMapMapper) {
        this.appUserFieldMapRepository = appUserFieldMapRepository;
        this.appUserFieldMapMapper = appUserFieldMapMapper;
    }

    @Override
    public AppUserFieldMapDTO save(AppUserFieldMapDTO appUserFieldMapDTO) {
        log.debug("Request to save AppUserFieldMap : {}", appUserFieldMapDTO);
        AppUserFieldMap appUserFieldMap = appUserFieldMapMapper.toEntity(appUserFieldMapDTO);
        appUserFieldMap = appUserFieldMapRepository.save(appUserFieldMap);
        return appUserFieldMapMapper.toDto(appUserFieldMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AppUserFieldMapDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AppUserFieldMaps");
        return appUserFieldMapRepository.findAllByStatus(GenericStatus.ENABLED, pageable)
                .map(appUserFieldMapMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<AppUserFieldMapDTO> findOne(Long id) {
        log.debug("Request to get AppUserFieldMap : {}", id);
        return appUserFieldMapRepository.findById(id).filter(x -> !GenericStatus.DISABLED.equals(x.getStatus()))
                .map(appUserFieldMapMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AppUserFieldMap : {}", id);
        appUserFieldMapRepository.deleteById(id);
    }
}
