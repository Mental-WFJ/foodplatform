package com.rearEnd.controller.report;

import com.rearEnd.dto.echarts.Option;
import com.rearEnd.dto.echarts.Serie;
import com.rearEnd.service.OrderReportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/orderReport")
public class OrderReportController {

    @Resource
    private OrderReportService orderReportService;

    @RequestMapping("/order")
    public String indexOrder() {
        return "/report/orderCount";
    }

    @RequestMapping("/money")
    public String indexMoney() {
        return "/report/moneyReport";
    }

    @ResponseBody
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Option count() {
        Option option = orderReportService.count();

//        // TODO 演示用数据 -- start
//        String[] names = new String[]{"小吃快餐", "自助餐", "面包甜点"};
//        option.getLegend().setData(Arrays.asList(names));
//        Random rand = new Random();
//        List<Serie> series = new ArrayList<>();
//        for (String name : names) {
//            Serie serie = new Serie();
//            series.add(serie);
//            serie.setName(name);
//            serie.setType("line");
//            for (int i = 0; i < 24; i++) {
//                serie.getData().add(Long.valueOf(rand.nextInt(1000)));
//            }
//        }
//        option.setSeries(series);
//        // TODO 演示用数据 -- end

        return option;
    }

    @ResponseBody
    @RequestMapping(value = "/total", method = RequestMethod.GET)
    public Option money() {
        Option option = orderReportService.money();

        return option;
    }
}