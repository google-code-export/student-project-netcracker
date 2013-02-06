/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.data.Container;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.IntegerValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Panel for Student view
 * @author Anna Kushnirenko
 */
public class MainPanelStudent extends MainPanel implements FieldEvents.BlurListener {
    private VerticalLayout blankLayout;
    private VerticalLayout interviewLayout;
    private VerticalLayout settingsLo;
    private Button save;
    private Panel contacts;
    private Button anotherContactsBut;
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
    
    private ComboBox universities;
    private ComboBox faculties;
    private TextField firstName;
    private TextField middleName;
    private TextField lastName;
    private TextField universityYear;
    private TextField universityGradYear;
    private TextField email1;
    private TextField email2;
    private TextField telephone;
    private OptionGroup whatInterest;
    private OptionGroup whatWorkSphere;
    private TextField anotherWorkSphere;
    private OptionGroup whatWorkType;
    private TextField anotherWorkType;
    private Slider sliderC;
    private Slider sliderJava;
    private Slider sliderNT;
    private Slider sliderEA;
    private Slider sliderOOP;
    private Slider sliderDB;
    private Slider sliderWeb;
    private Slider sliderGUI;
    private Slider sliderWP;
    private Slider sliderPP;
    private TextArea expirience;
    private Slider reading;
    private Slider writing;
    private Slider speaking;
    private OptionGroup advert;
    private TextField anotherAdvert;
    private TextArea whyYou;
    private TextArea moreInfo;
    
    public MainPanelStudent(HeaderLayout hlayout) {
        super(hlayout);
        setContent(getUserLayout(hlayout));
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
        blankLayout.setWidth("100%");
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
        agreement.addItem("Согласен с условиями соглашения: ");
        agreement.setMultiSelect(true);
        blankLayout.addComponent(agreement);
        Label agreementText = new Label("Я даю согласие на хранение, обработку и использование моих персональных данных с целью возможного обучения и трудоустройства в компании НЕТКРЕКЕР на данный момент и в будущем.");
        agreementText.setWidth("750");
        blankLayout.addComponent(agreementText);
        save = new Button("Сохранить");
        save.setRequired(true);
        save.setWidth("200");
        save.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                buttonSaveClick(event);
            }
        });
        blankLayout.addComponent(save);
        blankLayout.setComponentAlignment(save, Alignment.TOP_CENTER);
    }

    private void fillInterviewLayout() {
        
    }

    private void fillSettingsLayout() {
    }

    public void buttonSaveClick(ClickEvent event) {
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
        contactType.addValidator(new RegexpValidator("[[а-яА-ЯёЁa-zA-Z0-9_. -]{3,}", "Поле должно содержать хотя бы 3 символа."));
        contactValue = new TextField("Значение");
        contactValue.setRequired(true);
        contactValue.addValidator(new RegexpValidator("[а-яА-ЯёЁa-zA-Z0-9_. -]{3,}", "Поле должно содержать хотя бы 3 символа."));
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
        prLangName.addValidator(new RegexpValidator("[а-яА-ЯёЁa-zA-Z0-9_. -]{3,}", "Поле должно содержать хотя бы 3 символа."));
        Button okBut = new Button("Добавить");
        programLanguages = new ArrayList<Slider>();
        okBut.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                if(prLangName.isValid()) {
                    Slider newSlider = new Slider((String)prLangName.getValue());
                    sliderConfig(newSlider);
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
        sl.setWidth("220");
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
        knowlName.addValidator(new RegexpValidator("[а-яА-ЯёЁa-zA-Z0-9_. -]{3,}", "Поле должно содержать хотя бы 3 символа."));
        Button okBut = new Button("Добавить");
        knowlegesList = new ArrayList<Slider>();
        okBut.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                if(knowlName.isValid()) {
                    Slider newSlider = new Slider((String)knowlName.getValue());
                    sliderConfig(newSlider);
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
  
    private void persInfoPanelFill() {
        persInfo.setWidth("100%");
        GridLayout glayout1 = new GridLayout(3,3);
        glayout1.setMargin(true);
        glayout1.setSpacing(true);
        persInfo.setContent(glayout1);
        firstName = new TextField("Имя");
        firstName.addListener(this);
        firstName.addValidator(new RegexpValidator("[а-яА-ЯёЁa-zA-Z0-9]{3,}", "Поле должно содержать хотя бы 3 символа."));
        firstName.setRequired(true);
        middleName = new TextField("Отчество");
        middleName.addListener(this);
        middleName.addValidator(new RegexpValidator("[а-яА-ЯёЁa-zA-Z0-9]{3,}", "Поле должно содержать хотя бы 3 символа."));
        middleName.setRequired(true);
        lastName = new TextField("Фамилия");
        lastName.setRequired(true);
        lastName.addListener(this);
        lastName.addValidator(new RegexpValidator("[а-яА-ЯёЁa-zA-Z0-9-]{3,}", "Поле должно содержать хотя бы 3 символа."));
        universities = new ComboBox("ВУЗ",getUniversityList());
        universities.setRequired(true);
        universities.addListener(this);
        universities.addValidator(new RegexpValidator("[а-яА-ЯёЁa-zA-Z0-9_. -]{3,}", "Поле должно содержать хотя бы 3 символа."));
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
        universityYear = new TextField("Курс");
        universityYear.setRequired(true);
        universityYear.addValidator(new IntegerValidator("Ошибка! Введите номер курса."));
        faculties = new ComboBox("Факультет", getFacultiesList());
        faculties.setRequired(true);
        faculties.addListener(this);
        faculties.addValidator(new RegexpValidator("[а-яА-ЯёЁa-zA-Z0-9_. -]{3,}", "Поле должно содержать хотя бы 3 символа."));
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
        universityGradYear = new TextField("Год окончания");
        universityGradYear.setRequired(true);
        universityGradYear.addListener(this);
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
            c.setWidth("220");
        }
    }

    private void contactsPanelFill() {
        GridLayout glayout2 = new GridLayout(3,2);
        glayout2.setMargin(true);
        glayout2.setSpacing(true);
        contacts.setContent(glayout2);
        email1 = new TextField("Email 1");
        email1.setRequired(true);
        email1.addListener(this);
        email1.addValidator(new EmailValidator("Email должен содержать знак '@' и полный домен."));
        email2 = new TextField("Email 2");
        email2.addValidator(new EmailValidator("Email должен содержать знак '@' и полный домен."));
        email2.setRequired(true);
        email2.addListener(this);
        telephone = new TextField("Телефон");
        telephone.setRequired(true);
        telephone.addListener(this);
        anotherContactsBut = new Button("Добавить другие");
        anotherContactsBut.addListener(this);
        contacts.addComponent(email1);
        contacts.addComponent(email2);
        contacts.addComponent(telephone);
        contacts.addComponent(anotherContactsBut);
        Iterator<Component> i = contacts.getComponentIterator();
        while (i.hasNext()) {
            Component c = (Component) i.next();
            c.setWidth("220");
        }
        anotherContactsBut.setWidth("200");
    }

    private void interestsPanelFill() {
        GridLayout glayout3 = new GridLayout(3,2);
        glayout3.setMargin(true);
        glayout3.setSpacing(true);
        interests.setContent(glayout3);
        List<String> listInterests = Arrays.asList(new String[] {"учебный центр/стажировка","работа в компании NetCracker"});
        whatInterest = new OptionGroup("Что заинтересовало: ",listInterests);
        whatInterest.setMultiSelect(true);
        whatInterest.setImmediate(true);
        List<String> workSphere = Arrays.asList(new String[] {"разработка ПО","другие области (уточните)"});
        whatWorkSphere  = new OptionGroup("Интересующая область деятельности: ",workSphere);
        whatWorkSphere.setMultiSelect(true);
        whatWorkSphere.setImmediate(true);
        anotherWorkSphere = new TextField();
        List<String> workTypes = Arrays.asList(new String[] {"глубокая специализация","разнообразная работа","руководство специалистами","продажи","другое (уточните)"});
        whatWorkType  = new OptionGroup("Тип работы: ",workTypes);
        anotherWorkType = new TextField();
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
            c.setWidth("220");
        }
    }

    private void accomplishmentsPanelFill() {
        VerticalLayout vlayout = new VerticalLayout();
        vlayout.setSpacing(true);
        vlayout.setWidth("100%");
        vlayout.setMargin(true);
        glayoutPrLang = new GridLayout(3,3);
        glayoutPrLang.setSpacing(true);
        accomplishments.setContent(vlayout);
        Label progrLang = new Label("Владение языками программирования:"
                + " 1 – писал простые программы с книгой/справкой; 3 – хорошо помню синтаксис и некоторые "
                + "библиотеки; 5 – написал крупный проект");
        vlayout.addComponent(progrLang);
        vlayout.addComponent(glayoutPrLang);
        sliderC = new Slider("C++");
        sliderConfig(sliderC);
        glayoutPrLang.addComponent(sliderC);
        sliderJava = new Slider("Java");
        sliderConfig(sliderJava);
        glayoutPrLang.addComponent(sliderJava);
        addPrLangBut = new Button("Добавить язык");
        addPrLangBut.addListener(this);
        addPrLangBut.setWidth("200");
        vlayout.addComponent(addPrLangBut);

        Label knowledge = new Label("Как ты оцениваешь свои знания по разделам: ");
        vlayout.addComponent(knowledge);
        glayoutKnow = new GridLayout(3,2);
        glayoutKnow.setSpacing(true);
        vlayout.addComponent(glayoutKnow);
        sliderNT = new Slider("Сетевые технологии");
        sliderEA = new Slider("Эффективные алгоритмы");
        sliderOOP = new Slider("Объектно-ориент. программирование");
        sliderDB = new Slider("Базы данных");
        sliderWeb = new Slider("Web");
        sliderGUI = new Slider("Графический интерфейс (не Web)");
        sliderWP = new Slider("Сетевое программирование");
        sliderPP = new Slider("Проектирование программ");
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
        expirience = new TextArea("Если у тебя уже есть опыт работы и/или выполненные учебные проекты, опиши их: ");
        expirience.setWidth("700");
        expirience.setRows(4);
        expirience.setRequired(true);
        expirience.setWordwrap(true);
        expirience.addValidator(getExpirienceValidator());
        expirience.addListener(this);
        vlayout.addComponent(expirience);
        Label english = new Label("Уровень английского языка (от 1 = elementary до 5 = advanced): ");
        vlayout.addComponent(english);
        GridLayout glayoutEng = new GridLayout(3,1);
        vlayout.addComponent(glayoutEng);
        glayoutEng.setSpacing(true);
        reading = new Slider("Чтение");
        writing = new Slider("Письмо");
        speaking = new Slider("Устная речь");
        glayoutEng.addComponent(reading);
        glayoutEng.addComponent(writing);
        glayoutEng.addComponent(speaking);
        i = glayoutEng.getComponentIterator();
        while (i.hasNext()) {
            Component c = (Component) i.next();
            sliderConfig(c);
        }
        List<String> workTypes = Arrays.asList(new String[] {"Реклама в ВУЗе","Интернет","От знакомых","Реклама (СМИ)","Другое (уточните)"});
        advert = new OptionGroup("Откуда ты узнал о наборе в учебный центр?",workTypes);
        advert.setWidth("220");
        advert.setRequired(true);
        advert.setItemEnabled(0, true);
        vlayout.addComponent(advert);
        anotherAdvert = new TextField();
        anotherAdvert.setWidth("220");
        vlayout.addComponent(anotherAdvert);
        whyYou = new TextArea("Почему тебя обязательно надо взять в NetCracker (важные достоинства; возможно, обещания :) )");
        whyYou.setWidth("700");
        whyYou.setRows(3);
        whyYou.setRequired(true);
        whyYou.addValidator(getWhyYouValidator());
        whyYou.addListener(this);
        vlayout.addComponent(whyYou);
        moreInfo = new TextArea("Дополнительные сведения о себе: олимпиады, поощрения, курсы, сертификаты, личные качества, др.");
        moreInfo.setWidth("700");
        moreInfo.setRequired(true);
        moreInfo.setRows(3);
        moreInfo.addValidator(getMoreInfoValidator());
        moreInfo.addListener(this);
        vlayout.addComponent(moreInfo);   
    }

    public void blur(BlurEvent event) {
        Object source = event.getComponent();
        if(source instanceof TextField) {
            TextField tf = (TextField) source;
            tf.isValid();
        } else if(source instanceof ComboBox){
            ComboBox cb = (ComboBox) source;
            cb.isValid();
        } else if(source instanceof TextArea){
            TextArea ta = (TextArea) source;
            ta.isValid();
        }
    }

    /**
     * Implement this!
     * @return 
     */
    private Validator getExpirienceValidator() {
        Validator v = new AbstractValidator("Ошибка!") {

            public boolean isValid(Object value) {
                return true;
            }
        };
        return v;
    }

    private Validator getWhyYouValidator() {
        Validator v = new AbstractValidator("Ошибка!") {

            public boolean isValid(Object value) {
                return true;
            }
        };
        return v;
    }

    private Validator getMoreInfoValidator() {
        Validator v = new AbstractValidator("Ошибка!") {

            public boolean isValid(Object value) {
                return true;
            }
        };
        return v;
    }

    private void saveBlank() {
        
    }
    
    private Container getUniversityList() {
        return null;
    }
    
    private Container getFacultiesList() {
        return null;
    }

}
