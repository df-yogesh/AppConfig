package com.dreamfolkstech.appconfig.service.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dreamfolkstech.appconfig.config.Constants;
import com.dreamfolkstech.appconfig.domain.AppConfig;
import com.dreamfolkstech.appconfig.repository.AppConfigRepository;
import com.dreamfolkstech.appconfig.service.AppConfigService;
import com.dreamfolkstech.appconfig.service.dto.AppConfigDTO;
import com.dreamfolkstech.appconfig.service.mapper.AppConfigMapper;
import com.dreamfolkstech.appconfig.service.util.UtilityFunctions;
import com.dreamfolkstech.appconfig.web.rest.errors.ErrorConstants;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;
import com.dreamfolkstech.common.errors.ExternalBaseResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		return appConfigRepository.findAllByStatus(GenericStatus.ENABLED, pageable).map(appConfigMapper::toDto);
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

	/**Fetch all App configs for app code and request time(if available)
	 *@param code Appcode
	 *@param requestTime Optional
	 *@param deviceType Optional
	 *@return Appconfig List with responseTime as last modified or created app config in db
	 */
	@Override
	public ExternalBaseResponse findAllByProductCode(String code, Optional<Long> requestTime, Optional<String> deviceInfo)
	{
		log.debug("Request to get all AppConfigs for code {}", code);
		List<AppConfig> appconfigList = new ArrayList<>();
		String deviceType = null;
		Map<String, String> deviceInfoMap = null;
		if (deviceInfo.isPresent() && !StringUtils.isEmpty(deviceInfo.get())) {
				try {
					deviceInfoMap = new ObjectMapper().readValue(deviceInfo.get(), Map.class);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
				deviceType = deviceInfoMap.get(Constants.DEVICE_TYPE);
			}

		if (!requestTime.isPresent() || requestTime.get() == 0) {
			appconfigList = appConfigRepository.fetchAllByCode(code, deviceType);
		} else {
			Instant instant = Instant.ofEpochMilli(requestTime.get());
			appconfigList = appConfigRepository.fetchAllByCodeAndTime(code, deviceType, instant);
		}
		Instant lastModifiedEntryTime = null;
		if (!appconfigList.isEmpty())
			lastModifiedEntryTime = getLastModifiedEntryTime(appconfigList);
		Map<String, Object> hm = new HashMap<>();
		hm.put(Constants.LAST_DB_UPDATED_TIME, lastModifiedEntryTime);
		hm.put(Constants.APP_CONFIGS_LIST, appConfigMapper.toDto(appconfigList));

		return UtilityFunctions.getBaseExternalResponse(ErrorConstants.SUCCESS, hm);

	}

	/**Get last updated entry time
	 * @param appconfigList
	 * @return
	 */
	private Instant getLastModifiedEntryTime(List<AppConfig> appconfigList) {
		log.info("get Last updated Entry Time");
		return appconfigList.stream().
				map(x-> {
					Instant lastModifiedtime = x.getLastModifiedDate();
					Instant lastcreatedtime = x.getCreatedDate();
					if(lastModifiedtime.compareTo(lastcreatedtime)<0) 
						return lastcreatedtime;
						else
							return lastModifiedtime;
					}).max(Instant::compareTo).get();
	}
}
