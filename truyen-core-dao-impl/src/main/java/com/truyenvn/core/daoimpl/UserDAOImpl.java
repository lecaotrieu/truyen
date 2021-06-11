package com.truyenvn.core.daoimpl;

import com.truyenvn.core.common.utils.HibernateUtils;
import com.truyenvn.core.dao.UserDAO;
import com.truyenvn.core.data.daoimpl.AbstractDAO;
import com.truyenvn.core.persistence.entity.UserEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDAOImpl extends AbstractDAO<Integer, UserEntity> implements UserDAO {
    private final Logger logger = Logger.getLogger(this.getClass());

    public UserEntity userLogin(String name, String password) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        UserEntity userEntity = null;
        try {
            String sql = "FROM UserEntity ue WHERE ue.name =:name AND ue.password =:password ";
            Query query = session.createQuery(sql);
            query.setParameter("name", name);
            query.setParameter("password", password);

            if (query.list().size() > 0) {
                userEntity = (UserEntity) query.uniqueResult();
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            session.close();
        }
        return userEntity;
    }

    public int updateAvatar(Integer userId, String avatarLocation) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int result = -1;
        try {
            String sql = "UPDATE UserEntity ue set ue.avatar = :avatar where ue.userId = :userId";
            Query query = session.createQuery(sql);
            query.setParameter("avatar",avatarLocation);
            query.setParameter("userId",userId);
            result = query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            logger.error(e.getMessage(), e);
        } finally {
            session.close();
        }
        return result;
    }
}
