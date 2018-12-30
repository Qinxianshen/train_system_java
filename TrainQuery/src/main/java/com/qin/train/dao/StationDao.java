package com.qin.train.dao;

import java.util.List;

public interface StationDao {
    List<Integer> GetAllStationId();//��ȡ���г�վid
    List<String> GetAllStationName();//��ȡ���г�վ��
    int GetStationId(String stationName);//ͨ����վ����id
    String GetStationName(int stationId);//ͨ��id�ó�վ��
    void AddStation(String stationName);//��ӳ�վ
    void DeleteStation(String stationName);//ɾ����վ
}
