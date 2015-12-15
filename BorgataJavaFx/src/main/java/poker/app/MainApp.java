package poker.app;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import poker.app.view.PokerTableController;
import poker.app.view.RootLayoutController;
import pokerBase.Player;
import pokerBase.Table;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	public RootLayoutController rootController = null;
	private Table tbl;
	
	private int iGameType;
	
	@Override
	public void start(Stage primaryStage) {

		tbl = new Table();
		
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 1300, 500);

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Poker");

		// Set the application icon.
		this.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/res/img/26.png")));

		this.primaryStage.setScene(scene);
		this.primaryStage.show();

		initRootLayout();

		showPokerTable();

	}

	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);

			// Give the controller access to the main app.
			//RootLayoutController controller = loader.getController();
		    rootController = loader.getController();
			
			
		    rootController.setMainApp(this);

			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void showPokerTable() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PokerTable.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);

			// Give the controller access to the main app.
			PokerTableController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);		
	}
	
	public void AddPlayerToTable(Player p)
	{
		tbl.addPlayer(p);
	}
	
	public ArrayList<Player> GetSeatedPlayers()
	{
		return tbl.getPlayers();
	}
	
	public void RemovePlayerFromTable(int PlayerPosition)
	{
		Player playerToRemove = null;
		for (Player p: tbl.getPlayers())
		{
			if (p.getiPlayerPosition() == PlayerPosition)
			{
				playerToRemove = p;
				break;
			}
		}		
		tbl.removePlayer(playerToRemove);		
	}

	public int getiGameType() {
		return iGameType;
	}

	public void setiGameType(int iGameType) {
		this.iGameType = iGameType;
	}
	
/*	public ToggleGroup getToggleGroup()
	{
		ToggleGroup tgl = rootController.getTglGames();
		return tgl;
		

	}*/
	
	public String getRuleName()
	{
		return rootController.getRuleName();
	}
	
	
}