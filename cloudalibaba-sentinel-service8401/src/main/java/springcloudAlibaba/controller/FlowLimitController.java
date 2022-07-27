package springcloudAlibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController
{

    @GetMapping("/testA")
    public String testA()
    {
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB()
    {
        log.info(Thread.currentThread().getName() + ".....testB");          //演示流控效果中的排队等待
        return "------testB";
    }

    /*
    * 测试服务降级：
    * 1. RT（秒级  平均相应时间）
    * */
    @GetMapping("/testD")
    public String testD()
    {
        //暂停几秒钟线程
//        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
//        log.info("testD 测试RT");

        log.info("测试异常比例");     //QPS>=5 && 请求错误率超过阈值
        int age = 10/0;
        return "------testD";
    }

    @GetMapping("/testE")
    public String testE()
    {
        log.info("testE 测试异常数");
        int age = 10/0;
        return "------testE 测试异常数";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")           //value唯一标识控制台中的规则名。若违背规则跳转至自定义的兜底页面deal_testHotKey
    //@SentinelResource 只管 Sentinel控制台配置的违规情况，与 java 运行时异常无关该报错报错
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){
        return "-----testHotKey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException exception){
        return "-----deal_testHotKey,/(ㄒoㄒ)/~~";            //sentinel 默认的提示：Blocked by sentinel (flow limiting)
    }

}

