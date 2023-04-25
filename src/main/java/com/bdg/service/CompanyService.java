package com.bdg.service;

import com.bdg.model.CompanyMod;
import com.bdg.persistent.CompanyPer;
import com.bdg.persistent.TripPer;
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
    public CompanyMod getBy(int id) {
        checkId(id);

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            CompanyPer companyPer = session.get(CompanyPer.class, id);
            if (companyPer == null) {
                transaction.commit();
                return null;
            }

            CompanyMod companyMod = new CompanyMod();
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
    public Set<CompanyMod> getAll() {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            TypedQuery<CompanyPer> query = session.createQuery("FROM CompanyPer", CompanyPer.class);

            List<CompanyPer> companiesPerlist = query.getResultList();
            if (companiesPerlist.isEmpty()) {
                transaction.commit();
                return null;
            }

            Set<CompanyMod> companiesModSet = getCompaniesModSetFrom(companiesPerlist);

            transaction.commit();
            return companiesModSet;
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }


    @Override
    public Set<CompanyMod> get(int offset, int perPage, String sort) {
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

            TypedQuery<CompanyPer> query = session.createQuery("FROM CompanyPer order by " + sort);
            query.setFirstResult(offset);
            query.setMaxResults(perPage);

            List<CompanyPer> companiesPerList = query.getResultList();

            if (companiesPerList.isEmpty()) {
                transaction.commit();
                return null;
            }

            Set<CompanyMod> companiesModSet = getCompaniesModSetFrom(companiesPerList);

            transaction.commit();
            return companiesModSet;
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }


    @Override
    public CompanyMod save(CompanyMod item) {
        checkNull(item);

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            CompanyPer companyPer = new CompanyPer();
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
    public boolean updateBy(int id, CompanyMod item) {
        checkId(id);
        checkNull(item);

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            CompanyPer companyPer = session.get(CompanyPer.class, id);
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

            CompanyPer companyPer = session.get(CompanyPer.class, id);

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


    private Set<CompanyMod> getCompaniesModSetFrom(List<CompanyPer> companiesPerList) {
        if (companiesPerList == null) {
            throw new NullPointerException("Passed null value as 'companiesPerList': ");
        }

        Set<CompanyMod> companiesModSet = new LinkedHashSet<>(companiesPerList.size());

        for (CompanyPer tempCompanyPerPer : companiesPerList) {
            CompanyMod tempCompanyModMod = new CompanyMod();

            tempCompanyModMod.setId(tempCompanyPerPer.getId());
            tempCompanyModMod.setName(tempCompanyPerPer.getName());
            tempCompanyModMod.setFoundDate(tempCompanyPerPer.getFoundDate());

            companiesModSet.add(tempCompanyModMod);
        }
        return companiesModSet;
    }


    private boolean existsTripBy(int companyId) {
        checkId(companyId);

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            String hql = "select t from TripPer as t where t.company = :companyId";
            TypedQuery<TripPer> query = session.createQuery(hql);
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