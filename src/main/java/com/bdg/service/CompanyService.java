package com.bdg.service;

import com.bdg.model.Company;
import com.bdg.repository.CompanyRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class CompanyService implements CompanyRepository {

    private Session session;


    @Override
    public Company getBy(int id) {
        checkId(id);

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            com.bdg.persistent.Company companyPer = session.get(com.bdg.persistent.Company.class, id);
            if (companyPer == null) {
                transaction.commit();
                return null;
            }

            Company companyMod = new Company();
            companyMod.setId(companyPer.getId());
            companyMod.setName(companyPer.getName());
            companyMod.setFoundDate(companyPer.getFoundDate());

            transaction.commit();
            return companyMod;
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }


    @Override
    public Set<Company> getAll() {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            TypedQuery<com.bdg.persistent.Company> query = session.createQuery("FROM Company", com.bdg.persistent.Company.class);

            List<com.bdg.persistent.Company> companiesPerlist = query.getResultList();
            if (companiesPerlist.isEmpty()) {
                transaction.commit();
                return null;
            }

            Set<Company> companiesModSet = getCompaniesModSetFrom(companiesPerlist);

            transaction.commit();
            return companiesModSet;
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }


    @Override
    public Set<Company> get(int offset, int perPage, String sort) {
        if (offset <= 0 || perPage <= 0) {
            throw new IllegalArgumentException("Passed non-positive value as 'offset' or 'perPage': ");
        }
        if (sort == null || sort.isEmpty()) {
            throw new IllegalArgumentException("Passed null or empty value as 'sort': ");
        }
        if (!sort.equals("id") && !sort.equals("name") && !sort.equals("found_date")) {
            throw new IllegalArgumentException("Parameter 'sort' must be 'id' or 'name' or 'found_date': ");
        }

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            TypedQuery<com.bdg.persistent.Company> query = session.createQuery("FROM Company order by " + sort);
            query.setFirstResult(offset);
            query.setMaxResults(perPage);

            List<com.bdg.persistent.Company> companiesPerList = query.getResultList();

            if (companiesPerList.isEmpty()) {
                transaction.commit();
                return null;
            }

            Set<Company> companiesModSet = getCompaniesModSetFrom(companiesPerList);

            transaction.commit();
            return companiesModSet;
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }


    @Override
    public Company save(Company item) {
        checkNull(item);

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            com.bdg.persistent.Company companyPer = new com.bdg.persistent.Company();
            companyPer.setName(item.getName());
            companyPer.setFoundDate(item.getFoundDate());
            session.save(companyPer);
            item.setId(companyPer.getId());

            transaction.commit();
            return item;
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean updateBy(int id, Company item) {
        checkId(id);
        checkNull(item);

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            com.bdg.persistent.Company companyPer = session.get(com.bdg.persistent.Company.class, id);
            if (companyPer == null) {
                transaction.commit();
                return false;
            }

            companyPer.setName(item.getName());
            companyPer.setFoundDate(item.getFoundDate());

            transaction.commit();
            return true;
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean deleteBy(int id) {
        checkId(id);

        if (existsTripBy(id)) {
            System.out.println("First remove company by " + id + " in trip table: ");
            return false;
        }

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            com.bdg.persistent.Company companyPer = session.get(com.bdg.persistent.Company.class, id);

            if (companyPer == null) {
                transaction.commit();
                return false;
            }

            session.delete(companyPer);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }


    public void setSession(Session session) {
        checkNull(session);
        this.session = session;
    }


    private void checkId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("'id' must be a positive number: ");
        }
    }


    private void checkNull(Object item) {
        if (item == null) {
            throw new NullPointerException("Passed null value as 'item': ");
        }
    }


    private Set<Company> getCompaniesModSetFrom(List<com.bdg.persistent.Company> companiesPerList) {
        if (companiesPerList == null) {
            throw new NullPointerException("Passed null value as 'companiesPerList': ");
        }

        Set<Company> companiesModSet = new LinkedHashSet<>(companiesPerList.size());

        for (com.bdg.persistent.Company tempCompanyPer : companiesPerList) {
            Company tempCompanyMod = new Company();

            tempCompanyMod.setId(tempCompanyPer.getId());
            tempCompanyMod.setName(tempCompanyPer.getName());
            tempCompanyMod.setFoundDate(tempCompanyPer.getFoundDate());

            companiesModSet.add(tempCompanyMod);
        }
        return companiesModSet;
    }


    private boolean existsTripBy(int companyId) {
        checkId(companyId);

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            String hql = "select t from Trip as t where t.company = :companyId";
            TypedQuery<com.bdg.persistent.Trip> query = session.createQuery(hql);
            query.setParameter("companyId", companyId);

            transaction.commit();
            return !query.getResultList().isEmpty();
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }
}