package com.fwwb.back_end.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fwwb.back_end.dao.StationMapper;
import com.fwwb.back_end.entity.ODBean;
import com.fwwb.back_end.entity.StationInfo;
import com.fwwb.back_end.entity.StrokeBean;
import com.fwwb.back_end.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author： Harry Zhou
 * @description:
 * @date: Created in 12:02 2021/2/4
 */

@Service("stationService")
public class StationServiceImpl implements StationService {
    @Autowired
    private StationMapper stationMapper;

    @Override
    /**
     * @author Harry Zhou
     * @description
     * @date 12:09 2021/2/4
     * @param
     * @return {@link List< HashMap< String, Integer>>}
     * @throws
     **/
    public List<HashMap<String, Object>> getPassengerInfoByHour(StationInfo info) {
        return stationMapper.getPassengerInfoByHour(info);
    }

    @Override
    public List<StrokeBean> getEntranceStrokeByTime(StationInfo info) {
        return stationMapper.getEntranceStrokeByTime(info);
    }

    @Override
    public List<StrokeBean> getOutStrokeByTime(StationInfo info) {
        return stationMapper.getOutStrokeByTime(info);
    }

    @Override
    public List<HashMap<String, Integer>> getLineStationInfo() {
        return stationMapper.getLineStationInfo();
    }

    @Override
    public ArrayList<Integer> getLine() {
        return stationMapper.getLine();
    }

    @Override
    public List<ODBean> getODData() {
        List<Integer> stations=stationMapper.getStationList();
        List<List<ODBean>> odBeans=new ArrayList<>();
        List<HashMap<String,Integer>> sums=new ArrayList<>();
        for(Integer in:stations){
            for(Integer out:stations){
                if(!in.equals(out)){
                    List<ODBean> od=stationMapper.getStationODByTime(in,out);
                    int sum= od.stream().mapToInt(ODBean::getNum).sum();
                    System.out.println(in+"  "+out+"  "+sum);
                    HashMap<String,Integer> hashMap=new HashMap<>();
                    hashMap.put("in",in);
                    hashMap.put("out",out);
                    hashMap.put("sum",sum);
                    sums.add(hashMap);
                    odBeans.add(od);
                }
            }
        }
        System.out.println("#######################################");
        sums.sort((o1,o2)->{
            int sum1=o1.get("sum");
            int sum2=o2.get("sum");
            if(sum1==sum2){
                return 0;
            }else{
                return sum1>sum2?-1:1;
            }
        });
        for(int i=0;i<10;i++){
            System.out.println(sums.get(i));
        }
        return stationMapper.getStationODByTime(1,2);
    }

    @Override
    public void getStationData() {
        List<Integer> stations=stationMapper.getStationList();
        stations.forEach(i -> {
            List<HashMap<String,String>> stationData=stationMapper.getStationData(i);
            JSONArray jsonArray=new JSONArray();
            stationData.forEach(s->{
                jsonArray.add(JSONObject.parseObject(JSON.toJSONString(s)));
            });
            System.out.println("#######stations:  "+i);
            System.out.println(JSON.toJSONString(jsonArray));
        });
    }

    @Override
    public void getStationOutData() {
        List<Integer> stations=stationMapper.getStationList();
        stations.forEach(i -> {
            List<HashMap<String,String>> stationData=stationMapper.getStationOutData(i);
            JSONArray jsonArray=new JSONArray();
            stationData.forEach(s->{
                jsonArray.add(JSONObject.parseObject(JSON.toJSONString(s)));
            });
            System.out.println("#######stations:  "+i);
            System.out.println(JSON.toJSONString(jsonArray));
        });
    }
}
