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
public class ReportBuilderAdvertisingEfficiency extends ReportBuilder{

    @Override
    public Label buildTitle() {
        return report.getTitle("Эффективность видов рекламы");
    }

    @Override
    public Table buildTable() {
         return report.getTable(new String[]{"Категория рекламы", "Выбрано (кол-во анкет)", " Выбрано (%)"});
    }

    @Override
    public Embedded buildChart(MainPage mainPage) {
        return report.getChart(mainPage);
    }

    
}