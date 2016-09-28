package cn.ryanwu.nio2.exp.nio.resolve;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class FixLengthReader extends MessageReader{
	
	private int length;


	public FixLengthReader(int length) {
		this.length = length;
	}

	@Override
	public String read(ByteBuffer buffer) throws UnsupportedEncodingException {
		//长度不足
		if(readableBytes(buffer) < length) {
			return null;
		}
		byte[] tmp = new byte[length];
		buffer.get(tmp, 0, length);
		return new String(tmp, "UTF-8");
	}
	
	
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}
