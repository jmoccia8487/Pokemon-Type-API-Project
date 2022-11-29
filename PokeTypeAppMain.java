import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.*;

public class PokeTypeAppMain extends Application
{
   public static void main(String[] args)
   {
      launch(args);
   }
   
   @Override
   public void start(Stage stage) throws Exception
   {
      // Loads the GUI from FXML built in Scene Builder
      Parent root = FXMLLoader.load(getClass().getResource(""));
      Scene scene = new Scene(root);
      stage.setTitle("Pokemon Type Generator");
      
      // Set the scene and display it to the user
      stage.setScene(scene);
      stage.show();
   }
   
   @Override
   public void stop()
   {
      System.out.println("Stop is called in Application class");
   }
}