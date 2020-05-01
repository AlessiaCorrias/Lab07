/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Blackout;
import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	Model model;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="comboNERC"
	private ComboBox<Nerc> comboNERC; // Value injected by FXMLLoader

	@FXML // fx:id="txtYears"
	private TextField txtYears; // Value injected by FXMLLoader

	@FXML // fx:id="txtHours"
	private TextField txtHours; // Value injected by FXMLLoader

	@FXML // fx:id="btnWorstCase"
	private Button btnWorstCase; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader

	@FXML
	void doWorstCaseAnalysis(ActionEvent event) {
		txtResult.clear();

		String sY = txtHours.getText();
		String sX = txtYears.getText();
		Nerc nerc = null;

		if (comboNERC.getValue() != null)
			nerc = comboNERC.getValue();
		else 
			txtResult.setText("Selezionare un NERC ");

		try {
			int Y = Integer.parseInt(sY);
			int X = Integer.parseInt(sX);

			List<Blackout> blackouts = model.doWorstCaseAnalysis(nerc, X, Y);
			if(blackouts != null) {
			Collections.reverse(blackouts);
			txtResult.appendText("Tot people affected: "+model.getTotPeople(blackouts)+"\n");
			txtResult.appendText("Tot hours of outage: "+model.getToth(blackouts)+"\n");
			for(Blackout b: blackouts) {
				
				txtResult.appendText(b.toString());
			}
			} else {
				txtResult.appendText("errore");
			}

		} catch (NumberFormatException e) {
			txtResult.setText("Inserire un numero di ore di anni > 0");
		}

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert comboNERC != null : "fx:id=\"comboNERC\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnWorstCase != null : "fx:id=\"btnWorstCase\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

	}

	public void setModel(Model model) {
		this.model = model;
		comboNERC.getItems().addAll(model.getNercList());

	}

}
