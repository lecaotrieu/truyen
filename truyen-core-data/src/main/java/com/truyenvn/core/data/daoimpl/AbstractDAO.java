package com.truyenvn.core.data.daoimpl;

import com.truyenvn.core.common.constant.CoreConstant;
import com.truyenvn.core.common.utils.HibernateUtils;
import com.truyenvn.core.data.dao.GenericDAO;
import org.apache.log4j.Logger;
import org.hibernate.*;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AbstractDAO<ID extends Serializable, T> implements GenericDAO<ID, T> {
    private Class<T> persistenceClass;
    private final Logger log = Logger.getLogger(this.getClass());

    public AbstractDAO() {
        this.persistenceClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public String getPersistenceClassName() {
        return persistenceClass.getSimpleName();
    }

    public T update(T entity) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        T result = null;
        try {
            Object object = session.merge(entity);
            result = (T) object;
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            log.error(e.getMessage(), e);
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public ID save(T entity) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        ID result;
        try {
            result = (ID) session.save(entity);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            log.error(e.getMessage(), e);
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public Object[] findByProperty(Map<String, Object> properties, String sortExpression, String sortDirection, Integer offset, Integer limit, String selectClause, String joinClause, String whereClause) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<T> list = new ArrayList<T>();
        Integer totalItem = 0;
        String[] params = new String[0];
        Object[] values = new Object[0];
        if (properties != null && properties.size() > 0) {
            params = new String[properties.size()];
            values = new Object[properties.size()];
            int i = 0;
            for (Map.Entry item : properties.entrySet()) {
                params[i] = (String) item.getKey();
                values[i] = item.getValue();
                i++;
            }
        }
        try {
            StringBuilder sql1 = new StringBuilder("");
            if (selectClause != null) {
                sql1.append(selectClause);
            }
            sql1.append(" FROM " + getPersistenceClassName() + " T ");
            StringBuilder sql2 = new StringBuilder("SELECT COUNT(*) FROM " + getPersistenceClassName() + " WHERE 1=1 ");
            if (joinClause != null) {
                sql1.append(joinClause);
            }
            sql1.append(" WHERE 1=1");
            if (properties != null) {
                for (int i1 = 0; i1 < properties.size(); i1++) {
                    sql1.append(" AND ").append("LOWER(" + params[i1] + ") LIKE '%" + values[i1] + "%'");
                    sql2.append(" AND ").append("LOWER(" + params[i1] + ") LIKE '%" + values[i1] + "%'");
                }
            }
            if (whereClause != null) {
                sql1.append(whereClause);
                sql2.append(whereClause);
            }
            if (sortExpression != null && sortDirection != null) {
                sql1.append(" ORDER BY ").append(sortExpression).append(" " + (sortDirection.equals(CoreConstant.SORT_ASC) ? "asc" : "desc"));
            }
            Query query1 = session.createQuery(sql1.toString());
            if (offset != null && offset >= 0) {
                query1.setFirstResult(offset);
            }
            if (limit != null && limit >= 0) {
                query1.setMaxResults(limit);
            }
            list = query1.list();
            Query query2 = session.createQuery(sql2.toString());
            totalItem = Integer.parseInt(query2.list().get(0).toString());
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            log.error(e.getMessage(), e);
            throw e;
        } finally {
            session.close();
        }
        return new Object[]{totalItem, list};
    }

    public T findById(ID id) {
        T result = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            result = (T) session.get(persistenceClass, id);
            if (result == null) {
                throw new ObjectNotFoundException("NOT FOND" + id, null);
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            log.error(e.getMessage(), e);
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public Integer delete(List<ID> ids) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Integer count = 0;
        try {
            for (ID id : ids) {
                T t = (T) session.get(persistenceClass, id);
                session.delete(t);
                count++;
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            log.error(e.getMessage(), e);
            throw e;
        } finally {
            session.close();
        }
        return count;
    }

    public Integer deleteByParameters(Map<String, Object> parameters) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Integer result;
        String[] params = new String[0];
        Object[] values = new Object[0];
        if (parameters != null && parameters.size() > 0) {
            params = new String[parameters.size()];
            values = new Object[parameters.size()];
            int i = 0;
            for (Map.Entry item : parameters.entrySet()) {
                params[i] = (String) item.getKey();
                values[i] = item.getValue();
                i++;
            }
        }
        try {
            StringBuilder sql = new StringBuilder(" DELETE FROM " + getPersistenceClassName());
            sql.append(" WHERE 1=1");
            if (parameters != null) {
                for (int i = 0; i < parameters.size(); i++) {
                    sql.append(" AND ").append(params[i] + " = :" + i + "");
                }
            }
            Query query = session.createQuery(sql.toString());
            for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, values[i]);
            }
            result = query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            log.error(e.getMessage(), e);
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public T findEqualUnique(String property, Object value) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        T result = null;
        try {
            String sql = "FROM " + getPersistenceClassName() + " model WHERE model." + property + "= :value";
            Query query = session.createQuery(sql);
            query.setParameter("value", value);
            result = (T) query.uniqueResult();
        } catch (HibernateException e) {
            log.error(e.getMessage(), e);
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public T findByParameter(Map<String, Object> parameters) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        T result;
        String[] params = new String[0];
        Object[] values = new Object[0];
        if (parameters != null && parameters.size() > 0) {
            params = new String[parameters.size()];
            values = new Object[parameters.size()];
            int i = 0;
            for (Map.Entry item : parameters.entrySet()) {
                params[i] = (String) item.getKey();
                values[i] = item.getValue();
                i++;
            }
        }
        try {
            StringBuilder sql = new StringBuilder(" FROM " + getPersistenceClassName() + " T ");
            sql.append(" WHERE 1=1");
            if (parameters != null) {
                for (int i = 0; i < parameters.size(); i++) {
                    sql.append(" AND ").append(params[i] + " ='" + values[i] + "'");
                }
            }
            Query query = session.createQuery(sql.toString());
            /*for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(params[i], values[i]);
            }*/
            result = (T) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            log.error(e.getMessage(), e);
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public List<T> findAll() {
        List<T> result = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            String sql = "FROM " + this.getPersistenceClassName() + " ";
            Query query = session.createQuery(sql);
            result = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            log.error(e.getMessage(), e);
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public Integer countByParameter(Map<String, Object> parameters) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Integer count = 0;
        String[] params = new String[0];
        Object[] values = new Object[0];
        if (parameters != null && parameters.size() > 0) {
            params = new String[parameters.size()];
            values = new Object[parameters.size()];
            int i = 0;
            for (Map.Entry item : parameters.entrySet()) {
                params[i] = (String) item.getKey();
                values[i] = item.getValue();
                i++;
            }
        }
        try {
            StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM " + getPersistenceClassName() + " WHERE 1=1 ");
            if (parameters != null) {
                for (int i = 0; i < parameters.size(); i++) {
                    sql.append(" AND ").append(params[i] + " ='" + values[i] + "'");
                }
            }
            Query query = session.createQuery(sql.toString());
            count = Integer.parseInt(query.list().get(0).toString());
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            log.error(e.getMessage(), e);
            throw e;
        } finally {
            session.close();
        }
        return count;
    }
}
