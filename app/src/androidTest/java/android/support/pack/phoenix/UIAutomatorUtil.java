package android.support.pack.phoenix;

import android.graphics.Rect;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;

public class UIAutomatorUtil {
    public static boolean longClick(UiObject uiObject) throws Exception {
        return uiObject.dragTo(uiObject, 10);
    }

    public static boolean longClick(UiObject uiObject, int milliSeconds) throws Exception {
        return uiObject.dragTo(uiObject, milliSeconds/20);
    }

    public static void scrollToText(UiDevice uiDevice, UiScrollable uiScrollable, String text, int maxStep) throws Exception {
        boolean isTop = false;
        do{
            isTop = uiScrollable.flingToBeginning(1);
        }while(!isTop);

        Rect r = uiScrollable.getBounds();
        int dh = 100;
        if (r.height()>400) {
            dh = 200;
        }

        for (int i=0;i<maxStep;i++) {
            UiObject o = uiDevice.findObject(new UiSelector().text(text));
            if  (o.exists()&&o.getBounds().bottom<1710) {
                return;
            } else {
                uiDevice.swipe(
                        (r.left + r.width()) / 2,
                        (r.top + r.height())/2 + dh,
                        (r.left + r.width()) / 2,
                        (r.top + r.height())/2 - dh, 10);
            }
        }
    }
}
