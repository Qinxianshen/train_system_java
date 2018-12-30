package com.qin.train.dao.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.qin.train.dao.TrainDao;
import com.qin.train.pojo.Train;



@Repository("trainDao")
public class TrainDaoImpl implements TrainDao {
    @Resource
    private SessionFactory sessionFactory;

    public List<Train> GetAllTrain(){
        Session session = null;
        session = sessionFactory.openSession();
        List<Train> listTrain = session.createQuery("select u.trainId as trainId, u.trainName as trainName from Train u").setResultTransformer(Transformers.aliasToBean(Train.class)).list();
        session.close();
        return listTrain;
    }

    public String GetTrainName(int trainId){
        Session session = null;
        session = sessionFactory.openSession();
        List<String> trainList = session.createQuery("select u.trainName as trainName from Train u where u.trainId = "+ trainId).list();
        session.close();
        return trainList.get(0);
    }

    public int GetTrainId(String trainName){
        Session session = null;
        session = sessionFactory.openSession();
        List trainList = session.createQuery("select u.trainId as trainId from Train u where u.trainName = '"+ trainName + "'").list();
        session.close();
        return ((List<Integer>) trainList).get(0).intValue();
    }

    public void AddTrain(String trainName){
        Session session = null;
        session = sessionFactory.openSession();
        Transaction tran = session.beginTransaction();
//        System.out.println(trainName);
        Train train = new Train();
        train.setTrainName(trainName);
        session.save(train);
        tran.commit();
        session.close();
    }

    public void DeleteTrain(String trainName){
        Session session = null;
        session = sessionFactory.openSession();
        Transaction tran = session.beginTransaction();
        String hql = "Delete FROM Train u Where u.trainName = '" + trainName + "'";
        Query q = session.createQuery(hql);
        q.executeUpdate();
        tran.commit();
    }
}