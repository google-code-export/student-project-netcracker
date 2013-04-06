/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import ua.netcrackerteam.DAO.Entities.*;
import ua.netcrackerteam.configuration.HibernateUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * @author Test class
 */
public class DAOReport extends DAOCoreObject {

    public List getReportDynamicsOfIncreaseStudents() {

        Session session = null;
        Query query;
        List report = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            query = session.createSQLQuery("select to_char(start_date, 'DD/MM/YYYY HH24:MI'), max_number, count(form.id_form) as form_number, max_number - count(form.id_form) as free_number " +
                    "from interview left join form on interview.id_interview = form.id_interview " +
                    "where interview.id_interview <> 0 " +
                    "group by start_date, max_number " +
                    " order by start_date desc");
            report = query.list();

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return report;
    }


    /**
     * эффективность рекламы
     */
    public List getReportAdvertisingEfficiency() {
        Session session = null;
        Query query;
        List report = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            query = session.createSQLQuery("select description , count(id_form) " +
                    "from advert_category left join advert " +
                    "on advert_category.id_advert_category = advert.id_advert_category " +
                    "group by description" +
                    " order by count(id_form) desc");
            report = query.list();

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return report;
    }

    /*общие итоги (сколько анкет зарегистрировано, сколько человек записалось на собеседования, сколько человек прошло собеседование)*/
    public List getResultOfInterview() {
        Session session = null;
        Query query;
        List report = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            query = session.createSQLQuery("select sum(users_on_interview), sum(users_with_form), sum(users_with_set_interview) " +
                    "from( " +
                    "select count(distinct id_form) as users_on_interview, 0 as users_with_form, 0 as users_with_set_interview " +
                    "from interview_res " +
                    "union all " +
                    "select 0,  count(id_form) as users_with_form, 0 " +
                    "from form " +
                    "union all " +
                    "select 0, 0, count(id_form) " +
                    "from form " +
                    "where id_interview is not null) ");
            report = query.list();

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return report;
    }

    public List getFormByIdInterview(int idInterview) {

        List<Form> formsList = new ArrayList<Form>();
        List<Object> report = new LinkedList<Object>();

        beginTransaction();
        List listOfParam = new ArrayList();
        listOfParam.add(idInterview);
        String getFormQuery = "from Form where to_char(interview) = to_char(:param0)";
        formsList = super.<Form>executeListGetQuery(getFormQuery, listOfParam);

        List listOfContactCategoryParams = new ArrayList();
        List listOfTelephoneQueryParams = new ArrayList();
        for (Form currForm : formsList) {
            listOfContactCategoryParams.add("cellphone1");
            String getContactCategory = "from ContactCategory where category = :param0";
            ContactCategory cellphoneCategory = super.<ContactCategory>executeSingleGetQuery(getContactCategory, listOfContactCategoryParams);
            listOfTelephoneQueryParams.add(currForm);
            listOfTelephoneQueryParams.add(cellphoneCategory);
            String getTelephoneQuery = "from Contact where form = :param0 and contactCategory = :param1";
            Contact cellphoneContact = super.<Contact>executeSingleGetQuery(getTelephoneQuery, listOfTelephoneQueryParams);
            report.add(new Object[]{currForm.getInterview().getInterviwerNumber(), currForm.getFirstName(), currForm.getLastName(), currForm.getCathedra().getFaculty().getInstitute(), cellphoneContact.getInfo()});
            listOfContactCategoryParams.clear();
            listOfTelephoneQueryParams.clear();
        }
        commitTransaction();

        return report;

    }


    public List getResult() {
        List reportData = new ArrayList(5);

        reportData.add(new Object[]{"Дата", "34", "30", "30", "0"});
        reportData.add(new Object[]{"Дата", "34", "30", "25", "5"});
        reportData.add(new Object[]{"Дата", "6", "30", "20", "10"});
        reportData.add(new Object[]{"Дата", "34", "30", "20", "10"});
        reportData.add(new Object[]{"Дата", "43", "30", "1", "29"});

        return reportData;
    }

    public <T> List<T> getUnit(int idInstitute, int idFaculty, int queryType) {
        List<T> report = new ArrayList<T>();
        beginTransaction();
        String getUnit = "";
        if (queryType == 0) { //все институты
            getUnit = "from Institute";
            report = super.<T>executeListGetQuery(getUnit);
        } else if (queryType == 1) { //факультеты по институту
            getUnit = "from Faculty where to_char(institute) = to_char(:param0)";
            List listOfParams = new ArrayList();
            listOfParams.add(idInstitute);
            report = super.<T>executeListGetQuery(getUnit, listOfParams);
        } else if (queryType == 2) { //кафедры по Факультету
            getUnit = "from Cathedra where to_char(faculty) = to_char(:param0)";
            List listOfParams = new ArrayList();
            listOfParams.add(idFaculty);
            report = super.<T>executeListGetQuery(getUnit, listOfParams);
        }
        commitTransaction();


        return report;
    }


    public List getFormByIdInstitute(int idInstitute) {
        List reportData = new ArrayList();
        List<Form> listOfForms = new ArrayList<Form>();

        beginTransaction();
        List listOfParam = new ArrayList();
        listOfParam.add(idInstitute);
        String getFormQuery = "from Form where to_char(cathedra.faculty.institute) = to_char(:param0)";
        listOfForms = super.<Form>executeListGetQuery(getFormQuery, listOfParam);
        commitTransaction();

        for (Form currForm : listOfForms) {
            reportData.add(new Object[]{currForm.getIdForm(), currForm.getFirstName(), currForm.getLastName(), currForm.getUser().getEmail(), "1111-22"});
        }
        return reportData;
    }

    public List<Integer> getCourses() {
        List<Integer> reportData = new ArrayList();
        beginTransaction();
        String getCoursesQuery = "select distinct f.instituteYear from Form f";
        reportData = super.<Integer>executeListGetQuery(getCoursesQuery);
        commitTransaction();

        return reportData;
    }

    public List<Form> getFormByCourse(int course) {

        List reportData = new ArrayList();
        List<Form> listOfForms = new ArrayList<Form>();

        beginTransaction();
        List listOfParam = new ArrayList();
        listOfParam.add(course);
        String getFormQuery = "from Form where to_char(instituteYear) = to_char(:param0)";
        listOfForms = super.<Form>executeListGetQuery(getFormQuery, listOfParam);
        commitTransaction();

        return listOfForms;
    }

    public List getFormDataByCourse(int course) {

        List reportData = new ArrayList();
        List<Form> reportForms = new ArrayList();
        DAOReport currDAOReport = new DAOReport();
        reportForms = currDAOReport.getFormByCourse(course);
        for (Form currForm : reportForms) {
            reportData.add(new Object[]{currForm.getIdForm(), currForm.getFirstName(), currForm.getLastName(), currForm.getUser().getEmail(), "1111-22"});
        }
        return reportData;

    }


    public List getAmountByCourse() {
        List reportData = new ArrayList();
        DAOReport currDAOReport = new DAOReport();
        DAOHRImpl currDAOHr = new DAOHRImpl();
        List<Integer> listOfCourses = currDAOReport.getCourses();
        for (Integer currCourse : listOfCourses) {
            int allForms = 0;
            int epsent = 0;
            List<Form> listOfFormByCourse = currDAOReport.getFormByCourse(currCourse);
            for (Form currForm : listOfFormByCourse) {
                List<InterviewRes> resultOfCurrForm = currDAOHr.getAllStudentsMarks(currForm.getIdForm());
                if (resultOfCurrForm.isEmpty()) {
                    epsent++;
                }
                allForms++;
            }
            reportData.add(new Object[] {"Курс " + String.valueOf(currCourse), "Всего пришло " + String.valueOf(allForms-epsent), "Отсутствовало " + String.valueOf(epsent), "Всего было записано "+ String.valueOf(allForms)});
        }

        return reportData;
    }

    public List getAmountByInstitute() {

        List reportData = new ArrayList();
        DAOReport currDAOReport = new DAOReport();
        DAOHRImpl currDAOHr = new DAOHRImpl();
        List<Institute> listOfInstitutes = currDAOReport.getUnit(0,0,0);
        for (Institute currInstitute : listOfInstitutes) {
            int allForms = 0;
            int epsent = 0;
            List<Form> listOfFormByInstitute = currDAOReport.getFormByIdInstitute(currInstitute.getInstituteId());
            for (Form currForm : listOfFormByInstitute) {
                List<InterviewRes> resultOfCurrForm = currDAOHr.getAllStudentsMarks(currForm.getIdForm());
                if (resultOfCurrForm.isEmpty()) {
                    epsent++;
                }
                allForms++;
            }
            reportData.add(new Object[] {currInstitute.getName(), "Всего пришло " + String.valueOf(allForms-epsent), "Отсутствовало " + String.valueOf(epsent), "Всего было записано "+ String.valueOf(allForms)});
        }

        return reportData;
    }

    public List getAmountByFaculty() {
        List reportData = new ArrayList(3);
        reportData.add(new Object[]{"Институт ", "Факультет", "Пришедшие", "Не пришедшие", "Всего"});
        reportData.add(new Object[]{"Институт ", "Факультет", "Пришедшие", "Не пришедшие", "Всего"});
        reportData.add(new Object[]{"Институт ", "Факультет", "Пришедшие", "Не пришедшие", "Всего"});
        return reportData;
    }

    public List getAmountByCathedra() {
        List reportData = new ArrayList(3);
        reportData.add(new Object[]{"Институт ", "Факультет", "Кафедра", "Пришедшие", "Не пришедшие", "Всего"});
        reportData.add(new Object[]{"Институт ", "Факультет", "Кафедра", "Пришедшие", "Не пришедшие", "Всего"});
        reportData.add(new Object[]{"Институт ", "Факультет", "Кафедра", "Пришедшие", "Не пришедшие", "Всего"});
        return reportData;
    }


    /*public List getAmountByCourseOrUnit(int param) {
        List reportData = new ArrayList();
        DAOReport currDAOReport = new DAOReport();
        DAOHRImpl currDAOHr = new DAOHRImpl();
        if (param == 0) {           //get data by course
            List<Integer> listOfCourses = currDAOReport.getCourses();
            for (Integer currCourse : listOfCourses) {
                int allForms = 0;
                int epsent = 0;
                List<Form> listOfFormByCourse = currDAOReport.getFormByCourse(currCourse);
                for (Form currForm : listOfFormByCourse) {
                    List<InterviewRes> resultOfCurrForm = currDAOHr.getAllStudentsMarks(currForm.getIdForm());
                    if (resultOfCurrForm.isEmpty()) {
                        epsent++;
                    }
                    allForms++;
                }
                reportData.add(new Object[]{"Курс " + String.valueOf(currCourse), "Всего пришло " + String.valueOf(allForms - epsent), "Отсутствовало " + String.valueOf(epsent), "Всего было записано " + String.valueOf(allForms)});
            }

        }
        else if (param == 1)  {     //get data by Institute
            List<Integer> listOfInstitutes = currDAOReport.getUnit(0,0,0);
            for (Integer currInstitute : listOfInstitutes) {
                int allForms = 0;
                int epsent = 0;
                List<Form> listOfFormByCourse = currDAOReport.getFormByCourse(currCourse);
                for (Form currForm : listOfFormByCourse) {
                    List<InterviewRes> resultOfCurrForm = currDAOHr.getAllStudentsMarks(currForm.getIdForm());
                    if (resultOfCurrForm.isEmpty()) {
                        epsent++;
                    }
                    allForms++;
                }
                reportData.add(new Object[]{"Курс " + String.valueOf(currCourse), "Всего пришло " + String.valueOf(allForms - epsent), "Отсутствовало " + String.valueOf(epsent), "Всего было записано " + String.valueOf(allForms)});
            }

        }


    }*/



    public static void main(String[] args) {

        /*List allInst = new DAOReport().getUnit(0,0,0);
        List allFacultyOfInst = new DAOReport().getUnit(1, 0, 1);
        List adllCathedrasOfFaculty = new DAOReport().getUnit(1, 1, 2);
        List courses = new DAOReport().getCourses();
        List form1 = new DAOReport().getFormByCourse(6);*/

        List l1 = new DAOReport().getAmountByCourse();
    }


}
