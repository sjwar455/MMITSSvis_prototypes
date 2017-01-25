package application;
	

import static java.lang.Math.random;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
			VehicleInfo obu1 = new VehicleInfo(17264198);
			
			Task<Void> accelerate= new Task<Void>() {
				@Override
				public Void call() throws Exception {
					while(obu1.getSpeed() < 100){
						obu1.setSpeed(obu1.getSpeed() + 1);
						Thread.sleep(250);
					}
					return null;
				}
			};
			
			new Thread(accelerate).start();
			
			
			Label speed = new Label("62");
			speed.getStyleClass().add("speed");
			Label mph = new Label("mph");
			mph.getStyleClass().add("mph");
			
			AnchorPane speedometer = new AnchorPane();
			
			AnchorPane.setBottomAnchor(speed, 0.0);
			AnchorPane.setLeftAnchor(speed, 25.0);
			AnchorPane.setBottomAnchor(mph, 25.0);
			AnchorPane.setRightAnchor(mph, 25.0);
			
			speedometer.getChildren().addAll(speed,mph);
			speedometer.setPrefSize(200, 100);
			speedometer.getStyleClass().add("anchor-pane");
			
			Label tempID = new Label("" + obu1.getTempID());
		
			Image car = new Image(Main.class.getResourceAsStream("bus.png"));
			
			ImageView carview = new ImageView();
			carview.setImage(car);
			carview.setFitHeight(75);
			carview.setPreserveRatio(true);
			carview.setSmooth(true);
			carview.setCache(true);
			

			AnchorPane vehicle = new AnchorPane();
			vehicle.setPrefSize(200, 100);
			vehicle.getStyleClass().add("anchor-pane");
			AnchorPane.setTopAnchor(carview, 15.0);
			AnchorPane.setLeftAnchor(carview, 15.0);
			AnchorPane.setBottomAnchor(tempID, 0.0);
			AnchorPane.setLeftAnchor(tempID, 25.0);
			
			vehicle.getChildren().addAll(carview, tempID);
			
			HBox cardisplay = new HBox(vehicle, speedometer);
			cardisplay.getStyleClass().add("hbox");
			cardisplay.setPrefSize(400, 225);
			
			Image intersection = new Image(Main.class.getResourceAsStream("intersection.png"));
			
			ImageView intsctview = new ImageView();
			intsctview.setImage(intersection);
			intsctview.setFitHeight(250);
			intsctview.setPreserveRatio(true);
			intsctview.setSmooth(true);
			intsctview.setCache(true);
			
			BorderPane inter = new BorderPane();
			inter.setCenter(intsctview);
			inter.setPrefSize(400,400);
			inter.getStyleClass().add("border-pane");
			
			Group obus = new Group();
			
			Circle obu1circ = new Circle(5, Color.web("red", 1.0));
	        Timeline timeline = new Timeline();
	            timeline.getKeyFrames().addAll(
	                    new KeyFrame(Duration.ZERO, // set start position at 0
	                    new KeyValue(obu1circ.translateXProperty(), 400),
	                    new KeyValue(obu1circ.translateYProperty(), 400)),
	                    new KeyFrame(new Duration(400),
	                    new KeyValue(obu1circ.translateXProperty(), 400),
	                    new KeyValue(obu1circ.translateYProperty(), 400)));
	            
			obus.getChildren().add(obu1circ);
			
			StackPane map = new StackPane(inter, obus);
			
		
			VBox display = new VBox(cardisplay, map); 
			
			Scene scene = new Scene(display,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			
			Task<Void> speedtask = new Task<Void>() {
				@Override
				public Void call() throws Exception {
					while(obu1.getSpeed() < 100){
						updateMessage("" + obu1.getSpeed());
						Thread.sleep(250);
					}
					return null;
				}
			};
			
            speedtask.messageProperty().addListener((obs, oldMessage, newMessage) -> speed.setText(newMessage));
			new Thread(speedtask).start();
			
			primaryStage.setScene(scene);
			primaryStage.show();

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
