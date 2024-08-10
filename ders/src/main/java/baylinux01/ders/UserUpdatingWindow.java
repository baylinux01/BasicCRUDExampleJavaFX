package baylinux01.ders;

import java.sql.SQLException;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;


public class UserUpdatingWindow extends Application {
	Dao dao;
	int id;
	User user;
	@Override
	public void start(Stage primaryStage) {
		try {
			Group root = new Group();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			dao=new Dao();
			user= dao.getUserById(id);
			
			Pane pane=new Pane();
			pane.setPrefWidth(220);
			pane.setPrefHeight(360);
			pane.setLayoutX(90);
			pane.setLayoutY(40);
			root.getChildren().add(pane);
			
			Label lb=new Label("Kullanıcı Adı Giriniz:");
			lb.setPrefWidth(200);
			lb.setPrefHeight(20);
			lb.setLayoutX(10);
			lb.setLayoutY(10);
			pane.getChildren().add(lb);
			
			TextField tf=new TextField();
			tf.setPrefWidth(200);
			tf.setPrefHeight(20);
			tf.setLayoutX(10);
			tf.setLayoutY(30);
			tf.setText(user.getUsername());
			pane.getChildren().add(tf);
			
			Label lb2=new Label("Şifre Giriniz:");
			lb2.setPrefWidth(200);
			lb2.setPrefHeight(20);
			lb2.setLayoutX(10);
			lb2.setLayoutY(70);
			pane.getChildren().add(lb2);
			
			TextField tf2=new TextField();
			tf2.setPrefWidth(200);
			tf2.setPrefHeight(20);
			tf2.setLayoutX(10);
			tf2.setLayoutY(90);
			tf2.setText(user.getPassword());
			pane.getChildren().add(tf2);
			
			EventHandler eh=new EventHandler() 
			{

				@Override
				public void handle(Event event) {
					int result=0;
					try {
						result=dao.UpdateUsernameAndPasswordById(id,tf.getText(), tf2.getText());
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(result>0)
					{
						Alert alert=new Alert(AlertType.INFORMATION);
						alert.setTitle("Bildirim");
						alert.setHeaderText("İşlem Başarılı");
						alert.setContentText("Kullanıcı Güncellendi");
						ButtonType bt=alert.showAndWait().orElse(null);
						
						if(bt.equals(ButtonType.OK))
						{
							
						}
					}
					
				}
				
			};
			
			Button btn=new Button("Kullanıcı Güncelle");
			btn.setPrefWidth(200);
			btn.setPrefHeight(20);
			btn.setLayoutX(10);
			btn.setLayoutY(130);
			pane.getChildren().add(btn);
			btn.setOnAction(eh);
			
			EventHandler eh2=new EventHandler() 
			{

				@Override
				public void handle(Event event) {
					Group group=new Group();
					Scene scene=new Scene(group);
					Stage stage=new Stage();
					stage.setScene(scene);
					
					Main main=new Main();
					main.start(stage);
					primaryStage.hide();
				}
				
			};
			
			Button btn2=new Button("Anasayfaya Dön");
			btn2.setPrefWidth(200);
			btn2.setPrefHeight(20);
			btn2.setLayoutX(10);
			btn2.setLayoutY(170);
			pane.getChildren().add(btn2);
			btn2.setOnAction(eh2);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
