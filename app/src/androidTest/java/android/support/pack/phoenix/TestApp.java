package android.support.pack.phoenix;

import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)

public class TestApp {
    public static String TAG = "TestApp";
    private UiDevice mDevice;

//    private ConfigPhone config;
//    private PolicyPhone policy;

    public static int displayWidth;
    public static int getDisplayHeight;

    @Before
    public void init() {
        Log.d(TAG,"TestInside started. VERSION: 1.0");
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    void preHandle() throws Exception {
        Log.d(TAG,"");
        Log.d(TAG,"Case preHanlding ");
        if(!mDevice.isScreenOn()) {
            Log.d(TAG,"  -> screen is off, so to turn it on");
            mDevice.pressHome();
        }

        UiObject o1 = mDevice.findObject(new UiSelector().resourceId("com.android.systemui:id/left_shortcut"));
        UiObject o2 = mDevice.findObject(new UiSelector().resourceId("com.android.systemui:id/right_shortcut"));

        if (o1.exists()&&o2.exists()) {
            Log.d(TAG,"  -> it's in lock screen, so to swipe it up");
            int width = mDevice.getDisplayWidth();
            int height = mDevice.getDisplayHeight();
            mDevice.swipe(width/2, height/2+300, width/2, height/2-300,5);
        }
        Log.d(TAG,"");
    }

    @Test
    public void test(){
        String tastcaseString = null;
        try {

//            tastcaseString = FileUtils.readFileToString(new File("/data/local/tmp/alipay_money_000.json"));
            tastcaseString = FileUtils.readFileToString(new File("/data/local/tmp/sesame_config.json"),"utf-8");
//            tastcaseString = FileUtils.readFileToString(new File("/sdcard/sesame_config.json"),"utf-8");
//            tastcaseString = TCPClient.getCase("getCase");
            System.out.println("@@@@@@@"+tastcaseString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        TestCase testCase = FastJsonTools.getInstance(tastcaseString,TestCase.class);
            List<TestStep> testSteps = testCase.steps;

            for (int j = 0; j < testSteps.size(); j++) {
                TestStep testStep = testSteps.get(j);
//                testStepJsonBig = new JSONObject();
                try {
//                    testStepJsonBig.put("TestStep", testStep.stepJsonObject);
//                    testStep.stepJsonObject.put("stepNo", j);
//                    testStep.stepJsonObject.put("stepAll", testSteps.size());
//                    testStep.stepJsonObject.put("startTime", Util.getCurrentTimeLogging(new Date().getTime()));
////                    testStep.stepJsonObject.put("definedHappenRate", policy.interrateAverage*testCaseBrief.accelerateInteraction);
////                    testStep.stepJsonObject.put("definedTelRate", policy.interrateTel);
////                    testStep.stepJsonObject.put("definedSmsRate", policy.interrateSms);
//
//                    testStep.stepJsonObject.put("realHappenLevel", 0);
//                    testStep.stepJsonObject.put("realTelLevel", 0);
//                    testStep.stepJsonObject.put("isHappen", false);
//                    testStep.stepJsonObject.put("happenType", "");
                } catch (Exception e) {throw new AssertionError(e.toString());}

                Log.d(TAG,"");
                Log.d(TAG,"  TestStep: " + j + "/" + testSteps.size());


                try {
                    String serial = "";
                    TestStepHandler.handle(serial, this.mDevice, testStep);
//                    String timeDuration = timerTestStep.getDuration();
//                    testStep.stepJsonObject.put("duration", timeDuration);
//
//                    if (testStep.pause >= 60 * 1000 && testStep.interuptable != null && testStep.interuptable.equals("true")) {
//                        InterruptHandler.handle(testCaseBrief, testStep, policy, testStep.stepJsonObject);
//                    }

                    doPauseActionInMiMi(testStep.pause);
                    Log.d(TAG,"Paused finished");

//                    testStep.stepJsonObject.put("passed", true);
//                    testStep.stepJsonObject.put("snap", "");
//                    testStep.stepJsonObject.put("error", "");
                    Log.d(TAG,"    -> OK");
                } catch (Exception e) {
                    Log.e(TAG,"    -> failed: " + e.toString());
                    throw new AssertionError(e.toString());

                } // end of step
            } // end of case
    }

    private void doPauseActionInMiMi(int pause)  throws Exception {
        Log.d(TAG,"    -> pause " + pause + " ms");
        Thread.currentThread().sleep(pause);
    }

    private void doPauseActionInMi(int seconds)  throws Exception {
        Log.d(TAG,"pause " + seconds + " seconds" );
        Thread.currentThread().sleep(seconds*1000);
    }

    private boolean checkEndCall() {
        //UiObject endButton = new UiObject(new UiSelector().text("End"));//com.android.incallui:id/end_button
        UiObject endButton = mDevice.findObject(new UiSelector().textContains("End"));
        UiObject endButton2 = mDevice.findObject(new UiSelector().resourceId("com.android.dialer:id/floating_end_call_action_button"));
        if(endButton.exists() || endButton2.exists())
        {
            return true;
        }else
        {
            return false;
        }
    }


    private boolean doUiScreenAction(TestStep testStep) throws Exception {
        if (testStep.type .equals("screen")) {

                if (testStep.action.equals("click")) {
                    int x = Integer.parseInt(testStep.value.split(",")[0]);
                    int y = Integer.parseInt(testStep.value.split(",")[1]);
                    this.mDevice.click(x, y);
                } else if (testStep.action.equals("drag")) {
                    String[] blocks = testStep.value.split("&");
                    int startX = Integer.parseInt(blocks[0].split(",")[0]);
                    int startY = Integer.parseInt(blocks[0].split(",")[1]);
                    int endX = Integer.parseInt(blocks[1].split(",")[0]);
                    int endY = Integer.parseInt(blocks[1].split(",")[1]);
                    int steps = Integer.parseInt(blocks[2]);
                    this.mDevice.drag(startX, startY, endX, endY, steps);
                } else if (testStep.action.equals("swipe")) {
                    String[] blocks = testStep.value.split("&");
                    int startX = Integer.parseInt(blocks[0].split(",")[0]);
                    int startY = Integer.parseInt(blocks[0].split(",")[1]);
                    int endX = Integer.parseInt(blocks[1].split(",")[0]);
                    int endY = Integer.parseInt(blocks[1].split(",")[1]);
                    int steps = Integer.parseInt(blocks[2]);
                    this.mDevice.swipe(startX, startY, endX, endY, steps);
                } else if(testStep.action.equals("longPressCoordinate"))
                {
                    String[] blocks = testStep.value.split("&");
                    int X = Integer.parseInt(blocks[0].split(",")[0]);
                    int Y = Integer.parseInt(blocks[0].split(",")[1]);
                    int steps = Integer.parseInt(blocks[1]);
                    android.graphics.Point point = new android.graphics.Point(X,Y);
                    android.graphics.Point[] points =new android.graphics.Point[2];
                    points[0]= point; points[1]= point;
                    this.mDevice.swipe(X,Y,X,Y,steps);
                }
            return true;
        }
        return false;
    }
}
