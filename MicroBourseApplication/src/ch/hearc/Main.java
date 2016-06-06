package ch.hearc;

import ch.hearc.daoimplement.ActionnaireDaoImplement;
import ch.hearc.interfaces.AppController;
import ch.hearc.metiers.Actionnaire;
import ch.hearc.servicesdao.ServicesActionnaireDAO;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 */
public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        showApp(selectUser(), primaryStage);
    }
    
    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private Long selectUser() {
        ServicesActionnaireDAO soo = new ActionnaireDaoImplement();
        List<Actionnaire> listAct = soo.getActionnaires();
        
        Map<String, Actionnaire> choices = new HashMap<String, Actionnaire>();
        
        for(Actionnaire a : listAct){
            choices.put(a.getNom(), a);
        }
        
        ChoiceDialog<String> dialog = new ChoiceDialog<>("", choices.keySet());
        dialog.setTitle("Choix du compte");
        dialog.setHeaderText("Choix du compte d'utilisateur");
        dialog.setContentText("Choisissez votre compte: ");
        
        Optional<String> result = dialog.showAndWait();
        
        Long toReturn = null;
        
        if (!result.isPresent() || result.get().equals("")){
            System.exit(0);
        } else {
            toReturn = choices.get(result.get()).getIdActionnaire();
        }
        
        return toReturn;
    }
    
    private void showApp(Long ID , Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ch/hearc/interfaces/App.fxml"));
        AnchorPane app = null;
        try {
            app = (AnchorPane) loader.load();
            AppController appController = loader.getController();
            appController.setID(ID);
        } catch (IOException ex) {
            ex.getMessage();
        } finally {                   
            Scene scene = new Scene(app);
            
            primaryStage.setMaxHeight(800.0);
            primaryStage.setMaxWidth(1280.0);
            primaryStage.setTitle("Minibourse");
            //stage.getIcons().add(new Image(""));
            primaryStage.setScene(scene); 
            primaryStage.show();
        }
    }
}
