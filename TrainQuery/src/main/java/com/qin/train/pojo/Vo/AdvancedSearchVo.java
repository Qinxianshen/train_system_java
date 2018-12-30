package com.qin.train.pojo.Vo;

public class AdvancedSearchVo {
    int stationId1;
    int stationId2;
    int trainId;

    public AdvancedSearchVo(int stationId1, int stationId2, int trainId) {
        this.stationId1 = stationId1;
        this.stationId2 = stationId2;
        this.trainId = trainId;
    }

    public int getStationId1() {
        return stationId1;
    }

    public void setStationId1(int stationId1) {
        this.stationId1 = stationId1;
    }

    public int getStationId2() {
        return stationId2;
    }

    public void setStationId2(int stationId2) {
        this.stationId2 = stationId2;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    @Override
    public String toString() {
        return "AdvancedSearchVo{" +
                "stationId1=" + stationId1 +
                ", station2Id2=" + stationId2 +
                ", trainId=" + trainId +
                '}';
    }
}
