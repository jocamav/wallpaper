package org.jocamav.wallpaper.service;

import java.util.Collection;

import org.jocamav.wallpaper.dto.Room;
import org.springframework.web.multipart.MultipartFile;

public interface RoomService {

	int getNeededWallpaperForRooms(MultipartFile file);
	
	int getNeededWallpaperForRooms(Collection<Room> rooms);
	
	int getNeededWallpaperForRoom(Room room);
}
