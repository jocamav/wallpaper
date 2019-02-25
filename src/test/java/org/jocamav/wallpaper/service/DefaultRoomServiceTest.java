package org.jocamav.wallpaper.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.jocamav.wallpaper.dto.Room;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DefaultRoomService.class})
public class DefaultRoomServiceTest {
	
	@Autowired
	private RoomService roomService;

	@MockBean
	private RoomBuilderService roomBuilderService;

	@Before
	public void setUp() {
		Mockito.when(roomBuilderService.parseRoom("1x2x3")).thenReturn(new Room(1, 2, 3));
		Mockito.when(roomBuilderService.parseRoom("1x1x5")).thenReturn(new Room(1, 1, 5));
	}
	
	@Test
	public void getNeededWallpaper_forRoomWithNegativeLength() {
		int neededWallapaper = roomService.getNeededWallpaperForRoom(new Room(-1, 1, 1));
		assertThat(neededWallapaper).isEqualTo(0);
	}
	
	@Test
	public void getNeededWallpaper_forRoomWithNegativeWidth() {
		int neededWallapaper = roomService.getNeededWallpaperForRoom(new Room(1, -1, 1));
		assertThat(neededWallapaper).isEqualTo(0);
	}

	@Test
	public void getNeededWallpaper_forRoomWithNegativeHeight() {
		int neededWallapaper = roomService.getNeededWallpaperForRoom(new Room(1, -1, 1));
		assertThat(neededWallapaper).isEqualTo(0);
	}
	
	@Test
	public void getNeededWallpaper_forRoomWithNoDimensions() {
		int neededWallapaper = roomService.getNeededWallpaperForRoom(new Room(0, 0, 0));
		assertThat(neededWallapaper).isEqualTo(0);
	}
	
	@Test
	public void getNeededWallpaper_forRectangularRoom() {
		int neededWallapaper = roomService.getNeededWallpaperForRoom(new Room(1, 2, 3));
		assertThat(neededWallapaper).isEqualTo(24);
	}
	
	@Test
	public void getNeededWallpaper_forSquareRoom() {
		int neededWallapaper = roomService.getNeededWallpaperForRoom(new Room(1, 1, 5));
		assertThat(neededWallapaper).isEqualTo(23);
	}
	
	@Test
	public void getNeededWallpaper_forNoRooms() {
		Set<Room> rooms = new HashSet<Room>();
		int neededWallapaper = roomService.getNeededWallpaperForRooms(rooms);
		assertThat(neededWallapaper).isEqualTo(0);
	}
	
	@Test
	public void getNeededWallpaper_forTwoRooms() {
		Set<Room> rooms = new HashSet<Room>();
		rooms.add(new Room(1, 2, 3));
		rooms.add(new Room(1, 1, 5));
		int neededWallapaper = roomService.getNeededWallpaperForRooms(rooms);
		assertThat(neededWallapaper).isEqualTo(47);
	}

	@Test
	public void getNeededWallpaper_forNoRoomsFromFile() {
		String textFile = "";
		MultipartFile multipartFile = new MockMultipartFile("file.txt", textFile.getBytes());
		int neededWallapaper = roomService.getNeededWallpaperForRooms(multipartFile);
		assertThat(neededWallapaper).isEqualTo(0);
	}

	@Test
	public void getNeededWallpaper_forTwoRoomsFromFile() {
		String textFile = "1x2x3\n"
				+ "1x1x5\n";
		MultipartFile multipartFile = new MockMultipartFile("file.txt", textFile.getBytes());
		int neededWallapaper = roomService.getNeededWallpaperForRooms(multipartFile);
		assertThat(neededWallapaper).isEqualTo(47);
	}
	
	
}
