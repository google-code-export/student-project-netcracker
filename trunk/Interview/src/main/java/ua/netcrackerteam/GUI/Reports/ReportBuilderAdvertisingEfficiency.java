/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI.Reports;

import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import ua.netcrackerteam.GUI.MainPage;

/**
 *
 * @author home
 */
public class ReportBuilderAdvertisingEfficiency extends ReportBuilder{

    @Override
    public Label buildTitle() {
        return report.getTitle("Эффективность видов рекламы");
    }

    @Override
    public Table buildTable() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Embedded buildChart(MainPage mainPage) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
