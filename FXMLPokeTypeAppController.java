import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import java.util.ResourceBundle;
import java.util.Date;
import java.util.prefs.Preferences;
import java.text.SimpleDateFormat;
import javafx.application.Platform;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.IOException;

import com.google.gson.Gson;

public class FXMLPokeTypeAppController implements Initializable
{
   // All of the variables referenced from the FXML file
   // The annotations are required to tie to Scene Builder 
   

   // Used to retrieve data from the API   
   private HttpClient client;
   
   // Top-level class heirarchy that saves the GSON processed JSON
   private Pokemon pokemon; 

   /*Once the Pokemon data is downloaded, this method is called
   to parse the JSON and create a plain old java object (POJO)*/
   protected void updatePokeData()
   {
      try
      {  
         HttpRequest postRequest = HttpRequest.newBuilder()
            .uri(new URI("https://pokeapi.co/api/v2/pokemon?limit=2000&offset=0"));
            //.POST(BodyPublishers.ofString(jsonRequest))
            .build();
      
         HttpClient client = HttpClient.newHttpClient();
         
         HttpResponse<String> postResponse = client.send(postRequest, BodyHandlers.ofString());
         
         System.out.println(postResponse.body());        
      }
   
      catch (URISyntaxException e)
      {
         System.out.println("Issue with request");
      }
   
   }
}     