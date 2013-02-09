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
import com.vaadin.terminal.FileResource;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.themes.Reindeer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
/**
 *
 * @author akush_000
 */
public class StudentBlank extends VerticalLayout implements FieldEvents.BlurListener, Upload.SucceededListener,
                                   Upload.FailedListener,
                                   Upload.Receiver {
    private Button save;
    private Panel contacts;
    private Button addAnotherContactsBut;
    private TextField contactType;
    private TextField contactValue;
    private Window addContact;
    private Panel interests;
    private Panel accomplishments;
    private Panel persInfo;
    private Button addPrLangBut;
    private Window addPrLangWindow;
    private TextField prLangName;
    private GridLayout glayoutPrLang;
    private GridLayout glayoutKnow;
    private Button addKnowlegeBut;
    private Window addKnowlegeWindow;
    private TextField knowlName;
    private ButtonsListener buttonsListener = new ButtonsListener();
    private Upload photoUpload;
    private File photoFile;
    private MainPage mainPage;
    
    private ComboBox universities;
    private ComboBox faculties;
    private ComboBox cathedras;
    private TextField firstName;
    private TextField middleName;
    private TextField lastName;
    private TextField universityYear;
    private TextField universityGradYear;
    private TextField email1;
    private TextField email2;
    private TextField telephone;
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
    private TextField anotherContact;
    private ArrayList<Slider> programLangList = new ArrayList<Slider>();
    private ArrayList<Slider> knowlegesList = new ArrayList<Slider>();
    private InterestSelection eduCenter;
    private InterestSelection workNC;
    private InterestSelection development;
    private TextField anotherWorkSphere;
    private InterestSelection deepSpec;
    private InterestSelection variousWork;
    private InterestSelection mrSpec;
    private InterestSelection sale;
    private TextField anotherWorkType;
    private Embedded photo;

    public StudentBlank() {
        setMargin(true);
        setSpacing(true);
        setWidth("100%");
        persInfo = new Panel("Персональная информация");
        persInfoPanelFill();
        addComponent(persInfo);
        contacts = new Panel("Контакты");
        addComponent(contacts);
        contactsPanelFill();
        interests = new Panel("Интересы");
        addComponent(interests);
        interestsPanelFill();
        accomplishments = new Panel("Достоинства");
        addComponent(accomplishments);
        accomplishmentsPanelFill();
        OptionGroup agreement = new OptionGroup();
        agreement.addItem("Согласен с условиями соглашения: ");
        agreement.setMultiSelect(true);
        addComponent(agreement);
        Label agreementText = new Label("Я даю согласие на хранение, обработку и использование моих персональных данных с целью возможного обучения и трудоустройства в компании НЕТКРЕКЕР на данный момент и в будущем.");
        agreementText.setWidth("750");
        addComponent(agreementText);
        save = new Button("Сохранить");
        save.setRequired(true);
        save.setWidth("200");
        save.addListener(buttonsListener);
        addComponent(save);
        setComponentAlignment(save, Alignment.TOP_CENTER);
        
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
        contactType.addValidator(new RegexpValidator("[а-яА-ЯёЁa-zA-Z0-9_. -]{3,}", "Поле должно содержать хотя бы 3 символа."));
        contactValue = new TextField("Значение");
        contactValue.setRequired(true);
        contactValue.addValidator(new RegexpValidator("[а-яА-ЯёЁa-zA-Z0-9_. -]{3,}", "Поле должно содержать хотя бы 3 символа."));
        Button okBut = new Button("Добавить");
        okBut.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                if(contactType.isValid() && contactValue.isValid()) {
                    anotherContact = new TextField((String)contactType.getValue(),(String)contactValue.getValue());
                    GridLayout gl = (GridLayout) contacts.getContent();
                    gl.removeComponent(addAnotherContactsBut);
                    gl.addComponent(anotherContact,0,1);
                    anotherContact.setWidth("200");
                    gl.addComponent(addAnotherContactsBut,1,1);
                    gl.setComponentAlignment(addAnotherContactsBut, Alignment.BOTTOM_LEFT);
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
        okBut.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                if(prLangName.isValid()) {
                    Slider newSlider = new Slider((String)prLangName.getValue());
                    sliderConfig(newSlider,1);
                    programLangList.add(newSlider);
                    glayoutPrLang.addComponent(programLangList.get(programLangList.size()-1));
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
    
    private void sliderConfig(Component slider, int min) {
        Slider sl = (Slider) slider;
        sl.setWidth("220");
        sl.setMin(min);
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
        okBut.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                if(knowlName.isValid()) {
                    Slider newSlider = new Slider((String)knowlName.getValue());
                    sliderConfig(newSlider,0);
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
        GridLayout glayout1 = new GridLayout(3,5);
        glayout1.setSpacing(true);
        glayout1.setMargin(true);
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
        cathedras = new ComboBox("Кафедра", getCathedrasList());
        cathedras.setRequired(true);
        cathedras.addListener(this);
        cathedras.addValidator(new RegexpValidator("[а-яА-ЯёЁa-zA-Z0-9_. -]{3,}", "Поле должно содержать хотя бы 3 символа."));
        cathedras.setNewItemsAllowed(true);
                cathedras.setNullSelectionAllowed(false);
                cathedras.setNewItemHandler(new AbstractSelect.NewItemHandler() {
                    public void addNewItem(String newItemCaption) {
                        if (!cathedras.containsId(newItemCaption)) { 
                            cathedras.addItem(newItemCaption);
                            cathedras.setValue(newItemCaption);
                        }
                    }
                });
        cathedras.setImmediate(true);
        universityGradYear = new TextField("Год окончания");
        universityGradYear.setRequired(true);
        universityGradYear.addListener(this);
        universityGradYear.addValidator(new IntegerValidator("Ошибка! Введите год."));
        glayout1.addComponent(lastName,0,0);
        glayout1.addComponent(firstName,1,0);
        glayout1.addComponent(middleName,0,1);
        glayout1.addComponent(universities,1,1);
        glayout1.addComponent(universityYear,0,2);
        glayout1.addComponent(faculties,1,2);
        glayout1.addComponent(cathedras,0,3);
        glayout1.addComponent(universityGradYear,1,3);
        Iterator<Component> i = glayout1.getComponentIterator();
        while (i.hasNext()) {
            Component c = (Component) i.next();
            c.setWidth("220");
        }
         photoUpload = new Upload("Фото",this);
         photoUpload.setButtonCaption("Загрузка");
         photoUpload.addListener((Upload.SucceededListener) this);
         photoUpload.addListener((Upload.FailedListener) this);
         glayout1.addComponent(photoUpload,0,4,2,4);
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
        addAnotherContactsBut = new Button("Добавить другие");
        addAnotherContactsBut.addListener(buttonsListener);
        glayout2.addComponent(email1);
        glayout2.addComponent(email2);
        glayout2.addComponent(telephone);
        contacts.addComponent(addAnotherContactsBut);
        Iterator<Component> i = contacts.getComponentIterator();
        while (i.hasNext()) {
            Component c = (Component) i.next();
            c.setWidth("220");
        }
        addAnotherContactsBut.setWidth("200");
    }

    private void interestsPanelFill() {
        VerticalLayout vlayout = new VerticalLayout();
        vlayout.setSpacing(true);
        vlayout.setWidth("100%");
        vlayout.setMargin(true);
        GridLayout glayout1 = new GridLayout(3,1);
        glayout1.setSpacing(true);
        interests.setContent(vlayout);
        Label whatInterest = new Label("Что заинтересовало:");
        vlayout.addComponent(whatInterest);
        vlayout.addComponent(glayout1);
        eduCenter = new InterestSelection("Учебный центр/стажировка:");
        glayout1.addComponent(eduCenter);  
        workNC = new InterestSelection("Работа в компании NetCracker:");
        glayout1.addComponent(workNC);
        Label workSphere = new Label("Интересующая область деятельности:");
        vlayout.addComponent(workSphere);
        GridLayout glayout2 = new GridLayout(3,1);
        glayout2.setSpacing(true);
        vlayout.addComponent(glayout2);
        development = new InterestSelection("Разработка ПО:");
        anotherWorkSphere = new TextField("Другие: ");
        anotherWorkSphere.setWidth("250");
        glayout2.addComponent(development);
        glayout2.addComponent(anotherWorkSphere);
        glayout2.setComponentAlignment(development, Alignment.BOTTOM_LEFT);
        Label whatWorkType = new Label("Тип работы:");
        vlayout.addComponent(whatWorkType);
        GridLayout glayout3 = new GridLayout(2,3);
        glayout3.setSpacing(true);
        vlayout.addComponent(glayout3);
        deepSpec = new InterestSelection("Глубокая специализация: ");
        variousWork = new InterestSelection("Разнообразная работа: ");
        mrSpec = new InterestSelection("Руководство специалистами: ");
        sale = new InterestSelection("Продажи");
        glayout3.addComponent(deepSpec);
        glayout3.addComponent(variousWork);
        glayout3.addComponent(mrSpec);
        glayout3.addComponent(sale);
        anotherWorkType = new TextField("Другие: ");
        anotherWorkType.setWidth("250");
        glayout3.addComponent(anotherWorkType);
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
        sliderConfig(sliderC,1);
        glayoutPrLang.addComponent(sliderC);
        sliderJava = new Slider("Java");
        sliderConfig(sliderJava,1);
        glayoutPrLang.addComponent(sliderJava);
        addPrLangBut = new Button("Добавить язык");
        addPrLangBut.addListener(buttonsListener);
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
            sliderConfig(c,0);
        }
        addKnowlegeBut = new Button("Другие разделы");
        addKnowlegeBut.addListener(buttonsListener);
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
            sliderConfig(c,1);
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
    
    private Container getCathedrasList(){
        return null;
    }
    
    /**
     * Implement this!
     */
    private void checkPhotoFile() {
        
    }

    public void uploadSucceeded(SucceededEvent event) {
        getWindow().showNotification("Файл успешно загружен", Window.Notification.TYPE_TRAY_NOTIFICATION);
        FileResource imageResource = new FileResource(photoFile, getApplication());
        if(photo == null) {
            photo = new Embedded("", imageResource);
            GridLayout gl = (GridLayout) persInfo.getContent();
            gl.addComponent(photo,2,0,2,3);
            gl.setComponentAlignment(photo, Alignment.MIDDLE_CENTER);
        }
        else {
            Embedded oldPhoto = photo;
            photo = new Embedded("", imageResource);
            persInfo.replaceComponent(oldPhoto, photo);
        }
        
    }

    public void uploadFailed(FailedEvent event) {
        getWindow().showNotification("Ошибка загрузки файла", Window.Notification.TYPE_TRAY_NOTIFICATION);
    }

    public OutputStream receiveUpload(String filename, String mimeType) {
        FileOutputStream fos = null; 
        WebApplicationContext context = (WebApplicationContext) getApplication().getContext();
        photoFile = new File (context.getHttpSession().getServletContext().getRealPath("/WEB-INF/resources/"+filename) );
        try {
            fos = new FileOutputStream(photoFile);
            checkPhotoFile();
        } catch (final java.io.FileNotFoundException e) {
            getWindow().showNotification("Ошибка загрузки файла", Window.Notification.TYPE_TRAY_NOTIFICATION);
        }
        return fos; 
    }
 
    private class ButtonsListener implements Button.ClickListener {

        public void buttonClick(ClickEvent event) {
            Button source = event.getButton();
                if (source == addAnotherContactsBut) { 
                    if(anotherContact == null) {
                        addNewContact();
                    }
                    else {
                        getWindow().showNotification(new Window.Notification("Вы добавили максимальное количество контактов.",Window.Notification.TYPE_TRAY_NOTIFICATION));
                    }
                } else if(source == addPrLangBut) {
                    if(programLangList == null || programLangList.size()<3) {
                        addProgrammingLanguage();
                    }
                    else {
                        getWindow().showNotification(new Window.Notification("Вы добавили максимальное количество языков.",Window.Notification.TYPE_TRAY_NOTIFICATION));
                    }                    
                } else if (source == addKnowlegeBut) {
                    if(knowlegesList == null || knowlegesList.size()<3) {
                        addKnowlege();
                    }
                    else {
                        getWindow().showNotification(new Window.Notification("Вы добавили максимальное количество разделов.",Window.Notification.TYPE_TRAY_NOTIFICATION));
                    }  
                } else if(source == save) {
                    saveBlank();
                }
        }
        
    }
    
    public class InterestSelection extends HorizontalLayout implements Button.ClickListener{
        Label caption;
        ArrayList<Button> but = new ArrayList<Button>();
        String select = "-";
        
        public InterestSelection(String caption) {
            setWidth("250");
            this.caption = new Label(caption);
            this.caption.setStyleName(Reindeer.LABEL_SMALL);
            this.caption.setWidth("100");
            addComponent(this.caption);
            but.add(new NativeButton("-"));
            but.add(new NativeButton("±"));
            but.add(new NativeButton("+"));
            but.add(new NativeButton("?"));
            but.get(0).setEnabled(false);
            for(Button b : but) {
                b.setWidth("30");
                b.setDisableOnClick(true);
                b.addListener(this);
                addComponent(b); 
            }
            
        }

        public void buttonClick(ClickEvent event) {
            Button clickedButton = event.getButton();
            for(Button b : but) {
                if(b!=clickedButton) {
                    b.setEnabled(true);
                }
                else {
                    select = b.getCaption();
                }
            }
        }
        
        public String getValue() {
            return select;
        }
    }
    
}
