package ua.netcrackerteam.GUI;

import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.StreamResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.terminal.gwt.server.WebBrowser;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Runo;
import ua.netcrackerteam.applicationForm.ApplicationForm;
import ua.netcrackerteam.controller.HRPage;
import ua.netcrackerteam.controller.StudentDataShort;
import ua.netcrackerteam.controller.StudentsMarks;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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
    private BlanksTable tableOfBlanks;
    private ResultsTable tableOfResults;
    private Button searchButton;
    private TextField something;
    private NativeSelect searchSelect;
    //private StudentDataShort selectedValueInTable = null;
    private final String username;
    private Link pdfLink;
    private int height;
    private MainPage mainPage;

    private Tree rightPanelTree;

    private Button              verificateBlankButton;
    private HorizontalLayout    buttonPanel;
    private Label               markLabel;
    private TextArea            markTextArea;
    private Button              markSaveButton;
    //private Button              viewAndPrintButton;
    private Button              deleteButton;
    private Button              editButton;


    private static final List<String> categories = Arrays.asList(new String[]{"Фамилия", "Имя", "Номер анкеты",
            "ВУЗ", "Курс", "Факультет", "Кафедра"});



    public HRBlankLayout(String userName, MainPage mainPage) {

        this.username = userName;
        this.mainPage = mainPage;
        WebApplicationContext context = (WebApplicationContext) mainPage.getContext();
        WebBrowser webBrowser = context.getBrowser();
        height = webBrowser.getScreenHeight()-290;

        final HorizontalSplitPanel horiz = new HorizontalSplitPanel();
        horiz.setStyleName(Runo.SPLITPANEL_REDUCED);
        horiz.setSplitPosition(200, Sizeable.UNITS_PIXELS);
        addComponent(horiz);
        //list of blanks, search - left panel
        leftPanel = new Panel("Навигация");
        leftPanel.setHeight("100%");
        VerticalLayout leftVL = (VerticalLayout) leftPanel.getContent();
        leftVL.setMargin(false);
        leftVL.setSpacing(true);

        //create grid layout for blanks, filling it with button
        blankGridLO = new GridLayout(1,1);
        blankGridLO.setSpacing(true);
        FillBlankGridLO();
        searchGridLO = new GridLayout(1,1);
        searchGridLO.setSpacing(true);
        FillSearchGridLO();
        Accordion leftSideAcc = new Accordion();
        leftSideAcc.setSizeFull();
        leftSideAcc.addTab(blankGridLO, "Анкеты");
        leftSideAcc.addTab(searchGridLO, "Поиск");
        leftPanel.addComponent(leftSideAcc);
        horiz.addComponent(leftPanel);
        //blank view - right panel
        rightPanel = new Panel("Информация");
        rightPanel.setHeight("100%");
        VerticalLayout rightVL = (VerticalLayout) rightPanel.getContent();
        rightVL.setMargin(false);
        rightVL.setSpacing(true);
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

        rightPanelTree = new Tree();
        int countOfAllForms = HRPage.getCountOfAllForms();
        String idAllBlanks = "1";
        String firstTreeTitle = "Все(" + String.valueOf(countOfAllForms) + ")";
        rightPanelTree.addItem(idAllBlanks);
        rightPanelTree.setItemCaption(idAllBlanks, firstTreeTitle);
        rightPanelTree.setValue(firstTreeTitle);
        rightPanelTree.setNullSelectionAllowed(false);
        rightPanelTree.setChildrenAllowed(firstTreeTitle,false);
        String idNonVerBlanks = "2";
        int countOfNonVerificated = HRPage.getCountOfNonVerificatedForms();
        String secondTreeTitle = "Не проверенные(" + String.valueOf(countOfNonVerificated)+")";
        rightPanelTree.addItem(idNonVerBlanks);
        rightPanelTree.setItemCaption(idNonVerBlanks, secondTreeTitle);
        rightPanelTree.setChildrenAllowed(secondTreeTitle, false);


        rightPanelTree.addListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                if (event.getItemId().equals("1"))  {
                    FillBlankTable(HRPage.getAllForms());
                    verificateBlankButton.setEnabled(false);
                    markTextArea.setEnabled(false);
                    markSaveButton.setEnabled(false);
                    editButton.setEnabled(true);
                }
                else if(event.getItemId().equals("2"))  {
                    FillBlankTable(HRPage.getNonVerificatedForms());
                    verificateBlankButton.setEnabled(true);
                    markTextArea.setEnabled(false);
                    markSaveButton.setEnabled(false);
                    editButton.setEnabled(false);
                }
            }
        });

        blankGridLO.addComponent(rightPanelTree);

        deleteButton = new Button("Удалить");
        deleteButton.setWidth("150");
        deleteButton.setIcon(new ThemeResource("icons/32/document-delete.png"));
        deleteButton.setStyleName(Runo.BUTTON_LINK);
        deleteButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!(tableOfBlanks.getValue()==null)) {
                    StudentDataShort selectedValue = (StudentDataShort)tableOfBlanks.getValue();
                    HRPage.deleteStudentBlank(selectedValue.getIdForm());
                    getWindow().showNotification(
                            "Анкета удалена!",
                            "",
                            Window.Notification.TYPE_TRAY_NOTIFICATION);
                    RefrashBlankGridLO();
                }
                else {
                    getWindow().showNotification(
                            "Ошибка!",
                            "Перед удалением выберите анкету из списка!",
                            Window.Notification.TYPE_TRAY_NOTIFICATION);
                }
            }
        });
        blankGridLO.addComponent(deleteButton);
        editButton = new Button("Редактировать");
        editButton.setWidth("150");
        editButton.setIcon(new ThemeResource("icons/32/document-edit.png"));
        editButton.setStyleName(Runo.BUTTON_LINK);
        editButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!(tableOfBlanks.getValue()==null)) {
                    final Window window = new Window("Window");
                    StudentDataShort selectedValue = (StudentDataShort)tableOfBlanks.getValue();
                    StudentBlank studentBlank = new StudentBlank(HRPage.getUserNameByFormId(selectedValue.getIdForm()), mainPage, username);
                    window.setCaption("Просмотр анкеты");
                    window.setContent(studentBlank);
                    window.setHeight("80%");
                    window.setWidth("80%");
                    getWindow().addWindow(window);



                    /*getWindow().showNotification(
                            "Анкета подтверждена!",
                            "",
                            Window.Notification.TYPE_TRAY_NOTIFICATION);
                    FillBlankTable(HRPage.getNonVerificatedForms());*/
                }
                else {
                    getWindow().showNotification(
                            "Ошибка!",
                            "Перед редактированием выберите анкету из списка!",
                            Window.Notification.TYPE_TRAY_NOTIFICATION);
                }
            }
        });
        blankGridLO.addComponent(editButton);

        verificateBlankButton = new Button("Подтвердить");
        verificateBlankButton.setWidth("150");
        verificateBlankButton.setIcon(new ThemeResource("icons/32/Checkbox-Full-icon.png"));
        verificateBlankButton.setStyleName(Runo.BUTTON_LINK);
        verificateBlankButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!(tableOfBlanks.getValue()==null)) {
                    StudentDataShort selectedValue = (StudentDataShort)tableOfBlanks.getValue();
                    HRPage.verificateForm(selectedValue.getIdForm());
                    getWindow().showNotification(
                            "Анкета подтверждена!",
                            "",
                            Window.Notification.TYPE_TRAY_NOTIFICATION);
                    FillBlankTable(HRPage.getNonVerificatedForms());
                    RefrashBlankGridLO();
                }
                else {
                    getWindow().showNotification(
                            "Ошибка!",
                            "Перед подтверждением выберите анкету из списка!",
                            Window.Notification.TYPE_TRAY_NOTIFICATION);
                }
            }
        });
        verificateBlankButton.setEnabled(false);
        blankGridLO.addComponent(verificateBlankButton);
        //rightPanel.addComponent(buttonPanel);

        RefrashBlankGridLO();

    }

    private void RefrashBlankGridLO() {

        int countOfAllForms = HRPage.getCountOfAllForms();
        String idAllBlanks = "1";
        String idNonVerBlanks = "2";
        String firstTreeTitle = "Все(" + String.valueOf(countOfAllForms) + ")";
        int countOfNonVerificated = HRPage.getCountOfNonVerificatedForms();
        String secondTreeTitle = "Не проверенные(" + String.valueOf(countOfNonVerificated)+")";
        rightPanelTree.setItemCaption(idAllBlanks, firstTreeTitle);
        rightPanelTree.setItemCaption(idNonVerBlanks, secondTreeTitle);

    }

    private Link getPDFLink() {
        StreamResource resource = new StreamResource(new PdfStreamSource(), "form.pdf", mainPage);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String filename = "form-" + df.format(new Date()) + ".pdf";
        resource.setFilename(filename);
        resource.setCacheTime(0);
        Link pdf = new Link("Просмотреть/Распечатать анкету",resource);
        pdf.setTargetName("_blank");
        pdf.setTargetWidth(600);
        pdf.setTargetHeight(height-10);
        pdf.setTargetBorder(Link.TARGET_BORDER_NONE);
        pdf.setDescription("Анкета студента (откроется в новом окне)");
        ThemeResource icon = new ThemeResource("icons/32/document-pdf.png");
        pdf.setIcon(icon);
        return pdf;
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
        searchButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!(something.getValue().equals(""))){
                    //if (rightPanelTree.getValue())
                    String filter   = (String) searchSelect.getValue();
                    String value    = (String) something.getValue();
                    List<StudentDataShort> data = HRPage.searchStudents(filter, value);
                    FillBlankTable(data);
                }
            }
        });
        searchGridLO.addComponent(searchButton);
    }

    private void FillBlankTable(List<StudentDataShort> data) {


        Table oldTable = tableOfBlanks;
        tableOfBlanks = new BlanksTable(new BeanItemContainer(StudentDataShort.class, data));
        rightPanel.replaceComponent(oldTable, tableOfBlanks);

    }

    private void FillResultsTable(List<StudentsMarks> data) {
        Table oldTable = tableOfResults;
        tableOfResults = new ResultsTable(new BeanItemContainer(StudentsMarks.class, data));
        rightPanel.replaceComponent(oldTable, tableOfResults);
    }


    private void FillInformationPanel() {

        //data source
        tableOfBlanks = new BlanksTable(new BeanItemContainer(StudentDataShort.class, HRPage.getAllForms()));
        tableOfBlanks.addListener(new Table.ValueChangeListener() {
            public void valueChange(Property.ValueChangeEvent event) {
                Object value = event.getProperty().getValue();
                if (!(null == value)) {
                    StudentDataShort selectedValue = (StudentDataShort)value;
                    markTextArea.setEnabled(true);
                    markSaveButton.setEnabled(true);
                    List<StudentsMarks> currMarks = HRPage.getStudentMark(selectedValue.getIdForm());
                    FillResultsTable(currMarks);
                }
                else {
                    markTextArea.setEnabled(false);
                    markSaveButton.setEnabled(false);
                }
            }
        });
        rightPanel.addComponent(tableOfBlanks);

        tableOfResults = new ResultsTable(new BeanItemContainer(StudentsMarks.class, null));
        rightPanel.addComponent(tableOfResults);

        markLabel = new Label("Введите оценку:");
        rightPanel.addComponent(markLabel);
        markTextArea = new TextArea();
        markTextArea.setRows(2);
        markTextArea.setColumns(40);
        markTextArea.setRequired(true);
        markTextArea.setEnabled(false);
        rightPanel.addComponent(markTextArea);

        markSaveButton = new Button("Сохранить");
        markSaveButton.setIcon(new ThemeResource("icons/32/page_table_warning.png"));
        markSaveButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!markTextArea.getValue().equals("") && (!(tableOfBlanks.getValue() == null))) {
                    StudentDataShort selectedValue = (StudentDataShort) tableOfBlanks.getValue();
                    HRPage.setStudentMark(selectedValue.getIdForm(), username,markTextArea.getValue().toString());
                    getWindow().showNotification(
                            "Оценка выставлена!",
                            "",
                            Window.Notification.TYPE_TRAY_NOTIFICATION);
                    markTextArea.setEnabled(false);
                    markSaveButton.setEnabled(false);
                    RefrashBlankGridLO();
                    List<StudentsMarks> currMarks = HRPage.getStudentMark(selectedValue.getIdForm());
                    FillResultsTable(currMarks);
                } else {
                    getWindow().showNotification(
                            "Ошибка!",
                            "Перед сохранением выберите анкету из списка и введите оценку!",
                            Window.Notification.TYPE_TRAY_NOTIFICATION);
                }
            }
        });
        rightPanel.addComponent(markSaveButton);

        pdfLink = new Link();
        rightPanel.addComponent(pdfLink);
        markSaveButton.setEnabled(false);

        //button panels
        /*buttonPanel = new HorizontalLayout();
        deleteButton = new Button("Удалить");
        deleteButton.setIcon(new ThemeResource("icons/32/trash.png"));
        deleteButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!(selectedValueInTable==null)) {
                    HRPage.deleteStudentBlank(selectedValueInTable.getIdForm());
                    getWindow().showNotification(
                            "Анкета удалена!",
                            "",
                            Window.Notification.TYPE_TRAY_NOTIFICATION);
                    RefrashBlankGridLO();
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
        editButton = new Button("Редактировать");
        editButton.setIcon(new ThemeResource("icons/32/document-edit.png"));
        editButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!(selectedValueInTable==null)) {
                    final Window window = new Window("Window");

                    StudentBlank studentBlank = new StudentBlank(HRPage.getUserNameByFormId(selectedValueInTable.getIdForm()), mainPage);
                    window.setCaption("Просмотр анкеты");
                    window.setContent(studentBlank);
                    window.setHeight("80%");
                    window.setWidth("80%");
                    getWindow().addWindow(window);



                    *//*getWindow().showNotification(
                            "Анкета подтверждена!",
                            "",
                            Window.Notification.TYPE_TRAY_NOTIFICATION);
                    FillBlankTable(HRPage.getNonVerificatedForms());*//*
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

        verificateBlankButton = new Button("Подтвердить");
        verificateBlankButton.setIcon(new ThemeResource("icons/32/Checkbox-Full-icon.png"));
        verificateBlankButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!(selectedValueInTable==null)) {
                    HRPage.verificateForm(selectedValueInTable.getIdForm());
                    getWindow().showNotification(
                            "Анкета подтверждена!",
                            "",
                            Window.Notification.TYPE_TRAY_NOTIFICATION);
                    FillBlankTable(HRPage.getNonVerificatedForms());
                    RefrashBlankGridLO();
                }
                else {
                    getWindow().showNotification(
                            "Ошибка!",
                            "Перед подтверждением выберите анкету из списка!",
                            Window.Notification.TYPE_TRAY_NOTIFICATION);
                }
            }
        });
        verificateBlankButton.setEnabled(false);
        buttonPanel.addComponent(verificateBlankButton);
        rightPanel.addComponent(buttonPanel);*/
    }

    private class BlanksTable extends Table {

        private Object[] NATURAL_COL_ORDER = new Object[] {
                "idForm", "studentLastName", "studentFirstName", "studentMiddleName", "studentInstitute",
                "studentInstituteCourse", "studentFaculty", "studentCathedra" };
        private String[] COL_HEADERS_RUSSIAN = new String[] {
                "Номер анкеты", "Фамилия", "Имя", "Отчество",
                "ВУЗ", "Курс", "Факультет", "Кафедра" };

        private BlanksTable(Container dataSource) {
            super();
            setWidth("100%");
            //setHeight(height-400,UNITS_PIXELS);
            setHeight("300");
            setSelectable(true);
            setImmediate(true);
            setContainerDataSource(dataSource);
            setColumnReorderingAllowed(true);
            setColumnCollapsingAllowed(true);
            setVisibleColumns(NATURAL_COL_ORDER);
            setColumnHeaders(COL_HEADERS_RUSSIAN);
            addListener(new Table.ValueChangeListener() {

                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    Object value = event.getProperty().getValue();
                    if (!(null == value)) {
                        StudentDataShort selectedValue = (StudentDataShort)value;
                        selectedValue = (StudentDataShort) value;
                        markTextArea.setValue("");
                        markTextArea.setEnabled(true);
                        markSaveButton.setEnabled(true);
                        Link oldPDFLink = pdfLink;
                        pdfLink = getPDFLink();
                        rightPanel.replaceComponent(oldPDFLink, pdfLink);
                    }
                }
            });
        }
    }

    private class ResultsTable extends Table {

        private Object[] NATURAL_COL_ORDER = new Object[] {
                "idUser", "userName", "studentMark"};
        private String[] COL_HEADERS_RUSSIAN = new String[] {
                "Идентификатор пользователя", "Имя пользователя", "Оценка"};

        private ResultsTable(Container dataSource) {
            super();
            setWidth("100%");
            //setHeight(height/7, UNITS_PIXELS);
            setHeight("100");
            setSelectable(false);
            setImmediate(true);
            if (!(dataSource == null)) {
                setContainerDataSource(dataSource);
            }
            setColumnReorderingAllowed(true);
            setColumnCollapsingAllowed(true);
            setVisibleColumns(NATURAL_COL_ORDER);
            setColumnHeaders(COL_HEADERS_RUSSIAN);
        }
    }

    private class PdfStreamSource implements StreamResource.StreamSource {

        @Override
        public InputStream getStream() {
            StudentDataShort selectedValue = (StudentDataShort)tableOfBlanks.getValue();
            ApplicationForm form = new ApplicationForm(selectedValue.getIdForm());
            return new ByteArrayInputStream(form.generateFormPDF());
        }
    }

}
