/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.StreamResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.terminal.gwt.server.WebBrowser;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.themes.Runo;
import ua.netcrackerteam.controller.InterviewerPage;
import ua.netcrackerteam.controller.StudentDataShort;
import ua.netcrackerteam.controller.StudentInterview;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import ua.netcrackerteam.applicationForm.ApplicationForm;

/**
 * Panel for Interviewer view
 * @author Anna Kushnirenko
 */
public class MainPanelInterviewer extends MainPanel{
    
    private VerticalLayout interviewsLo;
    private Accordion accordion;
    private int height;
    private final MainPage mainPage;
    private static final List<String> categories = Arrays.asList(new String[] { "Фамилия", "Имя", "Номер анкеты",
            "ВУЗ", "Курс", "Факультет", "Кафедра" });

    private TextArea markField;
    private Panel rightPanel;
    private StudentsTable table;
    private Button saveEdit;
    private Link pdfLink;
    private VerticalLayout bottomLayout;
    private final String username;
    private int currFormID;
    private NativeSelect searchFilter;
    private Tree tree;
    private TextField searchField;
    private SettingsLayout settingsLayout;
    
    public MainPanelInterviewer(final HeaderLayout hlayout, final MainPage mainPage) {
        super(hlayout,mainPage);
        this.mainPage = mainPage;
        this.username = hlayout.getUsername();
        setContent(getUserLayout(hlayout));
        WebApplicationContext context = (WebApplicationContext) mainPage.getContext();
        WebBrowser webBrowser = context.getBrowser();
        height = webBrowser.getScreenHeight()-290;
        
        final Component c = new VerticalLayout();
        final Component c2 = new VerticalLayout();
        tabSheet.addTab(c,"Собеседования");
        tabSheet.addTab(c2,"Настройки");
        tabSheet.addListener(new TabSheet.SelectedTabChangeListener() {

            @Override
            public void selectedTabChange(SelectedTabChangeEvent event) {
                final TabSheet source = (TabSheet) event.getSource();
                if(source.getSelectedTab() == c) {
                    interviewsLo = new VerticalLayout();
                    interviewsLo.setHeight(height,UNITS_PIXELS);
                    interviewsLo.setWidth("100%");
                    interviewsLo.setMargin(true, false, false, false);
                    fillInterviewsLayout();
                    tabSheet.replaceComponent(c, interviewsLo);
                } else if (source.getSelectedTab() == c2) {
                    settingsLayout = new SettingsLayout(hlayout.getUsername(), mainPage);
                    source.replaceComponent(c2, settingsLayout);
                }
            }
        });
    }

    private void fillInterviewsLayout() {
        HorizontalSplitPanel splitH = new HorizontalSplitPanel();
        splitH.setStyleName(Runo.SPLITPANEL_REDUCED);
        splitH.setSplitPosition(200, Sizeable.UNITS_PIXELS);
        interviewsLo.addComponent(splitH);
        
        Panel sidebar = new Panel("Навигация");
        fillSideBar(sidebar);
        splitH.setFirstComponent(sidebar);
        
        rightPanel = new Panel("Информация");
        fillRightPanel();
        splitH.setSecondComponent(rightPanel);
    }

    private void fillSideBar(Panel sidebar) {
        sidebar.setHeight("100%");
        accordion = new Accordion();
        accordion.setSizeFull();
        accordion.addTab(getTreeMenu(), "Списки");
        accordion.addTab(getSearchLayout(), "Быстрый поиск");
        sidebar.setContent(accordion);
    }

    private void fillRightPanel() {
        rightPanel.setHeight("100%");
        VerticalLayout vl = (VerticalLayout) rightPanel.getContent();
        vl.setMargin(false);
        vl.setSpacing(true);
        List<StudentDataShort> stData = InterviewerPage.getAllStudents();
        BeanItemContainer<StudentDataShort> bean = new BeanItemContainer(StudentDataShort.class, stData);
        table = new StudentsTable(bean);
        rightPanel.addComponent(table);

        bottomLayout = new VerticalLayout();
        bottomLayout.setSpacing(true);
        bottomLayout.setMargin(true);
        bottomLayout.setVisible(false);
        rightPanel.addComponent(bottomLayout);
        
        markField = new TextArea("Оценка интервьюера:");
        markField.setWidth("100%");
        markField.setRequired(true);
        markField.setRows(4);
        bottomLayout.addComponent(markField);
        
        pdfLink = new Link();
        bottomLayout.addComponent(pdfLink);
        
        saveEdit = new Button();
        saveEdit.addListener(new SaveOrEditButtonListener());
        saveEdit.setWidth("150");
        bottomLayout.addComponent(saveEdit);
        bottomLayout.setComponentAlignment(saveEdit, Alignment.MIDDLE_CENTER);
    }

    private Component getTreeMenu() {
        List<StudentInterview> interviews = ua.netcrackerteam.controller.RegistrationToInterview.getInterviews();
        tree = new Tree();
        String all = "Все студенты";
        tree.addItem(all);
        tree.setValue(all);
        tree.setNullSelectionAllowed(false);
        tree.setChildrenAllowed(all,false);
        tree.setItemIcon(all,new ThemeResource("icons/32/users.png"));
        String dates = "Дата собеседования";
        tree.addItem(dates);
        tree.setItemIcon(dates,new ThemeResource("icons/32/calendar.png"));
        tree.setChildrenAllowed(dates, true);
        tree.expandItem(dates);
        for(StudentInterview stInterview : interviews) {
            Format formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");      
            String strDate = formatter.format(stInterview.getInterviewStartDate());
            tree.addItem(stInterview);
            tree.setChildrenAllowed(stInterview,false);
            tree.setItemCaption(stInterview,strDate);
            tree.setParent(stInterview, "Дата собеседования");
            tree.setItemIcon(stInterview,new ThemeResource("icons/32/blue_point.png"));
        }
        tree.addListener(new SelectTreeItem());
        return tree;
    }

    private Component getSearchLayout() {
        VerticalLayout searchLayout = new VerticalLayout();
        searchLayout.setSpacing(true);
        searchLayout.setMargin(true);
        
        searchFilter = new NativeSelect("Критерии поиска:",categories);
        searchFilter.setNullSelectionAllowed(false);
        searchFilter.setValue("Фамилия");
        searchFilter.setImmediate(true);
        searchFilter.setWidth("100%");
        searchLayout.addComponent(searchFilter);
                
        searchField = new TextField();
        searchField.setWidth("100%");
        searchField.setRequired(true);
        Button searchButton = new Button("Найти");
        searchButton.addListener(new SearchListener());
        searchLayout.addComponent(searchField);
        searchLayout.addComponent(searchButton);
        searchLayout.setComponentAlignment(searchButton, Alignment.TOP_CENTER);
        return searchLayout;
    }
    
    private Link getPDFLink() {
        StreamResource resource = new StreamResource(new PdfStreamSource(), "form.pdf", mainPage);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String filename = "form-" + df.format(new Date()) + ".pdf";
        resource.setFilename(filename);
        resource.setCacheTime(0);
        Link pdf = new Link("Анкета",resource);
        pdf.setTargetName("_blank");
        pdf.setTargetWidth(600);
        pdf.setTargetHeight(height-10);
        pdf.setTargetBorder(Link.TARGET_BORDER_NONE);
        pdf.setDescription("Анкета студента (откроется в новом окне)");
        ThemeResource icon = new ThemeResource("icons/32/document-pdf.png");
        pdf.setIcon(icon);
        return pdf;
    }
    
    private TextArea getMarkField() {
        TextArea markField = new TextArea("Оценка интервьюера:");
        markField.setWidth("100%");
        markField.setRequired(true);
        markField.setRows(4);
        return markField;
    }
    
    private void refreshTable(List<StudentDataShort> stData) {
            BeanItemContainer<StudentDataShort> bean = new BeanItemContainer(StudentDataShort.class, stData);
            StudentsTable oldTable = table;
            table = new StudentsTable(bean);
            rightPanel.replaceComponent(oldTable, table);
            bottomLayout.setVisible(false);
    }
    
          private class PdfStreamSource implements StreamResource.StreamSource {
            
        @Override
        public InputStream getStream() {
            //return new ByteArrayInputStream(InterviewerPage.getPdfForView(currFormID));
            ApplicationForm form = new ApplicationForm(currFormID);
            return new ByteArrayInputStream(form.generateFormPDF());
        } 
    }
  
    private class SearchListener implements Button.ClickListener{

        @Override
        public void buttonClick(ClickEvent event) {
            if(searchField.isValid()) {
                String filter = (String) searchFilter.getValue();
                String value = (String) searchField.getValue();
                List<StudentDataShort> stData = InterviewerPage.searchStudents(filter, value);
                refreshTable(stData);
            } else {
                getWindow().showNotification("Введите значение для поиска!",Window.Notification.TYPE_TRAY_NOTIFICATION);
            }
        }
    }

    private class SelectTreeItem implements ItemClickListener {

        @Override
        public void itemClick(ItemClickEvent event) {
            Object selectedObject = event.getItemId();
            if (selectedObject instanceof StudentInterview) {
                StudentInterview stInterview = (StudentInterview) selectedObject;
                List<StudentDataShort> stData = InterviewerPage.getStudentsByInterviewID(stInterview.getStudentInterviewId());
                refreshTable(stData);
            } else if(selectedObject.equals("Все студенты")) {
                List<StudentDataShort> stData = InterviewerPage.getAllStudents();
                refreshTable(stData);
            }
        }
             
    }
        
    private class SelectStudentListener implements Property.ValueChangeListener {
       

        @Override
        public void valueChange(ValueChangeEvent event) {
            StudentDataShort student = (StudentDataShort) event.getProperty().getValue();
            if (student != null) {
                bottomLayout.setVisible(true);
                currFormID = student.getIdForm();
                
                TextArea oldMarkField = markField;
                markField = getMarkField();
                markField.setValue(InterviewerPage.getStudentMark(currFormID, username));
                bottomLayout.replaceComponent(oldMarkField, markField);
                
                if(markField.getValue().equals("")) {
                    markField.setReadOnly(false);
                    saveEdit.setCaption("Сохранить");
                } else {
                    markField.setReadOnly(true);
                    saveEdit.setCaption("Редактировать");
                }
                
                Link oldPDFLink = pdfLink;
                pdfLink = getPDFLink();
                bottomLayout.replaceComponent(oldPDFLink, pdfLink);
            } else {
                bottomLayout.setVisible(false);
            }
        }
        
    }
    
    private class SaveOrEditButtonListener implements Button.ClickListener {

        @Override
        public void buttonClick(ClickEvent event) {
            if (saveEdit.getCaption().equals("Сохранить") && markField.isValid()) {
                InterviewerPage.setStudentMark(currFormID,username,markField.getValue().toString());
                markField.setReadOnly(true);
                saveEdit.setCaption("Редактировать");
            } else if (saveEdit.getCaption().equals("Редактировать")) {
                markField.setReadOnly(false);
                saveEdit.setCaption("Сохранить");
            } else {
                getWindow().showNotification("Введите оценку!",Window.Notification.TYPE_TRAY_NOTIFICATION);
            }
        }
        
    }
    
    private class StudentsTable extends Table {
        
        public Object[] NATURAL_COL_ORDER = new Object[] {
                "idForm", "studentLastName", "studentFirstName", "studentMiddleName", "studentInstitute",
                "studentInstituteCourse", "studentFaculty", "studentCathedra" };
        public String[] COL_HEADERS_RUSSIAN = new String[] {
                "Номер анкеты", "Фамилия", "Имя", "Отчество",
                "ВУЗ", "Курс", "Факультет", "Кафедра" };
        
        public StudentsTable(Container dataSource) {
            super();
            setWidth("100%");
            setHeight(height-300,UNITS_PIXELS);
            setSelectable(true);
            setImmediate(true);
            setContainerDataSource(dataSource);
            setColumnReorderingAllowed(true);
            setColumnCollapsingAllowed(true);
            setColumnCollapsed("idForm",true);
            setColumnCollapsed("studentMiddleName",true);
            setColumnCollapsed("studentCathedra",true);
            setVisibleColumns(NATURAL_COL_ORDER);
            setColumnHeaders(COL_HEADERS_RUSSIAN);
            setColumnExpandRatio("studentInstitute", 3);
            setColumnExpandRatio("studentFaculty",2);
            setColumnExpandRatio("studentInstituteCourse",1);
            addListener(new SelectStudentListener());
        }
        
    }

}
