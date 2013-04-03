/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI.Reports.TypeReports;

import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import ua.netcrackerteam.GUI.MainPage;
import ua.netcrackerteam.GUI.Reports.ReportBuilder;

/**
 *
 * @author Klitna Tetiana
 */
public class ReportBuilderAmountStudentsCourse extends ReportBuilder {
    
    private MainPage mainPage;
    
    public ReportBuilderAmountStudentsCourse(MainPage mainPage){
        this.mainPage = mainPage;
    }
    
    @Override
    public Label buildTitle() {
        return report.getTitle("Количество абитуриентов по курсам");
    }

    @Override
    public Table buildTable() {
        return null;
    }

    @Override
    public Embedded buildChart() {
        return new Embedded();
    }

    @Override
    public GridLayout buildGrid() {
        return report.getGridView(new String[]{"№", "Фамилия", "Имя", "Email", "Телефон"});
    }
    
}
