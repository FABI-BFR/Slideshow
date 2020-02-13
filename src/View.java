import java.awt.Color;
import java.io.FileInputStream;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;

public class View implements Observer {

  private Model model;
  private Stage stage;
  private Label label;
  private Button countButton;
  private Button showImage;
  private Button next;
  private Button back;
  private Button setNum;
  private ImageView bildIV;
  private TextField pfadTF;
  private String pfad;
  private Scene s;


  public View(Model model, Stage stage) {
    this.model = model;
    this.stage = stage;
    label = new Label();
    countButton = new Button("   Count   ");
    setNum = new Button("   8   ");
    showImage = new Button("Zeige Bild");
    next = new Button("next");
    back = new Button("back");
    bildIV = new ImageView();
    pfadTF = new TextField();
    
    GridPane gridPane = new GridPane();    
      
  
    gridPane.setMinSize(1000, 1000); 


    gridPane.setPadding(new Insets(10, 10, 10, 10)); 


    gridPane.setVgap(5); 
    gridPane.setHgap(5);       

    gridPane.setAlignment(Pos.CENTER); 
    
    //Arranging all the nodes in the grid 
    gridPane.add(bildIV, 0, 0);
    gridPane.add(pfadTF, 0, 1);
    gridPane.add(label, 0, 2);
    gridPane.add(countButton, 2, 3);
    gridPane.add(setNum, 1,3);
    gridPane.add(showImage, 2, 2);
    gridPane.add(back, 1, 2);
    gridPane.add(next, 3, 2);
    
    
    Scene scene = new Scene(gridPane);
    
    stage.setScene(scene);
    
    updateLabel();
    model.addObserver(this);
  }

  private void updateLabel() {
    
    label.setText("Countdown: " + model.getCountDown());
    pfadTF.setText("");
  }
  
  private void updatePicture() {
      bildIV.setImage(model.getImage());
  }

  @Override
  public void update(Observable o, Object arg) {
      updateLabel();
      updatePicture();
  }

  public Stage getStage() {
    return stage;
  }

  public Label getLabel() {
    return label;
  }

  public Button getCountButton() {
    return countButton;
  }
  
  public Button getSetNum(){
      return setNum;
  }
  
  
  
  
  public TextField getPfad() {
      
      return pfadTF;
  }
    
  public Button getshowImageButton() {
  
      return showImage;
  }
    
  public Button back(){
      return back;
  }
  
  public Button next (){
      return next;
  }
}
