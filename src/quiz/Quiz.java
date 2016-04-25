/* Created by Keeeenion | me@keeeenion.me */

package quiz;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Quiz extends Application {
	
	private static Stage primaryStage;
    private static AnchorPane rootLayout;
	
    static int questionsCount = 0;
	static int questionsDone = 0;
	static int points = 0;
	static String currentCorrectAnswer = "";
	static HashMap<Integer, String> hm_questions = new HashMap<Integer, String>();
	static HashMap<Integer, String[]> hm_answers = new HashMap<Integer, String[]>();

	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
        Quiz.primaryStage = primaryStage;
        Quiz.primaryStage.setTitle("Dynamic quizes by Keeeenion");
        
        parseQuestions();
        displayStartLayout();
	}
	
	public static void displayStartLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Quiz.class.getResource("start.fxml"));
            rootLayout = (AnchorPane) loader.load();
			
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static void startQuiz() {
		if (questionsDone == questionsCount) {
			//displayFinishedLayout();
			return;
		}
		System.out.print(questionsCount);
		
		currentCorrectAnswer = hm_answers.get(questionsDone)[0];
		
		initQuizLayout(hm_questions.get(questionsDone)
						,hm_answers.get(questionsDone)[0]
						,hm_answers.get(questionsDone)[1]
						,hm_answers.get(questionsDone)[2]
						,hm_answers.get(questionsDone)[3]);
	}
	
	public static void initQuizLayout(String q, String a0, String a1, String a2, String a3) {
        try {
            FXMLLoader quizLoader = new FXMLLoader();
            quizLoader.setLocation(Quiz.class.getResource("quizes.fxml"));
            rootLayout = (AnchorPane) quizLoader.load();
            QuizController qc = (QuizController) quizLoader.getController();
    		
    		qc.setQuestion(q);
            qc.setAnswer(0, a0);
            qc.setAnswer(1, a1);
            qc.setAnswer(2, a2);
            qc.setAnswer(3, a3);
            
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public void parseQuestions() {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    factory.setValidating(true);
	    factory.setIgnoringElementContentWhitespace(true);
	    try {	
	         File inputFile = new File("src/quiz/Data.xml");
	         DocumentBuilderFactory dbFactory  = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();
	         NodeList nList = doc.getElementsByTagName("question");
	         
	         String[] answer = new String[4];
	         
	         for (int i = 0; i < nList.getLength(); i++) {
	        	 questionsCount++;
	        	 Node nNode = nList.item(i);
	        	 if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	        		 Element eElement = (Element) nNode;
	               
	        		 answer[0] = eElement.getElementsByTagName("A0").item(0).getTextContent();
	        		 answer[1] = eElement.getElementsByTagName("A1").item(0).getTextContent();
	        		 answer[2] = eElement.getElementsByTagName("A2").item(0).getTextContent();
	        		 answer[3] = eElement.getElementsByTagName("A3").item(0).getTextContent();
	        		 String q = eElement.getElementsByTagName("Q").item(0).getTextContent();
	               
	        		 hm_questions.put(i, q);
	        		 hm_answers.put(i, answer);
	        	 }
	         }
	         
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
}
