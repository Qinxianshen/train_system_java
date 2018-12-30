package com.qin.train.dao.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.qin.train.dao.TrainStationDao;
import com.qin.train.pojo.TrainStation;
import com.qin.train.pojo.Vo.TrainQuery;



@Repository("trainStationDao")
public class TrainStationDaoImpl implements TrainStationDao {
    @Resource
    private SessionFactory sessionFactory;

    public List<TrainQuery> GetTrainStation(int initialStationId, int destinationStationId){
        Session session = null;
        session = sessionFactory.openSession();
        List<TrainQuery> trainQueryList = session.createQuery("select u.trainId as trainId, u.stationId as initialStationId, a.stationId as destinationStationId, a.distance - u.distance as distance, u.leaveTime as arriveTime, a.arriveTime as leaveTime from TrainStation u, TrainStation a where u.stationId = " + initialStationId + " and a.stationId = " + destinationStationId + " and u.trainId = a.trainId and u.distance < a.distance").setResultTransformer(Transformers.aliasToBean(TrainQuery.class)).list();
        session.close();
        return trainQueryList;
    }

    public List<Integer> getTrainSum() {
        Session session = null;
        session = sessionFactory.openSession();
        List<Long> list = session.createQuery("select count(ST.trainId) from TrainStation ST group by ST.trainId").list();
        session.close();
        List<Integer> list1= new ArrayList<Integer>();
        for (long l :list) {
            list1.add(new Integer((int)l));
        }
        return list1;
    }

    public List<TrainStation> getRecordSum() {
        Session session = null;
        session = sessionFactory.openSession();
        List<TrainStation> list = session.createQuery("from TrainStation ST Order by ST.trainId,ST.distance").list();
        session.close();
        return list;
    }

    public List<Integer> getAllTrainId() {
        Session session = null;
        session = sessionFactory.openSession();
        List<Integer> list = session.createQuery("select distinct ST.trainId from TrainStation ST order by ST.trainId").list();
        session.close();
        return list;
    }

    public TrainQuery GetTrainStationByDistance(int stationId1, int stationId2, double distance){
        Session session = null;
        session = sessionFactory.openSession();
        System.out.println("test....:" + Double.toString(distance));
        List<TrainQuery> trainStationList = session.createQuery("select u.trainId as trainId, u.stationId as initialStationId, a.stationId as destinationStationId, a.distance - u.distance as distance, u.leaveTime as arriveTime, a.arriveTime as leaveTime from TrainStation u, TrainStation a where u.stationId = " + stationId1 + " and a.stationId = " + stationId2 + " and u.trainId = a.trainId and u.distance < a.distance and a.distance - u.distance - " + distance + "<=0.0000001").setResultTransformer(Transformers.aliasToBean(TrainQuery.class)).list();
        session.close();
        return trainStationList.get(0);
    }

    public TrainQuery GetTransferTrain(int initialStationId, int destinationStationId, int trainId){
        Session session = null;
        session = sessionFactory.openSession();
        List<TrainQuery> trainStationList = session.createQuery("select u.trainId as trainId, u.stationId as initialStationId, a.stationId as destinationStationId, a.distance - u.distance as distance, u.leaveTime as arriveTime, a.arriveTime as leaveTime from TrainStation u, TrainStation a where u.stationId = " + initialStationId + " and a.stationId = " + destinationStationId + " and u.trainId = a.trainId and u.trainId = " + trainId + " and u.distance < a.distance").setResultTransformer(Transformers.aliasToBean(TrainQuery.class)).list();
        session.close();
        return trainStationList.get(0);
    }

    public TrainQuery GetTransferTrainByDistance(int initialStationId, int destinationStationId, int trainId, double distance){
        Session session = null;
        session = sessionFactory.openSession();
        System.out.println("test:" + Double.toString(distance));
        List<TrainQuery> trainStationList = session.createQuery("select u.trainId as trainId, u.stationId as initialStationId, a.stationId as destinationStationId, a.distance - u.distance as distance, u.leaveTime as arriveTime, a.arriveTime as leaveTime from TrainStation u, TrainStation a where u.stationId = " + initialStationId + " and a.stationId = " + destinationStationId + " and u.trainId = a.trainId and u.trainId = " + trainId + " and u.distance < a.distance and a.distance - u.distance - " + distance + "<=0.0000001").setResultTransformer(Transformers.aliasToBean(TrainQuery.class)).list();
        session.close();
        if (trainStationList.size() <= 0){
            return null;
        }
        return trainStationList.get(0);
    }

    public List<Double> GetDistance(int stationId1, int stationId2){
        Session session = null;
        session = sessionFactory.openSession();
        List<Double> stationList = session.createQuery("select a.distance - u.distance as distance from TrainStation u, TrainStation a where u.stationId = " + stationId1 + " and a.stationId = " + stationId2 + " and u.trainId = a.trainId and u.distance < a.distance").list();
        System.out.println(stationList);
        session.close();
        return stationList;
    }

    public void AddTrainStation(int trainId, int stationId, double distance, Date arriveTime, Date leaveTime){
        Session session = null;
        session = sessionFactory.openSession();
        Transaction tran = session.beginTransaction();
        TrainStation trainStation = new TrainStation();
        trainStation.setTrainId(trainId);
        trainStation.setStationId(stationId);
        trainStation.setDistance(distance);
        trainStation.setArriveTime(arriveTime);
        trainStation.setLeaveTime(leaveTime);
        session.save(trainStation);
        tran.commit();
        session.close();
    }

    public void DeleteTrainStation(int trainId, int stationId){
        Session session = null;
        session = sessionFactory.openSession();
        Transaction tran = session.beginTransaction();
        String hql = "Delete FROM TrainStation u Where u.trainId = " + trainId + " and u.stationId = " + stationId;
        Query q = session.createQuery(hql);
        q.executeUpdate();
        tran.commit();
    }
}