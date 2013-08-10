/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.terminal.gwt.server.WebBrowser;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import ua.netcrackerteam.DAO.Entities.Interview;
import ua.netcrackerteam.applicationForm.CreateLetterWithPDF;
import ua.netcrackerteam.controller.exceptions.FullInterviewException;
import ua.netcrackerteam.controller.exceptions.NoFormException;
import ua.netcrackerteam.controller.StudentInterview;
import ua.netcrackerteam.controller.RegistrationToInterview;

import java.text.DateFormatSymbols;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Anna Kushnirenko
 */
class InterviewLayout extends VerticalLayout implements Property.ValueChangeListener {
    
    private static DateFormatSymbols myDateFormatSymbols = new DateFormatSymbols(){
            @Override
            public String[] getMonths() {
                return new String[]{"января", "февраля", "марта", "апреля", "мая", "июня",
                    "июля", "августа", "сентября", "октября", "ноября", "декабря"};
            }
        };
    private final InlineDateField calendar;
    private OptionGroup dates;
    private Button saveEdit;
    private Button print;
    private String userName;
    private int selectedInterviewID;
    private boolean noPositionsFlag = true;
    private RegistrationToInterview registration = new RegistrationToInterview();
    private GridLayout layout;

    public InterviewLayout(String username, MainPage mainPage) {
        this.userName = username;
        WebApplicationContext context = (WebApplicationContext) mainPage.getContext();
        WebBrowser webBrowser = context.getBrowser();
        setHeight(webBrowser.getScreenHeight()-200,UNITS_PIXELS);
        setMargin(true);
        setSpacing(true);
        Panel panel = new Panel("Выберите дату собеседования");
        panel.setWidth("100%");
        addComponent(panel);
        layout = new GridLayout(2,2);
        layout.setSizeFull();
        panel.setContent(layout);
        layout.setSpacing(true);
        layout.setMargin(true);
        calendar = new InlineDateField();
        calendar.setImmediate(true);
        calendar.setValue(new Date());
        calendar.setResolution(InlineDateField.RESOLUTION_DAY);
        layout.addComponent(calendar, 0, 0);
        layout.setComponentAlignment(calendar, Alignment.TOP_CENTER);
        List<StudentInterview> interviews = registration.getInterviews();
        Interview selectedInterview = registration.getInterview(userName);
        StudentInterview nullInterview = registration.getNullInterview();
        dates = new OptionGroup("Доступные даты:");
        layout.addComponent(dates,1,0);
        fillDates(interviews, nullInterview, selectedInterview);

        print = new Button("Отправить PDF");
        print.setWidth("150");
        layout.addComponent(print,1,1);
        layout.setComponentAlignment(print, Alignment.TOP_CENTER);
        print.addListener(new ButtonsListener());
        if(selectedInterviewID != 0) {
            saveEdit = new Button("Редактировать");
            dates.setReadOnly(true);
        } else {
            saveEdit = new Button("Сохранить");
            print.setVisible(false);
        }
        layout.addComponent(saveEdit,0,1);
        saveEdit.setWidth("150");
        layout.setComponentAlignment(saveEdit, Alignment.TOP_CENTER);
        saveEdit.addListener(new ButtonsListener());        
    }

    private void refreshInterviews() {
        List<StudentInterview> interviews = registration.getInterviews();
        Interview selectedInterview = registration.getInterview(userName);
        StudentInterview nullInterview = registration.getNullInterview();
        fillDates(interviews, nullInterview, selectedInterview);
    }

    private void fillDates(List<StudentInterview> interviews, StudentInterview nullInterview, Interview selectedInterview) {
        OptionGroup newDates = new OptionGroup("Доступные даты:");
        layout.replaceComponent(dates,newDates);
        dates = newDates;
        dates.setRequired(true);
        if (selectedInterview != null) {
            selectedInterviewID = selectedInterview.getIdInterview();
        } else {
            selectedInterviewID = 0;
        }
        for(StudentInterview stInterview : interviews) {
            String strDate = getStrFromDate(stInterview.getInterviewStartDate(),
                    stInterview.getInterviewEndDate(), stInterview.getRestOfPositions());
            dates.addItem(stInterview);
            dates.setItemCaption(stInterview, strDate);
            if (stInterview.getRestOfPositions() == 0) {
                dates.setItemEnabled(stInterview, false);
            } else {
                noPositionsFlag = false;
            }
            if (selectedInterviewID == stInterview.getStudentInterviewId()) {
                dates.setValue(stInterview);
                calendar.setValue(stInterview.getInterviewStartDate());
            }
        }
        if (noPositionsFlag) {
            int restPos = nullInterview.getRestOfPositions();
            dates.addItem(nullInterview);
            dates.setItemCaption(nullInterview, "Дополнительное время. Осталось мест: "+restPos);
            if((selectedInterview!=null)&&(selectedInterview.getReserve() == 1)) {
                dates.setValue(nullInterview);
            }
        }
        dates.addListener(this);
        dates.setImmediate(true);
    }


    @Override
    public void valueChange(ValueChangeEvent event) {
        StudentInterview itemID = (StudentInterview) event.getProperty().getValue();
        selectedInterviewID = itemID.getStudentInterviewId();
        calendar.setValue(itemID.getInterviewStartDate());
    }

    private String getStrFromDate(Date startDate, Date endDate, int availability) {
        Format formatter = new SimpleDateFormat("dd MMMM HH:mm",myDateFormatSymbols);
        String strDate = formatter.format(startDate);
        formatter = new SimpleDateFormat("-HH:mm");
        strDate = strDate + formatter.format(endDate);
        strDate = strDate + ". Осталось мест: " + availability;
        return strDate;
    }
    
    private class ButtonsListener implements Button.ClickListener {

        @Override
        public void buttonClick(ClickEvent event) {
            Button source = event.getButton();
            if(source == saveEdit) {
                if(dates.isValid() && saveEdit.getCaption().equals("Сохранить")) {
                    try {
                        registration.updateRegistrationToInterview(userName, selectedInterviewID);
                        refreshInterviews();
                        dates.setReadOnly(true);
                        print.setVisible(true);
                        saveEdit.setCaption("Редактировать");
                    } catch (FullInterviewException e) {
                        refreshInterviews();
                        getWindow().showNotification(e.getMessage(), Window.Notification.TYPE_TRAY_NOTIFICATION);
                    } catch (NoFormException e) {
                        getWindow().showNotification(e.getMessage(), Window.Notification.TYPE_TRAY_NOTIFICATION);
                    }
                } else {
                    print.setVisible(false);
                    dates.setReadOnly(false);
                    saveEdit.setCaption("Сохранить");
                }
            } else {
                CreateLetterWithPDF letter =new CreateLetterWithPDF(userName);
                letter.sendPDFToStudent();
            }
        }
    }
    
    
}
