package by.epam.club.dao.impl;

import by.epam.club.dao.userdao.UserDaoImpl;
import by.epam.club.entity.User;
import by.epam.club.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;


public class UserDaoImplTest {
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImplTest.class);
    private UserDaoImpl userDao;
    private User user;

    @BeforeClass
    public void setUp() {
        userDao = new UserDaoImpl();
        user = new User();
    }

//    @Test
//    public void testCheckPositive() {
//        try {
//            Assert.assertEquals(userDao.checkUser("admin", "admin").getLogin(), "admin");
//            LOGGER.info("Test checkUser user is completed successfully");
//        } catch (DaoException e) {
//            LOGGER.info(e);
//        }
//    }
//
//    @Test
//    public void testCheckNegative() {
//        try {
//            Assert.assertNull(userDao.checkUser("admin", "admin2"));
//            LOGGER.info("Test checkUser user negative test is completed successfully");
//        } catch (DaoException e) {
//            LOGGER.info(e);
//        }
//    }

    @Test
    public void testCreateUser() throws DaoException {
        String PASSWORD = "12345";
        String EMAIL = "test2@gmail.com";
        String LOGIN = "test2";
        String DATE = "111111111";
        Assert.assertTrue(userDao.createUser(LOGIN, EMAIL, PASSWORD, DATE));
    }

/*    @Test
    public void testDeleteUser() {
    //    user.setId(ID_FOR_MARKING_DELETING); todo удалить

        try {
            Assert.assertTrue(userDao.markUserDeleted(ID_FOR_MARKING_DELETING));
            LOGGER.info("Test_Mark_deleted_user is completed");
        } catch (DaoException e) {
            LOGGER.info(e);
        }
    }*/

    @Test
    public void testTakeAllUser() {
        try {
            Assert.assertNotNull(userDao.takeAllUser());
            LOGGER.info("Test take all is completed successfully");

        } catch (DaoException e) {
            LOGGER.info(e);
        }
    }

    @Test
    public void testFindUserByLogin() {
        try {
            Assert.assertNotNull(userDao.findUserByLogin("admin"));
            LOGGER.info("Test take all is completed successfully");
        } catch (DaoException e) {
            LOGGER.info(e);
        }
    }

    /*@Test
    public void testMarkUserBannedUnbanned() throws DaoException {
        user.setId(2);
        user.setBanned(Parameter.UNBANNED_PARAM);
        Assert.assertTrue(userDao.markUserBannedUnbanned(user));

        user.setBanned(Parameter.BANNED_PARAM);
        Assert.assertTrue(userDao.markUserBannedUnbanned(user));
    }
*/
    @Test
    public void testMarkUserUndeleted() {
        int ID_FOR_MARKING_DELETING = 2;
        user.setId(ID_FOR_MARKING_DELETING);
        try {
            Assert.assertTrue(userDao.markUserUndeleted(user));
            LOGGER.info("Test_Mark_deleted_user is completed");
        } catch (DaoException e) {
            LOGGER.info(e);
        }
    }

    @Test
    public void testTakeAllUserUndeleted() {
        try {
            Assert.assertNotNull(userDao.takeAllUserUndeleted());
            LOGGER.info("Test take all is completed successfully");
        } catch (DaoException e) {
            LOGGER.info(e);
        }
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId(2);
        try {
            Assert.assertTrue(userDao.updateUser(user, "test", "test@test.ru", "test"));
            LOGGER.info("Test take all is completed successfully");
        } catch (DaoException e) {
            LOGGER.info(e);
        }
    }

    @Test
    public void testTakeAllUser1() {
        try {
            Assert.assertNotNull(userDao.takeAllUser());
            LOGGER.info("Test take all is completed successfully");

        } catch (DaoException e) {
            e.printStackTrace();
            LOGGER.info(e);
        }
    }

    @Test
    public void testTakeAllUserUndeleted1() {
        try {
            Assert.assertNotNull(userDao.takeAllUserUndeleted());
            LOGGER.info("Test take all is completed successfully");

        } catch (DaoException e) {
            e.printStackTrace();
            LOGGER.info(e);
        }
    }

    @Test
    public void testFindUserByLogin1() {
    }

    @Test
    public void testNewPassword() throws DaoException {
        userDao.newPassword("maevskii@list.ru");

    }

    @Test
    void test() { // FIXME: 6/20/2019  Удалить данные тесты
        Map<String, Integer> map = new HashMap<>();
        String[] strArray = {"ss", "ss", "ww", "w", "ss", "w"};

        List<String> strs = Arrays.asList(strArray);
        Collections.sort(strs);
        Set<String> str = new HashSet<>(strs);
        for (String s:str) {
            int count = 0;
            for (int j = 0; j < strs.size(); j++) {
                if (s.equals(strs.get(j))) {
                    count++;
                }
                map.put(s, count);
            }

        }
        System.out.println(map);
    }

    @Test
    void test2() {
        Map<String, Integer> map = new HashMap<>();
        String[] strArray = {"ss", "ss", "ww", "w", "ss", "w"};

        List<String> strs = Arrays.asList(strArray);
        Collections.sort(strs);
        System.out.println(strs);
        int count=1;
        for (int i=1; i<strs.size(); i++){
            if (strs.get(i-1).equals(strs.get(i))){
                count++;
            }else {
                map.put(strs.get(i-1), count);
                count=1;
            }
        }
        System.out.println(map.toString());
    }


    @Test
    void test3() {
        C c = new C();
        c.print(new A("s", "s1"));
        c.print(new B("Bs", "Bs1"));
        C cc = new A();
        System.out.println(cc.getClass().getName()+"!");
    }

    class A extends C{
        String s;
        String s1;

        public A(){}

        public A(String s, String s1){
            this.s = s;
            this.s1 = s1;
        }
    }

    class B extends C {
        String s;
        String s1;
        String wwww;

        public B(String s, String s1) {
            this.s = s;
            this.s1 = s1;
        }
    }

    class C extends Z {
        public void print(Object object) {
            System.out.println(object.getClass().getName());
        }
    }
    class Z {

    }

}