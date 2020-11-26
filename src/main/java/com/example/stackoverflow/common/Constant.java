package com.example.stackoverflow.common;

public class Constant {
    // Status when create, update in service
    public static final int SUCCESS = 1;
    public static final int FAIL = 0;

    // Status of question (status_of_question.status_of_question_id)
    public static final int QUESTION_STATUS_OPEN = 1;
    public static final int QUESTION_STATUS_CLOSED = 2;
    public static final int QUESTION_STATUS_DELETED = 3;

    // Role (role.role_id)
    public static final int ROLE_GUEST = 1;
    public static final int ROLE_MEMBER = 2;
    public static final int ROLE_MODERATOR = 3;
    public static final int ROLE_ADMIN = 4;
}
