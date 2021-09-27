import java.util.*;

/**
 * OVERVIEW: Un Social Network è un tipo di dato mutabile che contiene relazioni tra gli utenti e i post di una rete sociale.
 *
 *
 *TYPICAL ELEMENT: {(p1,[l1_1,...,l1_k]),...,(pn,[ln_1,...,ln_n]) | [li_1,...,li_m] sono utenti che hanno messo like al post pi}
 */

public interface Isocial {

    /**
     * REQUIRES:autore !=null && id!=null && contenuto!=null && autore!=stringavuota e contenuto !=stringavuota &&id!=null && id!=stringavuota
     * EFFECTS: crea un post p utilizzando autore id e contenuto come parametri del costruttore.
     *          dopo averlo creato, aggiunge p1 nella lista posts che contiene tutti i post pubblicati nel social network
     *          e aggiunge una nuova associazione nella mappa che tiene conto dei posts e dei like ricevuti
     *          inoltre inserisce l'autore del post nella mappa che tiene conto degli utenti e dei seguiti
     *          e nella mappa che tiene conto degli utenti e del corrispondente numero di followers
     *          Ritorna un riferimento al post p appena creato.
     * MODIFIES:this
     * THROWS:NullPointerException: id==null or autore==null or contenuto==null
     *        IllegalArgumentException: id=stringavuota or autore=stringavuota or contenuto=stringa vuota or contenuto.length()>140
     *        DuplicatePostException: se il post p è già presente nel social
     */
    Post createPost(String autore, String id, String contenuto) throws DuplicatePostException, NullPointerException, IllegalArgumentException;

    /**
     *REQUIRES: post p!=null
     *EFFECTS: aggiunge il post -che è stato creato all'esterno del social- al social network,
     *         in particolare viene aggiunto alla lista che contiene tutti i post presenti nel social
     *         viene aggiunta una nuova associazione alla mappa che contiene post e like ricevuti
     *         e viene inserito l'autore del post nella mappa che tiene conto degli utenti e dei seguiti
     *         e nella mappa che tiene conto degli utenti e del corrispondente numero di followers
     *MODIFIES:this
     *THROWS:NullPointerException: p==null
     *       DuplicatePostException: se il Post p che si vuol aggiungere al social network è già presente
     */
    void addPost(Post p ) throws DuplicatePostException, NullPointerException;

    /**
     * REQUIRES: ps!=null && ps!=emptyList && Ɐp ϵ ps => p!= null
     * EFFECTS: restituisce la rete sociale, ovvero una Map con le associazioni <Utente,ListaUtentiSeguiti>) derivata dalla lista ps
     *          Un utente a segue un utente b se ꓱ un post t.c p.autore=b in cui a ha messo mi piace
     * THROWS:  NullPointerException: se List ps==null o se ꓱ p ϵ ps t.c p==null
     *          EmptyListException: se la lista ps passata come parametro è vuota
     */
    Map<String,Set<String>> guessFollowers(List<Post> ps) throws EmptyListException, NullPointerException;

    /**
     * EFFECTS: restituisce la lista degli utenti più influenti del SocialNetwork, ovvero tutti gli utenti che hanno pubblicato almeno un post,
     *          odinati in ordine decrescente rispetto al numero di followers
     */
    List<String> influencers();

    /**
     * EFFECTS: restituisce l'insieme degli utenti menzionati nella rete sociale (utente menzionato==utente che ha pubblicato almeno un post)
     */
    Set<String> getMentionedUsers();

    /**
     * REQUIRES: ps !=null && ps!=EmptyList && Ɐp ϵ ps => p!= null
     * EFFECTS: restituisce l'insieme degli utenti menzionati nella lista dei post ps (utente menzionato==utente che ha pubblicato almeno un post)
     *          ovvero Ɐp ϵ ps restituisce p.autore
     * THROWS: NullPointerException: se la lista passata come parametro ps=null oppure se esiste un p appartenente a ps t.c p=null
     *          EmptyListException: se la lista passata come parametro ps è vuota
     */
    Set<String> getMentionedUsers(List<Post> ps) throws EmptyListException, NullPointerException;

    /**
     * REQUIRES: username!=null && username!=stringavuota
     * EFFECTS: restituisce la lista dei post pubblicati nel social network scritti dall'utente passato come parametro
     * THROWS:  NullPointerException: se l'username passato come parametro è =null
     *          IllegalArgumentException: se l'username passato come parametro è la stringa vuota
     */
    List<Post> writtenBy(String username) throws NullPointerException,IllegalArgumentException;

    /**
     * REQUIRES: username!=null && username!=stringavuota  && ps!=null && ps!=emptylist && Ɐp ϵ ps => p!= null
     * EFFECTS: restituisce l'insieme dei post presenti nella lista ps, scritti dall'utente "username" passato come parametro
     *          tutti i p ϵ ps tc p.autore=username
     * THROWS:  NullPointerException: se username=null || se post=null || se ꓱ p ϵ ps t.c. p=null
     *          IllegalArgumentException: se username==stringavuota
     *          EmptyListException: se la lista ps passata come parametro è la lista vuota
     */
    List<Post> writtenBy(List<Post> ps, String username) throws EmptyListException, NullPointerException, IllegalArgumentException;

    /**
     * REQUIRES: words!=null && Ɐw ϵ words => w!= null  && words!=emptyList
     * EFFECTS: restituisce la lista dei post che contengono nel loro testo almeno una delle String contenute nella lista words passata come parametro
     * THROWS: NullPointerException: se words=null oppure se c'è una Stringa w=null all'interno di words
     *          EmptyListException: se la lista words passata come parametro è la lista vuota
     */
    List<Post> containing(List<String> words) throws EmptyListException,NullPointerException;

    /**
     *REQUIRES: user!=null && user !=stringavuota && p!=null
     *EFFECTS: viene aggiornata la mappa che tiene conto dei like ricevuti inserendo l'utente che mette mi piace, e se user non è ancora follower dell'autore del post
     *          viene incrementato di uno il numero di followers di p.autore
     *THROWS: NullPointerException: se p==null || se user==null
     *        PostNotFound: se user cerca di mettere mi piace a un post non presente nel social
     *        DuplicateLikeException: se user cerca di mettere mi piace a un post a cui l'ha già messo
     *        AutolikeException: se user è proprio l'autore del post e cerca di mettersi like da solo
     */
    void addlike(String user, Post p) throws AutoLikeException, PostNotFound, DuplicateLikeException,NullPointerException;

    }

