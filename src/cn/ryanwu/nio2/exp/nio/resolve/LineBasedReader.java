package cn.ryanwu.nio2.exp.nio.resolve;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class LineBasedReader extends MessageReader{

	@Override
	public String read(ByteBuffer buffer) throws UnsupportedEncodingException {
		int endOfRowIndex = endOfRow(buffer);
		byte[] tmp = new byte[endOfRowIndex];
		if(buffer.position() + endOfRowIndex <= buffer.limit()) {
			buffer.get(tmp, 0, endOfRowIndex);
		}else {
			return null;
		}
		
		if(buffer.position() < buffer.limit()) {
			buffer.get();//跳过'\n'
		}
		
		return new String(tmp, "UTF-8");
	}

	private int endOfRow(ByteBuffer buffer) {
		for(int i = 0; i < buffer.limit(); i++) {
			final byte b = buffer.get(i);
            if (b == '\n') {
                return i;
            } else if (b == '\r' && buffer.get(i + 1) == '\n') {
                return i;  // \r\n
            }
		}
		return -1;
	}
	
	

}
