import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;

public class PPInstruction extends HBox {
    public PPInstruction(PicturePoker PP) {
        super(30 * PP.getScaleX());
        
        Font darumadropOneFont = Font.loadFont(getClass().getResource("/assets/fonts/Darumadrop_One/DarumadropOne-Regular.ttf").toExternalForm(), 60);
        Image star = new Image("assets/gfx/cards/star.png", true);
        Image mushroom = new Image("assets/gfx/cards/mushroom.png", true);
        ImageView[] comboView = new ImageView[23];
        
        VBox cardcombo = new VBox(14 * PP.getScaleX());
            HBox combo16 = new HBox(14 * PP.getScaleX());
                for(int i = 0; i < 5; i++) {
                    comboView[i] = new ImageView(star);
                    comboView[i].setFitWidth(120 * PP.getScaleX());
                    comboView[i].setPreserveRatio(true);
                    combo16.getChildren().addAll(comboView[i]);
                }
            HBox combo8 = new HBox(14 * PP.getScaleX());
                for(int i = 5; i < 9; i++) {
                    comboView[i] = new ImageView(star);
                    comboView[i].setFitWidth(120 * PP.getScaleX());
                    comboView[i].setPreserveRatio(true);
                    combo8.getChildren().addAll(comboView[i]);
                }
            HBox combo6 = new HBox(14 * PP.getScaleX());
                for(int i = 9; i < 12; i++) {
                    comboView[i] = new ImageView(star);
                    comboView[i].setFitWidth(120 * PP.getScaleX());
                    comboView[i].setPreserveRatio(true);
                    combo6.getChildren().addAll(comboView[i]);
                }
                for(int i = 12; i < 14; i++) {
                    comboView[i] = new ImageView(mushroom);
                    comboView[i].setFitWidth(120 * PP.getScaleX());
                    comboView[i].setPreserveRatio(true);
                    combo6.getChildren().addAll(comboView[i]);
                }
            HBox combo4 = new HBox(14 * PP.getScaleX());
                for(int i = 14; i < 17; i++) {
                    comboView[i] = new ImageView(star);
                    comboView[i].setFitWidth(120 * PP.getScaleX());
                    comboView[i].setPreserveRatio(true);
                    combo4.getChildren().addAll(comboView[i]);
                }
            HBox combo3 = new HBox(14 * PP.getScaleX());
                for(int i = 17; i < 19; i++) {
                    comboView[i] = new ImageView(star);
                    comboView[i].setFitWidth(120 * PP.getScaleX());
                    comboView[i].setPreserveRatio(true);
                    combo3.getChildren().addAll(comboView[i]);
                }
                for(int i = 19; i < 21; i++) {
                    comboView[i] = new ImageView(mushroom);
                    comboView[i].setFitWidth(120 * PP.getScaleX());
                    comboView[i].setPreserveRatio(true);
                    combo3.getChildren().addAll(comboView[i]);
                }
            HBox combo2 = new HBox(14 * PP.getScaleX());
                for(int i = 21; i < 23; i++) {
                    comboView[i] = new ImageView(star);
                    comboView[i].setFitWidth(120 * PP.getScaleX());
                    comboView[i].setPreserveRatio(true);
                    combo2.getChildren().addAll(comboView[i]);
                }
        cardcombo.getChildren().addAll(combo16, combo8, combo6, combo4, combo3, combo2);
        
        VBox combovalue = new VBox();
            HBox value16 = new HBox();
            HBox value8 = new HBox();
            HBox value6 = new HBox();
            HBox value4 = new HBox();
            HBox value3 = new HBox();
            HBox value2 = new HBox();
        combovalue.getChildren().addAll(value16, value8, value6, value4, value3, value2);
        
        ImageView cardvalue = new ImageView(new Image("assets/gfx/cardvalue.png", true));
        cardvalue.setFitWidth(264 * PP.getScaleX());
        cardvalue.setPreserveRatio(true);
        
        this.getChildren().addAll(cardvalue, cardcombo, combovalue);
    }
}