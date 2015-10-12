package com.qeeka.http;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by Neal on 10/12 0012.
 */
@XmlRootElement(name = "base_search_response")
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseSearchResponse<T> {
    @XmlElement(name = "entity")
    protected T entity;
    @XmlElementWrapper(name = "record_list")
    @XmlElement(name = "record")
    protected List<T> recordList;
    @XmlElement(name = "total_record")
    protected Long totalRecords;
    @XmlElement(name = "page_index")
    protected int pageIndex;
    @XmlElement(name = "page_size")
    protected int pageSize;

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public List<T> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<T> recordList) {
        this.recordList = recordList;
    }

    public Long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
