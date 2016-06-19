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
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Classe Main du projet
 */
public class Main extends Application{

    /**
     * Lancement du projet (partie graphique)
     * @param primaryStage : Stage (fenêtre) principal
     * @throws Exception 
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        showApp(selectUser(), primaryStage);
        primaryStage.setOnCloseRequest((WindowEvent t) -> {
            Platform.exit();
            System.exit(0);
        });
    }
    
    /**
     * Point d'entrée du projet
     * @param args 
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Affichage d'une boîte de dialogue permettant de choisir 
     * l'actionnaire représentant l'utilisateur
     * @return ID de l'actionnaire choisi(e) (utilisateur) 
     */
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
    
    /**
     * Lancement concret de l'application
     * @param ID            : ID de l'actionnaire sélectionné(e)
     * @param primaryStage  : Stage (fenêtre) principal
     */
    private void showApp(Long ID , Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ch/hearc/interfaces/App.fxml"));
        AnchorPane app = null;
        try {
            app = (AnchorPane) loader.load();
            AppController appController = loader.getController();
            appController.setUserID(ID);
        } catch (IOException ex) {
            ex.getMessage();
        } finally {                   
            Scene scene = new Scene(app);
            
            primaryStage.setMaxHeight(900.0);
            primaryStage.setMaxWidth(1380.0);
            primaryStage.setTitle("Minibourse");
            primaryStage.setScene(scene); 
            primaryStage.show();
        }
    }
}
