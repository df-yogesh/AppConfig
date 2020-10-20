package com.dreamfolkstech.appconfig.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dreamfolkstech.appconfig.domain.MasterUserField;
import com.dreamfolkstech.appconfig.repository.MasterUserFieldRepository;
import com.dreamfolkstech.appconfig.service.MasterUserFieldService;
import com.dreamfolkstech.appconfig.service.dto.MasterUserFieldDTO;
import com.dreamfolkstech.appconfig.service.mapper.MasterUserFieldMapper;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;

/**
 * Service Implementation for managing {@link MasterUserField}.
 */
@Service
@Transactional
public class MasterUserFieldServiceImpl implements MasterUserFieldService {

    private final Logger log = LoggerFactory.getLogger(MasterUserFieldServiceImpl.class);

    private final MasterUserFieldRepository masterUserFieldRepository;

    private final MasterUserFieldMapper masterUserFieldMapper;

    public MasterUserFieldServiceImpl(MasterUserFieldRepository masterUserFieldRepository, MasterUserFieldMapper masterUserFieldMapper) {
        this.masterUserFieldRepository = masterUserFieldRepository;
        this.masterUserFieldMapper = masterUserFieldMapper;
    }

    @Override
    public MasterUserFieldDTO save(MasterUserFieldDTO masterUserFieldDTO) {
        log.debug("Request to save MasterUserField : {}", masterUserFieldDTO);
        MasterUserField masterUserField = masterUserFieldMapper.toEntity(masterUserFieldDTO);
        masterUserField = masterUserFieldRepository.save(masterUserField);
        return masterUserFieldMapper.toDto(masterUserField);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MasterUserFieldDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MasterUserFields");
        return masterUserFieldRepository.findAllByStatus(GenericStatus.ENABLED, pageable)
                .map(masterUserFieldMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<MasterUserFieldDTO> findOne(Long id) {
        log.debug("Request to get MasterUserField : {}", id);
        return masterUserFieldRepository.findById(id).filter(x -> !GenericStatus.DISABLED.equals(x.getStatus()))
                .map(masterUserFieldMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete MasterUserField : {}", id);
        masterUserFieldRepository.deleteById(id);
    }
}
