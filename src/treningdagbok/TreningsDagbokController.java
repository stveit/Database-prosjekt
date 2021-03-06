package src.treningdagbok;

import java.sql.*;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TreningsDagbokController {

	@FXML private Button newApparatButton;
	@FXML private TextField apparatIDText;
	@FXML private TextField apparatNavnText;
	@FXML private TextField apparatBeskrivelseText;
	@FXML private TextArea apparatTextArea;

	@FXML private Button updateButton;

	@FXML private Button newOvelseButton;
	@FXML private TextField ovelseIDText;
	@FXML private TextField ovelseNavnText;
	@FXML private TextField ovelseBeskrivelseText;
	@FXML private TextField ovelseApparatIDText;


	private Connection conn;
	private Apparat a;

	@FXML
	private void initialize() {

	}

	@FXML
	public void newApparat() {
		try {
			Apparat a = new Apparat(Integer.parseInt(apparatIDText.getText()));
			this.a = a;
			a.setNavn(apparatNavnText.getText());
			a.setBeskrivelse(apparatBeskrivelseText.getText());
			a.save(conn);
		}
		catch (Exception e) {
			System.out.println("Error med å lage nytt apparat: "+e);
		}
	}

	@FXML
	public void newOvelse() {
		Øvelse o = new Øvelse(Integer.parseInt(ovelseIDText.getText()));
		o.setNavn(apparatNavnText.getText());
		o.setBeskrivelse(ovelseBeskrivelseText.getText());
		o.setApparatid(Integer.parseInt(ovelseApparatIDText.getText()));
		o.save(conn);
	}

	@FXML
	public void update() {
		String s = a.getApparatID()+"";
		s += "\n"+a.getNavn();
		s+= "\n"+a.getBeskrivelse();
		this.apparatTextArea.setText(s);
	}




}
