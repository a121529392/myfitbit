/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfitbit;

import models.Check_DateTime_Name_User_Model;
import java.sql.SQLException;
import java.util.Vector;
import models.Check_DateTime_Name_User_Time_Model;
import models.Check_DateTime_User_Time_Model;
import static org.json.JSONObject.NULL;

/**
 *
 * @author Yang
 */
public class CheckRedundancy {

    static boolean Check_User_DateTime_Name(Check_DateTime_Name_User_Model data, String user, String date, String name) throws SQLException {
        if (data.dateTime.length == 0) {
            return false;
        }

        for (int i = 0; i < data.dateTime.length; i++) {
            if (data.dateTime[i].equals(date) && data.name[i].equals(name) && data.user[i].equals(user)) {
                return true;
            }
        }
        return false;

    }

    static boolean Check_Devices(String[] data,String id) throws SQLException {
        if (data.length == 0) {
            return false;
        }

        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(id)) {
                return true;
            }
        }
        return false;

    }

    static boolean Check_User_DateTime_Name_Time(Check_DateTime_Name_User_Time_Model data, String DateTime, String ID_ACTIVITY, String User_ID, String time) throws SQLException {
        if (data.dateTime.length == 0) {
            return false;
        }
        for (int i = 0; i < data.dateTime.length; i++) {
            if (data.dateTime[i].equals(DateTime) && data.name[i].equals(ID_ACTIVITY) && data.user[i].equals(User_ID) && data.time[i].equals(time)) {
                return true;
            }
        }
        return false;

    }

    static boolean Check_DateTime_Time_User(Check_DateTime_User_Time_Model data, String user, String time, String datetime) throws SQLException {
        if (data.dateTime.length == 0) {
            return false;
        }
        for (int i = 0; i < data.dateTime.length; i++) {
            if (data.dateTime[i].equals(datetime) && data.time[i].equals(time) && data.user[i].equals(user)) {
                return true;
            }
        }

        return false;

    }

    static boolean Check_Fitbit_access(String[] user, String id) throws SQLException {
        if (user.length == 0) {
            return false;
        }
        for (int i = 0; i < user.length; i++) {
            if (user[i].equals(id)) {
                return true;
            }
        }

        return false;
    }

}
