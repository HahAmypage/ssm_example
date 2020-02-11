package com.davina.domain;

import java.util.List;

public class PageBean<T> {

    private Integer pageNum;

    private Integer pageSize;

    private Long totalCount;

    private Integer totalPage;

    private List<T> list;

    private Integer beginPage;

    private Integer endPage;

    public Integer getBeginPage() {
        //总页数少于要显示的页码
        if (getTotalPage() <= 5){
            beginPage = 1;
        }else {
            //总页数大于要显示的页码
            beginPage = pageNum - 2;

            //头溢出
            if (beginPage < 1){
                beginPage = 1;
            }else if (beginPage + 4 > getTotalPage()){
                //尾溢出
                beginPage = totalPage - 4;
            }


        }
        return beginPage;
    }

    public void setBeginPage(Integer beginPage) {
        this.beginPage = beginPage;
    }

    public Integer getEndPage() {
        //总页数少于要显示的页码
        if (getTotalPage() <= 5){
            endPage = getTotalPage();
        }else {
            //总页数大于要显示的页码
            endPage = pageNum + 2;
            //尾溢出
            if (endPage > getTotalPage()){
                endPage = getTotalPage();
            }else if (this.endPage - 4 < 1){
                //头溢出
                endPage = 5;
            }

        }

        return endPage;
    }

    public void setEndPage(Integer endPage) {
        this.endPage = endPage;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        totalPage = (int)(totalCount%pageSize == 0 ? totalCount/pageSize : (totalCount/pageSize)+1);
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public PageBean(Integer pageNum, Integer pageSize, Long totalCount, Integer totalPage, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.list = list;
    }

    public PageBean() {
    }
}
