package com.qin.train.dao;

import java.util.List;

public interface StationDao {
    List<Integer> GetAllStationId();//获取所有车站id
    List<String> GetAllStationName();//获取所有车站名
    int GetStationId(String stationName);//通过车站名拿id
    String GetStationName(int stationId);//通过id拿车站名
    void AddStation(String stationName);//添加车站
    void DeleteStation(String stationName);//删除车站
}
