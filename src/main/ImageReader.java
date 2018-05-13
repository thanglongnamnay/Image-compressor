package main;

import java.util.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageReader {
	public BufferedImage img;
	public File file;
	private final byte r = 0, g = 1, b = 2, a = 3;
	private Compresser compresser;
	public void inputImage(){
		try {
			img = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public byte[] getHeader() {
		FileInputStream is = null;
		byte[] stream = new byte[54];
		try {
			is = new FileInputStream(file);
			is.read(stream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stream;
	}
	public byte[] getPixels() {
		FileInputStream is = null;
		try {
			is = new FileInputStream(file);
			int fileLen = is.available();
			byte[] stream = new byte[fileLen-54];
			is.read(new byte[54]);
			is.read(stream);
			is.close();
			return stream;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void saveFile(String url, boolean mode) {
		compresser = new Compresser(getPixels());
		byte[] header = getHeader();
		int headerLen = header.length;
		byte[] Pixels = mode ? compresser.getCompressedBytes() : compresser.getDecompressedBytes();
		// mode = true : compress mode, mode = false : decompress mode
		byte[] File = new byte[headerLen+Pixels.length];
		for (int i=0; i < File.length; i++) {
			File[i] = i < header.length ? header[i] : Pixels[i - headerLen];
		}
		File f = new File(url);
		try {
			FileOutputStream fo = new FileOutputStream(f);
			fo.write(File);
			fo.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getValue(byte color, int x, int y) {
		int value = img.getRGB(x, y);
		switch (color) {
		case a:	
			return (value >> 24) & 255;
		case r:
			return (value >> 16) & 255;
		case g:
			return (value >> 8) & 255;
		case b:
			return value & 255;
		}
		return -1;
	}
}
