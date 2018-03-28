package Tournament;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.glass.events.MouseEvent;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Testing {
	
	MouseEvent click = new MouseEvent(); 
	Label Matches_label = new Label("Round 1");
	int i=0, currentround=1;
	boolean pairingready=true;
	ObservableList<StatMatch> obspairing = FXCollections.observableArrayList();
	ObservableList<User> standings = FXCollections.observableArrayList();
	List<Match> pairing = new ArrayList<>();
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double scrnH = screenSize.height*0.9;
	double scrnW = screenSize.width*0.9;
	Stage secondarystage = new Stage();
	Scene scene2 = new Scene(new Group());
	VBox Matches = new VBox();
	HBox pairing_manage = new HBox();
	List<List<Match>> matchbyround = new ArrayList<>();
	List<User> grouped_players = new ArrayList<>();
	TextField mng_table = new TextField("0");
	TextField mng_J1 = new TextField();
    TextField mng_J2 = new TextField();
    ObservableList<String> possible_outcome = FXCollections.observableArrayList();
    Map<String,User> players = new HashMap<>();
 
	
	public void test(List<Player> arg) {
	
	// Initialization graphics and data	
	secondarystage.setHeight(scrnH);
	secondarystage.setWidth(scrnW);
	secondarystage.setScene(scene2);
	VBox Standings = new VBox();
	VBox ManageResult = new VBox();
	
	final TableView<StatMatch> matchtable = new TableView<>();
	TableColumn<StatMatch,String> tabCol = new TableColumn<>("Table n°");
    tabCol.setMinWidth(30);
    tabCol.setCellValueFactory(new PropertyValueFactory<>("tab"));
    TableColumn<StatMatch,String> P1Col = new TableColumn<>("Player 1");
    P1Col.setMinWidth(150);
    P1Col.setCellValueFactory(new PropertyValueFactory<>("p1name"));
    TableColumn<StatMatch,String> P1scoreCol = new TableColumn<>("Score");
    P1scoreCol.setMinWidth(20);
    P1scoreCol.setCellValueFactory(new PropertyValueFactory<>("p1score"));
    TableColumn<StatMatch,String> P2Col = new TableColumn<>("Player 2");
    P2Col.setMinWidth(150);
    P2Col.setCellValueFactory(new PropertyValueFactory<>("p2name"));
    TableColumn<StatMatch,String> P2scoreCol = new TableColumn<>("Score");
    P2scoreCol.setMinWidth(20);
    P2scoreCol.setCellValueFactory(new PropertyValueFactory<>("p2score"));
    TableColumn<StatMatch,String> ResultCol = new TableColumn<>("Winner");
    ResultCol.setMinWidth(100);
    ResultCol.setCellValueFactory(new PropertyValueFactory<>("outcome"));
    matchtable.setItems(obspairing);
    matchtable.getColumns().addAll(tabCol,P1Col,P1scoreCol,P2Col,P2scoreCol,ResultCol);
    Matches.setSpacing(5);
    Matches.setPadding(new Insets(10, 0, 0, 10));
    Matches.setMaxWidth(scrnW*0.45);
    Matches.setMaxHeight(scrnH*0.45);
    Matches.getChildren().addAll(Matches_label,matchtable);
    
    Label Standing_Label = new Label("Standings");
    
    final TableView<User> pairingtable = new TableView<>();
    TableColumn NameCol = new TableColumn("Name");
    NameCol.setMinWidth(150);
    NameCol.setCellValueFactory(new PropertyValueFactory<>("FullName"));
    TableColumn ScoreCol = new TableColumn("Score");
    ScoreCol.setMinWidth(25);
    ScoreCol.setCellValueFactory(new PropertyValueFactory<>("Score"));
    TableColumn OppWinCol = new TableColumn("OppWin");
    OppWinCol.setMinWidth(25);
    OppWinCol.setCellValueFactory(new PropertyValueFactory<>("OppWin"));
    TableColumn OppOppWinCol = new TableColumn("OppOppWin");
    OppOppWinCol.setMinWidth(25);
    OppOppWinCol.setCellValueFactory(new PropertyValueFactory<>("OppOppWin"));
    
    pairingtable.setItems(standings);
    pairingtable.getColumns().addAll(NameCol,ScoreCol,OppWinCol,OppOppWinCol);
    Standings.setSpacing(5);
    Standings.setPadding(new Insets(10, 0, 0, 10));
    Standings.setMaxWidth(scrnW*0.45);
    Standings.setMaxHeight(scrnH*0.45);
    Standings.setMinWidth(scrnW*0.45);
    Standings.setMinHeight(scrnH*0.45);
    Standings.setTranslateX(scrnW*0.54);
    Standings.getChildren().addAll(Standing_Label,pairingtable);
	
    ManageResult.setSpacing(5);
    ManageResult.setPadding(new Insets(10, 0, 0, 10));
    ManageResult.setMaxWidth(scrnW*0.25);
    ManageResult.setMaxHeight(scrnH*0.45);
    ManageResult.setTranslateY(scrnH*0.5);
    HBox mng_hbtab = new HBox();
    HBox mng_hbJ1 = new HBox();
    HBox mng_hbJ2 = new HBox();
    HBox mng_rslt = new HBox();
    
    
    mng_table.setEditable(false);
    mng_table.setMaxSize(30, scrnH);
    Label mng_table_lbl = new Label("Table :");
    //matchtable.getSelectionModel().getSelectedItem().getP1name()matchtable.getSelectionModel().getSelectedItem().getP2name()
    
    mng_J1.setEditable(false);
    mng_J1.setAlignment(Pos.BASELINE_RIGHT);
    Label mng_J1_lbl = new Label("Player 1 :");
    
    mng_J2.setEditable(false);
    mng_J2.setAlignment(Pos.BASELINE_RIGHT);
    Label mng_J2_lbl = new Label("Player 2 :");
    
    //possible_outcome.addAll(mng_J1.getText(),mng_J2.getText(),"Draw");
    ToggleGroup winbuttons = new ToggleGroup();
    RadioButton p1win = new RadioButton("Left");
    RadioButton p2win = new RadioButton("Right");
    RadioButton b_draw = new RadioButton("Draw");
    p1win.setToggleGroup(winbuttons);
    p2win.setToggleGroup(winbuttons);
    b_draw.setToggleGroup(winbuttons);
    p1win.setUserData("P1");
    p2win.setUserData("P2");
    b_draw.setUserData("Draw");
    //rslt_choice.setItems(possible_outcome);
    Label mng_rslt_lbl = new Label("Winner : ");
    mng_hbtab.getChildren().addAll(mng_table_lbl,mng_table);
    mng_hbJ1.getChildren().addAll(mng_J1_lbl,mng_J1,p1win);
    mng_hbJ2.getChildren().addAll(mng_J2_lbl,mng_J2,p2win);
    mng_rslt.getChildren().addAll(mng_rslt_lbl,b_draw);
    ManageResult.getChildren().addAll(mng_hbtab,mng_hbJ1,mng_hbJ2,mng_rslt);
    
   
    
    matchtable.setOnMouseClicked((click) -> {
        if(click.getButton().equals(MouseButton.PRIMARY)){
        	mng_table.setText(matchtable.getSelectionModel().getSelectedItem().gettab());
        	mng_J1.setText(matchtable.getSelectionModel().getSelectedItem().getP1name());
        	mng_J2.setText(matchtable.getSelectionModel().getSelectedItem().getP2name());        	
        	/*possible_outcome.remove(0);
        	possible_outcome.remove(0);
        	possible_outcome.add(0,mng_J1.getText());
        	possible_outcome.add(1,mng_J2.getText());*/
        }
    });
    
    winbuttons.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
        public void changed(ObservableValue<? extends Toggle> ov,
            Toggle old_toggle, Toggle new_toggle) {
                if (winbuttons.getSelectedToggle() != null) {
                	if(new_toggle.getUserData()=="P1") {
                		obspairing.get(Integer.parseInt(mng_table.getText())-1).setoutcome("Left");
                	}
                	else if(new_toggle.getUserData()=="P2") {
                		obspairing.get(Integer.parseInt(mng_table.getText())-1).setoutcome("Right");
                	}
                	else if(new_toggle.getUserData()=="Draw") {
                		obspairing.get(Integer.parseInt(mng_table.getText())-1).setoutcome("Draw");
                	}
                }                
            }
    });
    
    /*rslt_choice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
        Object selection = rslt_choice.getItems().get((Integer) number2);
        if(selection.toString().equals(obspairing.get(Integer.parseInt(mng_table.getText())-1).getP1name())) {
        	obspairing.get(Integer.parseInt(mng_table.getText())-1).setoutcome("Left");
        	System.out.println(obspairing.get(Integer.parseInt(mng_table.getText())-1).getoutcome());
        }
        else if(selection.toString().equals(obspairing.get(Integer.parseInt(mng_table.getText())-1).getP2name())) {
        	obspairing.get(Integer.parseInt(mng_table.getText())-1).setoutcome("Right");
        	System.out.println(obspairing.get(Integer.parseInt(mng_table.getText())-1).getoutcome());
        }
        else {
        	obspairing.get(Integer.parseInt(mng_table.getText())-1).setoutcome("PENDING");
        	System.out.println(obspairing.get(Integer.parseInt(mng_table.getText())-1).getoutcome());
        }
        matchtable.setItems(obspairing);
      }
    });*/
    
	List<User> Local_Player = new ArrayList<>();
	for(i=0;i<arg.size();i++) {
		Local_Player.add(new User(arg.get(i)));
	}
	Tournament Test = new Tournament("TestTournament","07/12/2017","Aix",2,1);
	for(i=0;i<arg.size();i++) {
		Test.Addplayer(Local_Player.get(i));
	}
	
	int number_of_players=arg.size(),number_of_matchs=number_of_players/2+number_of_players%2;
	double number_of_rounds=Math.ceil(Math.log(number_of_players)/Math.log(2));
	for(i=0;i<number_of_players;i++) {
		players.put(Local_Player.get(i).getID(), Local_Player.get(i));
	}
	//Rounds
	final Button refresh_score = new Button("Refresh Score");
	refresh_score.setOnAction((ActionEvent e) -> {
		for(i=0;i<number_of_matchs;i++) {
			if(pairing.get(i).getResult().equals("PENDING")) {
				pairing.get(i).setResults(players, obspairing.get(i).getoutcome());
				players.put(pairing.get(i).getPlayer1().getID(), pairing.get(i).getPlayer1());
				players.put(pairing.get(i).getPlayer2().getID(), pairing.get(i).getPlayer2());
			}
			
		}
		for(i=0;i<number_of_players;i++) {
			players.get(Test.getplayers().get(i).getID()).RefreshWinrate(1);
		}
		for(i=0;i<number_of_players;i++) {
			if(players.get(Test.getplayers().get(i).getID()).getOppIDList().size()>0) players.get(Test.getplayers().get(i).getID()).RefreshOppWin(1,players);
		}
		for(i=0;i<number_of_players;i++) {
			if(players.get(Test.getplayers().get(i).getID()).getOppIDList().size()>0) players.get(Test.getplayers().get(i).getID()).RefreshOppOppWin(1,players);
		}
		standings = FXCollections.observableArrayList();
		for(i=0;i<number_of_players;i++) {
			standings.add(players.get(Test.getplayers().get(i).getID()));
		}
		standings.sort(Comparator.comparing(User::getScore).thenComparing(User::getOppWin).thenComparing(User::getOppOppWin).reversed());
		pairingtable.setItems(standings);
		pairingready=true;
		for(i=0;i<number_of_matchs;i++) {
			if(obspairing.get(i).getoutcome()=="PENDING") {
				pairingready=false;
			}
		}
	});
	final Button refresh_pairing = new Button("Pair");
    refresh_pairing.setOnAction((ActionEvent e) -> {
    if(pairingready) {
    Matches_label = new Label("Round "+currentround);
    Matches.getChildren().remove(0);
    Matches.getChildren().add(0,Matches_label);
	//Round 1
    if(currentround==1) {
	pairing = Test.MatchmakingFirstRound(Local_Player);
	obspairing = FXCollections.observableArrayList();
	for(i=0;i<pairing.size();i++) {
		obspairing.add(new StatMatch(pairing.get(i).getPlayer1(),pairing.get(i).getPlayer2(),pairing.get(i).getRound(),String.valueOf(i+1),"PENDING"));
		System.out.println(obspairing.get(i));
	}
	matchtable.setItems(obspairing);
	matchbyround.add(pairing);
	System.out.println("Round 1");
	/*for(i=0;i<number_of_matchs;i++) {
		System.out.println(pairing.get(i).getPlayer1().getFirstName()+" "+pairing.get(i).getPlayer1().getLastName()+" contre "+pairing.get(i).getPlayer2().getFirstName()+" "+pairing.get(i).getPlayer2().getLastName());
	}*/
	
	standings = FXCollections.observableArrayList();
	for(i=0;i<number_of_players;i++) {
		standings.add(players.get(Test.getplayers().get(i).getID()));
	}
	standings.sort(Comparator.comparing(User::getScore).thenComparing(User::getOppWin).thenComparing(User::getOppOppWin).reversed());
	pairingtable.setItems(standings);
	currentround++;
    }
    else if(currentround<=number_of_rounds) {
	//Round 2 to last :
	System.out.println("Round "+currentround);
	Test.SortByScore();
	grouped_players = Local_Player;
	grouped_players.sort(Comparator.comparing(User::getScore).thenComparing(User::getOppWin).thenComparing(User::getOppOppWin).reversed());
	pairing = Test.Matchmaking(grouped_players,currentround);
	obspairing = FXCollections.observableArrayList();
	for(i=0;i<pairing.size();i++) {
		obspairing.add(new StatMatch(pairing.get(i).getPlayer1(),pairing.get(i).getPlayer2(),pairing.get(i).getRound(),String.valueOf(i+1),"PENDING"));
		System.out.println(obspairing.get(i));
	}
	matchtable.setItems(obspairing);
	System.out.println(obspairing);
	matchbyround.add(pairing);
	for(i=0;i<number_of_matchs;i++) {
		System.out.println(pairing.get(i).getPlayer1().getFirstName()+" "+pairing.get(i).getPlayer1().getLastName()+" contre "+pairing.get(i).getPlayer2().getFirstName()+" "+pairing.get(i).getPlayer2().getLastName());
	}
	for(i=0;i<number_of_players;i++) {
		players.get(Test.getplayers().get(i).getID()).RefreshWinrate(currentround);
	}
	for(i=0;i<number_of_players;i++) {
		players.get(Test.getplayers().get(i).getID()).RefreshOppWin(currentround,players);
	}
	for(i=0;i<number_of_players;i++) {
		players.get(Test.getplayers().get(i).getID()).RefreshOppOppWin(currentround,players);
	}
	standings = FXCollections.observableArrayList();
	for(i=0;i<number_of_players;i++) {
		standings.add(players.get(Test.getplayers().get(i).getID()));
	}
	standings.sort(Comparator.comparing(User::getScore).thenComparing(User::getOppWin).thenComparing(User::getOppOppWin).reversed());
	pairingtable.setItems(standings);
	currentround++;
	}
    else {
    	standings = FXCollections.observableArrayList();
    	for(i=0;i<number_of_players;i++) {
    		standings.add(players.get(Test.getplayers().get(i).getID()));
    	}
    	standings.sort(Comparator.comparing(User::getScore).thenComparing(User::getOppWin).thenComparing(User::getOppOppWin).reversed());
    	pairingtable.setItems(standings);
    	Matches_label = new Label("Rounds finished");
    	Matches.getChildren().remove(0);
        Matches.getChildren().add(0,Matches_label);
    	System.out.println("Boup.");
    }
    pairingready=false;
    }
    });
	Test.SortByScore();
	System.out.println("Scores :");
	for(i=0;i<number_of_players;i++) {
		System.out.println(Test.getplayers().get(i).getFirstName()+Test.getplayers().get(i).getLastName()+" Score : "+Test.getplayers().get(i).getScore()+" | OppWin : "+Test.getplayers().get(i).getOppWin()+" | OppOppWin : "+Test.getplayers().get(i).getOppOppWin()+"</br>");
	}
	for(i=0;i<pairing.size();i++) {
		System.out.println(obspairing.get(i));
	}
	pairing_manage.setSpacing(5);
    pairing_manage.setPadding(new Insets(10, 0, 0, 10));
    pairing_manage.setTranslateX(scrnW*0.8);
    pairing_manage.setTranslateY(scrnH*0.9);
    pairing_manage.getChildren().addAll(refresh_score,refresh_pairing);
    
    ((Group) scene2.getRoot()).getChildren().addAll(Matches, Standings, pairing_manage, ManageResult);
	secondarystage.show();
	System.out.println("THE END?");
}
}