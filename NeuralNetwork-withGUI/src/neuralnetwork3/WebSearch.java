/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neuralnetwork3;

import com.google.gson.Gson;
//import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
//import java.net.URLConnection;
//import java.net.URLEncoder;
//import javax.swing.text.html.parser.DTD;

/**
 *
 * @author Dilusha
 */
public class WebSearch{
    private String nationality;
   private Vector<String> urlSports;
   private String outputName;
   private Vector<String> output;

   public WebSearch(String output){
 urlSports=new Vector<String>();
 Vector<String> justForOutput=new Vector<String>();
 justForOutput.add(output);
 this.outputName=output;
 this.output=justForOutput;
   }

public WebSearch(String nationality,String outputName,Vector<String> output){
this.nationality=nationality;
this.output=output;
this.outputName=outputName;
}


  public Vector<String> handleEachInterest(){
      Vector<String> interestWeb=new Vector<String>();
      if(this.outputName.equalsIgnoreCase("books")){

         for(int i=0;i<output.size();i++){
       interestWeb.add(this.output.get(i)+" "+"online");
          }

      }
 else if(this.outputName.equalsIgnoreCase("Finance")){
  for(int i=0;i<output.size();i++){
       interestWeb.add(this.output.get(i)+" in "+this.nationality);
          }

 }
    else if(this.outputName.equalsIgnoreCase("Games")){
  for(int i=0;i<output.size();i++){
       interestWeb.add(this.output.get(i)+" online");
          }
      }
   else if(this.outputName.equalsIgnoreCase("Hotels")){
  for(int i=0;i<output.size();i++){
       interestWeb.add("best "+this.output.get(i)+" Sri Lanka");
          }
      }
   else if(this.outputName.equalsIgnoreCase("Toys")){
  for(int i=0;i<output.size();i++){
       interestWeb.add(this.output.get(i)+" online");
          }
      }

else if(this.outputName.equalsIgnoreCase("Technology")){
  for(int i=0;i<output.size();i++){
       interestWeb.add("best sites for "+this.output.get(i));
          }
      }

else if(this.outputName.equalsIgnoreCase("fashions")){
  for(int i=0;i<output.size();i++){
       interestWeb.add("best sites for "+this.nationality+" "+this.output.get(i));
          }
      }
else if(this.outputName.equalsIgnoreCase("foods")){
  for(int i=0;i<output.size();i++){
       interestWeb.add("best sites for "+this.output.get(i)+" "+this.nationality);
          }
      }
else if(this.outputName.equalsIgnoreCase("music")){
  for(int i=0;i<output.size();i++){
       interestWeb.add("youtube "+this.nationality+" "+this.output.get(i));
          }
      }
      else if(this.outputName.equalsIgnoreCase("science")){
  for(int i=0;i<output.size();i++){
       interestWeb.add("best sites for "+this.output.get(i));
          }
      }
else if(this.outputName.equalsIgnoreCase("news")){
  for(int i=0;i<output.size();i++){
       interestWeb.add("best sites for "+this.nationality+" latest"+this.output.get(i));
          }
      }
else if(this.outputName.equalsIgnoreCase("pets")){
  for(int i=0;i<output.size();i++){
       interestWeb.add("best sites for "+this.output.get(i));
          }
      }
      else if(this.outputName.equalsIgnoreCase("films")){

  for(int i=0;i<output.size();i++){
      if(output.get(i).equalsIgnoreCase("films"))
       interestWeb.add("youtube "+this.nationality+this.output.get(i)+" trailers ");
      else
      interestWeb.add("youtube "+this.output.get(i)+" trailers ");

  }

      }



       return interestWeb;

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
 public void searchResults(GoogleResults results){
  if(!(results==null) )  {
 if(!(this.outputName.equalsIgnoreCase("Sports"))){
     System.out.println("hiii"+this.outputName);
   for(int i=0;i<results.getResponseData().getResults().size();i++){
   System.out.println(results.getResponseData().getResults().get(i).getUrl());

  }
     }
 else {
     if(!(results.getResponseData()==null)){
      for(int i=0;i<results.getResponseData().getResults().size();i++){
          urlSports.add(results.getResponseData().getResults().get(i).getUrl());
  // System.out.println(results.getResponseData().getResults().get(i).getUrl());
      //  this.htmlContentforSports=  this.getText(results.getResponseData().getResults().get(i).getUrl())+this.htmlContentforSports;

     }
      }


 }
     }
 else{
      System.out.println("please connect to inyternet:GoogleResult is null");
 }
 }

  public void searchResults_Sports(GoogleResults results){
      System.out.println("ttttttttttt");
    for(int i=0;i<results.getResponseData().getResults().size();i++){
   System.out.println(results.getResponseData().getResults().get(i).getUrl());

  }
  }


 public String getText(String fn) {


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

        // Changes the StringBuilder to a String
	return text.toString();
}

public Vector<String> getURLforSports(){
    return this.urlSports;
}

}

