/* Labb 2 i DD1352 Algoritmer, datastrukturer och komplexitet    */
/* Se labbanvisning under kurswebben https://www.kth.se/social/course/DD1352 */
/* Ursprunglig författare: Viggo Kann KTH viggo@nada.kth.se      */
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ClosestWords {
	LinkedList<String> closestWords = null;
	int[][] matrix = null;
	int minDistance = 0;
	int closestDistance = -1;

	int partDist(String w1, String w2, int w1len, int w2len) {
		if(checkMatrix(w1len, w2len)){	// If value exists
			// System.out.println(matrix[w1len][w2len]);
			return matrix[w1len][w2len];	 
		}
		
		if (w1len == 0) {
			return w2len;
		}
		if (w2len == 0) {
			return w1len;
		}
    
		int res = partDist(w1, w2, w1len - 1, w2len - 1) + 
				(w1.charAt(w1len - 1) == w2.charAt(w2len - 1) ? 0 : 1);
		int addLetter = partDist(w1, w2, w1len - 1, w2len) + 1;
		
		if (addLetter < res)
			res = addLetter;
		int deleteLetter = partDist(w1, w2, w1len, w2len - 1) + 1;
		if (deleteLetter < res){
			res = deleteLetter;
		}
		matrix[w1len][w2len] = res;
		return res;
	}

	int Distance(String w1, String w2) {
		matrix = new int[w1.length() + 1][w2.length() + 1]; //initialize matrix with the right dimensions
	    for(int i = 0; i < matrix.length; i++) {
	    	for(int j = 0; j < matrix[i].length; j++) {
	    		matrix[i][j] = -1;
	    	}
	    }
	  // System.out.println(Arrays.deepToString(matrix));
    return partDist(w1, w2, w1.length(), w2.length());
	}

	public ClosestWords(String w, List<String> wordList) {
		for (String s : wordList) {
			//System.out.println("w: " + w.length());
			if((s.length() <= (w.length() + minDistance)) || (w.equals(""))){
				int dist = Distance(w, s);
				// System.out.println("d(" + w + "," + s + ")=" + dist);
				if (dist < closestDistance || closestDistance == -1) {
					minDistance = dist;
					closestDistance = dist;
					closestWords = new LinkedList<String>();
					closestWords.add(s);
				} else if (dist == closestDistance)
					closestWords.add(s);
			}
		}
			
	}

	int getMinDistance() {
		return closestDistance;
	}

	List<String> getClosestWords() {
		return closestWords;
	}
  
	public boolean checkMatrix(int w1Index, int w2Index){
		return (matrix[w1Index][w2Index] != -1) ? true : false;
	}
}
