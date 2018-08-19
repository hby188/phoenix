package android.support.pack.phoenix;

import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;
import android.view.MotionEvent;

import junit.framework.Assert;

public class TestStepHandler {
    public static String TAG = "TestStepHandler";
    public static void handle(String device, UiDevice uiDevice, TestStep testStep) throws Exception {
        Log.d(TAG,"    -> Handle " + testStep.toJsonString());
//        if (!testStep.type.equals("adb")) {
//            generalCheckAndBypass(device, uiDevice);
//        }
//        if (!checkCondition(uiDevice, testStep)) {
//            return;
//        }
        switch(testStep.type) {
            case "uidevice":
                doUiDevcieAction(uiDevice, testStep);
                break;
            case "scroll":
                doScrollAction(uiDevice, testStep);
                break;
            case "adb":
                doAdbAction(testStep);
                break;
            default:
                doUiObjectAction(uiDevice,testStep);
        }
    }

    private static void doUiObjectAction(UiDevice mDevice, TestStep testStep) throws Exception {
        Log.d(TAG,"    -> doUiObjectAction " + testStep.toJsonString());
        UiObject button = getUiObject(mDevice, testStep.type, testStep.value);
        Log.d(TAG,"    -> UiObject exists now ? " + button.exists());
        if(!button.exists()) {
            if (testStep.isMust.equals("false")) {
                Log.d(TAG,"    -> the step is NOT MUST, just skip ");
                return;
            } else {
                Log.d(TAG,"    -> the step is MUST, Exception may happen ");
            }
        }

        Log.d(TAG,"    -> button = " + button.getText() + "/" + button.getClassName() + "/" + button.getVisibleBounds());

        String[] args = getStrs(testStep.argument, ",");
        switch (testStep.action) {
            case "click":
                button.click();
                break;
            case "clickAndWaitForNewWindow":
                long[] argsLong = getLongs(args);
                if (argsLong.length == 0) {
                    Log.d(TAG,"    -> clickAndWaitForNewWindow");
                    button.clickAndWaitForNewWindow();
                    Log.d(TAG,"    -> clickAndWaitForNewWindow ok");
                }
                else if (argsLong.length == 1) {
                    button.clickAndWaitForNewWindow(argsLong[0]);
                }
                break;
            case "clickBottomRight":
                button.clickBottomRight();
                break;
            case "clickTopLeft":
                button.clickTopLeft();
                break;
            case "longClick":
                int[] argsInt = getInts(args);
                assert (argsInt.length == 1);
                UIAutomatorUtil.longClick(button,argsInt[0]);
                break;
            case "longClickBottomRight":
                button.longClickBottomRight();
                break;
            case "longClickTopLeft":
                button.longClickTopLeft();
                break;
            case "dragTo":
                argsInt = getInts(args);
                assert (argsInt.length == 3);
                button.dragTo(argsInt[0],argsInt[1],argsInt[2]);
                break;
            case "swipeDown":
                argsInt = getInts(args);
                assert (argsInt.length == 1);
                button.swipeDown(argsInt[0]);
                break;
            case "swipeUp":
                argsInt = getInts(args);
                assert (argsInt.length == 1);
                button.swipeUp(argsInt[0]);
                break;
            case "swipeLeft":
                argsInt = getInts(args);
                assert (argsInt.length == 1);
                button.swipeLeft(argsInt[0]);
                break;
            case "swipeRight":
                argsInt = getInts(args);
                assert (argsInt.length == 1);
                button.swipeRight(argsInt[0]);
                break;
            case "setText":
                button.setText(testStep.argument);
                break;
            case "clearTextField":
                button.clearTextField();
                break;
            case "pinchIn":
                argsInt = getInts(args);
                assert (argsInt.length == 2);
                button.pinchIn(argsInt[0], argsInt[1]);
                break;
            case "pinchOut":
                argsInt = getInts(args);
                assert (argsInt.length == 2);
                button.pinchOut(argsInt[0], argsInt[1]);
                break;
            case "multiPointers":
                args = getStrs(testStep.argument, ";");
                MotionEvent.PointerCoords[][] ps = new MotionEvent.PointerCoords[5][];

                for(int i=0;i<args.length;i++) {
                    String[] ss = args[i].split(",");
                    argsInt = getInts(ss);
                    ps[i] =getCoords(argsInt[0], argsInt[1],argsInt[2],argsInt[3],argsInt[4]);
                }
                if (args.length == 2) {
                    button.performMultiPointerGesture(ps[0], ps[1]);
                } else if (args.length == 3) {
                    button.performMultiPointerGesture(ps[0], ps[1], ps[2]);
                } else if (args.length == 4) {
                    button.performMultiPointerGesture(ps[0], ps[1], ps[2], ps[3]);
                } else if (args.length == 5) {
                    button.performMultiPointerGesture(ps[0], ps[1], ps[2], ps[3], ps[4]);
                }

                break;
            case "waitForExists":
                argsLong = getLongs(args);
                assert (argsLong.length == 1);
                button.waitForExists(argsLong[0]);
                break;
            case "waitUntilGone":
                argsLong = getLongs(args);
                assert (argsLong.length == 1);
                button.waitUntilGone(argsLong[0]);
                break;
            case "exists":
                button.exists();
                break;
            default:
                throw new Exception("Action InCorrect. " + testStep.toJsonString());

        }
    }



    private static UiObject getUiObject(UiDevice uiDevice, String type, String value) throws Exception {
        String[] keys = getStrs(type, "&");
        String[] values = getStrs(value, ",");
        UiSelector uiSelector = new UiSelector();
        for (int i=0;i<keys.length;i++) {
            uiSelector = select(uiSelector, keys[i], values[i]);
        }
        return uiDevice.findObject(uiSelector);
   }

    private static UiSelector select(UiSelector uiSelector, String type, String value) throws Exception {
        switch (type) {
            case "text":
                return uiSelector.text(value);
            case "textContains":
                return uiSelector.textContains(value);
            case "textMatches":
                return uiSelector.textMatches(value);
            case "textStartsWith":
                return uiSelector.textStartsWith(value);
            case "description":
            case "content-desc":
                return uiSelector.description(value);
            case "descriptionContains":
                return uiSelector.descriptionContains(value);
            case "descriptionMatches":
                return uiSelector.descriptionMatches(value);
            case "descriptionStartsWith":
                return uiSelector.descriptionStartsWith(value);
            case "className":
            case "class":
                return uiSelector.className(value);
            case "classNameMatches":
                return uiSelector.classNameMatches(value);
            case "packageName":
            case "package":
                return uiSelector.packageName(value);
            case "packageNameMatches":
                return uiSelector.packageNameMatches(value);
            case "resourceId":
            case "resource-id":
                return uiSelector.resourceId(value);
            case "resourceIdMatches":
                return uiSelector.resourceIdMatches(value);
            case "index":
                return uiSelector.index(Integer.parseInt(value));
            case "checkable":
                 return uiSelector.checkable(Boolean.parseBoolean(value));
            case "checked":
                return uiSelector.checked(Boolean.parseBoolean(value));
            case "clickable":
                return uiSelector.clickable(Boolean.parseBoolean(value));
            case "longClickable":
                return uiSelector.longClickable(Boolean.parseBoolean(value));
            case "enabled":
                return uiSelector.enabled(Boolean.parseBoolean(value));
            case "focusable":
                return uiSelector.focusable(Boolean.parseBoolean(value));
            case "focused":
                return uiSelector.focused(Boolean.parseBoolean(value));
            case "scrollable":
                return uiSelector.scrollable(Boolean.parseBoolean(value));
            case "selected":
                return uiSelector.selected(Boolean.parseBoolean(value));
            default:
                throw new Exception("InCorrect type: " + type);
        }
    }


    private static void doScrollAction(UiDevice uiDevice, TestStep testStep) throws Exception {
        Log.d(TAG,"    -> doScrollAction " + testStep.toJsonString());
        UiScrollable uiScrollable = new UiScrollable(new UiSelector().scrollable(true));

        String vas = testStep.value;
        String[] vass = getStrs(vas, ",");
        if (vass.length>0) {
            int[] vassInt = getInts(vass);
            if (vassInt[0]==0) {
                uiScrollable.setAsHorizontalList();
            } else if (vassInt[0]==1) {
                uiScrollable.setAsVerticalList();
            }
            if (vassInt[1]>0) {
                uiScrollable.setMaxSearchSwipes(vassInt[1]);
            }

            if (vassInt[2]>0) {
                uiScrollable.setSwipeDeadZonePercentage(vassInt[2]);
            }
        }

        String argument = testStep.argument;
        String[] args = getStrs(argument, ",");
        switch (testStep.action) {
            case "scrollBackward":
                int[] argsInt = getInts(args);
                Assert.assertTrue(argsInt.length==0||argsInt.length==1);
                if (argsInt.length == 0) {
                    uiScrollable.scrollBackward();

                } else if (argsInt.length == 0) {
                    uiScrollable.scrollBackward(argsInt[0]);
                }
                break;
            case "scrollForward":
                argsInt = getInts(args);
                Assert.assertTrue(argsInt.length==0||argsInt.length==1);
                if (argsInt.length == 0) {
                    uiScrollable.scrollForward();

                } else if (argsInt.length == 0) {
                    uiScrollable.scrollForward(argsInt[0]);
                }
                break;
            case "scrollToBeginning":
                argsInt = getInts(args);
                Assert.assertTrue(argsInt.length==1||argsInt.length==2);
                if (argsInt.length == 1) {
                    uiScrollable.scrollToBeginning(argsInt[0]);

                } else if (argsInt.length == 2) {
                    uiScrollable.scrollToBeginning(argsInt[0],argsInt[1]);
                }
                break;
            case "scrollToEnd":
                argsInt = getInts(args);
                Assert.assertTrue(argsInt.length==1||argsInt.length==2);
                if (argsInt.length == 1) {
                    uiScrollable.scrollToEnd(argsInt[0]);

                } else if (argsInt.length == 2) {
                    uiScrollable.scrollToEnd(argsInt[0],argsInt[1]);
                }
                break;
            case "scrollDescriptionIntoView":
                Assert.assertTrue(args.length==1);
                uiScrollable.scrollDescriptionIntoView(args[0]);
                break;
            case "scrollTextIntoView":
                Assert.assertTrue(args.length==1);
                uiScrollable.scrollTextIntoView(args[0]);
                break;
            case "scrollTextIntoViewSpecial":
                Assert.assertTrue(args.length==2);
                UIAutomatorUtil.scrollToText(uiDevice, uiScrollable, args[0], Integer.parseInt(args[1]));
                break;
            case "flingBackward":
                Assert.assertTrue(args.length==0);
                uiScrollable.flingBackward();
                break;
            case "flingForward":
                Assert.assertTrue(args.length==0);
                uiScrollable.flingForward();
                break;
            case "flingToBeginning":
                argsInt = getInts(args);
                Assert.assertTrue(argsInt.length==1);
                uiScrollable.flingToBeginning(argsInt[0]);
                break;
            case "flingToEnd":
                argsInt = getInts(args);
                Assert.assertTrue(argsInt.length==1);
                uiScrollable.flingToEnd(argsInt[0]);
                break;

            default:
                throw new Exception("Unknown scroll action: " + testStep.action);
        }
    }

    private static void doUiDevcieAction(UiDevice uiDevice, TestStep testStep) throws Exception {
        Log.d(TAG,"    -> doUiDevcieAction " + testStep.toJsonString());
        final String[] uiDeviceActionList = {
                "pressHome",
                "pressBack",
                "pressMenu",
                "pressDelete",
                "pressEnter",
                "pressRecentApps",
                "pressSearch",
                "pressDPadCenter",
                "pressDPadDown",
                "pressDPadLeft",
                "pressDPadRight",
                "pressDPadUp",
                "sleep",
                "wakeUp",
                "freezeRotation",
                "unfreezeRotation",
                "openNotification",
                "openQuickSettings",
                "setOrientationLeft",
                "setOrientationNatural",
                "setOrientationRight"

                
        };

        if (Util.isIn(uiDeviceActionList, testStep.action)) {
            uiDevice.getClass().getMethod(testStep.action, null).invoke(uiDevice);
            return;
        }

        String argument = testStep.argument.trim();
        String[] args = getStrs(argument, ",");

        switch (argument) {
            case "down":
                argument = "w0.5,h0.25,w0.5,h0.75, 10";
                break;
            case "up":
                argument = "w0.5,h0.75,w0.5,h0.25, 10";
                break;
            case "left":
                argument = "w0.9,h0.5,w0.1,h0.5, 10";
                break;
            case "right":
                argument = "w0.1,h0.5,w0.9,h0.5, 10";
                break;
        }

        switch (testStep.action) {
            case "pressKeyCode":
                int[] argsInt = getInts(args);
                Assert.assertTrue(argsInt.length==1||argsInt.length==2);
                if (argsInt.length == 1) {
                    uiDevice.pressKeyCode(argsInt[0]);
                } else if (argsInt.length == 2) {
                    uiDevice.pressKeyCode(argsInt[0], argsInt[1]);
                } else {
                    throw new Exception("Augument incorrect: " + testStep.action + " with " + argument);
                }
                break;
            case "click":
                argsInt = getInts(args);
                Assert.assertTrue(argsInt.length==2);
                uiDevice.click(argsInt[0], argsInt[1]);
                break;
            case "longClick":
                argsInt = getInts(args);
                Assert.assertTrue(argsInt.length==3);
                uiDevice.drag(argsInt[0], argsInt[1],argsInt[0], argsInt[1], argsInt[2]/20);
                break;

            case "drag":
                argsInt = getInts(args);
                Assert.assertTrue(argsInt.length==5);
                uiDevice.drag(argsInt[0],argsInt[1],argsInt[2],argsInt[3],argsInt[4]);
                break;
            case "swipe":
                switch (argument) {
                    case "down":
                        argument = "w0.5,h0.1,w0.5,h0.9, 10";
                        break;
                    case "up":
                        argument = "w0.5,h0.9,w0.5,h0.1, 10";
                        break;
                    case "left":
                        argument = "w0.9,h0.5,w0.1,h0.5, 10";
                        break;
                    case "right":
                        argument = "w0.1,h0.5,w0.9,h0.5, 10";
                        break;
                }
                args = getStrs(argument, ",");
                argsInt = getIntsAdvanced(args);

                Assert.assertTrue(argsInt.length==5);
                uiDevice.swipe(argsInt[0],argsInt[1],argsInt[2],argsInt[3],argsInt[4]);
                break;
            case "swipeRight":
                argument = "w0.9,h0.5,w0.1,h0.5, 10";
                args = getStrs(argument, ",");
                argsInt = getIntsAdvanced(args);
                Assert.assertTrue(argsInt.length==5);
                uiDevice.swipe(argsInt[0],argsInt[1],argsInt[2],argsInt[3],argsInt[4]);
                break;
            case "voiceDown":
                uiDevice.pressKeyCode(25);
                break;
            case "voiceUp":
                uiDevice.pressKeyCode(24);
                break;
            case "launchApp":
                launchApp(uiDevice, argument);
                break;
            default:
                throw new Exception("Unknown uidevice action: " + testStep.action);
        }
    }

    static void launchApp(UiDevice uiDevice, String app) throws Exception {
        Log.d(TAG,"launchApp " + app);
        uiDevice.pressHome();
        uiDevice.pressHome();
        UiSelector uiSelector = new UiSelector();
        for (int i=0;i<20;i++) {
            UiObject o = uiDevice.findObject(uiSelector.text(app));
            if (o.exists()) {
                Log.d(TAG,"  -> got in page " + i);
                o.clickAndWaitForNewWindow();
                return;
            } else {
                Log.d(TAG,"  -> no in page " + i);
                String argument = "w0.9,h0.5,w0.1,h0.5, 10";
                String[] strs = getStrs(argument, ",");
                int[] argsInt = getIntsAdvanced(strs);
                uiDevice.swipe(argsInt[0],argsInt[1],argsInt[2],argsInt[3],argsInt[4]);
            }
        }
        throw new Exception("launchApp:" + app + " failed");
    }

    static String[] getStrs(String str, String sp) {
        if (str.trim().length()==0) {
            return new String[]{};
        }
        String[] ss = str.split(sp);
        for (int i=0;i<ss.length;i++) {
            ss[i].trim();
        }
        return ss;
    }

    static int[] getInts(String[] strs) {
        int[] as = new int[strs.length];
        for (int i=0;i<strs.length;i++) {
            as[i] = Integer.parseInt(strs[i].trim());
        }
        return as;
    }

    static int[] getIntsAdvanced(String[] strs) {
        int[] as = new int[strs.length];
        for (int i=0;i<strs.length;i++) {
            if (strs[i].startsWith("w")) {
                as[i] = (int) (Double.parseDouble(strs[i].substring(1))*TestApp.displayWidth);
            } else if (strs[i].startsWith("h")){
                as[i] = (int) (Double.parseDouble(strs[i].substring(1))*TestApp.getDisplayHeight);
            } else {
                as[i] = Integer.parseInt(strs[i].trim());
            }
        }
        return as;
    }

    static long[] getLongs(String[] strs) {
        long[] as = new long[strs.length];
        for (int i=0;i<strs.length;i++) {
            as[i] = Long.parseLong(strs[i].trim());
        }
        return as;
    }

    static private boolean doAdbAction(TestStep testStep) throws Exception {
        Log.d(TAG,"    -> doAdbAction:" + testStep.toJsonString());
        Log.d(TAG,testStep.toJsonString()) ;
        return false;
    }

    static void generalCheckAndBypass(String device, UiDevice mDevice) {

        //generalCheckAndBypass(mDevice, new String[]{"Authorization request", "Allow"}, 1);
        //generalCheckAndBypass(mDevice, new String[]{"OK", "Discard"}, 0);
        generalCheckAndBypass(mDevice, new String[]{"权限请求", "允许"}, 1);
        //generalCheckAndBypass(mDevice, new String[]{"OK", "Discard"}, 0);
        generalCheckAndBypass(mDevice, new String[]{"提示", "继续"}, 1);
        generalCheckAndBypass(mDevice, new String[]{"查看详情", "忽略风险"}, 1);
        generalCheckAndBypass(mDevice, new String[]{"地理位置", "取消"}, 1);
        generalCheckAndBypass(mDevice, new String[]{"发现新版本", "忽略本次"}, 1);
        generalCheckAndBypass(mDevice, new String[]{"恢复", "放弃"}, 1);
        generalCheckAndBypass(mDevice, new String[]{"系统升级", "稍后"}, 1);
        generalCheckAndBypass(mDevice, new String[]{"软件更新", "稍后"}, 1);
        generalCheckAndBypassContains(device, mDevice, new String[]{"要将其关闭吗", "确定"}, 1);

    }

    static void generalCheckAndBypassContains(String device, UiDevice uiDevice, String[] strs, int theClickOne) {
        Log.d(TAG,"generalCheckAndBypass " + strs[theClickOne]);
        UiObject[] uiObjects = new UiObject[strs.length];
        for (int i=0;i<uiObjects.length;i++) {
            uiObjects[i] = uiDevice.findObject(new UiSelector().textContains(strs[i]));
        }

        boolean exist = true;
        for (int i=0;i<uiObjects.length;i++) {
            if (!uiObjects[i].exists()) {
                exist = false;
            }
        }
        if(exist) {
            Log.d(TAG,"  -> find " );
            try{
                String errorFile = device + "_" + new java.util.Date().getTime() + "_ANR" + ".png";
                String snapshot = "{\"device\": \"" + device + "\", \"fileName\": \"" + errorFile + "\", \"type\": \"ANR\"  }";
                Log.d(TAG,snapshot);
                Thread.currentThread().sleep(5000);
                Log.d(TAG,"  -> Click " + strs[theClickOne]);
                uiObjects[theClickOne].click();
                Log.d(TAG,"  -> Click " + strs[theClickOne] + " OK ");
            }catch (Exception e) {
                Log.d(TAG,"  -> Click " + strs[theClickOne] + " Failed " + e.toString());
            }
        }
    }

    static void generalCheckAndBypass(UiDevice uiDevice, String[] strs, int theClickOne) {
        Log.d(TAG,"generalCheckAndBypass " + strs[theClickOne]);
        UiObject[] uiObjects = new UiObject[strs.length];
        for (int i=0;i<uiObjects.length;i++) {
            uiObjects[i] = uiDevice.findObject(new UiSelector().text(strs[i]));
        }

        boolean exist = true;
        for (int i=0;i<uiObjects.length;i++) {
            if (!uiObjects[i].exists()) {
                exist = false;
            }
        }
        if(exist) {
            Log.d(TAG,"  -> find " );
            try{
                Log.d(TAG,"  -> Click " + strs[theClickOne]);
                uiObjects[theClickOne].click();
                Log.d(TAG,"  -> Click " + strs[theClickOne] + " OK ");
            }catch (Exception e) {
                Log.d(TAG,"  -> Click " + strs[theClickOne] + " Failed " + e.toString());
            }
        }
    }

    static boolean checkCondition(UiDevice uiDevice, TestStep testStep) {
        Log.d(TAG,"checkCondition");
        if (testStep.conditionText.equals("")) {
            Log.d(TAG,"  -> no condition");
            return true;
        }
        UiObject o = uiDevice.findObject(new UiSelector().text(testStep.conditionText));
        if(o.exists()&&testStep.conditionType.equals("has")) {
            Log.d(TAG,"  -> condition meet: has " + testStep.conditionText);
            return true;
        }

        if (!o.exists()&&!testStep.conditionType.equals("hasMo")) {
            Log.d(TAG,"  -> condition meet: hasNo " + testStep.conditionText);
            return true;
        }
        Log.d(TAG,"  -> condition is not meet: " + testStep.conditionType + " " + testStep.conditionText);
        return false;
    }

    private static MotionEvent.PointerCoords[] getCoords(int x, int y, int x1, int y1, int steps) {
        MotionEvent.PointerCoords[] cs = new MotionEvent.PointerCoords[steps+1];
        for(int i=0;i<steps+1;i++) {
            MotionEvent.PointerCoords p1 = new MotionEvent.PointerCoords();
            p1.x = x + i * (x1-x) / steps;
            p1.y = y + i * (y1-y) / steps;
            p1.pressure = 1;
            p1.size = 1;

            cs[i] = p1;
        }
        return cs;
    }

}
