package ua.netcrackerteam.controller;

import org.hibernate.Session;
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

    public static List<String> GetUniversityList() {

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


    public static List<String> GetFacultyListByInstitute(String currInstitute) {

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

    public static List<String> GetCathedraListByFaculty(String currFaculty, String currInstitute) {

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

    public static void InsertNewBranch(String branchName) {

        Session session = null;
        org.hibernate.Query re = null;
        List selectedBranch = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            re = session.createQuery("from Branch where upper(name) ='" + branchName.toUpperCase() + "'");
            selectedBranch = re.list();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        if ((selectedBranch == null) || (selectedBranch.isEmpty())) {
            /*try {
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
        return result;*/
    }

    public static void main(String args[]) {

        List<String> newList = GetUniversityList();

        List<String> newListFaculty = GetFacultyListByInstitute("Одеський національний політехнічний університет");

        List<String> newListCathedra = GetCathedraListByFaculty("Інститут комп.ютерних систем", "Одеський національний політехнічний університет");

    }

}
