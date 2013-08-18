package ua.netcrackerteam.GUI;

import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.terminal.gwt.server.WebBrowser;
import com.vaadin.ui.*;
import ua.netcrackerteam.controller.bean.DifferenceData;
import ua.netcrackerteam.controller.HRPage;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Bri
 * Date: 08.04.13
 * Time: 5:55
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("serial")
public class BlankDifferensesWindow extends Window implements Button.ClickListener {
    private Integer formId;
    private DifferenceDataTable table;
    private List<DifferenceData> currUserDiffData = null;
    private int height;
    HorizontalLayout buttonsLayout;
    VerticalLayout tableLayout;
    Button exitButton;

    public BlankDifferensesWindow(MainPage mainPage, Integer formID) {
        this.formId = formID;
        setModal(true);
        setWidth(800);
        setResizable(false);
        center();
        setCaption("Отличия в анкетах студентов");

        WebApplicationContext context = (WebApplicationContext) mainPage.getContext();
        WebBrowser webBrowser = context.getBrowser();
        height = webBrowser.getScreenHeight()-440;

        buttonsLayout = new HorizontalLayout();
        addComponent(buttonsLayout);
        tableLayout = new VerticalLayout();
        addComponent(tableLayout);

        exitButton = new Button("Exit");
        exitButton.setVisible(true);
        exitButton.setIcon(new ThemeResource("icons/32/exit.png"));
        buttonsLayout.addComponent(exitButton);
        exitButton.addListener(this);

        currUserDiffData = HRPage.getDifferencesOfBlanks(this.formId);

        table = new DifferenceDataTable(new BeanItemContainer<DifferenceData>(DifferenceData.class, currUserDiffData));
        tableLayout.addComponent(table);
    }


    @Override
    public void buttonClick(Button.ClickEvent event) {
        Button source = event.getButton();
        if (source == exitButton) {
            BlankDifferensesWindow.this.close();
        }
    }

    private class DifferenceDataTable extends Table {

        private Object[] NATURAL_COL_ORDER = new Object[] {
                "fieldName", "oldValue", "newValue"};
        private String[] COL_HEADERS_RUSSIAN = new String[] {
                "Поле", "Старое значение", "Новое значение"};

        private DifferenceDataTable(Container dataSource) {
            super();
            setWidth("100%");
            //setHeight(height/7, UNITS_PIXELS);
            setSelectable(false);
            setImmediate(true);
            setWidth("100%");
            setHeight(height);
            if (!(dataSource == null)) {
                setContainerDataSource(dataSource);
            }
            setColumnReorderingAllowed(true);
            setColumnCollapsingAllowed(true);
            setVisibleColumns(NATURAL_COL_ORDER);
            setColumnHeaders(COL_HEADERS_RUSSIAN);
        }
    }

}
