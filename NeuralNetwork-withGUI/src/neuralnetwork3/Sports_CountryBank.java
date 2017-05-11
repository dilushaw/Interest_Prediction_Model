package neuralnetwork3;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Spring;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sinthu
 */
public class Sports_CountryBank implements Serializable{
private Vector<SportsCombination> combs;
private Vector<SportsCombination> noSportsCombs;

public Sports_CountryBank(){
    this.combs = new Vector<SportsCombination>();
    this.noSportsCombs = new Vector<SportsCombination>();
}

    public static void main(String[] args){

//     Sports_CountryBank b= new Sports_CountryBank();
//     b.createBank();
   // start = Bahamas;
        boolean proceed = false;
    Sports_CountryBank bank = new Sports_CountryBank();
    CountryNationalityHandler c = new CountryNationalityHandler();
    Vector<String> contries = c.getDistictCountries();
//
//    for(int i=0; i<contries.size(); i++){
//        String name = contries.get(i);
//        SportsCombination s= new SportsCombination(name);
//        s = s.retrieveBank(name);
//        if(s.getSports().size() == 0){
//            bank.noSportsCombs.add(s);
//        }
//        else bank.addSportCombination(s);
//    }
//
//    for(int i=0; i<bank.noSportsCombs.size(); i++){
//        System.out.println(bank.noSportsCombs.get(i).getCountry());
//    }

//    Vector<String> contries = new Vector<String>();
//     contries.add("Sri Lanka");

    contries = new Vector<String>();
//    Burundi
//Congo
//Fiji
//Gambia
//Guyana
//Laos
//Liechtenstein
//Maldives
//Mongolia
//Norway
//Oman
//Swaziland
//Tuvali
//United Arab Emirates
//Yugoslavia
//Zaire
//    contries.add("Burundi");
//    contries.add("Congo");
//    contries.add("Fiji");
//    contries.add("Gambia");
//    contries.add("Guyana");
//    contries.add("Laos");
//    contries.add("Liechtenstein");
//    contries.add("Maldives");
//    contries.add("Mongolia");
//    contries.add("Norway");
//    contries.add("Oman");
 //   contries.add("Swaziland");
//    contries.add("Tuvali");
//    contries.add("United Arab Emirates");
 //    contries.add("Yugoslavia");
     contries.add("Zaire");

    try{
    for(int i=0; i<contries.size(); i++){
        if(contries.get(i).equals("Zaire")){
            proceed = true;
        }
        if(proceed){
        System.out.println("######################################################################");
        System.out.println(contries.get(i));
            try {
                SportHandler sport = new SportHandler(contries.get(i));
                Vector<Vector<Object>> allSportName_url = sport.handleURL();
                SportsCombination s = new SportsCombination(contries.get(i));
                for(int j=0; j<allSportName_url.size(); j++){
                    s.addSports(allSportName_url.get(j).get(0).toString());
                }
                s.saveSports_Country();
                bank.addSportCombination(s);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Sports_CountryBank.class.getName()).log(Level.SEVERE, null, ex);
               // bank.saveSports_CountryBank();
            } catch (MalformedURLException ex) {
                Logger.getLogger(Sports_CountryBank.class.getName()).log(Level.SEVERE, null, ex);
               // bank.saveSports_CountryBank();
            } catch(Exception ex){
               // bank.saveSports_CountryBank();
                System.out.println(ex.toString());
            }
        }
    }
    bank.saveSports_CountryBank();
   }
   catch(Exception ex){
     // bank.saveSports_CountryBank();
       System.out.println("Exception......");
   }

    
//    Sports_CountryBank s = bank.retrieveBank();
//    System.out.println(s.getSports("Sri Lanka"));

}

   private void addSportCombination(SportsCombination s){
       this.combs.add(s);
   }

   private void saveSports_CountryBank(){
        try
      {
         FileOutputStream fileOut =
         new FileOutputStream("sports_country.ser");
         ObjectOutputStream out =
                            new ObjectOutputStream(fileOut);
         out.writeObject(this);
         out.close();
          fileOut.close();
          System.out.println("sports_country.ser saved...");
      }catch(IOException i)
      {
          i.printStackTrace();
          System.out.println("sports_country.ser notsaved...");
      }

   }

   private Sports_CountryBank retrieveBank(){
      Sports_CountryBank b;
       try
         {
            FileInputStream fileIn =
                          new FileInputStream("sports_country.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
             b= (Sports_CountryBank) in.readObject();
            in.close();
            fileIn.close();
            this.combs = b.combs;
            return b;

        }catch(IOException i)
        {
            i.printStackTrace();
            return null;
        }catch(ClassNotFoundException c)
        {
            System.out.println("sports bank  class not found");
            c.printStackTrace();
            return null;
        }
   }

   public void createBank(){
      //  Sports_CountryBank bank = new Sports_CountryBank();
    CountryNationalityHandler c = new CountryNationalityHandler();
    Vector<String> contries = c.getDistictCountries();

    for(int i=0; i<contries.size(); i++){
        String name = contries.get(i);
        SportsCombination s= new SportsCombination(name);
        s = s.retrieveBank(name);
        if(s.getSports().size() == 0){
            this.noSportsCombs.add(s);
        }
        else this.addSportCombination(s);
    }

//    for(int i=0; i<this.noSportsCombs.size(); i++){
//        System.out.println(this.noSportsCombs.get(i).getCountry());
//    }
   }

   public Vector<String> getSports(String country){
     //  this.retrieveBank();
       for(int i=0; i<this.combs.size(); i++){
           if(this.combs.get(i).getCountry().equalsIgnoreCase(country)){
               return this.combs.get(i).getSports();
           }
       }
       for(int i=0; i<this.noSportsCombs.size(); i++){
           if(this.noSportsCombs.get(i).getCountry().equalsIgnoreCase(country)){
               return this.noSportsCombs.get(i).getSports();
           }
       }
       return null;
   }
}
