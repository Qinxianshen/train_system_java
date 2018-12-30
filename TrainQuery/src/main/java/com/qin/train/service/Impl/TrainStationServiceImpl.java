package com.qin.train.service.Impl;


import org.springframework.stereotype.Service;

import com.qin.train.dao.TrainStationDao;
import com.qin.train.pojo.Vo.TrainQuery;
import com.qin.train.pojo.Vo.TrainTemp;
import com.qin.train.service.TrainStationService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class TrainStationServiceImpl implements TrainStationService {
    @Resource
    private TrainStationDao trainStationDao;


    
    public List<TrainQuery> GetTrainStation (int initialStationId, int destinationStationId){
        List<TrainQuery> trainQueryList = trainStationDao.GetTrainStation(initialStationId, destinationStationId);
        return trainQueryList;
    }

    
    public TrainQuery GetTrainStationByDistance(int stationId1, int stationId2, double distance){
        return trainStationDao.GetTrainStationByDistance(stationId1, stationId2, distance);
    }

    
    public TrainQuery GetTransferTrain(int initialStationId, int destinationStationId, int trainId){
        return trainStationDao.GetTransferTrain(initialStationId, destinationStationId,trainId);
    }

    
    public TrainQuery GetTransferTrainByDistance(int stationId1, int stationId2, int trainId, double distance){
        TrainQuery trainQuery = trainStationDao.GetTransferTrainByDistance(stationId1, stationId2,trainId, distance);
        if (trainQuery == null) {
            return null;
        } else {
            return trainQuery;
        }
    }

    
    public List<TrainTemp> getTrainRoute() {
        List<TrainTemp> trainTemps = new ArrayList<TrainTemp>();
        int count = 0;
        for (int i = 0; i < trainStationDao.getTrainSum().size(); i++) {
            Integer[] integers = new Integer[trainStationDao.getTrainSum().get(i).intValue()];
            for (int j = 0; j < trainStationDao.getTrainSum().get(i); j++) {
                integers[j] = trainStationDao.getRecordSum().get(count).getStationId();
                count++;
            }
            trainTemps.add(new TrainTemp(trainStationDao.getAllTrainId().get(i), integers));

        }
        return trainTemps;
    }

    
    public double GetDistance(int stationId1, int stationId2){
        List<Double> distanceList = trainStationDao.GetDistance(stationId1, stationId2);
        System.out.println("--------------");
        System.out.println(stationId1 + " " + stationId2 + " " + Collections.min(distanceList));
        return Collections.min(distanceList);
    }


    
    public void AddTrainStation(int trainId, int stationId, double distance, Date arriveTime, Date leaveTime){
        trainStationDao.AddTrainStation(trainId, stationId, distance, arriveTime, leaveTime);
    }

    
    public void DeleteTrainStation(int trainId, int stationId){
        trainStationDao.DeleteTrainStation(trainId, stationId);
    }
}
