package leetcode;

import java.util.*;


/**
 * *
 * *换字母，给定一个输入单词，一个目标单词，一个字典。要求对输入单词每次换一个字母，并且换字母后形成的新单词要求在字典以内，通过逐步变化后，最终得到目标字母。
 * *比如：输入单词：DAMP；目标单词：LIKE；字典：{DAMP,LAMP,LIMP,LISP,LISE,LIKE,abcd,abc,ab}
 */
public class ReplaceOneCharOnce2TargetWord {

    public static LinkedList<String> transform(String startWord, String stopWord, Set<String> dictionary) {
//        startWord = startWord.toUpperCase();
//        stopWord = stopWord.toUpperCase();
        Queue<String> dealQueue = new LinkedList<String>();
        Set<String> visitedSet = new HashSet<String>();
        Map<String, String> backTraceMap = new TreeMap<String, String>();

        dealQueue.add(startWord);
        visitedSet.add(startWord);

        while (!dealQueue.isEmpty()) {
            String currentWord = dealQueue.poll();
            // For each possible word v from w with one edit operation
            for (String nextWord : getOneEditWords(currentWord)) {
                if (nextWord.equals(stopWord)) {       // Found our word! Now, back track.
                    LinkedList<String> list = new LinkedList<String>();
                    list.add(stopWord);        // Append v to list
                    while (currentWord != null) {
                        list.push(currentWord);
                        currentWord = backTraceMap.get(currentWord);
                    }
                    return list;
                }

                // If v is a dictionary word
                if (dictionary.contains(nextWord) && !visitedSet.contains(nextWord)) {
                    dealQueue.add(nextWord);
                    visitedSet.add(nextWord);          // mark visited
                    backTraceMap.put(nextWord, currentWord); // w is the previous state of v
                }
            }
        }
        return null;
    }

    // 得到任意改变一个字母的新word不重复集合
    private static Set<String> getOneEditWords(String word) {
        Set<String> words = new TreeSet<String>();

        for (int i = 0; i < word.length(); i++) {        // for every letter
            char[] wordArray = word.toCharArray();
            // change that letter to something else
            for (char c = 'A'; c <= 'Z'; c++) {
                if (c != word.charAt(i)) {
                    wordArray[i] = c;
                    words.add(new String(wordArray));
                }
            }
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != word.charAt(i)) {
                    wordArray[i] = c;
                    words.add(new String(wordArray));
                }
            }
        }
        return words;
    }

    public static HashSet<String> setupDictionary(String[] words) {
        HashSet<String> hash = new HashSet<String>();
        for (String word : words) {
            hash.add(word);
        }
        return hash;
    }

    public static void main(String[] args) {
        String[] words = {"DAMP", "LAMP", "LIMP", "LISP", "LISE", "LIKE", "abcd", "abc", "ab"};
        HashSet<String> dict = setupDictionary(words);
        LinkedList<String> list = transform("DAMP", "LIKE", dict);
        for (String word : list) {
            System.out.println(word);
        }
    }
}