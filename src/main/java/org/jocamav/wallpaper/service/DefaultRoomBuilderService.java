package org.jocamav.wallpaper.service;

import org.jocamav.wallpaper.dto.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DefaultRoomBuilderService implements RoomBuilderService{

	private static final Logger log = LoggerFactory.getLogger(DefaultRoomBuilderService.class);
	
	public Room parseRoom(String dimensions) {
		if(dimensions == null) {
			return new Room(0,0,0);
		}
		try {
			String[] allDimensions = getDimmensionsAsArray(dimensions);
			return new Room(Integer.parseInt(allDimensions[0]), Integer.parseInt(allDimensions[1]), Integer.parseInt(allDimensions[2]));
		}
		catch(Exception e) {
			log.warn(String.format("Can't parse <%s> as Room", dimensions));
			return new Room(0,0,0);
		}
	}
	
	private String[] getDimmensionsAsArray(String dimensions) {
		return dimensions.split("x");
	}

}
