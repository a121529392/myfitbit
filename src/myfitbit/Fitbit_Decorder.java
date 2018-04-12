package myfitbit;

import Uniplat.Uniplat_ActivityModel;
import models.Fitbit_HeartrateDetailModel;
import models.Fitbit_HeartrateModel;
import models.Fitbit_ActivityModel;
import models.Fitbit_SleepModel;
import models.Fitbit_ActivityDetailModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.reflect.Type;
import Uniplat.Uniplat_GetData;
import java.util.*;
import models.Check_DateTime_Name_User_Time_Model;
import models.Check_DateTime_Name_User_Model;
import models.Check_DateTime_User_Time_Model;
import models.Fitbit_DeviceModel;

public class Fitbit_Decorder {

    public static void decode_activities(String user, String response, String date) throws JSONException, SQLException {
        Gson gson = new Gson();
        config c = new config();
        Check_DateTime_Name_User_Model checkdata = new Check_DateTime_Name_User_Model();
        checkdata = MySQLConnector.Check_Fitbit_DAY(user,date);

        Fitbit_ActivityModel data = gson.fromJson(response, Fitbit_ActivityModel.class);//decode json
        Uniplat_GetData.Fitbitac_to_Uniac(user, data, date);//decode fitbit data to uniplat
        StringBuffer sb = new StringBuffer("insert into DAY(User_ID, DateTime, ID_Activity, Time, Value, IsMainSleep, StartTime, Frequency,UpdateTime) VALUES ");//insert data , put instruction in stringbuffer
        StringBuffer checksb = new StringBuffer("insert into DAY(User_ID, DateTime, ID_Activity, Time, Value, IsMainSleep, StartTime, Frequency,UpdateTime) VALUES ");

        sb = FitbitDb_Proc.dayinsert(date, user, "activeScore", data.summary.getActiveScore(), "NULL", sb, checkdata);
        sb = FitbitDb_Proc.dayinsert(date, user, "activityCalories", data.summary.getActivityCalories(), "NULL", sb, checkdata);
        sb = FitbitDb_Proc.dayinsert(date, user, "caloriesBMR", data.summary.getCaloriesBMR(), "NULL", sb, checkdata);
        sb = FitbitDb_Proc.dayinsert(date, user, "elevation", data.summary.getElevation(), "NULL", sb, checkdata);
        sb = FitbitDb_Proc.dayinsert(date, user, "fairlyActiveMinutes", data.summary.getFairlyActiveMinutes(), "NULL", sb, checkdata);
        sb = FitbitDb_Proc.dayinsert(date, user, "floors", data.summary.getFloors(), "NULL", sb, checkdata);
        sb = FitbitDb_Proc.dayinsert(date, user, "lightlyActiveMinutes", data.summary.getLightlyActiveMinutes(), "NULL", sb, checkdata);
        sb = FitbitDb_Proc.dayinsert(date, user, "marginalCalories", data.summary.getMarginalCalories(), "NULL", sb, checkdata);
        sb = FitbitDb_Proc.dayinsert(date, user, "restingHeartRate", data.summary.getRestingHeartRate(), "NULL", sb, checkdata);
        sb = FitbitDb_Proc.dayinsert(date, user, "sedentaryMinutes", data.summary.getSedentaryMinutes(), "NULL", sb, checkdata);
        sb = FitbitDb_Proc.dayinsert(date, user, "steps", data.summary.getSteps(), "NULL", sb, checkdata);
        sb = FitbitDb_Proc.dayinsert(date, user, "veryActiveMinutes", data.summary.getVeryActiveMinutes(), "NULL", sb, checkdata);
        if (!sb.toString().equals(checksb.toString())) {
            MySQLConnector.insertdata(sb, c);//connect mysQl , insert data to mysQl
        }
    }

    public static void decode_minute_ac(String response, String user, String id) throws JSONException, SQLException {

        Gson gson = new Gson();
        config c = new config();
        Fitbit_ActivityDetailModel data = gson.fromJson(response, Fitbit_ActivityDetailModel.class);
        Uniplat_GetData.Fitbitminac_to_Uniminac(user, data, id);
        Check_DateTime_Name_User_Time_Model checkdata = new Check_DateTime_Name_User_Time_Model();
        checkdata = MySQLConnector.Check_Fitbit_activities_minute(user,id);
        StringBuffer sb = new StringBuffer("insert into activity_minute(User_ID , ID_ACTIVITY, DateTime ,  Time , Value ,Frequency,UpdateTime)"
                + " VALUES ");
        StringBuffer checksb = new StringBuffer("insert into activity_minute(User_ID , ID_ACTIVITY, DateTime ,  Time , Value ,Frequency,UpdateTime)"
                + " VALUES ");
        if ("calories".equals(id)) {//cause detaildata have many choice , so we must choice one
            for (int i = 0; i < data.calories_detail.dataset.size(); i++) {
                sb = FitbitDb_Proc.acinsert(user, id, data.calories.get(0).getDateTime(), data.calories_detail.dataset.get(i).getValue(), data.calories_detail.dataset.get(i).getTime(), data.calories_detail.getdatasetType(), sb, checkdata);
            }
        }
        if ("steps".equals(id)) {

            for (int i = 0; i < data.steps_detail.dataset.size(); i++) {
                sb = FitbitDb_Proc.acinsert(user, id, data.steps.get(0).getDateTime(), data.steps_detail.dataset.get(i).getValue(), data.steps_detail.dataset.get(i).getTime(), data.steps_detail.getdatasetType(), sb, checkdata);
            }
        }
        if ("distance".equals(id)) {
            for (int i = 0; i < data.distance_detail.dataset.size(); i++) {
                sb = FitbitDb_Proc.acinsert(user, id, data.distance.get(0).getDateTime(), data.distance_detail.dataset.get(i).getValue(), data.distance_detail.dataset.get(i).getTime(), data.distance_detail.getdatasetType(), sb, checkdata);
            }
        }
        if ("floors".equals(id)) {
            for (int i = 0; i < data.floors_detail.dataset.size(); i++) {
                sb = FitbitDb_Proc.acinsert(user, id, data.floors.get(0).getDateTime(), data.floors_detail.dataset.get(i).getValue(), data.floors_detail.dataset.get(i).getTime(), data.floors_detail.getdatasetType(), sb, checkdata);
            }
        }
        if ("elevation".equals(id)) {
            for (int i = 0; i < data.elevation_detail.dataset.size(); i++) {
                sb = FitbitDb_Proc.acinsert(user, id, data.elevation.get(0).getDateTime(), data.elevation_detail.dataset.get(i).getValue(), data.elevation_detail.dataset.get(i).getTime(), data.elevation_detail.getdatasetType(), sb, checkdata);
            }
        }
        if (!sb.toString().equals(checksb.toString())) {
            MySQLConnector.insertdata(sb, c);//insert data
        }

    }

    public static void decode_heartrate(String response, String user) throws JSONException, SQLException {
        StringBuffer sb = new StringBuffer("insert into activities_heart(USER_ID,DateTime,hz_name,max,min,caloriesOut,UpdateTime)" + " VALUES ");
        StringBuffer checksb = new StringBuffer("insert into activities_heart(USER_ID,DateTime,hz_name,max,min,caloriesOut,UpdateTime)" + " VALUES ");
        Gson gson = new Gson();
        config c = new config();
        Check_DateTime_Name_User_Model checkdata = new Check_DateTime_Name_User_Model();
        
        Fitbit_HeartrateModel data = gson.fromJson(response, Fitbit_HeartrateModel.class);
        Uniplat_GetData.FitbitHeatrate_to_UniHeatrate(data, user);//decode fitbit data to uniplat
        String datetime=null;
        for (int i = 0; i < data.heart.size(); i++) {//decode data to stringbuffer
            checkdata = MySQLConnector.Check_Fitbit_activities_heart(user, data.heart.get(i).getDateTime());
            for (int j = 0; j < data.heart.get(i).value.heartRateZones.size(); j++) {
              
                sb = FitbitDb_Proc.heartinsert(user, data.heart.get(i).getDateTime(), data.heart.get(i).value.heartRateZones.get(j).getName(), data.heart.get(i).value.heartRateZones.get(j).getMax(), data.heart.get(i).value.heartRateZones.get(j).getMin(), data.heart.get(i).value.heartRateZones.get(j).getCaloriesOut(), sb, checkdata);
            }
        }
        if (!sb.toString().equals(checksb.toString())) {
            MySQLConnector.insertdata(sb, c);//insert data
        }
    }

    public static void decode_intraday(String response, String user) throws JSONException, SQLException {
        StringBuffer sb = new StringBuffer("insert into Heartrate_Detail(User_Id  , DateTime , Time , Value , Frequency,UpdateTime)" + " VALUES ");
        StringBuffer checksb = new StringBuffer("insert into Heartrate_Detail(User_Id  , DateTime , Time , Value , Frequency,UpdateTime)" + " VALUES ");
        Gson gson = new Gson();
        config c = new config();
        Check_DateTime_User_Time_Model checkdata = new Check_DateTime_User_Time_Model();
       
        Fitbit_HeartrateDetailModel data = gson.fromJson(response, Fitbit_HeartrateDetailModel.class);
        Uniplat_GetData.FitbitDetailHeatrate_to_UniDetailHeatrate(data, user);
        checkdata = MySQLConnector.Check_Fitbit_Heartrate_Detail(user, data.heart.get(0).getDateTime());
        for (int k = 0; k < data.heartrateDetail.dataset.size(); k++) {
         
            sb = FitbitDb_Proc.inheart_second(user, data.heart.get(0).getDateTime(), data.heartrateDetail.dataset.get(k).getTime(), data.heartrateDetail.dataset.get(k).getValue(), data.heartrateDetail.getDatasetType(), sb, checkdata);
        }
        if (!sb.toString().equals(checksb.toString())) {
            MySQLConnector.insertdata(sb, c);//insert data
        }
        //   CheckRedundancy.Check_Fitbit_Heartrate_Detail();
    }

    public static void decode_sleep(String response, String user) throws JSONException, SQLException {
        Gson gson = new Gson();
        config c = new config();
        Fitbit_SleepModel data = gson.fromJson(response, Fitbit_SleepModel.class);
        StringBuffer sb = new StringBuffer("insert into DAY(User_ID, DateTime, ID_Activity, Time, Value, IsMainSleep, StartTime, Frequency,UpdateTime) VALUES ");//insert data , put instruction in stringbuffer
        StringBuffer sb1 = new StringBuffer("insert into SLEEP_MINUTE(User_Id , Log_ID , ID_Activity ,  DateTime , Time , Value , Frequency,UpdateTime)" + " VALUES ");
        StringBuffer checksb1 = new StringBuffer("insert into SLEEP_MINUTE(User_Id , Log_ID , ID_Activity ,  DateTime , Time , Value , Frequency,UpdateTime)" + " VALUES ");
        StringBuffer checksb = new StringBuffer("insert into DAY(User_ID, DateTime, ID_Activity, Time, Value, IsMainSleep, StartTime, Frequency,UpdateTime) VALUES ");//insert data , put instruction in stringbuffer

        Check_DateTime_User_Time_Model checkdata1 = new Check_DateTime_User_Time_Model();
        
        Check_DateTime_Name_User_Model checkdata = new Check_DateTime_Name_User_Model();
        
        Uniplat_GetData.FitbitSleep_to_UniSleep(data, user);
        for (int i = 0; i < data.sleep.size(); i++) {
            checkdata = MySQLConnector.Check_Fitbit_DAY(user,data.sleep.get(i).getDateOfSleep());
            sb = FitbitDb_Proc.dayinsert(data.sleep.get(i).getDateOfSleep(), user, "awakeCount", data.sleep.get(i).getAwakeCount(), data.sleep.get(i).getStartTime(), sb, checkdata);
            sb = FitbitDb_Proc.dayinsert(data.sleep.get(i).getDateOfSleep(), user, "awakeningsCount", data.sleep.get(i).getAwakeningsCount(), data.sleep.get(i).getStartTime(), sb, checkdata);
            sb = FitbitDb_Proc.dayinsert(data.sleep.get(i).getDateOfSleep(), user, "duration", data.sleep.get(i).getDuration(), data.sleep.get(i).getStartTime(), sb, checkdata);
            sb = FitbitDb_Proc.dayinsert(data.sleep.get(i).getDateOfSleep(), user, "awakeDuration", data.sleep.get(i).getAwakeDuration(), data.sleep.get(i).getStartTime(), sb, checkdata);
            sb = FitbitDb_Proc.dayinsert(data.sleep.get(i).getDateOfSleep(), user, "efficiency", data.sleep.get(i).getEfficiency(), data.sleep.get(i).getStartTime(), sb, checkdata);
            sb = FitbitDb_Proc.dayinsert(data.sleep.get(i).getDateOfSleep(), user, "minutesAfterWakeup", data.sleep.get(i).getMinutesAfterWakeup(), data.sleep.get(i).getStartTime(), sb, checkdata);
            sb = FitbitDb_Proc.dayinsert(data.sleep.get(i).getDateOfSleep(), user, "minutesAsleep", data.sleep.get(i).getMinutesAsleep(), data.sleep.get(i).getStartTime(), sb, checkdata);
            sb = FitbitDb_Proc.dayinsert(data.sleep.get(i).getDateOfSleep(), user, "minutesAwake", data.sleep.get(i).getMinutesAwake(), data.sleep.get(i).getStartTime(), sb, checkdata);
            sb = FitbitDb_Proc.dayinsert(data.sleep.get(i).getDateOfSleep(), user, "minutesToFallAsleep", data.sleep.get(i).getMinutesToFallAsleep(), data.sleep.get(i).getStartTime(), sb, checkdata);
            sb = FitbitDb_Proc.dayinsert(data.sleep.get(i).getDateOfSleep(), user, "restlessCount", data.sleep.get(i).getRestlessCount(), data.sleep.get(i).getStartTime(), sb, checkdata);
            sb = FitbitDb_Proc.dayinsert(data.sleep.get(i).getDateOfSleep(), user, "restlessDuration", data.sleep.get(i).getRestlessDuration(), data.sleep.get(i).getStartTime(), sb, checkdata);
           checkdata1 = MySQLConnector.Check_Fitbit_SLEEP_MINUTE(user,data.sleep.get(i).getDateOfSleep());
            for (int j = 0; j < data.sleep.get(i).minuteData.size(); j++) {
                
                sb1 = FitbitDb_Proc.inSleepMinutesData(data.sleep.get(i).getDateOfSleep(), data.sleep.get(i).getLogId(), data.sleep.get(i).minuteData.get(j).getDateTime(), data.sleep.get(i).minuteData.get(j).getValue(), user, sb1, checkdata1);
            }

        }
        if (!sb.toString().equals(checksb.toString())) {
            System.out.println(sb);
            MySQLConnector.insertdata(sb, c);//insert data
        }
        if (!sb1.toString().equals(checksb1.toString())) {
            System.out.println(sb1);
            MySQLConnector.insertdata(sb1, c);//insert data
        }

    }

    public static void decode_device(String user, String response, String date) throws JSONException, SQLException {
        StringBuffer sb = new StringBuffer("insert into Devices(User_ID, DateTime, Battery, DeviceVersion, ID, LastSyncTime, Mac, Type,UpdateTime)" + " VALUES ");//insert data , put instruction in stringbuffer
        StringBuffer checksb = new StringBuffer("insert into Devices(User_ID, DateTime, Battery, DeviceVersion, ID, LastSyncTime, Mac, Type,UpdateTime)" + " VALUES ");
        Gson gson = new Gson();
        config c = new config();
        String[] checkdata;
        checkdata = MySQLConnector.Check_devices();
        Fitbit_DeviceModel[] data = gson.fromJson(response, Fitbit_DeviceModel[].class);//decode json
        Uniplat_GetData.FitbitDevices_to_UniDevices(user, data, date);//decode fitbit data to uniplat
        for (int i = 0; i < data.length; i++) {
            sb = FitbitDb_Proc.deviceinsert(user, date, data[i].getBattery(), data[i].getDeviceVersion(), data[i].getID(), data[i].getLastSyncTime(), data[i].getMac(), data[i].getType(), sb, checkdata);
        }
        if (!sb.toString().equals(checksb.toString())) {
            System.out.println(sb);
            MySQLConnector.insertdata(sb, c);//insert data
        }

    }
}
