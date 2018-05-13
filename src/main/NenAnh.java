package main;

public class NenAnh {
	private static final byte r = 0, g = 1, b = 2;
	NenAnh(){
		ImageReader ir = new ImageReader();
		new Gui(ir);
	}
	public static void main(String[] args) {
		new NenAnh();
	}
}
