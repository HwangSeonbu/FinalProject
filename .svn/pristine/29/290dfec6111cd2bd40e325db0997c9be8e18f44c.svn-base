<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 5. 18.      작성자명      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>/*#39bbdb*/
.clearfix:before,
.clearfix:after {
    display: table;
    content: ' ';
}
.clearfix:after {
    clear: both;
}
body {
    background: #f0f0f0 !important;
}
.page-404 .outer {
    position: absolute;
    top: 8%;
    display: table;
    width: 80%;
    height: 80%;
}
.page-404 .outer .middle {
    display: table-cell;
    vertical-align: middle;
}
.page-404 .outer .middle .inner {
    width: 600px;
    margin-right: auto;
    margin-left: 20%;
}
.page-404 .outer .middle .inner .inner-circle {
    height: 150px;
    border-radius: 50%;
    background-color: #ffffff;
}
.page-404 .outer .middle .inner .inner-circle:hover i {
    color: #de7d0d!important;/*here*/
    background-color: #f5f5f5;
    box-shadow: 0 0 0 15px #de7d0d;/*here*/
}
.page-404 .outer .middle .inner .inner-circle:hover span {
    color: #de7d0d;/*here*/
}
.page-404 .outer .middle .inner .inner-circle i {
    font-size: 5em;
    line-height: 1em;
    float: right;
    width: 1.6em;
    height: 1.6em;
    margin-top: -.7em;
    margin-right: -.5em;
    padding: 20px;
    -webkit-transition: all .4s;
            transition: all .4s;
    text-align: center;
    color: #f5f5f5!important;
    border-radius: 50%;
    background-color: #de7d0d;/*here*/
    box-shadow: 0 0 0 15px #f0f0f0;
}
.page-404 .outer .middle .inner .inner-circle span {
    font-size: 5em;
    font-weight: 700;
    line-height: 1.2em;
    display: block;
    -webkit-transition: all .4s;
            transition: all .4s;
    text-align: center;
    color: #e0e0e0;
}
.page-404 .outer .middle .inner .inner-status {
    font-size: 20px;
    display: block;
    margin-top: 20px;
    margin-bottom: 5px;
    text-align: center;
    color: #de7d0d;/*here*/
    font-weight: bold;
}
.page-404 .outer .middle .inner .inner-detail {
    line-height: 1.4em;
    display: block;
    margin-bottom: 10px;
    text-align: center;
    color: black;
    font-weight: bold;
}
</style>

<%--     <h1>${action } 기간이 아닙니다.</h1> --%>
<%--     <h2>${accessPeriod }</h2> --%>

<div class="page-404">
    <div class="outer">
        <div class="middle">
            <div class="inner">
                <div class="inner-circle"><i class="fa fa-cogs"></i><span>${action }</span></div>
                <span class="inner-status">지금은 ${action } 기간이 아닙니다.</span>
                <span class="inner-detail">${accessPeriod }</span>
            </div>
        </div>
    </div>
</div>