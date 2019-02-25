package org.jocamav.wallpaper.dto;

public class Room {
	private int lenght;
	private int width;
	private int height;
	
	public Room(int lenght, int width, int height) {
		super();
		setLenght(lenght);
		setWidth(width);
		setHeight(height);
	}

	public int getLenght() {
		return lenght;
	}

	public void setLenght(int lenght) {
		this.lenght = lenght > 0 ? lenght : 0;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width > 0 ? width : 0;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height > 0 ? height : 0;
	}
	
	
	
}
