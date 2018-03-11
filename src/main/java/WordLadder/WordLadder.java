package WordLadder;

import java.io.*;
import java.util.*;

public class WordLadder {
    String word1,word2;
    Set<String> dictionary;

    public WordLadder(){}

    public WordLadder(String s1,String s2,Set<String> set){
        word1 = s1;
        word2 = s2;
        dictionary = set;
    }

    public void setDict(Set<String> set){
        dictionary = set;
    }

    public void setWord(String s1, String s2){
        word1 = s1;
        word2 = s2;
    }

    public boolean wordCheck(){
        for(char letter:word1.toCharArray()){
            if('a'<=letter && letter<='z') continue;
            else return false;
        }

        for(char letter:word2.toCharArray()){
            if('a'<=letter && letter<='z') continue;
            else return false;
        }

        return true;
    }

    public boolean isSameLength(){
        return word1.length()==word2.length();
    }

    public String replaceChar(String str, int index, char letter){
        String newStr = str.substring(0,index)+ letter + str.substring(index + 1,str.length());
        return newStr;
    }

    public String insertChar(String str, int index, char letter){
        String newStr = str.substring(0,index)+ letter + str.substring(index,str.length());
        return newStr;
    }

    public String removeChar(String str, int index){
        String newStr = str.substring(0,index) + str.substring(index + 1,str.length());
        return newStr;
    }

    public boolean transfer(){
        Queue<Stack<String>> pathQueue = new LinkedList<Stack<String>>();
        Stack<String> initialPath = new Stack<String>();
        Set<String> usedWord = new HashSet<String>();

        initialPath.push(word1);
        pathQueue.offer(initialPath);
        usedWord.add(word1);

        while(!pathQueue.isEmpty()){
            Stack<String> tempPath = pathQueue.poll();
            String tempWord = tempPath.peek();
            int tempSize;

            if(isSameLength()){
                tempSize = word1.length();
            }
            else{
                tempSize = 2 * tempWord.length() + 1;
            }

            for(int i = 0;i<tempSize;i++){
                for(char letter = 'a';letter<='z';letter++){
                    tempWord = tempPath.peek();

                    if(isSameLength()){
                        tempWord = replaceChar(tempWord,i,letter);
                    }
                    else{
                        if(i % 2 == 1){
                            tempWord = replaceChar(tempWord,(i-1)/2,letter);
                        }
                        else{
                            if(tempWord.length() < word2.length()){
                                tempWord = insertChar(tempWord,i/2,letter);
                            }
                            else{
                                if (i / 2 <tempWord.length()){
                                    tempWord = removeChar(tempWord,i/2);
                                }
                            }
                        }
                    }

                    if(usedWord.contains(tempWord)) continue;
                    if(!tempWord.equals(word2) && !dictionary.contains(word2)) continue;

                    Stack<String> newPath = (Stack<String>) tempPath.clone();
                    newPath.push(tempWord);
                    usedWord.add(tempWord);

                    if(tempWord.equals(word2)){
                        System.out.println("A ladder from" + word2 + " back to " + word1 + ";");
                        while(!newPath.isEmpty()){
                            System.out.print(newPath.pop() + " ");
                        }
                        System.out.println();
                        return true;
                    }
                    pathQueue.offer(newPath);

                }
            }
        }
        System.out.println("There is no ladder!");
        return false;

    }

    public static void main(String[] args) throws IOException
    {
        String filename,filepath;
        String path = "src\\main\\java\\WordLadder\\";
        String line;
        String wordA,wordB;
        Set<String> dictionary = new HashSet<String>();
        WordLadder wordLadder = new WordLadder();

        System.out.println("Dictionary file name?");
        Scanner scan = new Scanner(System.in);
        filename = scan.nextLine();
        filepath = path + filename;
        File fileReader = new File(filepath);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(fileReader));
        BufferedReader wordBuffer = new BufferedReader(reader);
        line = wordBuffer.readLine();
        while(line != null){
            dictionary.add(line);
            line = wordBuffer.readLine();
        }
        wordLadder.setDict(dictionary);

        while(true) {
            System.out.println("Word #1 (or Enter to quit):");
            wordA = scan.nextLine();
            if(wordA.length() == 0) break;
            wordA.toLowerCase();

            System.out.println("Word #2 (or Enter to quit):");
            wordB = scan.nextLine();
            if(wordB.length() == 0) break;
            wordB.toLowerCase();

            if (wordA.equals(wordB)){
                System.out.println("The two words must be different");
                continue;
            }

            wordLadder.setWord(wordA,wordB);
            if(!wordLadder.wordCheck()){
                System.out.println("Word Illegal!");
            }
            wordLadder.transfer();

        }
        System.out.println("Have a nice day");
    }
}


