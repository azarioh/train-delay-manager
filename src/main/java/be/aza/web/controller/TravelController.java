package be.aza.web.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;

import be.aza.web.service.TravelService;

@RestController
@RequestMapping("/train-delay/v1")
public class TravelController {

    @Autowired
    TravelService travelService;

    @RequestMapping(
    		method 		= RequestMethod.GET, 
    		value 		= "/travels/{name}", 
    		produces 	= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<JsonNode> getById(@PathVariable final String name) {
        JsonNode travelNode = travelService.getTravelByName(name);
        if(travelNode != null)
            return ResponseEntity.ok(travelNode);
        else
            return ResponseEntity.notFound().build();
    }
    

    @RequestMapping(
    		method 		= RequestMethod.GET, 
    		value 		= "/travels", 
    		produces 	= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<JsonNode> getAll() {
        JsonNode travelNode = travelService.getTravels();
        if(travelNode != null)
            return ResponseEntity.ok(travelNode);
        else
            return ResponseEntity.notFound().build();
    }
    
    @RequestMapping(
    		method 		= RequestMethod.POST, 
    		value 		= "/travels", 
    		consumes 	= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> createTravel (@RequestBody JsonNode travelNode)
    {
    	// TODO : add schema validation
    	JsonNode createdCase;
		
		createdCase = travelService.createTravel(travelNode);
	
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{name}").buildAndExpand(createdCase.get("data").get("name").textValue()).toUri();
        return ResponseEntity.created(location).body(travelNode);

    }

 
}
