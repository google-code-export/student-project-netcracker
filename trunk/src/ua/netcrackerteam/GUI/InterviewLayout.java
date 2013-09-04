/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.ui.*;

import ua.netcrackerteam.applicationForm.CreateLetterWithPDF;
import ua.netcrackerteam.applicationForm.Letter;
import ua.netcrackerteam.applicationForm.LetterPDF;
import ua.netcrackerteam.controller.RegistrationToInterview;
import ua.netcrackerteam.controller.bean.StudentInterview;
import ua.netcrackerteam.controller.exceptions.StudentInterviewException;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Anna Kushnirenko
 */
@SuppressWarnings("serial")
class InterviewLayout extends VerticalLayout {


    private final String username;
    private final MainPage mainPage;
    StudentInterview selectedInterview;
    StudentInterview nullInterview;
    private VerticalLayout verticalLayout;
    private RegistrationToInterview controller = new RegistrationToInterview();
    private List<StudentInterview> interviewsList;
    private Button save;
    private DaySelector daySelector;

    public InterviewLayout(String username, MainPage mainPage) {
        this.mainPage = mainPage;
        this.username = username;
        loadPageSettings();
        loadRegistrationPanelSettings();
        interviewsList = controller.getInterviews();
        selectedInterview = controller.getSelectedInterview(username);
        nullInterview = controller.getNullInterview();
        if (selectedInterview != null) {
            showEditComponents();
        }
        daySelector = new DaySelector();
        addSaveButton();
    }

    private void addSaveButton() {
        save = new Button("Сохранить");
        verticalLayout.addComponent(save);
        verticalLayout.setComponentAlignment(save, Alignment.BOTTOM_CENTER);
        save.setVisible(false);
        save.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if (daySelector.getOldTimeSelector().getValue() != null || (selectedInterview != null && selectedInterview.equals(nullInterview))) {
                    saveInterview();
                } else {
                    getWindow().showNotification("Выберите время собеседования!", Window.Notification.TYPE_TRAY_NOTIFICATION);
                }
            }
        });
    }

    private void saveInterview() {
        try {
            controller.updateRegistrationToInterview(username, selectedInterview.getStudentInterviewId());
        } catch (StudentInterviewException e) {
            getWindow().showNotification(e.getMessage(), Window.Notification.TYPE_TRAY_NOTIFICATION);
        }
        refreshPage();
    }

    private void refreshPage() {
        TabSheet studentTabs = mainPage.getPanel().getTabSheet();
        InterviewLayout interviewLayout = new InterviewLayout(username, mainPage);
        studentTabs.replaceComponent(this, interviewLayout);
    }

    private void showEditComponents() {
        if (selectedInterview.equals(nullInterview)) {
            showLabelsForNullInterview();
        } else {
            showLabels();
        }

        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.setSpacing(true);
        verticalLayout.addComponent(buttonsLayout);

        Button edit = new Button("Редактировать");
        buttonsLayout.addComponent(edit);
        edit.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if (validTime() || selectedInterview.equals(nullInterview)) {
                    daySelector.setVisible(true);
                } else {
                    getWindow().showNotification("Вы не можете перерегистрироваться за пол часа до начала выбранного собеседования.",
                            Window.Notification.TYPE_TRAY_NOTIFICATION);
                }
            }
        });
        Button sendPDF = new Button("Отправить PDF");
        buttonsLayout.addComponent(sendPDF);
        sendPDF.addListener(new

                                    Button.ClickListener() {
                                        @Override
                                        public void buttonClick(Button.ClickEvent clickEvent) {
                                            Letter letter = new LetterPDF(username);
                                            CreateLetterWithPDF sender = new CreateLetterWithPDF(username, letter);
                                            sender.sendPDFToStudent();
                                        }
                                    });
    }

    private void showLabelsForNullInterview() {
        String time = "резервное время. В случае добавления дополнительных собеседований Вы будете оповещены по email";
        Label caption = new Label("Вы зарегистрированы на " + time + ".");
        verticalLayout.addComponent(caption);
    }

    private void showLabels() {
        String time = "собеседование на " + selectedInterview.getStartTime() + " " +
                selectedInterview.getInterviewStartDay();
        Label caption = new Label("Вы зарегистрированы на " + time + ".");
        verticalLayout.addComponent(caption);
        caption = new Label("Внимание! Вы не сможете перерегистрироваться за пол часа до начала выбранного собеседования.");
        verticalLayout.addComponent(caption);
    }

    private boolean validTime() {
        Date currentDate = new Date();
        Date selectedInterviewDate = selectedInterview.getInterviewStartDate();
        long differenceInMinutes = ((selectedInterviewDate.getTime() / 60000) - (currentDate.getTime() / 60000));
        if (differenceInMinutes < 30) {
            return false;
        }
        return true;
    }

    private void loadRegistrationPanelSettings() {
        Panel panel = new Panel("Запись на собеседование");
        panel.setWidth("100%");
        addComponent(new Embedded(null, new ThemeResource("images/interview-logo2.png")));
        String s = null;
        try {
            WebApplicationContext context = (WebApplicationContext) mainPage.getContext();
            File file = new File (context.getHttpSession().getServletContext().getRealPath("/WEB-INF/resources/interview-text.txt") );
            DataInputStream in = new DataInputStream(new FileInputStream(file));
            byte[] array = new byte[in.available()];
            in.read(array);
            s = new String(array);
            in.close();
        } catch (IOException ex) {
            System.out.println("File interview-text.txt is not found");
        }
        Label label = new Label(s);
        label.setContentMode(Label.CONTENT_XHTML);
        label.setStyleName("form-info");
        addComponent(label);
        addComponent(panel);
        verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();
        panel.setContent(verticalLayout);
        verticalLayout.setSpacing(true);
        verticalLayout.setMargin(true);
    }

    private void loadPageSettings() {
        setMargin(true);
        setSpacing(true);
    }

    private class DaySelector extends ComboBox {

        private TimeSelector oldTimeSelector = new TimeSelector(this);

        public DaySelector() {
            super();
            setWidth(400, UNITS_PIXELS);
            setImmediate(true);
            if (selectedInterview != null) {
                setVisible(false);
            }
            setNullSelectionAllowed(false);
            final List<StudentInterview> interviewDays = parseDays();
            if (interviewDays.size() == 0) {
                interviewDays.add(nullInterview);
            }
            BeanItemContainer<StudentInterview> daysContainer = new BeanItemContainer(StudentInterview.class, interviewDays);
            setCaption("Выберите день собеседования:");
            setContainerDataSource(daysContainer);
            verticalLayout.addComponent(this);
            verticalLayout.addComponent(oldTimeSelector);
            addListener(new ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                    if (!interviewDays.contains(nullInterview)) {
                        refreshTimeSelector();
                    } else {
                        selectedInterview = nullInterview;
                        save.setVisible(true);
                    }
                }
            });
        }

        private List<StudentInterview> parseDays() {
            List<StudentInterview> days = new ArrayList<StudentInterview>();
            String prevDay = null;
            for (StudentInterview studentInterview : interviewsList) {
                String currentDay = studentInterview.getInterviewStartDay();
                if (!currentDay.equals(prevDay) && studentInterview.getRestOfPositions() > 0) {
                    days.add(studentInterview);
                    prevDay = currentDay;
                }
            }
            return days;
        }

        private void refreshTimeSelector() {
            TimeSelector newTimeSelector = new TimeSelector(this);
            verticalLayout.replaceComponent(oldTimeSelector, newTimeSelector);
            oldTimeSelector = newTimeSelector;
            save.setVisible(true);
        }

        public TimeSelector getOldTimeSelector() {
            return oldTimeSelector;
        }
    }

    private class TimeSelector extends Table {
        public Object[] NATURAL_COL_ORDER = new Object[]{"startTime"};//, "endTime"};
        public String[] COL_HEADERS_RUSSIAN = new String[]{"Ориентировочное время"};//, "Время окончания"};
        private DaySelector daySelector;

        public TimeSelector(DaySelector daySelector) {
            super();
            this.daySelector = daySelector;
            setVisible(true);
            List<StudentInterview> availableTimes = filterAvailableTimes();
            BeanItemContainer<StudentInterview> interviewContainer = new BeanItemContainer<StudentInterview>(StudentInterview.class, availableTimes);
            setWidth(400,UNITS_PIXELS);
            setHeight("50%");
            setSelectable(true);
            setImmediate(true);
            setNullSelectionAllowed(false);
            setContainerDataSource(interviewContainer);
            setVisibleColumns(NATURAL_COL_ORDER);
            setColumnHeaders(COL_HEADERS_RUSSIAN);
            
            addListener(new ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                    selectedInterview = (StudentInterview) valueChangeEvent.getProperty().getValue();
                }
            });
        }

        private List<StudentInterview> filterAvailableTimes() {
            List<StudentInterview> availableTimes = new ArrayList<StudentInterview>();
            StudentInterview selectedDate = (StudentInterview) daySelector.getValue();
            if (selectedDate != null) {
                for (StudentInterview studentInterview : interviewsList) {
                    if (selectedDate.getInterviewStartDay().equals(studentInterview.getInterviewStartDay())
                            && studentInterview.getRestOfPositions() > 0) {
                        availableTimes.add(studentInterview);
                    }
                }
            } else {
                setVisible(false);
            }

            return availableTimes;
        }

    }
}
