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

public class FXMLZooAnimalAppController implements Initializable
{
   // All of the variables referenced from the FXML file
   // The annotations are required to tie to Scene Builder 
   


protected void updateZooData()
{
   try
   {  
      HttpRequest postRequest = HttpRequest.newBuilder()
         .uri = new URI("https://zoo-animal-api.herokuapp.com/");
         .GET()
         .build();
      
      client.sendAsync(request, BodyHandlers.ofString())
         .thenApply(HttpResponse::body)
         .thenAccept(this::processAnimalData);         
   }
   
   catch (URISyntaxException e)
   {
      System.out.println("Issue with request");
   }
}
}     