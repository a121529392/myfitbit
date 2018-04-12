package myfitbit;

import java.io.IOException;
import org.json.JSONException;
import java.util.*;

public class Fitbit {

    public static void main(String user, String date, String start, String end, String dataType, String id, String endDate, String period) throws IOException, JSONException, Exception {
        Date d = new Date();
        System.out.println("S: " + user);
        System.out.println("S: " + date);
        System.out.println("S: " + start);
        System.out.println("S: " + end);
        System.out.println("S: " + dataType);
        config c = new config();
        MySQLConnector sql = new MySQLConnector(c.getUrlstr(), c.getUserStr(), c.getPwStr(), c.getDBname());
        sql.connectDB();
        sql.doQuery("select access_token from access where account =  '" + user + "' ");
        StringBuffer rs = sql.getResultString();
        String str = rs.toString();
        System.out.println("S: " + str.length());

        str = str.substring(15, 288);
        System.out.println("S: " + str);
        GetHR HR;
        GetSLEEPDATA SLEEP;
        GetSLEEPDATA SLEEP2;
        GetACT ACT;
        GetDevice DEVICE;
        String ans;

        if ("HR".equals(dataType)) {
            if (start.isEmpty()) {
                HR = new GetHR(date, period);
                ans = HR.Sendget(str);
                Fitbit_Decorder.decode_heartrate(ans, user);
            } else {
                HR = new GetHR(date, start, end, period);
                ans = HR.Sendget(str);
                Fitbit_Decorder.decode_intraday(ans, user);
            }
        }
        if ("ACTIVITY".equals(dataType)) {
            if (start.isEmpty()) {
                ACT = new GetACT(date);
                ans = ACT.Sendget(str);

                Fitbit_Decorder.decode_activities(user, ans, date);
            } else {
                ACT = new GetACT(date, start, end, id, period);
                ans = ACT.Sendget(str);
                Fitbit_Decorder.decode_minute_ac(ans, user, id);
            }
        }
        if ("SLEEP".equals(dataType)) {
           /* SLEEP = new GetSLEEPDATA(date, endDate,"1.2");
            ans = SLEEP.Sendget(str);
            String res;
            res=new DataRequester().send(ans);*/
            String answer;
            SLEEP2 = new GetSLEEPDATA(date);
            answer = SLEEP2.Sendget(str);
            Fitbit_Decorder.decode_sleep(answer, user);
        }
       // DEVICE = new GetDevice(date);
      //  ans = DEVICE.Sendget(str);
       // Fitbit_Decorder.decode_device(user,ans,date);
        return;

    }
}
