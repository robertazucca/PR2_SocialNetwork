import java.util.*;

public class SocialNetworkModeration extends SocialNetwork implements ISocialModeration {

    /**
     * FA(snM)=FA(sn)
     *
     * IR(snM) = IR(snM) && blackList!=null && Ɐp ϵ blackList => p!= null
     */

    private List<Post> blackList; //post e lista di contenuti offensivi che contiene


    public SocialNetworkModeration() {
        super();
        blackList = new ArrayList<>();
    }

    @Override
    public Map<String, Set<String>> guessFollowers(List<Post> ps) throws EmptyListException {
        return super.guessFollowers(ps);
    }

    @Override
    public void addPost(Post p) throws DuplicatePostException {
        super.addPost(p);
    }


    public void segnala(List<String> contenutioffensivi) throws EmptyListException{

        if(contenutioffensivi==null) throw new NullPointerException();
        if(contenutioffensivi.isEmpty()) throw new EmptyListException();

        for (Post p : posts) {

            for (String tmp : contenutioffensivi) {
                if (p.getText().contains(tmp)) {
                    blackList.add(p);
                }
            }
        }

        for(Post p : blackList) {
                System.out.println("SEGNALAZIONE! Attenzione " + p.getAuthor() + ": il post " + p.getId() + " è stato segnalato. Si prega di modificarlo o verrà rimosso");
            }
        }
}
