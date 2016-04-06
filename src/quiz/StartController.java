package quiz;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartController implements EventHandler<ActionEvent> {
	
	Quiz quiz;
	
	@FXML
    private Button b_startQuiz;

    @FXML
    private void initialize() {
    	b_startQuiz.setOnAction(this);
    }

	@Override
	public void handle(ActionEvent event) {
		Quiz.startQuiz();
	}
	
}
