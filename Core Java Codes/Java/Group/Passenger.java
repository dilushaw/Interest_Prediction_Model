/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Group;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author Sinthu
 */
public class Passenger implements Serializable{
private String passengerID;
private double age;
private double nationality;
private double gender;
private double prof;
private Vector<String> interests;

    public Passenger(String pasId, double age, double nationality, double gender, double prof) {
        this.age = age;
        this.nationality = nationality;
        this.gender = gender;
        this.prof = prof;
        this.passengerID = pasId;
        interests = new Vector<String>();
    }

    public void addInterest(String ints){
        boolean found = false;
        for(int i=0; i<this.interests.size(); i++){
            if(interests.get(i).equals(ints)){
                found = true;
                break;
            }
        }
        if(!found)this.interests.add(ints);
    }

    public double getAge() {
        return age;
    }

    public double getGender() {
        return gender;
    }

    public Vector<String> getInterests() {
        return interests;
    }

    public double getNationality() {
        return nationality;
    }

    public double getProf() {
        return prof;
    }

    public String getPassengerID() {
        return passengerID;
    }


}
