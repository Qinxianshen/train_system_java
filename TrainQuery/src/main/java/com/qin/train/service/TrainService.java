package com.qin.train.service;



import java.util.List;

import com.qin.train.pojo.Train;

public interface TrainService {
    List<Train> GetAllTrain();
    int GetTrainId(String trainName);
    String GetTrainName(int trainId);
    void AddTrain(String trainName);
    void DeleteTrain(String trainName);
}
