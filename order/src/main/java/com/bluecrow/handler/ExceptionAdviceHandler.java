package com.bluecrow.handler;

import com.bluecrow.core.consts.ExceptionEnum;
import com.bluecrow.core.consts.ResponseConst;
import com.bluecrow.core.entity.ResponseBo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author BlueCrow
 * @Package com.bluecrow.handler
 * @Decription
 * @date 2021/7/25 14:35
 */
@ControllerAdvice
@Slf4j
public class ExceptionAdviceHandler {

    /**
     * 没有做异常处理统一返回格式
     *
     * @param ex 异常
     * @return 统一报文
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseBo<Object> handle(Exception ex) {
        ResponseBo<Object> responseBo = ResponseBo.error(ExceptionEnum.SERVER_ERROR, ex.getMessage());
        log.error("{}\r\n错误编码: {}\r\n错误信息: {}\r\n错误描述: {}\r\nUUID: {}\r\n数据: {}",
                ex.getMessage(),
                responseBo.getInteger(ResponseConst.CODE),
                responseBo.getString(ResponseConst.MSG),
                ex.getMessage(),
                responseBo.getString(ResponseConst.UUID),
                null,
                ex);

        return responseBo;
    }
}