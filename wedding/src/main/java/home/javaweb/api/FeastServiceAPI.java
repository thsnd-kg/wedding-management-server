package home.javaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import home.javaweb.dto.FeastServiceDTO;
import home.javaweb.dto.ServiceSupport;
import home.javaweb.entity.FeastService;
import home.javaweb.entity.Service;
import home.javaweb.repository.FeastServiceRepository;
import home.javaweb.service.IFeastServiceService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class FeastServiceAPI {

	@Autowired
	private IFeastServiceService _service;
	

//	@GetMapping("/feast-service")
//	public List<FeastService> get() {
//		return _repo.findAll();
//	}

//	@GetMapping("/feast-service/{id}")
//	public List<Map<Service,Long>> findByFeastId(@PathVariable(value = "id") Long feastId){
//		return _repo.findByFeastId(feastId);
//	}
//	

	@GetMapping("/feast/{feastid}/service")
	public FeastServiceDTO findByFeastId(@PathVariable(value = "feastid") Long feastId) {

		return _service.findByFeastId(feastId);
	}
	
	@GetMapping("/feast/{feastid}/service/{serviceid}")
	public Object findByFeastIdServiceId(
			@PathVariable(value = "feastid") Long feastId,
			@PathVariable(value = "serviceid") Long serviceId
	) {

		return _service.findByFeastIdAndServiceId(feastId,serviceId);
	}
	
	@PostMapping("/feast/service")
	public FeastService addService(@RequestBody FeastServiceDTO dto) {
		return _service.save(dto);
	}
	
	@PutMapping("/feast/service")
	public FeastService updateService(@RequestBody FeastServiceDTO dto) {
		return _service.save(dto);
	}
	
	@DeleteMapping("/feast/{feastid}/service/{serviceid}")
	public void deleteFeastService(
			@PathVariable(value = "feastid") Long feastId,
			@PathVariable(value = "serviceid") Long serviceId
	) {
		_service.deleteById(feastId, serviceId);
	}

}
