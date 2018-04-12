/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfitbit;


public class GetSLEEPDATA extends GetData {
    
    public GetSLEEPDATA(String date) {
        System.out.println("SLEEP");
        url = " https://api.fitbit.com/1/user/-/sleep/date/" + date + ".json";
    }

}
