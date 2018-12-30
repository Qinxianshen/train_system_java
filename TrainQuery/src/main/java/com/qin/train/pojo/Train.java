package com.qin.train.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "train")
public class Train {

    int trainId;
    String trainName;

    public int getTrainId(){
        return trainId;
    }

    public void setTrainId(int trainId){
        this.trainId = trainId;
    }

    public String getTrainName(){
        return trainName;
    }

    public void setTrainName(String trainName){
        this.trainName = trainName;
    }

}