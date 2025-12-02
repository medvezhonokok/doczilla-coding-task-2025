<#import "commons.ftlh" as c>

<@c.page>
    <div class="fileUpload-container">
        <h2>Select file to upload</h2>
        <form method="post" action="/upload" enctype="multipart/form-data">
            <div class="form-group">
                <input type="file" name="file" id="file" required>
            </div>
            <button type="submit" class="fileUpload-btn">Upload</button>
        </form>
    </div>

    <style>
        .fileUpload-container {
            height: 100%;
            display: flex;
            flex-direction: column;
            gap: 1rem;
        }

        .fileUpload-btn {
            margin-top: 2rem;
            padding: 10px 20px;
            background: #e94378;
            color: black;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>
</@c.page>