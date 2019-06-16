/*
package by.epam.club.tool;

public class ServiceExemple {
    private User createUserAccount(User user) throws ServiceException {
        TransactionHelper transaction = new TransactionHelper();
        PaymentDaoImpl paymentDao = new PaymentDaoImpl();
        UserDaoImpl userDao = new UserDaoImpl();
        ProfileDaoImpl profileDao = new ProfileDaoImpl();
        User newUser = null;
        try {
            Profile profile = new Profile();
            Payment payment = new Payment();
            transaction.beginTransaction(userDao, paymentDao);
            userDao.create(user);
            newUser = userDao.findEmail(user.getEmail());
            logger.info("-----------------------------------------------------------");
            logger.info("-------------newUser.getId() ---------------" + newUser.getId());
            logger.info("-----------------------------------------------------------");
            payment.setUserId(newUser.getId());
            payment.setCurrentBalance(NEW_USER_BALANCE);
            logger.info("-----------------------------------------------------------");
            logger.info("-------------newUser.getId() ---------------" + payment.getUserId());
            logger.info("-----------------------------------------------------------");
            paymentDao.createPayment(payment);
            profile.setUserId(newUser.getId());
            profileDao.create(profile);
            transaction.commit();
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException ex) {
                throw new ServiceException(e.getMessage());
            }
            logger.error(e.getMessage());
        } finally {
            try {
                transaction.endTransaction();
            } catch (DaoException e) {
                throw new ServiceException(e.getMessage());
            }
        }
        return newUser;
    }

}
*/
