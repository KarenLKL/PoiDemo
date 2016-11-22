package com.newbee.poi.config.Exception;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 统一异常处理类
 * <p>
 * Created by Major on 2016/11/17.
 */
@ControllerAdvice
@RestController
@RequestMapping(value = "/error")
public class ExceptionHandlerControllr implements ErrorController {

    private ErrorAttributes errorAttributes;

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerControllr.class);

    @Autowired
    private ServerProperties serverProperties;

    /**
     * 初始化ExceptionController
     *
     * @param errorAttributes
     */
    @Autowired
    public ExceptionHandlerControllr(ErrorAttributes errorAttributes) {
        Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
        this.errorAttributes = errorAttributes;
    }

    /**
     * 定义404的modelAndVIew
     *
     * @param request
     * @param response
     * @return
     */
    /*@RequestMapping(value = "/404",produces = "text/html")
    public ModelAndView error404Html(HttpServletRequest request, HttpServletResponse response){
        response.setStatus(getStatus(request).value());
        Map<String, Object> model = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.TEXT_HTML));
        return new ModelAndView("error/404.html", model);
    }*/

    /**
     * 定义404的json数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/404")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> error404Json(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> body = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.TEXT_HTML));

        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(body, status);
    }

    /**
     * 定义500的ModelAndView
     * @param request
     * @param response
     * @return
     */
    /*@RequestMapping(produces = "text/html",value = "/500")
    public ModelAndView errorHtml500(HttpServletRequest request,
                                     HttpServletResponse response) {
        response.setStatus(getStatus(request).value());
        Map<String, Object> model = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.TEXT_HTML));
        return new ModelAndView("error/500.html", model);
    }
*/

    /**
     * 定义500的错误JSON信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/500")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> error500(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.TEXT_HTML));
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(body, status);
    }

    /**
     * 获取错误信息
     *
     * @param request
     * @param includeStackTrace
     * @return
     */
    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        ServletRequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return this.errorAttributes.getErrorAttributes(requestAttributes,
                includeStackTrace);
    }

    /**
     * @param request
     * @param textHtml
     * @return
     */
    private boolean isIncludeStackTrace(HttpServletRequest request, MediaType textHtml) {
        ErrorProperties.IncludeStacktrace includeStacktrace = this.serverProperties.getError().getIncludeStacktrace();
        ErrorProperties.IncludeStacktrace include = this.serverProperties.getError().getIncludeStacktrace();
        if (include == ErrorProperties.IncludeStacktrace.ALWAYS) {
            return true;
        }
        if (include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM) {
            return getTraceParameter(request);
        }
        return false;
    }

    /**
     * 是否包含trace
     *
     * @param request
     * @return
     */
    private boolean getTraceParameter(HttpServletRequest request) {
        String trace = request.getParameter("trace");
        if (trace == null)
            return false;
        return !"false".equals(trace.toLowerCase());
    }


    /**
     * 获取错误编码
     *
     * @param request
     * @return
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    /**
     * 实现错误路径,暂时无用
     *
     * @return
     * @see
     */
    @Override
    public String getErrorPath() {
        return this.serverProperties.getError().getPath();
    }

    /**
     * 全局异常信息处理
     *
     * @param request
     * @param e
     */
    //@ExceptionHandler(value = {MethodArgumentNotValidException.class,RuntimeException.class,ClassCastException.class})
    @ExceptionHandler(value = Exception.class)
    public void defualtExceptionHandler(HttpServletRequest request, Exception e) {
        LOGGER.error("-------------------------------------发生异常啦-----------------------------------------");
        LOGGER.error(e.getMessage());
    }
}
