package main;

import java.util.Arrays;

public class Compresser {
	byte[] stream;
	final int compressRate = 4;
	Compresser(byte[] s){
		stream = s;
	}
	byte[] getCompressedBytes(){
		byte[] compressedStream = new byte[(stream.length + 1) / 2];
		for(int i = 0; i < stream.length; i+=2) 
			if (i + 1 < stream.length) 
				compressedStream[i/2] = (byte) ((stream[i] & 255 >> compressRate << compressRate) + ((stream[i+1] & 255) >> compressRate));
			else
				compressedStream[i/2] = (byte) (stream[i] & 255 >> compressRate << compressRate);
		return compressedStream;
	}
	byte[] getDecompressedBytes() {
		byte[] decompressedBytes = new byte[stream.length * 2];
		for (int i = 0; i< stream.length; i++) {
			decompressedBytes[i * 2] = (byte) (stream[i] & 255 >> compressRate << compressRate);
			decompressedBytes[i * 2 + 1] = (byte) ((stream[i] & 15) << compressRate);
		}
		return decompressedBytes;
	}
}
