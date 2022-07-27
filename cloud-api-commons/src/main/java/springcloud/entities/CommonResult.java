package springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {              //使用泛型使得更通用

    private Integer code;                   //对应 json 字符串中的 错误码: eg:404,500...(200成功走正常逻辑，反之异常)
    private String message;                 //对应出错提示信息
    private T data;                         //出错的实体类对象

    //由于 lombok 提供的只有空参和全参构造器，这里写一个仅两个属性的构造器
    public CommonResult(Integer code, String message){
        this(code, message, null);
    }

}
