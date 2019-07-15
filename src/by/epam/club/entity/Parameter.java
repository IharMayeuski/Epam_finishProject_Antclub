package by.epam.club.entity;
/**
 *Class of Parameters, Attributes and Messages
 *
 * @author Maeuski Igor
 * @version 1.0
 */
public class Parameter {
    /**
     * parameters
     */
    public static final String RESOURCE_MESSAGES ="resource.messages";
    public static final String RESOURCE_CONFIG = "resource.config";
    public static final String LOGIN_PARAM = "login";
    public static final String PASSWORD_PARAM = "password";
    public static final String PASSWORD_PARAM1 = "password1";
    public static final String PASSWORD_PARAM2 = "password2";
    public static final String ROLE_PARAM = "role";
    public static final String UNBANNED_PARAM = "not banned";
    public static final String UNDELETED_PARAM ="not deleted";
    public static final String BANNED_PARAM ="banned";
    public static final String PARAM_NAME_COMMAND = "command";
    public static final String PROFILE_PARAM = "profile";
    public static final String ARTICLE_PARAM = "article";
    public static final String TYPE_PARAM = "type";
    public static final String ID_USER_PARAM ="id_user";
    public static final String ID_NEWS_PARAM = "id_news";
    public static final String POSITIVE_RATING_PARAM = "positiveRating";
    public static final String NEGATIVE_RATING_PARAM = "negativeRating";
    public static final String LIKE_PARAM ="like";
    public static final String DISLIKE_PARAM ="dislike";
    public static final String APPLICATION_JSON ="application/json";
    public static final String SUCCESS_PARAM ="success";
    public static final String NO_PARAM ="no";
    public static final String TYPE_ID = "typeId";
    public static final String ALL_USERS_PARAM ="ALL_users";
    public static final String DELETED_USERS_PARAM ="Deleted_Users";
    public static final String BANNED_USERS_PARAM ="Banned_Users";
    public static final String ALL_TYPES_PARAM ="ALL_types";
    public static final String DISLAKER_PARAM = "dislaker";
    public static final String LIKER_PARAM = "liker";
    public static final String USER_PARAM = "user";
    public static final String LETTER_FROM_PARAM = "letter_from";
    public static final String LETTER_TO_PARAM = "letter_to";
    public static final String GUEST_PARAM = "guest";
    public static final String ADMIN_PARAM = "admin";
    public static final String LOCAL_PARAM = "local";
    public static final String LOCALE_PARAM = "locale";
    public static final String ERROR_PARAM = "error";
    public static final String DELETED_PARAM ="deleted";
    public static final String USER_ID_PARAM ="userId";
    public static final String ARTICLE_ID_PARAM = "articleId";
    public static final String USER_EMAIL_PARAM ="userEmail";
    public static final String FILE_PARAM ="file";
    public static final String DATE_REGISTRATION_PARAM ="date_registration";
    public static final String DATE_ACTIVITY_PARAM ="date_activity";
    public static final String ACCOUNT_DELETED_PARAM ="account_deleted";
    public static final String EUROPE_MINSK_PARAM ="Europe/Minsk";
    public static final String ENCODING_PARAM ="encoding";
    public static final String EMPTY_STRING_PARAM = "";
    public static final String OPENING_ANGLE_BRACKETS_PARAM = "<";
    public static final String CLOSING_ANGLE_BRACKETS_PARAM = ">";
    public static final String EN_PARAM = "en";
    public static final String RUS_PARAM = "rus";
    public static final String PATH_PARAM = "path";
    public static final String EMAIL_PARAM = "email";
    public static final String IMAGE_PARAM = "image";
    public static final String ARTICLES_PARAM = "articles";
    public static final String COMMENT_PARAM = "comment";
    public static final String PICTURE_PARAM = "picture";
    public static final String LINK_ID_PARAM = "link_id";
    public static final String ID_PARAM = "id";
    public static final String UNKNOWN_PARAM = "unknown";
    public static final String FIRSTNAME_PARAM = "firstname";
    public static final String FAMILYNAME_PARAM = "familyname";
    public static final String USER_LOGIN_PARAM = "userLogin";
    public static final String FILE_NAME_PARAM ="fileName";
    public static final String USER_IS_EMPTY_PARAM = "user is empty";
    public static final String SERVICE_EXCEPTION_PARAM = "Service Exception";
    public static final String SEARCH_PARAM = "search";
    public static final String FIND_USER_PARAM = "findUser";
    public static final String FIND_USER_ID_PARAM = "find_user_id";
    public static final String REGISTRATION_PARAM ="registration";
    public static final String UPDATE_ALL_IS_OK_PARAM = "All_is_ok";
    public static final String LETTER_DELETED_FOR_BOTH_SIDE_MESSAGE = "delete.letter";
    public static final String NEW_PHOTO_PARAM = "new_photo";
    public static final String NEW_PARAM = "new";
    public static final String TYPES_PARAM = "types";
    public static final String TITLE_PARAM ="title";
    public static final String TEXT_PARAM ="text";
    public static final String COMMENT_ID = "commentId";
    public static final String LETTER_ID_PARAM ="letter_id";
    public static final String DATE_PARAM ="date";

    /**
     * the page paths
     */
    public static final String INDEX_PAGE ="path.page.index";
    public static final String DEFAULT_PAGE_FORVARD ="path.page.default";
    public static final String FIND_USER_PAGE_FORVARD = "path.page.search.user";
    public static final String REGISTRATION_PAGE_FORVARD ="path.page.registration";
    public static final String USER_PROFILE_PAGE_FORVARD = "path.page.user.profile";
    public static final String NEW_TYPE_PAGE_FORVARD = "path.page.newtype";
    public static final String UPDATE_COMMENT_PAGE_FORVARD = "path.page.updatecomment";
    public static final String UPDATE_ARTICLE_PAGE_FORVARD ="path.page.updatearticle";
    public static final String NEW_ARTICLE_PAGE_FORVARD = "path.page.newarticle";
    public static final String NEW_COMMENT_PAGE_FORVARD = "path.page.newcomment";
    public static final String ADMIN_VIEW_PAGE_FORVARD = "path.page.admin.control";
    public static final String NEW_ARTICLE_PICTURE_PAGE_FORVARD = "path.page.newarticlepic";
    public static final String LOOK_ARTICLE_PAGE_FORVARD = "path.page.look.article";
    public static final String DEFAULT_PAGE_REDIRECT ="command.page.default";
    public static final String REGISTRATION_PAGE_REDIRECT ="command.page.registration";
    public static final String FIND_USER_PAGE_REDIRECT = "command.search.user";

    /**
     * messages
     */
    public static final String UNKNOWN_MISTAKE_MESSAGE = "unknown_mistake";
    public static final String DELETE_ACCOUNT_MESSAGE = "message.deletedaccount";
    public static final String WRONG_DATA_MESSAGE = "message.wrongdata";
    public static final String ACCOUNT_DELETED_MESSAGE = "message.accountdeleted";
    public static final String DATA_OK_MESSAGE = "message.dataok";
    public static final String SERVICE_EXCEPTION_MESSAGE ="message.serviceexception";
    public static final String WRONG_ACTION_MESSAGE = "message.wrong-action";
    public static final String CONTROLLER_EXCEPTION_MESSAGE ="Controller Exception, ";
    public static final String USER_PASSWORD_UNCORRECT_MESSAGE ="user.password.uncorrect";
    public static final String USER_EMAIL_EMPTY_MESSAGE ="user.email.empty";
    public static final String USER_LOGIN_PASSWORD_CREDENTIAL ="user.login.password.credential";
    public static final String EMPTY_MESSAGE ="user.login.empty";
    public static final String USER_LOGIN_PASSWORD_UNCORRECT_MESSAGE ="user.login.password.uncorrect";
    public static final String USER_LOGIN_DELETED_MESSAGE ="user.login.deleted";
    public static final String USER_LOGIN_MESSAGE ="user.login";
    public static final String USER_EMAIL_DELETED_MESSAGE = "user.email.deleted";
    public static final String USER_EMAIL_MESSAGE ="user.email";
    public static final String UNKNOWN_ENTITY_TYPE_MESSAGE = "entity_type";
    public static final String SQL_EXCEPTION_MESSAGE = "SQL_exception";
    public static final String NO_USER_MESSAGE ="no_user";
    public static final String VERY_LONG_PARAMETER_MESSAGE ="parameter.long";
    public static final String NOTHING_FOR_CHANGING_MESSAGE = "message.nothing.for.changing";
    public static final String WE_HAVE_ANT_IN_DB_MESSAGE ="message.you.have.ant";
    public static final String ILLEGAL_ACTION_ON_PAGE_MESSAGE = "Illegal action on the page ";
    public static final String EXCEPTION_IN_PREPARED_STATEMENT_MESSAGE = "Exception in close PreparedStatement";
    public static final String NEW_PASSWORD_SENT_ON_EMAIL_MESSAGE ="password.send";
    public static final String LETTER_SENT_MESSAGE = "letter.send";
    public static final String SOMETHING_BAD_WITH_SEND_MESSAGE ="Something bad with sending message ";
    public static final String WE_HAVE_ARTICLE_LIKE_THIS_MESSAGE = "article.exception.we_have_alreade";
    public static final String SQL_TRANSACTION_EXCEPTION_MESSAGE ="sql_transaction_exception";
    public static final String WE_HAVE_THIS_LIKER_MESSAGE = "we_have_this_liker_message";

    private Parameter(){}
}
