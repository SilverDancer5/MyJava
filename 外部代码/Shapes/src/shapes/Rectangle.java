package shapes;

import java.awt.Graphics;

public class Rectangle extends Shape {
	private int x;
	private int y;
	private int width;
	private int height;
	
	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public Rectangle(int height, int width) {
		this.height = height;
		this.width = width;
	}

	@Override
	public void draw(Graphics g) {
		g.drawRect(x, y, width, height);
	}

	public void printArea() {
		int res = height * width;
		System.out.println("Rectangel Area is:" + res);
	}
}
