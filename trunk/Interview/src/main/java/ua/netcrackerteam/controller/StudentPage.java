package ua.netcrackerteam.controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.netcrackerteam.DAO.*;
import ua.netcrackerteam.configuration.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Student controller class for using in View
 *
 * @author Filipenko
 */
public class StudentPage {

    public static List<String> getUniversityList() {

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

        for (Object currObj : instituteList) {
            Institute currInst = (Institute) currObj;
            result.add(currInst.getName());
        }
        return result;
    }


    public static List<String> getFacultyListByInstitute(String currInstitute) {

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
            re = session.createQuery("from Institute where upper(name) ='" + currInstitute.toUpperCase() + "'");
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

            for (Object currObj : selectedFaculty) {
                Faculty currFaculty = (Faculty) currObj;
                result.add(currFaculty.getName());
            }

        }
        return result;
    }

    public static List<String> getCathedraListByFaculty(String currFaculty, String currInstitute) {

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
            re = session.createQuery("from Faculty as facul where upper(facul.name) = '" + currFaculty.toUpperCase() + "'" + " and  upper(facul.institute.name) = '" + currInstitute.toUpperCase() + "'" );
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

            for (Object currObj : selectedCathedra) {
                Cathedra currCathedra = (Cathedra) currObj;
                result.add(currCathedra.getName());
            }

        }
        return result;
    }

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

        List<String> newList = getUniversityList();

        List<String> newListFaculty = getFacultyListByInstitute("Одеський національний політехнічний університет");

        List<String> newListCathedra = getCathedraListByFaculty("Інститут комп.ютерних систем", "Одеський національний політехнічний університет");

        String[] listOfBranch = new String[3];
        listOfBranch[0] = "1C";
        listOfBranch[1] = "2C";
        listOfBranch[2] = "3C";
        insertNewBranch(listOfBranch);

        String newContactType = "skype";
        insertNewContacts(newContactType);

    }

}
