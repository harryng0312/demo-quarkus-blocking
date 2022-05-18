package org.harryng.demo.quarkus.util.page;

import io.quarkus.panache.common.Sort;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PagedResult<T> implements Serializable {

    private long total = 0L;
    private List<T> content = null;
    private PageInfo pageInfo = null;

    private static PagedResult EMPTY_PAGE = null;

    public static PagedResult emptyPage(){
        if(EMPTY_PAGE == null){
            EMPTY_PAGE = new PagedResult(Collections.emptyList(), new PageInfo(0 ,1, Sort.empty()), 0);
        }
        return EMPTY_PAGE;
    }

    public PagedResult(List<T> content, PageInfo pageInfo, long total) {
        this.total = total;
        this.content = content;
        this.pageInfo = pageInfo;
    }

    public long getTotal() {
        return total;
    }


    public List<T> getContent() {
        if (content == null) {
            content = new ArrayList<>();
        }
        return content;
    }

    public PageInfo getPageInfo() {
        if(pageInfo == null){
            pageInfo = new PageInfo(0, 0, Sort.empty());
        }
        return pageInfo;
    }
}
