package cn.ryanwu.nio2.exp.nio.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import cn.ryanwu.nio2.exp.utils.SystemUtil;

public class NioClient {
	
	public static int PORT = 1236;
	
	public static void main(String[] args) {
		new Thread(new NioEchoClient("localhost", PORT)).start();
	}
	
	public static class NioEchoClient implements  Runnable{
		
		public static int CAPACITY = 1024;
		
		private Selector selector;
		private SocketChannel socketChannel;
		private String host;
		private int port;
		
		private volatile boolean stop = false;
				
		public NioEchoClient(String host, int port) {
			try {
				this.host = host;
				this.port = port;
				this.selector = Selector.open();
				this.socketChannel = SocketChannel.open();
//				this.socketChannel.configureBlocking(false); //默认是阻塞channel
			} catch (IOException e) {
				e.printStackTrace();
				SystemUtil.exitWithErrorMessage("Failed to config socket channel.");
			}
			
		}

		@Override
		public void run() {
			try {
				if(this.socketChannel.connect(new InetSocketAddress(host, port))) {
					BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
					String inputMessage = null;
					while((inputMessage = input.readLine()) != null) {
						byte[] messageBytes = inputMessage.getBytes();
						ByteBuffer outputBuffer = ByteBuffer.allocate(messageBytes.length);
						outputBuffer.put(messageBytes);
						outputBuffer.flip();
						this.socketChannel.write(outputBuffer);
						
						ByteBuffer inputBuffer = ByteBuffer.allocate(CAPACITY);
						int num = this.socketChannel.read(inputBuffer);
						if(num > 0) {
							inputBuffer.flip();
							messageBytes = new byte[inputBuffer.remaining()];
							inputBuffer.get(messageBytes);
							String message = new String(messageBytes, "UTF-8");
							System.out.println("Get Echo:" + message);
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
//		private void doWrite(SocketChannel channel, String message) throws IOException {
//			byte[] messageBytes = message.getBytes();
//			ByteBuffer buffer = ByteBuffer.allocate(messageBytes.length);
//			buffer.put(messageBytes);
//			buffer.flip();
//			channel.write(buffer);
//		}
//		
//		private void handle(SelectionKey key) throws ClosedChannelException, IOException {
//			if(key.isValid()) {
//				SocketChannel sc = (SocketChannel)key.channel();
//				if(key.isConnectable()) {
//					if(sc.finishConnect()) {
//						sc.register(selector, SelectionKey.OP_READ);
//					}else {
//						System.exit(-1);
//					}
//				}
//				if(key.isReadable()) {
//					ByteBuffer buffer = ByteBuffer.allocate(CAPACITY);
//					int num = sc.read(buffer);
//					if(num > 0) {
//						buffer.flip();
//						byte[] messageBytes = new byte[buffer.remaining()];
//						buffer.get(messageBytes);
//						String message = new String(messageBytes, "UTF-8");
//						System.out.println("Get Echo:" + message);
//					}
//
//				}
//			}
		}
		
		
	
//	}
	

  

}
