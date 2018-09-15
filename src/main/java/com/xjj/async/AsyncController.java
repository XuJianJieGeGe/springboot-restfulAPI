package com.xjj.async;

import com.xjj.utils.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;
/**
 * Created by jie on 2018/9/9.
 */
@RestController
public class AsyncController {
    @Autowired(required = false)
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHandler deferredResultHandler;

    private Logger logger = LoggerFactory.getLogger(getClass());

    //同步的处理方式
   /* @RequestMapping("/order")
    public String order() throws Exception{
        logger.info("主线程开始");
        Thread.sleep(1000);
        logger.info("主线程返回");
        return "success";
    }*/


   /*//异步处理的模式
    @RequestMapping("/order")
    public Callable<String> order() throws Exception{
        //同步的处理方式
        logger.info("主线程开始");
        Callable<String> result = new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("副线程开始");
                Thread.sleep(1000);
                logger.info("副线程返回");
                return "success";
            }
        };
        logger.info("主线程返回");
        return result;
    }*/

    /**
     * 使用DeferredResult异步处理订单
     */
     public DeferredResult<String> order(){
         logger.info("主线程开始");
         String orderNumber = RandomUtils.generateString(8);
         mockQueue.setCompleteOrder(orderNumber);

         DeferredResult<String> result = new DeferredResult<>();
         deferredResultHandler.getMap().put(orderNumber,result);
         logger.info("主线程返回");
       return result;
     }






}
