/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import ua.netcrackerteam.DAO.Entities.Form;
import ua.netcrackerteam.configuration.HibernateUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 *
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
     *эффективность рекламы
     */
 public List getReportAdvertisingEfficiency(){
        Session session = null;
        Query query;        
        List report = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            query = session.createSQLQuery("select description , count(id_form) "+
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
    public List getResultOfInterview(){
        Session session = null;
        Query query;        
        List report = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            query = session.createSQLQuery("select sum(users_on_interview), sum(users_with_form), sum(users_with_set_interview) "+
                                            "from( "+
                                            "select count(distinct id_form) as users_on_interview, 0 as users_with_form, 0 as users_with_set_interview " +
                                            "from interview_res " +
                                            "union all " +
                                            "select 0,  count(id_form) as users_with_form, 0 " +
                                            "from form " +
                                            "union all "+
                                            "select 0, 0, count(id_form) "+
                                            "from form "+
                                            "where id_interview is not null) " );          
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
    
      public List getFormByIdInterview(int idInterview){

          List report = new LinkedList();

          beginTransaction();
          List listOfParam = new ArrayList();
          listOfParam.add(idInterview);
          String getFormQuery = "from Form where to_char(idInterview) = to_char(:param0)";
          report = super.<Long>executeListGetQuery(getFormQuery, listOfParam);
          commitTransaction();
          /*report.add(new Object[]{"1 " + idInterview, "Фамилия1", "Имя1", "ВУЗ1111111111111111111111111111111111111111111111111111", "Телефон1"});
            report.add(new Object[]{"2 " + idInterview, "Фамилия1", "Имя1", "ВУЗ1111111111111111111111111111111111111111111111111111", "Телефон1"});*/
       
       
       return report;
       
    }
      
      
      public List getResult(){
          List reportData = new ArrayList(5);
          
          reportData.add(new Object[] {"Дата", "34", "30", "30", "0"});
          reportData.add(new Object[] {"Дата", "34", "30", "25", "5"});
          reportData.add(new Object[] {"Дата", "6", "30", "20", "10"});
          reportData.add(new Object[] {"Дата", "34", "30", "20", "10"});
          reportData.add(new Object[] {"Дата", "43", "30", "1", "29"});
          
          return reportData;
      }
      
      public <T> List<T> getUnit(int idInstitute, int idFaculty, int queryType){
          List<T> report = new ArrayList<T>();
          /*List reportData = new ArrayList(5);

          
          Institute institute1 = new Institute();
          institute1.setInstituteId(1);
          institute1.setName("Институт 1");
          reportData.add(institute1);
          Institute institute2 = new Institute();
          institute2.setInstituteId(2);
          institute2.setName("Институт 2");
          reportData.add(institute2);
          Institute institute3 = new Institute();
          institute3.setInstituteId(3);
          institute3.setName("Институт 3");
          reportData.add(institute3);
          Institute institute4 = new Institute();
          institute4.setInstituteId(4);
          institute4.setName("Институт 4");         
          reportData.add(institute4); 
          Institute institute5 = new Institute();
          institute5.setInstituteId(5);
          institute5.setName("Институт 5");
          reportData.add(institute5);*/
          beginTransaction();
          String getUnit = "";
          if (queryType == 0) { //все институты
              getUnit = "from Institute";
              report = super.<T>executeListGetQuery(getUnit);
          }
          else if (queryType == 1) { //факультеты по институту
              getUnit = "from Faculty where to_char(institute) = to_char(:param0)";
              List listOfParams = new ArrayList();
              listOfParams.add(idInstitute);
              report = super.<T>executeListGetQuery(getUnit, listOfParams);
          }
          else if (queryType == 2) { //кафедры по Факультету
              getUnit = "from Cathedra where to_char(faculty) = to_char(:param0)";
              List listOfParams = new ArrayList();
              listOfParams.add(idFaculty);
              report = super.<T>executeListGetQuery(getUnit, listOfParams);
          }
          commitTransaction();

          
          return report;
      }


      
      public List getFormByIdInstitute(int idInstitute){
          List reportData = new ArrayList();
          List<Form> listOfForms = new ArrayList<Form>();

          beginTransaction();
          List listOfParam = new ArrayList();
          listOfParam.add(idInstitute);
          String getFormQuery = "from Form where to_char(cathedra.faculty.institute) = to_char(:param0)";
          listOfForms = super.<Form>executeListGetQuery(getFormQuery, listOfParam);
          commitTransaction();

          for(Form currForm:listOfForms) {
              reportData.add(new Object[] {currForm.getIdForm(), currForm.getFirstName(), currForm.getLastName(), currForm.getUser().getEmail(), "1111-22"});
          }
                     
           //reportData.add(new Object[] {"12" + idInstitute, "Фамилия 1", "Имя 1",  "Фамилия@mail.ru", "1111-11"});
           //reportData.add(new Object[] {"12" + idInstitute, "Фамилия 2", "Имя 2",  "Фамилия@mail.ru", "2222-22"});
           
           return reportData;
      }
      
      public List<Integer> getCourses(){
          List<Integer> reportData = new ArrayList();
          
          /*reportData.add(new Integer(1));
          reportData.add(new Integer(2));
          reportData.add(new Integer(3));
          reportData.add(new Integer(4));
          reportData.add(new Integer(5));*/
          beginTransaction();
          String getCoursesQuery = "select distinct f.instituteYear from Form f";
          reportData = super.<Integer>executeListGetQuery(getCoursesQuery);
          commitTransaction();
           
          return reportData;
      }
      
      public List getFormByCourse(int course){
           /*List reportData = new ArrayList(2);
                     
           reportData.add(new Object[] {"12" + course, "Фамилия 1", "Имя 1", "Фамилия@mail.ru", "1111-11"});
           reportData.add(new Object[] {"12" + course, "Фамилия 2", "Имя 2", "Фамилия@mail.ru", "2222-22"});


           
           return reportData;*/
          List reportData = new ArrayList();
          List<Form> listOfForms = new ArrayList<Form>();

          beginTransaction();
          List listOfParam = new ArrayList();
          listOfParam.add(course);
          String getFormQuery = "from Form where to_char(instituteYear) = to_char(:param0)";
          listOfForms = super.<Form>executeListGetQuery(getFormQuery, listOfParam);
          commitTransaction();

          for(Form currForm:listOfForms) {
              reportData.add(new Object[] {currForm.getIdForm(), currForm.getFirstName(), currForm.getLastName(), currForm.getUser().getEmail(), "1111-22"});
          }

          //reportData.add(new Object[] {"12" + idInstitute, "Фамилия 1", "Имя 1",  "Фамилия@mail.ru", "1111-11"});
          //reportData.add(new Object[] {"12" + idInstitute, "Фамилия 2", "Имя 2",  "Фамилия@mail.ru", "2222-22"});

          return reportData;
      }

    public static void main(String[] args) {

        List allInst = new DAOReport().getUnit(0,0,0);
        List allFacultyOfInst = new DAOReport().getUnit(1, 0, 1);
        List adllCathedrasOfFaculty = new DAOReport().getUnit(1, 1, 2);
        List courses = new DAOReport().getCourses();
        List form1 = new DAOReport().getFormByCourse(6);

    }
      

}
