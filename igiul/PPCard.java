import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;

public class PPCard extends Button {
    private ImageView view;
    private int value;
    
    public PPCard(int value) {
        super();
        view = new ImageView(new Image("assets/gfx/cards/" + value + ".png"));
        view.setFitHeight(200);
        view.setPreserveRatio(true);
        super.setGraphic(view);
        super.setPadding(new Insets(-5, -5, -5, -5));
    }
    
    public void setCard(int value) {
        view.setImage(new Image("assets/gfx/cards/" + value + ".png"));
        super.setGraphic(view);
    }
    
    public int getValue() {
        return value;
    }
}