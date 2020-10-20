package com.dreamfolkstech.appconfig.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dreamfolkstech.appconfig.domain.Partner;
import com.dreamfolkstech.appconfig.repository.PartnerRepository;
import com.dreamfolkstech.appconfig.service.PartnerService;
import com.dreamfolkstech.appconfig.service.dto.PartnerDTO;
import com.dreamfolkstech.appconfig.service.mapper.PartnerMapper;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;

/**
 * Service Implementation for managing {@link Partner}.
 */
@Service
@Transactional
public class PartnerServiceImpl implements PartnerService {

    private final Logger log = LoggerFactory.getLogger(PartnerServiceImpl.class);

    private final PartnerRepository partnerRepository;

    private final PartnerMapper partnerMapper;

    public PartnerServiceImpl(PartnerRepository partnerRepository, PartnerMapper partnerMapper) {
        this.partnerRepository = partnerRepository;
        this.partnerMapper = partnerMapper;
    }

    @Override
    public PartnerDTO save(PartnerDTO partnerDTO) {
        log.debug("Request to save Partner : {}", partnerDTO);
        Partner partner = partnerMapper.toEntity(partnerDTO);
        partner = partnerRepository.save(partner);
        return partnerMapper.toDto(partner);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PartnerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Partners");
        return partnerRepository.findAllByStatus(GenericStatus.ENABLED, pageable)
                .map(partnerMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PartnerDTO> findOne(Long id) {
        log.debug("Request to get Partner : {}", id);
        return partnerRepository.findById(id).filter(x -> !GenericStatus.DISABLED.equals(x.getStatus()))
                .map(partnerMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Partner : {}", id);
        partnerRepository.deleteById(id);
    }
}
