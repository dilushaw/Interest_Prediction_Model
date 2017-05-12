/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neuralnetwork3;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author Dilusha
 */
public class CountryNationalityHandler implements Serializable{
private Vector<String> nationality;
private Vector<String> country;

private void processFile(){
    try{
  FileInputStream fstream = new FileInputStream("countryAndNationality.txt");
  // Get the object of DataInputStream
  DataInputStream in = new DataInputStream(fstream);
  BufferedReader br = new BufferedReader(new InputStreamReader(in));
  String strLine;
  while ((strLine = br.readLine()) != null)   {
    String[] words = strLine.split("#");
    String ctry = words[words.length-1].trim();
    String allNats = words[0];

    String[] nats = allNats.split("&");

   for(int i=0; i<nats.length; i++){
       this.nationality.add(nats[i].trim());
       this.country.add(ctry);
   }
  }
  in.close();
    }catch (Exception e){
  System.err.println("Error: " + e.getMessage());
  }
  }

    public CountryNationalityHandler() {
    this.country = new Vector<String>();
    this.nationality = new Vector<String>();
    }

public static void main(String[] args){
    CountryNationalityHandler c= new CountryNationalityHandler();
    c.processFile();
    // c = c.retrieveCombination();
    //System.out.println(c.getCountry("nigerien"));
   // c.printCountryAndNationality();
 c.saveCountry_Nationality();
}

public Vector<String> getNationality(){
    this.retrieveCombination();
    return this.nationality;
}

public Vector<String> getDistictCountries(){
    Vector<String> c = new Vector<String>();
    this.retrieveCombination();
    for(int i=0; i<country.size(); i++){
        if(!c.contains(country.get(i))){
            c.add(country.get(i));
        }
    }
    return c;
}

public String getCountry(String nat){
    this.retrieveCombination();
    for(int i=0; i<this.nationality.size(); i++){
        if(this.nationality.get(i).equalsIgnoreCase(nat)){
            return this.country.get(i);
        }
    }
    return null;
}

public void printCountryAndNationality(){
    for(int i=0; i<this.nationality.size(); i++){
        System.out.println(nationality.get(i)+" : "+ this.country.get(i));
    }
}

private void saveCountry_Nationality(){
    try
      {
         FileOutputStream fileOut = new FileOutputStream("country_nationality.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(this);
         out.close();
          fileOut.close();
          System.out.println("country_nationality.ser saved..");
      }catch(IOException i)
      {
          i.printStackTrace();
          System.out.println("country_nationality.ser notsaved...");
      }
}

private CountryNationalityHandler retrieveCombination(){
    try
         {
            FileInputStream fileIn =new FileInputStream("country_nationality.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
             CountryNationalityHandler b= (CountryNationalityHandler) in.readObject();
             this.country = b.country;
             this.nationality = b.nationality;
            in.close();
            fileIn.close();
            return b;

        }catch(IOException i)
        {
            i.printStackTrace();
            return null;
        }catch(ClassNotFoundException c)
        {
            System.out.println("CountryNationalityHandler not found");
            c.printStackTrace();
            return null;
        }
}

public String getCountry(int natIndex){
    this.retrieveCombination();
    System.out.println(natIndex);
    return this.country.get(natIndex);
}

}

