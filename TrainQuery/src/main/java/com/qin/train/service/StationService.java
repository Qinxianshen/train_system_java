package com.qin.train.service;

import java.util.List;

public interface StationService {
    List<Integer> GetAllStationId();
    List<String> GetAllStationName();
    int GetStationId(String stationName);
    String GetStationName(int stationId);
    void AddStation(String stationName);
    void DeleteStation(String stationName);
}
