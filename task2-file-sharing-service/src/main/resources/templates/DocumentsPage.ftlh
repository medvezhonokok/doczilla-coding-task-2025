<#import "commons.ftlh" as c>

<@c.page>
    <div class="documents datatable">
        <table>
            <thead>
            <tr>
                <th>User</th>
                <th>File name</th>
                <th>Upload Time</th>
                <th>Link to download</th>
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
                            <button
                                    class="copy-link-button"
                                    data-link="/download/${document.hashedFileName}"
                                    onclick="copyToClipboard(this)">
                                Click to copy
                            </button>
                        </td>
                    </tr>
                </#list>
            </#if>
            </tbody>
        </table>
    </div>

    <script>
        const baseUrl = "http://localhost:8080/";

        function copyToClipboard(button) {
            notify("Copied!")

            const originalText = button.textContent;

            navigator.clipboard.writeText(baseUrl + button.getAttribute('data-link'))
                .then(() => {
                    button.textContent = "Copied!";
                })
                .catch(err => {
                    console.error('Failed to copy: ', err);
                    button.textContent = 'Error!';
                })
                .finally(() => {
                    setTimeout(() => {
                        button.textContent = originalText;
                    }, 2000);
                })
        }
    </script>

    <style>
        .copy-link-button {
            padding: 6px 12px;
            background: var(--border-color);
            color: white;
            border: 2px solid var(--border-color);
            border-radius: var(--border-radius);
            cursor: pointer;
        }
    </style>
</@c.page>