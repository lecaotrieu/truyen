package com.truyenvn.core.utils;

import com.truyenvn.core.command.AbstractCommand;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
    public static void initSearchBean(HttpServletRequest request, AbstractCommand bean) {
        if (bean != null) {
            String sortExpression = request.getParameter(new ParamEncoder(bean.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_SORT));
            String sortDirection = request.getParameter(new ParamEncoder(bean.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_ORDER));
            String strPage = request.getParameter((new ParamEncoder(bean.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_PAGE)));
            Integer page = 1;
            if (org.apache.commons.lang.StringUtils.isNotBlank(strPage)) {
                try {
                    page = Integer.valueOf(strPage);
                } catch (Exception e) {

                }
            }
            bean.setPage(page);
            bean.setSortExpression(sortExpression);
            bean.setSortDirection(sortDirection);
            bean.setFirstItem((bean.getPage() - 1) * bean.getMaxPageItems());
        }
    }

    public static void initSearchBeanManual(AbstractCommand command) {
        if (command != null) {
            Integer page = 1;
            if (command.getPage() != 0) {
                page = command.getPage();
            }
            command.setPage(page);
            command.setFirstItem((command.getPage() - 1) * command.getMaxPageItems());
        }
    }
}
