/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hibernate.util;




import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


public class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY = createSessionFactory();

    private static SessionFactory createSessionFactory() {        
        SessionFactory sessionFactory = null;        
        try {            
             
            sessionFactory = new AnnotationConfiguration()
                    .configure("conf/hibernate.cfg.xml").buildSessionFactory();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
    
    public static SessionFactory getSessionFactory(){
        return SESSION_FACTORY;
    }
    
    public static void shutdown(){
        SESSION_FACTORY.close();
    }
}
