/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.DAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Klitna Tetiana
 */
public class DAOReport extends DAOCoreObject {
    
    public List getReportDynamicsOfIncreaseStudents() {
        
           StringBuilder builder = new StringBuilder();
           beginTransaction();
           
            builder.append("select to_char(start_date, 'DD/MM/YYYY HH24:MI'), max_number, count(form.id_form) as form_number, max_number - count(form.id_form) as free_number ");                                        
            builder.append("from interview left join form on interview.id_interview = form.id_interview ");
            builder.append("where interview.id_interview <> 1 ");
            builder.append("group by start_date, max_number ");
            builder.append("order by start_date desc");  
            
          List report = super.executeListGetSQLQuery(builder.toString(), new ArrayList());           
          commitTransaction();
          
          return report;  
    } 
    
 public List getReportAdvertisingEfficiency(){
     
     StringBuilder builder = new StringBuilder();
     beginTransaction();
     
      builder.append("select description , count(id_form) ");
      builder.append("from advert_category left join advert ");
      builder.append("on advert_category.id_advert_category = advert.id_advert_category "); 
      builder.append("group by description "); 
      builder.append("order by count(id_form) desc"); 
      
     List report = super.executeListGetSQLQuery(builder.toString(), new ArrayList());           
     commitTransaction();
          
     return report;  
    }    

    public List getResultOfInterview(){
          
          StringBuilder builder = new StringBuilder();
          beginTransaction();
          
           builder.append("select sum(forms) sum_forms, sum(forms_interview) sum_forms_interview, sum(forms_mark) sum_forms_mark ");
           builder.append("from ");
           builder.append("(select count(id_form) forms,0  forms_interview,0 forms_mark ");
           builder.append("from form ");
           builder.append("union all ");
           builder.append("select 0, count(id_form),0 ");
           builder.append("from form ");
           builder.append("where id_interview is not null and id_interview <> 1 ");
           builder.append("union all ");
           builder.append("select 0, 0, count(distinct id_form) ");
           builder.append("from interview_res) ");

          List report = super.executeListGetSQLQuery(builder.toString(), new ArrayList());           
          commitTransaction();
          
          return report;  
          
    }
    
      public List getFormByIdInterview(int idInterview){
        
        StringBuilder builder = new StringBuilder();
        beginTransaction();
        
         builder.append("select form.id_form, form.last_name, form.first_name, form.middle_name,institute.name ");
         builder.append("from form inner join cathedra on form.id_cathedra = cathedra.id_cathedra "); 
         builder.append("inner join faculty on cathedra.id_faculty = faculty.id_faculty "); 
         builder.append("inner join institute on faculty.id_institute = institute.id_institute ");
         builder.append("where form.id_interview = :param0");
        
        List listOfParams = new ArrayList(1);
        listOfParams.add(idInterview);
        List report = super.executeListGetSQLQuery(builder.toString(), listOfParams);           
        commitTransaction();
          
        return report;           
    }
      
      
      public List getResult(){
            
            StringBuilder builder = new StringBuilder();
            beginTransaction();
            
             builder.append("select to_char(start_date, 'DD/MM/YYYY HH24:MI') start_interview, max_number, ");
             builder.append("count(form.id_form) forms, count(distinct interview_res.id_form) forms_with_mark, ");
             builder.append("count(form.id_form) - count(distinct interview_res.id_form) forms_without_mark  ");                                  
             builder.append("from interview left join form on interview.id_interview = form.id_interview ");
             builder.append("left join interview_res on  form.id_form = interview_res.id_form ");
             builder.append("where interview.id_interview <> 1 "); 
             builder.append("group by start_date, max_number "); 
             builder.append("order by start_date desc ");
            
            List report = super.executeListGetSQLQuery(builder.toString(), new ArrayList());           
            commitTransaction(); 
            
            return report;  
      }
      
      public <T> List<T> getUnit(int idInstitute, int idFaculty, int queryType){
          List<T> report = new ArrayList<T>();
          
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
      
    
       public List getAmountByCourse(){
           
        StringBuilder builder = new StringBuilder();
        beginTransaction();
        
        builder.append("select form.institute_year course, count(distinct interview_res.id_form) forms_with_mark, ");
        builder.append("count(form.id_form)- count(distinct interview_res.id_form)forms_without_mark, ");
        builder.append("count(form.id_form) forms ");
        builder.append("from form  left join interview_res on form.id_form = interview_res.id_form ");
        builder.append("group by form.institute_year");
               
        List report = super.executeListGetSQLQuery(builder.toString(), new ArrayList());           
        commitTransaction();
        
        return report;
       }
       
      public List getAmountByInstitute(){
            
            StringBuilder builder = new StringBuilder();
            beginTransaction();
            
            builder.append("select  institute.name institute, count(distinct interview_res.id_form) forms_with_mark, ");
            builder.append("count(form.id_form)- count(distinct interview_res.id_form)forms_without_mark, ");
            builder.append("count(form.id_form) forms ");
            builder.append("from form inner join cathedra on form.id_cathedra = cathedra.id_cathedra ");
            builder.append("inner join faculty on cathedra.id_faculty = faculty.id_faculty "); 
            builder.append("inner join institute on faculty.id_institute = institute.id_institute "); 
            builder.append("left join interview_res on form.id_form = interview_res.id_form "); 
            builder.append("group by institute.name"); 
            
            List report = super.executeListGetSQLQuery(builder.toString(), new ArrayList());           
            commitTransaction();
          
            return report;
       }
      
      public List getAmountByFaculty(int idInstitute){
       
       StringBuilder builder = new StringBuilder();
       beginTransaction();
       
       builder.append("select  faculty.name faculty, count(distinct interview_res.id_form) forms_with_mark, ");
       builder.append("count(form.id_form)- count(distinct interview_res.id_form)forms_without_mark, ");
       builder.append("count(form.id_form) forms ");
       builder.append("from form inner join cathedra on form.id_cathedra = cathedra.id_cathedra "); 
       builder.append("inner join faculty on cathedra.id_faculty = faculty.id_faculty "); 
       builder.append("inner join institute on faculty.id_institute = institute.id_institute "); 
       builder.append("left join interview_res on form.id_form = interview_res.id_form "); 
       builder.append("where institute.id_institute = :param0 ");
       builder.append("group by faculty.name "); 
       
       List listOfParams = new ArrayList(1);
       listOfParams.add(idInstitute);
       List report = super.executeListGetSQLQuery(builder.toString(), listOfParams);           
       commitTransaction();
          
       return report;
               
      }
      
     public List getAmountByCathedra(int idInstitute){         
              
       StringBuilder builder = new StringBuilder();
       beginTransaction();
       
       builder.append("select  cathedra.name cathedra, count(distinct interview_res.id_form) forms_with_mark, "); 
       builder.append("count(form.id_form)- count(distinct interview_res.id_form)forms_without_mark, ");
       builder.append("count(form.id_form) forms "); 
       builder.append("from form inner join cathedra on form.id_cathedra = cathedra.id_cathedra ");
       builder.append("inner join faculty on cathedra.id_faculty = faculty.id_faculty "); 
       builder.append("inner join institute on faculty.id_institute = institute.id_institute "); 
       builder.append("left join interview_res on form.id_form = interview_res.id_form  ");
       builder.append("where institute.id_institute = :param0 ");     
       builder.append("group by cathedra.name ");
       
       List listOfParams = new ArrayList(1);
       listOfParams.add(idInstitute);      
       List report = super.executeListGetSQLQuery(builder.toString(), listOfParams);           
       commitTransaction();
          
       return report;
      }
     

}
