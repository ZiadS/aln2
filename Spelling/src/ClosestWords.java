/* Labb 2 i DD1352 Algoritmer, datastrukturer och komplexitet    */
/* Se labbanvisning under kurswebben https://www.kth.se/social/course/DD1352 */
/* Ursprunglig författare: Viggo Kann KTH viggo@nada.kth.se      */
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ClosestWords {
	LinkedList<String> closestWords = null;
	int[][] matrix = new int[7][7];
	//int counter = 0;
	//int wordFiltered = 0;
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
	    for(int i = 0; i < w1.length() + 1; i++) {
	    	for(int j = 0; j < w2.length() + 1; j++) {
	    		matrix[i][j] = partDist(w1,w2,i,j);
	    	}
	    	
	    }

	  System.out.println(Arrays.deepToString(matrix));
    return matrix[w1.length() - 1][w2.length() - 1];
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
