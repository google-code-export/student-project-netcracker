package ua.netcrackerteam.GUI;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.*;

/**
 * @author AlexK
 * @version 1.0.0
 */
public class GreetingsWindow extends Window {
    private MainPage mainPage;
    private String userName;
    private Label label;
    private VerticalLayout verticalLayout;
    private Button continueButton;
    private TabSheet pageTabs;

    public GreetingsWindow(MainPage mainPage, TextField username) {
        this.mainPage = mainPage;
        this.userName = String.valueOf(username);
        this.setIcon(new ThemeResource("icons/32/sun-icon.png"));
        setModal(true);
        setClosable(false);
        setWidth("20%");
        setResizable(false);
        center();
        setCaption("Поздравляем!");
        init();
    }

    private void init() {
        verticalLayout = new VerticalLayout();
        addComponent(verticalLayout);
        label = new Label("Поздравляем! Ты сделал первый шаг!");
        label.setWidth(null);
        continueButton = new Button("Продолжить");
        verticalLayout.addComponent(label);
        verticalLayout.addComponent(continueButton);

        verticalLayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
        verticalLayout.setComponentAlignment(continueButton, Alignment.MIDDLE_CENTER);

        continueButton.addListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                buttonClickContinue();
            }
        });
    }

    private void buttonClickContinue() {
        mainPage.changeMode(4, userName);
        pageTabs = mainPage.getPanel().getTabSheet();
        pageTabs.setSelectedTab(1);
        close();

    }
}
