package org.jocamav.wallpaper.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.jocamav.wallpaper.service.RoomService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@RunWith(SpringRunner.class)
@WebMvcTest(WallpaperController.class)
public class WallpaperControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private RoomService roomService;
	
	@Test
    public void shouldReturnIndexPage() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Calculate the wallpaper needed for all the rooms")));
    }
	
	@Test
    public void shouldUploadFileAndReturnWords() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt","text/plain", "1x2x3".getBytes());
        this.mockMvc.perform(multipart("/").file(multipartFile))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", "/"));

        then(this.roomService).should().getNeededWallpaperForRooms(multipartFile);
    }
}
