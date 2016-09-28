package cn.ryanwu.nio2.exp.bio.pool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioServer {

	public static int PORT = 1234;
	
	public static ExecutorService exec = Executors.newFixedThreadPool(100);
	
	/**
	 * 使用线程池
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		try {
			serverSocket =  new ServerSocket(PORT);
			System.out.println("server start ..., listen port :" + PORT);
			while (true) {
//				new EchoThread(serverSocket.accept()).start();
				Socket socket = serverSocket.accept();
				exec.execute(new EchoThread(socket));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			serverSocket.close();
			System.out.println("server shutdown ....");
		}
	}

	public static class EchoThread extends Thread {

		private Socket socket;

		public EchoThread(Socket socket) {
			System.out.println("client connected at port: " + socket.getPort());
			this.socket = socket;
		}

		@Override
		public void run() {
			
			BufferedReader reader = null;
			PrintWriter writer = null;
			try {
				reader = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				writer = new PrintWriter(socket.getOutputStream(),true);
				String line = null;
				while((line = reader.readLine()) != null) {
					System.out.println("Echo: " + line);
					writer.println(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
