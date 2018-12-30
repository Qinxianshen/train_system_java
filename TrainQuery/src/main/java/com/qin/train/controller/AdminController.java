package com.qin.train.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qin.train.dao.StationDao;
import com.qin.train.pojo.Train;
import com.qin.train.service.StationService;
import com.qin.train.service.TrainService;
import com.qin.train.service.TrainStationService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Resource
    private StationDao stationDao;

    @Resource
    private StationService stationService;

    @Resource
    private TrainStationService trainStationService;

    @Resource
    private TrainService trainService;

    String trainName;
    String stationName;
    String trainRoute;
    int trainId;
    int stationId;
    int distance;
    Date arriveTime;
    Date leaveTime;

    @RequestMapping("/showStation")
    public ModelAndView ShowStation(){
        ModelAndView modelAndView = new ModelAndView();
        List<String> stations = stationService.GetAllStationName();
        modelAndView.addObject("AllStation", stations);
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @RequestMapping("/addTrainStation")
    public ModelAndView AddTrainStation(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        DateFormat format = new SimpleDateFormat("HH:mm");
        try {
            this.arriveTime = format.parse(request.getParameter("arriveTime"));
            this.leaveTime = format.parse(request.getParameter("leaveTime"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.trainName = request.getParameter("trainName");
        this.stationName = request.getParameter("stationName");
        this.distance = Integer.parseInt(request.getParameter("distance"));
        trainStationService.AddTrainStation(trainService.GetTrainId(trainName), stationService.GetStationId(stationName), distance, arriveTime, leaveTime);
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @RequestMapping("/deleteTrainStation")
    public ModelAndView DeleteTrainStation(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        this.trainName = request.getParameter("trainName");
        this.stationName = request.getParameter("stationName");
        trainStationService.DeleteTrainStation(trainService.GetTrainId(trainName), stationService.GetStationId(stationName));
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @RequestMapping("/addStation")
    public ModelAndView AddStation(HttpServletRequest request, HttpServletResponse response){
        System.out.println("..................");
        ModelAndView modelAndView = new ModelAndView();
        this.stationName = request.getParameter("stationName");
        stationDao.AddStation(stationName);
        List<String> stations = stationService.GetAllStationName();
        modelAndView.addObject("AllStation", stations);
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @RequestMapping("/deleteStation")
    public ModelAndView DeleteStation(HttpServletRequest request, HttpServletResponse response){
        System.out.println("..................");
        ModelAndView modelAndView = new ModelAndView();
        this.stationName = request.getParameter("stationName");
        stationDao.DeleteStation(stationName);
        List<String> stations = stationService.GetAllStationName();
        modelAndView.addObject("AllStation", stations);
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @RequestMapping("/addTrain")
    public ModelAndView AddTrain(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        this.trainName = request.getParameter("trainName");
        trainService.AddTrain(trainName);
        List<Train> trains = trainService.GetAllTrain();
        modelAndView.addObject("AllTrain", trains);
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @RequestMapping("/deleteTrain")
    public ModelAndView DeleteTrain(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        this.trainName = request.getParameter("trainName");
        trainService.DeleteTrain(trainName);
        List<Train> trains = trainService.GetAllTrain();
        modelAndView.addObject("AllTrain", trains);
        modelAndView.setViewName("admin");
        return modelAndView;
    }
}
