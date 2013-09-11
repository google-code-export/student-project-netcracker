
package ua.netcrackerteam.GUI;

import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

public class StopRegistrationWindow extends Window {

	public StopRegistrationWindow() {
		setWidth(300, UNITS_PIXELS);
		setResizable(false);
		center();
		setModal(true);
		setCaption("Регистрация");
		Label description = new Label("Извините, набор закончен! Ждем Вас в следующем году.");
		getContent().addComponent(description);
	}

}
