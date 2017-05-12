/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Group;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author Dilusha
 */
public class Interest implements Serializable{
private String interestname;
private Vector<String> urls;
private String interest_nationality;
private int passengerCount;
    public Interest(String interestname, Vector<String> urls, String interest_nationality) {
        this.interestname = interestname;
        this.urls = urls;
        this.passengerCount = 0;
        this.interest_nationality = interest_nationality;
    }

    public String getInterestname() {
        return interestname;
    }

    public Vector<String> getUrls() {
        return urls;
    }

    public String getInterest_nationality() {
        return interest_nationality;
    }
    
    public boolean addUrl(String url){
      boolean found = false;
        for(int i=0; i<this.urls.size(); i++){
            if(this.urls.get(i).equalsIgnoreCase(url)){
                found = true;
                break;
            }
        }
        if(!found) this.urls.add(url);
        return !found;
    }
    
    public void incrementPassengerCount(){
        this.passengerCount++;
    }

    public void setPassengerCount(int count){
        this.passengerCount = count;
    }
    
    public int getPassengerCount(){
        return this.passengerCount;
    }
public void print(){
    System.out.println("Inerest name: "+this.interestname);
    System.out.println("Urls: "+this.urls);
    System.out.println("Interest nationality:" + this.interest_nationality);
}
}
