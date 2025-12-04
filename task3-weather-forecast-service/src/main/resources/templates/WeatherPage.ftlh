<#import "/spring.ftl" as spring>
<#import "commons.ftlh" as c>

<@c.page>
    <div class="forecast datatable">
        <#if forecast??>
            24-hour forecast for
            <bold>${forecast.city!city!''}</bold> (${forecast.latitude}°, ${forecast.longitude}°), powered by <a
                href="https://open-meteo.com/" target="_blank">Open-Meteo API</a>

            <div id="temperatureChart" style="width:100%; height:400px;"></div>

            <table>
                <thead>
                <tr>
                    <th>Time</th>
                    <th>Temperature (°C)</th>
                </tr>
                </thead>
                <tbody>
                <#if forecast.hourlyForecasts?size == 0>
                    <tr class="noData">
                        <td colspan="2">No forecast data available</td>
                    </tr>
                <#else>
                    <#list forecast.hourlyForecasts as hour>
                        <tr>
                            <td>${hour?index + 1} - ${hour.time}</td>
                            <td>${hour.temperature?string("0.0")}</td>
                        </tr>
                    </#list>
                </#if>
                </tbody>
            </table>

            <script>
                var dates = [
                    <#list forecast.hourlyForecasts as hour>
                    '${hour.time}'<#sep>, </#sep>
                    </#list>
                ];


                var temperatures = [
                    <#list forecast.hourlyForecasts as hour>
                    ${hour.temperature?string?replace(',', '.')}<#sep>, </#sep>
                    </#list>
                ];

            </script>
        <#else>
            Please enter a city name to get weather forecast.
        </#if>
    </div>
    <style>
        .forecast {
            margin-top: 1rem;
        }
    </style>
</@c.page>