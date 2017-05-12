/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GsonTable;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author Dilusha
 */
public class Tuple implements Serializable{
    private String mainInterest;
    private String subInterest;
    private Vector<String> urls;
    private Vector<Integer> clickCount;
    private String name;
    private String nationality;
    private Vector<String> title;
    
    public Tuple(String mainInterest, String subInt, String nationality, Vector<String> urls, Vector<String> title){
        this.name = mainInterest+"_"+subInt+"_"+nationality;
        this.mainInterest = mainInterest;
        this.subInterest = subInt;
        this.nationality = nationality;
        this.urls = urls;
        this.title = title;
       clickCount = new Vector<Integer>();
        for(int i=0; i<urls.size(); i++){
            clickCount.add(0);
        }
    }
    
    public Vector<String> getUrls(){
        return this.urls;
    }

    public Vector<Integer> getClickCount() {
        return clickCount;
    }

    public String getMainInterest() {
        return mainInterest;
    }

    public String getName() {
        return name;
    }

    public String getSubInterest() {
        return subInterest;
    }

    public String getNationality() {
        return nationality;
    }

    public Vector<String> getTitle() {
        return title;
    }
    
    
    
    
}
