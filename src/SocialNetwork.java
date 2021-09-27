import java.util.*;
public class SocialNetwork implements Isocial {

    /**
     * FA(sn)=  {(post_i,[li_1,..,li_k]) | li_j ha messo like a post_i  && 0<=i<posts.size() && 0<=j<blog.size()}
     *
     * IR(sn)=  IR(Post) &&
     *
     *          sn.blog != null &&
     *          Ɐ i.i ϵ sn.blog.keySet() => i!=null &&
     *          Ɐ i.i ϵ sn.blog.values() => i!=null &&
     *          Ɐ (i,j) t.c 0<=i<j<sn.blog.size() ==> sn.blog(i)!= sn.blog(j) &&
     *
     *          sn.likes!=null &&
     *          Ɐ i. i ϵ sn.likes.keySet() => i!=null
     *          Ɐ i. i ϵ sn.likes.values() => i!=null
     *          Ɐ (i,j) t.c 0<=i<j<sn.likes.size() ==> sn.likes(i) != sn.likes(j)
     *
     *          sn.posts!=null &&
     *          Ɐ i. 0<=i<=sn.posts.size(), posts(i) !=null
     *          Ɐ p ϵ sn.posts => p ϵ sn.likes.keySet() &&
     *          Ɐ(i,j) t.c. 0<=i<j<sn.posts.size() => sn.posts(i) != sn.posts(j) &&
     *
     *          sn.follows!=null &&
     *          Ɐ i. i ϵ sn.follows.keySet() => i!=null &&
     *          Ɐi. i ϵ sn.follows.values() => i!=null &&
     *          Ɐ(i,j) t.c 0<=i<j<sn.follows.size => sn.follows(i) != sn.follows(j)
     *
     */

    protected Map<String, Set<String>> blog; //String: utente -- Set: lista delle persone seguite da utente
    protected Map<Post, Set<String>> likes; //Post: post nel social -- List: lista delle persone che hanno messo like al post
    protected List<Post> posts; //lista dei post del social network
    protected Map<String,Integer> follows; //String:Utente -- Integer:numero followers


    //METODO COSTRUTTORE
    public SocialNetwork() {
        this.blog = new HashMap<>();
        this.likes = new HashMap<>();
        this.posts= new ArrayList<>();
        this.follows=new HashMap<>();

    }

    //CREA UN NUOVO POST SE VENGONO RISPETTATI I CONTROLLI
    public Post createPost(String autore, String id, String contenuto) throws DuplicatePostException{

        for(Post tmp: likes.keySet()) {
            if (tmp.getId().equals(id)){ throw new DuplicatePostException();}
        }
        for(Post tmp: posts){
            if(tmp.getId().equals(id)) throw new DuplicatePostException();
        }

        Post p= new Post(autore,id,contenuto);

        if(!blog.containsKey(autore)){ blog.put(autore, new HashSet<>());}
        if(!follows.containsKey(autore)){ follows.put(autore,0);} //inizialmente non lo segue nessuno
        likes.put(p, new HashSet<>());

        if(!posts.contains(p)){posts.add(p);}

        return p;
    }

    //AGGIUNGE POST AL SOCIAL NETWORK SE VENGONO RISPETTATI I CONTROLLI
    public void addPost(Post p) throws DuplicatePostException {

        if (p == null) throw new NullPointerException();

        for (Post tmp : likes.keySet()) {
            if (p.getId().equals(tmp.getId())) throw new DuplicatePostException();
        } //se nel social esiste già un post con questo id non posso aggiungerlo : id deve essere univoco a livello del social
        for (Post tmp : posts) {
            if (p.getId().equals(tmp.getId())) throw new DuplicatePostException();
        }

        likes.put(p, new HashSet<>());

        if (!posts.contains(p)) {
            posts.add(p);
        }

        if (!blog.containsKey(p.getAuthor())) {
            blog.put(p.getAuthor(), new HashSet<>());
        }

        if (!follows.containsKey(p.getAuthor())) {
            follows.put(p.getAuthor(), 0);
        }
    }

    //AGGIUNGE LIKE A UN POST SE VENGONO SUPERATI I CONTROLLI
    public void addlike(String user, Post p) throws AutoLikeException,PostNotFound,DuplicateLikeException{

        if(p==null) throw new NullPointerException();
        if(user == null) throw new NullPointerException();
        if(user.length()==0) throw new IllegalArgumentException();

        if(user.equals(p.getAuthor())) throw new AutoLikeException(); //eccezione: l'autore non può mettersi mi piace da solo
        if(!likes.containsKey(p)) throw new PostNotFound(); //eccezione:non si può mettere mi piace a un post che non è presente nel social

        if (!blog.containsKey(user)) {
            blog.put(user, new HashSet<>());
        }

        if(!blog.get(user).contains(p.getAuthor())) { //controllo nella mappa blog, se l'utente non segue ancora l'autore del post, aggiungo un follower e poi
                                                    // aggiungo l'autore tra le persone che user segue
            follows.put(p.getAuthor(), follows.get(p.getAuthor()) + 1);

            Set<String> seguiti = blog.get(user);
            seguiti.add(p.getAuthor());
            blog.put(user, seguiti);

        }

        if(!likes.get(p).contains(user)){
          Set<String> like = likes.get(p);
          like.add(user);
          likes.put(p, like);}
        else{ throw new DuplicateLikeException();}



    }

    //RESTITUISCE LA RETE SOCIALE DERIVATA DALLA LISTA PS
    public Map<String, Set<String>> guessFollowers(List<Post> ps) throws EmptyListException {

        if(ps==null) throw new NullPointerException();
        if(ps.isEmpty()) throw new EmptyListException();

        Map<String, Set<String>> myBlog = new HashMap<>();

        for (Post tmp : ps) {
            String autore = tmp.getAuthor();
            if (!myBlog.containsKey(autore)) {
                myBlog.put(autore, new HashSet<>());
            }

            Set<String> likespost = likes.get(tmp);

            for (String follower : likespost) {

                if (!myBlog.containsKey(follower)) {
                    myBlog.put(follower, new HashSet<>());
                }

                Set<String> listaseguiti = myBlog.get(follower);
                listaseguiti.add(autore);
                myBlog.put(follower, listaseguiti);
            }


        }
        blog = myBlog; //assegno alla mappa del social il contenuto di myBlog così quando chiamo il metodo
                        //sulla lista di post del mio social riempio la mappa
        return myBlog;
    }


    //RESTITUISCE UTENTI DEL SOCIAL IN ORDINE DECRESCENTE IN BASE AL NUMERO DI FOLLOWERS
    public List<String> influencers() {

        List<Map.Entry<String, Integer>> influencersdec = new ArrayList<>(follows.entrySet());
        influencersdec.sort(Map.Entry.comparingByValue());  //ordine crescente

        List<String> result= new ArrayList<>();

        for(int j=influencersdec.size()-1;j>=0;j--){ //vado al contrario perchè precedentemente ordinati in modo crescente
            result.add((influencersdec.get(j).getKey()));
        }

        return result;
    }


    //RESTITUISCE L'INSIEME DI UTENTI CHE HANNO PUBBLICATO ALMENO UN POST NEL SOCIALNETWORK
    public Set<String> getMentionedUsers() {

        Set<String> mentioned = new HashSet<>();
        for (Post p : likes.keySet()) {
            mentioned.add(p.getAuthor()); //utente menzionato=utente che è autore di almeno un post
        }

        return mentioned;
    }

    //RESTITUISCE L'INSIEME DEGLI UTENTI CHE HANNO PUBBLICATO ALMENO UN POST NELLA LISTA PS
    public Set<String> getMentionedUsers(List<Post> ps) throws EmptyListException{

        if(ps== null) throw new NullPointerException();
        if(ps.isEmpty()) throw new EmptyListException();

        Set<String> mentioned = new HashSet<>();
        for(Post p: ps){
            mentioned.add(p.getAuthor());
        }

        return mentioned;
    }

    //RESTITUISCE L'INSIEME DEI POST DEL SOCIAL SCRITTI DALL'UTENTE PASSATO COME PARAMETRO
    public List<Post> writtenBy(String username){

        if(username == null) throw new NullPointerException();
        if(username.length()==0) throw new IllegalArgumentException();

        List<Post> postautore=new ArrayList<>();
        for(Post tmp: likes.keySet()) {
            if (tmp.getAuthor().equals(username)) {
                postautore.add(tmp);
            }
        }
        return postautore;


    }

    //RESTITUISCE L'INSIEME DEI POST DELLA LISTA PS SCRITTI DALL'UTENTE PASSATO COME PARAMETRO
    public List<Post> writtenBy(List<Post> ps, String username) throws EmptyListException{

        if(ps==null||username==null) throw new NullPointerException();
        if(ps.isEmpty()) throw new EmptyListException();
        if(username.length()==0) throw new IllegalArgumentException();
        List<Post> postautore=new ArrayList<>();

        for(Post tmp: ps){
            if(tmp.getAuthor().equals(username)){
                postautore.add(tmp);
            }
        }

        return postautore;
    }

    //RESTITUISCE LA LISTA DI POST PUBBLICATI NEL SOCIAL CHE INCLUDONO ALMENO UNA DELLE PAROLE CONTENUTE IN WORDS
    public List<Post> containing(List<String> words) throws EmptyListException {

        if(words==null) throw new NullPointerException();
        if(words.isEmpty()) throw new EmptyListException();

        List<Post> lista = new ArrayList<>();

        for (Post tmp : likes.keySet()) {

            for (String tmp1 : words) {
                if (tmp.getText().contains(tmp1)) {
                    lista.add(tmp);
                }
            }
        }
        return lista;
    }


}

