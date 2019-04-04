package com.gkemon.translatorusingyandex;

        import com.google.gson.annotations.SerializedName;

        import java.util.List;

public
class TranslatedLanguage {
    @SerializedName("code")
    public Integer code;
    @SerializedName("lang")
    public String lang;
    @SerializedName("text")
    public List<String> texts;

    public
    TranslatedLanguage(Integer code, String lang, List<String> texts) {
        this.code = code;
        this.lang = lang;
        this.texts = texts;
    }

    public
    String getLang() {
        return lang;
    }

    public
    void setLang(String lang) {
        this.lang = lang;
    }

    public
    List<String> getText() {
        return texts;
    }

    public
    void setText(List<String> texts) {
        this.texts = texts;
    }

    public
    Integer getCode() {
        return code;
    }

    public
    void setCode(Integer code) {
        this.code = code;
    }
}
