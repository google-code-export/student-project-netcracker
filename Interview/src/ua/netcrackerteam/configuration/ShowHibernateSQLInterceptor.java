package ua.netcrackerteam.configuration;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * @author krygin
 */
public class ShowHibernateSQLInterceptor {
    //аннотированный метод
    @AroundInvoke
    public Object showSQL(InvocationContext ictx) throws Exception {
        //включение логирования SQL запросов
        Logger sqlLogger = Logger.getLogger("org.hibernate.SQL");
        sqlLogger.setLevel(Level.DEBUG);
        //Включение логирования параметров запросов
        Logger descLogger = Logger.getLogger("org.hibernate.type.descriptor.sql.BasicBinder");
        descLogger.setLevel(Level.TRACE);
        //Выполнение аннотированного метода сервиса
        Object res = ictx.proceed();
        //Выключение логирования SQL
        sqlLogger = Logger.getLogger("org.hibernate.SQL");
        sqlLogger.setLevel(Level.INFO);
        //Выключение логирования параметров
        descLogger = Logger.getLogger("org.hibernate.type.descriptor.sql.BasicBinder");
        descLogger.setLevel(Level.INFO);
        return res;
    }
}
