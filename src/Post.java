import java.sql.Timestamp;
public class Post implements IPost{

    /**
     * AF(p) : f(p)= <id,autore,testo,dataeora>
     * IR(p): p.id!=null && p.id!=stringavuota && p.author!=null && p.author!=stringavuota && p.text!=null && p.text.length()<=140 && p.text!=stringaVuota
     */


    private final String id;
    private final String author;
    private final String text;
    private final Timestamp timestamp;


    /**
     *REQUIRES:user !=null && user !=stringavuota
     *         idPost !=null && idPost !=stringavuota
     *         contenuto!=null && contenuto!=stringavuota && contenuto.length()>140
     *MODIFIES:this
     *THROWS: NullPointerException: se user=null or idPost=null or contenuto=null
     *         IllegalArgumentException: se user=stringavuota or idPost=stringavuota or contenuto=stringavuota or contentolenght>140
     */
    public Post(String user, String idPost, String contenuto){

        if(user== null || idPost== null || contenuto== null) throw new NullPointerException();
        if(idPost.length()==0||user.length()==0||contenuto.length()==0) throw new IllegalArgumentException();
        if(contenuto.length()>140) throw new IllegalArgumentException();

        this.id=idPost;
        this.author=user;
        this.text=contenuto;

        this.timestamp=new Timestamp(System.currentTimeMillis());
    }

    public String getId(){
        return this.id;
    }

    public String getAuthor(){
        return this.author;
    }

    public String getText(){
        return this.text; }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", text='" + text + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
