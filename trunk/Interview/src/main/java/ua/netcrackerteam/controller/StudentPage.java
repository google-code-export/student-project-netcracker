package ua.netcrackerteam.controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.netcrackerteam.DAO.*;
import ua.netcrackerteam.configuration.HibernateUtil;
import ua.netcrackerteam.configuration.ShowHibernateSQLInterceptor;

import javax.interceptor.Interceptors;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Student controller class for using in View
 *
 * @author Filipenko, Zhokha Maksym
 */
public class StudentPage {
        
    @Interceptors(ShowHibernateSQLInterceptor.class)
    //public static List<String> getUniversityList() {
    public static List<Institute> getUniversityList() {

        Session session = null;
        org.hibernate.Query re = null;
        List instituteList = null;
        List<String> result = new ArrayList<String>();
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            re = session.createQuery("from Institute");
            instituteList = re.list();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

       /* for (Object currObj : instituteList) {
            Institute currInst = (Institute) currObj;
            result.add(currInst.getName());
        }*/
        return instituteList;
    }

    @Interceptors(ShowHibernateSQLInterceptor.class)
    public static List<Faculty> getFacultyListByInstitute(Institute currInstitute) {

        Session session = null;
        org.hibernate.Query re = null;
        List facultyList = null;
        List selectedFaculty = null;
        List selectedInstitute = null;
        List<String> result = new ArrayList<String>();
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            re = session.createQuery("from Institute where upper(name) ='" + currInstitute.getName().toUpperCase() + "'");
            selectedInstitute = re.list();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        if ((!selectedInstitute.isEmpty()) && (selectedInstitute.size() == 1)) {
            Institute newInst = (Institute) selectedInstitute.get(0);
            try {
                Locale.setDefault(Locale.ENGLISH);
                session = HibernateUtil.getSessionFactory().getCurrentSession();
                session.beginTransaction();
                re = session.createQuery("from Faculty where institute ='" + newInst.getInstituteId() + "'");
                selectedFaculty = re.list();
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }

 /*           for (Object currObj : selectedFaculty) {
                Faculty currFaculty = (Faculty) currObj;
                result.add(currFaculty.getName());
            }
*/
        }
        return selectedFaculty;
    }

    @Interceptors(ShowHibernateSQLInterceptor.class)
    public static List<Cathedra> getCathedraListByFaculty(Faculty currFaculty, Institute currInstitute) {

        Session session = null;
        org.hibernate.Query re = null;
        List cathedraList = null;
        List selectedFaculty = null;
        List selectedCathedra = null;
        List<String> result = new ArrayList<String>();
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            re = session.createQuery("from Faculty as facul where upper(facul.name) = '" + currFaculty.getName().toUpperCase() + "'" + " and  upper(facul.institute.name) = '" + currInstitute.getName().toUpperCase() + "'" );
            selectedFaculty = re.list();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        if ((!selectedFaculty.isEmpty()) && (selectedFaculty.size() == 1)) {
            Faculty newFaculty = (Faculty) selectedFaculty.get(0);
            try {
                Locale.setDefault(Locale.ENGLISH);
                session = HibernateUtil.getSessionFactory().getCurrentSession();
                session.beginTransaction();
                re = session.createQuery("from Cathedra where faculty ='" + newFaculty.getIdFaculty() + "'");
                selectedCathedra = re.list();
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
/*
            for (Object currObj : selectedCathedra) {
                Cathedra currCathedra = (Cathedra) currObj;
                result.add(currCathedra.getName());
            }*/

        }
        return selectedCathedra;
    }

    @Interceptors(ShowHibernateSQLInterceptor.class)
    public static boolean verificationOfTheExistenceSomthing (String tableForVerification, String inWhichColumn, String someThing) {
        Session session = null;
        org.hibernate.Query re = null;
        List selectedBranch = null;

        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            re = session.createQuery("from " + tableForVerification + " where upper(" + inWhichColumn + ") ='" + someThing.toUpperCase() + "'");
            selectedBranch = re.list();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        if ((selectedBranch == null) || (selectedBranch.isEmpty())) {
            return true;
        }
        else {
            return false;
        }
    }

    @Interceptors(ShowHibernateSQLInterceptor.class)
    public static List<Object> searchSomething (String tableForSearch, String inWhichColumn, String someThing) {
        Session session = null;
        org.hibernate.Query re = null;
        List selectedSomething = null;

        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            re = session.createQuery("from " + tableForSearch + " where upper(" + inWhichColumn + ") ='" + someThing.toUpperCase() + "'");
            selectedSomething = re.list();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return selectedSomething;
    }

    @Interceptors(ShowHibernateSQLInterceptor.class)
    public static void insertNewBranch(String[] branchNames) {

        Session session = null;
        org.hibernate.Query re = null;
        Transaction transaction = null;
        for (String currBranch:branchNames) {
            if (verificationOfTheExistenceSomthing("Branch", "name" ,currBranch)) {
                try {
                    Locale.setDefault(Locale.ENGLISH);
                    session = HibernateUtil.getSessionFactory().getCurrentSession();
                    transaction = session.beginTransaction();
                    Branch newBranch = new Branch();
                    newBranch.setName(currBranch);
                    newBranch.setDescription(currBranch);
                    session.save(newBranch);
                    transaction.commit();
                } catch (Exception e) {
                    System.out.println(e);
                } finally {
                    if (session != null && session.isOpen()) {
                        session.close();
                    }
                }
            }
        }
    }

    @Interceptors(ShowHibernateSQLInterceptor.class)
    public static void insertNewContacts(String contactType) {

        Session session = null;
        org.hibernate.Query re = null;
        Transaction transaction = null;

        if (verificationOfTheExistenceSomthing("ContactCategory","category" ,contactType)) {
            try {
                Locale.setDefault(Locale.ENGLISH);
                session = HibernateUtil.getSessionFactory().getCurrentSession();
                transaction = session.beginTransaction();
                ContactCategory newContactType = new ContactCategory();
                newContactType.setCategory(contactType);
                session.save(newContactType);
                transaction.commit();
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
        }
    }

    public static void main(String args[]) {

        //List<String> newList = getUniversityList();

       // List<String> newListFaculty = getFacultyListByInstitute("Одеський національний політехнічний університет");

        //List<String> newListCathedra = getCathedraListByFaculty("Інститут комп.ютерних систем", "Одеський національний політехнічний університет");

//        String[] listOfBranch = new String[3];
//        listOfBranch[0] = "1C";
//        listOfBranch[1] = "2C";
//        listOfBranch[2] = "3C";
//        insertNewBranch(listOfBranch);
//
//        String newContactType = "skype";
//        insertNewContacts(newContactType);
//
//        StudentData newStudentData = new StudentData();
//        newStudentData.setStudentFirstName("Василий");
//        newStudentData.setStudentLastName("Familiya");
//        newStudentData.setStudentMiddleName("MiddleName");
//        newStudentData.setStudentExperienceProjects("some projects");
//        newStudentData.setStudentReasonOffer("???????");
//        newStudentData.setStudentSelfAdditionalInformation("I'm a best of the best");
        
        
        StudentData std = StudentPage.getStudentDataByUserName("ThirdLogin");
        System.out.println(std);    
        

    }

    @Interceptors(ShowHibernateSQLInterceptor.class)
    public static Form addNewForm(StudentData newStudentData, Integer userID) {

        Form newForm = new Form();
        newForm.setFirstName        (newStudentData.getStudentFirstName());
        newForm.setMiddleName       (newStudentData.getStudentMiddleName());
        newForm.setLastName         (newStudentData.getStudentLastName());
        newForm.setExecProject      (newStudentData.getStudentExperienceProjects());
        newForm.setReason           (newStudentData.getStudentReasonOffer());
        newForm.setExtraInfo        (newStudentData.getStudentSelfAdditionalInformation());
        newForm.setInstituteYear    (newStudentData.getStudentInstituteCourse());
        newForm.setInterestStudy    (newStudentData.getStudentInterestStudy());
        newForm.setInterestWork     (newStudentData.getStudentInterestWork());
        newForm.setInterestSoftware (newStudentData.getStudentInterestDevelopment());
        newForm.setInterestTelecom  (newStudentData.getStudentInterestOther());
        /*if (!newStudentData.getStudentInstitute().equals("")) {
            List<Object> listOfInstitute = searchSomething("INSTITUTE", "NAME", newStudentData.getStudentInstitute());
            if (!(listOfInstitute.size() == 0)) {
                Institute selectedInstitute = (Institute) listOfInstitute.get(0);
                newForm.setInstitute(selectedInstitute);
            }
        }*/
        return newForm;

    }
    
    /**
     * Returns StudentData object, needed to fill form in UI,
     * by transforming Form object to StudentData object
     * @param UserName  user name (login)
     * @return object, needed to fill form using Vaadin
     */
    @Interceptors(ShowHibernateSQLInterceptor.class)
    public static StudentData getStudentDataByUserName(String UserName) {
        StudentData std = new StudentData();        
        Form form = new DAOStudentImpl().getFormByUserName(UserName);
        if (form != null)
        {
            std.setIdForm(form.getIdForm());
//            std.setStudentCPlusPlusMark();           //this methods not applicable yet

//            std.setStudentEmailFirst();
//            std.setStudentEmailSecond();
//            std.setStudentEnglishReadMark();
//            std.setStudentEnglishSpeakMark();
//            std.setStudentEnglishWriteMark();
            std.setStudentExperienceProjects(form.getExecProject());
            std.setStudentCathedra(form.getCathedra());
            std.setStudentFaculty(form.getCathedra().getFaculty());
            std.setStudentInstitute(form.getCathedra().getFaculty().getInstitute());
            std.setStudentFirstName(form.getFirstName());
            std.setStudentLastName(form.getLastName());
            
            Set adverts = form.getAdverts();
            Iterator iterator = adverts.iterator();            
            Advert advert = (Advert) iterator.next();
            std.setStudentHowHearAboutCentre(advert.getAdvertCategory().getDescription());
            
            std.setStudentInstituteCourse(form.getInstituteYear());
            std.setStudentInstituteGradYear(form.getInstituteGradYear());
            std.setStudentInterestDevelopment(form.getInterestSoftware());
            std.setStudentInterestOther(form.getInterestOther());
            std.setStudentInterestDevelopment(form.getInterestSoftware());
        }       
        return std;
        
    }

}
