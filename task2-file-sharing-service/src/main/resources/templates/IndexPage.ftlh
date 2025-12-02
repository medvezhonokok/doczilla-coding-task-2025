<#import "commons.ftlh" as c>

<@c.page>
    <#if user??>
        Welcome, ${user.login}!
    <#else>
        Welcome on Task2 solution!
    </#if>
</@c.page>
