<#import "commons.ftlh" as c>

<@c.page>
    <div class="documents datatable">
        <table>
            <thead>
            <tr>
                <th>User</th>
                <th>File Name</th>
                <th>Upload Time</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <#if documents?size==0>
                <tr class="noData">
                    <td colspan="4">
                        No data
                    </td>
                </tr>
            <#else>
                <#list documents as document>
                    <tr>
                        <td class="login">${document.user.login}</td>
                        <td class="fileName">${document.hashedFileName}</td>
                        <td class="uploadTime">${document.uploadTime?string('yyyy-MM-dd HH:mm')}</td>
                        <td class="action">
                            <a href="/download/${document.hashedFileName}" class="download-btn">Download</a>
                        </td>
                    </tr>
                </#list>
            </#if>
            </tbody>
        </table>
    </div>
</@c.page>