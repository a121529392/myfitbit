/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfitbit;


public class GetHR extends GetData {
    
    public GetHR(String date, String period) {
        //period	The range for which data will be returned. Options are 1d, 7d, 30d, 1w, 1m
        System.out.println("HR");
        url = "https://api.fitbit.com/1/user/-/activities/heart/date/"+date+"/"+period+".json";     
    }

    public GetHR(String date, String startime, String endtime,String period) {
        url = "https://api.fitbit.com/1/user/-/activities/heart/date/"+ date +"/1d/"+period+"/time/" + startime + "/"+ endtime + ".json";
    }
}
