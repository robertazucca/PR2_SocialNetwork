import java.util.List;

public interface ISocialModeration {

    /**OVERVIEW: SocialNetworkModeration è un tipo di dato mutabile che estende SocialNetwork con il metodo segnala
     *
     * TYPICAL ELEMENT: {(p1,[l1_1,...,l1_k]),...,(pn,[ln_1,...,ln_n]) | [li_1,...,li_m] sono utenti che hanno messo like al post pi}
     */


    /**
     * REQUIRES: contenutioffensivi!=null && Ɐc ϵ contenutioffensivi => c!=null && contenutioffensivi !=listavuota
     * EFFECTS: viene controllato il testo di ogni post presente nel social e se contiene uno o più contenuti offensivi, viene creata una nuova associazione
     *          per il Post nella map contenente <Post,contenutioffensivi>
     *          viene infine stampato un messaggio di segnalazione che indica quale post è stato segnalato e quali sono i contenuti offensivi in esso presenti
     * MODIFIES:this
     *THROWS: NullPointerException: se la lista passata come parametro è null o se ꓱ c ϵ contenutioffensivi t.c. c!=null
     *        EmptyListException: se la lista passata come parametro è la lista vuota
     */
    void segnala(List<String> contenutioffensivi) throws EmptyListException,NullPointerException;

}
