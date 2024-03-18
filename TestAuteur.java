import org.junit.*;
import static org.junit.Assert.assertEquals;

public class TestAuteur {


    String citation_guêpes =
        "TRYGÉE : Tiens, voilà quelqu'un qui s'amène avec une couronne de lauriers sur la tête.\n"
            + "LE SERVITEUR : Qui ça peut-il être ?\n"
            + "TRYGÉE : Il a une tête de charlatan.\n"
            + "LE SERVITEUR : Un devin peut-être ?";

    String citation_thesmophories =
        "La fortune a vite fait de changer en mal et de régner sous une autre face.";
    
    
    Auteur aristophane;

    @Before
    public void initVar(){
        this.aristophane = new Auteur("Aristophane", 5, this.citation_thesmophories, 100, this.citation_guêpes, 0, "");
    }


    @Test
    public void testGetCitationTragedie() {
        assertEquals(this.citation_thesmophories,this.aristophane.getCitationTragedie());
    }

    @Test
    public void testGetCitationComedie() {
        assertEquals(this.citation_guêpes,this.aristophane.getCitationComedie());
    }

    @Test
    public void testGetQualiteTragedie(){
        assertEquals(5,this.aristophane.getQualiteTragedie());
    }

}