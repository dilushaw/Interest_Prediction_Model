/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dilu;

import Group.Classifier;
import Group.Passenger;
import dynamiclearning.MainInterest;
import dynamiclearning.Processor;
import dynamiclearning.SubInterest;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import neuralnetwork3.InterestBank;
import neuralnetwork3.PointHandler;

/**
 *
 * @author Sinthu
 */
@WebService(serviceName = "Handler")
public class Handler {
private Vector<MainInterest> mainInts=new Vector<MainInterest>();
  private String mainInte;
  private String subInte;
 private static int interestcount;
    private Connection con;
    private Statement stmt;
    private static int counter=0;
private String keyword;
private String urls;
private Vector<String> passenger2;
private static int bookcou;
private static int filmcou;
private static int fashioncou;
private static int musiccou;
private static int gamescou;
//private static int techcou;
private static int foodcou;
private static int sciencecou;
    private int techcou;
    private int financecou;
    private int newInterestcou;
    private Classifier cluseter_bank_newInt;
    private Classifier existing_Int; //for music, films, fashions, all the retrain..
    private String interest_nationality;
    private String newIntsURLS;



/**
     * Web service operation
     */
    @WebMethod(operationName = "ProcessInterest")
    public void ContentMatching(@WebParam(name = "interest") Vector<String> keywords, @WebParam(name = "links") Vector<String> links1,@WebParam(name = "passengerinfo") Vector<String> passenger) throws IOException {
System.out.println("web method called");
//  this.saveTemp();
     Vector<Vector<String>> links = this.getSpecialVetcor(links1); 
     this.passenger2 = passenger;
        //this.printVec(links);
        
      
        this.cluseter_bank_newInt = this.retrieveClusterBank("NewInterest");

     this.existing_Int = this.retrieveClusterBank("Existing");
        for(int k=0;k<keywords.size();k++){
        this.keyword=keywords.get(k)  ;  
        boolean check=false;
        
        Processor processor=new Processor(keywords.get(k),links.get(k));
        try {
            Object[] obj = processor.main1();
            boolean isNewInterest = (Boolean)obj[obj.length-1];
             if(obj!=null){
    if(isNewInterest){
         check = false;
        String key = (String)obj[2];
        Vector<String> url = (Vector<String>)obj[3];
        this.mainInte = "";
        this.subInte = "";
        this.converturlToStirng(url);
        this.saveToDatabase(this.counterIncrementer(), check, url, processor);
        System.out.println("It's a new interest");
        System.out.println("Keyword:" +key );
        System.out.println("Urls:" +url );
       
    }
    else{
         check = true;
        Vector<MainInterest> mains =(Vector<MainInterest>)obj[0];
        Vector<SubInterest> subs = (Vector<SubInterest>)obj[1];
        String key = (String)obj[2];
        Vector<String> url = (Vector<String>)obj[3];
        System.out.println("It's a existing interest");
        System.out.println("Interest:");
        for(int i=0; i<mains.size(); i++){
            System.out.println(mains.get(i).getName());
            System.out.println(subs.get(i).getName());
            this.mainInte = mains.get(i).getName();
        this.subInte = subs.get(i).getName();
        this.converturlToStirng(url);
        this.saveToDatabase(this.counterIncrementer(), check, url, processor);
        }
//        System.out.println("Sub Interest:");
//         for(int i=0; i<subs.size(); i++){
//            System.out.println(subs.get(i).getName());
//        }
        System.out.println("Keyword:" +key );
        System.out.println("Urls:" +url );
       
    }
 }
 else{
     System.out.println("The object return is null from control method");
 }

//           this.mainInts= processor.mainInts;
//             System.out.println(this.mainInts.size()+"  size");
//           for(int i=0;i<this.mainInts.size();i++){
//
//                // System.out.println(this.mainInts.get(i));
//              if(this.mainInts.get(i).isFinalSelection()) {
//                  check=true;
//                this.mainInte= this.mainInts.get(i).getName();
//                for(int j=0;j<this.mainInts.get(i).getSubInts().size();j++){
//               if(this.mainInts.get(i).getSubInts().get(j).isFinalSelection()) {
//                 this.subInte=this.mainInts.get(i).getSubInts().get(j).getName();
//               }
//
//                }
//
//              }
//
//
//           }
//
//          if(check==false) {//new interest
//             this.mainInte= this.keyword;
//             this.subInte="";
//          }
//
           
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        //TODO write your implementation code here:
        
//        Writer output = null;
//  String text = "diluu";
//  File file = new File("write.txt");
//  output = new BufferedWriter(new FileWriter(file));
//  output.write(text);
//  output.close();
//  System.out.println("Your file has been written");  
  
        
        
        //this.passenger=passenger;//shud b uncommnted
//        passenger2=new Vector<String>();
//        passenger2.add("15");
//        passenger2.add("f");
//        passenger2.add("sri lankan");
//        passenger2.add("doctor");
//    //this.convertKeywordToStirng(keywords);
//    this.converturlToStirng(links.get(k));
//    //this.counterIncrementer();
//    this.saveToDatabase(this.counterIncrementer(),check, links.get(k),processor);
    }
    /** This is a sample web service operation */
    }
    
 private Vector<Vector<String>> getSpecialVetcor(Vector<String> links){
    Vector<Vector<String>> allVec =new Vector<Vector<String>>();
    Vector<String> oneVec = new Vector<String>();
     for(int i=0; i<links.size(); i++){
         if(links.get(i).equalsIgnoreCase("")){
             allVec.add(oneVec);
             oneVec = new Vector<String>();
         }
         else if((i==(links.size()-1))){
             oneVec.add(links.get(i));
             allVec.add(oneVec);
         }
         else{
             oneVec.add(links.get(i));
         }
     }
     return allVec;
 }

private void print(Vector<String> vec){
    System.out.println("####################################");
    for(int i =0; i<vec.size(); i++){
        System.out.println(vec.get(i));
    }
}


private void printVec(Vector<Object> vec){
    System.out.println("#############000000000000000000000#######################");
    for(int i =0; i<vec.size(); i++){
        Vector<String> s = (Vector<String>)vec.get(i);
        System.out.println("####################################");
        for(int j=0; j<s.size(); j++){
            System.out.println(s.get(j));
        }
        
    }
}
    private void saveTemp(){
        try {
            Writer output = null;
            String text = "Rajesh Kumar";
            File file = new File("write.txt");
            output = new BufferedWriter(new FileWriter(file));
            output.write(text);
            output.close();
            System.out.println("Path......."+ file.getAbsolutePath());
        } catch (IOException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void saveToDatabase(int eachcounter,boolean check,Vector<String> links,Processor processor ){
        System.out.println("im diluu");
        counter=counter+1;
        String   errMsg="";
        int a=0;//for each new interest
       
   try {
          System.out.println("database connected");
            Class.forName("com.mysql.jdbc.Driver");
         
            String connectionUrl = "jdbc:mysql://localhost/mysql?" +
                                   "user="+"root"+"&password="+"1234";
             con = (Connection) DriverManager.getConnection(connectionUrl);
        
    }
        catch (SQLException ex) {
           //Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            errMsg = "The user name and password of MySQL Database is Incorrect\n Please type your username and password in the below boxes;";
        }    catch (ClassNotFoundException cE) {
        }
            try {
                //System.out.println("Class Not Found Exception: "+ cE.toString());
            
    try{
          // System.out.println("databasesssssssssss");
        stmt =  (Statement) con.createStatement();
                stmt.executeUpdate("create database if not exists contentMatching");
                // System.out.println("databases craed");
                stmt.executeUpdate("use contentMatching");
              //  System.out.println("databases used");
                stmt.executeUpdate("CREATE TABLE if not exists interest (Id int,Counter int(10),MainInterest char(40),SubInterest char(40),Keywords char(50),Links char(255))");
                stmt.executeUpdate("CREATE TABLE if not exists passenger (Id int,Age char(40),Gender char(40),Nationality char(50),Profession char(50))");
stmt.executeUpdate("CREATE TABLE if not exists newInterest (Id int(10),CounterNew int(10),Keyword char(15),Links char(100))");
 //stmt.executeUpdate("CREATE TABLE if not exists cluster (Id int(10),ClusterId int(10),MainInterest char(40),SubInterest char(40),Keywords char(15),Links char(15))");
                  
                
                //String dt =nowTime();
                //stmt.executeUpdate("DELETE FROM passengers WHERE(DATEDIFF(Date_Time,'"+dt+"') < ('+0000-00-00 24:0:0+'))");
                 // stmt.executeUpdate("DELETE FROM passengers WHERE (Date_Time<=(now()-interval 40 minute))");
            }
            catch(Exception e){
               // JOptionPane.showMessageDialog(null,"Your Database Connection Failed!");
                e.printStackTrace();
            }

      
 ResultSet rss = stmt.executeQuery("Select * from passenger");
 int allPasCount = 0;
if(rss != null){
rss.beforeFirst();  
  rss.last();  
  allPasCount = rss.getRow();
}
int id = allPasCount++;

ResultSet rss1 = stmt.executeQuery("Select * from interest where MainInterest='"+this.mainInte+"'");
 int interCount = 0;
if(rss1 != null){
rss1.beforeFirst();  
  rss1.last();  
  interCount = rss1.getRow();
}
int interId = interCount++;
    stmt.executeUpdate("INSERT INTO passenger VALUES("+id+",'"+this.passenger2.get(0) +"','"+this.passenger2.get(1)+"','"+this.passenger2.get(2)+"','"+this.passenger2.get(3)+"')");
   stmt.executeUpdate("INSERT INTO interest VALUES("+id+","+interId+",'"+this.mainInte+"','"+this.subInte+"','"+this.keyword+"','"+this.urls+"')");
  
    
    
    if(check==true){//exsting
        interest_nationality = "";
                try {
                    //exsting
                  interest_nationality =  processor.getNationality();
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
                }
              //stmt.executeUpdate("INSERT INTO cluster VALUES("+this.counter+","+eachcounter+",'"+this.mainInte+"','"+this.subInte+"','"+this.keyword+"','"+this.urls+"')");
          PointHandler ph=new PointHandler ();
    ph.setAge_Str(this.passenger2.get(0));
    double ag = ph.get_DoubleAge();
    //pointPassenger.add(ph.getAge_Doub());
    ph.setGender_Str(this.passenger2.get(1));
  double g = ph.get_DoubleGender();
    // pointPassenger.add(ph.getGender_Doub());
    ph.setNationality_Str(this.passenger2.get(2));
    double n = ph.get_DoubleNationality();
    // pointPassenger.add(ph.getNationality_Doub());
    ph.setProfession_Str(this.passenger2.get(3));
    double pr = ph.get_DoubleProfession();
    
    //pointPassenger.add(ph.getProfession_Doub());
           Passenger p = new Passenger(String.valueOf(counter), ag , n, g, pr );
        this.existing_Int.setPassengerAndInterest(p, this.mainInte+"_"+this.subInte,interest_nationality, links); //format of the interest is mainIntName_SubInterestName
        this.saveSER("Existing", existing_Int);
      }
      else{//new interest

       try {
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM newInterest Where keyword='"+this.keyword+"'");//check not suree;(
      
      //int []key=null;
            while (rs1.next())
      {

a=rs1.getInt("CounterNew");
       //   key[a]=rs1.getInt("CounterNew");


      }
//            if(key!=null){
//Arrays.sort(key);
//   a=key[key.length-1];  
//
//       }
//            else{
//           a=0;
//            }
a=a+1;

        } catch (SQLException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
        }



       stmt.executeUpdate("INSERT INTO newInterest VALUES("+this.counter+","+a+",'"+this.keyword+"','"+this.urls+"')");
       
      }
            } catch (SQLException ex) {
                Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
         

 if(eachcounter >=1&&(this.mainInte.equalsIgnoreCase("books")||this.mainInte.equalsIgnoreCase("science")||this.mainInte.equalsIgnoreCase("foods")||this.mainInte.equalsIgnoreCase("fashions")||this.mainInte.equalsIgnoreCase("finance")||this.mainInte.equalsIgnoreCase("games")||this.mainInte.equalsIgnoreCase("technology")|| this.mainInte.equalsIgnoreCase("films") || this.mainInte.equalsIgnoreCase("music"))) {
     Vector<Vector<Double>> allpointPassengers=new Vector<Vector<Double>>();
     Vector<Double> pointPassenger=new Vector<Double>();
     
    Vector<Vector<String>> allPassengers= this.retrainingPas(this.mainInte);
    Vector<String> subinterest=this.retrainingSunInt(this.mainInte);
    for(int i=0;i<allPassengers.size();i++){
    PointHandler ph=new PointHandler ();
    ph.setAge_Str(allPassengers.get(i).get(0));
    pointPassenger.add(ph.get_DoubleAge());
    ph.setGender_Str(allPassengers.get(i).get(1));
     pointPassenger.add(ph.get_DoubleGender());
    ph.setNationality_Str(allPassengers.get(i).get(2));
     pointPassenger.add(ph.get_DoubleNationality());
    ph.setProfession_Str(allPassengers.get(i).get(3));
    pointPassenger.add(ph.get_DoubleProfession());
    
    allpointPassengers.add(pointPassenger);
    }
    
    InterestBank ib=new InterestBank();
    ib.retraining(this.mainInte, subinterest, allpointPassengers);
    this.clearCounter();
    
 }  
 
 if(a==10&&!(this.mainInte.equalsIgnoreCase("books")||this.mainInte.equalsIgnoreCase("science")||this.mainInte.equalsIgnoreCase("food")||this.mainInte.equalsIgnoreCase("fashions")||this.mainInte.equalsIgnoreCase("finance")||this.mainInte.equalsIgnoreCase("games")||this.mainInte.equalsIgnoreCase("technology")||this.mainInte.equalsIgnoreCase("music")||this.mainInte.equalsIgnoreCase("films"))) {
 //classification
   Vector<String> urlsN= this.parsingCommaForURL();
    this.cluseter_bank_newInt.ClassifyAndSetInterest(this.getPassengers(this.mainInte), this.mainInte,urlsN);
    this.saveSER("NewInterest", cluseter_bank_newInt);
 }
    }


    private void saveSER(String name, Classifier c){
     try
      {
         FileOutputStream fileOut =new FileOutputStream(name+".ser");
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


    public Classifier retrieveClusterBank(String name){
Classifier b = null;
 try
         {
            FileInputStream fileIn =
                          new FileInputStream(name+".ser");
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
            System.out.println("InterestBank  class not found");
            c.printStackTrace();
            return null;
        }

}
    
    private void converturlToStirng(Vector<String> urllist){
        
        for(int i=0;i<urllist.size();i++){
        if(i==0){
            this.urls=urllist.get(i)+",";
            }
            else{
                this.urls=urls+urllist.get(i)+",";
            }
        }
       this.urls= this.urls.substring(0, this.urls.length()-1);//chck this to eleminate comma  ,
    }

  
    
    private Vector<Vector<String>> retrainingPas(String intererst){
        Vector<Vector<String>> allPassenger=new Vector<Vector<String>>();
        Vector<String> subInterest=new  Vector<String>();
        Vector<String> passenger=new Vector<String>();
        
        try {
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM interest Where MainInterest='"+intererst+"'");//check not suree;(
            while (rs1.next())
      {
          int id=rs1.getInt("Id");
          String sub=rs1.getString("SubInterest");
          Statement stmt2 = con.createStatement();
        ResultSet rs2 = stmt2.executeQuery("SELECT * FROM passenger where Id="+id);
        passenger  = new Vector<String>();
        while(rs2.next()){
        String age=rs2.getString("Age");
        passenger.add(age);
        String gender=rs2.getString("Gender");
        passenger.add(gender);
        String nationality=rs2.getString("Nationality");
        passenger.add(nationality);
        String profession=rs2.getString("Profession");
        passenger.add(profession);
        }
         allPassenger.add(passenger);
         subInterest.add(sub);
      }
           
        } catch (SQLException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allPassenger;
    }


    private Vector<Passenger> getPassengers(String intererst){
//        Vector<Vector<String>> allPassenger=new Vector<Vector<String>>();
//        Vector<String> subInterest=new  Vector<String>();
//        Vector<String> passenger=new Vector<String>();
Vector<Passenger> pp = new Vector<Passenger>();
        try {
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM interest Where MainInterest='"+intererst+"'");//check not suree;(
            while (rs1.next())
      {
          int id=rs1.getInt("Id");
          if(id==10){
          String urls=rs1.getString("links");
          newIntsURLS=urls;
          }
          String sub=rs1.getString("SubInterest");
        ResultSet rs2 = stmt.executeQuery("SELECT * FROM passenger where Id="+id);
        String age=rs2.getString("Age");
//        passenger.add(age);
        String gender=rs2.getString("Gender");
//        passenger.add(gender);
        String nationality=rs2.getString("Nationality");
//        passenger.add(nationality);
        String profession=rs2.getString("Profession");
//        passenger.add(profession);

//    //pointPassenger.add(ph.getProfession_Doub());
//        ph.setAge_Str(this.passenger2.get(0));
//    double ag = ph.get_DoubleAge();
//    //pointPassenger.add(ph.getAge_Doub());
//    ph.setGender_Str(this.passenger2.get(1));
//  double g = ph.get_DoubleGender();
//    // pointPassenger.add(ph.getGender_Doub());
//    ph.setNationality_Str(this.passenger2.get(2));
//    double n = ph.get_DoubleNationality();
//    // pointPassenger.add(ph.getNationality_Doub());
//    ph.setProfession_Str(this.passenger2.get(3));
//    double pr = ph.get_DoubleProfession();
//    
//    //pointPassenger.add(ph.getProfession_Doub());
//           Passenger p = new Passenger(String.valueOf(counter), ag , n, g, pr );
             PointHandler ph=new PointHandler ();
    ph.setAge_Str(age);
     double ag = ph.get_DoubleAge();
    //pointPassenger.add(ph.getAge_Doub());
    ph.setGender_Str(gender);
     double g = ph.get_DoubleGender();
    // pointPassenger.add(ph.getGender_Doub());
    ph.setNationality_Str(nationality);
    double n = ph.get_DoubleNationality();
    // pointPassenger.add(ph.getNationality_Doub());
    ph.setProfession_Str(profession);
    double pr = ph.get_DoubleProfession();
    //pointPassenger.add(ph.getProfession_Doub());
           Passenger p = new Passenger(String.valueOf(id), ag, n, g, pr);
pp.add(p);
    //    Passenger p = new Passenger(String.valueOf(id), age, nationality, gender, profession);
//         allPassenger.add(passenger);
//         subInterest.add(sub);
      }

        } catch (SQLException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pp;
    }

    private Vector<String> retrainingSunInt(String intererst){
        
        Vector<String> subInterest=new  Vector<String>();
        
        
        try {
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM interest Where MainInterest='"+intererst+"'");//check not suree;(
            while (rs1.next())
      {
        
          String sub=rs1.getString("SubInterest");
        
         subInterest.add(sub);
      }
           
        } catch (SQLException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subInterest;
    }
    
    
    
    
    
    
    
    private int counterIncrementer(){
        int database=0;
        if(this.mainInte.equalsIgnoreCase("books")){
     this.bookcou++;
     database=bookcou;
        }
        else if(this.mainInte.equalsIgnoreCase("science")){
      this.sciencecou++;
      database=this.sciencecou;
        }
        else if(this.mainInte.equalsIgnoreCase("films")){
     filmcou++;
      database=filmcou;
        }
        
         else if(this.mainInte.equalsIgnoreCase("games")){
     
      gamescou++;
      database=gamescou;
        }
        else if(this.mainInte.equalsIgnoreCase("music")){
     
      musiccou++;
      database=musiccou;
        }
        else if(this.mainInte.equalsIgnoreCase("foods")){
     
      foodcou++;
      database=foodcou;
        }
        else if(this.mainInte.equalsIgnoreCase("fashions")){
     
      fashioncou++;
      database=fashioncou;
        }
        
        else if(this.mainInte.equalsIgnoreCase("technology")){
     
      techcou++;
      database=techcou;
        }
        
    
    else if(this.mainInte.equalsIgnoreCase("finance")){
     
     financecou++;
     database=financecou;
        }
    else {
    newInterestcou++;
     database=newInterestcou;
}
        return database;
    }
    
    private void clearCounter(){
          int database=0;
        if(this.mainInte.equalsIgnoreCase("books")){
     this.bookcou = 0;
        }
        else if(this.mainInte.equalsIgnoreCase("science")){
      this.sciencecou = 0;
        }
        else if(this.mainInte.equalsIgnoreCase("films")){
     filmcou = 0;
        }
        
         else if(this.mainInte.equalsIgnoreCase("games")){
     
      gamescou = 0;
        }
        else if(this.mainInte.equalsIgnoreCase("music")){
     
      musiccou = 0;
        }
        else if(this.mainInte.equalsIgnoreCase("foods")){
     
      foodcou = 0;
        }
        else if(this.mainInte.equalsIgnoreCase("fashions")){
     
      fashioncou = 0;
        }
        
        else if(this.mainInte.equalsIgnoreCase("technology")){
     
      techcou = 0;
        }
        
    
    else if(this.mainInte.equalsIgnoreCase("finance")){
     financecou = 0;
        }
   
    }
    
    
    
    private Vector<String> parsingCommaForURL(){
       Vector<String> newIntUrls=new Vector<String>(); 
        String urls=this.newIntsURLS;
        String [] url=urls.split(",");
        
        for(int i=0;i<url.length;i++){
         newIntUrls.add(url[i]);              
             
        }
       return newIntUrls; 
        
        
    }

}