$(function() {
    var myChart = echarts.init(document.getElementById('report'));
    common.ajax({
        url : $('#basePath').val() + '/orderReport/total',
        success : function(data) {
            var option = {
                title: {
                    text: '类别营业额报表'
                },
                tooltip: {
                    trigger: 'axis'
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false
                },
                yAxis: {
                    type: 'value'
                }
            };
            $.extend(true,option,data);
            myChart.setOption(option);
        },
        type : 'GET'
    });
});