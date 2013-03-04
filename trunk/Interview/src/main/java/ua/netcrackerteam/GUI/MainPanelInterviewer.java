/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.terminal.gwt.server.WebBrowser;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.themes.Runo;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;
import ua.netcrackerteam.controller.StudentInterview;

/**
 * Panel for Interviewer view
 * @author Anna Kushnirenko
 */
public class MainPanelInterviewer extends MainPanel{
    private VerticalLayout interviewsLo;
    private Accordion accordion;
    private int height;
    
    public MainPanelInterviewer(HeaderLayout hlayout,MainPage mainPage) {
        super(hlayout,mainPage);
        setContent(getUserLayout(hlayout));
        interviewsLo = new VerticalLayout();
        WebApplicationContext context = (WebApplicationContext) mainPage.getContext();
        WebBrowser webBrowser = context.getBrowser();
        height = webBrowser.getScreenHeight()-250;
        interviewsLo.setHeight(height,UNITS_PIXELS);
        interviewsLo.setWidth("100%");
        interviewsLo.setMargin(true, false, false, false);
        fillInterviewsLayout();
        tabSheet.addTab(interviewsLo,"Собеседования");
    }

    private void fillInterviewsLayout() {
        HorizontalSplitPanel splitH = new HorizontalSplitPanel();
        splitH.setStyleName(Runo.SPLITPANEL_REDUCED);
        splitH.setSplitPosition(200, Sizeable.UNITS_PIXELS);
        interviewsLo.addComponent(splitH);
        
        Panel sidebar = new Panel("Навигация");
        splitH.setFirstComponent(sidebar);
        VerticalLayout layout = (VerticalLayout) sidebar.getContent();
        layout.setMargin(false);
        sidebar.setHeight("100%");
        accordion = new Accordion();
        accordion.setSizeFull();
        
        VerticalLayout treeLayout = new VerticalLayout();
        List<StudentInterview> interviews = ua.netcrackerteam.controller.RegistrationToInterview.getInterviews();
        Tree tree = new Tree();
        String caption = "Дата собеседования";
        tree.addItem(caption);
        tree.setItemIcon(caption,new ThemeResource("icons/32/calendar.png"));
        tree.setChildrenAllowed(caption, true);
        tree.expandItem(caption);
        for(StudentInterview stInterview : interviews) {
            Format formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");      
            String strDate = formatter.format(stInterview.getInterviewStartDate());
            tree.addItem(strDate);
            tree.setParent(strDate, "Дата собеседования");
        }
        treeLayout.addComponent(tree);
                
        VerticalLayout searchLayout = new VerticalLayout();
        searchLayout.setSpacing(true);
        searchLayout.setMargin(true);
        TextField searchField = new TextField();
        searchField.setWidth("100%");
        Button searchButton = new Button("Найти");
        searchLayout.addComponent(searchField);
        searchLayout.addComponent(searchButton);
        searchLayout.setComponentAlignment(searchButton, Alignment.TOP_CENTER);
        
        accordion.addTab(treeLayout, "Списки");
        accordion.addTab(searchLayout, "Быстрый поиск");
        sidebar.setContent(accordion);
        
        VerticalSplitPanel splitV = new VerticalSplitPanel();
        splitV.setStyleName(Runo.SPLITPANEL_SMALL);
        splitV.setSplitPosition(height - 300, Sizeable.UNITS_PIXELS);
        splitV.setLocked(true);
        
        GridLayout grid = new GridLayout(2, 1);
        grid.setWidth("100%");
        grid.setHeight(height - 300,UNITS_PIXELS);
        grid.setMargin(true);
        grid.addStyleName(Runo.LAYOUT_DARKER);
        splitV.setFirstComponent(grid);
        
        CssLayout bottom = new CssLayout();
        bottom.setSizeFull();
        splitV.setSecondComponent(bottom);
        
        Panel top = new Panel("Абитуриенты");
        VerticalLayout topLo = (VerticalLayout) top.getContent();
        top.setSizeFull();
        topLo.setMargin(false);
        top.setContent(splitV);
        splitH.setSecondComponent(top);

        
        
        
        
        
    }
    
}
