package springcloudAlibaba.myHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import springcloud.entities.CommonResult;

/*
* 自定义两个错误页面。与主业务逻辑分隔开
* 且主业务可以自行决定跳转到哪个错误页面
* */

public class CustomerBlockHandler {

    public static CommonResult handlerException1(BlockException exception){
        return new CommonResult(444, "用户自定义的 global handlerException -----1");
    }

    public static CommonResult handlerException2(BlockException exception){
        return new CommonResult(444, "用户自定义的 global handlerException -----2");
    }
}
