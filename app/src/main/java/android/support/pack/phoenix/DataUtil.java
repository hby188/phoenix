package android.support.pack.phoenix;

import android.util.Log;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author yinhb
 * @date 20180817
 */
public class DataUtil extends Thread{
	@Override
	public void run() {
		getData();
	}
	public void getData() {

		try {
				String caseContent = HttpUtils.sendGet("http://192.168.1.100:8080/tommycms/case/showCase.action","id=2");
				System.out.println("*****s"+caseContent);
			CaseInstance caseInstance = FastJsonTools.getInstance(caseContent,CaseInstance.class);
//			File file = new File("/data/local/tmp/sesame_config.json");
			File file = new File("/sdcard/sesame_config.json");
//			FileUtils.forceDelete(file);
//			FileUtils.touch(file);
			FileUtils.writeStringToFile(file,caseInstance.getCaseContent(),"utf-8",false);
			System.out.println("++++++++"+FileUtils.readFileToString(file));
		} catch (Exception e) {
			Log.e("DataUtil","DataUtil getData Exception ",e);
		}
	}
	public void sendData() {

	}
}