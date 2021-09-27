import java.util.*;

public class Test {

    public static void TestSocial() throws AutoLikeException, EmptyListException, DuplicatePostException,PostNotFound,DuplicateLikeException {

        SocialNetwork mySocial = new SocialNetwork();
        List<Post> allposts = new ArrayList<>();

        System.out.println("\n************INIZIO TEST SOCIAL NETWORK************\n");

        //creazione post nel social network
        Post p1 = mySocial.createPost("Roberta", "lh26", "Mi chiamo Roberta, sono una studentessa di Informatica");
        Post p2 = mySocial.createPost("Nicola", "l566", "My name is Nicola, I'm from London; contenuto2");
        Post p3 = mySocial.createPost("Andrea", "lk13", "ciao! Mi piace ascoltare musica classica");
        Post p77 = mySocial.createPost("Roberta", "llll", "ciao! Mi piace ascoltare musica classica");

        //creazione post
        Post p4 = new Post("Silvio", "lj98", "Vorrei fare il giro del mondo, contenuto1");
        Post p5 = new Post("Giacomo", "kk00", "Che bello!");
        Post p6 = new Post("Giulia","jjy1","ciao a tutti");

        System.out.println("\n******TEST METODO CREATEPOST******");
        //Test sull'autore errato
        System.out.println("--Test sull'autore--");
        try {
            System.out.println("\n***Inserisco un autore del post nullo: mi aspetto NullPointerException***");
            mySocial.createPost(null, "kk99", "Ciao a tutti");
        } catch (NullPointerException e) {
            System.out.println("ERRORE! Autore nullo.");
        }
        try {
            System.out.println("\n***Inserisco un autore del post vuoto: mi aspetto IllegalArgumentException***");
            mySocial.createPost("", "kk99", "Ciao a tutti");
        } catch (IllegalArgumentException e) {
            System.out.println("ERRORE! Stringa autore vuota.");
        }


        //Test su id errato
        System.out.println("\n******Test sull'ID******");
        try {
            System.out.println("\n***Inserisco un id del post nullo: mi aspetto NullPointerException***");
            mySocial.createPost("Roberta", null, "Ciao a tutti");
        } catch (NullPointerException e) {
            System.out.println("ERRORE! Id post nullo.");
        }
        try {
            System.out.println("\n***Inserisco un id vuoto: mi aspetto IllegalArgumentException***");
            mySocial.createPost("Roberta", "", "Ciao a tutti");
        } catch (IllegalArgumentException e) {
            System.out.println("ERRORE! Id vuoto.");
        }
        try {
            System.out.println("\n***Provo a creare un post con id uguale a un post già creato: mi aspetto DuplicateIdException***");
            mySocial.createPost("Giorgia", "lk13", "Che bello!");
        } catch (DuplicatePostException e){
            System.out.println("ERRORE! E' già presente un post con questo id nel Social.");
        }

        //Test su testo
        System.out.println("\n******Test sul contenuto******");
        try {
            System.out.println("\n***Inserisco un testo del post nullo: mi aspetto NullPointerException***");
            mySocial.createPost("Roberta", "kj98", null);
        } catch (NullPointerException e) {
            System.out.println("ERRORE! Testo post nullo.");
        }
        try {
            System.out.println("\n***Inserisco un testo con lunghezza superiore a 140: mi aspetto IllegalArgumentException***");
            mySocial.createPost("Roberta", "k990", "ABCDEFGHIJKLMNOPQRTSUVWXYZABCDEFGHIJKLMNOPQRTSUVWXYZABCDEFGHIJKLMNOPQRTSUVWXYZABCDEFGHIJKLMNOPQRTSUVWXYZABCDEFGHIJKLMNOPQRTSUVWXYZABCDEFGHIJKLMNOPQRTSUVWXYZABCDEFGHIJKLMNOPQRTSUVWXYZ");
        } catch (IllegalArgumentException e) {
            System.out.println("ERRORE! LunghezzaTesto>140.");
        }
        try {
            System.out.println("\n***Inserisco un testo del post vuoto: mi aspetto IllegalArgumentException***");
            mySocial.createPost("Roberta", "kj98", "");
        } catch (IllegalArgumentException e) {
            System.out.println("ERRORE! Nessun testo.");
        }


        //--> aggiungo p4 al Social Network
        mySocial.addPost(p4);
        System.out.println("\n******TEST METODO ADDPOST******");
        try {
            System.out.println("\n***Provo ad aggiungere al social un post con id uguale a un post già esistente nel Social: mi aspetto DuplicatePostException***");
            mySocial.addPost(p4);
        } catch (DuplicatePostException e){
            System.out.println("ERRORE! E' già presente questo post nel Social.");
        }


        //-->aggiungo p4, p5 e p6 alla lista allposts
        allposts.add(p4);
        allposts.add(p5);
        allposts.add(p6);


        System.out.println("\n******TEST METODO ADDLIKE******");
        try {
            System.out.println("\n***Inserisco un utente che deve mettere mi piace al post nullo: mi aspetto NullPointerException***");
            mySocial.addlike(null, p1);
        } catch (NullPointerException e) {
            System.out.println("ERRORE! L'utente che sta provando a mettere mi piace è null.");
        }
        try {
            System.out.println("\n***Inserisco un utente vuoto: mi aspetto IllegalArgumentException***");
            mySocial.addlike("", p1);
        } catch (IllegalArgumentException e) {
            System.out.println("ERRORE! L'utente che sta provando a mettere mi piace è vuoto, ");
        }
        try {
            System.out.println("\n***Passo come utente che deve mettere mi piace l'autore del post: mi aspetto AutoLikeException***");
            mySocial.addlike("Roberta", p1);
        } catch (AutoLikeException e) {
            System.out.println("ERRORE! L'autore sta provando a mettersi mi piace da solo.");
        }
        try {
            System.out.println("\n***Passo come post a cui l'utente deve mettere mi piace un post che non è presente nel social: mi aspetto PostNotFound***");
            mySocial.addlike("Roberta", p6);
        } catch (PostNotFound e) {
            System.out.println("ERRORE! Il post a cui si vuole mettere mi piace non è presente nel social.");
        }


        //aggiungo like ai post con il metodo addlike
        mySocial.addlike("Giacomo", p2);
        mySocial.addlike("Silvia", p3);
        mySocial.addlike("Paolo", p1);
        mySocial.addlike("Andrea", p1);
        mySocial.addlike("Nicola", p1);
        mySocial.addlike("Roberta", p2);
        mySocial.addlike("Nicola", p3);
        mySocial.addlike("Giulia",p1);
        mySocial.addlike("Marcella",p2);
        mySocial.addlike("Andrea",p77);

        try {
            System.out.println("\n***Provo a far mettere like a un utente che l'ha già messo: mi aspetto DuplicateLikeException***");
            mySocial.addlike("Giulia", p1);
        } catch (DuplicateLikeException e) {
            System.out.println("ERRORE! L'utente ha già messo like a questo post .");
        }


        System.out.println("\n******TEST METODO GUESSFOLLOWERS******");
        try {
            System.out.println("\n***Passo la lista null come parametro: mi aspetto NullPointerException***");
            mySocial.guessFollowers(null);
        } catch (NullPointerException e) {
            System.out.println("ERRORE! La lista dei post è null.");
        }
        List<Post> listavuota= new ArrayList<>();
        try {
            System.out.println("\n***Passo una lista vuota come parametro: mi aspetto EmptyListException***");
            mySocial.guessFollowers(listavuota);
        } catch (EmptyListException e) {
            System.out.println("ERRORE! La lista dei post è vuota.");
        }
        //testo il metodo guessFollowers passando come parametro la lista dei post del social
        //in questo modo crea la rete sociale del social network
        System.out.println("\nRete sociale ottenuta dall'analisi dei post: " + "\n" + mySocial.guessFollowers(mySocial.posts));

        //stampo la mappa follow per vedere gli utenti e il rispettivo numero di seguaci
        //e verificare se influencers funziona correttamente
        System.out.println("\nMappa Utente-NumeroFollowers: " + mySocial.follows);

        System.out.println("\n******TEST METODO INFLUENCERS******");
        System.out.println("\nGli utenti più influenti del social network sono: " + mySocial.influencers());

        System.out.println("\n******TEST METODO GETMENTIONEDUSERS******");
        System.out.println("\nGli utenti che hanno postato almeno un post nel social sono: " + mySocial.getMentionedUsers());

        System.out.println("\n------TESTING METODO GETMENTIONEDUSERS con parametro-------"); //mi aspetto risultato uguale al metodo precedente
        try {
            System.out.println("\n***Passo la lista null come parametro: mi aspetto NullPointerException***");
            mySocial.getMentionedUsers(null);
        } catch (NullPointerException e) {
            System.out.println("ERRORE! La lista dei post è null.");
        }
        try {
            System.out.println("\n***Passo una lista vuota come parametro: mi aspetto EmptyListException***");
            mySocial.guessFollowers(listavuota);
        } catch (EmptyListException e) {
            System.out.println("ERRORE! La lista dei post è vuota.");
        }
        System.out.println("\n******TEST METODO GETMENTIONEDUSERS -- passando la lista di tutti i post del social" + "\n*mi aspetto risultatio uguale al metodo precedente senza parametro"); //mi aspetto risultato uguale al metodo precedente
        System.out.println(mySocial.getMentionedUsers(mySocial.posts));

        System.out.println("******TEST METODO GETMENTIONEDUSERS -- passando una lista casuale");
        System.out.println("\n Gli utenti che hanno postato almeno un post della lista sono: " + mySocial.getMentionedUsers(allposts));


        System.out.println("\n******TEST METODO WRITTENBY******");
        try {
            System.out.println("\n***Passo utente null come parametro: mi aspetto NullPointerException***");
            mySocial.writtenBy(null);
        } catch (NullPointerException e) {
            System.out.println("ERRORE! L'utente passato come parametro è null.");
        }
        System.out.println("\nPost scritti da Roberta:" + mySocial.writtenBy("Roberta"));
        mySocial.writtenBy("Mario"); //test passando utente che non ha scritto nemmeno un post


        System.out.println("\n******TEST METODO WRITTENBY + lista******");
        try {
            System.out.println("\n***Passo una lista vuota come parametro: mi aspetto EmptyListException***");
            mySocial.writtenBy(listavuota, "Roberta");
        } catch (EmptyListException e) {
            System.out.println("ERRORE! La lista dei post passata è vuota.");
        }
        try {
            System.out.println("\n***Passo la lista null come parametro: mi aspetto NullPointerException***");
            mySocial.writtenBy(null, "Roberta");
        } catch (NullPointerException e) {
            System.out.println("ERRORE! La lista dei post passata è null.");
        }
        try {
            System.out.println("\n***Passo un utente con username vuoto: mi aspetto IllegalArgumentException***");
            mySocial.writtenBy(allposts, "");
        } catch (IllegalArgumentException e) {
            System.out.println("ERRORE! L'username passato è la stringa vuota.");
        }
        try {
            System.out.println("\n***Passo un utente null: mi aspetto NullPointerException***");
            mySocial.writtenBy(allposts, null);
        } catch (NullPointerException e) {
            System.out.println("ERRORE! L'username passato è null.");
        }
        System.out.println("\n Post scritti da Roberta presenti nella lista: " + mySocial.writtenBy(allposts, "Roberta") + "\n");

        //creo lista parole
        List<String> words = new ArrayList<>();
        words.add("ciao");
        words.add("sissi");
        words.add("bello");
        //creo la lista vuota
        List<String> listavuota1 = new ArrayList<>();

        System.out.println("******TEST METODO CONTAINING******");
        try {
            System.out.println("\n***Passo una lista vuota come parameto: mi aspetto EmptyListException***");
            mySocial.containing(listavuota1);
        } catch (EmptyListException e) {
            System.out.println("ERRORE! La lista passata è vuota.");
        }
        try {
            System.out.println("\n***Passo una lista  null come parameto: mi aspetto NullPointerException***");
            mySocial.containing(null);
        } catch (NullPointerException e) {
            System.out.println("ERRORE! La lista passata è null.");
        }

        System.out.println("\nI post che contengono almeno una delle parole: "+ words + " sono i seguenti: \n" + mySocial.containing(words));

    }

    public static void TestSocialModeration() throws DuplicatePostException,EmptyListException,AutoLikeException,PostNotFound,DuplicateLikeException{

        SocialNetworkModeration mySocial1= new SocialNetworkModeration();
        System.out.println("\n\n\n************INIZIO TEST SOCIAL NETWORK MODERATION************\n");

        Post p7 = mySocial1.createPost("Roberta", "lh26", "Mi chiamo Roberta, sono una studentessa di Informatica");
        Post p8 = mySocial1.createPost("Nicola", "l566", "My name is Nicola, I'm from London; contenuto2");
        Post p5 = new Post("Giacomo", "kk00", "Che bello contenuto1!");

        mySocial1.addPost(p5);
        mySocial1.addlike("Jack",p5);
        mySocial1.addlike("Greta",p8);
        mySocial1.addlike("Patrik",p7);
        mySocial1.addlike("Kevin",p7);


        System.out.println("\nMappa Utenti-Seguiti: " + mySocial1.guessFollowers(mySocial1.posts));

        List<String> contenutioff = new ArrayList<>();
        contenutioff.add("contenuto1");
        contenutioff.add("contenuto2");

        System.out.println("******TEST METODO SEGNALA******");
        mySocial1.segnala(contenutioff);
    }



    public static void main(String[] args) throws AutoLikeException, EmptyListException,DuplicatePostException,PostNotFound,DuplicateLikeException {
        TestSocial();
        TestSocialModeration();
    }
}