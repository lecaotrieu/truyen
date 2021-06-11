package com.truyenvn.core.utils;

import com.truyenvn.core.common.WebConstant;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class WebCommonUtil {
    public static void addRedirectMessage(HttpServletRequest request, Map<String, String> mapMessage, String crudaction) {
        if (StringUtils.isNotBlank(crudaction) && crudaction.equals(WebConstant.REDIRECT_INSERT)) {
            request.setAttribute(WebConstant.MESSAGE_RESPONSE, mapMessage.get(WebConstant.REDIRECT_INSERT));
            request.setAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
        } else if (StringUtils.isNotBlank(crudaction) && crudaction.equals(WebConstant.REDIRECT_UPDATE)) {
            request.setAttribute(WebConstant.MESSAGE_RESPONSE, mapMessage.get(WebConstant.REDIRECT_UPDATE));
            request.setAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
        } else if (StringUtils.isNotBlank(crudaction) && crudaction.equals(WebConstant.REDIRECT_ERROR)) {
            request.setAttribute(WebConstant.MESSAGE_RESPONSE, mapMessage.get(WebConstant.REDIRECT_ERROR));
            request.setAttribute(WebConstant.ALERT, WebConstant.TYPE_ERROR);
        } else if (StringUtils.isNotBlank(crudaction) && crudaction.equals(WebConstant.REDIRECT_DELETE)) {
            request.setAttribute(WebConstant.MESSAGE_RESPONSE, mapMessage.get(WebConstant.REDIRECT_DELETE));
            request.setAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
        } else {
            request.setAttribute(WebConstant.MESSAGE_RESPONSE, mapMessage.get(crudaction));
            request.setAttribute(WebConstant.ALERT, WebConstant.TYPE_ERROR);
        }
    }
}
