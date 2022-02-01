package io.github.renjujv.meor.entity;

public class Category {
    private final String[] categories = { "Media", "Documents", "Software" };

    private final String[][] subCategories = {
            { "Audio", "Video", "Images", "." },
            { "Rich Text", "Spreadsheets", "Presentations", "eBooks", "Web Pages", "Pdfs", "." },
            { "Windows", "Linux", "Mac", "Android", "iOS", "." }
    };

    private final String[][][] extensions = {
            {
                { "mp3", "aac", "ogg", "flac", "m4a", "wma", "wav", "ape", "." },
                { "avi", "mkv", "wmv", "webm", "flv", "mp4", "3gp", "." },
                { "jpeg", "jpg", "png", "gif", "." }
            },
            {
                { "doc", "txt", "odt", "." },
                { "xls", "odg", "." },
                { "ppt", "pptx", "odp", "." },
                { "ePub", "." },
                { "htm", "html", "xhtml", "mht", "." },
                { "pdf", "." }
            },
            {
                { "exe", "msi", "." },
                { "deb", "rpm", "run", "mint", "app", "gz", "." },
                { "app", "dmg", "." },
                { "apk", "aapk", "." },
                { "ipa", ".app", "." }
            }
    };

    public String[] getCategories() { return categories;}

    public String[][] getSubCategories() {
        return subCategories;
    }

    public String[][][] getExtensions() {
        return extensions;
    }
}
