package com.qin.train.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "train_station")
public class TrainStation implements Serializable{

    private static final long serialVersionUID = 1L;
    int id;
    int trainId;
    int stationId;
    double distance;
    Date arriveTime;
    Date leaveTime;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getTrainId(){
        return trainId;
    }

    public void setTrainId(int trainId){
        this.trainId = trainId;
    }

    public int getStationId(){
        return stationId;
    }

    public void setStationId(int stationId){
        this.stationId = stationId;
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