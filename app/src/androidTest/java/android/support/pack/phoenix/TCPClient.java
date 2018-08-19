package android.support.pack.phoenix;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author yinhb
 * @date 20180817
 */
public class TCPClient {
	/**
	 * @param sendData
	 */
	public static String getCase(String sendData) {
		String server = "127.0.0.1";
//		String sendData="getCase";
		StringBuilder caseContent = new StringBuilder("");
		byte[] data = sendData.getBytes();
		int servPort = 9999;
		Socket socket = null;
		try {
			socket = new Socket(server, servPort);
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			out.write(data);
			int b = 0;
			while ((b = in.read())!= -1) {
				caseContent.append((char)b);
			}
			System.out.println("Received:" + caseContent);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			Log.e("TCPClient","TCPClient request UnknownHostException ",e);
		} catch (IOException e) {
			e.printStackTrace();
			Log.e("TCPClient","TCPClient request IOException ",e);
		} finally {
			try {
				if (socket != null) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				Log.e("TCPClient","TCPClient request socket.close() IOException ",e);
			}
		}
		return caseContent.toString();
	}
}