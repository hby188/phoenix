package android.support.pack.phoenix;

/**
 * @author yinhb
 * @date 20180817
 */
public class CloseApp extends Thread{
        @Override
        public void run() {
            //class后的参数是包名+类名
            ShellUtils.execCommand("am instrument -w -r   -e debug false -e class android.support.pack.phoenix.ExampleInstrumentedTest#demo android.support.pack.phoenix.test/android.support.test.runner.AndroidJUnitRunner",true);
//                             String command = "am instrument -w -r   -e debug false -e class com.ec.uiautomator2demo.ExampleInstrumentedTest#openMeituan com.ec.uiautomator2demo.test/android.support.test.runner.AndroidJUnitRunner";

        }
    }