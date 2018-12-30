package com.qin.train.pojo.Vo;

import java.util.Date;

public class TrainQuery {

    int trainId;
    int stationId1;
    int stationId2;
    double distance;
    Date leaveTime;
    Date arriveTime;

    public int getTrainId(){
        return trainId;
    }

    public void setTrainId(int trainId){
        this.trainId = trainId;
    }

    public int getInitialStationId(){
        return stationId1;
    }

    public void setInitialStationId(int stationId1){
        this.stationId1 = stationId1;
    }

    public int getDestinationStationId(){
        return stationId2;
    }

    public void setDestinationStationId(int stationId2){
        this.stationId2 = stationId2;
    }

    public double getDistance(){
        return distance;
    }

    public void setDistance(double distance){
        this.distance = distance;
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

}