/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neuralnetwork3;

/**
 *
 * @author Dilusha
 */
public class Passenger {
private double nationality,age,gender,profession;
private String nationalityString;

public Passenger(double nationality,double age,double gender,double profession){
  this.age=age;
  this.nationality=nationality;
  this.gender=gender;
  this.profession=profession;

}


    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public double getGender() {
        return gender;
    }

    public void setGender(double gender) {
        this.gender = gender;
    }

    public double getNationality() {
        return nationality;
    }

    public void setNationality(double nationality) {
        this.nationality = nationality;
    }

    public double getProfession() {
        return profession;
    }

    public void setProfession(double profession) {
        this.profession = profession;
    }

    public String getNationalityString() {
        return nationalityString;
    }

    public void setNationalityString(String nationalityString) {
        this.nationalityString = nationalityString;
    }



}
