<#import "/spring.ftl" as spring>

<#macro page>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Doczilla File Sharing Service</title>
        <link rel="stylesheet" type="text/css" href="<@spring.url '/css/normalize.css'/>">
        <link rel="stylesheet" type="text/css" href="<@spring.url '/css/style.css'/>">
        <link rel="stylesheet" type="text/css" href="<@spring.url '/css/form.css'/>">
        <link rel="stylesheet" type="text/css" href="<@spring.url '/css/datatable.css'/>">
        <script src="<@spring.url '/js/jquery-3.6.1.min.js'/>"></script>
        <script src="<@spring.url '/js/notify.min.js'/>"></script>
        <script src="<@spring.url '/js/app.js'/>"></script>
    </head>
    <body>
    <header>
        <div class="header-container">
            <a href="/"><img
                        class="main-logo"
                        src="<@spring.url 'https://static.tildacdn.com/tild3837-6661-4663-b131-346334383531/Dz_DopLogo_Horiz_Lig.png'/>"
                        alt="Doczilla" title="Doczilla"/></a>
            <div class="enter-or-register-box">
                <#if user??>
                    ${user.login}
                    |
                    <a href="<@spring.url '/logout'/>">Logout</a>
                <#else>
                    <a href="<@spring.url '/enter'/>">Enter</a>
                    |
                    <a href="<@spring.url '/register'/>">Register</a>
                </#if>
            </div>
        </div>
        <nav>
            <ul>
                <li><a href="<@spring.url '/'/>">Home</a></li>
                <#if user??>
                    <li><a href="<@spring.url '/upload'/>">Upload</a></li>
                    <li><a href="<@spring.url '/documents'/>">Documents</a></li>
                </#if>
            </ul>

        </nav>
    </header>
    <div class="middle">
        <aside>
            <section>
                <div class="header">
                    Pay attention
                </div>
                <div class="body">
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cupiditate ducimus enim facere impedit
                    nobis,
                    nulla placeat quam suscipit unde voluptatibus.
                </div>
                <div class="footer">
                    <a href="#">View all</a>
                </div>
            </section>
        </aside>
        <main>
            <#nested/>
        </main>
    </div>
    <footer>
        <a href="/">Doczilla Task 2</a> &copy; 2025 by <a href="https://github.com/medvezhonokok">@medvezhonokok</a>
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
</#macro>

<#macro error field>
    <@spring.bind field/>
    <#if spring.status.errorMessage??>
        <div class="error">${spring.status.errorMessage}</div>
    </#if>
</#macro>