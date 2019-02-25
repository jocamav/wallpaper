package org.jocamav.wallpaper.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


import org.jocamav.wallpaper.dto.Room;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DefaultRoomBuilderService.class})
public class DefaultBuilderRoomServiceTest {
	
	@Autowired
	private RoomBuilderService roomBuilderService;
	
	@Test
	public void parseRoomForValidFormat() {
		Room room = roomBuilderService.parseRoom("1x2x3");
		assertThat(room.getLenght()).isEqualTo(1);
		assertThat(room.getWidth()).isEqualTo(2);
		assertThat(room.getHeight()).isEqualTo(3);
	}
	
	@Test
	public void parseRoomForNullString() {
		Room room = roomBuilderService.parseRoom(null);
		assertThat(room.getLenght()).isEqualTo(0);
		assertThat(room.getWidth()).isEqualTo(0);
		assertThat(room.getHeight()).isEqualTo(0);
	}
	
	@Test
	public void parseRoomForEmptyString() {
		Room room = roomBuilderService.parseRoom("");
		assertThat(room.getLenght()).isEqualTo(0);
		assertThat(room.getWidth()).isEqualTo(0);
		assertThat(room.getHeight()).isEqualTo(0);
	}
	
	@Test
	public void parseRoomForInvalidFormat() {
		Room room = roomBuilderService.parseRoom("mock");
		assertThat(room.getLenght()).isEqualTo(0);
		assertThat(room.getWidth()).isEqualTo(0);
		assertThat(room.getHeight()).isEqualTo(0);
	}
	

	@Test
	public void parseRoomForMissingDimessions() {
		Room room = roomBuilderService.parseRoom("1x3");
		assertThat(room.getLenght()).isEqualTo(0);
		assertThat(room.getWidth()).isEqualTo(0);
		assertThat(room.getHeight()).isEqualTo(0);
	}
	
}
