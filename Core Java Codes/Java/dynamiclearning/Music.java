/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dynamiclearning;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Vector;

/**
 *
 * @author Dilusha
 */
public class Music {
   private Vector <String> nationswiki;
  private Vector <String> nationsU;
  private String musicName;
    private ProcessorNat p;
    private Vector <String> allurls;

Music(ProcessorNat p,String musicName){
this.p=p;
this.musicName=musicName;
this.allurls=new Vector <String> ();

}
public void handler() throws UnsupportedEncodingException, MalformedURLException, IOException{

  String x=(p.WikiSearch_otherinterests(musicName));
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
          String y=  p.youtubeSearch(musicName);
       p.findYoutube(y);


       int youtube[]=p.getYoutube();
boolean b=false;
  for(int i=0;i<youtube.length;i++){
     if(youtube[i]>0){
         b=true;
         break;

     }
 else{
         b=false;

 }
  }


if(b==true){
    System.out.println("hereeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
       p.findMaxyou();
       this.nationsU=p.getMaxcountryU();
}
 else{
    System.out.println("pppppppppppppppppppppppppppppppppppppppppppp");
    //do dynamic search for the music and get the nation

  GoogleResults results= p.getURL(this.musicName);

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
    }



public String print(){
String nat="";
    if(this.nationswiki!=null){
  for(int i=0;i<nationswiki.size();i++){
      System.out.println(this.nationswiki.get(i));
nat=this.nationswiki.get(i);
  }
  return nat;
    }
 if(this.nationsU!=null){
  for(int i=0;i<nationsU.size();i++){
      System.out.println(this.nationsU.get(i));

  nat=this.nationswiki.get(i);
  }
  return nat;
    }
 else
     return null;

}



public void print2(){

    if(this.nationswiki!=null){
  for(int i=0;i<nationswiki.size();i++){
      System.out.println(this.nationswiki.get(i));

  }
    }
 if(this.nationsU!=null){
  for(int i=0;i<nationsU.size();i++){
      System.out.println(this.nationsU.get(i));

  }
    }

}


}
