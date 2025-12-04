document.addEventListener('DOMContentLoaded', function () {
    if (typeof dates !== 'undefined' && typeof temperatures !== 'undefined') {
        Highcharts.chart('temperatureChart', {
            chart: {
                type: 'line',
                title: 'Forecast chart'
            },
            title: {
                text: '',
                align: 'left'
            },
            xAxis: {
                categories: dates.map(d => d.split(" ")[1]),
                title: {
                    text: 'Time'
                }
            },
            yAxis: {
                title: {
                    text: 'Temperature (°C)'
                }
            },
            series: [{
                // name: 'Temperature',
                data: temperatures,
                color: '#e94378',
            }],
            plotOptions: {}
        });
    }
});