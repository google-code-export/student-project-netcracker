package ua.netcrackerteam.GUI;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Runo;
import ua.netcrackerteam.controller.InterviewerPage;
import ua.netcrackerteam.controller.StudentDataShort;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Bri
 * Date: 09.03.13
 * Time: 19:04
 * To change this template use File | Settings | File Templates.
 */
public class HRBlankLayout extends VerticalLayout implements Button.ClickListener{

    private Panel leftPanel;
    private Panel rightPanel;
    private GridLayout blankGridLO;
    private GridLayout searchGridLO;
    private Table tableOfBlanks = new Table();

    private static final List<String> categories = Arrays.asList(new String[]{"Фамилия", "Имя", "Номер анкеты",
            "ВУЗ", "Курс", "Факультет", "Кафедра"});

    private Object[] NATURAL_COL_ORDER = new Object[] {
            "idForm", "studentLastName", "studentFirstName", "studentMiddleName", "studentInstitute",
            "studentInstituteCourse", "studentFaculty", "studentCathedra" };
    private String[] COL_HEADERS_RUSSIAN = new String[] {
            "Номер анкеты", "Фамилия", "Имя", "Отчество",
            "ВУЗ", "Курс", "Факультет", "Кафедра" };

    public HRBlankLayout() {

        final HorizontalSplitPanel horiz = new HorizontalSplitPanel();
        horiz.setStyleName(Runo.SPLITPANEL_REDUCED);
        horiz.setSplitPosition(200, Sizeable.UNITS_PIXELS);
        addComponent(horiz);
        //list of blanks, search - left panel
        leftPanel = new Panel("Навигация");
        leftPanel.setHeight("100%");

        //create grid layout for blanks, filling it with button
        blankGridLO = new GridLayout(1,1);
        blankGridLO.setSpacing(true);
        FillBlankGridLO();
        //create grid layout for search, filling it with native select
        //Label labelSearch = new Label("Search");
        searchGridLO = new GridLayout(1,1);
        searchGridLO.setSpacing(true);
        FillSearchGridLO();
        Accordion leftSideAcc = new Accordion();
        leftSideAcc.setSizeFull();
        leftSideAcc.addTab(blankGridLO,  "Анкеты");
        leftSideAcc.addTab(searchGridLO, "Поиск");
        leftPanel.addComponent(leftSideAcc);
        horiz.addComponent(leftPanel);
        //blank view - right panel
        rightPanel = new Panel("Информация");
        rightPanel.setHeight("100%");
        FillInformationPanel();
        horiz.addComponent(rightPanel);
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    private void FillBlankGridLO(){
        //Button allBlanks = new Button("Все анкеты");
        //allBlanks.addListener(this);
        Label allBlanks = new Label("Список анкет приведен в таблице справа");
        blankGridLO.addComponent(allBlanks);
    }

    private void FillSearchGridLO(){
        //creating native select
        NativeSelect searchSelect = new NativeSelect("Критерии поиска:");
        //filling ns
        for (String currCategory:categories) {
            searchSelect.addItem(currCategory);
        }
        searchSelect.setNullSelectionAllowed(false);
        searchSelect.setValue(categories.get(0));
        searchSelect.setImmediate(true);
        //add to LO
        searchGridLO.addComponent(searchSelect);
        //add text field
        TextField something = new TextField();
        something.setInputPrompt("Что ищем...");
        something.setImmediate(true);
        something.setRequired(true);
        searchGridLO.addComponent(something);
        //add search buttom
        Button searchButton = new Button("Найти");
        searchButton.addListener(this);
        searchGridLO.addComponent(searchButton);
    }

    private void FillInformationPanel() {

        tableOfBlanks.setWidth("100%");
        tableOfBlanks.setHeight("100%");

        // selectable
        tableOfBlanks.setSelectable     (true);
        tableOfBlanks.setMultiSelect    (false);
        tableOfBlanks.setImmediate      (true);

        //data source
        List<StudentDataShort> stData = InterviewerPage.getAllStudents();
        BeanItemContainer<StudentDataShort> bean = new BeanItemContainer(StudentDataShort.class, stData);

        // connect data source
        tableOfBlanks.setContainerDataSource(bean);

        // turn on column reordering and collapsing
        tableOfBlanks.setColumnReorderingAllowed(true);
        tableOfBlanks.setColumnCollapsingAllowed(true);

        tableOfBlanks.setVisibleColumns(NATURAL_COL_ORDER);

        //headers
        tableOfBlanks.setColumnHeaders(COL_HEADERS_RUSSIAN);

        rightPanel.addComponent(tableOfBlanks);

    }
}
