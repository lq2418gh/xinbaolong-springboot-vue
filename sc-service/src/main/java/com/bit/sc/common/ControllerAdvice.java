package com.bit.sc.common;

import com.bit.base.exception.BusinessException;
import com.bit.base.exception.CheckException;
import com.bit.base.vo.BaseVo;
import com.bit.common.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:  全局的controller异常处理
 * @Author: liyujun
 * @Date: 2018-11-06
 **/
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);
    /**
     * 全局异常捕捉处理
     * @param ex
     * @return vo
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public BaseVo errorHandler(Exception ex) {
        BaseVo vo=new BaseVo();
        if(ex instanceof BusinessException){
            ex=  (BusinessException)ex;
            vo.setCode(ResultCode.WRONG.getCode());
            vo.setMsg(ex.getMessage());

        }else if(ex instanceof CheckException){
            ex=  (CheckException)ex;
            vo.setCode(ResultCode.PARAMETER_ERROR.getCode());
            vo.setMsg(ex.getMessage());
        }else{
            vo.setCode(ResultCode.WRONG.getCode());
            vo.setMsg(ResultCode.WRONG.getInfo());
        }
        logger.error(ex.getMessage(),ex);
        return vo;
    }
}
