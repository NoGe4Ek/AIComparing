package dev.aphirri;

import dev.aphirri.ai.chatGPT.ChatGPT;
import dev.aphirri.ai.copilot.Copilot;
import dev.aphirri.ai.gemini.Gemini;
import dev.aphirri.ai.yandexGPT.YandexGPT;

public class Main {
    public static void main(String[] args) {
        ChatGPT chatGPT = new ChatGPT();
        Copilot copilot = new Copilot();
        Gemini gemini = new Gemini();
        YandexGPT yandexGPT = new YandexGPT();

        chatGPT.generate();
        copilot.generate();

        String s = "login = login";
    }
}