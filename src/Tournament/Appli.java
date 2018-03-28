package Tournament;
	
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Appli extends Application {
	
private final ObservableList<Player> data = FXCollections.observableArrayList();
private final ObservableList<Player> data2 = FXCollections.observableArrayList();
	@Override
	public void start(Stage primaryStage) {
		try {
			
			final TableView<Player> PlayerDB = new TableView<>();
			final TableView<Player> Reg_players = new TableView<>();
			final HBox hb = new HBox();
			final VBox vb2 = new VBox();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			double scrnH = screenSize.height*0.9;
			double scrnW = screenSize.width*0.9;
			Scene scene = new Scene(new Group());
			primaryStage.setHeight(scrnH);
			primaryStage.setWidth(scrnW);
			primaryStage.setScene(scene);
			
			
			final Label label = new Label("Player Database");
		        label.setFont(new Font("Arial", 20));
		 
		        PlayerDB.setEditable(true);
		 
		        TableColumn firstNameCol = new TableColumn("First Name");
		        firstNameCol.setMinWidth(100);
		        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		        TableColumn lastNameCol = new TableColumn("Last Name");
		        lastNameCol.setMinWidth(100);
		        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		        TableColumn surnameCol = new TableColumn("Surname");
		        surnameCol.setMinWidth(100);
		        surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
		        TableColumn IDCol = new TableColumn("ID");
		        IDCol.setMinWidth(100);
		        IDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
		        TableColumn countryCol = new TableColumn("Country");
		        countryCol.setMinWidth(100);
		        countryCol.setCellValueFactory(new PropertyValueFactory<>("Country"));
		        TableColumn locCol = new TableColumn("Location");
		        locCol.setMinWidth(100);
		        locCol.setCellValueFactory(new PropertyValueFactory<>("Location"));
		        
		        PlayerDB.setItems(data);
		        PlayerDB.getColumns().addAll(firstNameCol, lastNameCol, surnameCol, IDCol, countryCol, locCol);
		 
		        final VBox vbox = new VBox();
		        vbox.setSpacing(5);
		        vbox.setPadding(new Insets(10, 0, 0, 10));
		        vbox.setMaxWidth(scrnW*0.45);
		        vbox.setMaxHeight(scrnH*0.45);
		 
		        ((Group) scene.getRoot()).getChildren().addAll(vbox);
		        
		    final Label label2 = new Label("Registered Players");
		        label2.setFont(new Font("Arial", 20));
		 
		        Reg_players.setEditable(true);
		 
		        TableColumn firstNameCol2 = new TableColumn("First Name");
		        firstNameCol2.setMinWidth(100);
		        firstNameCol2.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		        TableColumn lastNameCol2 = new TableColumn("Last Name");
		        lastNameCol2.setMinWidth(100);
		        lastNameCol2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		        TableColumn surnameCol2 = new TableColumn("Surname");
		        surnameCol2.setMinWidth(100);
		        surnameCol2.setCellValueFactory(new PropertyValueFactory<>("surname"));
		        TableColumn IDCol2 = new TableColumn("ID");
		        IDCol2.setMinWidth(100);
		        IDCol2.setCellValueFactory(new PropertyValueFactory<>("ID"));
		        TableColumn countryCol2 = new TableColumn("Country");
		        countryCol2.setMinWidth(100);
		        countryCol2.setCellValueFactory(new PropertyValueFactory<>("Country"));
		        TableColumn locCol2 = new TableColumn("Location");
		        locCol2.setMinWidth(100);
		        locCol.setCellValueFactory(new PropertyValueFactory<>("Location"));
		        
		        Reg_players.getColumns().addAll(firstNameCol2, lastNameCol2, surnameCol2, IDCol2, countryCol2, locCol2);
		        Reg_players.setItems(data2);
		        
		        final VBox vbox2 = new VBox();
		        vbox2.setSpacing(5);
		        vbox2.setTranslateX(scrnW*0.55);
		        vbox2.setPadding(new Insets(10, 0, 0, 10));
		        vbox2.setMaxWidth(scrnW*0.45);
		        vbox2.setMaxHeight(scrnH*0.45);
		        
		        final TextField addFirstName = new TextField();
		        addFirstName.setPromptText("First Name");
		        addFirstName.setMaxWidth(firstNameCol.getPrefWidth());
		        final TextField addLastName = new TextField();
		        addLastName.setMaxWidth(lastNameCol.getPrefWidth());
		        addLastName.setPromptText("Last Name");
		        final TextField addSurname = new TextField();
		        addSurname.setMaxWidth(surnameCol.getPrefWidth());
		        addSurname.setPromptText("Surname");
		        final TextField addID = new TextField();
		        addID.setMaxWidth(IDCol.getPrefWidth());
		        addID.setPromptText("ID");
		        final TextField addCountry = new TextField();
		        addCountry.setMaxWidth(IDCol.getPrefWidth());
		        addCountry.setPromptText("Country");
		        final TextField addLocation = new TextField();
		        addLocation.setMaxWidth(IDCol.getPrefWidth());
		        addLocation.setPromptText("Location");
		        
		        final Button addButton = new Button("Add");
		        addButton.setOnAction((ActionEvent e0) -> {
		            data.add(new Player(addFirstName.getText(),addLastName.getText(),addSurname.getText(),addID.getText(),addCountry.getText(),addLocation.getText()));
		            addFirstName.clear();
		            addLastName.clear();
		            addSurname.clear();
		            addID.clear();
		            addCountry.clear();
		            addLocation.clear();
		        });
		        
		        final Button RefreshButton = new Button("ImportWSEL");
		        RefreshButton.setOnAction((ActionEvent e) -> {
		        	Scanner scanner = null;
					try {
						scanner = new Scanner(new FileReader("WSELBDD.csv"));
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            //Scanner scanner = new Scanner(new FileReader("Project_0/WSEL Pairing System/WSELBDD.csv"));
		    		scanner.useDelimiter(";");
		            int i=0;
		            scanner.next();scanner.next();scanner.next();scanner.next();scanner.next();scanner.next();
		            while(i<940){
	                	data.add(new Player(scanner.next(),scanner.next(),scanner.next(),scanner.next(),scanner.next(),scanner.next()));
		                i++;
		            }
		            scanner.close();
		        });
		        
		        ((Group) scene.getRoot()).getChildren().addAll(vbox2);
	        
		        final Button Register = new Button("Register player =>");
		        Register.setOnAction((ActionEvent e2) -> {
		        		Player temp = PlayerDB.getSelectionModel().getSelectedItem();
		        		data2.add(temp);
		        		data.remove(PlayerDB.getSelectionModel().getSelectedIndex());
		        });
		        
		        final Button Unregister = new Button("<= Unregister");
		        Unregister.setOnAction((ActionEvent e3) -> {
		        		Player temp = Reg_players.getSelectionModel().getSelectedItem();
		        		data.add(temp);
		        		data2.remove(Reg_players.getSelectionModel().getSelectedIndex());
		        });
		        Testing testing = new Testing();
		        
		        // Managing Match after the start
		        final Button Test = new Button("TEST");
		        Test.setOnAction((ActionEvent e3) -> {
		        	testing.test(data2);
		        	
		        });
		 
		        hb.getChildren().addAll(addFirstName, addLastName, addSurname, addID, addCountry, addLocation, addButton);
		        hb.setSpacing(3);
		        vb2.getChildren().addAll(RefreshButton,Register,Unregister,Test);
		        vb2.setAlignment(Pos.BASELINE_CENTER);
		        
		        final VBox vbox3 = new VBox();
		        vbox3.setSpacing(5);
		        vbox3.setTranslateX(scrnW*0.425);
		        vbox3.setTranslateY(scrnH*0.20);
		        vbox3.setPadding(new Insets(10, 0, 0, 10));
		        vbox3.setMaxWidth(scrnW*0.45);
		        vbox3.setMaxHeight(scrnH*0.45);
		        
		        ((Group) scene.getRoot()).getChildren().addAll(vbox3);
		        
		        vbox.getChildren().addAll(label, PlayerDB, hb);
		        vbox2.getChildren().addAll(label2, Reg_players);
		        vbox3.getChildren().addAll(vb2);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
