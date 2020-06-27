package com.frontEnd.controller;

import com.alipay.api.internal.util.AlipaySignature;
import com.frontEnd.entity.AlipayConfig;
import com.frontEnd.entity.Comment;
import com.frontEnd.entity.Orders;
import com.frontEnd.service.CategoryService;
import com.frontEnd.service.CommentService;
import com.frontEnd.service.OrdersService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: Mental
 * @Date:
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Resource
    private OrdersService ordersService;
    @Resource
    private CommentService commentService;
    @Resource
    private CategoryService categoryService;
    @Autowired
    private HttpSession session;

    /**
     * @Description: 支付宝异步 通知页面
     */
    @RequestMapping("/alipayNotifyNotice")
    @ResponseBody
    public String alipayNotifyNotice(HttpServletRequest request, HttpServletRequest response) throws Exception {

        System.out.println("支付成功,进入异步通知接口...");//log.info("支付成功, 进入异步通知接口...");

        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            /*valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");*/
            params.put(name, valueStr);
        }

        //调用SDK验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);

        //——请在这里编写您的程序（以下代码仅作参考）——

		/* 实际验证过程建议商户务必添加以下校验：
		1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
		3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
		4、验证app_id是否为该商户本身。
		*/
        //验证成功
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
            System.out.println("商户订单号="+out_trade_no);
            System.out.println("支付宝交易号="+trade_no);
            System.out.println("付款金额="+total_amount);
            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意： 尚自习的订单没有退款功能, 这个条件判断是进不来的, 所以此处不必写代码
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            }else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //付款完成后，支付宝系统发送该交易状态通知

                // 修改叮当状态，改为 支付成功，已付款; 同时新增支付流水
				/*orderService.updateOrderStatus(out_trade_no, trade_no, total_amount);

				Order order = orderService.getOrderById(out_trade_no);
				Product product = productService.getProductById(order.getProductId());

				log.info("********************** 支付成功(支付宝异步通知) **********************");
				log.info("* 订单号: {}", out_trade_no);
	    		log.info("* 支付宝交易号: {}", trade_no);
	    		log.info("* 实付金额: {}", total_amount);
	    		log.info("* 购买产品: {}", product.getName());
	    		log.info("***************************************************************");*/

                System.out.println("支付成功");
            }

            //log.info("支付成功...");
        }else {//验证失败
            System.out.println("支付失败,进入异步通知接口");//log.info("支付, 验签失败...");
        }
        return "redirect:/webAPI/init";
    }

    /**
     * @Description: 支付宝同步通知页面
     */
    @RequestMapping("/alipayReturnNotice")
    public ModelAndView alipayReturnNotice(HttpSession session, HttpServletRequest request, HttpServletRequest response) throws Exception {
        System.out.println("支付成功, 进入同步通知接口");
        //log.info("支付成功, 进入同步通知接口...");
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        //调用SDK验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        ModelAndView mv = new ModelAndView("/home");
        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
            System.out.println("商户订单号="+out_trade_no);
            System.out.println("支付宝交易号="+trade_no);
            System.out.println("付款金额="+total_amount);
            // 修改订单状态为支付成功，已付款; 同时新增支付流水
            //orderService.updateOrderStatus(out_trade_no, trade_no, total_amount);

			/*Order order = orderService.getOrderById(out_trade_no);
			Product product = productService.getProductById(order.getProductId());
			log.info("********************** 支付成功(支付宝同步通知) **********************");
    		log.info("* 订单号: {}", out_trade_no);
    		log.info("* 支付宝交易号: {}", trade_no);
    		log.info("* 实付金额: {}", total_amount);
    		log.info("* 购买产品: {}", product.getName());
    		log.info("***************************************************************");
    		mv.addObject("out_trade_no", out_trade_no);
    		mv.addObject("trade_no", trade_no);
    		mv.addObject("total_amount", total_amount);
    		mv.addObject("productName", product.getName());*/
            System.out.println("同步支付，验签成功");
        }else {
            System.out.println("同步支付，验签失败");
            //log.info("支付, 验签失败...");
        }
        return mv;
    }

    @RequestMapping("/getOrderList/{memberId}")
    public ModelAndView getOrderList(@PathVariable(value = "memberId") Long memberId){
        ModelAndView mv = new ModelAndView("/memberOrders");
        System.out.println("memberId: " + memberId);
        List<Orders> orders = ordersService.selectByMemberId(memberId);
        List<Orders> oList = new ArrayList<Orders>();
        List<Comment> comments = new ArrayList<Comment>();
        for(Orders o : orders){
            if(o.getCommentState() == 2){
                Comment c = commentService.getByOrderID(o.getId());
                c.setOrders(o);
                comments.add(c);
                System.out.println("business: " + c.getOrders().getBusiness().getTitle() + " commentState: " + c.getOrders().getCommentState() + " createTime: " + c.getCreateTime());
                System.out.println("businessID: " + c.getOrders().getBusinessId() + " memberID: " + c.getOrders().getMemberId());
            }
            else{
                oList.add(o);
                System.out.println("business: " + o.getBusiness().getTitle() + " commentState: " + o.getCommentState() + " createTime: " + o.getCreateTime());
                System.out.println("businessID: " + o.getBusinessId() + " memberID: " + o.getMemberId());
            }
        }
        mv.addObject("orderList", oList);
        mv.addObject("commentList", comments);
        mv.addObject("categoryList", categoryService.getCategoryList());
        return mv;
    }

    @RequestMapping("/submitComment")
    @ResponseBody
    public boolean submitComment(HttpServletRequest request) throws ParseException {
        Comment comment = new Comment();
        Long id = Long.parseLong(request.getParameter("ordersId"));
        Integer star = Integer.parseInt(request.getParameter("star"));
        String commentTxt = request.getParameter("comment");
        Date date = new Date();//获得系统时间.
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date time = sdf.parse( nowTime );
        comment.setOrdersId(id);
        comment.setStar(star);
        comment.setComment(commentTxt);
        comment.setCreateTime(time);
        if(commentService.insertComment(comment))
            if(ordersService.updateOrder(id))
                return true;
        return false;
    }
}
