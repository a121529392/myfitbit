/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfitbit;


public class GetDevice extends GetData {
    
    public GetDevice(String date) {
        System.out.println("Device:");
        url = "https://api.fitbit.com/1/user/-/devices.json";
    }
}
