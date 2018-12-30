package com.qin.train.dao;

import java.util.List;

import com.qin.train.pojo.Train;

public interface TrainDao {
    List<Train> GetAllTrain();
    String GetTrainName(int trainId);
    int GetTrainId(String trainName);
    void AddTrain(String trainName);
    void DeleteTrain(String trainName);
}
