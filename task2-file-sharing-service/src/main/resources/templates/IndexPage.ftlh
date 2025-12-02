<#import "commons.ftlh" as c>

<@c.page>
    <#if user??>
        Welcome, ${user.login}!
    <#else>
        Welcome on Task2 solution!
        <br/>
        <br/>
        Для получения наилучшего опыта использования платформой, пожалуйста, <a href="/register">зарегистрируйтесь</a>
    </#if>
</@c.page>
