package com.bdg.service;

import com.bdg.converter.model_to_persistent.ModToPerCompany;
import com.bdg.converter.persistent_to_model.PerToModCompany;
import com.bdg.hibernate.HibernateUtil;
import com.bdg.model.CompanyMod;
import com.bdg.persistent.CompanyPer;
import com.bdg.persistent.TripPer;
import com.bdg.repository.CompanyRepository;
import com.bdg.validator.Validator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;


public class CompanyService implements CompanyRepository {

    private static final ModToPerCompany MOD_TO_PER = new ModToPerCompany();
    private static final PerToModCompany PER_TO_MOD = new PerToModCompany();


    @Override
    public CompanyMod getBy(int id) {
        Validator.checkId(id);

        Transaction transaction = null;
        try(Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();

            CompanyPer companyPer = session.get(CompanyPer.class, id);
            if (companyPer == null) {
                transaction.commit();
                return null;
            }

            transaction.commit();
            return PER_TO_MOD.getModelFrom(companyPer);
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }


    @Override
    public Set<CompanyMod> getAll() {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();

            TypedQuery<CompanyPer> query = session.createQuery("FROM CompanyPer", CompanyPer.class);

            List<CompanyPer> companiesPerlist = query.getResultList();
            if (companiesPerlist.isEmpty()) {
                transaction.commit();
                return null;
            }

            transaction.commit();
            return (Set<CompanyMod>) PER_TO_MOD.getModelListFrom(companiesPerlist);
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
        try(Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();

            TypedQuery<CompanyPer> query = session.createQuery("FROM CompanyPer order by " + sort);
            query.setFirstResult(offset);
            query.setMaxResults(perPage);

            List<CompanyPer> companiesPerList = query.getResultList();

            if (companiesPerList.isEmpty()) {
                transaction.commit();
                return null;
            }

            transaction.commit();
            return (Set<CompanyMod>) PER_TO_MOD.getModelListFrom(companiesPerList);
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }


    @Override
    public CompanyMod save(CompanyMod item) {
        Validator.checkNull(item);

        Transaction transaction = null;
        try(Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();

            CompanyPer companyPer = MOD_TO_PER.getPersistentFrom(item);
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
        Validator.checkId(id);
        Validator.checkNull(item);

        Transaction transaction = null;
        try(Session session = HibernateUtil.getSession()) {
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
        Validator.checkId(id);

        if (existsTripBy(id)) {
            System.out.println("First remove company by " + id + " in trip table: ");
            return false;
        }

        Transaction transaction = null;
        try(Session session = HibernateUtil.getSession()) {
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


    private boolean existsTripBy(int companyId) {
        Validator.checkId(companyId);

        Transaction transaction = null;
        try(Session session = HibernateUtil.getSession()) {
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