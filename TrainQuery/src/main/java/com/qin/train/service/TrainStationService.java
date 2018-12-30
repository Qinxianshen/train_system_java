package com.qin.train.service;



import java.util.Date;
import java.util.List;

import com.qin.train.pojo.Vo.TrainQuery;
import com.qin.train.pojo.Vo.TrainTemp;

public interface TrainStationService {
    List<TrainQuery> GetTrainStation(int stationId1, int stationId2);
    TrainQuery GetTrainStationByDistance(int stationId1, int stationId2, double distance);
    TrainQuery GetTransferTrain(int stationId1, int stationId2, int trainId);
    TrainQuery GetTransferTrainByDistance(int stationId1, int stationId2, int trainId, double distance);
    List<TrainTemp> getTrainRoute();
    double GetDistance(int stationId1, int stationId2);
    void AddTrainStation(int trainId, int stationId, double distance, Date arriveTime, Date leaveTime);
    void DeleteTrainStation(int trainId, int stationId);
}
