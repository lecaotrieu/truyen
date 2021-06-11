package com.truyen.controller.common;

import com.truyenvn.core.command.AbstractCommand;
import com.truyenvn.core.common.WebConstant;
import com.truyenvn.core.dto.ParameterToQuery;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class GeneralFunction {
    ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
    public Map<String, String> buildMapMessage() {
        Map<String, String> mapMessage = new HashMap<String, String>();
        mapMessage.put(WebConstant.REDIRECT_ERROR, bundle.getString("label.message.error"));
        mapMessage.put(WebConstant.REDIRECT_INSERT, bundle.getString("label.user.message.add.success"));
        mapMessage.put(WebConstant.REDIRECT_DELETE, bundle.getString("label.user.message.delete.success"));
        mapMessage.put(WebConstant.REDIRECT_UPDATE, bundle.getString("label.user.message.update.success"));
        return mapMessage;
    }

    public ParameterToQuery buildParameterToQuery(AbstractCommand command) {
        ParameterToQuery parameterToQuery = new ParameterToQuery();
        parameterToQuery.setLimit(command.getMaxPageItems());
        parameterToQuery.setOffset(command.getFirstItem());
        parameterToQuery.setSortExpression(command.getSortExpression());
        parameterToQuery.setSortDirection(command.getSortDirection());
        Map<String, Object> mapValue = new HashMap<String, Object>();
        if (command.getSearch() != null && StringUtils.isNotBlank(command.getSearch())) {
            mapValue.put("search", command.getSearch());
            parameterToQuery.setProperties(mapValue);
        }
        return parameterToQuery;
    }
}
