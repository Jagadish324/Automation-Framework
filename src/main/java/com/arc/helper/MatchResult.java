package com.arc.helper;

import java.util.List;

public class MatchResult {
    public final double matchPercentage;
    public final List<String> nonMatchingWords;
    public final List<Integer> nonMatchingIndices;

    public MatchResult(double matchPercentage, List<String> nonMatchingWords, List<Integer> nonMatchingIndices) {
        this.matchPercentage = matchPercentage;
        this.nonMatchingWords = nonMatchingWords;
        this.nonMatchingIndices = nonMatchingIndices;
    }

    public double getMatchPercentage() {
        return matchPercentage;
    }

    public List<String> getNonMatchingWords() {
        return nonMatchingWords;
    }
    public List<Integer> getNonMatchingIndices() {
        return nonMatchingIndices;
    }

}
