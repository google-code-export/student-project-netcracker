package ua.netcrackerteam.GUI;

import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.StreamResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Runo;
import ua.netcrackerteam.applicationForm.ApplicationForm;
import ua.netcrackerteam.controller.HRPage;
import ua.netcrackerteam.controller.InterviewerPage;
import ua.netcrackerteam.controller.RegistrationToInterview;
import ua.netcrackerteam.controller.StudentPage;
import ua.netcrackerteam.controller.bean.StudentData;
import ua.netcrackerteam.controller.bean.StudentDataShort;
import ua.netcrackerteam.controller.bean.StudentInterview;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Miralissa
 * Date: 22.08.13
 * Time: 23:22
 * To change this template use File | Settings | File Templates.
 */
public class HRBlankLayout extends VerticalLayout {
    private static final List<String> categories = Arrays.asList(new String[]{"Фамилия", "Имя", "Номер анкеты",
            "ВУЗ", "Курс", "Факультет", "Кафедра"});
    private final MainPage mainPage;
    private final String username;
    private Accordion accordion;
    private TextArea markField;
    private Panel rightPanel;
    private StudentsTable table;
    private Button saveEdit;
    private VerticalLayout bottomLayout;
    private int currFormID;
    private NativeSelect searchFilter;
    private Tree tree;
    private TextField searchField;
    private RegistrationToInterview registration = new RegistrationToInterview();
    private String allFormsTreeItem;
    private String notAcceptedFormsTreeItem;
    private FormState state = FormState.VALIDATED;
    private SelectMode selectMode = SelectMode.ONE;

    public HRBlankLayout(final HeaderLayout hlayout, final MainPage mainPage) {
        this.mainPage = mainPage;
        this.username = hlayout.getUsername();
        final Component c = new VerticalLayout();
        final Component c2 = new VerticalLayout();
        fillInterviewsLayout();
    }

    private void fillInterviewsLayout() {
        HorizontalSplitPanel splitH = new HorizontalSplitPanel();
        splitH.setStyleName(Runo.SPLITPANEL_REDUCED);
        splitH.setSplitPosition(200, Sizeable.UNITS_PIXELS);
        addComponent(splitH);

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
        accordion.addTab(getFunctionsMenu(), "Функции");
        sidebar.setContent(accordion);
    }

    private Component getFunctionsMenu() {
        return new VerticalLayout();
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
        rightPanel.addComponent(getBottomLayout());
        bottomLayout.setVisible(false);
    }

    private Component getBottomLayout() {
        bottomLayout = new VerticalLayout();
        bottomLayout.setSpacing(true);
        bottomLayout.setMargin(true);
        bottomLayout.addComponent(getButtonsLayout());
        bottomLayout.addComponent(getMarkLayout());
        return bottomLayout;
    }

    private Component getMarkLayout() {
        VerticalLayout markLayout = new VerticalLayout();

        markField = new TextArea("Оценка HR:");
        markField.setWidth("100%");
        markField.setRequired(true);
        markField.setRows(4);

        saveEdit = new Button();
        saveEdit.addListener(new SaveOrEditButtonListener());
        saveEdit.setWidth("150");
        //markLayout.addComponent(saveEdit);
        //markLayout.setComponentAlignment(saveEdit, Alignment.MIDDLE_CENTER);
        return markLayout;
    }

    private Component getButtonsLayout() {
        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.setSpacing(true);
        buttonsLayout.addComponent(getPDFLink());
        buttonsLayout.addComponent(getDownloadPhotoLink());
        buttonsLayout.addComponent(getEditFormButton());
        buttonsLayout.addComponent(getDeleteFormButton());
        if (state.equals(FormState.NOT_CHECKED)) {
            buttonsLayout.addComponent(getShowFormsDifferenceButton());
            buttonsLayout.addComponent(getAcceptButton());
        }

        return buttonsLayout;
    }

    private Component getDeleteFormButton() {
        Button deleteFormButton = new Button("Удалить анкету(ы)");
        deleteFormButton.setStyleName(Runo.BUTTON_LINK);
        deleteFormButton.setIcon(new ThemeResource("icons/32/document-delete.png"));
        deleteFormButton.addListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    deleteForms();
                }
            });
        return deleteFormButton;
    }

    private void deleteForms() {
        final Window confirm = new Window("Внимание!");
        VerticalLayout vl = (VerticalLayout) confirm.getContent();
        vl.setSpacing(true);
        vl.setMargin(true);
        confirm.setModal(true);
        confirm.setWidth("20%");
        confirm.setResizable(false);
        confirm.center();
        confirm.addComponent(new Label("Вы уверены что хотите удалить анкету(ы)? Отменить это действие будет невозможно."));
        HorizontalLayout layout = new HorizontalLayout();
        Button ok = new Button("ОК");
        ok.setWidth("100");
        ok.addListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                Set<StudentDataShort> forms = (Set<StudentDataShort>) table.getValue();
                for(StudentDataShort form: forms) {
                    HRPage.deleteStudentBlank(form.getIdForm());
                }
                refreshTable(tree.getValue());
                refreshCount();
                getWindow().removeWindow(confirm);
                getWindow().showNotification("Операция выполнена успешно!", Window.Notification.TYPE_TRAY_NOTIFICATION);
            }
        });
        Button cancel = new Button("Отмена");
        cancel.setWidth("100");
        cancel.addListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                getWindow().removeWindow(confirm);
            }
        });
        layout.addComponent(ok);
        layout.addComponent(cancel);
        confirm.addComponent(layout);
        getWindow().addWindow(confirm);
    }

    private Component getEditFormButton() {
        Button editFormButton = new Button("Редактировать анкету");
        editFormButton.setStyleName(Runo.BUTTON_LINK);
        editFormButton.setIcon(new ThemeResource("icons/32/document-edit.png"));
        if(selectMode.equals(SelectMode.MULTI)){
            editFormButton.setVisible(false);
        } else {
            editFormButton.addListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    final Window window = new Window("Window");
                    StudentBlank studentBlank = new StudentBlank(HRPage.getUserNameByFormId(currFormID), mainPage, username);
                    window.setCaption("Просмотр анкеты");
                    window.setContent(studentBlank);
                    window.setHeight("80%");
                    window.setWidth("80%");
                    getWindow().addWindow(window);
                }
            });
        }

        return editFormButton;
    }

    private Component getAcceptButton() {
        Button acceptButton = new Button("Подтвердить анкету(ы)");
        acceptButton.setStyleName(Runo.BUTTON_LINK);
        acceptButton.setIcon(new ThemeResource("icons/32/Checkbox-Full-icon.png"));
        acceptButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                Set<StudentDataShort> forms = (Set<StudentDataShort>) table.getValue();
                for(StudentDataShort form: forms) {
                    currFormID = form.getIdForm();
                    HRPage.verificateForm(currFormID);
                }
                getWindow().showNotification(
                        "Операция выполнена успешно!",
                        Window.Notification.TYPE_TRAY_NOTIFICATION);
                refreshTable(tree.getValue());
                refreshCount();
            }
        });
        return acceptButton;
    }

    private void refreshCount() {
        Long formCount = HRPage.getCountOfAllForms();
        String allFormsTreeItemCaption = "Основные анкеты (" + String.valueOf(formCount) + ")";
        tree.setItemCaption(allFormsTreeItem, allFormsTreeItemCaption);

        Long notAcceptedCount = HRPage.getCountOfNonVerificatedForms();
        String notAcceptedFormsTreeItemCaption = "Не подтвержденные (" + String.valueOf(notAcceptedCount) + ")";
        tree.setItemCaption(notAcceptedFormsTreeItem, notAcceptedFormsTreeItemCaption);
    }

    private Button getShowFormsDifferenceButton() {
        Button showDiffButton = new Button("Различия");
        showDiffButton.setStyleName(Runo.BUTTON_LINK);
        showDiffButton.setIcon(new ThemeResource("icons/32/Difference-tables-icon.png"));
        if(selectMode.equals(SelectMode.MULTI)){
            showDiffButton.setVisible(false);
        } else {
            showDiffButton.addListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    BlankDifferensesWindow diffWindow = new BlankDifferensesWindow(
                            MainPage.getInstance(), currFormID);
                    getWindow().addWindow(diffWindow);
                }
            });
        }
        return showDiffButton;
    }

    private Button getDownloadPhotoLink() {
        Button downloadPhotoButton = new Button("Открыть фото");
        downloadPhotoButton.setStyleName(Runo.BUTTON_LINK);
        downloadPhotoButton.setIcon(new ThemeResource("icons/32/photo.png"));
        if(selectMode.equals(SelectMode.MULTI)){
            downloadPhotoButton.setVisible(false);
        } else {
            downloadPhotoButton.addListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    StreamResource.StreamSource ss = new StreamResource.StreamSource() {
                        StudentData studentData = StudentPage.getStudentDataByIdForm(currFormID);
                        byte[] bytes = studentData.getPhoto();
                        InputStream is = new ByteArrayInputStream(bytes);

                        @Override
                        public InputStream getStream() {
                            return is;
                        }
                    };
                    StreamResource sr = new StreamResource(ss, "userPhoto.jpg", MainPage.getInstance());
                    getWindow().open(sr, "_blank");
                }
            }
            );
        }

        return downloadPhotoButton;
    }

    private Component getTreeMenu() {
        List<StudentInterview> interviews = registration.getInterviews();
        tree = new Tree();
        tree.setImmediate(true);
        tree.setNullSelectionAllowed(false);

        Long formCount = HRPage.getCountOfAllForms();
        String allFormsTreeItemCaption = "Основные анкеты (" + String.valueOf(formCount) + ")";
        allFormsTreeItem = "Основные анкеты";
        tree.addItem(allFormsTreeItem);
        tree.setItemCaption(allFormsTreeItem,allFormsTreeItemCaption);
        tree.setValue(allFormsTreeItem);
        tree.setChildrenAllowed(allFormsTreeItem, false);
        tree.setItemIcon(allFormsTreeItem, new ThemeResource("icons/32/users.png"));

        Long notAcceptedCount = HRPage.getCountOfNonVerificatedForms();
        String notAcceptedFormsTreeItemCaption = "Не подтвержденные (" + String.valueOf(notAcceptedCount) + ")";
        notAcceptedFormsTreeItem = "Не подтвержденные";
        tree.addItem(notAcceptedFormsTreeItem);
        tree.setItemCaption(notAcceptedFormsTreeItem, notAcceptedFormsTreeItemCaption);

        tree.setChildrenAllowed(notAcceptedFormsTreeItem, false);
        tree.setItemIcon(notAcceptedFormsTreeItem, new ThemeResource("icons/32/Checkbox-Full-icon.png"));

        String dates = "Дата собеседования";
        tree.addItem(dates);
        tree.setItemIcon(dates, new ThemeResource("icons/32/calendar.png"));
        tree.setChildrenAllowed(dates, true);
        tree.expandItem(dates);
        for (StudentInterview stInterview : interviews) {
            Format formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String strDate = formatter.format(stInterview.getInterviewStartDate());
            tree.addItem(stInterview);
            tree.setChildrenAllowed(stInterview, false);
            tree.setItemCaption(stInterview, strDate);
            tree.setParent(stInterview, "Дата собеседования");
            tree.setItemIcon(stInterview, new ThemeResource("icons/32/blue_point.png"));
        }
        tree.addListener(new SelectTreeItem());
        return tree;
    }

    private Component getSearchLayout() {
        VerticalLayout searchLayout = new VerticalLayout();
        searchLayout.setSpacing(true);
        searchLayout.setMargin(true);

        searchFilter = new NativeSelect("Критерии поиска:", categories);
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
        Link pdf = new Link();
        pdf.setIcon(new ThemeResource("icons/32/document-pdf.png"));
        pdf.setCaption("Просмотреть/Распечатать анкету");
        if(selectMode.equals(SelectMode.MULTI)) {
            pdf.setVisible(false);
        } else {
            StreamResource resource = new StreamResource(new PdfStreamSource(), "form.pdf", mainPage);
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String filename = "form-" + df.format(new Date()) + ".pdf";
            resource.setFilename(filename);
            resource.setCacheTime(0);
            pdf.setResource(resource);
            pdf.setTargetName("_blank");
            pdf.setTargetWidth(600);
            pdf.setTargetHeight((int) getHeight() - 10);
            pdf.setTargetBorder(Link.TARGET_BORDER_NONE);
            pdf.setDescription("Анкета студента (откроется в новом окне)");
        }
        return pdf;
    }

    private TextArea getMarkField() {
        TextArea markField = new TextArea("Оценка интервьюера:");
        markField.setWidth("100%");
        markField.setRequired(true);
        markField.setRows(4);
        return markField;
    }

    private void refreshTable(Object selectedMenuItem) {
        Object selectedObject = selectedMenuItem;
        List<StudentDataShort> stData = new ArrayList<StudentDataShort>();
        if (selectedObject instanceof StudentInterview) {
            StudentInterview stInterview = (StudentInterview) selectedObject;
            stData = InterviewerPage.getStudentsByInterviewID(stInterview.getStudentInterviewId());
            state = FormState.VALIDATED;
        } else if (selectedObject.equals(allFormsTreeItem)) {
            stData = HRPage.getAllForms();
            state = FormState.VALIDATED;
        } else if (selectedObject.equals(notAcceptedFormsTreeItem)) {
            stData = HRPage.getNonVerificatedForms();
            state = FormState.NOT_CHECKED;
        } else if (selectedObject instanceof List) {
            stData = (List<StudentDataShort>) selectedObject;
            state = FormState.VALIDATED;
        }
        BeanItemContainer<StudentDataShort> bean = new BeanItemContainer(StudentDataShort.class, stData);
        StudentsTable oldTable = table;
        table = new StudentsTable(bean);
        rightPanel.replaceComponent(oldTable, table);
        bottomLayout.setVisible(false);
    }

    private enum FormState {
        VALIDATED, NOT_CHECKED
    }

    private enum SelectMode {
        MULTI, ONE
    }

    private class PdfStreamSource implements StreamResource.StreamSource {

        @Override
        public InputStream getStream() {
            ApplicationForm form = new ApplicationForm(currFormID);
            return new ByteArrayInputStream(form.generateFormPDF());
        }
    }

    private class SearchListener implements Button.ClickListener {

        @Override
        public void buttonClick(Button.ClickEvent event) {
            if (searchField.isValid()) {
                String filter = (String) searchFilter.getValue();
                String value = (String) searchField.getValue();
                List<StudentDataShort> stData = HRPage.searchStudents(filter, value);
                refreshTable(stData);
            } else {
                getWindow().showNotification("Введите значение для поиска!", Window.Notification.TYPE_TRAY_NOTIFICATION);
            }
        }
    }

    private class SelectTreeItem implements ItemClickEvent.ItemClickListener {

        @Override
        public void itemClick(ItemClickEvent event) {
            refreshTable(event.getItemId());
        }

    }

    private class SelectStudentListener implements Property.ValueChangeListener {


        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            Set<StudentDataShort> students = (Set<StudentDataShort>) event.getProperty().getValue();
            if (students.size() != 0) {
                if(students.size() == 1) {
                    currFormID = students.iterator().next().getIdForm();
                    selectMode = SelectMode.ONE;
                }   else {
                    selectMode = SelectMode.MULTI;
                }

                Component oldBottomLayout = bottomLayout;
                rightPanel.replaceComponent(oldBottomLayout, getBottomLayout());

            } else {
                bottomLayout.setVisible(false);
            }
        }

    }

    private class SaveOrEditButtonListener implements Button.ClickListener {

        @Override
        public void buttonClick(Button.ClickEvent event) {
            if (saveEdit.getCaption().equals("Сохранить") && markField.isValid()) {
                InterviewerPage.setStudentMark(currFormID, username, markField.getValue().toString());
                markField.setReadOnly(true);
                saveEdit.setCaption("Редактировать");
            } else if (saveEdit.getCaption().equals("Редактировать")) {
                markField.setReadOnly(false);
                saveEdit.setCaption("Сохранить");
            } else {
                getWindow().showNotification("Введите оценку!", Window.Notification.TYPE_TRAY_NOTIFICATION);
            }
        }

    }

    private class StudentsTable extends Table {

        public Object[] NATURAL_COL_ORDER = new Object[]{
                "idForm", "studentLastName", "studentFirstName", "studentMiddleName", "studentInstitute",
                "studentInstituteCourse", "studentFaculty", "studentCathedra"};
        public String[] COL_HEADERS_RUSSIAN = new String[]{
                "Номер анкеты", "Фамилия", "Имя", "Отчество",
                "ВУЗ", "Курс", "Факультет", "Кафедра"};

        public StudentsTable(Container dataSource) {
            super();
            setWidth("100%");
            setHeight(300, UNITS_PIXELS);
            setSelectable(true);
            setImmediate(true);
            setMultiSelect(true);
            setContainerDataSource(dataSource);
            setColumnReorderingAllowed(true);
            setColumnCollapsingAllowed(true);
            setColumnCollapsed("idForm", true);
            setColumnCollapsed("studentMiddleName", true);
            setColumnCollapsed("studentCathedra", true);
            setVisibleColumns(NATURAL_COL_ORDER);
            setColumnHeaders(COL_HEADERS_RUSSIAN);
            setColumnExpandRatio("studentInstitute", 3);
            setColumnExpandRatio("studentFaculty", 2);
            setColumnExpandRatio("studentInstituteCourse", 1);
            addListener(new SelectStudentListener());
        }

    }

}
