import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class MenuButton extends Button{
    public MenuButton() {
        super("Menu");
        super.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Menu.window.setScene(Menu.MainMenu);
                Menu.window.setFullScreen(true);
            }
        });
    }
}