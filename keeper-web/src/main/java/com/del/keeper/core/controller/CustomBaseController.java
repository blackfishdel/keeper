package com.del.keeper.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.del.keeper.core.constant.Constant;
import com.del.keeper.core.exception.ControllerException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 基础controller,所有controller继承该对象
 *
 * @author xie
 */
public abstract class CustomBaseController {
    protected final Logger log = LoggerFactory.getLogger(this.getClass().getName());

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    protected SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @ModelAttribute
    public void setRequestAndResponse(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

    public String getClientType() {
        return this.request.getHeader(Constant.DEVICE_TYPE);
    }

    public String getClientId() {
        return this.request.getHeader(Constant.DEVICE_ID);
    }

    public Date stringToDate(String dateStr) {
        try {
            Date date = dateFormat.parse(dateStr);
            return date;
        } catch (ParseException e) {
            throw new ControllerException("时间类型转换失败!");
        }
    }
}
