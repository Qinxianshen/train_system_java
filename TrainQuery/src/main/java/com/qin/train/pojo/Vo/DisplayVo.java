package com.qin.train.pojo.Vo;

import java.util.Date;

public class DisplayVo {
    String initialStationName;
    String destinationStationName;
    String trainName;
    double distance;
    Date arriveTime;
    Date leaveTime;

    public DisplayVo(String initialStationName, String destinationStationName, String trainName, double distance, Date arriveTime, Date leaveTime){
        this.initialStationName = initialStationName;
        this.destinationStationName = destinationStationName;
        this.trainName = trainName;
        this.distance = distance;
        this.arriveTime = arriveTime;
        this.leaveTime = leaveTime;
    }

    public String getInitialStationName() {
        return initialStationName;
    }

    public void setInitialStationName(String initialStationName) {
        this.initialStationName = initialStationName;
    }

    public String getDestinationStationName() {
        return destinationStationName;
    }

    public void setDestinationStationName(String destinationStationName) {
        this.destinationStationName = destinationStationName;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName){
        this.trainName = trainName;
    }

    public double getDistance(){
        return distance;
    }

    public void setDistance(double distance){
        this.distance = distance;
    }

    public Date getArriveTime(){
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime){
        this.arriveTime = arriveTime;
    }

    public Date getLeaveTime(){
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime){
        this.leaveTime = leaveTime;
    }
}
