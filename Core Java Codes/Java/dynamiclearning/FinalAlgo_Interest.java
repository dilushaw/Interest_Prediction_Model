/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dynamiclearning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Dilusha
 */
public class FinalAlgo_Interest {
    private Vector<MainInterest> priorityMain_Interest;
    private Vector<String> priorityMain_Interesttemp;
    private Vector<String> priority_prioritySub_Interest;
    private Vector<String> priority_notprioritySub_Interest;

    private Vector<String> not_priorityMain_Interest;
     private Vector<String> not_priority_prioritySub_Interest;
    private Vector<String> not_priority_notprioritySub_Interest;
   private boolean isMain[]=new boolean[9];//have to change to no.of interets
    private   boolean iswiki[]=new boolean[9];//have to change to no.of interets
    private   boolean isMax[]=new boolean[9];//have to change to no.of interets
     private  double matches[]=new double[9];//have to change to no.of interets
     private Vector<String> urls;
     private String keyword;
private Vector<MainInterest> mainInts;
private boolean isNewInterest;
private boolean isNewInterestMatchthreshold;
private int[] keywordMatch;
private int[] urlMatch;
private int[] titleMatch;
public FinalAlgo_Interest(Vector<MainInterest> mainInts, Vector<String> url, String keyword){

this.mainInts=mainInts;
this.urls = url;
this.keyword = keyword;
priorityMain_Interest =new Vector<MainInterest>();
//priority_prioritySub_Interest=new Vector<String>();
//priority_notprioritySub_Interest=new Vector<String>();
not_priorityMain_Interest=new Vector<String>();
not_priority_prioritySub_Interest=new Vector<String>();
not_priority_notprioritySub_Interest=new Vector<String>();
this.isNewInterestMatchthreshold = true;
this.isNewInterest =false;
this.keywordMatch = new int[this.mainInts.size()];
this.urlMatch= new int[this.mainInts.size()];
this.titleMatch = new int[this.mainInts.size()];
}

public void find_keyword_subInterest(Vector<MainInterest> likelyMain){
    if(this.isNewInterestMatchthreshold){
    int temp=0;
    int x=0;
   // System.out.println(this.isMain[0]);
for(int i=0;i<likelyMain.size();i++){
   //if(this.isMain[i]==true) {

       x=likelyMain.get(i).getSubInts().size();
    // int submatches[]=new int[x];
     //  temp=i;
       double submatches[]=new double[x];
       for(int j=0;j<x;j++){
//        System.out.println(this.mainInts.get(temp).getSubInts().get(j).getName());
//        System.out.println(this.mainInts.get(temp).getSubInts().get(j).getMatches());
        submatches[j] = likelyMain.get(i).getSubInts().get(j).getMatches();
       }
       if(x!=0){
       this.sortSubInterest(submatches, likelyMain.get(i));
}
   

  //     }
}
      



    }

}


public Object[] control(){
    Object[] main_sub = new Object[4];
    this.find_mainInterest();
    if(this.isNewInterestMatchthreshold){
       Object[] urlObj = this.processMainInterestURL(urls);
       Vector<MainInterest> urlMain = (Vector<MainInterest>) urlObj[1];
       Object[] titleObj = this.processMainInterestTitle(urls);
        Vector<MainInterest> titleMain = (Vector<MainInterest>) titleObj[1];
       this.setMatchKeywordArray();
       Vector<MainInterest> likelyMains = this.findLikelyMain(this.priorityMain_Interest, urlMain, titleMain);
       this.clearAllMaxArrays(likelyMains);
  
       this.find_keyword_subInterest(likelyMains);
   
       this.find_url_subInterest(likelyMains);
       this.find_Title_subInterest(likelyMains);
       Object[] obj = this.findSuitableMain_Sub(likelyMains);
       main_sub[0] = (Vector<MainInterest>)obj[0];
       main_sub[1] = (Vector<SubInterest>) obj[1];
       main_sub[2] =this.keyword;
      main_sub[3] = this.urls;
      return main_sub;
    }
    else{
        if(this.isNewInterest){
            main_sub[2] =this.keyword;
            main_sub[3] = this.urls;
            return main_sub;
        }
        else{
            for(int i=0; i<this.mainInts.size(); i++){
                MainInterest m = this.mainInts.get(i);
                if(this.mainInts.get(i).isFinalSelection()){
                    for(int j=0; j<m.getSubInts().size(); j++){
                        if(m.getSubInts().get(j).isFinalSelection()){
                        SubInterest s = m.getSubInts().get(j);
                        Vector<MainInterest> finalMain = new Vector<MainInterest>();
                        finalMain.add(m);
                         Vector<SubInterest> finalSub = new Vector<SubInterest>();
                        finalSub.add(s);
                        main_sub[0] = finalMain;
                        main_sub[1] = finalSub;
                         main_sub[2] =this.keyword;
                         main_sub[3] = this.urls;
                            return main_sub;
                        }
                    }
                }
            }
        }
    }
    return null;
}


private void clearAllMaxArrays(Vector<MainInterest> likely){
    for(int i=0; i<likely.size(); i++){
        likely.get(i).clearMaxArrays();
    }
}

private Object[] findSuitableMain_Sub(Vector<MainInterest> likelyMain){
    this.setTotalMaxInLikelyMains(likelyMain);
    int allSubIntSize = 0;
    for(int i=0; i<likelyMain.size(); i++){
        allSubIntSize = allSubIntSize + likelyMain.get(i).getSubInts().size();
    }
    int[] allMaxArray = new int[allSubIntSize];
    SubInterest[] allSubs = new SubInterest[allSubIntSize];
   for(int i=0; i<likelyMain.size(); i++){
        Vector<SubInterest> subs = likelyMain.get(i).getSubInts();
        for(int j=0; j<subs.size(); j++){
            allMaxArray[i+j] = likelyMain.get(i).getTotalMax()[j];
            allSubs[i+j] = subs.get(j);
        }
    }
    Vector<SubInterest> finalSubs = this.sortFinalSubInterestMatch(allMaxArray, allSubs);
    Vector<MainInterest> finalMains = new Vector<MainInterest>();
    for(int i=0; i<finalSubs.size(); i++){
        finalSubs.get(i).setFinalSelection(true);
        finalSubs.get(i).getMainInt().setFinalSelection(true);
        finalMains.add(finalSubs.get(i).getMainInt());
    }

    Object[] obj = new Object[2];
    obj[0] = finalMains;
    obj[1] = finalSubs;
    return obj;
}




private void setTotalMaxInLikelyMains(Vector<MainInterest> likelyMain){
    for(int i=0; i<likelyMain.size(); i++){
        MainInterest aMain = likelyMain.get(i);
        for(int j=0; j<aMain.getSubInts().size(); j++){
            aMain.getTotalMax()[j] = aMain.getSubInterestMaxKeyword()[j]+aMain.getSubInterestMaxURL()[j]+aMain.getSubInterestMaxTitle()[j];
        }
    }
}
private void find_url_subInterest(Vector<MainInterest> likelyMain){
    for(int i=0; i<likelyMain.size(); i++){
    Object[] obj = this.processSubInterestURLPerMainInt(urls, likelyMain.get(i));
    if((Boolean)obj[0]){
        Vector<SubInterest> url_subs = (Vector<SubInterest>)obj[1];
        this.setSubInterestMatchURLInMainInterest(url_subs, likelyMain.get(i));
    }
    }
}

private void find_Title_subInterest(Vector<MainInterest> likelyMain){
    for(int i=0; i<likelyMain.size(); i++){
    Object[] obj = this.processsubInterestTitlePerMainInterest(urls, likelyMain.get(i));
    if((Boolean)obj[0]){
        Vector<SubInterest> url_subs = (Vector<SubInterest>)obj[1];
        this.setSubInterestMatchTitleInMainInterest(url_subs, likelyMain.get(i));
    }
    }
}

private void setMatchKeywordArray(){
    for(int i=0; i<this.mainInts.size(); i++){
        if(this.mainInts.get(i).isIschosen()){
            this.keywordMatch[i] = 1;
        }
    }
}



private Vector<MainInterest> findLikelyMain(Vector<MainInterest> keywordMain,Vector<MainInterest> urlMain, Vector<MainInterest> titleMain){
   // Vector<MainInterest> common = new Vector<MainInterest>();
    int[] allcount = new int[this.mainInts.size()];
    for(int i=0; i<this.keywordMatch.length; i++){
        allcount[i] = this.keywordMatch[i]+this.urlMatch[i]+this.titleMatch[i];
    }
    Object[] obj = this.sortMainInterest(allcount);
    Vector<MainInterest> temp = new Vector<MainInterest>();
    int[] sorted = (int[])obj[0];
    MainInterest[] sortedInt = (MainInterest[])obj[1];
    temp.add(sortedInt[sortedInt.length-1]);
    for(int i=sorted.length-1; i>=0; i--){
        if(sorted[i] == sorted[i-1]){
            temp.add(sortedInt[i-1]);
        }
        else{
            break;
        }
    }

   return temp;
}

private Object[] sortMainInterest(int[] count){
    Object[] obj = new Object[2];
    MainInterest[] mainAr = new MainInterest[this.mainInts.size()];
    for(int i=0; i<this.mainInts.size();i++){
        mainAr[i]= this.mainInts.get(i);
    }
  int i, j,t=0;
  MainInterest temp;
  for(i = 0; i < count.length; i++){
  for(j = 1; j < (count.length-i); j++){
  if(count[j-1] > count[j]){
  t = count[j-1];
  temp = mainAr[j-1];
  count[j-1]=count[j];
  mainAr[j-1] = mainAr[j];
  count[j]=t;
  mainAr[j] = temp;
  }
  }
  }
  obj[0]=count;
  obj[1] = mainAr;
  return obj;
}


private void sortSubInterest(double[] count, MainInterest m){
    Object[] obj = new Object[2];
    SubInterest[] subAr = new SubInterest[m.getSubInts().size()];
    for(int i=0; i<subAr.length;i++){
        subAr[i]= m.getSubInts().get(i);
    }
  
  double t=0;
  SubInterest temp;
  for(int i = 0; i < count.length; i++){
  for(int j = 1; j < (count.length-i); j++){
  if(count[j-1] > count[j]){
  t = count[j-1];
  temp = subAr[j-1];
  count[j-1]=count[j];
  subAr[j-1] = subAr[j];
  count[j]=t;
  subAr[j] = temp;
  }
  }
  }
  Vector<SubInterest> te = new Vector<SubInterest>();
  te.add(subAr[subAr.length-1]);
    for(int i=count.length-1; i>=0; i--){
        if(i>0){
        if(count[i] == count[i-1]){
            te.add(subAr[i-1]);
        }
        }
        else{
            break;
        }
    }
  for(int i=0; i<m.getSubInts().size(); i++){
      String subName = m.getSubInts().get(i).getName();
      for(int j=0; j<te.size(); j++){
          if(te.get(j).getName().equalsIgnoreCase(subName)){
//              System.out.println("Keyword length: "+m.getSubInterestMaxKeyword().length);
//               System.out.println("interation: "+i);
              m.getSubInterestMaxKeyword()[i] = 1;
          }
      }
  }
}


private Vector<SubInterest> sortFinalSubInterestMatch(int[] allcount, SubInterest[] allsubs){
    Vector<SubInterest> finalSubs = new Vector<SubInterest>();
  int t = 0;
  SubInterest temp;
  for( int i = 0; i < allcount.length; i++){
  for( int j = 1; j < (allcount.length-i); j++){
  if(allcount[j-1] > allcount[j]){
  t = allcount[j-1];
  temp = allsubs[j-1];
  allcount[j-1]=allcount[j];
  allsubs[j-1] = allsubs[j];
  allcount[j]=t;
  allsubs[j] = temp;
  }
  }
  }

 //  Vector<SubInterest> te = new Vector<SubInterest>();
   Vector<String> nameOfmains = new Vector<String>();
  finalSubs.add(allsubs[allsubs.length-1]);
 nameOfmains.add(allsubs[allsubs.length-1].getName());
    for(int i=allcount.length-1; i>=0; i--){
        if(i>0){
        if(allcount[i] == allcount[i-1]){
            if(!this.isContains(allsubs[i-1].getMainInt().getName(), nameOfmains)){
            finalSubs.add(allsubs[i-1]);
            nameOfmains.add(allsubs[i-1].getName());
            }
        }
        }
        else{
            break;
        }
    }
  return finalSubs;
}

private boolean isContains(String name, Vector<String> primary){
    for(int i=0; i<primary.size(); i++){
        if(primary.get(i).equalsIgnoreCase(name)){
            return true;
        }
    }
    return false;
}
private void setSubInterestMatchURLInMainInterest(Vector<SubInterest> subs, MainInterest m){
    Vector<SubInterest> allSubs = m.getSubInts();
    for(int i=0; i<allSubs.size(); i++){
        String aSubName = allSubs.get(i).getName();
        for(int j=0; j<subs.size(); j++){
            if(aSubName.equalsIgnoreCase(subs.get(j).getName())){
                m.getSubInterestMaxURL()[i] = 1;
            }
        }
        if(allSubs.get(i).isHasNextLayer()){
           Vector<SubInterest> allChildSub = allSubs.get(i).getAllChildSubInterests();
             for(int j=0; j<allChildSub.size(); j++){
                String aChildSub = allChildSub.get(j).getName();
                for(int k=0; k<subs.size(); k++){
            if(aChildSub.equalsIgnoreCase(subs.get(k).getName())){
                m.getSubInterestMaxURL()[i] = 1;
            }
        }
        }
        }
    }
}

    public boolean isIsNewInterest() {
        return isNewInterest;
    }


private void setSubInterestMatchTitleInMainInterest(Vector<SubInterest> subs, MainInterest m){
    Vector<SubInterest> allSubs = m.getSubInts();
    for(int i=0; i<allSubs.size(); i++){
        String aSubName = allSubs.get(i).getName();
        for(int j=0; j<subs.size(); j++){
            if(aSubName.equalsIgnoreCase(subs.get(j).getName())){
                m.getSubInterestMaxTitle()[i] = 1;
            }
        }
         if(allSubs.get(i).isHasNextLayer()){
           Vector<SubInterest> allChildSub = allSubs.get(i).getAllChildSubInterests();
             for(int j=0; j<allChildSub.size(); j++){
                String aChildSub = allChildSub.get(j).getName();
                for(int k=0; k<subs.size(); k++){
            if(aChildSub.equalsIgnoreCase(subs.get(k).getName())){
                m.getSubInterestMaxTitle()[i] = 1;
            }
        }
        }
        }
    }
}
public void find_mainInterest(){

    this.isWikifor_main();
    this.matches_main();
   Vector<String> maxInterest= this.getMaxMatches();
if(this.isNewInterestMatchthreshold){
    //if(this.isNewInterest)
    if(maxInterest.size()==1){
      //  System.out.println("diluuuuuuuuuuuuuuuuuuuuuu");
for(int i=0;i<this.isMax.length;i++){

   if(this.isMax[i]==true) {
      if(this.iswiki[i]==true) {
          this.priorityMain_Interest.add(this.mainInts.get(i));
this.isMain[i]=true;
this.mainInts.get(i).setIschosen(true);
//          for(int j=0;j<this.iswiki.length;j++){
//  if(this.iswiki[j]==true&&(!(j==i))) {
//     if(this.matches[i]==this.matches[j]*0.90) {
//         this.not_priorityMain_Interest.add(this.mainInts.get(j).getName());
//     }
//  }
//    }


      }

 else{//if max is not wiki
          this.priorityMain_Interest.add(this.mainInts.get(i));
          this.isMain[i]=true;
          this.mainInts.get(i).setIschosen(true);
    for(int j=0;j<this.iswiki.length;j++){
  if(this.iswiki[j]==true&&(!(j==i))) {
     if(this.matches[i]==this.matches[j]*0.80) {
         this.not_priorityMain_Interest.add(this.mainInts.get(j).getName());
     }
  }
 
    }

 }
   }




}
    }
 else{//if two maxs are there
       
      for(int i=0;i<this.isMax.length;i++){
   if(this.isMax[i]==true) {
      if(this.iswiki[i]==true) {
          this.priorityMain_Interest.add(this.mainInts.get(i));
          this.isMain[i]=true;
          this.mainInts.get(i).setIschosen(true);
 }
      else{
            this.not_priorityMain_Interest.add(this.mainInts.get(i).getName());  
      }


    }
        }



       if(this.priorityMain_Interest.size()==0){//both dont have is wiki
         for(int i=0;i<this.isMax.length;i++){
   if(this.isMax[i]==true) {
      this.priorityMain_Interest.add(this.mainInts.get(i));
      this.isMain[i]=true;
      this.mainInts.get(i).setIschosen(true);
       for(int j=0;j<this.iswiki.length;j++){
  if(this.iswiki[j]==true&&(!(j==i))) {
     if(this.matches[i]==this.matches[j]*0.80) {
         this.not_priorityMain_Interest.add(this.mainInts.get(j).getName());
     }
   }
       }
  }

    }
    }
    }

   // System.out.println("Main Interest:"+this.priorityMain_Interest.get(0));
   // System.out.println(this.isMax[0]+" "+this.mainInts.get(0).getName()+" "+this.isMax[1]);
    }
}
private void isWikifor_main(){
  
for(int i=0;i<this.mainInts.size();i++){
     

    if(mainInts.get(i).isIsWiki()) iswiki[i]=true;
    for(int j=0;j<this.mainInts.get(i).getSubInts().size();j++){
  

if(mainInts.get(i).getSubInts().get(j).isIsWiki()) {

iswiki[i]=true;
}
    }
}

}

private void matches_main(){

    for(int i=0;i<this.mainInts.size();i++){
        double temp=0;
        for(int j=0;j<this.mainInts.get(i).getSubInts().size();j++){
          temp=temp+  this.mainInts.get(i).getSubInts().get(j).getMatches();
           System.out.println(temp+" "+"sub");
        }

this.matches[i]=temp+this.mainInts.get(i).getMatches();
System.out.println(this.matches[i]+"kkkkkkkkkkk");
this.mainInts.get(i).setsum(this.matches[i]);
    }
    
}

private Vector<String> getMaxMatches(){
   Vector<String> temp =new Vector<String>();
    Arrays.sort(matches);
    System.out.println(matches[0]);
      System.out.println(matches[1]);
    for(int i=0;i<this.mainInts.size();i++){
//System.out.print(this.mainInts.get(i).getsum()+"hereeeeeeeeeee");
if(this.matches[this.matches.length-1]==this.mainInts.get(i).getsum()){

    System.out.print(this.mainInts.get(i).getName());
     double x=(this.mainInts.get(i).getPercentage_newInterest());
       System.out.println("newInt_percentage:"+x);
    if(x >= 15.0){
        temp.add(this.mainInts.get(i).getName());
    this.isMax[i]=true;
    }
}
 else{
   this.isMax[i]=false;
 }
    }

//  for(int i=0;i<temp.size();i++){
//      this.priorityMain_Interest.add(temp.get(i));
//  }

 if(temp.size() == 0){
     this.isNewInterestMatchthreshold = false;
     Object[] url = this.processMainInterestURL(this.urls);
        Object[] title = this.processMainInterestTitle(this.urls);
        if((Boolean)url[0] & (Boolean)title[0]){
boolean commonMain=false;
Vector<MainInterest> commonmain = new Vector<MainInterest>();
             Vector<MainInterest> mainURL = (Vector<MainInterest>)url[1];
            Vector<MainInterest> titleURL = ( Vector<MainInterest>)title[1];
           for(int i=0;i<mainURL.size();i++){
               for(int j=0;j<titleURL.size();j++){
            if(mainURL.get(i).getName().equalsIgnoreCase(titleURL.get(j).getName())){
             commonMain=true;
             commonmain.add(mainURL.get(i));
            }
            }
             }
            if(commonMain){
                Object[] suburl = this.processSubInterestURL(urls, commonmain);
                Object[] subtitle = this.processsubInterestTitle(urls, commonmain);
                if((Boolean)suburl[0] & (Boolean)subtitle[0]){  //if there is atleast one common sub
                Vector<SubInterest> allsubUrl = (Vector<SubInterest>)suburl[1];
                Vector<SubInterest> allsubTitle = ( Vector<SubInterest>)subtitle[1];
                boolean commonsub = false;
                Vector<SubInterest> subCommon = new Vector<SubInterest>();
                for(int i=0;i<allsubUrl.size();i++){
               for(int j=0;j<allsubTitle.size();j++){
                if(allsubUrl.get(i).getName().equalsIgnoreCase(allsubTitle.get(j).getName())){
                    commonsub=true;
                    subCommon.add(allsubUrl.get(i));
            }
            }
             }
                if(subCommon.size()==1){
                    if(subCommon.get(0).isParentSubInterest()){
                        subCommon.get(0).getParentSubInterest().setFinalSelection(true);
                    }
                    subCommon.get(0).getMainInt().setFinalSelection(true);
                    subCommon.get(0).setFinalSelection(true);
                }
                else{
                    this.isNewInterest = true;
                }
            }
                else{
                    //if there no one sub interest match
                    if((Boolean)suburl[0]){
                        Vector<SubInterest> selectedSub = (Vector<SubInterest>)suburl[1];
                        if(selectedSub.size() ==1){
                             if(selectedSub.get(0).isParentSubInterest()){
                        selectedSub.get(0).getParentSubInterest().setFinalSelection(true);
                    }
                    selectedSub.get(0).getMainInt().setFinalSelection(true);
                    selectedSub.get(0).setFinalSelection(true);
                        }
                        else{
                            this.isNewInterest = true;
                        }
                    }
                    else if((Boolean)subtitle[0]){
                         Vector<SubInterest> selectedSub = (Vector<SubInterest>)subtitle[1];
                        if(selectedSub.size() ==1){
                             if(selectedSub.get(0).isParentSubInterest()){
                        selectedSub.get(0).getParentSubInterest().setFinalSelection(true);
                    }
                    selectedSub.get(0).getMainInt().setFinalSelection(true);
                    selectedSub.get(0).setFinalSelection(true);
                        }
                        else{
                            this.isNewInterest = true;
                        }
                    }
                    else{
                        this.isNewInterest = true;
                    }
                }
            }
            else{
                this.isNewInterest = true;
            }


            
            
        }
        else{
            if((Boolean)url[0]){
                Vector<MainInterest> mainURL = (Vector<MainInterest>)url[1];
                 Object[] suburl = this.processSubInterestURL(urls, mainURL);
                 Object[] subtitle = this.processsubInterestTitle(urls, mainURL);
                if((Boolean)suburl[0] & (Boolean)subtitle[0]){  //if there is atleast one common sub
                Vector<SubInterest> allsubUrl = (Vector<SubInterest>)suburl[1];
                Vector<SubInterest> allsubTitle = ( Vector<SubInterest>)subtitle[1];
                boolean commonsub = false;
                Vector<SubInterest> subCommon = new Vector<SubInterest>();
                for(int i=0;i<allsubUrl.size();i++){
               for(int j=0;j<allsubTitle.size();j++){
                if(allsubUrl.get(i).getName().equalsIgnoreCase(allsubTitle.get(j).getName())){
                    commonsub=true;
                    subCommon.add(allsubUrl.get(i));
            }
            }
             }
                if(subCommon.size()==1){
                    if(subCommon.get(0).isParentSubInterest()){
                        subCommon.get(0).getParentSubInterest().setFinalSelection(true);
                    }
                    subCommon.get(0).getMainInt().setFinalSelection(true);
                    subCommon.get(0).setFinalSelection(true);
                }
                else{
                    this.isNewInterest = true;
                }
            }
                else{
                    //if there no one sub interest match
                    if((Boolean)suburl[0]){
                        Vector<SubInterest> selectedSub = (Vector<SubInterest>)suburl[1];
                        if(selectedSub.size() ==1){
                             if(selectedSub.get(0).isParentSubInterest()){
                        selectedSub.get(0).getParentSubInterest().setFinalSelection(true);
                    }
                    selectedSub.get(0).getMainInt().setFinalSelection(true);
                    selectedSub.get(0).setFinalSelection(true);
                        }
                        else{
                            this.isNewInterest = true;
                        }
                    }
                    else if((Boolean)subtitle[0]){
                         Vector<SubInterest> selectedSub = (Vector<SubInterest>)subtitle[1];
                        if(selectedSub.size() ==1){
                             if(selectedSub.get(0).isParentSubInterest()){
                        selectedSub.get(0).getParentSubInterest().setFinalSelection(true);
                    }
                    selectedSub.get(0).getMainInt().setFinalSelection(true);
                    selectedSub.get(0).setFinalSelection(true);
                        }
                        else{
                            this.isNewInterest = true;
                        }
                    }
                    else{
                        this.isNewInterest = true;
                    }
                }

            }
            else if((Boolean)title[0]){
                  Vector<MainInterest> mainTitle = (Vector<MainInterest>)title[1];
                 Object[] suburl = this.processSubInterestURL(urls, mainTitle);
                 Object[] subtitle = this.processsubInterestTitle(urls, mainTitle);
                if((Boolean)suburl[0] & (Boolean)subtitle[0]){  //if there is atleast one common sub
                Vector<SubInterest> allsubUrl = (Vector<SubInterest>)suburl[1];
                Vector<SubInterest> allsubTitle = ( Vector<SubInterest>)subtitle[1];
                boolean commonsub = false;
                Vector<SubInterest> subCommon = new Vector<SubInterest>();
                for(int i=0;i<allsubUrl.size();i++){
               for(int j=0;j<allsubTitle.size();j++){
                if(allsubUrl.get(i).getName().equalsIgnoreCase(allsubTitle.get(j).getName())){
                    commonsub=true;
                    subCommon.add(allsubUrl.get(i));
            }
            }
             }
                if(subCommon.size()==1){
                    if(subCommon.get(0).isParentSubInterest()){
                        subCommon.get(0).getParentSubInterest().setFinalSelection(true);
                    }
                    subCommon.get(0).getMainInt().setFinalSelection(true);
                    subCommon.get(0).setFinalSelection(true);
                }
                else{
                    this.isNewInterest = true;
                }
            }
                else{
                    //if there no one sub interest match
                    if((Boolean)suburl[0]){
                        Vector<SubInterest> selectedSub = (Vector<SubInterest>)suburl[1];
                        if(selectedSub.size() ==1){
                             if(selectedSub.get(0).isParentSubInterest()){
                        selectedSub.get(0).getParentSubInterest().setFinalSelection(true);
                    }
                    selectedSub.get(0).getMainInt().setFinalSelection(true);
                    selectedSub.get(0).setFinalSelection(true);
                        }
                        else{
                            this.isNewInterest = true;
                        }
                    }
                    else if((Boolean)subtitle[0]){
                         Vector<SubInterest> selectedSub = (Vector<SubInterest>)subtitle[1];
                        if(selectedSub.size() ==1){
                             if(selectedSub.get(0).isParentSubInterest()){
                        selectedSub.get(0).getParentSubInterest().setFinalSelection(true);
                    }
                    selectedSub.get(0).getMainInt().setFinalSelection(true);
                    selectedSub.get(0).setFinalSelection(true);
                        }
                        else{
                            this.isNewInterest = true;
                        }
                    }
                    else{
                        this.isNewInterest = true;
                    }
                }
            }
            else{
                this.isNewInterest = true;
            }
        }
 }
return temp;
}

//First element should say boolean it has the mainInterest
//second element mainInt name
private Object[] processMainInterestURL(Vector<String> url){
    boolean mainURL = false;
    Vector<MainInterest> main = new Vector<MainInterest>();
    for(int i=0; i<url.size(); i++){
        String url_lowercase = url.get(i).toLowerCase();
        for(int j=0; j<this.mainInts.size(); j++){
            String mainIntLow = this.mainInts.get(j).getName().toLowerCase();
            if(url_lowercase.contains(mainIntLow)){
                main.add(this.mainInts.get(j));
                mainURL = true;
                this.urlMatch[j] = 1;
            }
        }
    }
    Object[] obj = new Object[2];
    obj[0] = mainURL;
    obj[1] = main;
    return obj;
}


private Object[] processSubInterestURL(Vector<String> url, Vector<MainInterest> mains){
    boolean subURL = false;
    Vector<SubInterest> subs = new Vector<SubInterest>();
    for(int m=0; m<mains.size(); m++){
       Vector<SubInterest> allSubs = mains.get(m).getSubInts();
    for(int i=0; i<url.size(); i++){
        String url_lowercase = url.get(i).toLowerCase();
        for(int j=0; j<allSubs.size(); j++){
            String subIntLow = allSubs.get(j).getName().toLowerCase();
            if(url_lowercase.contains(subIntLow)){
                subs.add(allSubs.get(j));
                subURL = true;
            }
            if(allSubs.get(j).isHasNextLayer()){
                Vector<SubInterest> thirdLayerSubs = allSubs.get(j).getAllChildSubInterests();
                for(int k=0; k<thirdLayerSubs.size(); k++){
                   String subChileIntLow = thirdLayerSubs.get(k).getName().toLowerCase();
                    if(url_lowercase.contains(subChileIntLow)){
                subs.add(allSubs.get(j));
                subURL = true;
            }
                }
            }
        }
    }
    }
    Object[] obj = new Object[2];
    obj[0] = subURL;
    obj[1] = subs;
    return obj;
}


private Object[] processSubInterestURLPerMainInt(Vector<String> url, MainInterest mains){
    boolean subURL = false;
    Vector<SubInterest> subs = new Vector<SubInterest>();
   // for(int m=0; m<mains.size(); m++){
       Vector<SubInterest> allSubs = mains.getSubInts();
    for(int i=0; i<url.size(); i++){
        String url_lowercase = url.get(i).toLowerCase();
        for(int j=0; j<allSubs.size(); j++){
            String subIntLow = allSubs.get(j).getName().toLowerCase();
            if(url_lowercase.contains(subIntLow)){
                subs.add(allSubs.get(j));
                subURL = true;
            }
            if(allSubs.get(j).isHasNextLayer()){
                Vector<SubInterest> thirdLayerSubs = allSubs.get(j).getAllChildSubInterests();
                for(int k=0; k<thirdLayerSubs.size(); k++){
                   String subChileIntLow = thirdLayerSubs.get(k).getName().toLowerCase();
                    if(url_lowercase.contains(subChileIntLow)){
                subs.add(allSubs.get(j));
                subURL = true;
            }
                }
            }
        }
    }

    Object[] obj = new Object[2];
    obj[0] = subURL;
    obj[1] = subs;
    return obj;
}


private Object[] processsubInterestTitle(Vector<String> urls, Vector<MainInterest> mains){
    boolean subTitle = false;
    Vector<SubInterest> subs = new Vector<SubInterest>();
    for(int m=0; m<mains.size(); m++){
       Vector<SubInterest> allSubs = mains.get(m).getSubInts();
     for(int i=0; i<urls.size(); i++){
        String url = urls.get(i);
        String title = "";
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

             Pattern p = Pattern.compile("<title>(.*?)</title>");
            
                 Matcher mm = p.matcher(allcontent);
    while (mm.find() == true) {
        title =title+mm.group();
        title.replaceAll("<title>", "");
        title.replaceAll("</title>", "");
    }
    } catch (IOException ex) {
            //Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("exce");
        }

     for(int j=0; j<allSubs.size(); j++){
            String subIntLow = allSubs.get(j).getName().toLowerCase();
            if(title.contains(subIntLow)){
                subs.add(allSubs.get(j));
                subTitle = true;
            }
             if(allSubs.get(j).isHasNextLayer()){
                Vector<SubInterest> thirdLayerSubs = allSubs.get(j).getAllChildSubInterests();
                for(int k=0; k<thirdLayerSubs.size(); k++){
                   String subChileIntLow = thirdLayerSubs.get(k).getName().toLowerCase();
                    if(title.contains(subChileIntLow)){
                subs.add(allSubs.get(j));
                subTitle = true;
            }
                }
            }
        }
    }
    }
    Object[] obj = new Object[2];
    obj[0] = subTitle;
    obj[1] = subs;
    return obj;
}


private Object[] processsubInterestTitlePerMainInterest(Vector<String> urls, MainInterest mains){
    boolean subTitle = false;
    Vector<SubInterest> subs = new Vector<SubInterest>();

       Vector<SubInterest> allSubs = mains.getSubInts();
     for(int i=0; i<urls.size(); i++){
        String url = urls.get(i);
        String title = "";
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

             Pattern p = Pattern.compile("<title>(.*?)</title>");
                 Matcher mm = p.matcher(allcontent);
    while (mm.find() == true) {
        title =title+mm.group();
        title.replaceAll("<title>", "");
        title.replaceAll("</title>", "");
    }
    } catch (IOException ex) {
            //Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("exce");
        }

     for(int j=0; j<allSubs.size(); j++){
            String subIntLow = allSubs.get(j).getName().toLowerCase();
            if(title.contains(subIntLow)){
                subs.add(allSubs.get(j));
                subTitle = true;
            }
             if(allSubs.get(j).isHasNextLayer()){
                Vector<SubInterest> thirdLayerSubs = allSubs.get(j).getAllChildSubInterests();
                for(int k=0; k<thirdLayerSubs.size(); k++){
                   String subChileIntLow = thirdLayerSubs.get(k).getName().toLowerCase();
                    if(title.contains(subChileIntLow)){
                subs.add(allSubs.get(j));
                subTitle = true;
            }
                }
            }
        }
    }

    Object[] obj = new Object[2];
    obj[0] = subTitle;
    obj[1] = subs;
    return obj;
}

private Object[] processMainInterestTitle(Vector<String> urls){
    boolean mainURL = false;
    Vector<MainInterest> main = new Vector<MainInterest>();
    for(int i=0; i<urls.size(); i++){
        String url_lowercase = urls.get(i).toLowerCase();
        String url = urls.get(i);
        String title = "";
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

             Pattern p = Pattern.compile("<title>(.*?)</title>");
             
    Matcher m = p.matcher(allcontent);
    while (m.find() == true) {
        title =title+m.group();
        title.replaceAll("<title>", "");
        title.replaceAll("</title>", "");
        System.out.println("Title: " + title);
    }
    } catch (IOException ex) {
            //Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("exce");
        }
        for(int j=0; j<this.mainInts.size(); j++){
            String mainIntLow = this.mainInts.get(j).getName().toLowerCase();
            if(title.contains(mainIntLow)){
                main.add(this.mainInts.get(j));
                mainURL = true;
                this.titleMatch[j] = 1;
            }
        }
    }
    Object[] obj = new Object[2];
    obj[0] = mainURL;
    obj[1] = main;
    return obj;



    }

}

