import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {

  private View view;
  private Model model;

  public Controller(Model model, View view) {
    this.model = model;
    this.view = view;

    // Event-Handler registrieren
    this.view.getCountButton().setOnAction(event -> countAction(event));
    this.view.getSetNum().setOnAction(event -> setNum(event));
    
    this.view.getshowImageButton().setOnAction(event -> setFolderPath(event));
    this.view.back().setOnAction(event -> vorherigesBild(event));
    this.view.next().setOnAction(event -> naechstesBild(event));
            
    model.addObserver(this);
    this.view.getStage().show();
  }

  
  
  public void countAction(ActionEvent event) {
    model.tick();
  }
  
  @Override
  public void update(Observable o, Object arg) {
    if (model.getCountDown() == 1) {
      Alert alert = new Alert(Alert.AlertType.WARNING, 
              "Jetzt isch s no glei vorbei!", ButtonType.OK);
      alert.show();
    }
  }
   
  private void setNum(ActionEvent event){
        model.setCountDown8();
    }
  
  
  
  
  
  
    private void setPicture(String path) {
        
        model.ladeBild(path);
       
    }
    
   
    
    public void vorherigesBild(ActionEvent event) {
        model.vorherigesBild();
        setPicture(model.result.get(model.bilderIndex));
    }
    
     private void setFolderPath(ActionEvent event) {
        model.allesAusDemOrdnerLaden(view.getPfad().getText());
    }

    private void naechstesBild(ActionEvent event){
        model.naechstesBild();
        setPicture(model.result.get(model.bilderIndex));
    }
    
}
