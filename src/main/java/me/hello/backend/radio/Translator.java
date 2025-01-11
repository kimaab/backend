package me.hello.backend.radio;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class Translator {
    public static void main(String[] args) {
        // API 키를 사용하여 Translate 객체를 생성합니다.
        Translate translate = TranslateOptions.newBuilder()
                .setApiKey("AIzaSyA17p24-sdYhDqM4gH4qF4pLepcO2Yvu8M") // 여기에 생성한 API 키를 넣으세요.
                .build()
                .getService();

        // 텍스트 번역 예시
        Translation translation = translate.translate("안녕하세요",
                Translate.TranslateOption.targetLanguage("en"));

        System.out.println("Translated text: " + translation.getTranslatedText());
    }
}
