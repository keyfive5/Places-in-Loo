import java.util.ArrayList;

public class Sublessor {
	public ArrayList<Posting> postings = new ArrayList<Posting>();
	
	public void createPosting(String location, Sublessor sublessor, int duration, String description, int post_id) {
		//TODO
		Posting posting = new Posting(location, sublessor, duration, description, post_id);
		postings.add(posting);
	}
	
	public void deletePosting(int post_id) {
		for(int i = 0; i < postings.length(); i++) {
			if(postings.get(i).getID() == post_id) {
				postings.remove(i);
			}
		}
	}
	public ArrayList<Posting> getPostings(){
		return postings;
	}
}
