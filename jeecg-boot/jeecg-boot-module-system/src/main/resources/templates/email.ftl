<!DOCTYPE html>
<html>
<body>
    <font class="myTitle">下列公司距离到期还有${interval}天,请及时通知公司联系人</font><br><br>
    <table class="gridtable">
        <tr>
            <th>公司名称</th>
            <th>统一信用码</th>
            <th>注册时间</th>
            <th>地址</th>
            <th>法人</th>
            <th>法人身份证</th>
            <th>法人电话</th>
            <th>实例控制人</th>
            <th>注册类型</th>
            <th>开始时间</th>
            <th>到期时间</th>
        </tr>
        <#list copmanyInfoList as copmanyInfo>
        <tr>
            <td><#if copmanyInfo.companyName??>${copmanyInfo.companyName}<#else></#if></td>
            <td><#if copmanyInfo.creditCode??>${copmanyInfo.creditCode}<#else></#if></td>
            <td>${(copmanyInfo.registerDate?string("yyyy-MM-dd"))!}
            <td><#if copmanyInfo.address??>${copmanyInfo.address}<#else></#if></td>
            <td><#if copmanyInfo.regalPersonName??>${copmanyInfo.regalPersonName}<#else></#if></td>
            <td><#if copmanyInfo.regalPersonId??>${copmanyInfo.regalPersonId}<#else></#if></td>
            <td><#if copmanyInfo.regalPersonTel??>${copmanyInfo.regalPersonTel}<#else></#if></td>
            <td><#if copmanyInfo.controller??>${copmanyInfo.controller}<#else></#if></td>
            <td><#if copmanyInfo.registerType??>${copmanyInfo.registerType}<#else></#if></td>
            <td>${(copmanyInfo.startDate?string("yyyy-MM-dd"))!}
            <td>${(copmanyInfo.expireDate?string("yyyy-MM-dd"))!}

            </td>

        </tr>
        </#list>
    </table></br><br>
</body>
<style type="text/css">
    table.gridtable {
        font-family: verdana,arial,sans-serif;
        font-size:11px;
        color:#333333;
        border-width: 1px;
        border-color: #666666;
        border-collapse: collapse;
    }
    table.gridtable th {
        border-width: 1px;
        padding: 8px;
        border-style: solid;
        border-color: #666666;
        background-color: #dedede;
    }
    table.gridtable td {
        border-width: 1px;
        padding: 8px;
        border-style: solid;
        border-color: #666666;
        background-color: #ffffff;
    }
    .cu{ font-weight:bold}
    .myTitle{
        font-weight:bold;
        font-size: large;
    }
</style>
</html>