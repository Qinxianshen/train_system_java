package com.qin.train.service.Impl;


import org.springframework.stereotype.Service;

import com.qin.train.dao.StationDao;
import com.qin.train.service.StationService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StationServiceImpl implements StationService {
    @Resource
    private StationDao stationDao;

    
    public List<Integer> GetAllStationId(){
        return stationDao.GetAllStationId();
    }

    
    public List<String> GetAllStationName() {
        return stationDao.GetAllStationName();
    }

    
    public String GetStationName(int stationId){
        return stationDao.GetStationName(stationId);
    }

    
    public int GetStationId(String stationName){
        int stationId = stationDao.GetStationId(stationName);
        return stationId;
    }

    
    public void AddStation(String stationName){
        stationDao.AddStation(stationName);
    }

    
    public void DeleteStation(String stationName){
        stationDao.DeleteStation(stationName);
    }
}
