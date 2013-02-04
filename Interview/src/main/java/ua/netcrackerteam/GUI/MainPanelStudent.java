/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.data.Container;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.IntegerValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Slider;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;



/**
 * Panel for Student view
 * @author Anna Kushnirenko
 */
public class MainPanelStudent extends MainPanel implements Button.ClickListener  {
    private VerticalLayout blankLayout;
    private VerticalLayout interviewLayout;
    private VerticalLayout settingsLo;
    private Button save;
    private Panel contacts;
    private Button anotherContactsBut;
    private ComboBox universities;
    private ComboBox faculties;
    private ArrayList<TextField> contactList;
    private TextField contactType;
    private TextField contactValue;
    private Window addContact;
    private Panel interests;
    private Panel accomplishments;
    private Panel persInfo;
    private Button addPrLangBut;
    private Window addPrLangWindow;
    private TextField prLangName;
    private ArrayList<Slider> programLanguages;
    private GridLayout glayoutPrLang;
    
    public MainPanelStudent(HeaderLayout hlayout) {
        super(hlayout);
        setContent(getUserLayout());
        blankLayout = new VerticalLayout();
        fillBlankLayout();
        tabSheet.addTab(blankLayout,"Анкета");
        interviewLayout = new VerticalLayout();
        fillInterviewLayout();
        tabSheet.addTab(interviewLayout,"Собеседование");
        settingsLo = new VerticalLayout();
        fillSettingsLayout();
        tabSheet.addTab(settingsLo,"Настройки");
    }

    private void fillBlankLayout() {
        blankLayout.setMargin(true);
        blankLayout.setSpacing(true);
        blankLayout.setWidth("98%");
        persInfo = new Panel("Персональная информация");
        blankLayout.addComponent(persInfo);
        persInfo.setWidth("100%");
        GridLayout glayout1 = new GridLayout(4,2);
        glayout1.setMargin(true);
        glayout1.setSpacing(true);
        persInfo.setContent(glayout1);
        TextField firstName = new TextField("Имя");
        firstName.addValidator(new RegexpValidator("\\w{3,}", "Поле должно содержать хотя бы 3 символа."));
        firstName.setRequired(true);
        TextField middleName = new TextField("Отчество");
        middleName.addValidator(new RegexpValidator("\\w{3,}", "Поле должно содержать хотя бы 3 символа."));
        middleName.setRequired(true);
        TextField lastName = new TextField("Фамилия");
        lastName.setRequired(true);
        lastName.addValidator(new RegexpValidator("[a-zA-Z0-9-]{3,}", "Поле должно содержать хотя бы 3 символа."));
        universities = new ComboBox("ВУЗ",getUniversityList());
        universities.setRequired(true);
        universities.addValidator(new RegexpValidator("[a-zA-Z0-9_. -]{3,}", "Поле должно содержать хотя бы 3 символа."));
        universities.setNewItemsAllowed(true);
                universities.setNullSelectionAllowed(false);
                universities.setNewItemHandler(new AbstractSelect.NewItemHandler() {
                    public void addNewItem(String newItemCaption) {
                        if (!universities.containsId(newItemCaption)) { 
                            universities.addItem(newItemCaption);
                            universities.setValue(newItemCaption);
                        }
                    }
                });
        universities.setImmediate(true);
        TextField universityYear = new TextField("Курс");
        universityYear.setRequired(true);
        universityYear.addValidator(new IntegerValidator("Ошибка! Введите номер курса."));
        faculties = new ComboBox("Факультет", getFacultiesList());
        faculties.setRequired(true);
        faculties.addValidator(new RegexpValidator("\\w{3,}", "Поле должно содержать хотя бы 3 символа."));
        faculties.setNewItemsAllowed(true);
                faculties.setNullSelectionAllowed(false);
                faculties.setNewItemHandler(new AbstractSelect.NewItemHandler() {
                    public void addNewItem(String newItemCaption) {
                        if (!faculties.containsId(newItemCaption)) { 
                            faculties.addItem(newItemCaption);
                            faculties.setValue(newItemCaption);
                        }
                    }
                });
        faculties.setImmediate(true);
        TextField universityGradYear = new TextField("Год окончания");
        universityGradYear.setRequired(true);
        universityGradYear.addValidator(new IntegerValidator("Ошибка! Введите год."));
        persInfo.addComponent(lastName);
        persInfo.addComponent(firstName);
        persInfo.addComponent(middleName);
        persInfo.addComponent(universities);
        persInfo.addComponent(universityYear);
        persInfo.addComponent(faculties);
        persInfo.addComponent(universityGradYear);
        Iterator<Component> i = persInfo.getComponentIterator();
        while (i.hasNext()) {
            Component c = (Component) i.next();
            c.setWidth("200");
        }
        
        contacts = new Panel("Контакты");
        blankLayout.addComponent(contacts);
        GridLayout glayout2 = new GridLayout(4,3);
        glayout2.setMargin(true);
        glayout2.setSpacing(true);
        contacts.setContent(glayout2);
        TextField email1 = new TextField("Email 1");
        email1.setRequired(true);
        email1.addValidator(new EmailValidator("Email должен содержать знак '@' и полный домен."));
        TextField email2 = new TextField("Email 2");
        email2.addValidator(new EmailValidator("Email должен содержать знак '@' и полный домен."));
        email2.setRequired(true);
        TextField telephone = new TextField("Телефон");
        telephone.setRequired(true);
        anotherContactsBut = new Button("Добавить другие");
        anotherContactsBut.addListener(this);
        contacts.addComponent(email1);
        contacts.addComponent(email2);
        contacts.addComponent(telephone);
        contacts.addComponent(anotherContactsBut);
        glayout2.setComponentAlignment(anotherContactsBut, Alignment.BOTTOM_RIGHT);
        i = contacts.getComponentIterator();
        while (i.hasNext()) {
            Component c = (Component) i.next();
            c.setWidth("200");
        }
        
        interests = new Panel("Интересы");
        blankLayout.addComponent(interests);
        GridLayout glayout3 = new GridLayout(3,2);
        glayout3.setMargin(true);
        glayout3.setSpacing(true);
        interests.setContent(glayout3);
        List<String> listInterests = Arrays.asList(new String[] {"учебный центр/стажировка","работа в компании NetCracker"});
        OptionGroup whatInterest = new OptionGroup("Что заинтересовало: ",listInterests);
        whatInterest.setMultiSelect(true);
        whatInterest.setImmediate(true);
        List<String> workSphere = Arrays.asList(new String[] {"разработка ПО","другие области (уточните)"});
        OptionGroup whatWorkSphere  = new OptionGroup("Интересующая область деятельности: ",workSphere);
        whatWorkSphere.setMultiSelect(true);
        whatWorkSphere.setImmediate(true);
        TextField anotherWorkSphere = new TextField();
        List<String> workTypes = Arrays.asList(new String[] {"глубокая специализация","разнообразная работа","руководство специалистами","продажи","другое (уточните)"});
        OptionGroup whatWorkType  = new OptionGroup("Тип работы: ",workTypes);
        TextField anotherWorkType = new TextField();
        whatWorkType.setMultiSelect(true);
        whatWorkType.setImmediate(true);
        interests.addComponent(whatInterest);
        interests.addComponent(whatWorkSphere);
        interests.addComponent(whatWorkType);
        glayout3.addComponent(anotherWorkSphere,1,1);
        glayout3.addComponent(anotherWorkType,2,1);
        i = interests.getComponentIterator();
        while (i.hasNext()) {
            Component c = (Component) i.next();
            c.setWidth("250");
        }
        
        accomplishments = new Panel("Достоинства");
        blankLayout.addComponent(accomplishments);
        VerticalLayout vlayout = new VerticalLayout();
        vlayout.setMargin(true);
        vlayout.setSpacing(true);
        vlayout.setWidth("100%");
        glayoutPrLang = new GridLayout(4,2);
        glayoutPrLang.setMargin(true);
        glayoutPrLang.setSpacing(true);
        accomplishments.setContent(vlayout);
        Label progrLang = new Label("Владение языками программирования (по шкале от 1 до 5):"
                + " 1 – писал простые программы с книгой/справкой; 3 – хорошо помню синтаксис и нек. "
                + "библиотеки; 5 – написал крупный проект");
        vlayout.addComponent(progrLang);
        vlayout.addComponent(glayoutPrLang);
        Slider sliderC = new Slider("C++");
        sliderC.setWidth("200");
        sliderC.setMin(1);
        sliderC.setMax(5);
        sliderC.setImmediate(true);
        glayoutPrLang.addComponent(sliderC);
        Slider sliderJava = new Slider("Java");
        sliderJava.setWidth("200");
        sliderJava.setMin(1);
        sliderJava.setMax(5);
        sliderJava.setImmediate(true);
        glayoutPrLang.addComponent(sliderJava);
        addPrLangBut = new Button("Добавить язык");
        addPrLangBut.addListener(this);
        addPrLangBut.setWidth("200");
        vlayout.addComponent(addPrLangBut);
        
    }

    private void fillInterviewLayout() {
        
    }

    private void fillSettingsLayout() {
    }

    public void buttonClick(ClickEvent event) {
        Button source = event.getButton();
        if (source == anotherContactsBut) { 
            addNewContact();
        } else if(source == addPrLangBut) {
            addProgrammingLanguage();
        }
    }
    
    private void addNewContact() {
        addContact = new Window("Добавить контакт");
        addContact.setModal(true);
        addContact.setWidth("20%");
        addContact.setResizable(false);
        addContact.center();
        VerticalLayout layout = new VerticalLayout();
        addContact.setContent(layout);
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.setWidth("100%");
        contactType = new TextField("Тип");
        contactType.setRequired(true);
        contactType.addValidator(new RegexpValidator("[a-zA-Z0-9_. -]{3,}", "Поле должно содержать хотя бы 3 символа."));
        contactValue = new TextField("Значение");
        contactValue.setRequired(true);
        contactValue.addValidator(new RegexpValidator("[a-zA-Z0-9_. -]{3,}", "Поле должно содержать хотя бы 3 символа."));
        Button okBut = new Button("Добавить");
        contactList = new ArrayList<TextField>();
        okBut.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                if(contactType.isValid() && contactValue.isValid()) {
                    TextField newTextField = new TextField((String)contactType.getValue(),(String)contactValue.getValue());
                    newTextField.setWidth("200");
                    contactList.add(newTextField);
                    contacts.addComponent(contactList.get(contactList.size()-1));
                    getWindow().removeWindow(addContact);
                }
            }
        });
        layout.addComponent(contactType);
        layout.addComponent(contactValue);
        layout.addComponent(okBut);
        layout.setComponentAlignment(contactValue, Alignment.TOP_CENTER);
        layout.setComponentAlignment(contactType, Alignment.TOP_CENTER);
        layout.setComponentAlignment(okBut, Alignment.TOP_CENTER);
        getWindow().addWindow(addContact);
    }
    /**
     * Need to implement!
     * @return list of universities from DB
     */
    private Container getUniversityList() {
        return null;
    }
    
    private Container getFacultiesList() {
        return null;
    }

    private void addProgrammingLanguage() {
        addPrLangWindow = new Window("Добавить язык");
        addPrLangWindow.setModal(true);
        addPrLangWindow.setWidth("20%");
        addPrLangWindow.setResizable(false);
        addPrLangWindow.center();
        VerticalLayout layout = new VerticalLayout();
        addPrLangWindow.setContent(layout);
        layout.setMargin(true);
        layout.setSpacing(true);
        prLangName = new TextField("Язык");
        prLangName.setRequired(true);
        Button okBut = new Button("Добавить");
        programLanguages = new ArrayList<Slider>();
        okBut.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                if(prLangName.isValid()) {
                    Slider newSlider = new Slider((String)prLangName.getValue(),1,5);
                    newSlider.setWidth("200");
                    programLanguages.add(newSlider);
                    glayoutPrLang.addComponent(programLanguages.get(programLanguages.size()-1));
                    getWindow().removeWindow(addPrLangWindow);
                }
            }
        });
        layout.addComponent(prLangName);
        layout.addComponent(okBut);
        layout.setComponentAlignment(prLangName, Alignment.TOP_CENTER);
        layout.setComponentAlignment(okBut, Alignment.TOP_CENTER);
        getWindow().addWindow(addPrLangWindow);
    }

}
