<#import "/spring.ftl" as spring>

<#macro page>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Doczilla Weather Forecast Service</title>
        <link rel="stylesheet" type="text/css" href="<@spring.url '/css/normalize.css'/>">
        <link rel="stylesheet" type="text/css" href="<@spring.url '/css/style.css'/>">
        <link rel="stylesheet" type="text/css" href="<@spring.url '/css/form.css'/>">
        <link rel="stylesheet" type="text/css" href="<@spring.url '/css/datatable.css'/>">
        <script src="https://code.highcharts.com/highcharts.js"></script>
        <script src="<@spring.url '/js/jquery-3.6.1.min.js'/>"></script>
        <script src="<@spring.url '/js/jquery-3.6.1.min.js'/>"></script>
        <script src="<@spring.url '/js/notify.min.js'/>"></script>
        <script src="<@spring.url '/js/app.js'/>"></script>
        <script src="<@spring.url '/js/weather-chart.js'/>"></script>
    </head>
    <body>
    <header>
        <div class="header-container">
            <a href="/"><img
                        class="main-logo"
                        src="<@spring.url 'https://static.tildacdn.com/tild3837-6661-4663-b131-346334383531/Dz_DopLogo_Horiz_Lig.png'/>"
                        alt="Doczilla" title="Doczilla"/></a>
            <div class="enter-or-register-box">
                Cache size
                |
                <b>${cacheSize!0}</b>
            </div>
        </div>
        <nav>
            <ul>
                <li class="active"><a href="/weather?city=Moscow">Weather Forecast</a></li>
                <#if forecast??>
                    <li>
                        <a href="https://geocoding-api.open-meteo.com/v1/search?name=${forecast.city?url}">Geocoding
                            API</a>
                    </li>
                    <li>
                        <a href="https://api.open-meteo.com/v1/forecast?latitude=${forecast.latitude?replace(',', '.')}&longitude=${forecast.longitude?replace(',', '.')}&hourly=temperature_2m&timezone=Europe/Moscow&forecast_days=2">Forecast
                            API</a>
                    </li>
                </#if>
            </ul>
        </nav>
    </header>

    <div class="middle">
        <main>
            <#nested />
        </main>
    </div>
    <footer>
        <a href="/weather">Doczilla Task 3</a> &copy; 2025 by <a
                href="https://github.com/medvezhonokok">@medvezhonokok</a>
    </footer>
    <script>
        $(function () {
            <#if message??>
            notify("${message?js_string}");
            </#if>
        })
    </script>
    </body>
    </html>
    <style>
        .active {
            font-weight: bold;
            text-decoration: underline;
        }
    </style>
</#macro>
