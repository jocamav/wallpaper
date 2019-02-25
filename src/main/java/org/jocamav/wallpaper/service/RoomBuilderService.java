package org.jocamav.wallpaper.service;

import org.jocamav.wallpaper.dto.Room;

public interface RoomBuilderService {
	Room parseRoom(String dimensions);
}
