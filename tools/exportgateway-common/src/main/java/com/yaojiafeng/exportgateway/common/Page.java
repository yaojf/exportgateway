package com.yaojiafeng.exportgateway.common;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/4/21 下午10:06 $
 */
public class Page {

    private static final Integer DEFUALT_PAGE_SIZE = 10;

    private static final Integer DEFAULT_TOTAL_NUM = 0;

    private Integer pageSize = DEFUALT_PAGE_SIZE;

    private Integer currentPage = 1;

    private Integer totalNum = DEFAULT_TOTAL_NUM;

    private Integer offset;

    private Integer limit;

    private Integer totalPage;

    public Page(Integer currentPage, Integer pageSize) {
        this.pageSize = pageSize;
        setCurrentPage(currentPage);
    }

    public Page(Integer currentPage, Integer pageSize, Integer totalNum) {
        super();
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalNum = totalNum;
    }

    public Page() {
        setPageSize(DEFUALT_PAGE_SIZE);
    }

    public Integer getTotalPageNum() {
        if (totalNum == 0) {
            return Integer.valueOf(0);
        }
        return totalNum / pageSize + (totalNum % pageSize == 0 ? 0 : 1);
    }


    public Integer getPageSize() {
        return pageSize;
    }


    public void setPageSize(Integer pageSize) {
        if (pageSize == null) {
            this.pageSize = DEFUALT_PAGE_SIZE;
        } else {
            this.pageSize = pageSize;
        }
        caculatIndex();
    }

    public Integer getCurrentPage() {
        return currentPage;
    }


    public void setCurrentPage(Integer currentPage) {
        if (currentPage == null) {
            this.currentPage = 1;
        } else {
            this.currentPage = currentPage;
        }
        caculatIndex();
    }


    public Integer getTotalNum() {
        return totalNum;
    }


    public void setTotalNum(Integer totalNum) {
        if (totalNum == null) {
            this.totalNum = DEFAULT_TOTAL_NUM;
            return;
        }
        this.totalNum = totalNum;
    }

    private void caculatIndex() {
        if (this.currentPage == null || this.currentPage <= 0) {
            this.currentPage = 1;
        }
        if (this.pageSize == null || this.pageSize <= 0) {
            this.pageSize = DEFUALT_PAGE_SIZE;
        }
        this.offset = (this.currentPage - 1) * this.pageSize;
        this.limit = this.pageSize;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getTotalPage() {
        return 1 + this.getTotalNum() / this.getPageSize();
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }


}
