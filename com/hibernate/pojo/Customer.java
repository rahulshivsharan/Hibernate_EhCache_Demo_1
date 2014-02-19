/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hibernate.pojo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author rs69421
 */

@Entity
@Table(name = "customer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "cache_two")
@NamedQuery(query = "select c.customerName from Customer c",name = "findCustomerNames")
public class Customer implements Serializable{   
    
    public Customer(){}
    public Customer(Integer customerId){
        this.customerId = customerId;
    }
    
    public Customer(Integer customerId,String customerName){
        this.customerId = customerId;
        this.customerName = customerName;
    }
    
    @Id
    private Integer customerId;
    private String customerName;

    /**
     * @return the customerId
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    @Override
    public String toString(){
        StringBuffer strb = new StringBuffer();
        strb.append("\n\n CUSTOMER-ID : ")
                .append(this.customerId)
                .append("\n CUSTOMER-NAME : ")
                .append(this.customerName);
        return strb.toString();
    }
    
    @Override
    public int hashCode(){
        return this.customerId * 29;
    }
    
    @Override
    public boolean equals(Object object){
        boolean flag = false;
        
        if(object instanceof Customer){            
            Customer c = (Customer) object;
            flag =  (this.customerId == c.getCustomerId()) ? true : false;
        }
        return flag;
    }   
}
