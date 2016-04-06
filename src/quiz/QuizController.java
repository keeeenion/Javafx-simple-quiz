package quiz;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

public class QuizController implements EventHandler<ActionEvent> {
	
	@FXML
    private RadioButton r_answer0, r_answer1, r_answer2, r_answer3;
	@FXML
	private Label l_question;
	@FXML
	private Button b_answer;

    @FXML
    private void initialize() {
    	r_answer0.setOnAction(this);
    	r_answer1.setOnAction(this);
    	r_answer2.setOnAction(this);
    	r_answer3.setOnAction(this);
    	b_answer.setOnAction(this);
    	r_answer0.setSelected(true);
    }
    
	public void setAnswer(int answer, String text) {
    	switch (answer) {
    		case 0: r_answer0.setText(text);
    				break;
    		case 1: r_answer1.setText(text);
    				break;
    		case 2: r_answer2.setText(text);
    				break;
    		case 3: r_answer3.setText(text);
    				break;
    	}
    }
	
	public void setQuestion(String q) {
		l_question.setText(q);
	}
	
    
    public String getAnswer(int answer) {
    	switch (answer) {
			case 0: return this.r_answer0.getText();
			case 1: return this.r_answer1.getText();
			case 2: return this.r_answer2.getText();
			case 3: return this.r_answer3.getText();
    	}
		return null;
    }
    
    public boolean isSelected(int answer) {
    	switch (answer) {
			case 0: return this.r_answer0.isSelected();
			case 1: return this.r_answer1.isSelected();
			case 2: return this.r_answer2.isSelected();
			case 3: return this.r_answer3.isSelected();
    	}
		return false;
    }

	@Override
	public void handle(ActionEvent e) {
		Object source = e.getSource();
		
		if (source instanceof RadioButton) {
			r_answer0.setSelected(false);
			r_answer1.setSelected(false);
			r_answer2.setSelected(false);
			r_answer3.setSelected(false);
			
			((RadioButton) source).setSelected(true);
		}
		if (source instanceof Button) {
			boolean found = false;
			int j = 0;
			while (found != true) {
				if (isSelected(j) == true) {
					found = true;
					if (getAnswer(j) == Quiz.currentCorrectAnswer){
						Quiz.questionsDone++; Quiz.points++; Quiz.startQuiz(); return; 	//Triggered if the answer is valid
					} else {
						Quiz.questionsDone++; Quiz.startQuiz(); return; 					//Triggered if the answer is invalid
					}
				}
				j++;
			}
		}
	}
}
