package com.qin.train.pojo.Vo;

public class TrainTemp {
    int trainId;
    Integer[] trainRoute;

    public TrainTemp(int trainId, Integer[] trainRoute) {
        this.trainId = trainId;
        this.trainRoute = trainRoute;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public Integer[] getTrainRoute() {
        return trainRoute;
    }

    public void setTrainRoute(Integer[] trainRoute) {
        this.trainRoute = trainRoute;
    }
}
