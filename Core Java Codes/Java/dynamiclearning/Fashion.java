/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dynamiclearning;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Vector;

/**
 *
 * @author Dilusha
 */
public class Fashion {
private Vector <String> nationswiki;
  private Vector <String> nationsU;
  private String fashionName;
    private ProcessorNat p;
    private Vector <String> allurls;

Fashion(ProcessorNat p,String fashionName){
this.p=p;
this.fashionName=fashionName;
this.allurls=new Vector <String> ();

}

public void handler() throws UnsupportedEncodingException, MalformedURLException, IOException{

  String x=(p.WikiSearch_otherinterests(fashionName));
  System.out.println(x);
  p.findWiki(x);





int wiki[]=p.getWiki();
boolean a=false;
  for(int i=0;i<wiki.length;i++){
     if(wiki[i]>0){
         a=true;
         break;

     }
 else{
         a=false;

 }
  }


if(a==true){
    System.out.println("diluuuuuuuuuuuuuuuuuuuuuuuuu");
         p.findMaxwiki();
       this.nationswiki=p.getMaxcountryWiki();
}
 else{
         
    System.out.println("pppppppppppppppppppppppppppppppppppppppppppp");
    //do dynamic search for the music and get the nation

  GoogleResults results= p.getURL(this.fashionName);

  for(int i=0;i<results.getResponseData().getResults().size();i++){
          this.allurls.add(results.getResponseData().getResults().get(i).getUrl());
   System.out.println(results.getResponseData().getResults().get(i).getUrl());



 }

 for(int i=0;i<this.allurls.size();i++){
   String q=  p.getHtml(this.allurls.get(i));
     p.findWiki(q);//find wiki is same for all url searches

    }
p.findMaxwiki();
      this.nationswiki=p.getMaxcountryWiki();
 }


}
    

//public String print(){
//String nat="";
//    if(this.nationswiki!=null){
//  for(int i=0;i<nationswiki.size();i++){
//      System.out.println(this.nationswiki.get(i));
//
//  }
//    }
// if(this.nationsU!=null){
//  for(int i=0;i<nationsU.size();i++){
//      System.out.println(this.nationsU.get(i));
//
//  }
//    }
//return nat;
//}

public String print(){
String nat="";
    if(this.nationswiki!=null){
  for(int i=0;i<nationswiki.size();i++){
      System.out.println(this.nationswiki.get(i));
nat= this.nationswiki.get(i);
  }
  return nat;
    }
// if(this.nationsU!=null){
//  for(int i=0;i<nationsU.size();i++){
//      System.out.println(this.nationsU.get(i));
//
//  }
//    }
    else{
        return "";
    }
}
    
}
