import java.util.ArrayList;

public class Sublessor {
	public ArrayList<Posting> postings = new ArrayList<Posting>();
	
	public void createPosting() {
		//TODO
	}
	
	public void deletePosting(post_id ID) {
		for(int i = 0; i < postings.length(); i++) {
			if(postings.get(i).getID() == ID) {
				postings.remove(i);
			}
		}
	}
	public ArrayList<Posting> getPostings(){
		return postings;
	}
}
