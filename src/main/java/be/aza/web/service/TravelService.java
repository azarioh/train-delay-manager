package be.aza.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;

import be.aza.web.mappers.TravelMapper;
import be.aza.web.model.Travel;
import be.aza.web.repository.TravelRepository;

@Service
@Transactional(readOnly = true)
public class TravelService {
	
    @Autowired
    TravelRepository travelRepository;
    
    @Autowired
    TravelMapper travelMapper;
    
    @Transactional(readOnly = false)
    public JsonNode createTravel(JsonNode repTravel) {
        Travel travel = travelMapper.map(repTravel);
        travelRepository.save(travel);
        return travelMapper.map(travel);
    }
    
	public JsonNode getTravelByName(String name) {
        Travel travelJson = travelRepository.findByName(name);
        return (travelJson == null)? null : travelMapper.map(travelJson);
	}
    
	public JsonNode getTravels() {
        List<Travel> travelListJson = travelRepository.findAll();
        return (travelListJson == null)? null : travelMapper.map(travelListJson);
	}

	
}
