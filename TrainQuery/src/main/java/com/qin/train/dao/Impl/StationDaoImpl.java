package com.qin.train.dao.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.qin.train.pojo.Station;
import com.qin.train.dao.StationDao;

@Repository("stationDao")
public class StationDaoImpl implements StationDao{
	@Resource
    private SessionFactory sessionFactory;
	
	 public List<Integer> GetAllStationId(){
	        Session session = null;
	        session = sessionFactory.openSession();
	        List<Integer> stationList = session.createQuery("select u.stationId as stationId from Station u").list();
	        session.close();
	        return stationList;
	    }

	    public List<String> GetAllStationName(){
	        Session session = null;
	        session = sessionFactory.openSession();
	        List<String> stationList = session.createQuery("select u.stationName as stationName from Station u").list();
	        session.close();
	        return stationList;
	    }

	    public int GetStationId(String stationName){
	        Session session = null;
	        session = sessionFactory.openSession();
	        System.out.println(stationName);
	        List stationList = session.createQuery("select u.stationId as stationId from Station u where u.stationName = '" + stationName + "'").list();
	        System.out.println(stationList);
	        session.close();
	        return ((List<Integer>) stationList).get(0).intValue();
	    }

	    public String GetStationName(int stationId){
	        Session session = null;
	        session = sessionFactory.openSession();
	        System.out.println(stationId);
	        List<String> stationList = session.createQuery("select u.stationName as stationName from Station u where u.stationId = " + stationId).list();
	        System.out.println(stationList);
	        session.close();
	        return stationList.get(0);
	    }

	    public void AddStation(String stationName){
	        Session session = null;
	        session = sessionFactory.openSession();
	        Transaction tran = session.beginTransaction();
	        System.out.println(stationName);
	        Station station = new Station();
	        station.setStationName(stationName);
	        session.save(station);
	        tran.commit();
	        session.close();
	    }

	    public void DeleteStation(String stationName){
	        Session session = null;
	        session = sessionFactory.openSession();
	        Transaction tran = session.beginTransaction();
	        String hql = "Delete FROM Station u Where u.stationName = '" + stationName + "'";
	        Query q = session.createQuery(hql);
	        q.executeUpdate();
	        tran.commit();
	    }
	
}
