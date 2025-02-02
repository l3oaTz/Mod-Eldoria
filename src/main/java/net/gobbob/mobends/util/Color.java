package net.gobbob.mobends.util;

public class Color {
	
	public static final Color white = new Color(1,1,1,1);
	public static final Color red = new Color(1,0,0,1);
	public static final Color green = new Color(0,1,0,1);
	public static final Color blue = new Color(0,0,1,1);
	public static final Color black = new Color(0,0,0,1);
	public static final Color zero = new Color(0, 0, 0, 0);
	
	public float r = 0, g = 0, b = 0, a = 0;
	
	public Color(float r,float g,float b){
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = 1.0f;
	}
	
	public Color(float r,float g,float b,float a){
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	public int hex() {
		int valueA = (int) GUtil.clamp(this.a*256.0f, 0, 255);
		int valueR = (int) GUtil.clamp(this.r*256.0f, 0, 255);
		int valueG = (int) GUtil.clamp(this.g*256.0f, 0, 255);
		int valueB = (int) GUtil.clamp(this.b*256.0f, 0, 255);
		int value = valueA;
		value <<= 8;
		value |= valueR & 255;
		value <<= 8;
		value |= valueG & 255;
		value <<= 8;
		value |= valueB & 255;
		return value;
	}
}