package com.truyenvn.core.daoimpl;

import com.truyenvn.core.dao.BookDAO;
import com.truyenvn.core.data.daoimpl.AbstractDAO;
import com.truyenvn.core.persistence.entity.BookEntity;
import org.apache.log4j.Logger;

public class BookDAOImpl extends AbstractDAO<Integer, BookEntity> implements BookDAO {
    private final Logger log = Logger.getLogger(this.getClass());
}
