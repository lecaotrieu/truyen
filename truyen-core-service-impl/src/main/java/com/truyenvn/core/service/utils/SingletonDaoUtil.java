package com.truyenvn.core.service.utils;

import com.truyenvn.core.daoimpl.*;

public class SingletonDaoUtil {
    private static AuthorDAOImpl authorDAO = null;
    private static BookDAOImpl bookDAO = null;
    private static CategoryDAOImpl categoryDAO = null;
    private static ChapterDAOImpl chapterDAO = null;
    private static CommentDAOImpl commentDAO = null;
    private static EvaluateDAOImpl evaluateDAO = null;
    private static RoleDAOImpl roleDAO = null;
    private static UserDAOImpl userDAO = null;

    public static AuthorDAOImpl getAuthorDAO() {
        if (authorDAO == null) {
            synchronized (AuthorDAOImpl.class) {
                authorDAO = new AuthorDAOImpl();
            }
        }
        return authorDAO;
    }

    public static BookDAOImpl getBookDAO() {
        if (bookDAO == null) {
            synchronized (BookDAOImpl.class) {
                bookDAO = new BookDAOImpl();
            }
        }
        return bookDAO;
    }

    public static CategoryDAOImpl getCategoryDAO() {
        if (categoryDAO == null) {
            synchronized (CategoryDAOImpl.class) {
                categoryDAO = new CategoryDAOImpl();
            }
        }
        return categoryDAO;
    }

    public static ChapterDAOImpl getChapterDAO() {
        if (chapterDAO == null) {
            synchronized (ChapterDAOImpl.class) {
                chapterDAO = new ChapterDAOImpl();
            }
        }
        return chapterDAO;
    }

    public static CommentDAOImpl getCommentDAO() {
        if (commentDAO == null) {
            synchronized (CommentDAOImpl.class) {
                commentDAO = new CommentDAOImpl();
            }
        }
        return commentDAO;
    }

    public static EvaluateDAOImpl getEvaluateDAO() {
        if (evaluateDAO == null) {
            synchronized (EvaluateDAOImpl.class) {
                evaluateDAO = new EvaluateDAOImpl();
            }
        }
        return evaluateDAO;
    }

    public static RoleDAOImpl getRoleDAO() {
        if (roleDAO == null) {

                roleDAO = new RoleDAOImpl();
        }
        return roleDAO;
    }

    public static UserDAOImpl getUserDAO() {
        if (userDAO == null) {
            synchronized (UserDAOImpl.class) {
                userDAO = new UserDAOImpl();
            }
        }
        return userDAO;
    }
}
