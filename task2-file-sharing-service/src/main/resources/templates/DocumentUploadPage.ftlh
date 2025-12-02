<#import "commons.ftlh" as c>

<@c.page>
    <div class="upload-container">
        <h2>Select file to upload</h2>
        <form method="post" action="/upload" enctype="multipart/form-data">
            <div class="form-group">
                <input type="file" name="file" id="file" required>
            </div>
            <button type="submit" class="upload-btn">Upload</button>
        </form>
    </div>

    <style>
        .upload-container {
            height: 100%;
            display: flex;
            flex-direction: column;
            gap: 1rem;
        }

        .upload-btn {
            margin-top: 2rem;
            padding: 10px 20px;
            background: #e94378;
            color: black;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .success {
            color: green;
            margin-top: 10px;
            padding: 10px;
            background: #d4edda;
            border: 1px solid #c3e6cb;
            border-radius: 4px;
        }

        .error {
            color: var(--error-color);
            margin-top: 10px;
            padding: 10px;
            background: #f8d7da;
            border: 1px solid #f5c6cb;
            border-radius: 4px;
        }
    </style>
</@c.page>