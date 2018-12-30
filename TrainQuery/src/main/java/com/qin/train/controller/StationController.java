package com.qin.train.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qin.train.pojo.Vo.DisplayVo;
import com.qin.train.pojo.Vo.TrainQuery;
import com.qin.train.pojo.Vo.TrainTemp;
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
}
