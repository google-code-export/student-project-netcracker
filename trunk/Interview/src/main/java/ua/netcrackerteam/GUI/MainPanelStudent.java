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
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Slider;
import com.vaadin.ui.TextArea;
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
    private GridLayout glayoutKnow;
    private Button addKnowlegeBut;
    private Window addKnowlegeWindow;
    private TextField knowlName;
    private ArrayList<Slider> knowlegesList;
    
    public MainPanelStudent(HeaderLayout hlayout) {
        super(hlayout);
        setContent(getUserLayout());
        blankLayout = new VerticalLayout();
        tabSheet.addTab(blankLayout,"Анкета");
        fillBlankLayout();
        interviewLayout = new VerticalLayout();
        tabSheet.addTab(interviewLayout,"Собеседование");
        fillInterviewLayout();
        settingsLo = new VerticalLayout();
        tabSheet.addTab(settingsLo,"Настройки");
        fillSettingsLayout();
    }

    private void fillBlankLayout() {
        blankLayout.setMargin(true);
        blankLayout.setSpacing(true);
        blankLayout.setWidth("98%");
        persInfo = new Panel("Персональная информация");
        persInfoPanelFill();
        blankLayout.addComponent(persInfo);
        contacts = new Panel("Контакты");
        blankLayout.addComponent(contacts);
        contactsPanelFill();
        interests = new Panel("Интересы");
        blankLayout.addComponent(interests);
        interestsPanelFill();
        accomplishments = new Panel("Достоинства");
        blankLayout.addComponent(accomplishments);
        accomplishmentsPanelFill();
        OptionGroup agreement = new OptionGroup();
        agreement.addItem("Я даю согласие на хранение, обработку и использование моих персональных данных с целью возможного обучения и трудоустройства в компании НЕТКРЕКЕР на данный момент и в будущем.");
        blankLayout.addComponent(agreement);
        save = new Button("Сохранить");
        save.setRequired(true);
        save.setWidth("200");
        save.addListener(this);
        blankLayout.addComponent(save);
        blankLayout.setComponentAlignment(save, Alignment.TOP_CENTER);
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
        } else if (source == addKnowlegeBut) {
            addKnowlege();
        } else if(source == save) {
            
            saveBlank();
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
        contactType.addValidator(new RegexpValidator("[a-zA-Z0-9_. -]{3,25}", "Поле должно содержать хотя бы 3 символа."));
        contactValue = new TextField("Значение");
        contactValue.setRequired(true);
        contactValue.addValidator(new RegexpValidator("[a-zA-Z0-9_. -]{3,25}", "Поле должно содержать хотя бы 3 символа."));
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
        prLangName.addValidator(new RegexpValidator("[a-zA-Z0-9_. -]{3,}", "Поле должно содержать хотя бы 3 символа."));
        Button okBut = new Button("Добавить");
        programLanguages = new ArrayList<Slider>();
        okBut.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                if(prLangName.isValid()) {
                    Slider newSlider = new Slider((String)prLangName.getValue(),1,5);
                    newSlider.setWidth("250");
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
    
    private void sliderConfig(Component slider) {
        Slider sl = (Slider) slider;
        sl.setWidth("250");
        sl.setMin(1);
        sl.setMax(5);
    }

    private void addKnowlege() {
        addKnowlegeWindow = new Window("Добавить раздел");
        addKnowlegeWindow.setModal(true);
        addKnowlegeWindow.setWidth("20%");
        addKnowlegeWindow.setResizable(false);
        addKnowlegeWindow.center();
        VerticalLayout layout = new VerticalLayout();
        addKnowlegeWindow.setContent(layout);
        layout.setMargin(true);
        layout.setSpacing(true);
        knowlName = new TextField("Раздел (в области IT или сетей)");
        knowlName.setRequired(true);
        knowlName.addValidator(new RegexpValidator("[a-zA-Z0-9_. -]{3,}", "Поле должно содержать хотя бы 3 символа."));
        Button okBut = new Button("Добавить");
        knowlegesList = new ArrayList<Slider>();
        okBut.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                if(knowlName.isValid()) {
                    Slider newSlider = new Slider((String)knowlName.getValue(),1,5);
                    newSlider.setWidth("250");
                    knowlegesList.add(newSlider);
                    glayoutKnow.addComponent(knowlegesList.get(knowlegesList.size()-1));
                    getWindow().removeWindow(addKnowlegeWindow);
                }
            }
        });
        layout.addComponent(knowlName);
        layout.addComponent(okBut);
        layout.setComponentAlignment(knowlName, Alignment.TOP_CENTER);
        layout.setComponentAlignment(okBut, Alignment.TOP_CENTER);
        getWindow().addWindow(addKnowlegeWindow);
    }

    private void saveBlank() {
        
    }

    private void persInfoPanelFill() {
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
            c.setWidth("250");
        }
    }

    private void contactsPanelFill() {
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
        Iterator<Component> i = contacts.getComponentIterator();
        while (i.hasNext()) {
            Component c = (Component) i.next();
            c.setWidth("250");
        }
        anotherContactsBut.setWidth("200");
    }

    private void interestsPanelFill() {
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
        Iterator<Component> i = interests.getComponentIterator();
        while (i.hasNext()) {
            Component c = (Component) i.next();
            c.setWidth("250");
        }
    }

    private void accomplishmentsPanelFill() {
        VerticalLayout vlayout = new VerticalLayout();
        vlayout.setSpacing(true);
        vlayout.setWidth("100%");
        vlayout.setMargin(true);
        glayoutPrLang = new GridLayout(4,2);
        glayoutPrLang.setSpacing(true);
        accomplishments.setContent(vlayout);
        Label progrLang = new Label("Владение языками программирования:"
                + " 1 – писал простые программы с книгой/справкой; 3 – хорошо помню синтаксис и некоторые "
                + "библиотеки; 5 – написал крупный проект");
        vlayout.addComponent(progrLang);
        vlayout.addComponent(glayoutPrLang);
        Slider sliderC = new Slider("C++");
        sliderConfig(sliderC);
        glayoutPrLang.addComponent(sliderC);
        Slider sliderJava = new Slider("Java");
        sliderConfig(sliderJava);
        glayoutPrLang.addComponent(sliderJava);
        addPrLangBut = new Button("Добавить язык");
        addPrLangBut.addListener(this);
        addPrLangBut.setWidth("200");
        vlayout.addComponent(addPrLangBut);

        Label knowledge = new Label("Как ты оцениваешь свои знания по разделам: ");
        vlayout.addComponent(knowledge);
        glayoutKnow = new GridLayout(4,2);
        glayoutKnow.setSpacing(true);
        vlayout.addComponent(glayoutKnow);
        Slider sliderNT = new Slider("Сетевые технологии");
        Slider sliderEA = new Slider("Эффективные алгоритмы");
        Slider sliderOOP = new Slider("Объектно-ориент. программирование");
        Slider sliderDB = new Slider("Базы данных");
        Slider sliderWeb = new Slider("Web");
        Slider sliderGUI = new Slider("Графический интерфейс (не Web)");
        Slider sliderWP = new Slider("Сетевое программирование");
        Slider sliderPP = new Slider("Проектирование программ");
        glayoutKnow.addComponent(sliderNT);
        glayoutKnow.addComponent(sliderEA);
        glayoutKnow.addComponent(sliderOOP);
        glayoutKnow.addComponent(sliderDB);
        glayoutKnow.addComponent(sliderWeb);
        glayoutKnow.addComponent(sliderGUI);
        glayoutKnow.addComponent(sliderWP);
        glayoutKnow.addComponent(sliderPP);        
        Iterator<Component> i = glayoutKnow.getComponentIterator();
        while (i.hasNext()) {
            Component c = (Component) i.next();
            sliderConfig(c);
        }
        addKnowlegeBut = new Button("Другие разделы");
        addKnowlegeBut.addListener(this);
        addKnowlegeBut.setWidth("200");
        vlayout.addComponent(addKnowlegeBut);
        TextArea expirience = new TextArea("Если у тебя уже есть опыт работы и/или выполненные учебные проекты, опиши их: ");
        expirience.setWidth("1020");
        expirience.setRows(4);
        expirience.setRequired(true);
        expirience.setWordwrap(true);
        expirience.setMaxLength(100);
        vlayout.addComponent(expirience);
        Label english = new Label("Уровень английского языка (от 1 = elementary до 5 = advanced): ");
        vlayout.addComponent(english);
        GridLayout glayoutEng = new GridLayout(3,1);
        vlayout.addComponent(glayoutEng);
        glayoutEng.setSpacing(true);
        Slider reading = new Slider("Чтение");
        Slider writing = new Slider("Письмо");
        Slider speaking = new Slider("Устная речь");
        glayoutEng.addComponent(reading);
        glayoutEng.addComponent(writing);
        glayoutEng.addComponent(speaking);
        i = glayoutEng.getComponentIterator();
        while (i.hasNext()) {
            Component c = (Component) i.next();
            sliderConfig(c);
        }
        List<String> workTypes = Arrays.asList(new String[] {"Реклама в ВУЗе","Интернет","От знакомых","Реклама (СМИ)","Другое (уточните)"});
        OptionGroup advert = new OptionGroup("Откуда ты узнал о наборе в учебный центр?",workTypes);
        advert.setWidth("250");
        advert.setRequired(true);
        advert.setItemEnabled(0, true);
        vlayout.addComponent(advert);
        TextField anotherAdvert = new TextField();
        anotherAdvert.setWidth("250");
        vlayout.addComponent(anotherAdvert);
        TextArea whyYou = new TextArea("Почему тебя обязательно надо взять в NetCracker (важные достоинства; возможно, обещания :) )");
        whyYou.setWidth("1020");
        whyYou.setRows(3);
        whyYou.setRequired(true);
        whyYou.setMaxLength(100);
        vlayout.addComponent(whyYou);
        TextArea moreInfo = new TextArea("Дополнительные сведения о себе: олимпиады, поощрения, курсы, сертификаты, личные качества, др.");
        moreInfo.setWidth("1020");
        moreInfo.setRequired(true);
        moreInfo.setRows(3);
        moreInfo.setMaxLength(100);
        vlayout.addComponent(moreInfo);   
    }

}
