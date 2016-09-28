package cn.ryanwu.nio2.exp.nio.resolve;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public abstract class  MessageReader {
	
	public abstract String read(ByteBuffer buffer) throws UnsupportedEncodingException;
	
	public int readableBytes(ByteBuffer buffer) {
		return buffer.limit() - buffer.position();
	}

	
}
