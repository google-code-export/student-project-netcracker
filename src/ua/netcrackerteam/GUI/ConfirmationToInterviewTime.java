package ua.netcrackerteam.GUI;

import com.vaadin.ui.*;

/**
 * @author AlexK
 * @version 1.0.0
 */
public class ConfirmationToInterviewTime extends Window implements Button.ClickListener {
    private MainPage mainPage;
    private String userName;
    private Button yesButton;
    private VerticalLayout layout = (VerticalLayout) getContent();
    private HorizontalLayout buttonsPanel = new HorizontalLayout();
    private static final String CONFIRMATION_MESSAGE ="Поздравляем, шаг 1 закончен! Перейдите к шагу 2: запись на собеседование.";
    private static final String WARNING_MESSAGE = "Внимание! Вы не сможете пройти собеседование, предварительно не записавшись на него.";
    private Label message;
    private Label message2;
    private TabSheet pageTabs;

	public ConfirmationToInterviewTime(MainPage mainPage, String username) {
		this.userName = username;
		this.mainPage = mainPage;
		setCaption("Запись на собеседование");
		setModal(true);
		setWidth(300,UNITS_PIXELS);
		setResizable(false);
		center();

		initButtons();
		initLayouts();
	}

	private void initButtons() {
		yesButton = new Button("Продолжить");
		yesButton.setVisible(true);
		yesButton.addListener(this);
		message = new Label(CONFIRMATION_MESSAGE);
        message2 = new Label(WARNING_MESSAGE);
	}

	private void initLayouts() {
		layout.setWidth("100%");
		layout.setSpacing(true);
		layout.setMargin(true);
		layout.addComponent(message);
		layout.setComponentAlignment(message, Alignment.TOP_CENTER);
        layout.addComponent(message2);
        layout.setComponentAlignment(message2, Alignment.TOP_CENTER);
		layout.addComponent(buttonsPanel);
		layout.setComponentAlignment(buttonsPanel, Alignment.BOTTOM_CENTER);
		buttonsPanel.addComponent(yesButton);
	}

	@Override
	public void buttonClick(Button.ClickEvent event) {
		Button source = event.getButton();
		if (source == yesButton) {
			pageTabs = mainPage.getPanel().getTabSheet();
			pageTabs.setSelectedTab(2);
			ConfirmationToInterviewTime.this.close();
		}
	}
}
