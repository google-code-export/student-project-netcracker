/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI.Reports;

import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Table;
import ua.netcrackerteam.GUI.MainPage;

/**
 *
 * @author home
 */
public class ReportBuilderDynamicsOfIncreaseStudents extends ReportBuilder{

    @Override
    public Label buildTitle() {
        return report.getTitle("Статистика увеличения записанных студентов на собеседования");
    }


    @Override
    public Table buildTable() {
        return report.getTable(new String[]{"Дата собеседования", "Всего", "Зарегистрировано", "Свободно"});
    }

    @Override
    public Embedded buildChart(MainPage mainPage) {
        return report.getChart(mainPage);
    }
    
}
