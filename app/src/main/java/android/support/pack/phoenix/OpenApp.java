package android.support.pack.phoenix;

public class OpenApp extends Thread{
        @Override
        public void run() {

            //class后的参数是包名+类名
            //apk
            ShellUtils.execCommand("am instrument -w -r   -e debug false -e class android.support.pack.phoenix.ExampleInstrumentedTest#demo android.support.pack.phoenix.test/android.support.test.runner.AndroidJUnitRunner",true);
            //jar
//            ShellUtils.execCommand("uiautomator runtest demo.jar --nohup -c com.mypack.UiDevicesTest#testProguard",true);
//                             String command = "am instrument -w -r   -e debug false -e class com.ec.uiautomator2demo.ExampleInstrumentedTest#openMeituan com.ec.uiautomator2demo.test/android.support.test.runner.AndroidJUnitRunner";

        }
    }