public interface IPost {

    /**
     * OVERVIEW: Post è un tipo di dato immutabile caratterizzato da un id univoco, un autore, un testo di massimo 140 caratteri
     *            e un timestamp che rappresenta data e ora della pubblicazione.
     *
     * TYPICAL ELEMENT: <id,author,text,timestamp>
     *
     *
     *
     *
     *EFFECTS: restituisce una stringa che contiene l'id del post
     */
    String getId();

    /**
     *EFFECTS: restituisce una stringa che contiene l'autore del post
     */
    String getAuthor();

    /**
     * EFFECTS: restituisce una stringa che contiene il testo del post
     */
    String getText();

}
