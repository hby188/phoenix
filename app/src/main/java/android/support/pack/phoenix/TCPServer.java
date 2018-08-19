package android.support.pack.phoenix;

import android.util.Log;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author yinhb
 * @date 20180817
 */
public class TCPServer extends Thread{
	@Override
	public void run() {
		initTcpServer();
	}
	//Size of receive buffer
	private final int BUFSIZE=64;
	public void initTcpServer() {
		int servPort = 9999;
		ServerSocket servSocket =null;
		int recvMsgSize=0;
		byte[] receivBuf=new byte[BUFSIZE];

		try {
			servSocket=new ServerSocket(servPort);
			while(true){
				Socket clientSocket=servSocket.accept();
				SocketAddress clientAddress = clientSocket.getRemoteSocketAddress();
				System.out.println("Handling client at "+ clientAddress);
				InputStream in =clientSocket.getInputStream();
				OutputStream out= clientSocket.getOutputStream();
				while(!clientSocket.isClosed()&&(recvMsgSize=in.read(receivBuf))!=-1){
					String receivedData=new String(receivBuf,"utf-8");
					System.out.println(receivedData);
					String caseContent = HttpUtils.sendGet("http://192.168.1.100:8080/tommycms/case/showCase.action","id=1");
	//				System.out.println("*****s"+caseContent);
					out.write(caseContent.getBytes());
					in.close();
					clientSocket.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			Log.e("TCPServer","TCPServer init Exception ",e);
		}


	}

}