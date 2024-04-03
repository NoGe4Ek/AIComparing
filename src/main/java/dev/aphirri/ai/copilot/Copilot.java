package dev.aphirri.ai.copilot;

import dev.aphirri.ai.AI;

public class Copilot implements AI {

    @Override
    public void generate() {
        BubbleSketch.generate();
    }
}