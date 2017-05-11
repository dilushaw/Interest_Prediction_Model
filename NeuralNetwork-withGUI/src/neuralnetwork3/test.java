/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neuralnetwork3;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.StringTokenizer;

/**
 *
 * @author Dilusha
 */
public class test {
public static void main(String[] args){
   try
      {
         FileOutputStream fileOut =
         new FileOutputStream("sports/"+"sss"+".ser");
         ObjectOutputStream out =
                            new ObjectOutputStream(fileOut);
         //out.writeObject(this);
         out.close();
          fileOut.close();
          System.out.println("sports_country.ser saved...");
      }catch(IOException i)
      {
          i.printStackTrace();
          System.out.println("sports_country.ser notsaved...");
      }
  }
}

