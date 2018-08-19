package android.support.pack.phoenix;

import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;

public class TestStep {

    public static String TAG = "TestStep";

//    JSONObject stepJsonObject;
    public String target;
    public String action;
    public String argument;
    public String type;
    public String value;
    public int pause;
    //public int repeat;
    public String interuptable;
    public String isMust;
    public String conditionType;
    public String conditionText;

//    public JSONObject getStepJsonObject() {
//        return stepJsonObject;
//    }
//
//    public void setStepJsonObject(JSONObject stepJsonObject) {
//        this.stepJsonObject = stepJsonObject;
//    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getPause() {
        return pause;
    }

    public void setPause(int pause) {
        this.pause = pause;
    }

    public String getInteruptable() {
        return interuptable;
    }

    public void setInteruptable(String interuptable) {
        this.interuptable = interuptable;
    }

    public String getIsMust() {
        return isMust;
    }

    public void setIsMust(String isMust) {
        this.isMust = isMust;
    }

    public String getConditionType() {
        return conditionType;
    }

    public void setConditionType(String conditionType) {
        this.conditionType = conditionType;
    }

    public String getConditionText() {
        return conditionText;
    }

    public void setConditionText(String conditionText) {
        this.conditionText = conditionText;
    }

//    TestStep() {
//    }
//
//    public static ArrayList<TestStep> getTestStep(JSONObject stepJsonObject) throws Exception {
//        ArrayList<TestStep> testSteps = new ArrayList<TestStep>();
//
//        //String include  = Util.getJsonValue(stepJsonObject, "action");
//
//        TestStep teststep = new TestStep();
//        teststep.stepJsonObject = stepJsonObject;
//        teststep.target = Util.getJsonValueStr(stepJsonObject,"target", "master");
//        teststep.action = stepJsonObject.getString("action");
//        teststep.argument = Util.getJsonValueStr(stepJsonObject,"argument", "");
////        teststep.argument = getParaedValue(teststep.argument, testCaseBrief);
//        teststep.stepJsonObject.put("argument", teststep.argument);
//        teststep.type = Util.getJsonValueStr(stepJsonObject,"type", "uidevice");
//        teststep.value = Util.getJsonValueStr(stepJsonObject,"value", "");
////        teststep.value = getParaedValue(teststep.value, testCaseBrief);
//        teststep.stepJsonObject.put("value", teststep.value);
//        teststep.pause = Util.getJsonValueInt(stepJsonObject, "pause", 500);
//        teststep.interuptable = Util.getJsonValueStr(stepJsonObject, "interuptable", "false");
//
//        teststep.isMust = Util.getJsonValueStr(stepJsonObject, "isMust", "true");
//        if (stepJsonObject.isNull("isMust")) {
//            stepJsonObject.put("isMust", teststep.isMust);
//        }
//
//        teststep.conditionType = Util.getJsonValueStr(stepJsonObject, "conditionType", "has");
//        if (stepJsonObject.isNull("conditionType")) {
//            stepJsonObject.put("conditionType", "has");
//        }
//
//        teststep.conditionText = Util.getJsonValueStr(stepJsonObject, "conditionText", "");
//        if (stepJsonObject.isNull("conditionText")) {
//            stepJsonObject.put("conditionText", "");
//        }
//
//        testSteps.add(teststep);
//
//        return testSteps;
//    }
//
//    public static String getParaedValue(String key) throws Exception {
//        if (key!=null && key.startsWith("%") && key.endsWith("%")) {
//            Log.d(TAG,"  -> getParaedData " + key);
//
//            String realKey = key.substring(1, key.length() -1);
//            String value = null;
//
//            if (realKey.length() == 0) {
//                throw new Exception("Incorrect para:" + key + " of " + testCaseBrief.name);
//            }
//            String[] realKeyData = realKey.split("=");
//            if (realKeyData.length>2) {
//                throw new Exception("Incorrect para:" + key + " of " + testCaseBrief.name);
//            }
//            if (realKeyData.length == 2) {
//                realKey = realKeyData[0];
//            }
//
//            if (realKey.equals("master_phone_number")) {
//                value = ConfigPhone.getConfig().master_phone_number;
//            } else if (realKey.equals("master_phone_number_2")) {
//                value = ConfigPhone.getConfig().master_phone_number_2;
//            } else if (realKey.equals("slave_phone_number")) {
//                value = ConfigPhone.getConfig().slave_phone_number;
//            } else if (realKey.equals("master_phone_serial")) {
//                value = ConfigPhone.getConfig().master_phone_serial;
//            } else if (realKey.equals("slave_phone_serial")) {
//                value = ConfigPhone.getConfig().slave_phone_serial;
//            } else if (realKey.equals("emailAccount")) {
//                value = ConfigPhone.getConfig().emailAccount;
//            } else if (realKey.equals("emailPassword")) {
//                value = ConfigPhone.getConfig().emailPassword;
//            }
//
//            if(value!=null) {
//                Log.d(TAG,"  -> value from config " + value);
//                return value;
//            }
//
//            if (testCaseBrief.para.containsKey(realKey)) {
//                value = testCaseBrief.para.get(realKey);
//                Log.d(TAG,"  -> value from testcasebrief " + value);
//                return value;
//            }
//
//            if (PolicyPhone.getPholicyPhone().paras.containsKey(realKey)) {
//                value = PolicyPhone.getPholicyPhone().paras.get(realKey);
//                Log.d(TAG,"  -> value from policy " + value);
//                return value;
//            }
//
//            if (value == null&&realKeyData.length == 2) {
//                value = realKeyData[1];
//                Log.d(TAG,"  -> value from default " + value);
//                return value;
//            }
//
//            if (value == null) {
//                throw new Exception("Undefined para:" + key + " in " + testCaseBrief.name);
//            } else {
//                return value;
//            }
//
//    		/*
//            if(realKey.equals("emailAccount")) {
//                return testCaseBrief.module.p.config.e;
//            } else if(value.equals("emailPassword")) {
//                return ConfigPhone.getConfig().emailPassword;
//            }
//            */
//        }
//        return key;
//    }

    public String toJsonString() {
        return "{\"target\": \""+this.target+"\", " +
                "\"action\": \""+this.action+"\", " +
                "\"argument\": \""+this.argument+"\", " +
                "\"type\": \""+this.type+"\", " +
                "\"value\": \""+this.value+"\", " +
                "\"pause\": \""+this.pause+"\", " +
                "\"interuptable\": \""+this.interuptable+"\", " +
                "\"isMust\": \""+this.isMust+"\", " +
                "\"conditionType\": \""+this.conditionType+"\", " +
                "\"conditionText\": \""+this.conditionText +"\"" +
                "}";
    }
}

