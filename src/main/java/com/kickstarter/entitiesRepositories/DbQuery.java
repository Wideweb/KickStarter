package com.kickstarter.entitiesRepositories;

import com.kickstarter.extentions.Action1T;
import com.kickstarter.extentions.Function1T;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional
public class DbQuery {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public void tryExecute(Action1T<Session> action){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            action.run(session);
            tx.commit();
        }catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public Object tryGetResult(Function1T<Object, Session> function){
        Object result = null;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            result = function.getResult(session);
            tx.commit();
        }catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

        return result;
    }
}
