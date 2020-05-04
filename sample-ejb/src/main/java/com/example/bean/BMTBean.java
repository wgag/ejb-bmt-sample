package com.example.bean;

import java.sql.Connection;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class BMTBean {

    private static Logger logger = Logger.getLogger(BMTBean.class.getName());

    public void transaction() {
        UserTransaction txn = null;
        Connection conn = null;

        try {
            InitialContext ic = new InitialContext();
            txn = (UserTransaction) ic.lookup("java:comp/UserTransaction");
            DataSource ds = (DataSource) ic.lookup("java:comp/DefaultDataSource");

            logger.info("begin transaction");
            txn.begin();

            logger.info("get connection");
            conn = ds.getConnection();

            logger.info("close connection");
            conn.close();

            logger.info("commit connection");
            txn.commit();

        } catch (Exception e) {
            try {
                if (txn != null) {
                    txn.rollback();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            throw new RuntimeException(e);
        }
    }
}
