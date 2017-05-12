/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neuralnetwork3;
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
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;
/**
 *
 * @author Dilusha
 */
public class FindNewInterestHandler {
   private Vector<String> interest=new Vector<String>();
   private  Vector<InterestNetwork> serializedObjects=new Vector<InterestNetwork>();
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

 public void findKeyInterest(String text){
int a=0;

 //for(int i=0;i<text.length();i++) {
    String data[]=(text.split(" "));
    for(int i=0;i<data.length;i++) {
        System.out.println(data[i]);
        for(int j=0;j<interest.size();j++){
           // System.out.println(interest.get(j)+"ppppppppppppppppppppppppppppppppp");
            if(data[i].contains(interest.get(j))||data[i].equalsIgnoreCase(interest.get(j))){
            for(int k=0;k<serializedObjects.get(j).getOutputLayer().size();k++){
                 if(data[i].contains(serializedObjects.get(j).getOutputLayer().get(k).name)||data[i].equalsIgnoreCase(serializedObjects.get(j).getOutputLayer().get(k).name)){

                 }
            }
                System.out.println("found");
            a=a+1;
        }


        }
    }
System.out.println(a);
 //}

 }


 public void addInterest(){
     InterestNetwork b = null;
     String networkName="";
    try
         {
            FileInputStream fileIn =
                          new FileInputStream(networkName+".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
             b= (InterestNetwork) in.readObject();
            in.close();
            fileIn.close();
            serializedObjects.add(b);

        }catch(IOException i)
        {
            i.printStackTrace();
           // return null;
        }catch(ClassNotFoundException c)
        {
            System.out.println("InterestBank  class not found");
            c.printStackTrace();
           // return null;
        }

     interest.add("fashions");
     interest.add("films");
     interest.add("sports");
     interest.add("music");
     interest.add("books");
     interest.add("foods");
     interest.add("news");

     //return interest;
 }
}
