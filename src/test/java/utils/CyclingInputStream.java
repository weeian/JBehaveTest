package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class CyclingInputStream extends InputStream{
	private int bytes;
	private int readedBytes;

	public CyclingInputStream(int bytes){
		this.bytes = bytes;
	}

	@Override
	public int read() throws IOException {
		return 0;
	}

	@Override
	public int read(byte[] b) throws IOException {
		return read(b, 0 , b.length);
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		if(readedBytes >= bytes){
			return -1;
		}
		int leftLen = bytes-readedBytes;
		if(b.length <= leftLen){
			readedBytes += b.length;
			randomByte(b,b.length);
			return b.length;
		}else{
			readedBytes = bytes;
			randomByte(b,leftLen);
			return leftLen;
		}
	}

	public byte[] randomByte(byte[] buffer,int len){
		for (int i = 0; i < len ; i++) {
			buffer[i] = (byte) new Random().nextInt(10);
		}
		return buffer;
	}

	@Override
	public int available() throws IOException {
		return this.bytes;
	}

	@Override
	public void close() throws IOException {
	}
}
