/* Labb 2 i DD1352 Algoritmer, datastrukturer och komplexitet    */
/* Se labbanvisning under kurswebben https://www.kth.se/social/course/DD1352 */
/* Ursprunglig författare: Viggo Kann KTH viggo@nada.kth.se      */
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ClosestWords {
	LinkedList<String> closestWords = null;
	int[][] matrix = new int[40][40];
	//int counter = 0;
	//int wordFiltered = 0;
	String previousWord = "";
	int minDistance = 0;
	int closestDistance = -1;

	int partDist(String w1, String w2, int w1len, int w2len) {
		if (w1len == 0) {
			return w2len;
		}
		if (w2len == 0) {
			return w1len;
		}
		
		int res = matrix[w1len - 1][w2len - 1] +
			(w1.charAt(w1len - 1) == w2.charAt(w2len - 1) ? 0 : 1);
		
		int addLetter = matrix[w1len - 1][w2len] + 1;
		
		if (addLetter < res){
			res = addLetter;
		}
		
		int deleteLetter = matrix[w1len][w2len - 1] + 1;
		if (deleteLetter < res){
			res = deleteLetter;
		}
		return res;
	}

	int Distance(String w1, String w2) {
		int subWordCount = wordMatch(w2);
		System.out.println("subword: " + subWordCount);
	    System.out.println("Previous word: " + previousWord);
		for(int i = subWordCount; i <= w1.length(); i++) {
	    	for(int j = 0; j <= w2.length(); j++) {
	    		matrix[i][j] = partDist(w1,w2, i, j);
	    	}
	    }
	//System.out.println(Arrays.deepToString(matrix));
	    previousWord = w2;
	    return matrix[w1.length()][w2.length()];
	}
	
	private int wordMatch(String w2) {
		int count = 0;
		int interval;
		if(w2.length() >= previousWord.length()) {
			interval = previousWord.length();
		} else {
			interval = w2.length();
		}
		
		for(int i = 0; i < interval; i++) {
			if(w2.charAt(i) == previousWord.charAt(i)) {
				count++;
			}
		}
		return count;
	}
	
	public ClosestWords(String w, List<String> wordList) {
		for (String s : wordList) {
			
//	System.out.println("The word is next to get testet: " + s);	
			if(((s.length() <= (w.length() + minDistance)) && (s.length() >= (w.length() - minDistance))) || closestWords == null){
			//System.out.println("The word is now in if statement: " + s);	
				//wordFiltered++;
				int dist = Distance(w, s);
		//		System.out.println("d(" + w + "," + s + ")=" + dist);
				if (dist < closestDistance || closestDistance == -1) {
					minDistance = dist;
					closestDistance = dist;
					closestWords = new LinkedList<String>();
					closestWords.add(s);
				} else if (dist == closestDistance)
					closestWords.add(s);
			}
			//counter++;
		}
	
		//	System.out.println("ngt matchar inte");
	}
	
	int getMinDistance() {
		//System.out.println("Words searched: " + counter + " Words skipped: " + (counter - wordFiltered) + " Times in if: " + wordFiltered);
		return closestDistance;
	}

	List<String> getClosestWords() {
		return closestWords;
	}
  
	public boolean checkMatrix(int w1Index, int w2Index){
		return (matrix[w1Index][w2Index] != -1) ? true : false;
	}
}
