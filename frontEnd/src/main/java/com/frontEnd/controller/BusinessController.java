package com.frontEnd.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.frontEnd.entity.*;
import com.frontEnd.service.*;
import com.frontEnd.util.GetOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Mental
 * @Date:
 */
@Controller
@RequestMapping("/bussiness")
public class BusinessController {

    @Resource
    private BusinessService businessService;
    @Resource
    private CityService cityService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private OrdersService ordersService;
    @Resource
    private CommentService commentService;
    @Resource
    private FavoritiesService favoritiesService;
    @Resource
    private TendencyService tendencyService;
    @Autowired
    private HttpSession session;

//    @RequestMapping
//    public ModelAndView init(BusinessDto dto){
//        ModelAndView mv = new ModelAndView("/restaurantList");
////        mv.addObject("bList", businessService.searchByPage(dto));
////        mv.addObject("searchParam", dto);
////        for(BusinessDto bDto : businessService.searchByPage(dto)){
////            System.out.println(bDto.toString());
////        }
//        mv.addObject("bList", businessService.getHotBusiness("北京"));
//        mv.addObject("cityList", cityService.getCityList());
//        mv.addObject("categoryList", categoryService.getCategoryList());
//        return mv;
//    }

    @RequestMapping("article/{businessId}/{memberId}")
    public ModelAndView article(@PathVariable(value = "businessId") String businessId,
                                @PathVariable(value = "memberId") String memberId) {
        System.out.println("BusinessID: " + businessId);
        System.out.println("MemberID: " + memberId);
        ModelAndView mv = new ModelAndView("/restaurantArticle");
        Long bId = Long.parseLong(businessId);
        List<Favorities> fList = new ArrayList<Favorities>();
        boolean flag = false;
        if (!memberId.equals("null")) {
            Business b = businessService.getById(bId);
            Tendency tendency = new Tendency();
            tendency.setMemberId(Long.parseLong(memberId));
            int index = 0;
            for(Category c : categoryService.getCategoryList()){
                if(b.getCategory().equals(c.getType())){
                    index = c.getId();
                    break;
                }
            }
            if(index == 1)
                tendency.setTypeOne(1);
            else if(index == 2)
                tendency.setTypeTwo(1);
            else if(index == 3)
                tendency.setTypeThree(1);
            else if(index == 4)
                tendency.setTypeFour(1);
            else if(index == 5)
                tendency.setTypeFive(1);
            else if(index == 6)
                tendency.setTypeSix(1);
            else if(index == 7)
                tendency.setTypeSeven(1);
            else if(index == 8)
                tendency.setTypeEight(1);
            else if(index == 9)
                tendency.setTypeNine(1);
            else if(index == 10)
                tendency.setTypeTen(1);
            else if(index == 11)
                tendency.setTypeEleven(1);
            else if(index == 12)
                tendency.setTypeTwelve(1);
            else if(index == 13)
                tendency.setTypeThirteen(1);
            else if(index == 14)
                tendency.setTypeFourteen(1);
            tendencyService.updateTendency(tendency);
            Long mId = Long.parseLong(memberId);
            fList = favoritiesService.getListByMemberID(mId);
            for (Favorities f : fList) {
                if (f.getBusinessId() == bId) {
                    flag = true;
                    break;
                }
            }
        }
        List<Orders> orders = ordersService.selectBybusinessId(bId);
        List<Comment> comments = new ArrayList<Comment>();
        for (Orders o : orders) {
            Comment c = commentService.getByOrderID(o.getId());
            if(c.getUseable() == 0)
                continue;
            c.setOrders(o);
            comments.add(c);
        }
        mv.addObject("flag", flag);
        mv.addObject("categoryList", categoryService.getCategoryList());
        mv.addObject("business", businessService.getById(bId));
        mv.addObject("commentList", comments);
        return mv;
    }

    @RequestMapping("search/{memberId}/{cityId}/{catagoryId}/{searchKey}")
    public ModelAndView indexCity(@PathVariable("memberId") String memberId,
                                  @PathVariable(value = "cityId") String cityId,
                                  @PathVariable(value = "catagoryId") String catagoryId,
                                  @PathVariable(value = "searchKey") String searchKey) {

        System.out.println("memberId: " + memberId + " cityId: " + cityId + " catagoryId: " + catagoryId + " searchKey: " + searchKey);
        int cId = Integer.parseInt(cityId);
        ModelAndView mv = new ModelAndView("/restaurantList");
        if(!memberId.equals("null")){
            Long mId = Long.parseLong(memberId);
            Tendency t = tendencyService.getTendencyByID(mId);
            if(t != null){
                int index = 1;
                if(t.getTypeOne() < t.getTypeTwo())
                    index = 2;
                else if(t.getTypeOne() < t.getTypeThree())
                    index = 3;
                else if(t.getTypeOne() < t.getTypeFour())
                    index = 4;
                else if(t.getTypeOne() < t.getTypeFive())
                    index = 5;
                else if(t.getTypeOne() < t.getTypeSix())
                    index = 6;
                else if(t.getTypeOne() < t.getTypeSeven())
                    index = 7;
                else if(t.getTypeOne() < t.getTypeEight())
                    index = 8;
                else if (t.getTypeOne() < t.getTypeNine())
                    index = 9;
                else if (t.getTypeOne() < t.getTypeTen())
                    index = 10;
                else if (t.getTypeOne() < t.getTypeEleven())
                    index = 11;
                else if (t.getTypeOne() < t.getTypeTwelve())
                    index = 12;
                else if (t.getTypeOne() < t.getTypeThirteen())
                    index = 13;
                else if (t.getTypeOne() < t.getTypeFourteen())
                    index = 14;
                System.out.println("index: " + index);
                for(Category c : categoryService.getCategoryList()){
                    if(c.getId() == index){
                        mv.addObject("tendency", c.getType());
                        System.out.println("tendency: " + c.getId() + "、" + c.getType());
                        break;
                    }
                }
            }
        }
        City c = new City();
        Category cate = new Category();
        for (City city1 : cityService.getCityList()) {
            if (city1.getId() == cId) {
                c = city1;
                break;
            }
        }
        if (searchKey.equals("null"))
            searchKey = "";
        List<Business> businessList = new ArrayList<Business>();
        if (catagoryId.equals("null")) {
            businessList = businessService.getBusinessBySearch(c.getCity(), searchKey);
            cate = null;
        } else {
            int cateId = Integer.parseInt(catagoryId);
            System.out.println("categoryId: " + cateId);
            for (Category category : categoryService.getCategoryList()) {
                if (category.getId() == cateId) {
                    cate = category;
                    break;
                }
            }
            businessList = businessService.getBySearchAndCate(c.getCity(), cate.getType(), searchKey);
        }
        session.setAttribute("selectedCity", c);
        mv.addObject("bList", businessList);
        session.setAttribute("selectedCategory", cate);
        mv.addObject("cityList", cityService.getCityList());
        mv.addObject("categoryList", categoryService.getCategoryList());
        return mv;
    }

    @RequestMapping("topay")
    @ResponseBody
    public String goAlipay(HttpServletRequest request) throws Exception {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        Orders o = new Orders();
        Long businessId = Long.parseLong(request.getParameter("businessId"));
        Long memberId = Long.parseLong(request.getParameter("memberId"));
        int num = Integer.parseInt(request.getParameter("num"));
        double price = Double.parseDouble(request.getParameter("money"));
        o.setBusinessId(businessId);
        o.setMemberId(memberId);
        o.setNum(num);
        o.setPrice(price);
        Date date = new Date();//获得系统时间.
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date time = sdf.parse( nowTime );
        o.setCreateTime(time);
        ordersService.insertOrder(o);
        System.out.println("order: " + o.getBusinessId() + " " + o.getMemberId() + " " + o.getNum() + " " + o.getPrice());

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);//AlipayConfig.return_url+"?accepterid="+accepterid);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = GetOrder.getUniqueOrder();
        //付款金额，必填
        String total_amount = request.getParameter("total_amount");
        System.out.println(out_trade_no);
        System.out.println(total_amount);
        //订单名称，必填
        String subject = "大众美食点评系统";
        //商品描述，可空
        String body = "";

        String timeout_express = "1c";
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"timeout_express\":\"" + timeout_express + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        return result;
    }
}
