package mp4;

import java.util.Comparator;

public class QueueComparer implements Comparator<Vertex>{

	public int compare(Vertex v1, Vertex v2){
		
		if(v1.minimumdistance < v2.minimumdistance){
			return -1;
		} else if (v1.minimumdistance > v2.minimumdistance){
			return 1;
		}
		
		return 0;
		
	}
}
