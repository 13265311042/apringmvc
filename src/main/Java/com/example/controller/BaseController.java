package com.example.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hyt
 */
public class BaseController {
    protected HttpServletResponse response;
    protected HttpServletRequest request;
    protected Logger logger = Logger.getLogger(getClass());

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }
}
