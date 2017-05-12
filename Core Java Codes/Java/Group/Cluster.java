/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Group;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Vector;

/**
 *
 * @author Dilusha
 */
public class Cluster implements Serializable{
private String name;
private double[] age;
private double[] nationality;
private double[] gender;
private double[] prof;
private Vector<SubGroup> subGrps;
//private Vector<String> interests;   //this should analyzed for the new interest thing, which involves classification and clustering.
//the below two for the clustering of existing interest, music, flims, etc
private Vector<PassengerDetails> passengers;    //this has the passenger details along with the passenger details and the interest name, urls.
private Vector<Interest> interest_url; //this has the interest name and the urls which we have to show if a new passenger comes
private int urlLimit;
private Vector<Interest> processedInterestsUrls; //this is the urls and the name of interest which is supposed to be shown for the passengers in this category
private Vector<Vector> filteredUrls; //this is going to hold the final filterest result from the processed Urls
                                             //each vector will be consisting oneMainInt_oneSubInt, the interestObject with natioanlity will be coming..
private int nationality_limit;
    public Cluster(double[] age, double[] nationality, double[] gender, double[] prof) {
        this.subGrps = new Vector<SubGroup>();
        this.age = age;
        this.nationality = nationality;
        this.gender = gender;
        this.prof = prof;
        this.name = age[0]+"_"+age[1]+"_"+nationality[0]+"_"+nationality[1]+"_"+gender[0]+"_"+gender[1]+"_"+prof[0]+"_"+prof[1];
        //this.interests = new Vector<String>();
        this.passengers = new Vector<PassengerDetails>();
        this.interest_url = new Vector<Interest>();
        this.processedInterestsUrls = new Vector<Interest>();
        this.urlLimit = 5;
        this.filteredUrls = new Vector<Vector>();
        this.nationality_limit  = 3;
    }

    public void addSubGroup(SubGroup subGrp){
        boolean found  = false;
        for(int i=0; i< subGrps.size(); i++){
            if(subGrps.get(i).getName().equals(subGrp.getName())){
                found = true;
                break;
            }
        }
        if(!found)this.subGrps.add(subGrp);
    }

    public double[] getAge() {
        return age;
    }

    public double[] getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public double[] getNationality() {
        return nationality;
    }

    public double[] getProf() {
        return prof;
    }

    public Vector<SubGroup> getSubGrps() {
        return subGrps;
    }

    public void addInterest(String ints, Vector<String> url){
        boolean found = false;
        for(int i=0; i<this.interest_url.size(); i++){
            if(ints.toLowerCase().equals(this.interest_url.get(i).getInterestname())){
                found = true;
                break;
            }
        }
        if(!found){
            Interest interest = new Interest(ints, url, "");
            this.interest_url.add(interest);
        }
    }

    public Vector<String> getInterests() {
        Vector<String> temp = new Vector<String>();
       for(int i=0; i<this.interest_url.size(); i++){
           temp.add(this.interest_url.get(i).getInterestname());
       }
       return temp;
    }


    public void addPassengerDetails(PassengerDetails p){
        this.passengers.add(p);
        this.interest_url.add(new Interest(p.getInterestName(), p.getUrls(), p.getInterest_nationality()));
        this.setProcessedInterestUrls();
        this.setFilteredUrls(p);
        
    }
    
    //here each interest nationality of the interest will be holding the each prcoessedurl vector objects.
    //by going through the number of pas in the interests we have to select high number of processed interest objects
    private void setProcessedInterestUrls(){
       PassengerDetails pasDet =  this.passengers.lastElement();
       String intName = pasDet.getInterestName();
       int index = this.isInProcessedInterestsUrls(intName, pasDet.getInterest_nationality());
       if(index != -1){
               this.alterTheUrlOfProcessedUrls(pasDet, index);
                   
       }
       else{
//           PassengerDetails anotherPas = this.isAnyPassengerAsThisBefore(pasDet);
//           if(anotherPas != null){
//               this.addToProcessedInterestUrls(anotherPas, pasDet);
//       }
           this.addToProcessedInterestUrls(pasDet, pasDet);
       }
    }

    
    private void addToProcessedInterestUrls(PassengerDetails prev,PassengerDetails cur){
//        Interest inte = new Interest(cur.getInterestName(), new Vector<String>(),cur.getInterest_nationality());
//        int count = 0;
//        for(int i=0; i<this.urlLimit; i++){
//            if(count<= this.urlLimit){
//            if((cur.getUrls().size()>i)){
//                inte.addUrl(cur.getUrls().get(i));
//               
//            }
//            if (prev.getUrls().size() >i){
//                inte.addUrl(prev.getUrls().get(i));
//                
//            }
//            count= inte.getUrls().size();
//            }
//            else{
//                break;
//            }
//        }
         Interest inte = new Interest(cur.getInterestName(), new Vector<String>(),cur.getInterest_nationality());
        int count = 0;
        for(int i=0; i<cur.getUrls().size(); i++){
            if(count<= this.urlLimit){
            inte.addUrl(cur.getUrls().get(i));
            }
            else{
                break;
            }
        }
        inte.incrementPassengerCount();
        this.processedInterestsUrls.add(inte);
    }
    
    
    private void setFilteredUrls(PassengerDetails pas){
       String intName=pas.getInterestName();
       String filterName = intName;
       Vector filterRes = new Vector<String>();
       filterRes.add(intName);
         Vector<Interest> interest = new Vector<Interest>();
        for(int i=0; i< this.processedInterestsUrls.size(); i++){
           String curName = this.processedInterestsUrls.get(i).getInterestname();
          if(curName.equalsIgnoreCase(intName)){
              interest.add(this.processedInterestsUrls.get(i));
          }
        }
        int[] count = new int[interest.size()];
        
        for(int i=0; i<interest.size(); i++){
            count[i] = interest.get(i).getPassengerCount();
        }
       Arrays.sort(count);
       for(int i=0; i< count.length; i++){
           if(i<this.nationality_limit){
           int val = count[count.length-1-i];
           Interest f = null;
                for(int j=0; j<interest.size(); j++){
                    if(interest.get(j).getPassengerCount() == val){
                        f = interest.get(j);
                        int temp =interest.size();
                        interest.remove(j);
                        j=temp;
                    }
                }
            if(f !=null){
                filterRes.add(f);
            }
           }
           else{
               break;
           }
       }
       if(filterRes.size()>1){
           this.removeFilteredInterest(intName);
           this.filteredUrls.add(filterRes);
       }
    }
    
    private void removeFilteredInterest(String intName){
        for(int i=0; i<this.filteredUrls.size(); i++){
            Vector v = (Vector)this.filteredUrls.get(i);
            if(v.firstElement().toString().equalsIgnoreCase(intName)){
                this.filteredUrls.remove(i);
                break;
            }
        }
    }
   
    private void alterTheUrlOfProcessedUrls(PassengerDetails pas, int index){
         Interest inter = this.processedInterestsUrls.get(index);
         Interest newInte = new Interest(pas.getInterestName(), new Vector<String>(), pas.getInterest_nationality());
        int count = 0;
        for(int i=0; i<this.urlLimit; i++){
            if(count<= this.urlLimit){
            if((pas.getUrls().size()>i)){
                newInte.addUrl(pas.getUrls().get(i));
                
            }
            if (inter.getUrls().size() >i){
                newInte.addUrl(inter.getUrls().get(i));
              
            }
            count = newInte.getUrls().size();
            }
            else{
                break;
            }
        }
        this.processedInterestsUrls.remove(index);
        newInte.setPassengerCount(inter.getPassengerCount()+1);
        this.processedInterestsUrls.add(newInte);
    }
    private int isInProcessedInterestsUrls(String intName, String interest_nationality){
        int index = -1;
        for(int i=0; i<this.processedInterestsUrls.size(); i++){
            if(this.processedInterestsUrls.get(i).getInterestname().equalsIgnoreCase(intName)){
                if(this.processedInterestsUrls.get(i).getInterest_nationality().equalsIgnoreCase(interest_nationality)){
                index = i;
                break;
                }
            }
        }
        return index;  
    }
    
    private PassengerDetails isAnyPassengerAsThisBefore(PassengerDetails pas){
        //the -1 is due to the fact that the last element is the same apssenger that is being checked.. so no need to check that pas details
        for(int i=0; i<this.passengers.size()-1; i++){
            if(this.passengers.get(i).getInterestName().equalsIgnoreCase(pas.getInterestName())){
                if(this.passengers.get(i).getInterest_nationality().equalsIgnoreCase(pas.getInterest_nationality())){
                    return this.passengers.get(i);
                }
            }
        }
        return null;
    }
    
    public Vector<Interest> getInterest_url() {
        return interest_url;
    }

    public Vector<PassengerDetails> getPassengers() {
        return passengers;
    }

    public Vector<Vector> getFilteredUrls() {
        return filteredUrls;
    }

    public Vector<Interest> getProcessedInterestsUrls() {
        return processedInterestsUrls;
    }

    
}
