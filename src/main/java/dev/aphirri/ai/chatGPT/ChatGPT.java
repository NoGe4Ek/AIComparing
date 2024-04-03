package dev.aphirri.ai.chatGPT;

import dev.aphirri.ai.AI;

public class ChatGPT implements AI {

    @Override
    public void generate() {
        generateSecondTry();
    }

    private void generateFirstTry() {
        BubblesSimulation.generate();
    }

    private void generateSecondTry() {
        BubblePhysics.generate();
    }
}
