package com.example.demo;
import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.util.Map.Entry;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class SymptomDetector{
    private String[] myDocs;
    public ArrayList<String> diseases_list=new ArrayList<String>();
    private ArrayList<String> termAList;
    private ArrayList<ArrayList<Doc>> docLists;
    private double[] docLength;  
    public ArrayList<String> user=new ArrayList<String>();            
      ChatBot cb;
    /**
	 * Construct an inverted index using tf-idf weighting
	 * @param docs List of input strings or file names
	 * @author: Samruddhi Deshpande,Vikram Parmar
	 */

     public void compute (String[] sym_list){
           
      //instanciate the attributes
      myDocs = sym_list;
      termAList = new ArrayList<String>();
      
      docLists = new ArrayList<ArrayList<Doc>>();
      ArrayList<Doc> docList;
      for(int i=1;i<myDocs.length;i++) {
       //System.out.println(myDocs[i]);

         String word;
         
            boolean match = false;
            word=myDocs[i];
            //System.out.println(word);
            if(!termAList.contains(word)) {
               termAList.add(word);
               docList = new ArrayList<Doc>();
               Doc doc = new Doc(i, 1);         //raw term frequence is one
               docList.add(doc);
               docLists.add(docList);
            }
            else {
               int index = termAList.indexOf(word);
               docList = docLists.get(index);
                              
               for(Doc did:docList) {
                  if(did.docId == i) {
                     did.tw++;
                     match = true;
                     break;
               }
            }
            if(!match) {
               Doc doc = new Doc(i,1);
               docList.add(doc);
              //System.out.println("docLists" + docLists.size());

            }
         }
      
     }
     


     // compute the tf.idf
     int N = myDocs.length;
     docLength = new double[N];
     
     for(int i=0;i<termAList.size();i++) {
      docList = docLists.get(i);
      int df = docList.size();
      Doc doc;
      for(int j=0;j<docList.size();j++) {
         doc = docList.get(j);
         double tfidf = (1+Math.log10(doc.tw)) * Math.log10(N/df*1.0);
         docLength[doc.docId] += Math.pow(tfidf,2);
         doc.tw = tfidf;
         docList.set(j,doc);
      }
     }
     for(int i=0;i<N;i++) {
      docLength[i] = Math.sqrt(docLength[i]);
     }
   }

   public  String readFileAsString(String fileName) throws Exception
    { 
      String data = ""; 
      data = new String(Files.readAllBytes(Paths.get(fileName))); 
      return data; 
    } 
    public int searchStopWord(String key,String[] stopWords) {
        Arrays.sort(stopWords);
        int lo = 0;
        int hi = stopWords.length -1;
        while(lo <= hi) {
           int mid = lo + (hi-lo)/2;
           int result = key.compareTo(stopWords[mid]);
           if (result < 0) hi = mid-1;
           else if(result > 0) lo = mid +1;
           else return mid;
        }
        return -1;
     }

    public ArrayList<Integer> queryClean (String query,String stopword){
        ArrayList<String> usersSymptoms=new ArrayList<String>();
        String single;
        //Tokenization 
        //Remove stopwords

        String[] stopWords = stopword.split("\n");
        query=query.toLowerCase();
        String[] split = query.split(",");
        for(String temp:split){
        String[] tokens= temp.split(" ");
        single = new String();
            for(String token:tokens) {
                //System.out.println(token);
                if(searchStopWord(token,stopWords) == -1) {
                    if(single.contains("")){
                    single = token;
                    }
                    else{
                        single = single+"_"+token;
                    }
                }
            }
            usersSymptoms.add(single);
            user=usersSymptoms;
                   } 
        ArrayList<Integer> user_in=new ArrayList<Integer>();
        user_in = rankSearch(usersSymptoms);
        return user_in;
    }




public ArrayList<Integer> rankSearch(ArrayList<String> query) {
      //resultset that contains the matching documents, each of which has an id and a matching score
        HashMap<Integer, Double> docs = new HashMap<Integer, Double>();
       ArrayList<Integer> symp_index= new ArrayList<Integer>(); 
		
	    ArrayList<Doc> docList;
		for(String phrase:query)
		{
            
			int index = termAList.indexOf(phrase);
			/* if phrase is not found*/
			if(index == -1){
               for(String temp:termAList){
                   if(temp.contains(phrase)){
                        String re = temp;
                        //System.out.println(re);
                        phrase=re;
                        index = termAList.indexOf(phrase);
                   } 
            }
           
            }
            else{
      		
			docList = docLists.get(index);
			double termWeight = Math.log(myDocs.length*1.0/docList.size());
			Doc doc;
			for(int i = 0; i< docList.size(); i++)
			{
				doc = docList.get(i);
				double scoreVal = termWeight * doc.tw;
				if(!docs.containsKey(doc.docId))
				{
					docs.put(doc.docId, scoreVal);
				}
				else
				{
					scoreVal += docs.get(doc.docId);
					docs.put(doc.docId, scoreVal);
				}
            }
		}
      }
    
		
		/*Normalization of values */
		double word = 0;                                                              
		for(Entry<Integer, Double> entry : docs.entrySet())
		{
			word += entry.getValue();
		}
		
		HashMap<Integer, Double> nzDocs = new HashMap<Integer, Double>();
		for(Entry<Integer, Double> entry : docs.entrySet())
		{
            nzDocs.put(entry.getKey(), entry.getValue()/word);
            symp_index.add(entry.getKey());
		}
		//System.out.println("Original Value   :  "+docs);
      //System.out.println("Nomalized Value   :  "+ nzDocs);
      Set<Map.Entry<Integer, Double>> mapSet = nzDocs.entrySet();
      Map.Entry<Integer, Double> elementAt5 = (Map.Entry<Integer, Double>) mapSet.toArray()[0];
        
        //This is giving the best match of Symptom Acc to user input.
        //The Best Match Symption is : "  myDocs[elementAt5.getKey();
      
        //System.out.println(symp_index);
   return symp_index;
   
}


   //This function tokenizes disease data set and maches with users and gives output

   public String getDisease(String dataset_read,ArrayList<Integer> user_input, String [] symptoms_list){

    int count=0,oldcount=0;
    ArrayList<Integer> disease_index = new ArrayList<Integer>();
    ArrayList<String> diseases = new ArrayList<String>();
    String final_disease=new String();
      ArrayList<String> forRank;
    
    String[] temp =dataset_read.split("\n"); //Diseases at temp[0] thyen symptoms
       // for(int z =0;z<temp.length;z++)
//        {System.out.println(temp[z]);
//         }  
for(String single : temp){
        String[] temp2 = single.split(",");
        diseases_list.add(temp2[0]);
        if(!diseases.contains(temp2[0])){
            diseases.add(temp2[0]);
        }
    }
    for(int i=1;i<temp.length;i++){
        String[] temp2 = temp[i].split(",");
        forRank = new ArrayList<String>();
        for(int j=1;j<temp2.length;j++) {
            if(!temp2[j].equals("")){
               if(!temp2[j].equals("\\r")){
                  forRank.add(temp2[j]);
                  } 
            }
        }
        disease_index = rankSearch(forRank);
        //System.out.println(disease_index);
        for(int ind=0;ind<disease_index.size();ind++){
            for(int z=0;z<user_input.size();z++){
               if(disease_index.get(ind) == user_input.get(z)){
                count++;
            }
            }
        }
        if(count>oldcount){
            oldcount=count;
               // final_index.add(i);
               final_disease=diseases_list.get(i);
               
            }
    }
    //System.out.println(final_disease);
    accuracy(final_disease,temp);
    return final_disease;
   }
 public void accuracy(String disease, String [] list)
 
 { int tp =0;
      for(int i=1;i<list.length;i++){
      
         String[] temp2 = list[i].split(",");
          if (temp2[0].equals(disease)) {
         // System.out.println(temp2[0]);
          //System.out.println(disease);
          for(int j=0;j<user.size();j++){
         
          //System.out.println(user);
          for(int k=0;k<temp2.length;k++){
         if( user.get(j).equals(temp2[k])){
          tp++;
          //System.out.println(tp);
          break;  }
          
 }  }System.out.println(tp);
 
 
 }break;
 }}
   public String getDetails(String finalStr){
      String detail = new String();
      ArrayList<String> indexDel = new ArrayList<String>();
      ArrayList<String> detail_arr = new ArrayList<String>();
      try{
      String readDetails  = readFileAsString("disease_Description.csv");
      
      String[] arrDetails = readDetails.split("\n");
      for(int i=1;i<arrDetails.length;i++){
         String[] final_split = arrDetails[i].split(",");
         indexDel.add(final_split[0]);
         detail_arr.add(final_split[1]);
      }
      int temp = indexDel.indexOf(finalStr);
      detail=detail_arr.get(temp);
     }
      catch (Exception e) {
         e.printStackTrace();
       }
      return detail;
   

}
public String getHospitals(String finalStr){
   String detail = new String();
   ArrayList<String> indexDel = new ArrayList<String>();
   ArrayList<String> detail_arr = new ArrayList<String>();
   try{
   String readDetails  = readFileAsString("Hospitals.csv");
   
   String[] arrDetails = readDetails.split("\n");
   for(int i=1;i<arrDetails.length;i++){
      String[] final_split = arrDetails[i].split(",");
      indexDel.add(final_split[8]);
      detail_arr.add(final_split[4]);
   }
   if(indexDel.contains(finalStr)){
   int temp = indexDel.indexOf(finalStr);
   detail=detail_arr.get(temp);
  }
  else{
   detail="Sorry we dont have data for this zipcode!!";
}
}
   catch (Exception e) {
      e.printStackTrace();
    }
   return detail;


}
 }      

class Doc {
   int docId;
   double tw;
   //ArrayList<Integer> positionList;
   
   public Doc(int did, double tw) {
      docId = did;
      this.tw = tw;
   }
   
   public String toString() {
      String docIdString = docId + ":" + tw;
      return docIdString;
      
   }
}

