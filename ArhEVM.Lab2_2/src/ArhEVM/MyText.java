/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArhEVM;

/**
 *
 * @author andre
 */
public class MyText {
    private final String text;
    
    public MyText(String text) {
        this.text = text;
    }
    public String FindWords() {
        String shortestWord = this.text,
                longestWord = "",
                currentWord = "",
                result = "";
        int indexOfSentence = 1;
        for (char chr : this.text.toCharArray())
            switch (chr) {
                case ' ':
                case ',':
                case '\n':
                    if (currentWord.isEmpty()) break;
                    shortestWord = (currentWord.length() < shortestWord.length()
                            ? currentWord : shortestWord);
                    longestWord = (currentWord.length() > longestWord.length()
                            ? currentWord : longestWord);
                    currentWord = "";
                    break;
                case '.':
                    if (longestWord.isEmpty() && currentWord.isEmpty()) break;
                    if (!currentWord.isEmpty()) {
                        shortestWord = (currentWord.length() < shortestWord.length()
                                ? currentWord : shortestWord);
                        longestWord = (currentWord.length() > longestWord.length()
                                ? currentWord : longestWord);
                    }
                    result += "В "+(indexOfSentence++)+"-м предложении самое "
                            + "короткое слово - \"" + shortestWord + "\", а "
                            + "самое длинное - \"" + longestWord + "\".\n";
                    shortestWord = this.text;
                    longestWord = "";
                    currentWord = "";
                    break;
                default:
                    currentWord += chr;
            }
        return result;
    }
}
