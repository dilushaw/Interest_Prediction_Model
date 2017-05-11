package neuralnetwork3.TestPack;

import java.util.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;



class WebSearch
{
    
        public static void main(String[] args) throws Exception {
        //for(int i=0; i<1000; i++){
        URL oracle = new URL("http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=java");
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                yc.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
           System.out.println(inputLine);
        in.close();
  //  }
        }
    }

