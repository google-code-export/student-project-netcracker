/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
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
 * @author akush_000
 */
class InterviewLayout extends VerticalLayout implements Property.ValueChangeListener {
    
    private static DateFormatSymbols myDateFormatSymbols;
    private final InlineDateField calendar;
    private final OptionGroup dates;
    private final Button saveEdit;
    private Date selectedDate;

    public InterviewLayout(String username) {
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
        List interviews = new ArrayList();
        interviews.add(new Date(2013,10,24,18,0));
        interviews.add(new Date(2013,10,24,20,0));
        interviews.add(20);
        interviews.add(new Date(2013,10,26,16,0));
        interviews.add(new Date(2013,10,26,19,0));
        interviews.add(15);
        interviews.add(new Date(2013,10,27,16,0));
        interviews.add(new Date(2013,10,27,19,0));
        interviews.add(0);
        dates = new OptionGroup("Доступные даты:");
        dates.setRequired(true);
        layout.addComponent(dates,1,0);
        myDateFormatSymbols = new DateFormatSymbols(){
            @Override
            public String[] getMonths() {
                return new String[]{"января", "февраля", "марта", "апреля", "мая", "июня",
                    "июля", "августа", "сентября", "октября", "ноября", "декабря"};
            }
        };
        for (int i=0; i<interviews.size(); i=i+3) {
            Format formatter = new SimpleDateFormat("dd MMMM HH:mm",myDateFormatSymbols);
            String sdate = formatter.format((Date) interviews.get(i));
            formatter = new SimpleDateFormat("-HH:mm");
            sdate = sdate + formatter.format((Date) interviews.get(i+1));
            sdate = sdate + ". Осталось мест: " + interviews.get(i+2);
            dates.addItem(sdate);
            if (Integer.parseInt(interviews.get(i+2).toString()) == 0) {
                dates.setItemEnabled(sdate, false);
            }
        }
        dates.addListener(this);
        dates.setImmediate(true);
        saveEdit = new Button("Сохранить");
        layout.addComponent(saveEdit,0,1);
        layout.setComponentAlignment(saveEdit, Alignment.TOP_CENTER);
        saveEdit.addListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                if(dates.isValid() && saveEdit.getCaption().equals("Сохранить")) {
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
        String sdate = event.getProperty().getValue().toString();
        sdate = sdate.substring(0,sdate.indexOf("-"));
        String year = new SimpleDateFormat("yyyy").format(new Date());
        sdate = sdate + year;
        try {
            Date date = new SimpleDateFormat("dd MMMM HH:mmyyyy", myDateFormatSymbols).parse(sdate);
            selectedDate = date;
            calendar.setValue(selectedDate);
        } catch (ParseException ex) {
            Logger.getLogger(InterviewLayout.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
