package com.truyenvn.core.utils;

import com.truyenvn.core.service.impl.*;

public class SingletonServiceUtil {
    private static AuthorServiceImpl authorService = null;
    private static BookServiceImpl bookService = null;
    private static CategoryServiceImpl categoryService = null;
    private static ChapterServiceImpl chapterService = null;
    private static CommentServiceImpl commentService = null;
    private static EvaluateServiceImpl evaluateService = null;
    private static RoleServiceImpl roleService = null;
    private static UserServiceImpl userService = null;

    public static AuthorServiceImpl getAuthorService() {
        if (authorService == null) {
            synchronized (AuthorServiceImpl.class) {
                authorService = new AuthorServiceImpl();
            }
        }
        return authorService;
    }

    public static BookServiceImpl getBookService() {
        if (bookService == null) {
            synchronized (BookServiceImpl.class) {
                bookService = new BookServiceImpl();
            }
        }
        return bookService;
    }

    public static CategoryServiceImpl getCategoryService() {
        if (categoryService == null) {
            synchronized (CategoryServiceImpl.class) {
                categoryService = new CategoryServiceImpl();
            }
        }
        return categoryService;
    }

    public static ChapterServiceImpl getChapterService() {
        if (chapterService == null) {
            synchronized (ChapterServiceImpl.class) {
                chapterService = new ChapterServiceImpl();
            }
        }
        return chapterService;
    }

    public static CommentServiceImpl getCommentService() {
        if (commentService == null) {
            synchronized (CommentServiceImpl.class) {
                commentService = new CommentServiceImpl();
            }
        }
        return commentService;
    }

    public static EvaluateServiceImpl getEvaluateService() {
        if (evaluateService == null) {
            synchronized (EvaluateServiceImpl.class) {
                evaluateService = new EvaluateServiceImpl();
            }
        }
        return evaluateService;
    }

    public static RoleServiceImpl getRoleService() {
        if (roleService == null) {

                roleService = new RoleServiceImpl();
        }
        return roleService;
    }

    public static UserServiceImpl getUserService() {
        if (userService == null) {
            synchronized (UserServiceImpl.class) {
                userService = new UserServiceImpl();
            }
        }
        return userService;
    }
}
