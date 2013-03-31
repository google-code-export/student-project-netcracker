/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI.Reports.TypeReports;

import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import ua.netcrackerteam.GUI.MainPage;
import ua.netcrackerteam.GUI.Reports.ReportBuilder;

/**
 *
 * @author Klitna Tetiana
 */
public class ReportBuilderDynamicsOfIncreaseStudents extends ReportBuilder{
    
    private MainPage mainPage;
    
    public ReportBuilderDynamicsOfIncreaseStudents(MainPage mainPage){
       this.mainPage = mainPage; 
    }
  
    @Override
    public Label buildTitle() {
        return report.getTitle("Статистика увеличения записанных студентов на собеседования");
    }


    @Override
    public Table buildTable() {
        return report.getTable(new String[]{"Дата собеседования", "Всего", "Зарегистрировано", "Свободно"});
    }

    @Override
    public Embedded buildChart() {
        return report.getChart(mainPage);
    }
    
}
