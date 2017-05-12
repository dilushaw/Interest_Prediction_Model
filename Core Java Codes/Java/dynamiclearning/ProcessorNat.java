/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dynamiclearning;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Vector;

/**
 *
 * @author Dilusha
 */
public class ProcessorNat {
    private Vector<String> nationality_country;
    private Vector<String> nationality_countryUSA;//with usa
private Vector<Vector<String>> allNations;
private Vector<Vector<String>> allNationsUSA;

private Vector<String> maxcountryU;//max from u tube
private Vector<String> maxcountryWiki;//max from wiki


private int[]youtube;
private int[]wiki;
 private int allyoutube[];
 private int allwiki[];
private String interest;

    public Vector<String> getMaxcountryU() {
        return maxcountryU;
    }

    public Vector<String> getMaxcountryWiki() {
        return maxcountryWiki;
    }

    public int[] getWiki() {
        return wiki;
    }

    public int[] getYoutube() {
        return youtube;
    }


ProcessorNat(Vector<String> nationality_country,Vector<String> nationality_countryUSA,Vector<Vector<String>> allNations,Vector<Vector<String>> allNationsUSA){
    this.allNations=allNations;
      this.allNationsUSA=allNationsUSA;
      this.nationality_country=nationality_country;
      this.nationality_countryUSA=nationality_countryUSA;
    this.maxcountryU=new Vector<String>();
    this.maxcountryWiki=new Vector<String>();
   this.youtube=new int[this.allNations.size()];
    this.wiki=new int[this.allNationsUSA.size()];
    this.interest=interest;
   
}


 

 public String youtubeSearch(String interest){
    String interest_append="";
 String data[]=(interest.split(" "));
 for(int i=0;i<data.length;i++){

     if(i!=data.length-1){
 interest_append=interest_append+data[i]+"+";
     }

 else{
    interest_append=interest_append+data[i];
 }
         }

System.out.println(interest_append);
String fn="http://www.youtube.com/results?search_query="+interest_append+"&aq=f";
//String fn="http://en.wikipedia.org/wiki/Angels_in_the_Dust";
	StringBuilder text = new StringBuilder();

	try {
	        URL page = new URL(fn);

		// Connect to the webpage
		HttpURLConnection conn =
			(HttpURLConnection) page.openConnection();
		conn.connect();

                // Connect to the CONTENT and set
                // up a BufferedReader for it.
		InputStreamReader in = new InputStreamReader(
			(InputStream) conn.getContent());
		BufferedReader read = new BufferedReader(in);

		String line;
		do {
			line = read.readLine();
			if (line != null)
				text.append(line +"\n");
		} while (line != null);



	} catch (IOException e) {
		return "Error - :" + e.getMessage();
	}
System.out.println(text);
	return text.toString();


}
public void findYoutube(String text){
   System.out.println(this.allNations.size()+"*********************************");
  System.out.println(this.nationality_country.size()+"*********************************");


       String data[]=(text.split(","));
    for(int i=0;i<data.length/2;i++) {

        for(int j=0;j<this.allNations.size();j++){

   for(int k=0;k<this.allNations.get(j).size();k++)     {
    if(data[i].toLowerCase().contains(this.allNations.get(j).get(k).toLowerCase())){
    this.youtube[j]++;
}
   }



        }

        }




for(int j=0;j<this.youtube.length;j++){

if(this.youtube[j]>0){
System.out.println(this.allNations.get(j).get(0));

System.out.println(this.youtube[j]);
    }
        }

}

public String WikiSearch_films(String film){
  String film_append="";
 String data[]=(film.split(" "));
 for(int i=0;i<data.length-1;i++){

 film_append=film_append+data[i].substring(0, 1).toUpperCase()+data[i].substring(1).toLowerCase()+"_";

         }
film_append=film_append+data[data.length-1].substring(0, 1).toUpperCase()+data[data.length-1].substring(1).toLowerCase()+"_(film)";
System.out.println(film_append);
String fn="http://en.wikipedia.org/wiki/"+film_append;
	StringBuilder text = new StringBuilder();

	try {
	        URL page = new URL(fn);

		// Connect to the webpage
		HttpURLConnection conn =
			(HttpURLConnection) page.openConnection();
		conn.connect();

                // Connect to the CONTENT and set
                // up a BufferedReader for it.
		InputStreamReader in = new InputStreamReader(
			(InputStream) conn.getContent());
		BufferedReader read = new BufferedReader(in);

		String line;
		do {
			line = read.readLine();
			if (line != null)
				text.append(line +"\n");
		} while (line != null);



	} catch (IOException e) {

		return "Error - :" + e.getMessage();
	}
System.out.println(text);
	return text.toString();
}
public String WikiSearch_otherinterests(String interest){
  String interest_append="";
 String data[]=(interest.split(" "));
 for(int i=0;i<data.length-1;i++){

 interest_append=interest_append+data[i].substring(0, 1).toUpperCase()+data[i].substring(1).toLowerCase()+"_";

         }
interest_append=interest_append+data[data.length-1].substring(0, 1).toUpperCase()+data[data.length-1].substring(1).toLowerCase();
System.out.println(interest_append);
String fn="http://en.wikipedia.org/wiki/"+interest_append;
	StringBuilder text = new StringBuilder();

	try {
	        URL page = new URL(fn);

		// Connect to the webpage
		HttpURLConnection conn =
			(HttpURLConnection) page.openConnection();
		conn.connect();

                // Connect to the CONTENT and set
                // up a BufferedReader for it.
		InputStreamReader in = new InputStreamReader(
			(InputStream) conn.getContent());
		BufferedReader read = new BufferedReader(in);

		String line;
		do {
			line = read.readLine();
			if (line != null)
				text.append(line +"\n");
		} while (line != null);



	} catch (IOException e) {

		return "Error - :" + e.getMessage();
	}
System.out.println(text);
	return text.toString();
}

public void findWiki(String text){

 //this.wiki=new int[this.allNationsUSA.size()];
       String data[]=(text.split(","));
    for(int i=0;i<data.length/2;i++) {

        for(int j=0;j<this.allNationsUSA.size();j++){

   for(int k=0;k<this.allNationsUSA.get(j).size();k++)     {
    if(data[i].toLowerCase().contains(this.allNationsUSA.get(j).get(k).toLowerCase())){
    this.wiki[j]++;
}
   }



        }

        }




for(int j=0;j<this.wiki.length;j++){

if(this.wiki[j]>0){
System.out.println(this.allNationsUSA.get(j).get(0));

System.out.println(this.wiki[j]);
    }
        }


}

public void findMaxyou(){
    allyoutube=new int[this.youtube.length];
   for(int i=0;i<this.youtube.length;i++){
    System.out.println(youtube[i]);
    System.out.println(":--------------------------------------");
}
    int maxU=this.youtube[0];
    int indexU=0;
   for(int i=1;i<this.youtube.length;i++){

       if(maxU<this.youtube[i]){
           maxU=this.youtube[i];
           indexU=i;
       }
   }

for(int i=0;i<this.youtube.length;i++){
    if(maxU==this.youtube[i]){
        allyoutube[i]=1;
    }
}
for(int i=0;i<allyoutube.length;i++){
    if(allyoutube[i]>0){
     this.maxcountryU.add(this.allNations.get(i).get(0));
      System.out.println(this.allNations.get(i).get(0)+"===========");
    }
}
   
}

public void findMaxwiki(){

allwiki=new int[this.wiki.length];
for(int i=0;i<this.wiki.length;i++){
    System.out.println(wiki[i]);
    System.out.println(":--------------------------------------");
}
    int maxWiki=this.wiki[0];
    int indexwiki=0;
   for(int i=0;i<this.wiki.length;i++){

       if(maxWiki<this.wiki[i]){
           maxWiki=this.wiki[i];
           System.out.println(maxWiki);
           indexwiki=i;
            System.out.println(indexwiki);
       }
   }

for(int i=0;i<this.wiki.length;i++){
    if(maxWiki==this.wiki[i]){
        allwiki[i]=1;
          System.out.println(i);
    }
}
for(int i=0;i<allwiki.length;i++){
    if(allwiki[i]>0){
        System.out.println(i);
     this.maxcountryWiki.add(this.allNationsUSA.get(i).get(0));
      System.out.println(this.allNationsUSA.get(i).get(0));
    }
}


}


public GoogleResults  getURL(String search1) throws UnsupportedEncodingException, MalformedURLException, IOException,UnknownHostException{
   //System.out.println("hiii"+this.outputName);
     String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
 String search = search1;
System.out.println(search);
    String charset = "UTF-8";
Reader reader=null; GoogleResults results=null;
    URL url = new URL(google + URLEncoder.encode(search, charset));
 try{
    reader = new InputStreamReader(url.openStream(), charset);
     }
 catch(java.io.IOException ex){
   System.out.println(ex.toString());
 }
 try{
    results = new Gson().fromJson(reader, GoogleResults.class);
        }
 catch(com.google.gson.JsonParseException ex){
System.out.println(ex.toString());
 }
    catch(java.lang.NullPointerException ex){
System.out.println(ex.toString());
 }


// if(!(this.outputName.equalsIgnoreCase("Sports"))){
//     System.out.println("hiii"+this.outputName);
//   for(int i=0;i<results.getResponseData().getResults().size();i++){
//   System.out.println(results.getResponseData().getResults().get(i).getUrl());
//
//  }
//     }
// else {
//
//      for(int i=0;i<results.getResponseData().getResults().size();i++){
//          urlSports.add(results.getResponseData().getResults().get(i).getUrl());
//   System.out.println(results.getResponseData().getResults().get(i).getUrl());
//      //  this.htmlContentforSports=  this.getText(results.getResponseData().getResults().get(i).getUrl())+this.htmlContentforSports;
//  }


 //}


//System.out.println(Thread.activeCount());


    return (results);


    }


public String getHtml(String url){

String fn=url;
	StringBuilder text = new StringBuilder();

	try {
	        URL page = new URL(fn);

		// Connect to the webpage
		HttpURLConnection conn =
			(HttpURLConnection) page.openConnection();
		conn.connect();

                // Connect to the CONTENT and set
                // up a BufferedReader for it.
		InputStreamReader in = new InputStreamReader(
			(InputStream) conn.getContent());
		BufferedReader read = new BufferedReader(in);

		String line;
		do {
			line = read.readLine();
			if (line != null)
				text.append(line +"\n");
		} while (line != null);



	} catch (IOException e) {

		return "Error - :" + e.getMessage();
	}
System.out.println(text);
	return text.toString();
}


}
