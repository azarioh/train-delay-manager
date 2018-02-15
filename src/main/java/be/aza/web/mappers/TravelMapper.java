package be.aza.web.mappers;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import be.aza.web.model.Travel;

@Component
public class TravelMapper {
	
    @Autowired
    TravelMapper travelMapper;
    
    public JsonNode map (List<Travel> cases){
        final JsonNodeFactory nodeFactory = JsonNodeFactory.instance;
        ArrayNode arrayNode = nodeFactory.arrayNode();

        for(Travel travel : cases){
            arrayNode.add(map(travel));
        }
        return arrayNode;
    }
    
    public JsonNode map(Travel travel) {
            final JsonNodeFactory nodeFactory = JsonNodeFactory.instance;
            
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode rootNode = nodeFactory.objectNode();
            
            JsonNode dataNode = null;
			
			try {
				dataNode = mapper.readTree(mapper.writeValueAsString(travel));
			} catch (IOException e) {
				// TODO throw a custom exception
				e.printStackTrace();
			}
			
            rootNode.set("data",dataNode);
            return rootNode;
    }
    
    public Travel map(JsonNode repCase) {
        ObjectMapper mapper = new ObjectMapper();
        Travel travel = null;
		
		try {
			travel = mapper.readValue( repCase.toString(), Travel.class);
		} catch ( IOException e) {
			// TODO throw a custom exception
			e.printStackTrace();
		}

        return travel;
    }
    
}
