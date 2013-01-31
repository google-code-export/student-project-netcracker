/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import java.util.List;


/**
 *
 * @author akush_000
 */
public class RegistrationForm extends Form implements Button.ClickListener{
    private List<Panel> panelList = null;
    private Button save = null;

    public RegistrationForm() {
        setWidth("98%");
        HorizontalLayout footer = new HorizontalLayout();
        footer.setSpacing(true);
        save = new Button("Регистрация",this,"commit");
        footer.addComponent(save);
        footer.setWidth("100%");
        footer.setComponentAlignment(save, Alignment.TOP_CENTER);
        setFooter(footer);
//        panelList = new ArrayList<Panel>();
//        panelList.add(new Panel("Персональная информация"));
//        panelList.add(new Panel("Контакты"));
//        panelList.add(new Panel("Интересы"));
//        panelList.add(new Panel("Достоинства"));
//        panelList.add(new Panel("Соглашение"));
//        for(Panel p: panelList) {
//            p.setStyleName(Runo.PANEL_LIGHT);
//            getLayout().addComponent(p);
//        }
        BlankFormBean bean = new BlankFormBean();
        BeanItem item = new BeanItem(bean);
        setItemDataSource(item);
    }

    public void buttonClick(ClickEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
