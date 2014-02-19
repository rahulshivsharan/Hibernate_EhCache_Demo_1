/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hibernate.main;

import com.hibernate.pojo.Customer;
import com.hibernate.testing.HibernateUtil;
import org.hibernate.CacheMode;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author rs69421
 */
public class MainTesting {

    private static void getCustomers() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from com.hibernate.pojo.Customer")
                .setCacheable(true).setCacheRegion("cache_two");
        query.list();
        query.list();
        query.list();

    }

    private static void getCustomer() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Customer obj = null;
        obj = (Customer) session.get(Customer.class, new Integer(2));
        session.close();
        session = HibernateUtil.getSessionFactory().openSession();
        obj = (Customer) session.get(Customer.class, new Integer(2));
        session.close();
        session = HibernateUtil.getSessionFactory().openSession();
        obj = (Customer) session.get(Customer.class, new Integer(2));
        session.close();
        System.out.println(obj);
    }

    private static void getMyQuery() throws Exception {
        Session session = null;
        Query q = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            q = session.getNamedQuery("findCustomerNames").setCacheable(true).setCacheRegion("cache_two");
            q.list();
            q.list();
            q.list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }

    private static void getCustomerFromSession() throws Exception {
        Session[] session = new Session[]{
            HibernateUtil.getSessionFactory().openSession(),
            HibernateUtil.getSessionFactory().openSession(),
            HibernateUtil.getSessionFactory().openSession()
        };

        Customer obj1 = (Customer) session[0].get(Customer.class, new Integer(2));
        session[0].close();
        Customer obj2 = (Customer) session[1].get(Customer.class, new Integer(2));
        session[1].close();
        Customer obj3 = (Customer) session[2].get(Customer.class, new Integer(2));
        session[2].close();        
    }

    private static void loadCustomer() throws Exception {
        Session[] sessions = new Session[]{
            HibernateUtil.getSessionFactory().openSession(),
            HibernateUtil.getSessionFactory().openSession(),
            HibernateUtil.getSessionFactory().openSession()
        };
        
        
        Customer cus1 = (Customer) sessions[0].load(Customer.class, new Integer(2));
        sessions[0].close();
        Customer cus2 = (Customer) sessions[1].load(Customer.class, new Integer(2));
        sessions[1].close();
        Customer cus3 = (Customer) sessions[2].load(Customer.class, new Integer(2));
        sessions[2].close();
        
        
    }

    public static void main(String[] args) {
        try {
//              getCustomers();
//            getCustomer();
            getCustomerFromSession();
//            getMyQuery();
//                loadCustomer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
