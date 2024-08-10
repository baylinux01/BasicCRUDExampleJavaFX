package baylinux01.ders;

import java.sql.SQLException;
import java.util.List;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;


public class Main extends Application {
	TableView<User> tableView;
	List<User> users;
	@Override
	public void start(Stage primaryStage) {
		try {
			Group root = new Group();
			Scene scene = new Scene(root,800,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			Dao dao=new Dao();
			try {
				dao.createDatabase();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				dao.creatUserTable();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Pane pane=new Pane();
			pane.setPrefWidth(220);
			pane.setPrefHeight(360);
			pane.setLayoutX(0);
			pane.setLayoutY(0);
			root.getChildren().add(pane);
			
			EventHandler eh=new EventHandler() 
			{

				@Override
				public void handle(Event event) {
					Group group=new Group();
					Scene scene=new Scene(group);
					Stage stage=new Stage();
					stage.setScene(scene);
					
					UserAddingWindow uaw=new UserAddingWindow();
					uaw.start(stage);
					primaryStage.hide();
				}
				
			};
			
			Button btn=new Button("Kullanıcı Ekle");
			btn.setPrefWidth(200);
			btn.setPrefHeight(20);
			btn.setLayoutX(10);
			btn.setLayoutY(10);
			pane.getChildren().add(btn);
			btn.setOnAction(eh);
			
			EventHandler eh2=new EventHandler() 
			{

				@Override
				public void handle(Event event) {
					int id=tableView.getSelectionModel().getSelectedItem().getId();
					Group group=new Group();
					Scene scene=new Scene(group);
					Stage stage=new Stage();
					stage.setScene(scene);
					
					UserUpdatingWindow uuw=new UserUpdatingWindow();
					uuw.id=id;
					uuw.start(stage);
					primaryStage.hide();
				}
				
			};
			
			Button btn2=new Button("Kullanıcı Güncelle");
			btn2.setPrefWidth(200);
			btn2.setPrefHeight(20);
			btn2.setLayoutX(10);
			btn2.setLayoutY(50);
			pane.getChildren().add(btn2);
			btn2.setOnAction(eh2);
			
			EventHandler eh3=new EventHandler() 
			{

				@Override
				public void handle(Event event) {
					int id=tableView.getSelectionModel().getSelectedItem().getId();
					try {
						dao.deleteUserById(id);
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						users=dao.getAllUsers();
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					tableView.getItems().clear();
					tableView.getItems().addAll(users);
				}
				
			};
			
			Button btn3=new Button("Kullanıcı Sil");
			btn3.setPrefWidth(200);
			btn3.setPrefHeight(20);
			btn3.setLayoutX(10);
			btn3.setLayoutY(90);
			pane.getChildren().add(btn3);
			btn3.setOnAction(eh3);
			
			EventHandler eh4=new EventHandler() 
			{

				@Override
				public void handle(Event event) {
					
					try {
						dao.deleteAllUsers();
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						users=dao.getAllUsers();
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					tableView.getItems().clear();
					tableView.getItems().addAll(users);
				}
				
			};
			
			Button btn4=new Button("Veritabanını Temizle");
			btn4.setPrefWidth(200);
			btn4.setPrefHeight(20);
			btn4.setLayoutX(10);
			btn4.setLayoutY(130);
			pane.getChildren().add(btn4);
			btn4.setOnAction(eh4);
			
			tableView=new TableView<User>();
			tableView.setPrefWidth(540);
			tableView.setPrefHeight(360);
			tableView.setLayoutX(220);
			tableView.setLayoutY(10);
			root.getChildren().add(tableView);
			
			TableColumn<User, Integer> col1=new TableColumn<User, Integer>("id");
			TableColumn<User, String> col2=new TableColumn<User, String>("Kullanıcı Adı");
			TableColumn<User, String> col3=new TableColumn<User, String>("Şifresi");
			
			
			col1.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
			col2.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
			col3.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
			
			col1.setMinWidth(180);
//			col1.setMaxWidth(50);
			col2.setMinWidth(180);
//			col2.setMaxWidth(50);
			col3.setMinWidth(180);
			
			col1.setStyle("-fx-font:normal bold 10px 'serif' ");
			col2.setStyle("-fx-font:normal bold 10px 'serif' ");
			col3.setStyle("-fx-font:normal bold 10px 'serif' ");
			
			
			tableView.getColumns().add(col1);
			tableView.getColumns().add(col2);
			tableView.getColumns().add(col3);
			//tableView.autosize();
			users=dao.getAllUsers();
			tableView.getItems().clear();
			tableView.getItems().addAll(users);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
