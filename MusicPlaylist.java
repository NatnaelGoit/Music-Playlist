import java.util.*;
import java.io.*;

public class MusicPlaylist {
   public static void main (String[] args){
      Queue<String> userPlaylist = new LinkedList<>();
      Stack<String> userHistory = new Stack<>();
      Stack<String> currentUserHistory = new Stack<>();
      System.out.println("Welcome to the CSE 122 Music Playlist!");
      System.out.println("(A) Add song");
      System.out.println("(P) Play song");
      System.out.println("(Pr) Print history");
      System.out.println("(C) Clear history");
      System.out.println("(D) Delete from history");
      System.out.println("(Q) Quit");
      System.out.println();
      System.out.print("Enter your choice: ");
      Scanner console = new Scanner (System.in);
      String userChoice = console.nextLine();
      while (!userChoice.equalsIgnoreCase("Q")){
         if (userChoice.equalsIgnoreCase("A")){
            addSong(console, userPlaylist);
         }else if (userChoice.equalsIgnoreCase("P")){
            playSong(userPlaylist, userHistory);
         }else if (userChoice.equalsIgnoreCase("Pr")){
            printHistory(userHistory, currentUserHistory);
         }else if (userChoice.equalsIgnoreCase("C")){
            clearHistory(userHistory);
         }else if (userChoice.equalsIgnoreCase("D")){
            deleteHistory(console, userHistory);
         }
         System.out.println();
         System.out.println("(A) Add song");
         System.out.println("(P) Play song");
         System.out.println("(Pr) Print history");
         System.out.println("(C) Clear history");
         System.out.println("(D) Delete from history");
         System.out.println("(Q) Quit");
         System.out.println();
         System.out.print("Enter your choice: ");
         userChoice = console.nextLine();
      }
   }
   
   public static void addSong(Scanner console, Queue<String> userPlaylist){
      System.out.print("Enter song name: ");
      String songName = console.nextLine();
      userPlaylist.add(songName);
      System.out.println("Successfully added " + songName);
      System.out.println();
   }
   
   public static void playSong(Queue<String> userPlaylist, Stack<String> userHistory){
      if (userPlaylist.size() == 0){
         throw new IllegalStateException();
      }
      String currentSong = userPlaylist.remove();
      System.out.println("Playing song: " + currentSong);
      userHistory.push(currentSong);
      System.out.println();
   }
   
   public static void replaceValues(Stack<String> currentUserHistory, Stack<String> userHistory){
      while (!currentUserHistory.isEmpty()){
         String oldValues = currentUserHistory.pop();
         userHistory.push(oldValues);
      }
   }

   public static void printHistory(Stack<String> userHistory, Stack<String> currentUserHistory){
      if (userHistory.size() == 0){
         throw new IllegalStateException();
      }
      String numberedSong = null;
      while (!userHistory.isEmpty()){
         numberedSong = userHistory.pop();
         System.out.println("    " + numberedSong);
         currentUserHistory.push(numberedSong);
      }
      replaceValues(currentUserHistory, userHistory);
   }
   
   public static void clearHistory(Stack<String> userHistory){
      while (!userHistory.isEmpty()){
         userHistory.pop();
      }
      System.out.println(userHistory);
   }
   
   public static void deleteHistory(Scanner console, Stack<String> userHistory){
      System.out.println("A positive number will delete from recent history.");
      System.out.println("A negative number will delete from the beginning of history.");
      System.out.print("Enter number of songs to delete: ");
      int howManyDeletedSongs = Integer.parseInt(console.nextLine());
      int deletedSongs = Math.abs(howManyDeletedSongs);
      if (userHistory.size() < deletedSongs){
         throw new IllegalArgumentException();
      }
      Stack<String> currentUserHistory = new Stack<>();
      if (howManyDeletedSongs > 0){
         for (int i = 0; i < deletedSongs; i++) {
            userHistory.pop();
         }
      }else if (howManyDeletedSongs < 0){
         replaceValues(userHistory, currentUserHistory);
         for (int i = 0; i < deletedSongs; i++){
            currentUserHistory.pop();
         }
         replaceValues(currentUserHistory, userHistory);  
      }  
   }
}
