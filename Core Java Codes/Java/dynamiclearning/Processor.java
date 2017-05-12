/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dynamiclearning;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Sinthu
 */
public class Processor {
public Vector<MainInterest> mainInts;
private int noOfLineWiki;
private String keyword;
private Vector<String> urls;
private double[] percentage_match;

public Processor(String keyword,Vector<String> urls){
    this.mainInts = new Vector<MainInterest>();
    this.noOfLineWiki = 10;
     this.urls=urls;
     this.keyword=keyword;

}

//public Processor(){
//    this.mainInts = new Vector<MainInterest>();
//    this.noOfLineWiki = 10;
//}
public void createInterest(){
    this.createBooksInterest();
    this.createFashionInterest();
    this.createFilmsInterest();
    this.createScienceInterest();
    this.createMusicInterest();
    this.createGamesInterest();
    this.createTechnologyInterest();
    this.createFinanceInterest();
    this.createFoodInterest();

    //create all interests as above book interest

this.percentage_match = new double[this.mainInts.size()];
    //processing...
    for(int i=0; i<this.mainInts.size(); i++){
        MainInterest m = this.mainInts.get(i);
        this.processFileKeywords(m);
      //System.out.println(m.getKeywords());
        for(int j=0; j<m.getSubInts().size(); j++){
            SubInterest subInt = m.getSubInts().get(j);
            this.processFileKeywords(subInt);
            if(subInt.isHasNextLayer()){
                Vector<SubInterest> child = subInt.getAllChildSubInterests();
                for(int a=0; a<child.size(); a++){
                    this.processFileKeywords(child.get(a));
                }
            }

            //System.out.println(m.getSubInts().get(j).getKeywords());
        }
    }
}

private void createBooksInterest(){
    MainInterest book = new MainInterest("Books");
    this.mainInts.add(book);
    SubInterest elder = new SubInterest("Elders' story books", book);
   SubInterest child = new SubInterest("Children's book", book);
   SubInterest cook = new SubInterest("Cookery Books", book);
   SubInterest profBook = new SubInterest("Professional books", book);
   SubInterest agri = new SubInterest("Agricultural Book", profBook);
   SubInterest archi = new SubInterest("Architectural Book", profBook);
   SubInterest bio = new SubInterest("Biological Book", profBook);
   SubInterest business = new SubInterest("Business Book", profBook);
    SubInterest science = new SubInterest("Scientific Book", profBook);
   SubInterest journal = new SubInterest("Journalism Book", profBook);
   SubInterest computer = new SubInterest("Computer Book", profBook);
   SubInterest eng = new SubInterest("Engineering Book", profBook);
   SubInterest politic = new SubInterest("Political Book", profBook);
// //  SubInterest liberal = new SubInterest("Liberal art Book", profBook);
//  // SubInterest mech = new SubInterest("Mechanical Book", profBook);
   SubInterest medical = new SubInterest("Medical Book", profBook);
   SubInterest physical = new SubInterest("Physical Science Book", profBook);
   SubInterest psyco = new SubInterest("Psychological Book", profBook);
   SubInterest trans = new SubInterest("Management Book", profBook);
//   SubInterest art = new SubInterest("Artist Book", profBook);
//    SubInterest elder = new SubInterest("Elders' Book", book);
//    SubInterest child = new SubInterest("Childrens' Book", book);
//    SubInterest cook = new SubInterest("Cookery Book", book);
//    SubInterest sc = new SubInterest("Scientific Book", book);
//    SubInterest bus = new SubInterest("Business Book", book);
}

private void createTechnologyInterest(){
    MainInterest tech = new MainInterest("Technology");
    this.mainInts.add(tech);
    SubInterest kid = new SubInterest("Kids Electronics", tech);
    SubInterest newTrend = new SubInterest("New Trends", tech);
     SubInterest laptop = new SubInterest("Laptops", tech);
////  SubInterest computer = new SubInterest("Computer", tech);
       SubInterest household = new SubInterest("Household Electronics", tech);
//       SubInterest cookery = new SubInterest("Cookery Electronics", tech);
        SubInterest mobile = new SubInterest("Mobile Phones", tech);
        SubInterest prof = new SubInterest("Professional Technology", tech);
         SubInterest tele = new SubInterest("Telecommunication Technology", prof);
          SubInterest elec = new SubInterest("Electronic Technology", prof);
           SubInterest medi = new SubInterest("Medical Technology", prof);
            SubInterest com = new SubInterest("Computer Technology", prof);
//     SubInterest prof = new SubInterest("Professional Electronics", tech);
//      SubInterest agri = new SubInterest("Agricultural Technology", prof);
//   SubInterest archi = new SubInterest("Architectural Technology", prof);
//   SubInterest bio = new SubInterest("Biological Technology", prof);
//   SubInterest business = new SubInterest("Business Technology", prof);
//    SubInterest science = new SubInterest("Scientific Technology", prof);
//   SubInterest journal = new SubInterest("Journalism Technology", prof);
//   SubInterest computer = new SubInterest("Computer Technology", prof);
//   SubInterest eng = new SubInterest("Engineering Technology", prof);
////Not needed for technology. check with dilu  SubInterest politic = new SubInterest("Political Technology", prof);//
//// //  SubInterest liberal = new SubInterest("Liberal art Book", profBook);
////  // SubInterest mech = new SubInterest("Mechanical Book", profBook);
//   SubInterest medical = new SubInterest("Medical Technology", prof);
//   SubInterest physical = new SubInterest("Physical Technology", prof);
//   SubInterest psyco = new SubInterest("Psychological Technology", prof);
//   SubInterest trans = new SubInterest("Management Technology", prof);

}

private void createFinanceInterest(){
    MainInterest finance = new MainInterest("Finance");
    this.mainInts.add(finance);
    SubInterest bank = new SubInterest("Banks", finance);
    SubInterest ins = new SubInterest("Insurance", finance);
    SubInterest stock = new SubInterest("Share Market", finance);
}
private void createFashionInterest(){
    MainInterest fashion = new MainInterest("Fashions");
    this.mainInts.add(fashion);
    SubInterest girl = new SubInterest("girl fashion", fashion);
    SubInterest boy = new SubInterest("boy fashion", fashion);
    SubInterest wom = new SubInterest("ladies fashion", fashion);
    SubInterest men = new SubInterest("gent fashion", fashion);
}

private void createFilmsInterest(){
    MainInterest films = new MainInterest("Films");
    this.mainInts.add(films);

}

private void createScienceInterest(){
    MainInterest science = new MainInterest("Science");
    this.mainInts.add(science);
    SubInterest simple_sci = new SubInterest("Simple Science", science);
    SubInterest advanced_sci = new SubInterest("Advanced Science", science);

}
private void createMusicInterest(){
    MainInterest music = new MainInterest("Music");
    this.mainInts.add(music);
}
private void createFoodInterest(){
    MainInterest foods = new MainInterest("Foods");
    SubInterest food = new SubInterest("Food", foods);
     SubInterest food1 = new SubInterest("Food1", food);
      SubInterest food2 = new SubInterest("Food2", food);
    this.mainInts.add(foods);
}

private void createGamesInterest(){
    MainInterest games = new MainInterest("Games");
    this.mainInts.add(games);
    SubInterest advanced_ga = new SubInterest("Advanced Games", games);
    SubInterest simple_ga = new SubInterest("simple games", games);

}
private void processFileKeywords(Object ints){
       boolean isMainInt = false;
       boolean isSubInt = false;
       MainInterest mainInt = null;
       SubInterest subInt = null;

       String name = "";
       try{
           mainInt = (MainInterest)ints;
           name = mainInt.getName()+"/"+ mainInt.getName();
           isMainInt = true;
           isSubInt = false;
       }catch(ClassCastException ex){
           isMainInt = false;
           isSubInt = true;
       }

       if(isSubInt){
            try{
           subInt = (SubInterest)ints;
           name = subInt.getMainInt().getName()+"/"+subInt.getName();
           isSubInt = true;
           isMainInt = false;
       }catch(ClassCastException ex){
           isMainInt = false;
           isSubInt = false;
       }
       }

if(isMainInt | isSubInt){
       try{
    FileInputStream fstream = new FileInputStream(name+".txt");
  // Get the object of DataInputStream
  DataInputStream in = new DataInputStream(fstream);
  BufferedReader br = new BufferedReader(new InputStreamReader(in));
  String strLine;
  while ((strLine = br.readLine()) != null)   {
      strLine = strLine.toLowerCase();
    if(isMainInt) mainInt.addKeyword(strLine);
    else if(isSubInt) subInt.addKeyword(strLine);
  }
  in.close();
    }catch (Exception e){
  System.err.println("Error: " + e.getMessage());
  }
}
 
}


public void findSuitableMatch(String url){
    Vector<String> keys = this.getKeywordsURL(url);
   // newInterest.addKeywords(keys);
    System.out.println(keys);
    
    for(int i=0; i<this.mainInts.size(); i++){
        MainInterest m = this.mainInts.get(i);
        int startIndex = m.getKeys_newInterest().size();
        m.addNewInterestKeyword(keys);
        int c =this.getMatches(m.getKeywords(), keys, startIndex, m, false);
        m.addMatches(c);
       // System.out.println(m.getName()+" : "+ c);
      //  System.out.println(m.getKeywords());
        for(int j=0; j<m.getSubInts().size(); j++){
           // this.processFileKeywords(m.getSubInts().get(j));
            SubInterest sub = m.getSubInts().get(j);
            c =this.getMatches(sub.getKeywords(), keys, startIndex, m, false);
            sub.addMatches(c);
            if(sub.isHasNextLayer()){
                Vector<SubInterest> allChild = sub.getAllChildSubInterests();
                for(int a=0; a<allChild.size(); a++){
                  c =this.getMatches(allChild.get(a).getKeywords(), keys, startIndex, m, false);
                  allChild.get(a).addMatches(c);
                }
                
            }

         //   System.out.println(m.getSubInts().get(j).getName()+" : "+ c);
           // System.out.println(m.getSubInts().get(j).getKeywords());
        }
    }
}


private void clearMainInterestNewInterestData(){
    for(int i=0; i<this.mainInts.size(); i++){
        this.mainInts.get(i).clearNewInterestData();
    }
}
public void findSuitableMatches(Vector<String> keys){
   // Vector<String> keys = this.getKeywordsURL(url);
   // System.out.println(keys);
    for(int i=0; i<this.mainInts.size(); i++){
        MainInterest m = this.mainInts.get(i);
        int c =this.getMatches(m.getKeywords(), keys, 0, null, true);
        m.addMatches(c);
        System.out.println(m.getName()+" : "+ c);
      //  System.out.println(m.getKeywords());
        for(int j=0; j<m.getSubInts().size(); j++){
           // this.processFileKeywords(m.getSubInts().get(j));
            SubInterest sub = m.getSubInts().get(j);
            c =this.getMatches(sub.getKeywords(), keys,  0, null, true);
            sub.addMatches(c);
            System.out.println(sub.getName()+" : "+ c);
             if(sub.isHasNextLayer()){
                Vector<SubInterest> allChild = sub.getAllChildSubInterests();
                for(int a=0; a<allChild.size(); a++){
                  c =this.getMatches(allChild.get(a).getKeywords(), keys, 0, null, true);
                  allChild.get(a).addMatches(c);
                  System.out.println(allChild.get(a).getName()+" : "+ c);
                }
            }
           // System.out.println(m.getSubInts().get(j).getKeywords());
        }
    }
}

private int getMatches(Vector<String> netKeys, Vector<String> urlKeys, int startIndex, MainInterest mainInt, boolean isWikiUsage){
    int count =0;
   // System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
    for(int i=0; i< netKeys.size(); i++){
        String netKey = netKeys.get(i);
//        if(netKey.equalsIgnoreCase("old books")){
//            System.out.println(netKey);
//        }
        netKey = netKey.trim();
        if(!netKey.equalsIgnoreCase("")){
  //      System.out.println("Net keys:********************"+netKey);
        String[] allkeys = netKey.split(" ");
        for(int j=0; j<urlKeys.size(); j++){
        String[] urlSpaceSplit = urlKeys.get(j).trim().split(" ");
        boolean[] matches = new boolean[allkeys.length];
       for(int a=0; a<allkeys.length; a++){
        String oneKey = allkeys[a].trim();
        for(int b=0; b<urlSpaceSplit.length; b++){
            String aURLsplit = urlSpaceSplit[b].toLowerCase().trim();
           int allowedFirstMatch = (int)Math.round(aURLsplit.length() * 0.5);
           int keyLength = oneKey.length();
           if(keyLength == 0){
               matches[a] = true;
           }
           else if(aURLsplit.startsWith(oneKey.toLowerCase())){
                 if((keyLength>= allowedFirstMatch)){
                     matches[a] = true;
                      b=urlSpaceSplit.length;
            }
            else{
                matches[a] = false;
            }        
            }
            else{
                if(this.isPartialMatch(aURLsplit, oneKey)){
                    matches[a] = true;
                   b=urlSpaceSplit.length;
                }
            }
        }
       }
        boolean accepted = true;
        int limit = (int)Math.round(matches.length * 0.125);
        int misMatchCount = 0;
        for(int m=0; m<matches.length; m++){
            if(!matches[m]){
              misMatchCount++;
              if(misMatchCount > limit){
                 accepted = false;
                  break;
              }
            }
        }
        if(accepted) {
            count++;
            if(!isWikiUsage){
            mainInt.setMatchFound(j+startIndex);
            }
            System.out.println("Net key : " + netKey);
            System.out.println("URL key : "+ urlKeys.get(j));
        }
        }
        }
    }
    return count;
}


private boolean isPartialMatch(String urlKey, String netKey){
    boolean match = false;
   // String[] partsOfURL = allurlKey.split(" ");
   // for(int iter=0; iter<partsOfURL.length; iter++){
   // String urlKey = partsOfURL[iter];
    if(netKey.length() >= 5){
    if(netKey.length() == urlKey.length()){
        int keyLength = netKey.length();
        int maxSize = (int)Math.round(netKey.length()*0.1);
        for(int i=1; i<=maxSize; i++){
           String tempNetKey = netKey.substring(0, keyLength-i);
           if(urlKey.toLowerCase().startsWith(tempNetKey.toLowerCase())){
              match =true;
              break;
           }
        }
    }else if(netKey.length()< urlKey.length()){
        int keyLength = netKey.length();
        int allowedFirstMatch = (int)Math.round(urlKey.length() * 0.5);
        if(urlKey.toLowerCase().startsWith(netKey)){
            if((keyLength>= allowedFirstMatch)){
             match = true;
            }
            else{
                match = false;
            }
        }else{
        int maxSize = (int)Math.round(netKey.length()*0.1);
        for(int i=1; i<=maxSize; i++){
           String tempNetKey = netKey.substring(0, keyLength-i);
           if(urlKey.toLowerCase().startsWith(tempNetKey)){
              match =true;
              break;
           }
        }
        }
    }else{
         int keyLength = urlKey.length();
           int allowedFirstMatch = (int)Math.round(netKey.length() * 0.5);
        if(netKey.toLowerCase().startsWith(urlKey)){
             if((keyLength>= allowedFirstMatch)){
             match = true;
            }
            else{
                match = false;
            }
        }
        else{
        int maxSize = (int)Math.round(urlKey.length()*0.1);
        //if(urlKey.length()>= keyLength-maxSize){
        for(int i=1; i<=maxSize; i++){
           String tempNetKey = urlKey.substring(0, keyLength-i);
           if(netKey.toLowerCase().startsWith(tempNetKey)){
              match =true;
              break;
           }
       // }
        }
        }
    }
    }
    return match;
}
public Vector<String> getKeywordsURL(String url){
    Vector<String> keywords = new Vector<String>();
    this.getMetaTagsInfor(keywords, url);
    return keywords;
}

private void getMetaTagsInfor(Vector<String> keywords, String url){
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

            Elements e = Jsoup.parse(allcontent).select("meta[name=keywords]");
            Element ele = e.first();
             if(ele != null){
            String desc = ele.attr("content");
            String[] tags = desc.split(",");
            for(int i=0; i<tags.length; i++){
                String tag = tags[i].trim().toLowerCase();
                if(!keywords.contains(tag)){
                    keywords.add(tag);
                }
            }

            }
            else{
               // doc = Jsoup.connect(url).get();
             e = Jsoup.parse(allcontent).select("meta[name=Keywords]");
            ele = e.first();
            if(ele != null){
            String desc = ele.attr("content");
            String[] tags = desc.split(",");
            for(int i=0; i<tags.length; i++){
                String tag = tags[i].trim().toLowerCase();
                if(!keywords.contains(tag)){
                    keywords.add(tag);
                }
            }

            }
            else{
            // doc = Jsoup.connect(url).get();
            e = Jsoup.parse(allcontent).select("meta[name=KEYWORDS]");
            ele = e.first();
            if(ele != null){
            String desc = ele.attr("content");
            String[] tags = desc.split(",");
            for(int i=0; i<tags.length; i++){
                String tag = tags[i].trim().toLowerCase();
                if(!keywords.contains(tag)){
                    keywords.add(tag);
                }
            }

            }
            }
            }
            e = Jsoup.parse(allcontent).select("meta[name=description]");
            ele = e.first();
             if(ele != null){
            String desc = ele.attr("content");
            String[] tags = desc.split(",");
            for(int i=0; i<tags.length; i++){
                String tag = tags[i].trim().toLowerCase();
                if(!keywords.contains(tag)){
                    keywords.add(tag);
                }
            }
            }
             else{
                e = Jsoup.parse(allcontent).select("meta[name=Description]");
            ele = e.first();
             if(ele != null){
            String desc = ele.attr("content");
            String[] tags = desc.split(",");
            for(int i=0; i<tags.length; i++){
                String tag = tags[i].trim().toLowerCase();
                if(!keywords.contains(tag)){
                    keywords.add(tag);
                }
            }
            }
             else{
             e = Jsoup.parse(allcontent).select("meta[name=DESCRIPTION]");
            ele = e.first();
             if(ele != null){
            String desc = ele.attr("content");
            String[] tags = desc.split(",");
            for(int i=0; i<tags.length; i++){
                String tag = tags[i].trim().toLowerCase();
                if(!keywords.contains(tag)){
                    keywords.add(tag);
                }
            }
            }
             }
             }
            System.out.println(keywords);
        } catch (IOException ex) {
            Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
        }
}

public void processWiki(String keyword){
    Vector<String> wikiKeys = this.getKeysFromWiki(keyword);
    this.checkWikiAboutWord(wikiKeys);
    this.checkWikiIsAKeyword(wikiKeys);
    this.printAllNets();
    System.out.println();
    this.findSuitableMatches(wikiKeys);
}
private void checkWikiIsAKeyword(Vector<String> wikiKeys){
   // Vector<String> wikiKeys = this.getKeysFromWiki(keyword);
   // Vector<String> priority = new Vector<String>();
//    String isAbout = "";
//    String isA = "";
   // String matchAbout = "article is about";
    String[] match = {"is a", " is category", "is known as", "is", "are"};
    for(int i=0; i<wikiKeys.size(); i++){
       for(int g=0; g<match.length; g++){
        if(wikiKeys.get(i).contains(match[g])){
            String wikiAbout = wikiKeys.get(i);
            for(int j=0; j<this.mainInts.size(); j++){
                if(wikiAbout.toLowerCase().contains(this.mainInts.get(j).getName().toLowerCase())){
                    this.mainInts.get(j).setIsWiki(true);
                }
                MainInterest main = this.mainInts.get(j);
                for(int k=0; k<main.getSubInts().size(); k++){
                SubInterest sub = main.getSubInts().get(k);
                this.mainProcessingWiki_isA(sub, wikiAbout);
                if(sub.isHasNextLayer()){
                    Vector<SubInterest> child = sub.getAllChildSubInterests();
                    for(int a=0; a<child.size(); a++){
                        SubInterest oneChild = child.get(a);
                        this.mainProcessingWiki_isA(oneChild, wikiAbout);
                    }
                }
//                    String subName = main.getSubInts().get(k).getName().toLowerCase();
//                    if(wikiAbout.toLowerCase().contains(main.getSubInts().get(k).getName().toLowerCase())){
//                        main.getSubInts().get(k).setIsWiki(true);
//                        main.setIsWiki(true);
//                    }
//                    else{
//                    String[] allkeys = subName.split(" ");
//                    boolean[] matches = new boolean[allkeys.length];
//
//                    for(int a=0; a<allkeys.length; a++){
//                             String oneKey = allkeys[a];
//                         if(wikiAbout.toLowerCase().contains(oneKey)){
//                            matches[a] = true;
//                            }
//                        else{
//                            if(this.isPartialMatch(wikiAbout.toLowerCase(), oneKey)){
//                                 matches[a] = true;
//                            }
//            }
//        }
//        boolean accepted = true;
//        int limit = (int)Math.round(matches.length * 0.125);
//        int misMatchCount = 0;
//        for(int m=0; m<matches.length; m++){
//            if(!matches[m]){
//              misMatchCount++;
//              if(misMatchCount> limit){
//                 accepted = false;
//                  break;
//              }
//            }
//        }
//        if(accepted) {
//            main.setIsWiki(true);
//            main.getSubInts().get(k).setIsWiki(true);
//        }
//                    }
                }
            }

        }
       }
    }
}

private void mainProcessingWiki_isA(SubInterest sub, String wikiAbout){
                    String subName = sub.getName().toLowerCase();
                    if(wikiAbout.toLowerCase().contains(sub.getName().toLowerCase())){
                        sub.setIsWiki(true);
                        sub.getMainInt().setIsWiki(true);
                    }
                    else{
                    String[] allkeys = subName.split(" ");
                    boolean[] matches = new boolean[allkeys.length];

                    for(int a=0; a<allkeys.length; a++){
                             String oneKey = allkeys[a];
                         if(wikiAbout.toLowerCase().contains(oneKey)){
                            matches[a] = true;
                            }
                        else{
                            if(this.isPartialMatch(wikiAbout.toLowerCase(), oneKey)){
                                 matches[a] = true;
                            }
            }
        }
        boolean accepted = true;
        int limit = (int)Math.round(matches.length * 0.125);
        int misMatchCount = 0;
        for(int m=0; m<matches.length; m++){
            if(!matches[m]){
              misMatchCount++;
              if(misMatchCount> limit){
                 accepted = false;
                  break;
              }
            }
        }
        if(accepted) {
            sub.getMainInt().setIsWiki(true);
            sub.setIsWiki(true);
            if(sub.isParentSubInterest()){
                sub.getParentSubInterest().setIsWiki(true);
            }
        }
                    }

}

private void printAllNets(){
        for(int i=0; i<this.mainInts.size(); i++){
            MainInterest m = this.mainInts.get(i);
            System.out.println(m.getName() +" : "+ m.getMatches());
            for(int j=0; j<m.getSubInts().size(); j++){
                System.out.println(m.getSubInts().get(j).getName() + " : "+ m.getSubInts().get(j).getMatches());
                if((m.getSubInts().get(j).isHasNextLayer())){
                    Vector<SubInterest> allchild = m.getSubInts().get(j).getAllChildSubInterests();
                   for(int a=0; a<allchild.size(); a++){
                       System.out.println(allchild.get(a).getName() + " : "+ allchild.get(a).getMatches());
                   }
                }
                System.out.println("-----------------------------------------------");
            }
            System.out.println("###################################################");
        }
}

private void checkWikiAboutWord(Vector<String> wikiKeys){
    // Vector<String> wikiKeys = this.getKeysFromWiki(keyword);
    Vector<String> priority = new Vector<String>();
    String isAbout = "";
    String isA = "";
    String matchAbout = "article is about";
    //String[] match = {"is a", " is a category", "is known as", "is", "are", "general te"};
    for(int i=0; i<wikiKeys.size(); i++){
        if(wikiKeys.get(i).contains(matchAbout)){
            String wikiAbout = wikiKeys.get(i);
            for(int j=0; j<this.mainInts.size(); j++){
                if(wikiAbout.toLowerCase().contains(this.mainInts.get(j).getName().toLowerCase())){
                    this.mainInts.get(j).setIsWiki(true);
                }
                MainInterest main = this.mainInts.get(j);
                for(int k=0; k<main.getSubInts().size(); k++){
                     SubInterest sub = main.getSubInts().get(k);
                this.mainProcessingWiki_about(sub, wikiAbout);
                if(sub.isHasNextLayer()){
                    Vector<SubInterest> child = sub.getAllChildSubInterests();
                    for(int a=0; a<child.size(); a++){
                        SubInterest oneChild = child.get(a);
                        this.mainProcessingWiki_about(oneChild, wikiAbout);
                    }
                }
//                    String subName = main.getSubInts().get(k).getName().toLowerCase();
//                    if(wikiAbout.toLowerCase().contains(main.getSubInts().get(k).getName().toLowerCase())){
//                        main.getSubInts().get(k).setIsWiki(true);
//                        main.setIsWiki(true);
//                    }
//                    else{
//                    String[] allkeys = subName.split(" ");
//                    boolean[] matches = new boolean[allkeys.length];
//
//                    for(int a=0; a<allkeys.length; a++){
//                             String oneKey = allkeys[a];
//                         if(wikiAbout.toLowerCase().contains(oneKey)){
//                            matches[a] = true;
//                            }
//                        else{
//                            if(this.isPartialMatch(wikiAbout.toLowerCase(), oneKey)){
//                                 matches[a] = true;
//                            }
//            }
//        }
//        boolean accepted = true;
//        int limit = (int)Math.round(matches.length * 0.125);
//        int misMatchCount = 0;
//        for(int m=0; m<matches.length; m++){
//            if(!matches[m]){
//              misMatchCount++;
//              if(misMatchCount > limit){
//                  accepted = false;
//                  break;
//              }
//            }
//        }
//        if(accepted) {
//            main.setIsWiki(true);
//            main.getSubInts().get(k).setIsWiki(true);
//        }
//                    }
                }
            }

        }
    }
}


private void mainProcessingWiki_about(SubInterest sub, String wikiAbout){
     String subName = sub.getName().toLowerCase();
                    if(wikiAbout.toLowerCase().contains(sub.getName().toLowerCase())){
                        sub.setIsWiki(true);
                        sub.getMainInt().setIsWiki(true);
                    }
                    else{
                    String[] allkeys = subName.split(" ");
                    boolean[] matches = new boolean[allkeys.length];

                    for(int a=0; a<allkeys.length; a++){
                             String oneKey = allkeys[a];
                         if(wikiAbout.toLowerCase().contains(oneKey)){
                            matches[a] = true;
                            }
                        else{
                            if(this.isPartialMatch(wikiAbout.toLowerCase(), oneKey)){
                                 matches[a] = true;
                            }
            }
        }
        boolean accepted = true;
        int limit = (int)Math.round(matches.length * 0.125);
        int misMatchCount = 0;
        for(int m=0; m<matches.length; m++){
            if(!matches[m]){
              misMatchCount++;
              if(misMatchCount > limit){
                  accepted = false;
                  break;
              }
            }
        }
        if(accepted) {
            sub.getMainInt().setIsWiki(true);
            sub.setIsWiki(true);
             if(sub.isParentSubInterest()){
                sub.getParentSubInterest().setIsWiki(true);
            }
        }
                    }
}
private Vector<String> getKeysFromWiki(String keyword){
       String html = this.getHTMLCode(keyword);
if(html!=null){
      String content = Jsoup.parse(html).text();
      Vector<String> wikiKeys = new Vector<String>();
      int end = content.indexOf("Contents 1");
      content = content.substring(0, end);
      String[] sent = content.split("[.]");
      int limit = 0;
      if(sent.length> this.noOfLineWiki){
          limit = (sent.length + (this.noOfLineWiki))/2;
      }
      else{
          limit = sent.length;
      }
      for(int i=0; i<limit; i++){
          wikiKeys.add(sent[i]);
      }
      return wikiKeys;
    }
 else{
          return null;
 }
}

private String getHTMLCode(String keyword){
     try {
            keyword = keyword.replaceAll(" ", "_");
            String allcontent = "";
            String content = "";
            URL searchUrl = new URL("http://en.wikipedia.org/wiki/" + keyword);
            URLConnection yc = searchUrl.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
             // System.out.println(inputLine);
                allcontent = allcontent + inputLine;
            }
           // System.out.println(allcontent);
            in.close();
            return allcontent;

        } catch (MalformedURLException ex) {
            Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
          return "";
        }catch (IOException ex) {
            Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
}

//this is the method we shoould call for all keys and links..
public void Process(){
    //NewInterest newIntrest = new NewInterest();
    this.clearMainInterestNewInterestData();
    for(int i=0; i<urls.size(); i++){
        this.findSuitableMatch(urls.get(i));
    }
    this.setPercentageMatchNewInterest();
    this.processWiki(keyword);

    this.printAllNets();
//    for(int i=0; i<this.mainInts.size(); i++){
//        MainInterest main = this.mainInts.get(i);
//        System.out.println(main.getName()+" : " +main.getMatches());
//
//        for(int j=0; j<main.getSubInts().size(); j++){
//            SubInterest sub = main.getSubInts().get(j);
//            System.out.println(sub.getName()+" : "+ sub.getMatches());
//            System.out.println("--------------------------------------");
//        }
//        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
//    }
}

private void setPercentageMatchNewInterest(){
    for(int i=0; i<this.mainInts.size(); i++){
        this.mainInts.get(i).findPercentageMatchNewInterest();
    }
}
public static void main(String[] args) throws UnsupportedEncodingException, MalformedURLException, IOException{
    Vector<String> urls = new Vector<String>();
    //urls.add("https://www.sareeonline.com/proj/home/default.aspx");
    urls.add("http://www.bindaasbharat.com/toptenmoviename.aspx?tmovieid=1074");
    urls.add("http://www.dailymotion.com/video/x5hj11_titanic-full-movie-online_shortfilms");
    Processor p = new Processor("Titanic",urls);
    p.createInterest();
    p.Process();
  p.callAlgo_Class();
    //p.findSuitableMatch("http://www.magickeys.com/books/");
    //p.findSuitableMatch("http://www.eldersbookstore.com/");
    //p.findSuitableMatch("http://www.amazon.com/Science-Books/b?ie=UTF8&node=75");

    //http://www.world-newspapers.com/business.html
    //p.findSuitableMatch("http://www.world-newspapers.com/business.html");
    //http://www.taste.com.au/
   // p.findSuitableMatch("http://www.taste.com.au/");
//   Vector<String> url = new Vector<String>();
//   url.add("http://www.whsmith.co.uk/CatalogAndSearch/BooksFoodAndDrink.aspx");
//   p.Process("CookBook", url);
//    System.out.println("---------------------------------------------------------");
//    p.printAllNets();
//    p.callAlgo_Class();
p.getNationality();
//   Vector<String> urls =p.addFinance();
//  p.assignKeywordsForInterest(p.getInterest("Finance"), urls, true);
//
//  urls =p.addBanks();
//  p.assignKeywordsForInterest(p.getInterest("Banks"), urls, true);
//
//  urls =p.addInsurance();
//  p.assignKeywordsForInterest(p.getInterest("Insurance"), urls, true);
//
//   urls =p.addStock();
//  p.assignKeywordsForInterest(p.getInterest("Stock Market"), urls, true);

   // p.processWiki("saree");
}

public Object[] callAlgo_Class(){
 FinalAlgo_Interest algo=new FinalAlgo_Interest(this.mainInts, this.urls, this.keyword);
// algo.find_mainInterest();
// algo.find_subInterest();
 Object[] obj = algo.control();
 Object[] temp = new Object[obj.length+1];
 for(int i=0; i<temp.length; i++){
     if(i==temp.length-1){
         temp[i] = algo.isIsNewInterest();
     }
     else{
         temp[i] = obj[i];
     }
 }
 return temp;
// System.out.println("--------------------------------------------Results----------------------------------------");
// if(obj!=null){
//    if(algo.isIsNewInterest()){
//        String key = (String)obj[2];
//        Vector<String> url = (Vector<String>)obj[3];
//        System.out.println("It's a new interest");
//        System.out.println("Keyword:" +key );
//        System.out.println("Urls:" +url );
//    }
//    else{
//        Vector<MainInterest> mains =(Vector<MainInterest>)obj[0];
//        Vector<SubInterest> subs = (Vector<SubInterest>)obj[1];
//        String key = (String)obj[2];
//        Vector<String> url = (Vector<String>)obj[3];
//        System.out.println("It's a existing interest");
//        System.out.println("Main Interest:");
//        for(int i=0; i<mains.size(); i++){
//            System.out.println(mains.get(i).getName());
//        }
//        System.out.println("Sub Interest:");
//         for(int i=0; i<subs.size(); i++){
//            System.out.println(subs.get(i).getName());
//        }
//        System.out.println("Keyword:" +key );
//        System.out.println("Urls:" +url );
//    }
// }
// else{
//     System.out.println("The object return is null from control method");
// }

}
public String getNationality() throws UnsupportedEncodingException, MalformedURLException, IOException{
String nat="";
   for(int i=0;i<this.mainInts.size();i++){
       if(this.mainInts.get(i).isFinalSelection()){
 NationalityMatcher m=new NationalityMatcher(this.mainInts.get(i).getName(),this.keyword);
 nat= m.handler();
    }
   }
return nat;
}

public Object getInterest(String name){
    for(int i=0; i<this.mainInts.size(); i++){
        MainInterest main = this.mainInts.get(i);
        if(this.mainInts.get(i).getName().equalsIgnoreCase(name)){
            return this.mainInts.get(i);
        }
        for(int j=0; j<main.getSubInts().size(); j++){
           SubInterest sub = main.getSubInts().get(j);
           if(sub.getName().equalsIgnoreCase(name)){
            return sub;
        }
           if(sub.isHasNextLayer()){
               Vector<SubInterest> child = sub.getAllChildSubInterests();
               for(int a=0; a<child.size(); a++){
                   if(child.get(a).getName().equalsIgnoreCase(name)){
                       return child.get(a);
                   }
               }
           }
        }
    }
    return null;
}

private GoogleResults  getURL(String search1) throws UnsupportedEncodingException, MalformedURLException, IOException{
   //System.out.println("hiii"+this.outputName);
     String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
 String search = search1;

    String charset = "UTF-8";

    URL url = new URL(google + URLEncoder.encode(search, charset));
    Reader reader = new InputStreamReader(url.openStream(), charset);
    GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);
    return (results);
    }

private Vector<String> getURLForKeyword(String key){
    try{
        GoogleResults gr = this.getURL(key);
        Vector<String> urls1 = new Vector<String>();
          for(int k=0;k<gr.getResponseData().getResults().size();k++){
               urls1.add(gr.getResponseData().getResults().get(k).getUrl());
                    }
        return urls1;
    }
        catch (UnsupportedEncodingException ex) {
           // Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
            return null;
        } catch (MalformedURLException ex) {
           // Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println(ex.toString());
              return null;
        } catch (IOException ex) {
           // Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println(ex.toString());
              return null;
        }
}


public Object[] main1() throws UnsupportedEncodingException, MalformedURLException, IOException{
//   // Processor p = new Processor();
//    this.createInterest();
//    //p.findSuitableMatch("http://www.magickeys.com/books/");
//    //p.findSuitableMatch("http://www.eldersbookstore.com/");
//    //p.findSuitableMatch("http://www.amazon.com/Science-Books/b?ie=UTF8&node=75");
//
//    //http://www.world-newspapers.com/business.html
//    //p.findSuitableMatch("http://www.world-newspapers.com/business.html");
//    //http://www.taste.com.au/
//   // p.findSuitableMatch("http://www.taste.com.au/");
//
//   urls.add("https://www.sareeonline.com/proj/home/default.aspx");
////   this.Process("Saree", urls);//have to uncomment
//  // this.Process(keyword, urls);//have to uncomment
//
//
//    System.out.println("---------------------------------------------------------");
//    this.printAllNets();
//    this.callAlgo_Class();
////this.getNationality();
//   // p.processWiki("saree");

   //  Processor p = new Processor("Titanic",urls);

    this.createInterest();
    this.Process();
  return this.callAlgo_Class();
  //String nationalityOFInterest = this.getNationality();
}


private void assignKeywordsForInterest(Object interest, Vector<String> urls, boolean isSaveText){
    Vector<String> keys = this.getUniqueKeysFromURLs(urls);
    System.out.println("Extracted key : "+ keys);
    if(keys.size() != 0){
    boolean isMainInt = false;
       boolean isSubInt = false;
       MainInterest mainInt = null;
       SubInterest subInt = null;
       Vector<String> saveKey = null;
       String name = "";
       try{
           mainInt = (MainInterest)interest;
           this.addKeys(mainInt.getKeywords(), keys);
           saveKey =mainInt.getKeywords();
            name = mainInt.getName()+"/"+ mainInt.getName()+".txt";
           isMainInt = true;
           isSubInt = false;
           
       }catch(ClassCastException ex){
           isMainInt = false;
           isSubInt = false;
       }

       if(!isMainInt){
            try{
           
           subInt = (SubInterest)interest; 
           this.addKeys(subInt.getKeywords(), keys);
           saveKey = subInt.getKeywords();
            name = subInt.getMainInt().getName()+"/"+ subInt.getName()+".txt";
           isSubInt = true;
           isMainInt = false;
       }catch(ClassCastException ex){
           isMainInt = false;
           isSubInt = false;
       }
       }

       if(isSaveText){
       if(isMainInt | isSubInt){
           this.saveInterestText(saveKey, name);
       }
    }
    }
}

private Vector<String> getUniqueKeysFromURLs(Vector<String> urls){
    Vector<String> allKeys = new Vector<String> ();
    if(urls != null){
    for(int i=0; i<urls.size(); i++){
        String url = urls.get(i);
        Vector<String> keys = this.getKeywordsURL(url);
        this.addKeys(allKeys, keys);
    }
    }
    return allKeys;
}

private void addKeys(Vector<String> primary, Vector<String> secondary){
    for(int i=0; i<secondary.size(); i++){
        String key = secondary.get(i).trim().toLowerCase();
//        key = key.replaceAll("sri lanka", "");          //have to comment
//         key = key.replaceAll("Sri lanka", "");
//          key = key.replaceAll("Sri Lanka", "");
//           key = key.replaceAll("srilanka", "");
//           key = key.replaceAll("SriLanka", "");
//           key = key.replaceAll("Srilanka", "");
        if(!primary.contains(key)){
            primary.add(key);
        }

    }
}

private void saveInterestText(Vector<String> keys, String path){
     try {
         Writer output = null;
         String text = "";
 for(int i=0; i<keys.size(); i++){
     text = text + keys.get(i)+"\n";
 }
  File file = new File(path);
  output = new BufferedWriter(new FileWriter(file));
  output.write(text);
  output.close();
  System.out.println("Your file "+path+" has been written");
          } catch (IOException e){
              e.printStackTrace();
          }
}

private Vector<String> addBooksURL(){
    Vector<String> url = new Vector<String>();
    //books
    url.add("http://www.readprint.com/");
    url.add("http://www.readbookonline.net/");
    url.add("http://www.magickeys.com/books/");
    url.add("http://www.bartleby.com/");
    url.add("http://safaribooksonline.com/Corporate/Index/");
    url.add("http://www.lib.utexas.edu/books/etext.html");
    url.add("http://www.chapters.indigo.ca/home/?cookieCheck=1");
    return url;
}


private Vector<String> addProfessionalBooks(){
  Vector<String> gen = new Vector<String>();
    gen.add("http://www.professionalbooks.co.uk/");
    gen.add("http://www.amazon.com/Professional-Technical-Books/b?ie=UTF8&node=173507");
    gen.add("http://www.heinemann.com/books-multimedia.aspx");
    return gen;
}

private Vector<String> addAgriBooks(){
    Vector<String> gen = new Vector<String>();
    gen.add("http://www.amazon.com/Agricultural-Sciences-Books/b?ie=UTF8&node=226680");
    gen.add("http://www.phenotypebooks.co.uk/");
    gen.add("http://www.sustainableinsight.com.au/shop/agriculture-books.htm");
    gen.add("http://www.pearsoned.co.uk/bookshop/subject.asp?item=257");
    gen.add("http://www.landsmansbooks.com/books/index.aspx?r=0632058293");
    gen.add("http://www.cplbookshop.com/glossary/G6.htm");
    gen.add("http://www.springer.com/life+sciences/agriculture?SGWID=0-10028-0-0-0");
    gen.add("http://www.scipub.net/agriculture/index.html");
    return gen;
}

private Vector<String> addArchiBook(){
    Vector<String> gen = new Vector<String>();
    gen.add("http://www.architecture.com.au/architext/");
    gen.add("http://www.amazon.com/Vitruvius-Ten-Books-Architecture-Bks/dp/0486206459");
    gen.add("http://www.petermiller.com");
    gen.add("http://www.assouline.com/books-design.html");
    gen.add("http://www.greatbuildings.com/books.html");
    return gen;
}

private Vector<String> addBiologicalBook(){
    Vector<String> url = new Vector<String>();
    url.add("http://www.scipub.net/biology/biology-ecology-evaluation-arthropods.html");
    url.add("http://www.nature.com/nature/journal/v142/n3592/abs/142428a0.html");
    url.add("http://www.intechopen.com/subject/biological-sciences/");
    url.add("http://www.infinite-energy.com/iemagazine/issue34/bookreview_biotrans.html");
    return url;
}

private Vector<String> addJournalismBooks(){
    Vector<String> url = new Vector<String>();
    url.add("http://www.schindler.org/book.shtml");
    url.add("http://www.goodreads.com/shelf/show/immersion-journalism");
    url.add("http://www.buyselloldbooks.com/buy-books/category/36/Journalism");
    url.add("http://booksaboutjournalism.com/home_1.html");
    url.add("http://mindymcadams.com/tojou/2007/best-books-about-online-journalism/");
    return url;
}


private Vector<String> addComputerBooks(){
    Vector<String> url = new Vector<String>();
    url.add("http://freecomputerbooks.com/");
    url.add("http://www.barnesandnoble.com/u/computer-technology-technical-books-professional-books/379001257/");
    url.add("http://www.amazon.com/Computer-Books/lm/R1GNXG467PVEQ9");
    url.add("http://oreilly.com/");
    url.add("http://www.nerdbooks.com/");
    url.add("http://www.computerbooksonline.com/");
    url.add("http://www.onlinecomputerbooks.com/");
    url.add("http://www.freetechbooks.com/");
    url.add("http://www.compbook.com.sg/");
    return url;
}


//have to do...
private Vector<String> addEngineeringBooks(){
    Vector<String> url = new Vector<String>();
    url.add("http://www.e-booksdirectory.com/engineering.php");
    url.add("http://www.sciencebooksonline.info/engineering.html");
    url.add("http://www.amazon.com/Engineering-Books-New-amp-Old/lm/R34UKPJROGAZDT");
    url.add("http://bookboon.com/uk/textbooks/mechanics");
    url.add("http://www.onesmartclick.com/engineering/engineering.html");
    url.add("http://www.freetechbooks.com/software-engineering-f15.html");
    url.add("http://www.freebookcentre.net/Electronics/Engineering-Books-Online.html");
    url.add("http://www.electricalengineering-book.com/");
    url.add("http://www.bin95.com/ebooks/");
    return url;
}

private Vector<String> addPoliticalooks(){
    Vector<String> url = new Vector<String>();
    url.add("http://www.barnesandnoble.com/u/Political-Books-Politics-In-the-News/379001128/");
    url.add("http://politicalbooks.org/");
    url.add("http://www.amazon.com/Politics-Nonfiction-Books/b?ie=UTF8&node=11079");
    url.add("http://www.politicos.co.uk/pages/home.htm");
    url.add("http://www.civilserviceindia.com/subject/Political-Science/suggested-reading.html");
    url.add("http://www.nttam.com/political-science-books.html");
    url.add("http://www.textbooks.com/Catalog/SBD/General-and-Introductory-Political-Science.php?s=1");
    url.add("http://www.lawbooks-online.com/");
    url.add("http://www.lexisnexis.in/");
    return url;
}

private Vector<String> addmedicalBooks(){
    Vector<String> url = new Vector<String>();
    url.add("http://www.medicalbooks.com/");
    url.add("http://www.freebooks4doctors.com/");
    url.add("http://www.us.elsevierhealth.com/home.jsp?sgCountry=--");
    url.add("http://www.medicalbooks.com.au/");
    url.add("http://bookshop.blackwell.co.uk/jsp/buy.jsp?oid=-14586");
    url.add("http://www.barnesandnoble.com/u/medical-nursing-books-merck-manual-professional-books/379001253/");
    url.add("http://www.medicalbookcentre.com.au/");
    return url;
}

private Vector<String> addManagementBooks(){
    Vector<String> url = new Vector<String>();
    url.add("http://www.managementbooks.com.au/bookweb/");
    url.add("http://www.managementbooks.co.uk/");
    url.add("http://www.jimclemmer.com/");
    url.add("http://www.naaptol.com/buy/books/business_-_management.html");
    url.add("http://www.officeteam.com/hrbooks");
    url.add("http://www.bpp.com/learning-materials/our-products/business--management.aspx");
    return url;
}

private Vector<String> addPhysicalScBooks(){
    Vector<String> url = new Vector<String>();
    url.add("http://glencoe.com/sec/science/lep_science/physical_science/index.html");
    url.add("http://www.learn4good.com/bookstore/physical_science_books_cds.htm");
    url.add("http://www.textbooks.com/Catalog/MBR/General-Physical-Science.php?s=1");
    url.add("http://www.sciencea-z.com/scienceweb/domain/physical?domainId=3");
    return url;
}

private Vector<String> addPsycologicalBooks(){
    Vector<String> url = new Vector<String>();
    url.add("http://psychology.about.com/od/academicresources/tp/psychology-books-for-students.htm");
    url.add("http://www.onlinebooks4free.com/menu/psychology.html");
    url.add("http://www.molwick.com/en/ebooks/psychology.html");
    url.add("http://www.pearsoned.co.uk/bookshop/subject.asp?item=272");
    url.add("http://www.psypress.com/");
    return url;
}

private Vector<String> addArtistBooks(){
    Vector<String> url = new Vector<String>();
    url.add("");
    return url;
}
private Vector<String> addChlidrenBookURL(){
    Vector<String> url = new Vector<String>();
    //books
    url.add("http://www.magickeys.com/books/");
     url.add("http://childrensbooks.about.com/");
     url.add("http://www.write4kids.com/");
     url.add("http://www.cbomc.com/");
     url.add("http://www.kidsbooks.ca/default.aspx");
     url.add("http://www.scholastic.com/kids/stacks/books/");
     url.add("http://www.oprah.com/packages/kid-reading-list.html");
    return url;
}


private Vector<String> addEldersBookURL(){
    Vector<String> url = new Vector<String>();
    //books
    url.add("http://www.yabookscentral.com/");
    url.add("http://www.bookspot.com/youngadult.htm");
    return url;
}


private Vector<String> addCookeryBookURL(){
    Vector<String> url = new Vector<String>();
    //books
    url.add("http://food.sify.com/");
     url.add("http://www.whsmith.co.uk/CatalogAndSearch/BooksFoodAndDrink.aspx");
     url.add("http://www.nitamehta.com/");
     url.add("http://www.abebooks.co.uk/docs/Community/Featured/cookery-books.shtml");
     url.add("http://www.cookerybook.com/");
     url.add("http://www.cookery-books-online.co.uk/");
     url.add("http://www.foodtourist.com/ftguide/cookbook_reviews.htm");
     url.add("http://www.stylist.co.uk/life/the-best-cookbooks-of-all-time");
     url.add("http://www.foodtourist.com/ftguide/cookbook_reviews.htm");
    return url;
}

private Vector<String> addScinetificBookURL(){
    Vector<String> url = new Vector<String>();
    //books
    url.add("http://www.reiters.com/");
    url.add("http://www.sciambookclub.com/");
    url.add("http://www.uscibooks.com/");
    url.add("http://jupiterscientific.org/sciinfo/bestbooks.html");
    url.add("http://www.digitaljournal.com/article/56009");
    url.add("http://scienceblogs.com/confessions/2010/03/best_science_books_2009_librar_1.php");
    url.add("http://topsciencebooks.net/");
    url.add("http://www.freebooks4doctors.com/");
    url.add("http://www.e-booksdirectory.com/engineering.php");
    url.add("http://artikel-software.com/blog/");
    url.add("http://www.techbooksforfree.com/science.shtml");
    return url;
}

private Vector<String> addBusinessBookURL(){
    Vector<String> url = new Vector<String>();
    //books
    url.add("http://www.amazon.com/Business-Investing-Books/b?ie=UTF8&node=3");
    url.add("http://www.businessbookreview.com/");
    url.add("http://www.businessbookreview.com/");
    url.add("http://www.managementbooks.com.au/bookweb/");
    url.add("http://100bestbiz.com/more-on-the-100-best/");
    url.add("http://www.businessweek.com/bschools/books/");
    url.add("http://www.inc.com/best-business-books/");
    url.add("http://www.tripwiremagazine.com/2010/10/15-best-business-and-finance-magazines-for-entrepreneurs.html");
    url.add("http://www.businessweek.com/");
    url.add("http://www.kissmarketing.com/magazines.htm");
    url.add("http://www.chicagotribune.com/chi-040615mags,0,3835.story");

    return url;
}


private Vector<String> addMusicURL(){
    Vector<String> url = new Vector<String>();
    //books
    url.add("http://www.playlist.com/");
    url.add("http://grooveshark.com/#/?");
     url.add("http://www.jango.com/");
      url.add("http://www.mixpod.com/");
       url.add("http://www.youtube.com/music");
       url.add("http://new.music.yahoo.com/");
       url.add("http://www.youtube.com/music/pop");
       url.add("http://mashable.com/2010/04/25/best-youtube-cover-songs/");
       url.add("http://songs.pk/");
    return url;
}

private Vector<String> addLatestMusicURL(){
    //http://www.take40.com/music/new-music
    Vector<String> url = new Vector<String>();
    //books
    url.add("http://www.take40.com/music/new-music");
   url.add("http://www.billboard.com/charts/hot-100#/charts/hot-100");
    url.add("http://www.thehothits.com/music/new-music");
     url.add("http://www.latestmusic.com/");
      url.add("http://www.altnet.com/");
       url.add("http://web.orange.co.uk/p/musichub/");
        url.add("http://www.coverpop.com/pop/yt_music/");
        url.add("http://www.guardian.co.uk/music/2011/jul/23/this-weeks-new-live-music");
    return url;
}

private Vector<String> addOldMusic(){
    Vector<String> url = new Vector<String>();
    //books
    url.add("http://www.jr.co.il/videos/oldies-music-videos.htm");
    url.add("http://www.oldieslist.com/");
    url.add("http://ww.smashits.com/old-is-gold/songs-2287.html");
    url.add("http://www.oldiesmusic.com/");
    url.add("http://www.tropicalglen.com/");
    url.add("http://www.foreveroldies.com/");
    url.add("http://music.aol.com/radioguide/oldies-radio");
    url.add("http://www.escortsdosdonts.com/dos-donts-song-list-60s-music.php");
    url.add("http://www.live365.com/index.live");
    return url;
}

private Vector<String> addGames(){
     Vector<String> url = new Vector<String>();
    url.add("http://www.miniclip.com/games/en/");
    url.add("http://www.addictinggames.com/");
     url.add("http://www.games.com/");
      url.add("http://www.agame.com/");
       url.add("http://armorgames.com/");
        url.add("http://www.nick.com/games/");
         url.add("http://www.shockwave.com/home.jsp");
         url.add("http://games.yahoo.com/");
         url.add("http://www.freeonlinegames.com/");
    return url;
}

private Vector<String> addSimpleGames(){
     Vector<String> url = new Vector<String>();
   url.add("http://www.albinoblacksheep.com/games/verysimple");
   url.add("http://www.makeuseof.com/tag/16-free-fun-simple-addictive-games-to-take-the-edge-off/");
   url.add("http://www.easyminigames.com/");
   url.add("http://www.addictinggames.com/tag/easy-games.jsp");
   url.add("http://pbskids.org/games/");
   url.add("http://www.kidsgames.org/");
    url.add("http://www.playkidsgames.com/");
     url.add("http://kids.yahoo.com/games");
      url.add("http://www.free-online-games-to-play.net/games/kidsgames/");
       url.add("http://www.thekidzpage.com/");
        url.add("http://www.gameskidsplay.net/");

    return url;
}

private Vector<String> addAdvancedGames(){
     Vector<String> url = new Vector<String>();
   url.add("http://www.jjgames.com/system/gba");
   url.add("http://www.freeonlinegames6.com/Free_Online_elders_Games");
   url.add("http://www.pathoftheelders.com/game");
   url.add("http://www.elder-one-stop.com/elderly-games.html");
   url.add("http://www.nursinghomeactivitiesresource.com/games-for-seniors.shtml");
   url.add("http://www.addictinggames.com/tag/hard-games.jsp");
   url.add("http://www.sploder.com/games/tags/hard/");
   url.add("http://www.hardknoxgames.com/");
   url.add("http://www.gamersenterprise.com/");
   url.add("http://www.playtruckgames.org/game/hard-racing.html");
   url.add("http://www.cargames1.com/hardrace.htm");

    return url;
}

private Vector<String> addTechnology(){
     Vector<String> url = new Vector<String>();
   url.add("http://www.wikinvest.com/industry/Technology");
   url.add("http://www.popsci.com/technology");
    url.add("http://www.reuters.com/news/technology");
     url.add("http://www.guardian.co.uk/technology");
      url.add("http://www.nytimes.com/pages/technology/index.html");
       url.add("http://www.physorg.com/technology-news/");
        url.add("http://www.telegraph.co.uk/technology/");
         url.add("http://www.businessweek.com/technology/");
          url.add("http://www.forbes.com/technology/");

    return url;
}

private Vector<String> addKidsElectronics(){
     Vector<String> url = new Vector<String>();
   url.add("http://www.walmart.com/browse/Kids-Electronics/_/N-2rcxZaq90Zaqce/Ne-2p4f");
   url.add("http://www.target.com/c/toys-kids-electronics/-/N-5xt9q");
   url.add("http://www.fisher-price.com/fp.aspx?st=10&e=pelanding");
   url.add("http://www.toysrus.com/family/index.jsp?categoryId=3250978");
   url.add("http://www.overstock.com/Sports-Toys/Kids-Electronics/931/cat.html");
    return url;
}


private Vector<String> addNewTrends(){
     Vector<String> url = new Vector<String>();
//   url.add("http://techabsolute.com/");
//   url.add("http://www.webopedia.com/DidYouKnow/Computer_Science/2006/2005_terms.asp");
//   url.add("http://www.ciol.com/customsites.aspx");
//   url.add("http://www.trendhunter.com/cool-hunting/category/Technology-Trends-and-Gadgets");
//   url.add("http://www.gartner.com/it/page.jsp?id=1454221");
//   url.add("http://www.computerworld.com/s/article/352068/5_Trends_to_Watch_in_2011");
//   url.add("http://www.informationweek.com/news/smb/ebusiness/229201235");
//   url.add("http://www.intechweb.org/books/show/title/new-trends-in-technologies--devices--computer--communication-and-industrial-systems");
   url.add("http://www.health-infosys-dir.com/information_technology_trends.htm");
   url.add("http://www.exforsys.com/tutorials/technology-trends.html");
   return url;
}


private Vector<String> addMobilePhones(){
     Vector<String> url = new Vector<String>();
   url.add("http://www.lankaphones.com/");
   url.add("http://www.cellebrite.com/mobile-products/ume-36pro-universal-memory-exchanger.html?gclid=CISk0oXsuKsCFYMa6wodRj-5dA");
   url.add("http://www.gsmarena.com/");
   url.add("http://www.mobileburn.com/");
   url.add("http://www.e2save.com/");
   url.add("http://www.buyandsell.lk/sri-lanka/classified/cell-phones-pdas-cell-phones-smartphones-anything-0-0-95-0.html");
    return url;
}


private Vector<String> addLaptops(){
     Vector<String> url = new Vector<String>();
   url.add("http://laptop.lk/");
   url.add("http://www.dell.com/us/p/laptops");
   url.add("http://mcentre.lk/categories/Laptops/");
   url.add("http://reviews.cnet.com/laptops/");
   url.add("http://uk.computers.toshiba-europe.com/innovation/home/");
   url.add("http://www.harveynorman.com.au/page/1255508459989/computing-computers-laptops-netbooks");
   url.add("http://www.hp.com/sbso/busproducts_notebooks.html");
   url.add("http://www.freepcclinic.com/buy_laptop_in_srilanka.html");
   return url;
}


private Vector<String> addHouseHoldElectronics(){
     Vector<String> url = new Vector<String>();
   url.add("http://kaiteli.en.made-in-china.com/");
   url.add("http://www.africa-business.com/features/electronics.html");
   //for this one i have got the alliance list manually and entered
   return url;
}


private Vector<String> addFinance(){
      Vector<String> url = new Vector<String>();
   url.add("http://finance.yahoo.com/");
   url.add("http://www.reuters.com/finance");
   url.add("http://www.bestgrowthstock.com/");
   url.add("http://www.telegraph.co.uk/finance/");
   url.add("http://www.financesrilanka.com/");
   return url;

}

private Vector<String> addBanks(){
      Vector<String> url = new Vector<String>();
   url.add("http://www.hnb.net/");
   //i eneter manually
   return url;

}

private Vector<String> addInsurance(){
      Vector<String> url = new Vector<String>();
   url.add("http://www.srilankainsurance.com/");
   url.add("http://www.ceylinco-insurance.com/");
   url.add("http://www.allianz.lk/");
   url.add("http://www.unionassurance.com/english/index.php");
   //i eneter manually
   return url;

}

private Vector<String> addStock(){
      Vector<String> url = new Vector<String>();
   url.add("http://finance.yahoo.com/?u");
   url.add("http://www.livecharts.co.uk/");
   url.add("http://www.antharjalaya.com/invest-in-sri-lanka/");
   //i eneter manually
   return url;

}
}
