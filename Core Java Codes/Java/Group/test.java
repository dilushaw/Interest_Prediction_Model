/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Group;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.sun.org.apache.bcel.internal.generic.SIPUSH;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import neuralnetwork3.PointHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Sinthu
 */
public class test {

//    public static void main(String[] args){
//      Classifier c= new Classifier();
//      c.createClusters();
//      Vector<Passenger> pas = new Vector<Passenger>();
////      for(int i=0; i<5; i++){
////      Passenger p = new Passenger(String.valueOf(i), 0.2, 0.24, 0.3, 0.18);
////      pas.add(p);
////       p = new Passenger(String.valueOf(i)+"_"+String.valueOf(i), 0.2, 0.24, 0.6, 0.18);
////      pas.add(p);
////      }
////      for(int i=0; i<10; i++){
////       Passenger p = new Passenger(String.valueOf(i)+"_"+String.valueOf(i), 0.3, 0.48, 0.3, 0.36);
////      pas.add(p);
////      }
//
//      Passenger p = new Passenger("1", 0.3, 0.48, 0.3, 0.36);
//      pas.add(p);
//      p = new Passenger("2", 0.3, 0.48, 0.3, 0.36);
//      pas.add(p);
//      p = new Passenger("3", 0.3, 0.48, 0.3, 0.36);
//      pas.add(p);
//      p = new Passenger("4", 0.3, 0.48, 0.3, 0.36);
//      pas.add(p);
//      p = new Passenger("5", 0.2, 0.48, 0.3, 0.36);
//      pas.add(p);
//      p = new Passenger("10", 0.2, 0.48, 0.3, 0.36);
//      pas.add(p);
//      p = new Passenger("11", 0.2, 0.48, 0.3, 0.36);
//      pas.add(p);
//      p = new Passenger("12", 0.2, 0.48, 0.3, 0.24);
//      pas.add(p);
//      p = new Passenger("7", 0.2, 0.48, 0.3, 0.24);
//      pas.add(p);
//      p = new Passenger("8", 0.2, 0.48, 0.3, 0.24);
//      pas.add(p);
//      p = new Passenger("9", 0.2, 0.48, 0.3, 0.24);
//      pas.add(p);
//
//   c.ClassifyAndSetInterest(pas, "Interest1");
//     Passenger pp = new Passenger("x", 0.15, 0.3, 0.6, 0.18);
//   System.out.println(c.getInterest(pp));
//    }

    public static void main(String[] args){
//      Classifier c= new Classifier();
//      c.createClusters();
//      Passenger p = new Passenger("name", 0.16, 0.3, 0.6, 0.18);
//      Vector<String> urls = new Vector<String>();
//      urls.add("urls");
//      c.setPassengerAndInterest(p, "my interest", urls);
//     Passenger pp = new Passenger("x", 0.19, 0.3, 0.6, 0.18);
//     Vector<Interest> in = c.getInterestWithURL(pp);
//     for(int i=0; i<in.size(); i++){
//         in.get(i).print();
//     }

          try {
            Connection con = null;
   try {
          System.out.println("database connected");
            Class.forName("com.mysql.jdbc.Driver");
         
            String connectionUrl = "jdbc:mysql://localhost/mysql?" +
                                   "user="+"root"+"&password="+"1234";
            con = (Connection) DriverManager.getConnection(connectionUrl);
        
    }
        catch (SQLException ex) {
           //Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
           // errMsg = "The user name and password of MySQL Database is Incorrect\n Please type your username and password in the below boxes;";
        }    catch (ClassNotFoundException cE) {
        }
         
             
  
Statement stmt =(Statement) con.createStatement();
stmt.executeUpdate("use contentMatching");
ResultSet rs = stmt.executeQuery("Select * from passenger");
 int allPasCount = 0;
if(rs != null){
rs.beforeFirst();  
  rs.last();  
  allPasCount = rs.getRow();
}
    System.out.println(allPasCount);
        
//         Classifier c= new Classifier();
//      c.createClusters();
//      test t =new test();
//      t.saveSER("NewInterest", c);
//
//      Classifier cc= new Classifier();
//      cc.createClusters();
//
//      t.saveSER("Existing", cc);
        



//        test t = new test();
//        t.processTitle("http://jsoup.org/apidocs/org/jsoup/select/Selector.html");
//        double a = new Double(100);
//        double b = new Double(7);
//        System.out.println((a/b)*100);
    }
        catch (SQLException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//     private void setFilteredInterestFromExistingtable(){
//        Classifier existing_int = this.retrieveClusterBank("Existing");
//                 PointHandler ph=new PointHandler ();
//                ph.setAge_Str("16");
//               double age_double = ph.get_DoubleAge();
//   
//                 ph.setGender_Str("f");
//                double gen_double = ph.get_DoubleGender();
//   
//                 ph.setNationality_Str("country1");
//                double nat_double = ph.get_DoubleNationality();
//   
//                 ph.setProfession_Str("Student");
//                 double prof_double = ph.get_DoubleProfession();
//    
//           Passenger p = new Passenger("ss", age_double , nat_double, gen_double, prof_double );
//          
//            Vector<Vector> existingInterests = existing_int.getFilteredInterest(p);
//            for(int i=0; i<existingInterests.size(); i++){
//               Vector v = existingInterests.get(i);
//               System.out.println(v.get(0).toString());
//                for(int k=1; k<v.size(); k++){
//                        Interest inter = (Interest)v.get(k);
//                        System.out.println(inter.getInterest_nationality()+" " + v.get(0).toString());
//                       for(int a=0; a<inter.getUrls().size(); a++){
//                           System.out.println(inter.getUrls().get(a));
//                        }
//                    }
//            }
//    }

     
     public Classifier retrieveClusterBank(String name){
Classifier b = null;
 try
         {
            FileInputStream fileIn = new FileInputStream(name+".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
             b= (Classifier) in.readObject();
            in.close();
            fileIn.close();
            return b;

        }catch(IOException i)
        {
            i.printStackTrace();
            return null;
        }catch(ClassNotFoundException c)
        {
            System.out.println("Classifier  class not found");
            c.printStackTrace();
            return null;
        }

}
    
    private void saveSER(String name, Classifier c){
     try
      {
         FileOutputStream fileOut =
         new FileOutputStream(name+".ser");
         ObjectOutputStream out =
                            new ObjectOutputStream(fileOut);
         out.writeObject(c);
         out.close();
          fileOut.close();
          System.out.println(name +" ser saved...");
      }catch(IOException i)
      {
          i.printStackTrace();
          System.out.println(name +" Network notsaved...");
      }
}

    private void retrainingPas(String intererst){
        try {
            Connection con = null;
   try {
          System.out.println("database connected");
            Class.forName("com.mysql.jdbc.Driver");
         
            String connectionUrl = "jdbc:mysql://localhost/mysql?" +
                                   "user="+"root"+"&password="+"1234";
            con = (Connection) DriverManager.getConnection(connectionUrl);
        
    }
        catch (SQLException ex) {
           //Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
           // errMsg = "The user name and password of MySQL Database is Incorrect\n Please type your username and password in the below boxes;";
        }    catch (ClassNotFoundException cE) {
        }
         
            
     Vector<Vector<String>> allPassenger=new Vector<Vector<String>>();
        Vector<String> subInterest=new  Vector<String>();
        Vector<String> passenger=new Vector<String>();
          // System.out.println("databasesssssssssss");
        Statement stmt =  (Statement) con.createStatement();
        try {
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM interest Where MainInterest='"+intererst+"'");//check not suree;(
            while (rs1.next())
      {
          int id=rs1.getInt("Id");
          String sub=rs1.getString("SubInterest");
        ResultSet rs2 = stmt.executeQuery("SELECT * FROM passenger where Id="+id);
        String age=rs2.getString("Age");
        passenger.add(age);
        String gender=rs2.getString("Gender");
        passenger.add(gender);
        String nationality=rs2.getString("Nationality");
        passenger.add(nationality);
        String profession=rs2.getString("Profession");
        passenger.add(profession);
        
         allPassenger.add(passenger);
         subInterest.add(sub);
      }
           
        } catch (SQLException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private void processTitle(String url){
          try {
            String allcontent = "";
            URL searchUrl = new URL(url);
            URLConnection yc = searchUrl.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
              allcontent = allcontent + inputLine;
                  //System.out.println(inputLine);
            }
            //System.out.println(allcontent);
            in.close();
            //Document doc = Jsoup.connect(u).get();

//            Elements e = Jsoup.parse(allcontent).select("title");
//            Element ele = e.first();
//             if(ele != null){
//            String desc = ele.val();
//             System.out.println(desc);
//            }

             Pattern p = Pattern.compile("<title>(.*?)</title>");
    Matcher m = p.matcher(allcontent);
    while (m.find() == true) {
      System.out.println(m.group(1));
    }
            


            
        } catch (IOException ex) {
            //Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("exce");
        }
    }
}



