package android.support.pack.phoenix;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TestCase {
    public static String TAG = "TestCase";
    public String name;
    public String version;
    public int duration;

    public List<TestStep> steps;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<TestStep> getSteps() {
        return steps;
    }

    public void setSteps(List<TestStep> steps) {
        this.steps = steps;
    }
    //    ArrayList<String> subCaseNames = new ArrayList<String>();

//    public static TestCase getTestCase(String path, String caseFileName) throws Exception {
//        TestCase tc = new TestCase();
//        JSONObject testCaseJson = new JSONObject(Util.getFile(path + "/" + caseFileName));
//        tc.name = testCaseJson.getString("name");
//        tc.version = testCaseJson.getString("version");
//        tc.duration = testCaseJson.getInt("duration");
//
//        JSONArray stepsJ = testCaseJson.getJSONArray("steps");
//        for (int i=0;i<stepsJ.length();i++) {
////            JSONObject stepJsonObject = stepsJ.getJSONObject(i);
////            ArrayList<TestStep> tss = TestStep.getTestStep(stepJsonObject, testCaseBrief);
////            tc.testSteps.addAll(tss);
//        }
//
//        return tc;
//    }
//
//    public int getInteruptables(String type) {
//        int n = 0;
//        for (int i =0;i<this.testSteps.size();i++) {
//            if (type.equals(this.testSteps.get(i).interuptable)) {
//                n+=1;
//            }
//        }
//        return n;
//    }

    public String toString() {
        return this.name + " steps=" + this.steps.size();
    }
}

