/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neuralnetwork3;

/**
 *
 * @author Dilusha
 */
public class PointHandler {
private String age_Str,nationality_Str,gender_Str,profession_Str;
private double age_Doub,nationality_Doub,gender_Doub,profession_Doub;

    public double getAge_Doub() {
        return age_Doub;
    }

    public void setAge_Doub(double age_Doub) {
        this.age_Doub = age_Doub;
    }

    public double getGender_Doub() {
        return gender_Doub;
    }

    public void setGender_Doub(double gender_Doub) {
        this.gender_Doub = gender_Doub;
    }

    public double getNationality_Doub() {
        return nationality_Doub;
    }

    public void setNationality_Doub(double nationality_Doub) {
        this.nationality_Doub = nationality_Doub;
    }

    public double getProfession_Doub() {
        return profession_Doub;
    }

    public void setProfession_Doub(double profession_Doub) {
        this.profession_Doub = profession_Doub;
    }

    public void setAge_Str(String age_Str) {
        this.age_Str = age_Str;
    }

    public void setGender_Str(String gender_Str) {
        this.gender_Str = gender_Str;
    }

    public void setNationality_Str(String nationality_Str) {
        this.nationality_Str = nationality_Str;
    }

    public void setProfession_Str(String profession_Str) {
        this.profession_Str = profession_Str;
    }

public double get_DoubleAge(){
    int intAge = Integer.parseInt(age_Str);
    if(intAge<10) this.age_Doub = 0.1;
    else if(intAge>60) this.age_Doub = 0.6;
    else{
 this.age_Doub = (Double.parseDouble(this.age_Str))/100;
    }
 return this.age_Doub;

}
public double get_DoubleNationality(){
    this.nationality_Doub=0.36;
    return 0.36;
    
}

public double get_DoubleGender(){
    double a=0.0;
  if(this.gender_Str.equalsIgnoreCase("f")){
     a=0.30;
     this.gender_Doub=0.30;
  }
 else  {
   a=0.60;
     this.gender_Doub=0.60;
 }
 return a;
}

public double get_DoubleProfession(){
String a=this.profession_Str;
double temp=0.0;
if(a.equalsIgnoreCase("Not Working")){
    this.profession_Doub=0.06;
    temp=0.06;
   }
 else if(a.equalsIgnoreCase("Student")){
     this.profession_Doub=0.12;
     temp=0.12;
 }
else if(a.equalsIgnoreCase("Under Graduate Student")){
    this.profession_Doub=0.18;
     temp=0.18;
 }
else if(a.equalsIgnoreCase("Doctor")){
    this.profession_Doub=0.24;
     temp=0.24;
 }
 else if(a.equalsIgnoreCase("Lecturer")){
     this.profession_Doub=0.28;
     temp=0.28;
 }
else if(a.equalsIgnoreCase("Engineer")){
    this.profession_Doub=0.30;
     temp=0.30;
 }

else if(a.equalsIgnoreCase("Business Person")){
    this.profession_Doub=0.36;
     temp=0.36;
 }

else if(a.equalsIgnoreCase("Accountant")){
    this.profession_Doub=0.42;
     temp=0.42;
 }
else if(a.equalsIgnoreCase("Lawyer")){
    this.profession_Doub=0.48;
     temp=0.48;
 }

else if(a.equalsIgnoreCase("Artist")){
    this.profession_Doub=0.60;
     temp=0.60;
 }

else if(a.equalsIgnoreCase("Politician")){
    this.profession_Doub=0.54;
     temp=0.54;
 }
else {
    this.profession_Doub=0.60;
     temp=0.60;
 }
return temp;

}



}
