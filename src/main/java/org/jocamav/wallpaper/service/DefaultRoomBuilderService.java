package org.jocamav.wallpaper.service;

import org.jocamav.wallpaper.dto.Room;
import org.springframework.stereotype.Service;

@Service
public class DefaultRoomBuilderService implements RoomBuilderService{

	public Room parseRoom(String dimensions) {
		if(dimensions == null) {
			return new Room(0,0,0);
		}
		String[] allDimensions = dimensions.split("x");
		try {
			return new Room(Integer.parseInt(allDimensions[0]), Integer.parseInt(allDimensions[1]), Integer.parseInt(allDimensions[2]));
		}
		catch(Exception e) {
			return new Room(0,0,0);
		}
	}

}
