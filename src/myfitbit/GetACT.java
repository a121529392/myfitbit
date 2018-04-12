/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfitbit;


public class GetACT extends GetData {
    
    public GetACT(String date, String starttime, String endtime,String id,String period) {
        url = "https://api.fitbit.com/1/user/-/activities/"+id+"/date/"+date+"/1d/"+period+"/time/"+starttime+"/"+endtime+".json";
    }

    public GetACT(String date) {
        System.out.println("ACT:");
        url = " https://api.fitbit.com/1/user/-/activities/date/" + date + ".json";
    }
}
