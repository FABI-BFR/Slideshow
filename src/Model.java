import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Model extends Observable {

    
  private int countDown;
  private Image img;
  protected int bilderIndex;
  protected List<String> result = new ArrayList<String>();
  
  

  public Model() {
    countDown = 10;
  }

  public int getCountDown() {
    return countDown;
  }

  public void tick() {
    
    if (countDown > 0) {
      countDown--;
      setChanged();
    }
    notifyObservers();
  
  }
  
  public void setCountDown8(){
      countDown = 8;
      
      setChanged();
      notifyObservers();
  }

  
  
  
  
  
 
  public Image getImage() {
      return img;
  }
  
  public void ladeBild(String pfadVomBild) {
  
      img = new Image("file:"+pfadVomBild);
      setChanged();  //geerbt - legt fest, dass das beochtbare Objekt geändert hat
      notifyObservers(); //geerbt - Beobachter benachrichten
        
  }
  
  public void vorherigesBild() {

        bilderIndex--;
        
        if(bilderIndex == -1){
            bilderIndex = result.size()-1;
        }
        ladeBild(result.get(bilderIndex));

    }
  
   public void naechstesBild(){
        bilderIndex++;
        if(bilderIndex == result.size()){
            bilderIndex = 0;
        }
        ladeBild(result.get(bilderIndex));
    }
   
   public List<String> allesAusDemOrdnerLaden(String ordnerPfad){

        try (Stream<Path> walk = Files.walk(Paths.get(ordnerPfad))) {

            result = walk.filter(Files::isRegularFile)
                    .map(Path::toString).collect(Collectors.toList());

            result.forEach(System.out::println);
            for(int i=0;i<result.size();i++){
                char[] chars= result.get(i).toCharArray();
                for(int y=0;y<chars.length;y++){
                    if(chars[y]=='\\'){
                        chars[y]='/';
                    }
                }
                result.remove(i);
                result.add(i,String.valueOf(chars                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   ));
            }
            bilderIndex=0;
            img = new Image("file:"+result.get(bilderIndex));
            setChanged();
            notifyObservers();
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
