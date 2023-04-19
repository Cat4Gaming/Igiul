import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;

public class PPCard extends Button {
    private ImageView view;
    private int value;
    private boolean isHidden;
    
    public PPCard(int value) {
        super();
        this.value = value;
        setHidden(false);
        super.setPadding(new Insets(-4, -4, -4, -4));
    }
    
    public PPCard(int value, boolean isHidden) {
        this(value);
        setHidden(isHidden);
    }
    
    public int getValue() {
        return value;
    }
    
    public boolean isHidden(){
        return isHidden;
    }
    
    public void setHidden(boolean isHidden) {
        this.isHidden = isHidden;
        if(isHidden) view = new ImageView(new Image("assets/gfx/cards/hidden.png"));
        else view = new ImageView(new Image("assets/gfx/cards/" + value + ".png"));
        view.setFitHeight(200);
        view.setPreserveRatio(true);
        this.setGraphic(view);
    }
}