/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Runo;
import java.util.List;
import ua.netcrackerteam.controller.StudentInterview;

/**
 *
 * @author Anna Kushnirenko
 */
public class HRInterviewsLayout extends VerticalLayout{
    private final Panel rightPanel;
    private final Panel sidebar;
    private final int screenHeight;
    private InterviewsTable table;

    public HRInterviewsLayout(int height) {
        this.screenHeight = height;
        setHeight(screenHeight,UNITS_PIXELS);
        setWidth("100%");
        setMargin(true, false, false, false);
        
        HorizontalSplitPanel splitH = new HorizontalSplitPanel();
        splitH.setStyleName(Runo.SPLITPANEL_REDUCED);
        splitH.setSplitPosition(230, Sizeable.UNITS_PIXELS);
        addComponent(splitH);
        
        sidebar = new Panel("Меню");
        fillSideBar();
        splitH.setFirstComponent(sidebar);
        
        rightPanel = new Panel("Список собеседований");
        fillRightPanel();
        splitH.setSecondComponent(rightPanel);
    }

    private void fillSideBar() {
        VerticalLayout vl = (VerticalLayout) sidebar.getContent();
        vl.setSpacing(true);
        
        sidebar.setHeight("100%");
        Button addInterview = new Button("Создать собеседование");
        addInterview.setStyleName(Runo.BUTTON_LINK);
        addInterview.setIcon(new ThemeResource("icons/32/document-add.png"));
        sidebar.addComponent(addInterview);
        
        Button addTime = new Button("Ввод запасного времени");
        addTime.setStyleName(Runo.BUTTON_LINK);
        addTime.setIcon(new ThemeResource("icons/32/time-icon.png"));
        sidebar.addComponent(addTime);
    }

    private void fillRightPanel() {
        rightPanel.setHeight("100%");
        VerticalLayout vl = (VerticalLayout) rightPanel.getContent();
        vl.setMargin(false);
        vl.setSpacing(true);
        List<StudentInterview> interviews = ua.netcrackerteam.controller.RegistrationToInterview.getInterviews();
        BeanItemContainer<StudentInterview> bean = new BeanItemContainer(StudentInterview.class, interviews);
        table = new InterviewsTable(bean);
        rightPanel.addComponent(table);
        
        VerticalLayout bottomLayout = new VerticalLayout();
        bottomLayout.setSpacing(true);
        bottomLayout.setMargin(true);
        bottomLayout.setVisible(false);
        rightPanel.addComponent(bottomLayout);
    }

    private class InterviewsTable extends Table {
        
        public String[] NATURAL_COL_ORDER = new String[] {
                "date", "time", "intervNum", "duration",
                "posNum", "restPosNum"};
        
        public String[] COL_HEADERS_RUSSIAN = new String[] {
                "Дата собеседования", "Время собеседования", "Количество интервьюеров", "Продолжительность",
                "Количество мест", "Остаток мест"};
        
        private InterviewsTable(Container dataSource) {
            super();
            setWidth("100%");
            setHeight(screenHeight-300,UNITS_PIXELS);
            setSelectable(true);
            setImmediate(true);
            setContainerDataSource(dataSource);
            setColumnReorderingAllowed(true);
            setColumnCollapsingAllowed(true);
//            setVisibleColumns(NATURAL_COL_ORDER);
//            setColumnHeaders(COL_HEADERS_RUSSIAN);
            
        }
    }
    
}
