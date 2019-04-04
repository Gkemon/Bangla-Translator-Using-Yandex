package com.gkemon.translatorusingyandex;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public
interface APIinterface {
    @FormUrlEncoded
    @POST("translate")
    Call<TranslatedLanguage> doTranslate(@Field("key") String key, @Field("text") String text, @Field("lang") String lang);

}
