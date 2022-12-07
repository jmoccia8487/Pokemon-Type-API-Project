import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
   @FXML
   private TextArea dataOutput;

   @FXML
   private Label heightOutput;

   @FXML
   private Label nameOutput;

   @FXML
   private Button searchButton;

   @FXML
   private Label titleLabel;

   @FXML
   private Label typeOutput;
   
   @FXML
    private Label updateTimeLabel;

   @FXML
   private TextField userInput;

   @FXML
   private Label weightOutput;
    
   // All of the variables referenced from the FXML file
   // The annotations are required to tie to Scene Builder 
  // @FXML 
  // private Label updateLabel;

   // Used to retrieve data from the API   
   private HttpClient client;
   
   // This is a Top-level class heirarchy that saves the GSON processed JSON
   private PokemonData pokemonData; 
   
   //
   private Forms forms;
   
  // private Results results;
   
   // Keeps track of last time the pokemon data was updated
   private Date updateTime;
   
   // Action to perform when the refresh button is pressed
   @FXML 
   protected void handleSearchButtonAction(ActionEvent event)
   {  
      //updateUI();
      
      updatePokeData();       
   }


   // Updates the GUI to reflect new changes. 
   protected void updateUI()
   {
      // Update the time data was refreshed.
      SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yy hh:mm a");
      updateTimeLabel.setText(fmt.format(this.updateTime));

      // Set the nameOutput label
      nameOutput.setText(this.pokemonData.forms.name);
   
      /* Added another API call to see if it fixes my problem*/
                           // !!!!!!!!! //
   //    try
//       {  
//         HttpRequest postRequest = HttpRequest.newBuilder()
//            .uri(new URI("https://pokeapi.co/api/v2/pokemon/" + userInput.getText())).GET().build();
//          //.POST(BodyPublishers.ofString(jsonRequest))
//          
//          System.out.println("https://pokeapi.co/api/v2/pokemon/" + userInput.getText());
//                      
//          //HttpResponse<String> postResponse = client.send(postRequest, BodyHandlers.ofString());
//          
//          // Makes a request to the website to get the data
//          client.sendAsync(postRequest, BodyHandlers.ofString()).thenApply(HttpResponse::body).thenAccept(this::processPokemonData);
//                  
//       }
//    
//       catch (URISyntaxException e)
//       {
//          System.out.println("Issue with request");
//       }
      
      // Used to show the app is collecting data
      System.out.println("Updating Pokemon Data...");
   

      
      // Set the typeOutput Label
      typeOutput.setText(this.pokemonData.types.name);
      
      // Set the heightOutput Label
      heightOutput.setText( String.format("%d\u00B0" + "feet", this.pokemonData.types.height));
      
      // Set the lengthOutput Label
      weightOutput.setText( String.format("%d\u00B0" + "pounds", this.pokemonData.types.weight));
   }
      
   /*
   Once the Pokemon data is downloaded, this method is called
   to parse the JSON and create a plain old java object (POJO)
   */
   protected void processPokemonData(String data)
   {
      // Save the time this data was retrieved to be displayed in the GUI
      this.updateTime = new Date();
      
      // Some debugging text for the console.
      System.out.println(data);      
      
      // Converts the JSON data to a POJO
      Gson gson = new Gson();
      this.pokemonData = gson.fromJson(data, PokemonData.class);  
      
      // Schedule UI updates on the GUI thread
       Platform.runLater( new Runnable() {
                           public void run() {
                              updateUI();
                           }
                        });
   }
   
   // This method runs when the app is initialized
   protected void updatePokeData()
   {
   	  // The app can use the same client for its entire life
      if(this.client == null)
         this.client = HttpClient.newHttpClient();
		 
    //  HttpClient client = HttpClient.newHttpClient();

      try
      {  
        HttpRequest postRequest = HttpRequest.newBuilder()
           .uri(new URI( "https://pokeapi.co/api/v2/pokemon/" + userInput.getText()) ).GET().build();
         //.POST(BodyPublishers.ofString(jsonRequest))
System.out.println("https://pokeapi.co/api/v2/pokemon/" + userInput.getText());                     
                     
         //HttpResponse<String> postResponse = client.send(postRequest, BodyHandlers.ofString());
         
         // Makes a request to the website to get the data
         client.sendAsync(postRequest, BodyHandlers.ofString()).thenApply(HttpResponse::body).thenAccept(this::processPokemonData);
                 
      }
   
      catch (URISyntaxException e)
      {
         System.out.println("Issue with request");
      }
      
      // Used to show the app is collecting data
      System.out.println("Updating Pokemon Data...");
   }
   
   // This is the first method called when user opens app
   @Override
   public void initialize(URL location, ResourceBundle resources)
   {
      Preferences p = Preferences.userNodeForPackage(FXMLPokeTypeAppController.class);
      
      // Get new Pokemon Data
      //updatePokeData();  
   }  
}