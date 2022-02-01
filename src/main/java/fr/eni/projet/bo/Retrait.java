package fr.eni.projet.bo;
public class Retrait {
    private String rue;
    private String codePostal;
    private String ville;
    private ArticleVendu articleVendu;
    public Retrait() {
    }
    public Retrait(String rue, String codePostal, String ville, ArticleVendu articleVendu) {
        setRue(rue);
        setCodePostal(codePostal);
        setVille(ville);
        setArticleVendu(articleVendu);
    }
    public String getRue() {
        return rue;
    }
    public void setRue(String rue) {
        this.rue = rue;
    }
    public String getCodePostal() {
        return codePostal;
    }
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }
    public String getVille() {
        return ville;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }
    public ArticleVendu getArticleVendu() {
        return articleVendu;
    }
    public void setArticleVendu(ArticleVendu articleVendu) {
        this.articleVendu = articleVendu;
    }
}