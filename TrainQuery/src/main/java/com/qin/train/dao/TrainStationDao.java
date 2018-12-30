package com.qin.train.dao;

import java.util.Date;
import java.util.List;

import com.qin.train.pojo.TrainStation;
import com.qin.train.pojo.Vo.TrainQuery;


public interface TrainStationDao {
    List<TrainQuery> GetTrainStation(int stationId1, int stationId2);
    List<Integer> getTrainSum();
    List<TrainStation> getRecordSum();
    List<Integer> getAllTrainId();
    TrainQuery GetTrainStationByDistance(int stationId1, int stationId2, double distance);
    TrainQuery GetTransferTrain(int stationId1, int stationId2, int trainId);
    TrainQuery GetTransferTrainByDistance(int stationId1, int stationId2, int trainId, double distance);
    List<Double> GetDistance(int stationId1, int stationId2);
    void AddTrainStation(int trainId, int stationId, double distance, Date arriveTime, Date leaveTime);
    void DeleteTrainStation(int trainId, int stationId);
}
