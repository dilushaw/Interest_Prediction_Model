/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GsonTable;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import neuralnetwork3.CountryNationalityHandler;
import neuralnetwork3.InterestBank;
import neuralnetwork3.InterestHandler;
import neuralnetwork3.Passenger;
import neuralnetwork3.TrainingEngine;
import neuralnetwork3.WebSearch;
import neuralnetwork3.GoogleResults;
import neuralnetwork3.InterestNetwork;
import neuralnetwork3.Neural_GUI;
import neuralnetwork3.SportHandler;

/**
 *
 * @author Dilusha
 */
public class GSonProcessor {
private InterestBank ib;
private Vector<InterestNetwork> nets;
private String[] natDependant = {"music", "films", "fashions", "foods", "sports", "news", "finance"};
private String[] professions = {"Medical", "Psychology", "Physcial Science", "Biology", "Agriculture", "Engineering", "Computer", "Business", "Management", "Accounting", 
                                "Law", "Journalism", "Art", "Political"};

private String[] professionalInts = {"Professional books", "Professional New Trends"};
private Table table;

public GSonProcessor(){
    ib = new InterestBank();
    nets = ib.getAllInterestNetwork();
    this.table = new Table();
}

public void processAndSaveTable(){
    Vector<String> links;
    String intName = "fashions";
    Passenger pas = new Passenger(0.02, 0.24, 0.3, 0.24);
    pas.setNationalityString("sri lankan");
     Vector<String> subInts = ib.getSubInterests(pas, intName);
        Vector<Vector<Object>> allSportName_url = new Vector<Vector<Object>>();
         Vector<Vector<String>> allurl=new Vector<Vector<String>>();
 // for(int i=0; i<sub.size(); i++){
     Vector<String> url=new Vector<String>();
            try {
                InterestHandler interHandler = new InterestHandler(pas);
                //Vector<String> interest = inter.get(i);
                interHandler.setInterest_name(intName);
               // System.out.println(interest.get(0));
                //url.add(intName);
             //   Vector<String> allinterests2 =
                interHandler.setInterest_output(subInts);
                //  interHandlerBank.add(interHandler);
              if(!  interHandler.checkInterest()){
     WebSearch web=new WebSearch(pas.getNationalityString(),interHandler.getInterestName(),interHandler.getOutput()) ;
 Vector<String> webInterest=web.handleEachInterest();
 for(int j=0;j<webInterest.size();j++){
                try {
                  //allurl.add(subInts.get(j));
                  url.add(subInts.get(j));
                  GoogleResults gr=( web.getURL(webInterest.get(j))) ;
                  for(int k=0; k<gr.getResponseData().getResults().size();k++){
               url.add(  gr.getResponseData().getResults().get(k).getUrl()) ;
                    }

                } catch (IOException ex) {
                   j = webInterest.size();
                   url.removeAllElements();
                  // JOptionPane.showMessageDialog(this, "No Internet Connection.\nPlease connect to internet and try again");
                }
                catch(Exception ex){
                    j = webInterest.size();
                   url.removeAllElements();
                  // JOptionPane.showMessageDialog(this, "No Internet Connection.\nPlease connect to internet and try again");
                }
          }
 allurl.add(url);
 links = url;
 String temp = "";
 for(int i=0; i<url.size(); i++){
     if(i+1<url.size()){
        if(url.get(i+1).indexOf("http") == -1)temp = temp + url.get(i) + "\n" + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+ "\n" + "\n";
        else temp = temp + url.get(i) + "\n";
    }

     else temp = temp + url.get(i) + "\n";
    //  temp = temp + url.get(i) + "\n";
 }
 System.out.println("this is temp..."+ temp);
              }

 else{              
                  CountryNationalityHandler c = new CountryNationalityHandler();
                  //String contry = 
                    String contry = "sri lanka";
                    links = new Vector<String>();
                    SportHandler sport=new SportHandler(contry) ;
                    allSportName_url= sport.handleURL();
                    if(allSportName_url !=null){
                    String text = "";
                    for(int k=0;k<allSportName_url.size();k++){
     Vector<Object> sports_url;
     sports_url=allSportName_url.get(k);

     text = text+ "\n" + "+++++++++++++++++++++++++++++++++++++++++++"+ "\n";
      for(int a=0;a<sports_url.size();a++){
          links.add(sports_url.get(a).toString());
Object x=sports_url.get(a);
    text = text + x.toString() + "\n";
        }
        }

                    System.out.println("This is text.."+text);

 }
                    else{
//                        this.jTextArea2.setText("");
//                        JOptionPane.showMessageDialog(this, "No Internet Connection.\nPlease connect to internet and try again");
                    }
 }
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Neural_GUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Neural_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }



// }
 // JOptionPane.showMessageDialog(this,"URLs have been found!\nURLs of the passenger interets:\n"+allurl);
 // String q="";
 //JOptionPane.showMessageDialog(this,"URLs have been found!\nURLs of the SPORTS:\n"+allSportName_url);


  
}

public void process(){
    for(int i=0; i<1; i++){ //have to change j<this.nets.size()
        InterestNetwork net = this.nets.get(i);
        Vector<String> allSubInts = new Vector<String>();
        for(int j=0; j<net.getOutputLayer().size(); j++){
             String name = net.getOutputLayer().get(j).getName();
             if(!(name.equalsIgnoreCase("no") | name.equalsIgnoreCase("no "+net.getName()))){
            String[] ar = name.split("&");
            for(int k=0; k<ar.length; k++){
                String trimInt = ar[k].trim();
                if(!trimInt.equalsIgnoreCase("")){
                    if(!this.isConsists(allSubInts, trimInt))allSubInts.add(trimInt);
               } 
            }
            }
        }
       if(this.isNatDependant(net.getName())){
            CountryNationalityHandler c = new CountryNationalityHandler();
            Vector<String> natList = c.getNationality();
            for(int j=0; j<1; j++){ //have to change j<natList.size();
                this.searchAndSave(net.getName(), allSubInts, j, natList.get(j));
            }
        }
        else{
            if(net.getName().equalsIgnoreCase("Books") | net.getName().equalsIgnoreCase("Technology")){
              allSubInts =  this.checkAndGetprofessionalDependencies(allSubInts, net.getName());
              this.searchAndSave(net.getName(), allSubInts, -1, "");
              
           }
           else this.searchAndSave(net.getName(), allSubInts, -1, "");
        }
    }
    
//     for(int i=0; i<1; i++){
//        InterestNetwork net = this.nets.get(i);
//        Vector<String> allSubInts = new Vector<String>();
//        for(int j=0; j<net.getOutputLayer().size(); j++){
//            String name = net.getOutputLayer().get(j).getName();
//            //if(!(name.equalsIgnoreCase("no") | name.equalsIgnoreCase("no "+net.getName()))){
//            String[] ar = name.split("&");
//            for(int k=0; k<ar.length; k++){
////                ar[k] = ar[k].trim();
////                if(!ar[k].equalsIgnoreCase("")){
//                    allSubInts.add(ar[k]);
//              //  }
//                
//            //}
//            }
//        }
//       if(this.isNatDependant(net.getName())){
//            CountryNationalityHandler c = new CountryNationalityHandler();
//            Vector<String> natList = c.getNationality();
//            for(int j=0; j<1; j++){
//                this.searchAndSave(net.getName(), allSubInts, j, natList.get(j));
//            }
//        }
//        else{
//           if(net.getName().equalsIgnoreCase("Books") | net.getName().equalsIgnoreCase("Technology")){
//              allSubInts =  this.checkAndGetprofessionalDependencies(allSubInts, net.getName());
//              this.searchAndSave(net.getName(), allSubInts, -1, "");
//              
//           }
//           else this.searchAndSave(net.getName(), allSubInts, -1, "");
//        }
//    }
}

private boolean isConsists(Vector<String> primary, String target){
    for(int i=0; i<primary.size(); i++){
        if(primary.get(i).equalsIgnoreCase(target)){
            return true;
        }
    }
    return false;
}
private Vector<String> checkAndGetprofessionalDependencies(Vector<String> subInt, String mainInt){
    Vector<String> newSub = new Vector<String>();
    for(int i=0; i<subInt.size(); i++){
        if(this.isProfDepend(subInt.get(i))){
            for(int j=0; j<this.professions.length; j++){
                String name = this.professions[j] +" " + mainInt;
                newSub.add(name);
            }
        }
        else{
            newSub.add(subInt.get(i));
        }
    }
    return newSub;
}

private boolean isProfDepend(String subInt){
    for(int i=0; i<this.professionalInts.length; i++){
        if(this.professionalInts[i].equalsIgnoreCase(subInt)){
            return true;
        }
    }
    return false;
}
private void searchAndSave(String mainIntname, Vector<String> subInt, int natIndex, String nationality){
      Vector<String> links;
    String intName = mainIntname;
    
     Vector<String> subInts = subInt;
        Vector<Vector<Object>> allSportName_url = new Vector<Vector<Object>>();
         Vector<Vector<String>> allurl=new Vector<Vector<String>>();
 // for(int i=0; i<sub.size(); i++){
     Vector<String> url=new Vector<String>();
     Vector<String> title = new Vector<String>();
            try {
                InterestHandler interHandler = new InterestHandler();
                //Vector<String> interest = inter.get(i);
                interHandler.setInterest_name(intName);
               // System.out.println(interest.get(0));
                //url.add(intName);
             //   Vector<String> allinterests2 =
                interHandler.setInterest_output(subInts);
                //  interHandlerBank.add(interHandler);
              if(!  interHandler.checkInterest()){
     WebSearch web=new WebSearch(nationality,interHandler.getInterestName(),interHandler.getOutput()) ;
 Vector<String> webInterest=web.handleEachInterest();
// for(int j=0;j<webInterest.size();j++){
     for(int j=0;j<webInterest.size();j++){ //have to change
                try {
                  //allurl.add(subInts.get(j));
                  url.add(subInts.get(j));
                  title.add(subInts.get(j));
                  GoogleResults gr=( web.getURL(webInterest.get(j))) ;
                  for(int k=0; k<gr.getResponseData().getResults().size();k++){
               url.add(  gr.getResponseData().getResults().get(k).getUrl()) ;
               title.add(gr.getResponseData().getResults().get(k).getTitle());
                    }

                } catch (IOException ex) {
                   j = webInterest.size();
                   url.removeAllElements();
                  // JOptionPane.showMessageDialog(this, "No Internet Connection.\nPlease connect to internet and try again");
                }
                catch(Exception ex){
                    j = webInterest.size();
                   url.removeAllElements();
                  // JOptionPane.showMessageDialog(this, "No Internet Connection.\nPlease connect to internet and try again");
                }
          }
 allurl.add(url);
 System.out.println(url);
 links = url;
 Vector<String> temp = new Vector<String>();
 String nameSubInt = "";
 Vector<String> titleTemp =  new Vector<String>();
 
 for(int i=0; i<url.size(); i++){
     if(url.get(i).indexOf("http") == -1){
         if(temp.size()>0){
         this.table.addTuple(mainIntname, nameSubInt, nationality, url,titleTemp, true);
         this.saveTable();
         nameSubInt = url.get(i);
         temp = new Vector<String>();
         titleTemp = new Vector<String>();
         }
         else{
             nameSubInt = url.get(i);
             temp = new Vector<String>();
             titleTemp = new Vector<String>();
         }
 }
     else{
      temp.add(url.get(i));
      titleTemp.add(title.get(i));
     }
              }
              }

 else{              
                  CountryNationalityHandler c = new CountryNationalityHandler();
                  //String contry = 
                    String contry = c.getCountry(natIndex);
                    links = new Vector<String>();
                    SportHandler sport=new SportHandler(contry) ;
                    allSportName_url= sport.handleURL();
                    Vector<Vector<Object>>allSportTile = sport.getSportUrlTile();
                    if(allSportName_url !=null){
                    String text = "";
                    for(int k=0;k<allSportName_url.size();k++){
     Vector<Object> sports_url;
     sports_url=allSportName_url.get(k);
     Vector<Object> sport_title = allSportTile.get(k);
     Vector<String> tempUrl = new Vector<String>();
     Vector<String> tempTitle = new Vector<String>();
     String subName = "";
      for(int a=0;a<sports_url.size();a++){
          if(a==0){
              subName = sports_url.get(a).toString();
          }
          else{
              tempUrl.add(sports_url.get(a).toString());
              tempTitle.add(sport_title.get(a).toString());
          }
        }
      if(!subName.equalsIgnoreCase("")){
          if(tempUrl.size()>0){
              this.table.addTuple(mainIntname, subName, nationality, tempUrl, tempTitle, true);
              this.saveTable();
          }
      }

                    System.out.println("This is text.."+text);

 }
                    }
                    else{
//                        this.jTextArea2.setText("");
//                        JOptionPane.showMessageDialog(this, "No Internet Connection.\nPlease connect to internet and try again");
                    }
 }
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Neural_GUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Neural_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }



// }
 // JOptionPane.showMessageDialog(this,"URLs have been found!\nURLs of the passenger interets:\n"+allurl);
 // String q="";
 //JOptionPane.showMessageDialog(this,"URLs have been found!\nURLs of the SPORTS:\n"+allSportName_url);


}

private boolean isNatDependant(String netName){
    for(int i=0; i<this.natDependant.length; i++){
        if(this.natDependant[i].equalsIgnoreCase(netName)){
            return true;
        }
    }
    return false;
}

public static void main(String[] args){
    GSonProcessor pro = new GSonProcessor();
    pro.process();
}

private void saveTable(){
    try
      {
         FileOutputStream fileOut = new FileOutputStream("gsonTable.ser");
         ObjectOutputStream out =  new ObjectOutputStream(fileOut);
         out.writeObject(this.table);
         out.close();
          fileOut.close();
          System.out.println("gson table saved..");
      }catch(IOException i)
      {
          i.printStackTrace();
          System.out.println("gson table notsaved...");
      }
}

public Table retrievetable(){
this.table = null;
 try
         {
            FileInputStream fileIn = new FileInputStream("gsonTable.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
             this.table = (Table) in.readObject();
            in.close();
            fileIn.close();
            return this.table;

        }catch(IOException i)
        {
            i.printStackTrace();
            return null;
        }catch(ClassNotFoundException c)
        {
            System.out.println("Table class not found");
            c.printStackTrace();
            return null;
        }

}

//Firlt element title, second urls
public Object[] getUrlsAndTile(String mainInt, String subInt, String nat_pas){
    String nationality = "";
    if(this.isNatDependant(mainInt)) nationality = nat_pas;
    else nationality = "";
    
    String tupleName = mainInt+"_"+subInt+"_"+nationality;
    
    Vector<Tuple> allTuples = this.table.getAllTuples();
    Tuple finalTuple = null;
    for(int i=0; i<allTuples.size(); i++){
        Tuple t = allTuples.get(i);
        if(t.getName().equalsIgnoreCase(tupleName)){
            finalTuple = t;
            break;
        }
    }
    Object[] obj = new Object[2];
    obj[0] = finalTuple.getTitle();
    obj[1] = finalTuple.getUrls();
    return obj;
    
}
}
