/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neuralnetwork3;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dilusha
 */
public class InterestHandler {
    Passenger pas;
   public InterestHandler(Passenger pas)  {
   this.pas=pas;

   }
  private  String interestName;
   private Vector<String> output;
public void setInterest_name(String name){
this.interestName=name;
}

public void setInterest_output(Vector<String> output){
this.output=output;
}


public boolean checkInterest() throws UnsupportedEncodingException, MalformedURLException{

if(interestName.equalsIgnoreCase("Sports")){
  
System.out.println("sports foundd");
return true;
}

 else{
//     WebSearch web=new WebSearch(pas.getNationalityString(),this.interestName,this.output) ;
// Vector<String> webInterest=web.handleEachInterest();
// for(int i=0;i<webInterest.size();i++){
//                try {
//                  GoogleResults gr=( web.getURL(webInterest.get(i))) ;
//                  web.searchResults(gr);
//                } catch (IOException ex) {
//                    Logger.getLogger(InterestHandler.class.getName()).log(Level.SEVERE, null, ex);
//                }
//          }
return false;
 }



}

    public String getInterestName() {
        return interestName;
    }

    public Vector<String> getOutput() {
        return output;
    }




}
