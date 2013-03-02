/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.terminal.gwt.server.WebBrowser;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import java.text.DateFormatSymbols;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        };;
    private final InlineDateField calendar;
    private final OptionGroup dates;
    private final Button saveEdit;
    private Date selectedDate;
    private String userName;

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
        GridLayout layout = new GridLayout(2,2);
        layout.setSizeFull();
        panel.setContent(layout);
        layout.setSpacing(true);
        layout.setMargin(true);
        calendar = new InlineDateField();
        calendar.setImmediate(true);
        calendar.setValue(new Date());
        calendar.setResolution(InlineDateField.RESOLUTION_DAY);
        layout.addComponent(calendar,0,0);
        layout.setComponentAlignment(calendar, Alignment.TOP_CENTER);
        List interviews = ua.netcrackerteam.controller.RegistrationToInterview.getInterviews();
//        List interviews = new ArrayList();
//        interviews.add(new Date(2013,10,24,18,0));
//        interviews.add(new Date(2013,10,24,20,0));
//        interviews.add(20);
//        interviews.add(new Date(2013,10,26,16,0));
//        interviews.add(new Date(2013,10,26,19,0));
//        interviews.add(15);
//        interviews.add(new Date(2013,10,27,16,0));
//        interviews.add(new Date(2013,10,27,19,0));
//        interviews.add(0);
        dates = new OptionGroup("Доступные даты:");
        dates.setRequired(true);
        layout.addComponent(dates,1,0);
        for (int i=0; i<interviews.size(); i=i+3) {
            Date startDate = (Date) interviews.get(i);
            Date endDate = (Date) interviews.get(i+1);
            String availability = interviews.get(i+2).toString();
            String strDate = getStrFromDate(startDate, endDate, availability);
            dates.addItem(strDate);
            if (Integer.parseInt(availability) == 0) {
                dates.setItemEnabled(strDate, false);
            }
        }
        dates.addListener(this);
        dates.setImmediate(true);
        List selectedInterview = ua.netcrackerteam.controller.RegistrationToInterview.getInterview(userName);
        if(selectedInterview != null) {
            saveEdit = new Button("Редактировать");
            dates.setReadOnly(true);
            dates.setValue(getStrFromDate((Date) selectedInterview.get(0),
                                        (Date)selectedInterview.get(1),
                                        selectedInterview.get(2).toString()));
        } else {
            saveEdit = new Button("Сохранить");
        }
        layout.addComponent(saveEdit,0,1);
        layout.setComponentAlignment(saveEdit, Alignment.TOP_CENTER);
        saveEdit.addListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                if(dates.isValid() && saveEdit.getCaption().equals("Сохранить")) {
                    ua.netcrackerteam.controller.RegistrationToInterview.updateRegistrationToInterview(userName, selectedDate);
                    dates.setReadOnly(true);
                    saveEdit.setCaption("Редактировать");
                } else {
                    dates.setReadOnly(false);
                    saveEdit.setCaption("Сохранить");
                }
            }
        });
    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        String strDate = event.getProperty().getValue().toString();
        Date date = getDateFromStr(strDate);
        selectedDate = date;
        calendar.setValue(selectedDate);
    }

    private Date getDateFromStr(String sdate) {
        sdate = sdate.substring(0,sdate.indexOf("-"));
        String year = new SimpleDateFormat("yyyy").format(new Date());
        sdate = sdate + year;
        Date date = null;
        try {
             date = new SimpleDateFormat("dd MMMM HH:mmyyyy", myDateFormatSymbols).parse(sdate);
        } catch (ParseException ex) {
            Logger.getLogger(InterviewLayout.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }

    private String getStrFromDate(Date startDate, Date endDate, String availability) {
        Format formatter = new SimpleDateFormat("dd MMMM HH:mm",myDateFormatSymbols);
        String strDate = formatter.format(startDate);
        formatter = new SimpleDateFormat("-HH:mm");
        strDate = strDate + formatter.format(endDate);
        strDate = strDate + ". Осталось мест: " + availability;
        return strDate;
    }
    
}
