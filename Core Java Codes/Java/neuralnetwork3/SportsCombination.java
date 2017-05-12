/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neuralnetwork3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author Dilusha
 */
public class SportsCombination implements Serializable{
private String country;
private Vector<String> sports;

public SportsCombination(String ctry){
    this.country = ctry;
    sports = new Vector<String>();
}

public void addSports(String sport){
    if(!this.sports.contains(sport))this.sports.add(sport);
}

public boolean isEqual(SportsCombination sc){
    if(this.country.equals(sc.country)){
        return true;
    }
    else return false;
}

    public String getCountry() {
        return country;
    }

    public Vector<String> getSports() {
        return sports;
    }


public void saveSports_Country(){
        try
      {
         FileOutputStream fileOut =
         new FileOutputStream("sports/"+this.country+".ser");
         ObjectOutputStream out =
                            new ObjectOutputStream(fileOut);
         out.writeObject(this);
         out.close();
          fileOut.close();
          System.out.println("sports"+this.country+" saved...");
      }catch(IOException i)
      {
          i.printStackTrace();
          System.out.println("sports"+this.country+" saved...");
      }

   }

public SportsCombination retrieveBank(String countryName){
     SportsCombination b;
       try
         {
            FileInputStream fileIn =
                          new FileInputStream("sports/"+countryName+".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
             b= (SportsCombination) in.readObject();
            in.close();
            fileIn.close();
            this.country = b.country;
            this.sports = b.sports;
            return b;

        }catch(IOException i)
        {
            i.printStackTrace();
            return null;
        }catch(ClassNotFoundException c)
        {
            System.out.println("sprots class not found");
            c.printStackTrace();
            return null;
        }
   }
}
