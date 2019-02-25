package org.jocamav.wallpaper.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;

import org.jocamav.wallpaper.dto.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DefaultRoomService implements RoomService{

	private static final Logger log = LoggerFactory.getLogger(DefaultRoomService.class);
	
	@Autowired
	private RoomBuilderService roomBuilderService;

	public int getNeededWallpaperForRooms(MultipartFile file) {
        String dimensions;
        int neededWallpaper = 0;
        try {
	        InputStream is = file.getInputStream();
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));
	        while ((dimensions = br.readLine()) != null) {
	             log.info(String.format("Room: %s", dimensions));
	             Room room = roomBuilderService.parseRoom(dimensions);
	             neededWallpaper += getNeededWallpaperForRoom(room);
	             log.info(String.format("\tWe need %d square feet of wallpaper (so far...)", neededWallpaper));
	        }
        }
        catch(Exception e) {
        	log.error("Error reading file", e);
            return 0;
        }
        return neededWallpaper;
	}
	
	public int getNeededWallpaperForRooms(Collection<Room> rooms) {
		int neededWallpaper = 0;
		for(Room room: rooms) {
			neededWallpaper += getNeededWallpaperForRoom(room);
		}
		return neededWallpaper;
	}
	
	public int getNeededWallpaperForRoom(Room room) {
		if(anyDimensionIsNegativeOrZero(room)) {
			return 0;
		}
		return getWallpaperForRoom(room) + getExtraWallpaperForRoom(room);
	}
	
	private boolean anyDimensionIsNegativeOrZero(Room room) {
		return room.getHeight() <= 0 || room.getLenght() <= 0 || room.getWidth() <= 0;
	}
	
	private int getWallpaperForRoom(Room room) {
		return (2*getFloorSurface(room)) + (2*getWidthWallSurface(room)) + (2*getLengthWallSurface(room));
	}
	
	private int getExtraWallpaperForRoom(Room room) {
		int[] surfaces = getAllSurfaces(room);
		return Arrays.stream(surfaces)
	      .min()
	      .getAsInt();
	}
	
	private int[] getAllSurfaces(Room room) {
		int floorSurface = getFloorSurface(room);
		int widthWallSurface = getWidthWallSurface(room);
		int heightWallSurface = getLengthWallSurface(room);
		return new int[] {floorSurface, widthWallSurface, heightWallSurface};
	}
	
	public int getFloorSurface(Room room) {
		return getSurface(room.getLenght(), room.getWidth());
	}
	
	public int getWidthWallSurface(Room room) {
		return getSurface(room.getWidth(), room.getHeight());
	}
	
	public int getLengthWallSurface(Room room) {
		return getSurface(room.getHeight(), room.getLenght());
	}
	
	private int getSurface(int length, int width) {
		return length * width;
	}


}
