package ua.netcrackerteam.GUI;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Runo;
import ua.netcrackerteam.DAO.DAOHRImpl;
import ua.netcrackerteam.controller.InterviewerPage;
import ua.netcrackerteam.controller.StudentDataShort;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

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
    private Button searchButton;
    private TextField something;
    private NativeSelect searchSelect;
    private StudentDataShort selectedValueInTable = null;


    private HorizontalLayout    buttonPanel;
    private Label               markLabel;
    private TextArea            markTextArea;
    private Button              markSaveButton;
    private Button              viewAndPrintButton;
    private Button              deleteButton;
    private Button              editButton;

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
        Button source = event.getButton();
        if (source == searchButton) {
            onButtonSearchClick();
        }
    }

    private void onButtonSearchClick() {
        String somethingValue = (String)something.getValue();
        String inWhichColumn = (String)searchSelect.getValue();
        //List <StudentDataShort> searchResults = DAOHRImpl.searchSomethingSomewere(inWhichColumn, somethingValue);
        //BeanItemContainer<StudentDataShort> bean = new BeanItemContainer(StudentDataShort.class, searchResults);
        //tableOfBlanks.setContainerDataSource(bean);
    }

    private void FillBlankGridLO(){

    }

    private void FillSearchGridLO(){
        //creating native select
        searchSelect = new NativeSelect("Критерии поиска:");
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
        something = new TextField();
        something.setInputPrompt("Что ищем...");
        something.setImmediate(true);
        something.setRequired(true);
        searchGridLO.addComponent(something);
        //add search buttom
        searchButton = new Button("Найти");
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

        tableOfBlanks.addListener(new Table.ValueChangeListener() {
            public void valueChange(Property.ValueChangeEvent event) {
                Set<?> value = (Set<?>) event.getProperty().getValue();
                if (null == value || value.size() == 0) {
                    selectedValueInTable = (StudentDataShort) value;
                }
            }
        });

        rightPanel.addComponent(tableOfBlanks);

        markLabel = new Label("Введите оценку:");
        rightPanel.addComponent(markLabel);
        markTextArea = new TextArea();
        markTextArea.setRows(5);
        markTextArea.setColumns(40);
        markTextArea.setRequired(true);
        rightPanel.addComponent(markTextArea);

        markSaveButton = new Button("Сохранить оценку");
        markSaveButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                String insertedBlankValue = "";
                if (!insertedBlankValue.equals("")&&(!(selectedValueInTable==null))) {
                    //DAOHRImpl.setBlankMark(selectedValueInTable, insertedBlankValue);
                }
                else {
                    getWindow().showNotification(
                            "Ошибка!",
                            "Перед сохранением выберите анкету из списка и введите оценку!",
                            Window.Notification.TYPE_TRAY_NOTIFICATION);
                }
            }
        });
        rightPanel.addComponent(markSaveButton);

        //button panels
        buttonPanel = new HorizontalLayout();
        viewAndPrintButton = new Button("Распечатать анкету");
        viewAndPrintButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!(selectedValueInTable==null)) {
                    //some actions
                }
                else {
                    getWindow().showNotification(
                            "Ошибка!",
                            "Перед печатью выберите анкету из списка!",
                            Window.Notification.TYPE_TRAY_NOTIFICATION);
                }
            }
        });
        buttonPanel.addComponent(viewAndPrintButton);
        deleteButton = new Button("Удалить анкету");
        deleteButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!(selectedValueInTable==null)) {
                    //some actions
                }
                else {
                    getWindow().showNotification(
                            "Ошибка!",
                            "Перед удалением выберите анкету из списка!",
                            Window.Notification.TYPE_TRAY_NOTIFICATION);
                }
            }
        });
        buttonPanel.addComponent(deleteButton);
        editButton = new Button("Редактировать анкету");
        editButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!(selectedValueInTable==null)) {
                    //some actions
                }
                else {
                    getWindow().showNotification(
                            "Ошибка!",
                            "Перед редактированием выберите анкету из списка!",
                            Window.Notification.TYPE_TRAY_NOTIFICATION);
                }
            }
        });
        buttonPanel.addComponent(editButton);
        rightPanel.addComponent(buttonPanel);
    }

}
