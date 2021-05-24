package home.javaweb.service;

import home.javaweb.dto.FeastServiceDTO;
import home.javaweb.entity.FeastService;

public interface IFeastServiceService {
	FeastServiceDTO findByFeastId(Long feastId);
	FeastService findByFeastIdAndServiceId(Long feastId, Long serviceId);
	FeastService save(FeastServiceDTO dto);
}