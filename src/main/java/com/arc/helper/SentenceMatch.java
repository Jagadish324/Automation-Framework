package com.arc.helper;

import java.util.*;

public class SentenceMatch {
    public static MatchResult returnMatchData(String data1, String data2) {
        MatchResult result = getMatchPercentage(data1, data2);
        return result;
    }

    public static MatchResult getMatchPercentage(String sentence1, String sentence2) {
        int matchCount = 0;
        List<String> nonMatchingWords = new ArrayList<>();
        List<Integer> nonMatchingIndex = new ArrayList<>();
        List<String> sentenceList1 = Arrays.asList(sentence1.split("\\s+"));
        Map<String, Integer> sentenceListMap1 = getWordCount(sentenceList1);

        List<String> sentenceList2 = Arrays.asList(sentence2.split("\\s+"));
        Map<String, Integer> sentenceListMap2 = getWordCount(sentenceList2);

        for (int i = 0; i < sentenceList1.size(); i++) {
            String word = sentenceList1.get(i);
            if (sentenceListMap2.containsKey(word) && sentenceListMap2.get(word) > 0) {
                matchCount++;
                sentenceListMap2.put(word, sentenceListMap2.get(word) - 1);
            } else {
                nonMatchingWords.add(word);
                nonMatchingIndex.add(i);
            }
        }
        // Include words from sentence2 that are not in sentence1
        for (int i = 0; i < sentenceList2.size(); i++) {
            String word = sentenceList2.get(i);
            if (sentenceListMap1.containsKey(word) && sentenceListMap1.get(word) > 0) {
                sentenceListMap1.put(word, sentenceListMap1.get(word) - 1);
            } else if (!nonMatchingWords.contains(word)) {
                nonMatchingWords.add(word);
                nonMatchingIndex.add(i + sentenceList1.size());
            }
        }

        int totalWords = Math.max(sentenceList1.size(), sentenceList2.size());
        double matchPercentage = ((double) matchCount / totalWords) * 100;

        return new MatchResult(matchPercentage, nonMatchingWords, nonMatchingIndex);
    }

    public static String highlightNonMatchingWords(String sentence, List<Integer> nonMatchingIndices, int offset) {
        String[] words = sentence.split("\\s+");
        StringBuilder highlightedSentence = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (nonMatchingIndices.contains(i + offset)) {
                highlightedSentence.append("\033[1;31m").append(words[i]).append("\033[0m").append(" ");
            } else {
                highlightedSentence.append(words[i]).append(" ");
            }
        }

        return highlightedSentence.toString().trim();
    }

    public static Map<String, Integer> getWordCount(List<String> words) {
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        return wordCount;
    }
}


