README
*********************************************************************************
Our program file contains the following:
*********************************************************************************
--------------------------------------------------------------------------------------
Datasets :
--------------------------------------------------------------------------------------
dataset.csv : Contains diseases and related symptoms. Has 4921 rows of data.
disease_Description.csv : Contains disease and their description.
disease_precaution.csv : Contains diseases and related suggestions.
Hospitals.csv : Contains latitude, longitude, address, state, zip code of the hospitals.
Symptom_list.csv : One row containing just the symptoms.

--------------------------------------------------------------------------------------
Text Files:
--------------------------------------------------------------------------------------
stopwords.txt : Contains the list of stopwords that is called in java file.

--------------------------------------------------------------------------------------
Java:
--------------------------------------------------------------------------------------
SymptomDetector.java : Instantiates the attributes, computes their tf-idf scores, search the stop words, stemming , deriving the cosine similarity between the query and the term documents, normalizing the scores, returning the Index string
Chatbot.java : The Chatbot program shows a welcome message and will ask  basic questions like age and the symptoms. Compares the index term and fetch a Wikipedia webpage relating to the illness.

--------------------------------------------------------------------------------------
Javascript:
--------------------------------------------------------------------------------------
Constants.js : Set of inputs and predefined outputs for the chatbot.
Index.js : Javascript file to run the chatbot (GET and POST )
Speech.js : Will convert the chatbot output in computer speech.

*********************************************************************************
We have connected our project  with a website using Springboot (by creating API)
*********************************************************************************

*********************************************************************************
Steps to Run the Project:
*********************************************************************************
--------------------------------------------------------------------------------------
To run the Website :
Pre-requisites :
1. Browser , preferably Chrome
2. Latest Java 
3. Symptom Detector package
--------------------------------------------------------------------------------------
Steps : 
--------------------------------------------------------------------------------------
1. Unzip the SymptomDetector.zip package.
2. Go to SymptomDetector/src/main/java/com/example/demo/Chatbot.java and change the directory where the project is saved in your computer. This will be the path to csv files(stopwords.txt, dataset.csv, Symptom_list.csv).
3. Compile SymptomDetector.java then Compile Chatbot.java then run the demoapplication.java, this will run the Springboot controller file which helps url file to to implement the methods.
4. Goto SymptomDetector/site/index.java.
5. Input "hi", "hey", "hello", "good morning", "good afternoon"  to initiate conversation with the Chatbot.
6. Chabot will respond back asking the symptoms . Enter value using comma separated symptoms.

--------------------------------------------------------------------------------------
To run the Java GUI:
Pre-requisites :
Latest Java 
Symptom Detector package
--------------------------------------------------------------------------------------
Steps :
-------------------------------------------------------------------------------------- 
1. Unzip the SymptomDetector.zip package.
2. Go to SymptomDetector/src/main/java/com/example/demo/Chatbot.java and change the directory where the project is saved in your computer. This will be the path to csv files(stopwords.txt, dataset.csv, Symptom_list.csv).
3. Compile SymptomDetector.java then Compile Chatbot.java. 
4. Run Chatbot.java
5. Enter your age and the Chabot will respond back asking the symptoms . Enter value using comma separated symptoms.
6. The Chatbot will respond with the best accurate disease.
7. Then the chat bot will ask if you need the details of the disease. Type “Yes” to see the details.
8. For more details type “MORE” which will redirect you to Wikipedia search
9. Enter your zip code to find the nearest hospital. 

--------------------------------------------------------------------------------------
To run the Android Application:
Pre-requisites :
A mobile phone with Android phone (KitKat 4.4 +) 
SymptomDetector APK
--------------------------------------------------------------------------------------
Steps :
--------------------------------------------------------------------------------------
1. Unzip the SymptomDetector.zip package.
2. Take the APK file and copy in the Android Mobile Phone
3. Go to Developer Tools in settings.
4. Allow install from external resources
5. Install the SymptomDetector APK file 
6. Enter the Details required!
--------------------------------------------------------------------------------------






