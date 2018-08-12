package android.support.pack.phoenix;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("pack.support.android.phoenix", appContext.getPackageName());
    }

    private UiDevice mDevice;

    @Test
    public void demo() throws Exception {
        int i = 0;
        while (i < 3) {
            mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
            mDevice.pressHome();
            mDevice.pressHome();
            Thread.sleep(1500);
//            mDevice.click(950, 1600);
            mDevice.click(620, 1144);
            Thread.sleep(1500);
            mDevice.pressBack();
            mDevice.click(950, 1600);
            Thread.sleep(1500);
            mDevice.pressBack();
//            mDevice.pressHome();
//            mDevice.pressHome();
//        UiObject x=mDevice.findObject(new UiSelector().text("支付宝"));
//        x.clickAndWaitForNewWindow();
//        x.click();
            i++;
        }
    }

    @Test
    public void demo1() throws Exception {
        int i = 0;
        while (i < 3) {
            mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
            mDevice.pressHome();
            mDevice.pressHome();
            Thread.sleep(2000);
//            mDevice.click(950, 1600);
            mDevice.click(620, 1144);
            Thread.sleep(3000);
            mDevice.pressBack();
            mDevice.click(950, 1600);
            Thread.sleep(3000);
            mDevice.pressBack();
//            mDevice.pressHome();
//            mDevice.pressHome();
//        UiObject x=mDevice.findObject(new UiSelector().text("支付宝"));
//        x.clickAndWaitForNewWindow();
//        x.click();
            i++;
        }
    }

    @Test
    public void openMeituan() throws Exception {

//        int i = 0;
        for(int i=0;i<10;i++){
            mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
            mDevice.pressHome();
            mDevice.pressHome();
//        mDevice.click(540,1633);
            mDevice.click(620, 1144);
            mDevice.pressHome();
            Thread.sleep(1000);
        }

//        UiObject meituanApp = mDevice.findObject(new UiSelector().text("美团"));
//        meituanApp.clickAndWaitForNewWindow();
////        mDevice.pressBack();
//        UiObject searchBox = mDevice.findObject(new UiSelector().resourceId("com.sankuai.meituan:id/search_edit"));
//        searchBox.click();
//        searchBox.setText("民国红公馆");
//        UiObject searchButton = mDevice.findObject(new UiSelector().resourceId("com.sankuai.meituan:id/search"));
//        searchButton.clickAndWaitForNewWindow();


//        int i = 0;
//        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
//        mDevice.pressHome();
//        mDevice.pressHome();
//        UiObject meituanApp = mDevice.findObject(new UiSelector().text("美团"));
//        meituanApp.clickAndWaitForNewWindow();
////        mDevice.pressBack();
//        UiObject searchBox = mDevice.findObject(new UiSelector().resourceId("com.sankuai.meituan:id/search_edit"));
//        searchBox.click();
//        searchBox.setText("民国红公馆");
//        UiObject searchButton = mDevice.findObject(new UiSelector().resourceId("com.sankuai.meituan:id/search"));
//        searchButton.clickAndWaitForNewWindow();
    }
}
