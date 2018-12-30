package com.qin.train.service.Impl;


import org.springframework.stereotype.Service;

import com.qin.train.dao.TrainDao;
import com.qin.train.pojo.Train;
import com.qin.train.service.TrainService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TrainServiceImpl implements TrainService {
    @Resource
    private TrainDao trainDao;

    
    public List<Train> GetAllTrain(){
        List<Train> trainList = trainDao.GetAllTrain();
        return trainList;
    }

    
    public int GetTrainId(String trainName){
        int trainId = trainDao.GetTrainId(trainName);
        return trainId;
    }

    
    public String GetTrainName(int trainId){
        String trainName = trainDao.GetTrainName(trainId);
        return trainName;
    }

    
    public void AddTrain(String trainName){
        trainDao.AddTrain(trainName);
    }

    
    public void DeleteTrain(String trainName){
        trainDao.DeleteTrain(trainName);
    }
}
