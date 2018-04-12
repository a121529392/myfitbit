package myfitbit;

import java.sql.SQLException;
import java.util.Vector;
import models.Check_DateTime_Name_User_Time_Model;
import models.Check_DateTime_Name_User_Model;
import models.Check_DateTime_User_Time_Model;
import myfitbit.config;

public class FitbitDb_Proc {

    public static StringBuffer dayinsert(String date, String user, String data, double value, String startTime, StringBuffer sb, Check_DateTime_Name_User_Model checkdata) throws SQLException {
        java.util.Date time = new java.util.Date();
        StringBuffer sb1 = new StringBuffer("insert into DAY(User_ID, DateTime, ID_Activity, Time, Value, IsMainSleep, StartTime, Frequency,UpdateTime) VALUES ");//insert data , put instruction in stringbuffer
        if (CheckRedundancy.Check_User_DateTime_Name(checkdata, user, date, data)) {
            return sb;

        } else if (startTime.equals("NULL")) {
            if (sb.toString().equals(sb1.toString())) {
                sb.append("(").append('"').append(user).append('"').append(",").append('"').append(date).append('"').append(",").append('"').append(data).append('"').append(",").append("NULL").append(",").append(value).append(",").append('"').append("True").append('"').append(",").append(startTime).append(",").append('"').append("Day").append('"').append(",").append('"').append(time.toString()).append('"').append(")");
            } else {
                sb.append(",(").append('"').append(user).append('"').append(",").append('"').append(date).append('"').append(",").append('"').append(data).append('"').append(",").append("NULL").append(",").append(value).append(",").append('"').append("True").append('"').append(",").append(startTime).append(",").append('"').append("Day").append('"').append(",").append('"').append(time.toString()).append('"').append(")");
            }
        } else if (sb.toString().equals(sb1.toString())) {
            sb.append("(").append('"').append(user).append('"').append(",").append('"').append(date).append('"').append(",").append('"').append(data).append('"').append(",").append("NULL").append(",").append(value).append(",").append('"').append("True").append('"').append(",").append('"').append(startTime).append('"').append(",").append('"').append("Day").append('"').append(",").append('"').append(time.toString()).append('"').append(")");
        } else {
            sb.append(",(").append('"').append(user).append('"').append(",").append('"').append(date).append('"').append(",").append('"').append(data).append('"').append(",").append("NULL").append(",").append(value).append(",").append('"').append("True").append('"').append(",").append('"').append(startTime).append('"').append(",").append('"').append("Day").append('"').append(",").append('"').append(time.toString()).append('"').append(")");
        }

        return sb;
    }

    public static StringBuffer heartinsert(String user, String dateTime, String name, int max, int min, double cal, StringBuffer sb, Check_DateTime_Name_User_Model checkdata) throws SQLException {
        java.util.Date time = new java.util.Date();
        StringBuffer checksb = new StringBuffer("insert into activities_heart(USER_ID,DateTime,hz_name,max,min,caloriesOut,UpdateTime)" + " VALUES ");

        if (CheckRedundancy.Check_User_DateTime_Name(checkdata, user, dateTime, name)) {
            return sb;
        } else {
            if (sb.toString().equals(checksb.toString())) {
                sb.append("(").append('"').append(user).append('"').append(",").append('"').append(dateTime).append('"').append(",").append('"').append(name).append('"').append(",").append(max).append(",").append(min).append(",").append(cal).append(",").append('"').append(time.toString()).append('"').append(")");
            } else {
                sb.append(",(").append('"').append(user).append('"').append(",").append('"').append(dateTime).append('"').append(",").append('"').append(name).append('"').append(",").append(max).append(",").append(min).append(",").append(cal).append(",").append('"').append(time.toString()).append('"').append(")");
            }
            System.out.println(sb);
            return sb;
        }

    }

    public static StringBuffer inheart_second(String user, String date, String Time, double value, String type, StringBuffer sb, Check_DateTime_User_Time_Model checkdata) throws SQLException {
        java.util.Date time = new java.util.Date();
        StringBuffer checksb = new StringBuffer("insert into Heartrate_Detail(User_Id  , DateTime , Time , Value , Frequency,UpdateTime)" + " VALUES ");

        if (CheckRedundancy.Check_DateTime_Time_User(checkdata, user, Time, date)) {
            return sb;
        }
        if (sb.toString().equals(checksb.toString())) {
            sb.append("(").append('"').append(user).append('"').append(",").append('"').append(date).append('"').append(",").append('"').append(Time).append('"').append(",").append(value).append(",").append('"').append(type).append('"').append(",").append('"').append(time.toString()).append('"').append(")");
        } else {
            sb.append(",(").append('"').append(user).append('"').append(",").append('"').append(date).append('"').append(",").append('"').append(Time).append('"').append(",").append(value).append(",").append('"').append(type).append('"').append(",").append('"').append(time.toString()).append('"').append(")");
        }
        return sb;
    }

    public static StringBuffer acinsert(String user, String id, String dateTime, double value, String time, String datasetType, StringBuffer sb, Check_DateTime_Name_User_Time_Model checkdata) throws SQLException {
        java.util.Date time1 = new java.util.Date();
        if (CheckRedundancy.Check_User_DateTime_Name_Time(checkdata, dateTime, id, user, time)) {
            return sb;
        }
        StringBuffer checksb = new StringBuffer("insert into activity_minute(User_ID , ID_ACTIVITY, DateTime ,  Time , Value ,Frequency,UpdateTime)"
                + " VALUES ");

        if (sb.toString().equals(checksb.toString())) {
            sb.append("(").append('"').append(user).append('"').append(",").append('"').append(id).append('"').append(",").append('"').append(dateTime).append('"').append(",").append('"').append(time).append('"').append(",").append(value).append(",").append('"').append(datasetType).append('"').append(",").append('"').append(time1.toString()).append('"').append(")");
        } else {
            sb.append(",(").append('"').append(user).append('"').append(",").append('"').append(id).append('"').append(",").append('"').append(dateTime).append('"').append(",").append('"').append(time).append('"').append(",").append(value).append(",").append('"').append(datasetType).append('"').append(",").append('"').append(time1.toString()).append('"').append(")");
        }
        return sb;
    }

    public static StringBuffer inSleepMinutesData(String dateOfSleep, String logId, String dateTime, double value, String user, StringBuffer sb, Check_DateTime_User_Time_Model checkdata) throws SQLException {
        java.util.Date time = new java.util.Date();
        StringBuffer checksb = new StringBuffer("insert into SLEEP_MINUTE(User_Id , Log_ID , ID_Activity ,  DateTime , Time , Value , Frequency,UpdateTime)" + " VALUES ");

        if (CheckRedundancy.Check_DateTime_Time_User(checkdata, user, dateTime, dateOfSleep)) {
            return sb;
        }
        if (sb.toString().equals(checksb.toString())) {
            sb.append("(").append('"').append(user).append('"').append(",").append('"').append(logId).append('"').append(",").append('"').append("minuteData").append('"').append(",").append('"').append(dateOfSleep).append('"').append(",").append('"').append(dateTime).append('"').append(",").append(value).append(",").append('"').append("minutes").append('"').append(",").append('"').append(time.toString()).append('"').append(")");
        } else {
            sb.append(",(").append('"').append(user).append('"').append(",").append('"').append(logId).append('"').append(",").append('"').append("minuteData").append('"').append(",").append('"').append(dateOfSleep).append('"').append(",").append('"').append(dateTime).append('"').append(",").append(value).append(",").append('"').append("minutes").append('"').append(",").append('"').append(time.toString()).append('"').append(")");
        }
        return sb;

    }

    public static StringBuffer deviceinsert(String user, String date, String battery, String deviceVersion, String id, String lastSyncTime, String mac, String type, StringBuffer sb, String[] checkdata) throws SQLException {
        java.util.Date time = new java.util.Date();
        StringBuffer checksb = new StringBuffer("insert into Devices(User_ID, DateTime, Battery, DeviceVersion, ID, LastSyncTime, Mac, Type,UpdateTime)" + " VALUES ");

        if (CheckRedundancy.Check_Devices(checkdata, id)) {
            StringBuffer updatesb = new StringBuffer("UPDATE Devices set Battery = ").append('"').append(battery).append('"').append(",").append("UpdateTime=").append('"').append(time.toString()).append('"')
                    .append("where ID=").append('"').append(id).append('"');
            sb = updatesb;
            return sb;

        } else {
            if (sb.toString().equals(checksb.toString())) {
                sb.append("(").append('"').append(user).append('"').append(",").append('"').append(date).append('"').append(",").append('"').append(battery).append('"').append(",").append('"').append(deviceVersion).append('"')
                        .append(",").append('"').append(id).append('"').append(",").append('"').append(lastSyncTime).append('"').append(",").append('"').append(mac).append('"').append(",").append('"').append(type).append('"')
                        .append(",").append('"').append(time.toString()).append('"').append(")");
            } else {
                sb.append(",(").append('"').append(user).append('"').append(",").append('"').append(date).append('"').append(",").append('"').append(battery).append('"').append(",").append('"').append(deviceVersion)
                        .append('"').append(",").append('"').append(id).append('"').append(",").append('"').append(lastSyncTime).append('"').append(",").append('"').append(mac).append('"').append(",").append('"')
                        .append(type).append('"').append(",").append('"').append(time.toString()).append('"').append(")");
            }
            return sb;
        }
    }

}
