import java.io.File;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Data {
    public HashMap<Integer, String> docs;
    public HashMap<Integer, String> T_Docs;
    public static HashMap<Integer, String> DataHash;
    public int[] classes;

    public Data() {
        
    }
      public void read(String train, String[] test){
        //test - symptoms train-dataset
        docs = new HashMap<Integer, String>();
        DataHash = new HashMap<Integer, String>();
        T_Docs = new HashMap<Integer, String>();
        int i = 0;
        classes = new int[2000];
        for(int s=0;s<test.length;s++){
          //test[s]=test[s].replace("_", " ");
          docs.put(s,test[s]);
          System.out.println(docs.getKey(test[s]));
          
        }
        //System.out.println("docs:"+docs);
        String[] temp =train.split("\n");
       
        for(int j=1;j<temp.length;j++){
          String[] temp2 = temp[j].split(",");
        
          DataHash.put(j, temp2[0]);
          
          for(int k=1;k<temp2.length;k++){
             
              if(docs.containsValue(temp2[k])){

                
    
                 //System.out.println(docs.  (temp2[k]));
                  
                  //DataHash.put(key, "1");
              }
              
          }
        }


    }
     
      }