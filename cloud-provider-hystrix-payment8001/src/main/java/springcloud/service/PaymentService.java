package springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_ok(Integer id){
        return "线程池：" + Thread.currentThread().getName() + "   paymentInfo_ok, id : " + id + "\t" + "O(∩_∩)O";
    }

    /*
    * 故意制造两个异常：
   1  int age = 10/0; 计算异常
   2  我们能接受3秒钟，它运行5秒钟，超时异常。
    * */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")     //设定超时时间 3s
    })             //服务降级（注解代替编码）,当前请求出事儿了，给出一个 fallbackMethod 路径
    public String paymentInfo_TimeOut(Integer id){
//        int age = 10 / 0;
//        int timeNumber = 5;
//        try {
//            TimeUnit.SECONDS.sleep(timeNumber);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "线程池:)" + Thread.currentThread().getName() + "   paymentInfo_TimeOut, id : " + id + "\t";      // + "耗时(s)：" + timeNumber
    }

    //兜底：返回友好提示
    public String paymentInfo_TimeOutHandler(Integer id){       //传参要保持一致
        return "/(ㄒoㄒ)/调用支付接口超时或异常：\t"+ "\t当前线程池名字" + Thread.currentThread().getName();
    }

    //-------------------服务熔断 （保险丝） 在规定时间内(10s)请求 (10) 次若有 (60%) 的请求失败，断路器熔断
    //服务降级-> 服务熔断 -> 自适应恢复 (该过程均以时间窗口期为单位往复循环)
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),           //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),      //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),        //时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),            //失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        if(id < 0)
        {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id)
    {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
    }

}
