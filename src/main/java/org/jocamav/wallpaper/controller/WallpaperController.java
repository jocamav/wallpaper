package org.jocamav.wallpaper.controller;

import org.jocamav.wallpaper.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WallpaperController {
	
	@Autowired
	private RoomService roomService;
	
	@GetMapping("/")
    public String index() {
        return "uploadForm";
    }
	
	@PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {
		
		int neededWallpaper = roomService.getNeededWallpaperForRooms(file);

        redirectAttributes.addFlashAttribute("message",
                "You need " + neededWallpaper + " square feet of wallpaper!");


        return "redirect:/";
    }
	
}
