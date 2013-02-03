/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.IntegerValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.io.Serializable;



/**
 * Panel for Student view
 * @author Anna Kushnirenko
 */
public class MainPanelStudent extends MainPanel implements Button.ClickListener  {
    private VerticalLayout blankLayout;
    private VerticalLayout interviewLayout;
    private VerticalLayout settingsLo;
    private Form blankForm;
    private Button save;
    private ComboBox universities;
    private ComboBox faculties;
    private final String[] ORDER = new String[] {"firstName","middleName","lastName","university",
        "universityYear","faculty","universityGradYear","email1", "email2", "telephone"};
    
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
        blankForm = new Form();
        blankForm.setWidth("600");
        blankForm.setWriteThrough(false);
        blankLayout.addComponent(blankForm);
        blankLayout.setComponentAlignment(blankForm, Alignment.TOP_LEFT);
        GridLayout glayout = new GridLayout(4,10);
        glayout.setMargin(true);
        glayout.setSpacing(true);
        blankForm.setLayout(glayout);
        HorizontalLayout footer = new HorizontalLayout();
        footer.setSpacing(true);
        save = new Button("Сохранить",(Button.ClickListener) this);
        footer.addComponent(save);
        footer.setWidth("100%");
        footer.setComponentAlignment(save, Alignment.TOP_CENTER);
        blankForm.setFooter(footer);
        blankForm.setFormFieldFactory(new fieldFactory());
        PersonalInfoBean bean = new PersonalInfoBean();
        BeanItem item = new BeanItem(bean, ORDER);
        blankForm.setItemDataSource(item);
        
//        Button pdf = new Button("PDF");
//        footer.addComponent(pdf);
//        pdf.addListener(new com.vaadin.ui.Button.ClickListener() {
//                        
//                        @Override
//                        public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
//                                PdfFromComponent factory = new PdfFromComponent();
//                                factory.setFileName("Анкета.pdf");
//                                factory.export(blankLayout);
//                                
//                        }
//                });

    }

    private void fillInterviewLayout() {
        
    }

    private void fillSettingsLayout() {
    }
    
    private class fieldFactory extends DefaultFieldFactory {
        @Override
        public Field createField(Item item, Object propertyId, Component uiContext) {

            if(propertyId.equals("university")) {
                universities = new ComboBox("ВУЗ");
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
                universities.setWidth("200px");
                universities.setRequired(true);
                return universities;
            } else if(propertyId.equals("faculty")) {
                faculties = new ComboBox("Факультет");
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
                faculties.setRequired(true);
                faculties.setImmediate(true);
                faculties.setWidth("200px");
                return faculties;
            }

            Field field = super.createField(item, propertyId, uiContext);
            if (propertyId.equals("firstName")) {
                field.setCaption("Имя");
                field.addValidator(new RegexpValidator("\\w{3,}", "Поле должно содержать хотя бы 3 символа."));
            } else if (propertyId.equals("middleName")) {
                field.setCaption("Отчество");
                field.addValidator(new RegexpValidator("\\w{3,}", "Поле должно содержать хотя бы 3 символа."));
            } else if (propertyId.equals("lastName")) {
                field.setCaption("Фамилия");
                field.addValidator(new RegexpValidator("\\w{3,}", "Поле должно содержать хотя бы 3 символа."));
            } else if (propertyId.equals("universityYear")) {
                field.setCaption("Курс");
                field.addValidator(new IntegerValidator("Ошибка! Введите номер курса."));
            } else if (propertyId.equals("universityGradYear")) {
                field.setCaption("Год окончания");
                field.addValidator(new IntegerValidator("Ошибка! Введите год."));
            } else if (propertyId.equals("email1")) {
                field.setCaption("Email 1");
                field.addValidator(new EmailValidator("Email должен содержать знак '@' и полный домен."));
            } else if (propertyId.equals("email2")) {
                field.setCaption("Email 2");
                field.addValidator(new EmailValidator("Email должен содержать знак '@' и полный домен."));
            } else if (propertyId.equals("telephone")) {
                field.setCaption("Телефон");
            }
            field.setRequired(true);
            field.setWidth("200px");
            return field;
        }

        
    }
    
    public void buttonClick(ClickEvent event) {
        Button source = event.getButton();
        if (source == save) { 
            if(!blankForm.isValid()) {
                return;
            }
            blankForm.commit();
        }
    }
    
    public class PersonalInfoBean implements Serializable{
        private String firstName = "";
        private String middleName= "";
        private String lastName= "";
        private String university= "";
        private String universityYear= "";
        private String faculty= "";
        private String universityGradYear= "";
        private String email1 = "";
        private String email2 = "";
        private String telephone = "";

        public void setEmail1(String email1) {
            this.email1 = email1;
        }

        public void setEmail2(String email2) {
            this.email2 = email2;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getEmail1() {
            return email1;
        }

        public String getEmail2() {
            return email2;
        }

        public String getTelephone() {
            return telephone;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getMiddleName() {
            return middleName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getUniversity() {
            return university;
        }

        public String getUniversityYear() {
            return universityYear;
        }

        public String getFaculty() {
            return faculty;
        }

        public String getUniversityGradYear() {
            return universityGradYear;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setMiddleName(String middleName) {
            this.middleName = middleName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public void setUniversity(String university) {
            this.university = university;
        }

        public void setUniversityYear(String universityYear) {
            this.universityYear = universityYear;
        }

        public void setFaculty(String faculty) {
            this.faculty = faculty;
        }

        public void setUniversityGradYear(String universityGradYear) {
            this.universityGradYear = universityGradYear;
        }

        
    } 
}
