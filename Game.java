import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;

public class Game extends GameApplication{
    @Override
    protected void initSettings(GameSettings settings){
        settings.setGameMenuEnabled(false);
        settings.setFullScreenAllowed(true);
        settings.setFullScreenFromStart(true);
    }
    public static void main(String[] args){
        launch(args);
    }
    @Override
    protected void initGame(){
        var casinoBack = FXGL.getAssetLoader().loadTexture("casino.png");
        casinoBack.setFitHeight(108*2);
        casinoBack.setFitWidth(192*1.5);
        FXGL.getGameScene().addUINode(casinoBack);
    }
}