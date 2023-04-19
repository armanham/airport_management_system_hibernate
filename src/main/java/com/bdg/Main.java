package com.bdg;

import com.bdg.hibernate.HibernateUtil;
import org.hibernate.SessionFactory;

public class Main {

    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();


        System.out.println("Hello world!");
    }
}