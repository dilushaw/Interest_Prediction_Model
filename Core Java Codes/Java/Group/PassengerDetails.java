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
public class PassengerDetails implements Serializable{
private Passenger pas;
private String interestName;
private Vector<String> urls;
private String interest_nationality;

    public PassengerDetails(Passenger pas, String interestName, String interest_nationality, Vector<String> urls) {
        this.pas = pas;
        this.interestName = interestName;
        this.urls = urls;
        this.interest_nationality = interest_nationality;
    }

    public String getInterestName() {
        return interestName;
    }

    public Passenger getPas() {
        return pas;
    }

    public Vector<String> getUrls() {
        return urls;
    }

    public String getInterest_nationality() {
        return interest_nationality;
    }


}
