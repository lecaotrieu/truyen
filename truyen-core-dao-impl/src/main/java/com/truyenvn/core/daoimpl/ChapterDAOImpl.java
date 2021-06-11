package com.truyenvn.core.daoimpl;

import com.truyenvn.core.common.utils.HibernateUtils;
import com.truyenvn.core.dao.ChapterDAO;
import com.truyenvn.core.data.daoimpl.AbstractDAO;
import com.truyenvn.core.persistence.entity.BookEntity;
import com.truyenvn.core.persistence.entity.ChapterEntity;
import com.truyenvn.core.persistence.entity.UserEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ChapterDAOImpl extends AbstractDAO<Integer, ChapterEntity> implements ChapterDAO {
    private final Logger logger = Logger.getLogger(this.getClass());
    public Integer findPreviousChapter(Integer bookId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Integer chapter = 0;
        try {
            String sql = "SELECT chapter FROM ChapterEntity ue WHERE ue.bookEntity.bookId =:bookId ORDER BY chapter desc";
            Query query = session.createQuery(sql);
            query.setParameter("bookId", bookId);
            query.setMaxResults(1);
            if (query.list().size() > 0) {
                chapter = (Integer) query.uniqueResult();
            }
            chapter = chapter+1;
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            session.close();
        }
        return chapter;
    }
}
