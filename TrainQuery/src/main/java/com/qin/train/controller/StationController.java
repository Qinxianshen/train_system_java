package com.qin.train.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qin.train.pojo.Vo.AdvancedSearchVo;
import com.qin.train.pojo.Vo.DisplayVo;
import com.qin.train.pojo.Vo.StationNode;
import com.qin.train.pojo.Vo.TrainQuery;
import com.qin.train.pojo.Vo.TrainTemp;
import com.qin.train.pojo.Vo.Transfer;
import com.qin.train.service.StationService;
import com.qin.train.service.TrainService;
import com.qin.train.service.TrainStationService;

@Controller
@RequestMapping("/station")
public class StationController {
	@Resource
    private StationService stationService;
    @Resource
    private TrainService trainService;
    @Resource
    private TrainStationService trainStationService;

    String stationName1;
    String stationName2;
    int stationId1;
    int stationId2;

    List<TrainTemp> trainTemps;
    List<TrainTemp> departureTemps;
    List<TrainTemp> destinationTemps;
    
    @RequestMapping("/select")
    public ModelAndView GetTrainStation(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        this.stationName1 = request.getParameter("initialStationName");
        this.stationName2 = request.getParameter("destinationStationName");
        this.stationId1 = stationService.GetStationId(stationName1);
        this.stationId2 = stationService.GetStationId(stationName2);

        List<DisplayVo> displayVos = new ArrayList<DisplayVo>();

        List<TrainQuery> trainQueryList = trainStationService.GetTrainStation(stationId1, stationId2);
        Calendar calendar = Calendar.getInstance();
        if (trainQueryList.size() <= 0) {
            modelAndView.addObject("NODirect", "请使用高级查询");
        }
        for (TrainQuery t : trainQueryList){
            Date date = new Date();
            t.setArriveTime(new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), t.getArriveTime().getHours(), t.getArriveTime().getMinutes(), t.getArriveTime().getSeconds()));
            t.setLeaveTime(new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), t.getLeaveTime().getHours(), t.getLeaveTime().getMinutes(), t.getLeaveTime().getSeconds()));
            displayVos.add(new DisplayVo(
                    stationService.GetStationName(t.getInitialStationId()),
                    stationService.GetStationName(t.getDestinationStationId()),
                    trainService.GetTrainName(t.getTrainId()),
                    t.getDistance(),
                    t.getArriveTime(),
                    t.getLeaveTime()
            ));
        }
        modelAndView.addObject("TrainRoute", displayVos);
        modelAndView.setViewName("success");
        return modelAndView;
    }
    
    
    @RequestMapping("/StationDijkstra")
    public ModelAndView StationDijkstra(){

        ModelAndView modelAndView = new ModelAndView();
        trainTemps = trainStationService.getTrainRoute();
        StationNode[][] arr = StationDigraph();
//        System.out.println(arr);
        List<Integer> single = StationDijkstra(arr, stationId1, stationId2);
        System.out.println(".................");
        System.out.println(single);

        int station1 = single.get(0);
        int station2 = single.get(1);
        int train = 0;
        List<DisplayVo> displayVos = new ArrayList<DisplayVo>();
        Calendar calendar = Calendar.getInstance();
        TrainQuery trainQueryList;
        for (int i = 0; i < single.size() - 1; i++){
            //trainQueryList = trainStationService.GetTrainStationByDistance(single.get(i), single.get(i+1), arr[single.get(i)][single.get(i+1)].getDistance());
            if (train == 0){
                trainQueryList = trainStationService.GetTrainStationByDistance(single.get(i), single.get(i+1), arr[single.get(i)][single.get(i+1)].getDistance());
                train = trainQueryList.getTrainId();
                System.out.println("train = " + train);
            } else if ((trainQueryList = trainStationService.GetTransferTrainByDistance(single.get(i), single.get(i+1), train, arr[single.get(i)][single.get(i+1)].getDistance())) != null){
                station2 = trainQueryList.getDestinationStationId();
            } else {
                trainQueryList = trainStationService.GetTrainStationByDistance(single.get(i), single.get(i+1), arr[single.get(i)][single.get(i+1)].getDistance());
                TrainQuery trainQuery = trainStationService.GetTransferTrainByDistance(station1, station2, train, arr[station1][station2].getDistance());
                Date date = new Date();
                trainQuery.setArriveTime(new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), trainQuery.getArriveTime().getHours(), trainQuery.getArriveTime().getMinutes(), trainQuery.getArriveTime().getSeconds()));
                trainQuery.setLeaveTime(new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), trainQuery.getLeaveTime().getHours(), trainQuery.getLeaveTime().getMinutes(), trainQuery.getLeaveTime().getSeconds()));
                displayVos.add(new DisplayVo(
                        stationService.GetStationName(trainQuery.getInitialStationId()),
                        stationService.GetStationName(trainQuery.getDestinationStationId()),
                        trainService.GetTrainName(trainQuery.getTrainId()),
                        trainQuery.getDistance(),
                        trainQuery.getArriveTime(),
                        trainQuery.getLeaveTime()
                ));
                station1 = trainQueryList.getInitialStationId();
                station2 = trainQueryList.getDestinationStationId();
                train = trainQueryList.getTrainId();
            }
        }
        TrainQuery trainQuery = trainStationService.GetTransferTrainByDistance(station1, station2, train, arr[station1][station2].getDistance());
        Date date = new Date();
        trainQuery.setArriveTime(new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), trainQuery.getArriveTime().getHours(), trainQuery.getArriveTime().getMinutes(), trainQuery.getArriveTime().getSeconds()));
        trainQuery.setLeaveTime(new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), trainQuery.getLeaveTime().getHours(), trainQuery.getLeaveTime().getMinutes(), trainQuery.getLeaveTime().getSeconds()));
        displayVos.add(new DisplayVo(
                stationService.GetStationName(trainQuery.getInitialStationId()),
                stationService.GetStationName(trainQuery.getDestinationStationId()),
                trainService.GetTrainName(trainQuery.getTrainId()),
                trainQuery.getDistance(),
                trainQuery.getArriveTime(),
                trainQuery.getLeaveTime()
        ));
        modelAndView.addObject("TrainRoute", displayVos);
        modelAndView.setViewName("success");
        return modelAndView;

    }

    @RequestMapping("/AdvancedSearch")
    public ModelAndView AdvancedSearch(){

        List<TrainQuery> trainQueries = new ArrayList<TrainQuery>();
        List<DisplayVo> displayVos = new ArrayList<DisplayVo>();
        ModelAndView modelAndView = new ModelAndView();
        trainTemps=trainStationService.getTrainRoute();
        System.out.println("-------------出发--------------");
        GetAllDeparture(stationId1);
        System.out.println("-------------终点--------------");
        GetAllDestination(stationId2);
        System.out.println("-------------中转---------------");
        if (GetMiddleStation(stationId1, stationId2).size() > 0) {
            for (AdvancedSearchVo ad : GetMiddleStation(stationId1, stationId2)) {
                if(!(trainStationService.GetTransferTrain(ad.getStationId1(), ad.getStationId2(), ad.getTrainId()).equals("0"))){
                    trainQueries.add(trainStationService.GetTransferTrain(ad.getStationId1(), ad.getStationId2(), ad.getTrainId()));
                    System.out.println(ad.toString());
                }
            }
        }
        Calendar calendar = Calendar.getInstance();
        if (trainQueries.size() > 0){
            for (TrainQuery t : trainQueries){
                Date date = new Date();
                t.setArriveTime(new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), t.getArriveTime().getHours(), t.getArriveTime().getMinutes(), t.getArriveTime().getSeconds()));
                t.setLeaveTime(new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), t.getLeaveTime().getHours(), t.getLeaveTime().getMinutes(), t.getLeaveTime().getSeconds()));
                displayVos.add(new DisplayVo(
                        stationService.GetStationName(t.getInitialStationId()),
                        stationService.GetStationName(t.getDestinationStationId()),
                        trainService.GetTrainName(t.getTrainId()),
                        t.getDistance(),
                        t.getArriveTime(),
                        t.getLeaveTime()
                ));
            }
        }
        modelAndView.addObject("TrainRoute", displayVos);
        modelAndView.setViewName("success");
        return modelAndView;
    }

    private void GetAllDeparture(int stationId) {
        departureTemps = new ArrayList<TrainTemp>();
        int flag = 0;
        int index = 0;
        for (TrainTemp t : trainTemps) {
            for (int i = 0; i < t.getTrainRoute().length; i++) {
                if (t.getTrainRoute()[i].equals(stationId)) {
                    flag = 1;
                    index = i;
                    break;
                }
            }
            if (flag == 1) {
                Integer[] ret = new Integer[t.getTrainRoute().length - index];
                for (int j = index, k = 0; j < t.getTrainRoute().length; j++, k++) {
                    ret[k] = t.getTrainRoute()[j];
                }
                departureTemps.add(new TrainTemp(t.getTrainId(), ret));
            }
            flag = 0;
            index = 0;
        }
        for (TrainTemp t : departureTemps) {
            System.out.println(t.getTrainId());
            System.out.println(Arrays.toString(t.getTrainRoute()));
        }
    }

    private void GetAllDestination(int stationId) {
        destinationTemps = new ArrayList<TrainTemp>();
        int flag = 0;
        int index = 0;
        for (TrainTemp t : trainTemps) {
            for (int i = 0; i < t.getTrainRoute().length; i++) {
                if (t.getTrainRoute()[i].equals(stationId)) {
                    flag = 1;
                    index = i;
                    break;
                }
            }
            if (flag == 1) {
                Integer[] ret = new Integer[index + 1];
                for (int j = 0, k = 0; j <= index; j++, k++) {
                    ret[k] = t.getTrainRoute()[j];
                }
                destinationTemps.add(new TrainTemp(t.getTrainId(), ret));
            }
            flag = 0;
            index = 0;
        }
        for (TrainTemp t : destinationTemps) {
            System.out.println(t.getTrainId());
            System.out.println(Arrays.toString(t.getTrainRoute()));
        }
    }

    private List<AdvancedSearchVo> GetMiddleStation(int stationId1, int stationId2) {
        List<AdvancedSearchVo> advancedSearchVos = new ArrayList<AdvancedSearchVo>();
        List<Transfer> transferList = new ArrayList<Transfer>();
        for (TrainTemp t1 : departureTemps) {
            for (TrainTemp t2 : destinationTemps) {
                if (intersect(t1.getTrainRoute(), t2.getTrainRoute()).length > 0) {
                    transferList.add(new Transfer(stationId1, stationId2, intersect(t1.getTrainRoute(), t2.getTrainRoute()), t1.getTrainId(), t2.getTrainId()));
                }
            }
        }
        for (Transfer t : transferList
                ) {
            for (Integer integer : t.getMiddle()
                    ) {
                advancedSearchVos.add(new AdvancedSearchVo(stationId1, integer, t.getTrainId()));
                advancedSearchVos.add(new AdvancedSearchVo(integer, stationId2, t.getTrainId2()));
            }
        }
        return advancedSearchVos;
    }

    private static Integer[] intersect(Integer[] arr1, Integer[] arr2) {
        Set<Integer> result = new HashSet<Integer>();
        Set<Integer> set1 = new HashSet<Integer>(Arrays.asList(arr1));
        Set<Integer> set2 = new HashSet<Integer>(Arrays.asList(arr2));
        result.clear();
        result.addAll(set1);
        result.retainAll(set2);
        Integer[] arr = new Integer[result.size()];
        return result.toArray(arr);
    }

    //构建站点图，二维数组
    private StationNode[][] StationDigraph(){
        int length = stationService.GetAllStationName().size();
        StationNode[][] arr = new StationNode[length+1][length+1];
        for (int i = 1; i <= length; i++){
            for (int j = 1; j <= length; j++){
                arr[i][j] = new StationNode();
            }
        }
        for (TrainTemp t : trainTemps) {
            int ret[] = new int[t.getTrainRoute().length];
            ret = intUnPack(t.getTrainRoute());
            for (int i = 0; i < ret.length - 1; i++){
                System.out.println(ret[i]);
                System.out.println(ret[i+1]);
                System.out.println();
                if (arr[ret[i]][ret[i+1]].getFlag() == 0){
                    arr[ret[i]][ret[i+1]].setStationId1(ret[i]);
                    arr[ret[i]][ret[i+1]].setStationId2(ret[i+1]);
                    arr[ret[i]][ret[i+1]].setStationName1(stationService.GetStationName(ret[i]));
                    arr[ret[i]][ret[i+1]].setStationName2(stationService.GetStationName(ret[i+1]));
                    arr[ret[i]][ret[i+1]].setDistance(trainStationService.GetDistance(ret[i], ret[i+1]));
                    arr[ret[i]][ret[i+1]].setFlag(1);
                }
            }
        }
        return arr;
    }

    //Dijkstra算法计算最短路径，返回经过的站点Id
    private List<Integer> StationDijkstra(StationNode[][] arr, int stationId1, int stationId2){
        int length = stationService.GetAllStationName().size();
        List<Integer> single = new ArrayList<Integer>();
        List<Integer> station = stationService.GetAllStationId();
        int[][] array = new int[length+1][length+1];
        System.out.println(length);
        for (int i = 1; i <= length; i++){
            for (int j = 1; j <= length; j++){
                array[i][j] = 0;
            }
        }
        double[] distance = new double[length+1];
        int[] left = new int[length+1];
        for (int i = 1; i <= length; i++){
            distance[i] = Integer.MAX_VALUE;
            left[i] = 0;
        }
        distance[stationId1] = 0;
        left[stationId1] = stationId1;
        int flag = stationId1;
        int count  = 0;
        double dis = Double.MAX_VALUE;
        for (int n = 0; n < length; n++){
            dis = Integer.MAX_VALUE;
            for (int i = 1; i <= length; i++){
                if (arr[flag][i].getFlag() != 0){
                    if (arr[flag][i].getDistance() + distance[flag] <= distance[i]){
                        distance[i] = arr[flag][i].getDistance() + distance[flag];
                        left[i] = flag;
                        System.out.println("+++++++++++");
                        System.out.println(left[i]);
                        if (distance[i] < dis){
                            dis = distance[i];
                            count = i;
                        }
                    }
                }
            }
            flag = count;
        }
        single.add(stationId2);
        for (int i = stationId2; left[i] != stationId1; i = left[i]){
            single.add(left[i]);
            System.out.println("--------");
            System.out.println(left[i]);
        }
        single.add(stationId1);
        Collections.reverse(single);
        return single;
    }

    private int[] intUnPack(Integer[] integers) {
        int[] ints = new int[integers.length];
        for (int i = 0; i < integers.length; i++) {
            ints[i] = integers[i].intValue();
        }
        return ints;
    }


}
