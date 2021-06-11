package com.truyenvn.core.daoimpl;

import com.truyenvn.core.common.utils.HibernateUtils;
import com.truyenvn.core.dao.CategoryDAO;
import com.truyenvn.core.data.daoimpl.AbstractDAO;
import com.truyenvn.core.persistence.entity.CategoryEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

public class CategoryDAOImpl extends AbstractDAO<Integer, CategoryEntity> implements CategoryDAO {
    private final Logger log = Logger.getLogger(this.getClass());

    public List<Object[]> findByBook(Integer bookId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Object[]> result = null;
        try {
            String sql = "FROM CategoryEntity c JOIN c.bookEntities b WHERE b.bookId = :bookId";
            Query query = session.createQuery(sql);
            query.setParameter("bookId",bookId);
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
}
