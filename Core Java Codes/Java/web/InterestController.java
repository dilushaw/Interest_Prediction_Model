/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web;

import java.util.Vector;
import neuralnetwork3.InterestBank;
import neuralnetwork3.InterestHandler;
import neuralnetwork3.Passenger;
import neuralnetwork3.PointHandler;

/**
 *
 * @author Dilusha
 */
public class InterestController {
private InterestBank ib;
    public InterestController(){
         ib=new InterestBank();
    }

    public Vector<String> getMainIntNeuralNet(String age, String nat,  String gen, String prof ){
         PointHandler point=new PointHandler();
        Vector<Vector<Object>> allSportName_url=null;
//if(this.isValidAge(jTextField1.getText())){
   // if(this.isValidProfession(jTextField1.getText(), jComboBox2.getSelectedItem().toString())){
       point.setAge_Str(age);
    point.setNationality_Str(nat);
    point.setGender_Str(gen);
    point.setProfession_Str(prof);

Passenger pas=new Passenger(point.get_DoubleNationality(),point.get_DoubleAge(),point.get_DoubleGender(),point.get_DoubleProfession());
   System.out.println(point.getAge_Doub()+"    "+point.getNationality_Doub()+"    "+point.getGender_Doub()+"    "+point.getProfession_Doub());
pas.setNationalityString(nat);

   InterestHandler ihandler=new InterestHandler(pas);
Vector<Vector<String>> inter=ib.showInterest(pas);

 String allinterests ="";
 
 Vector<String> temp = new Vector<String>();
    for(int i=0; i<inter.size(); i++){
    Vector<String> interest = inter.get(i);
    if(interest.size() >=2){
        temp.add(interest.firstElement());
    }
        }
 return temp;

   // }
//    else{
//        JOptionPane.showMessageDialog(this, "The profession and age mismatch! The people who are less than 15 yrs should be students!");
//    }
//}
//else{
//    JOptionPane.showMessageDialog(this, "The age entered is invalid!! Please check it");
//}
//JOptionPane.showMessageDialog(this,"URLs have been found!\nURLs of the SPORTS:\n"+q);


    }
    public static void main(String[] args){
     InterestController ic = new InterestController();
     System.out.println(ic.getMainIntNeuralNet("17", "country3", "m", "Student"));

 }

}
